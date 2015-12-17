<<<<<<< HEAD
package com.peanutbuttercookies.trainsystem.trackmodel;

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
import com.peanutbuttercookies.trainsystem.train.TrainModelInterface;
=======
<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> refs/remotes/origin/master
package com.peanutbuttercookies.trainsystem.trackmodel;

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
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrainInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrainInterface;
import com.peanutbuttercookies.trainsystem.ui.TrackModelUI;
<<<<<<< HEAD

public class TrackModel implements TrackModelInterface {
	private TrackControllerInterface trackComm;
	private TrainInterface trainComm;
	private ArrayList<Block> track;
	private TrackModelUI tmUI;
	
	public TrackModel() {
		fileRead();
	}
	
	
	public void fileRead()	{
		track = new ArrayList<Block>();
		
		try	{
			BufferedReader br = new BufferedReader(new InputStreamReader(TrackModel.class.getResourceAsStream("/trackLayout2.txt")));	
			while(br.ready()) {
				String line = br.readLine();
				String delims = "[ ]+";
				String[] tokens = line.split(delims);
				System.out.println(line);
				if(!tokens[0].equals("Line"))	{
					setBlock(tokens[0], tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Double.parseDouble(tokens[4]), 
							Integer.parseInt(tokens[5]), tokens[6], Double.parseDouble(tokens[7]), Double.parseDouble(tokens[8]), 
							tokens[9], tokens[10], 0);
				}
			}
		}
		catch(IOException e) {
			System.out.println("Unable to open file.");
		}
	}
	@Override
	public int getSpeed(int trainId) {
		// TODO Auto-generated method stub
		return 0;
		
	}

	@Override
	public int getAuthority(int trainId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBlockOccupied(int blockId, int trainId) {
		track.get(blockId-1).setOccupancy();
 		if(tmUI.currentView(blockId))
 			tmUI.display(blockId);
		trackComm.setTrainPresence(trainId, blockId);
		trainComm.setSpeedLimit(track.get(blockId-1).getSpeedLim());
		if(!track.get(blockId-1).getInfra().equals("none"))
			trainComm.setStation(track.get(blockId-1).getInfra());
		trainComm.setBlockId(blockId);
		trainComm.setBlockLength(track.get(blockId-1).getBlockLen());
	}

	@Override
	public void setBlockUnoccupied(int blockId) {
		for(int i =0; i<track.size(); i++)	{
			if(track.get(i).getBlockId() == blockId)	{
				track.get(i).setOccupancy();
				if(tmUI.currentView(blockId))
		 			tmUI.display(blockId);
			}
		}
	}

	@Override
	public void setBeacon() {
		//trainComm.setBeaconInfo("1");
	}
	
	@Override
	public void setLayout(Block newBlock) {
		track.add(newBlock);
	}

	@Override
	public void setBlock(String line, String section, int blockId, int blockLen, double blockGrade, int speedLim, String infra, double elevation, double cumElev, String switchId, String direction, int occupancy) {
		Block newBlock = new Block(line, section, blockId, blockLen, blockGrade, speedLim, infra, elevation, cumElev, switchId, direction, occupancy);
		setLayout(newBlock);
	}
	
	@Override
	public void setSpeed(int trainId, int speed)	{
		trainComm.setSpeed((double)speed);
		//trainComm.run();
	}
	
	@Override
	public void setAuthority(int trainId, int authority)	{
		trainComm.setAuthority(authority);
	}

	@Override
	public int getBeacon(int trainId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setStation(String station) {
		if(!station.equals("none"))	{
			setBeacon();
			trainComm.setStation(station);
		}
	}



	@Override
	public void setTC(TrackControllerInterface trackComm) {
		this.trackComm = trackComm;
		
	}



	@Override
	public void setTI(TrainInterface trainComm) {
		this.trainComm = trainComm;
	}
	@Override
	public ArrayList<Block> getTrack() {
		return track;
	}


	@Override
	public void setUI(TrackModelUI tmUI) {
		this.tmUI = tmUI;
		
	}
}
=======
>>>>>>> refs/remotes/origin/master
package com.peanutbuttercookies.trainsystem.trackmodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrainInterface;
=======
>>>>>>> refs/remotes/origin/master
import com.peanutbuttercookies.trainsystem.ui.TrackModelUI;
>>>>>>> refs/remotes/origin/autumn_workspace

public class TrackModel implements TrackModelInterface {
	private TrackControllerInterface trackComm;
<<<<<<< HEAD
	private TrainModelInterface trainComm;
	private TrackModelUI tmUI;
	private ExcelFileDownloaderInterface excelDownloader;
	private LinkedList<Block> line;
	private LinkedList<Line> track;
	private Map<String, LinkedList<Block>> yardMap;
	private Map<Integer, LinkedList<Block>> switchMap;

<<<<<<< HEAD
=======
	private TrainInterface trainComm;
<<<<<<< HEAD
	private ArrayList<Block> track;
<<<<<<< HEAD
	private TrackModelUI newUI;
=======
	private TrackModelUI tmUI;
>>>>>>> refs/remotes/origin/master
=======
	private TrackModelUI tmUI;
	private ExcelFileDownloaderInterface excelDownloader;
    private LinkedList<Block> track;
    private LinkedList<Line> lines;
    private Map<Integer, LinkedList<Block>> switchMap;

>>>>>>> refs/remotes/origin/autumn_workspace

>>>>>>> refs/remotes/origin/master
	
	public TrackModel()throws IOException {
=======
	public TrackModel() throws IOException {
>>>>>>> branch 'master' of https://github.com/PeanutButterCookies/TrainSystem.git
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
							&& !tokens[i].equals("CROSSING") && !tokens[i].equals("RAILWAY")
							&& !tokens[i].equals("SWITCH"))
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
		for (int key : switchMap.keySet()) {
			LinkedList<Block> newList = switchMap.get(key);
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
				if ((curBlock.getSwitchNum() == nextBlock.getSwitchNum() || curBlock.getSwitchNum() < 0
						|| nextBlock.getSwitchNum() < 0) && !nextBlock.getIsYard()) {
					nextSwitch = true;
				}
				if ((curBlock.getSwitchNum() == prevBlock.getSwitchNum() || curBlock.getSwitchNum() < 0
						|| prevBlock.getSwitchNum() < 0) && !prevBlock.getIsYard()) {
					prevSwitch = true;
				}
				
				
				if (curBlock.getBackwards()) {
					if (prevSwitch) {
						curBlock.setNext(prevBlock);
						curBlock.setNextPossible(prevBlock);
						if(prevBlock.hasStation()){
							curBlock.setBeacon(true, prevBlock.getStationName());
						}
					}
				} else {
					if (nextSwitch) {
						curBlock.setNext(nextBlock);
						curBlock.setNextPossible(nextBlock);
						if(nextBlock.hasStation()){
							curBlock.setBeacon(true, nextBlock.getStationName());
						}
					}
				}
				if (curBlock.getTwoWay()) {
					if (nextBlock.getTwoWay() && nextSwitch) {
						curBlock.setNextPossible(nextBlock);
						nextBlock.setNextPossible(curBlock);
						if(nextBlock.hasStation()){
							curBlock.setBeacon(true, nextBlock.getStationName());
						}
					}
					if (nextSwitch & (nextBlock.getArrowDirection() == -1 || nextBlock.getArrowDirection() == 2)) {
						curBlock.setNextPossible(nextBlock);
						if(nextBlock.hasStation()){
							curBlock.setBeacon(true, nextBlock.getStationName());
						}
					}
					if (nextSwitch & (nextBlock.getArrowDirection() == 1 || nextBlock.getArrowDirection() == 3)) {
						nextBlock.setNextPossible(curBlock);
					}
					if (prevBlock.getTwoWay() && prevSwitch) {
						curBlock.setNextPossible(prevBlock);
						prevBlock.setNextPossible(curBlock);
						if(prevBlock.hasStation()){
							curBlock.setBeacon(true, prevBlock.getStationName());
						}
					}
					if (prevSwitch & (prevBlock.getArrowDirection() == -1 || prevBlock.getArrowDirection() == 2)) {
						prevBlock.setNextPossible(curBlock);
					}
					if (prevSwitch & (prevBlock.getArrowDirection() == 1 || prevBlock.getArrowDirection() == 3)) {
						curBlock.setNextPossible(prevBlock);
						if(prevBlock.hasStation()){
							curBlock.setBeacon(true, prevBlock.getStationName());
						}
					}
				} else {
					if (curBlock.getArrowDirection() == 1) {
						if (nextSwitch && nextBlock.getTwoWay()) {
							curBlock.setNextPossible(nextBlock);
							if(nextBlock.hasStation()){
								curBlock.setBeacon(true, nextBlock.getStationName());
							}
						} 
						else if (nextSwitch && nextBlock.getArrowDirection() == -1
								|| nextBlock.getArrowDirection() == 2) {
							curBlock.setNextPossible(nextBlock);
							if(nextBlock.hasStation()){
								curBlock.setBeacon(true, nextBlock.getStationName());
							}
						}
						if (prevSwitch && prevBlock.getTwoWay()) {
							curBlock.setNextPossible(prevBlock);
						} else if (prevSwitch && prevBlock.getArrowDirection() == -1) {
							curBlock.setNextPossible(prevBlock);
							if(prevBlock.hasStation()){
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
<<<<<<< HEAD

	@Override
	public int getAuthority(int trainId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
<<<<<<< HEAD
	public void setBlockOccupied(String line, int blockId) {
	}
//		for(int i = 0; i < lines.size(); i++)
//		{
//			if(lines.get(i).getLine().equals(line))
//				track = lines.get(i).getAllBlocks();
//		}
//		for(int i = 0; i < track.size(); i++)
//		{
//			Block curBlock = track.get(i);
//			if(curBlock.getBlockNumber() == blockId)	{
//				curBlock.setBlockOccupation(true);
//				curBlock.setNext();
//			}
//		}
=======
	public void setBlockOccupied(int blockId, int trainId) {
<<<<<<< HEAD
<<<<<<< HEAD
		track.get(blockId-1).setOccupancy();
		trackComm.setTrainPresence(trainId, blockId);
		//trainComm.setSpeedLimit(track.get(blockId-1).getSpeedLim());
		//trainComm.setStation(track.get(blockId-1).getInfra());
		//trainComm.setBlockId(blockId);
		//trainComm.setBlockLength(track.get(blockId-1).getBlockLen());
=======
		
=======
	/*	
>>>>>>> refs/remotes/origin/master
		// TEMP HACK
//		blockId += 1;
		// TEMP HACK
>>>>>>> refs/remotes/origin/autumn_workspace
		
/*
		System.out.println("setBlockOccupied blockId: " + blockId + " trainId: " + trainId);
		
		track.get(blockId-1).setOccupancy();
 		if(tmUI.currentView(blockId))
 			tmUI.display(blockId);
		//trackComm.setTrainPresence(trainId, blockId);
		trainComm.setSpeedLimit(track.get(blockId-1).getSpeedLim());
		if(!track.get(blockId-1).getInfra().equals("none")) {
			trainComm.setStation(track.get(blockId-1).getInfra());
		}
		trainComm.setBlockId(blockId);
<<<<<<< HEAD
		trainComm.setBlockLength(track.get(blockId-1).getBlockLen());
=======
<<<<<<< HEAD
		trainComm.setBlockLength(track.get(blockId-1).getBlockLen());
>>>>>>> refs/remotes/origin/master
=======
		trainComm.setBlockLength(track.get(blockId-1).getBlockLen());
>>>>>>> refs/remotes/origin/autumn_workspace
	*/
<<<<<<< HEAD
//	}
=======
>>>>>>> refs/remotes/origin/master
	}
>>>>>>> refs/remotes/origin/autumn_workspace

	@Override
	public void setBlockUnoccupied(String line, int blockId) {
		/*
		for(int i =0; i<track.size(); i++)	{
			if(track.get(i).getBlockId() == blockId)	{
				track.get(i).setOccupancy();
<<<<<<< HEAD
=======
				if(tmUI.currentView(blockId))
		 			tmUI.display(blockId);
>>>>>>> refs/remotes/origin/master
			}
		}
		*/
	}

	@Override
	public void setBeacon() {
		//trainComm.setBeaconInfo("1");
	}
	
	//@Override
	//public void setLayout(Block newBlock) {
	//	track.add(newBlock);
	//}

<<<<<<< HEAD
	@Override
	public void setBlock(String line, String section, int blockId, int blockLen, int speedLim, String infra, int occupancy) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		//@SuppressWarnings("unused")
=======
>>>>>>> refs/remotes/origin/master
		Block newBlock = new Block(line, section, blockId, blockLen, speedLim, infra, occupancy);
=======
	//@Override
	/*public void setBlock(String line, String section, int blockId, int blockLen, double blockGrade, int speedLim, String infra, double elev, double cumElev, String switchId, String direction, int occupancy) {
		Block newBlock = new Block(line, section, blockId, blockLen, blockGrade, speedLim, infra, elev, cumElev, switchId, direction, occupancy);
>>>>>>> refs/remotes/origin/master
		setLayout(newBlock);
	}*/
	
	@Override
<<<<<<< HEAD
	public void setSpeedAuthority(String line, int blockId, int speed, int authority)	{
		//trainComm.setSpeedAuthority(line, blockId, speed, authority);
//		trainComm.run();
		System.out.println("Speed Received " + blockId + " " + speed + "  authority: " + authority + " -Track Model");
=======
<<<<<<< HEAD
	public void setSpeed(int trainId, int speed)	{
		//trainComm.setSpeed(trainId, speed);
	}
	
	@Override
	public void setAuthority(int trainId, int authority)	{
		//trainComm.setAuthority(trainId, authority);
	}

	@Override
=======
	public void setSpeedAuthority(int trainId, int speed, int authority)	{
		trainComm.setSpeed(speed);
		trainComm.setAuthority(authority);
		trainComm.run();
		System.out.println("Speed Received " + trainId + " " + speed + "  authority: " + authority + " -Track Model");
>>>>>>> refs/remotes/origin/autumn_workspace

	}
	
	@Override
>>>>>>> refs/remotes/origin/master
	public int getBeacon(int trainId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setStation(String station) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		if(!station.equals("none"))	{
			setBeacon();
			//trainComm.getStation(station);
=======
		if(!station.equals("none"))	{
			setBeacon();
<<<<<<< HEAD
//			trainComm.setStation(station);
=======
			trainComm.setStation(station);
>>>>>>> refs/remotes/origin/master
>>>>>>> refs/remotes/origin/autumn_workspace
		}
	}


=======
>>>>>>> branch 'master' of https://github.com/PeanutButterCookies/TrainSystem.git

	@Override
	public void setTC(TrackControllerInterface trackComm) {
		this.trackComm = trackComm;

	}

	@Override
	public void setTI(TrainModelInterface trainComm) {
		this.trainComm = trainComm;
	}
<<<<<<< HEAD
<<<<<<< HEAD
	@Override
	public ArrayList<Block> getTrack() {
		return track;
	}
<<<<<<< HEAD
}
=======
=======
	
>>>>>>> refs/remotes/origin/master

=======
>>>>>>> branch 'master' of https://github.com/PeanutButterCookies/TrainSystem.git

	@Override
	public void setUI(TrackModelUI tmUI) {
		this.tmUI = tmUI;
	}

	@Override
	public LinkedList<Line> getTrack() {
		// TODO Auto-generated method stub
		return track;
	}
<<<<<<< HEAD
	
<<<<<<< HEAD
	@Override
	public LinkedList<Line> getLines() {
		return lines;
	}


	@Override
	public void excelReader() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSwitch(int blockId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSpeedAuthority(int blockId, int speed, int authority) {
		// TODO Auto-generated method stub
		
	}

	


	

}	

=======
}
<<<<<<< HEAD
>>>>>>> branch 'master' of https://github.com/PeanutButterCookies/TrainSystem
>>>>>>> refs/remotes/origin/master
=======
>>>>>>> refs/remotes/origin/master
>>>>>>> refs/remotes/origin/autumn_workspace
=======
}
>>>>>>> branch 'master' of https://github.com/PeanutButterCookies/TrainSystem.git
