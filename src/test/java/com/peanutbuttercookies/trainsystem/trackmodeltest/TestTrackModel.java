package com.peanutbuttercookies.trainsystem.trackmodeltest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.interfaces.ExcelFileDownloaderInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.trackmodel.TrackModelUI;
import com.peanutbuttercookies.trainsystem.train.TrainModelInterface;

public class TestTrackModel implements TrackModelInterface {
	private TrackControllerInterface trackComm;
	private TrainModelInterface trainComm;
	private TrackModelUI tmUI;
	private ExcelFileDownloaderInterface excelDownloader;
	private LinkedList<Block> line;
	private LinkedList<Line> track;
	private Map<String, LinkedList<Block>> yardMap;
	private Map<Integer, LinkedList<Block>> switchMap;

	public TestTrackModel() throws IOException {
	}

	public void fileRead(String filename) throws IOException {
		track = new LinkedList<Line>();
		line = new LinkedList<Block>();
		yardMap = new HashMap<String, LinkedList<Block>>();
		switchMap = new HashMap<Integer, LinkedList<Block>>();
		filename = filename.replace("\\", "/");
		excelReader(filename);
		findDirection();
		organizeSwitch();
		setNextPossible();
		makeYard();
		organizeYard();
		initSwitchDirection();
	}

	public void excelReader(String filePath) throws IOException {
		FileInputStream inputStream = new FileInputStream(new File(filePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		int sheetNum = workbook.getNumberOfSheets();
		for (int i = 0; i < sheetNum; i++) {
			Sheet firstSheet = workbook.getSheetAt(i);
			Iterator<Row> iterator = firstSheet.iterator();
			String lineName = firstSheet.getSheetName();
			if (lineName.contains("Line")) {
				while (iterator.hasNext()) {
					Row nextRow = iterator.next();
					Iterator<Cell> cellIterator = nextRow.cellIterator();
					if (nextRow.getCell(0) != null && !nextRow.getCell(0).getStringCellValue().equals("Line")) {
						iterateRow(nextRow);
					}
				}
				LinkedList<Block> newLine = new LinkedList<Block>(line);
				Line newTrack = new Line(line.get(0).getLine(), newLine);
				track.add(newTrack);
				line.clear();
			}

		}

	}

	public void iterateRow(Row row) {
		String lineStr = row.getCell(0).getStringCellValue();
		String section = row.getCell(1).getStringCellValue();
		int blockNum = (int) row.getCell(2).getNumericCellValue();
		double blockLen = row.getCell(3).getNumericCellValue();
		double blockGrade = row.getCell(4).getNumericCellValue();
		double speedLim = row.getCell(5).getNumericCellValue();
		String infra = "";
		boolean toYard = false;
		boolean fromYard = false;
		boolean hasUnderground = false;
		boolean hasCrossing = false;
		boolean hasStation = false;
		boolean masterSwitch = false;
		String station = "";
		if (row.getCell(6) != null) {
			infra = row.getCell(6).getStringCellValue();
			if (infra.contains("SWITCH")) {
				masterSwitch = true;
			}
			if (infra.contains("TO YARD")) {
				toYard = true;
			}
			if (infra.contains("FROM YARD")) {
				fromYard = true;
			}
			if (infra.contains("TO/FROM YARD")) {
				toYard = true;
				fromYard = true;
			}
			if (infra.contains("UNDERGROUND")) {
				hasUnderground = true;
			}
			if (infra.contains("CROSSING")) {
				hasCrossing = true;
			}
			if (infra.contains("STATION")) {
				hasStation = true;
				String delims = "[ ;]+";
				String[] tokens = infra.split(delims);
				for (int i = 0; i < tokens.length; i++) {
					if (!tokens[i].equals("UNDERGROUND") && !tokens[i].contains("STATION")
							&& !tokens[i].equals("CROSSING") && !tokens[i].equals("RAILWAY"))
						if (station.length() > 1) {
							station = station + " " + tokens[i];
						} else {
							station = station + tokens[i];
						}
				}
			}
		}
		double elev = row.getCell(8).getNumericCellValue();
		double cumElev = row.getCell(9).getNumericCellValue();
		int switchBlock = -1;
		boolean hasSwitch = false;
		if (row.getCell(10) != null) {
			String cellInfo = row.getCell(10).getStringCellValue();
			cellInfo = cellInfo.replaceFirst(".*?(\\d+).*", "$1");
			switchBlock = Integer.parseInt(cellInfo);
			hasSwitch = true;
		}
		String arrow = "";
		int arrowInt = 0;
		if (row.getCell(11) != null) {
			arrow = row.getCell(11).getStringCellValue();
			if (arrow.contains("Head")) {
				arrowInt = 1;
			}
			if (arrow.contains("Tail")) {
				arrowInt = -1;
			}
			if (arrow.contains("Tail/Head")) {
				arrowInt = 2;
			}
			if (arrow.contains("Head/Tail")) {
				arrowInt = 3;
			}
			if (arrow.contains("Head/Head")) {
				arrowInt = 4;
			}
		}
		Block newBlock = new Block(lineStr, section, blockNum, blockLen, blockGrade, speedLim, elev, cumElev, toYard,
				fromYard, hasSwitch, hasUnderground, hasCrossing, hasStation, station, switchBlock, arrowInt);
		if (toYard || fromYard) {
			if (yardMap.get(lineStr) == null) {
				LinkedList<Block> newList = new LinkedList<Block>();
				newList.add(newBlock);
				yardMap.put(lineStr, newList);
			} else {
				LinkedList<Block> newList = yardMap.get(lineStr);
				newList.add(newBlock);
				yardMap.put(lineStr, newList);
			}
		}
		if (hasSwitch) {

			if (switchMap.get(switchBlock) == null) {
				LinkedList<Block> newList = new LinkedList<Block>();
				newList.add(newBlock);
				switchMap.put(switchBlock, newList);
			} else {
				LinkedList<Block> newList = switchMap.get(switchBlock);
				newList.add(newBlock);
				switchMap.put(switchBlock, newList);
			}
		}
		newBlock.setMasterSwitch(masterSwitch);
		line.add(newBlock);
	}

	public void findDirection() {
		for (int i = 0; i < track.size(); i++) {
			line = track.get(i).getAllBlocks();
			for (int j = 0; j < line.size(); j++) {
				String section = line.get(j).getSection();
				int secStartDir = line.get(j).getArrowDirection();
				int secEndDir = 0;
				int count = j + 1;
				while (count < line.size() && line.get(j).getSection().equals(line.get(count).getSection())) {
					secEndDir = line.get(count).getArrowDirection();
					count++;
				}
				if (secStartDir == 3) {
					line.get(j).setBackwards();
				}
				if (secStartDir == 4) {
					line.get(j).setTwoWay(true);
				}
				if (secStartDir == 1 && secEndDir == -1) {
					for (int k = j; k < count; k++) {
						line.get(k).setBackwards();
					}
				}
				if (secStartDir == 1 && secEndDir == 1) {
					for (int k = j; k < count; k++) {
						line.get(k).setTwoWay(true);
					}
				}
				j = count - 1;
			}
		}
	}

	public void organizeYard() {
		for (String key : yardMap.keySet()) {
			LinkedList<Block> yardPos = yardMap.get(key);
			for (int i = 0; i < yardPos.size(); i++) {
				String lineStr = yardPos.get(i).getLine();
				Block curBlock = yardPos.get(i);
				for (int j = 0; j < track.size(); j++) {
					if (track.get(i).getLine().equals(lineStr))
						;
					{
						LinkedList<Block> curLine = track.get(i).getAllBlocks();
						Block yardBlock = curLine.get(curLine.size() - 1);
						if (curBlock.isToYard()) {
							curBlock.setNext(yardBlock);
							curBlock.setNextPossible(yardBlock);
						}
						if (curBlock.isFromYard()) {
							yardBlock.setNext(curBlock);
							yardBlock.setNextPossible(curBlock);
						}
					}
				}
			}
			System.out.println();
		}
	}

	public void organizeSwitch() {
		for (int i = 0; i < switchMap.size(); i++) {
			LinkedList<Block> newList = switchMap.get(i);
			Block master = newList.get(0);
			for (int j = 0; j < newList.size(); j++) {
				if (newList.get(j).getMasterSwitch())
					master = newList.get(j);
			}
			for (int j = 0; j < newList.size(); j++) {
				Block slave = newList.get(j);
				if (slave != master) {
					master.setSwitchList(slave);
					slave.setSwitchList(master);
					if (master.getTwoWay()) {
						if (slave.getTwoWay()) {
							master.setNext(slave);
							master.setNextPossible(slave);
							slave.setNext(master);
							slave.setNextPossible(master);
						} else if (slave.getArrowDirection() == -1 || slave.getArrowDirection() == 2) {
							master.setNext(slave);
							master.setNextPossible(slave);
						} else if (slave.getArrowDirection() == 1) {
							slave.setNext(master);
							slave.setNextPossible(master);
						}
					} else if (master.getArrowDirection() == 1) {
						if (slave.getTwoWay()) {
							master.setNext(slave);
							master.setNextPossible(slave);
						} else if (slave.getArrowDirection() == -1) {
							master.setNext(slave);
							master.setNextPossible(slave);
						} else if (slave.getArrowDirection() == 2) {
							master.setNext(slave);
							master.setNextPossible(slave);
						}
					} else if (master.getArrowDirection() == -1) {
						if (slave.getTwoWay()) {
							slave.setNext(master);
							slave.setNextPossible(master);
						} else if (slave.getArrowDirection() == 1 || slave.getArrowDirection() == 2) {
							slave.setNext(master);
							slave.setNextPossible(master);
						}
					} else {
						if (slave.getArrowDirection() == 1 && !slave.getTwoWay()) {
							slave.setNext(master);
							slave.setNextPossible(master);
						}
						if (slave.getArrowDirection() == -1 && !slave.getTwoWay()) {
							master.setNext(slave);
							master.setNextPossible(slave);
						}
					}
					if (slave.getNext() == null) {
						slave.setNext(master);
						slave.setNextPossible(master);
					}
				}
			}
		}
	}

	public void setNextPossible() {
		for (int i = 0; i < track.size(); i++) {
			line = track.get(i).getAllBlocks();
			for (int j = 0; j < line.size(); j++) {
				Block curBlock = line.get(j);
				Block nextBlock = line.get(j);
				Block prevBlock = line.get(j);
				if (j == line.size() - 1) {
					nextBlock = line.get(0);
				} else {
					nextBlock = line.get(j + 1);
				}
				if (j == 0) {
					prevBlock = line.get(line.size() - 1);
				} else {
					prevBlock = line.get(j - 1);
				}
				boolean nextSwitch = false;
				boolean prevSwitch = false;
				if (curBlock.getSwitchNum() == nextBlock.getSwitchNum() || curBlock.getSwitchNum() < 0
						|| nextBlock.getSwitchNum() < 0 && !nextBlock.getIsYard()) {
					nextSwitch = true;
				}
				if (curBlock.getSwitchNum() == prevBlock.getSwitchNum() || curBlock.getSwitchNum() < 0
						|| prevBlock.getSwitchNum() < 0 && !prevBlock.getIsYard()) {
					prevSwitch = true;
				}
				if (curBlock.getBackwards()) {
					if (prevSwitch) {
						curBlock.setNext(prevBlock);
						curBlock.setNextPossible(prevBlock);
						if (nextBlock.hasStation()) {
							curBlock.setBeacon(true, nextBlock.getStationName());
						}
					}
				} else {
					if (nextSwitch) {
						curBlock.setNext(nextBlock);
						curBlock.setNextPossible(nextBlock);
						if (nextBlock.hasStation()) {
							curBlock.setBeacon(true, nextBlock.getStationName());
						}
					}
				}
				if (curBlock.getTwoWay()) {
					if (nextBlock.getTwoWay() && nextSwitch) {
						curBlock.setNextPossible(nextBlock);
						nextBlock.setNextPossible(curBlock);
						if (nextBlock.hasStation()) {
							curBlock.setBeacon(true, nextBlock.getStationName());
						}
					}
					if (nextSwitch & (nextBlock.getArrowDirection() == -1 || nextBlock.getArrowDirection() == 2)) {
						curBlock.setNextPossible(nextBlock);
						if (nextBlock.hasStation()) {
							curBlock.setBeacon(true, nextBlock.getStationName());
						}
					}
					if (nextSwitch & (nextBlock.getArrowDirection() == 1 || nextBlock.getArrowDirection() == 3)) {
						nextBlock.setNextPossible(curBlock);
						if (nextBlock.hasStation()) {
							curBlock.setBeacon(true, nextBlock.getStationName());
						}
					}
					if (prevBlock.getTwoWay() && prevSwitch) {
						curBlock.setNextPossible(prevBlock);
						prevBlock.setNextPossible(curBlock);
						if (prevBlock.hasStation()) {
							curBlock.setBeacon(true, prevBlock.getStationName());
						}
					}
					if (prevSwitch & (prevBlock.getArrowDirection() == -1 || prevBlock.getArrowDirection() == 2)) {
						prevBlock.setNextPossible(curBlock);
					}
					if (prevSwitch & (prevBlock.getArrowDirection() == 1 || prevBlock.getArrowDirection() == 3)) {
						curBlock.setNextPossible(prevBlock);
						if (prevBlock.hasStation()) {
							curBlock.setBeacon(true, prevBlock.getStationName());
						}
					}
				} else {
					if (curBlock.getArrowDirection() == 1) {
						if (nextSwitch && nextBlock.getTwoWay()) {
							curBlock.setNextPossible(nextBlock);
							if (nextBlock.hasStation()) {
								curBlock.setBeacon(true, nextBlock.getStationName());
							}
						} else if (nextSwitch && nextBlock.getArrowDirection() == -1
								|| nextBlock.getArrowDirection() == 2) {
							curBlock.setNextPossible(nextBlock);
							if (nextBlock.hasStation()) {
								curBlock.setBeacon(true, nextBlock.getStationName());
							}
						}
						if (prevSwitch && prevBlock.getTwoWay()) {
							curBlock.setNextPossible(prevBlock);
							if (prevBlock.hasStation()) {
								curBlock.setBeacon(true, prevBlock.getStationName());
							}
						} else if (prevSwitch && prevBlock.getArrowDirection() == -1) {
							curBlock.setNextPossible(prevBlock);
							if (prevBlock.hasStation()) {
								curBlock.setBeacon(true, prevBlock.getStationName());
							}
						}
					} else if (curBlock.getArrowDirection() == -1) {
						if (nextSwitch && nextBlock.getTwoWay()) {
							nextBlock.setNextPossible(curBlock);
						}
						if (nextSwitch && nextBlock.getArrowDirection() == 1) {
							nextBlock.setNextPossible(curBlock);
						}
						if (prevSwitch && prevBlock.getTwoWay()) {
							prevBlock.setNextPossible(curBlock);
						}
						if (prevSwitch && prevBlock.getArrowDirection() == 1) {
							prevBlock.setNextPossible(curBlock);
						}
					}
				}
			}
		}
	}

	public void makeYard() {
		for (int i = 0; i < track.size(); i++) {
			LinkedList<Block> newLine = track.get(i).getAllBlocks();
			Block yardBlock = new Block(newLine.get(0).getLine(), true);
			newLine.add(yardBlock);
		}
	}

	public void initSwitchDirection() {
		for (int i = 0; i < switchMap.size(); i++) {
			LinkedList<Block> newList = switchMap.get(i);
			Block master = newList.get(0);
			for (int j = 0; j < newList.size(); j++) {
				if (newList.get(j).getMasterSwitch())
					master = newList.get(j);
			}
			if (master.getNext().hasSwitch()) {
				master.setSwitchEngaged(false);
				master.setNext(master.getSwitchList().get(0));
			}
			if (master.getPrev().hasSwitch()) {
				master.setSwitchEngaged(false);
				master.setPrev(master.getSwitchList().get(0));
			}
		}
	}

	@Override
	public void setTC(TrackControllerInterface trackComm) {
		this.trackComm = trackComm;

	}

	@Override
	public void setTI(TrainModelInterface trainComm) {
		this.trainComm = trainComm;
	}

	@Override
	public void setUI(TrackModelUI tmUI) {
		this.tmUI = tmUI;
	}

	@Override
	public LinkedList<Line> getTrack() {
		// TODO Auto-generated method stub
		return track;
	}
	
	private void setTrack()	{
		this.trackComm.setTrack(track);
	}
	
}
