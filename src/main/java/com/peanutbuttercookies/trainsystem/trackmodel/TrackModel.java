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

public class TrackModel implements TrackModelInterface {
	private TrackControllerInterface trackComm;
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


>>>>>>> refs/remotes/origin/master
	
	public TrackModel()throws IOException {
	}
	
	public void fileRead(String filename) throws IOException
	{
		lines = new LinkedList<Line>();
		track = new LinkedList<Block>();
		switchMap = new HashMap<Integer, LinkedList<Block>>();
		filename = filename.replace("\\", "/");
		excelReader(filename);
	}
	
	public void excelReader(String filePath) throws IOException {
		FileInputStream inputStream = new FileInputStream(new File(filePath));
		
		Workbook workbook = new XSSFWorkbook(inputStream);
		int sheetNum		= workbook.getNumberOfSheets();
		for(int i = 0; i < sheetNum; i++)
		{
			Sheet firstSheet = workbook.getSheetAt(i);
			Iterator<Row> iterator = firstSheet.iterator();
			String lineName=firstSheet.getSheetName();
			if(lineName.contains("Line"))	{
				int sectionStart = 0;
	            int sectionEnd = 0;
	            String curSection = "";
				while (iterator.hasNext()) {
		            Row nextRow = iterator.next();
		            Iterator<Cell> cellIterator = nextRow.cellIterator();
		            int index = 0;
		            boolean hasInfra = false;
		            boolean hasSwitch = false;
		            boolean hasInfraSwitch = false;
		            boolean hasArrow = false;
		            boolean hasStation = false;
		            boolean hasCrossing = false;
		            boolean hasUnderground = false;
		            boolean hasToYard = false;
		            boolean hasFromYard = false;
		            boolean masterSwitch = false;
		            String station = "";
		            int arrowA = 0;
		            int arrowB = 0;
		            String switchId = "-1";
		            String[] info = new String[11];
		            while (cellIterator.hasNext()) {
		            	Cell cell = cellIterator.next();
		            	if(cell.getCellType() == Cell.CELL_TYPE_STRING)
		            	{
		                	if(cell.getStringCellValue().contains("CROSSING"))	{
		                		hasCrossing = true;
		                	}
		                	if(cell.getStringCellValue().contains("SWITCH"))	{
		                		hasSwitch = true;
		                		masterSwitch = true;
		                	}
		            		if(cell.getStringCellValue().contains("TO YARD"))	{
		                    	hasToYard = true;
		                    }
		            		if(cell.getStringCellValue().contains("FROM YARD"))	{
		                    	hasFromYard = true;
		                    }
		            		if(cell.getStringCellValue().contains("TO YARD"))	{
		                    	hasToYard = true;
		                    	hasFromYard = true;
		                    }
		                	if(cell.getStringCellValue().contains("STATION"))	{
		                		hasStation = true;
		                		String delims = "[;]+";
		                		String tokens[] = cell.getStringCellValue().split(delims);
		                		for(int j = 0; j<tokens.length; j++)	{
		        					if(j>0 && tokens[j-1].contains("STATION"))	{
		        						if(tokens[j].length() > 0)
		        							station = tokens[j].replace(";", "");
		        					}
		        				}
		                	}
		                	if(cell.getStringCellValue().contains("UNDERGROUND"))	{
		                		hasUnderground = true;
		                	}
		                    if(cell.getStringCellValue().contains("Switch"))	{
		                    	hasSwitch = true;
		                    	String cellInfo = cell.getStringCellValue();
		                    	cellInfo = cellInfo.replaceFirst(".*?(\\d+).*", "$1");
                            	switchId = cellInfo;
		                    }
		                    if(cell.getStringCellValue().contains("TO YARD"))	{
		                    	hasToYard = true;
		                    }
		            		if(cell.getStringCellValue().contains("FROM YARD"))	{
		                    	hasFromYard = true;
		                    }
		            		if(cell.getStringCellValue().contains("TO YARD"))	{
		                    	hasToYard = true;
		                    	hasFromYard = true;
		                    }
		                    if(cell.getStringCellValue().contains("Head"))	{
		                    	hasArrow = true;
		                    	arrowA = 1;
		                    }
		                    if(cell.getStringCellValue().contains("Tail"))	{
		                    	hasArrow = true;
		                    	arrowA = -1;
		                    }
		                    if(cell.getStringCellValue().contains("Tail/Head"))	{
		                    	hasArrow = true;
		                    	arrowA = 2;
		                    	arrowB = 0;
		                    }
		                    if(cell.getStringCellValue().contains("Head/Tail"))	{
                            	hasArrow = true;
                            	arrowA = 2;
                            	arrowB = 0;
                            }
		                    if(cell.getStringCellValue().contains("Head/Head"))	{
                            	hasArrow = true;
                            	arrowA = 3;
                            	arrowB = 0;
                            }
		        		}
		            	if(cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
		                    switch(cell.getCachedFormulaResultType()) {
		                        case Cell.CELL_TYPE_NUMERIC:
		                            info[index++] = "" + cell.getNumericCellValue();
		                            break;
		                        case Cell.CELL_TYPE_STRING:
		                            String cellInfo = "" + cell.getRichStringCellValue();
		                            if(cellInfo.contains("STATION"))	{
		                            	hasStation = true;
		                            	String delims = "[ ]+";
		                				String[] tokens = cellInfo.split(delims);
		                				for(int j = 0; j<tokens.length; j++)	{
		                					if(tokens[j].contains("STATION"))	{
		                						station = tokens[j+1].replace(";", "");
		                					}
		                				}
		                            }
		                            if(cellInfo.contains("CROSSING"))	{
		                            	hasCrossing = true;
		                            }
		                            if(cellInfo.contains("SWITCH"))	{
		                            	hasSwitch = true;
		                            	masterSwitch = true;
		                            }
		                            if(cellInfo.contains("UNDERGROUND"))	{
		                                hasUnderground = true;
		                        	}
		                            if(cellInfo.contains("Switch"))	{
		                            	cellInfo = cellInfo.replaceFirst(".*?(\\d+).*", "$1");
		                            	switchId = cellInfo;
		                            }
		                            if(cellInfo.contains("Head"))	{ 
		                            	hasArrow = true;
		                            	arrowA = 1;
		                            	arrowB = 0;
		                            }
		                            if(cellInfo.contains("Tail"))	{
		                            	hasArrow = true;
		                            	arrowA = -1;
		                            	arrowB = 0;
		                            }
		                            if(cellInfo.contains("Tail/Head"))	{
		                            	hasArrow = true;
		                            	arrowA = 2;
		                            	arrowB = 0;
		                            }
		                            if(cellInfo.contains("Head/Tail"))	{
		                            	hasArrow = true;
		                            	arrowA = 2;
		                            	arrowB = 0;
		                            }
		                            if(cellInfo.contains("Head/Head"))	{
		                            	hasArrow = true;
		                            	arrowA = 3;
		                            	arrowB = 0;
		                            }
		                            if(!(hasSwitch || hasUnderground || hasStation || hasCrossing ||hasArrow))
		                            	info[index++] = cellInfo;
		                            }
		                 }
		            	else	{
		            		String newWord = "" + cell;
		            		if(newWord.length()>0 && !(hasSwitch || hasUnderground || hasStation || hasCrossing ||hasArrow))
		            			info[index++] = newWord; 
		            	}
		            } 
		            if(info[0] != null && !info[0].equals("Line") && info[0].length()>0)	{
		            	Double blockNum = Double.parseDouble(info[2]);
		            	int blockNumI = blockNum.intValue();
		            	Double blockLen = Double.parseDouble(info[3]);
		            	int blockLenI = blockLen.intValue();
		            	Double speedLim = Double.parseDouble(info[5]);
		            	int speedLimI = blockLen.intValue();
		            	
		            	if(curSection.equals(""))
		            	{
		            		curSection = info[1];
		            		sectionStart = 0;
		            	}
		            	else if (!info[1].equals(curSection))
		            	{
		            		if(track.get(sectionStart).getArrowDirectionA() == 1 && ((track.get(sectionEnd-1).getArrowDirectionA() == 1) || (arrowA==3)))
		            			for(int j = sectionStart; j<sectionEnd; j++)
		            				track.get(j).setTwoWay(true);
		            		curSection = info[1];
		            		sectionStart = sectionEnd;
		            	}
		            	sectionEnd++;
		            	
		            	Block newBlock = new Block(info[0], info[1], blockNumI, blockLenI, Float.parseFloat(info[4]), 
		            			speedLimI, Float.parseFloat(info[6]), Float.parseFloat(info[7]), hasToYard, 
		            			hasFromYard, hasSwitch, hasUnderground, hasCrossing, hasStation, station,
		            			Integer.parseInt(switchId), arrowA, arrowB);
		            	if(hasSwitch)	{
		            		
		            		if(switchMap.get(Integer.parseInt(switchId)) == null)	{
		            			LinkedList<Block> newList = new LinkedList<Block>();
		            			newList.add(newBlock);
		            			switchMap.put(Integer.parseInt(switchId), newList);
		            		}
		            		else	{
		            			LinkedList<Block> newList = switchMap.get(Integer.parseInt(switchId));
		            			newList.add(newBlock);
		            			switchMap.put(Integer.parseInt(switchId), newList);
		            		}
		            	}
		            	if(masterSwitch)	{
		            		newBlock.setMasterSwitch(true);
		            	}
		            	track.add(newBlock);
		            }
				}
				LinkedList<Block> newTrack = new LinkedList<Block>(track);
				Line newLine = new Line(track.get(0).getLine(), newTrack);
		        lines.add(newLine);
		        track.clear();
			}
		}
		
		for(int i = 0; i < lines.size(); i++)	{
			track = lines.get(i).getAllBlocks();
			for(int j = 0; j<track.size(); j++)
			{
				Block curBlock = track.get(j);
				Block nextBlock = track.get(j);
				Block prevBlock = track.get(j);
				boolean switchEqualNext = true;
				boolean switchEqualPrev = true;

				if(j<track.size()-1){
					nextBlock = track.get(j+1);
				}
				else	{
					nextBlock = track.get(0);
				}
				if(j == 0){
					prevBlock = track.get(track.size()-1);
				}
				else	{
					prevBlock = track.get(j-1);
				}
				
				if(curBlock.hasSwitch())	{
					LinkedList<Block> switches = switchMap.get(curBlock.getSwitchBlockId());
					for(int k = 0; k < switches.size(); k++)	{
						Block switchBlock = switches.get(k);
						if(switchBlock.getBlockNumber() != curBlock.getBlockNumber())	{
								curBlock.setSwitchList(switchBlock);
						}
					}
				}
				if(curBlock.hasSwitch() && nextBlock.hasSwitch())	{
					switchEqualNext = false;
				}
				if(curBlock.hasSwitch() && prevBlock.hasSwitch())	{
					switchEqualPrev = false;
				}
				
				if(curBlock.getTwoWay())
				{
					if((nextBlock.getTwoWay() || nextBlock.getArrowDirectionA() == -1) && switchEqualNext)	{
						curBlock.setNextPossible(nextBlock);
					}
					else if(nextBlock.getArrowDirectionA() == 1 && switchEqualNext)	{
						nextBlock.setNextPossible(curBlock);
					}
					if((prevBlock.getTwoWay() || prevBlock.getArrowDirectionA() == -1) && switchEqualPrev)	{
						curBlock.setNextPossible(prevBlock);
					}
					else if(prevBlock.getArrowDirectionA() == 1 && switchEqualPrev)	{
						prevBlock.setNextPossible(curBlock);
					}
				}
				else	{
					if(curBlock.getArrowDirectionA() == 1)	{
						int count = 2;
						Block tempCurBlock = curBlock;
						Block tempNextBlock = nextBlock;
						while(tempNextBlock.getSection().equals(tempCurBlock.getSection()) && j+count < track.size())	{
							tempNextBlock.setNextPossible(tempCurBlock);
							tempCurBlock = tempNextBlock;
							tempNextBlock = track.get(j + count);
							count++;
						}
						if(nextBlock.getArrowDirectionA() == -1)	{
							curBlock.setNextPossible(nextBlock);
						}
						if(prevBlock.getArrowDirectionA() == -1)	{
							curBlock.setNextPossible(prevBlock);
						}
					}
					if(curBlock.getArrowDirectionA() == -1)	{
						int count = 2;
						Block tempCurBlock = curBlock;
						Block tempNextBlock = nextBlock;
						while(tempNextBlock.getSection().equals(tempCurBlock.getSection()) && j+count < track.size())	{
							tempCurBlock.setNextPossible(tempNextBlock);
							tempCurBlock = tempNextBlock;
							tempNextBlock = track.get(j + count);
							count++;
						}
						if(nextBlock.getArrowDirectionA() == 1)	{
							nextBlock.setNextPossible(curBlock);
						}
						if(prevBlock.getArrowDirectionA() == 1)	{
							prevBlock.setNextPossible(curBlock);
						}
					}
				}
			}
		}
		for(int i = 0; i < lines.size(); i++)	{
			track = lines.get(i).getAllBlocks();
			for(int j = 0; j<track.size(); j++)
			{
				Block curBlock = track.get(j);
				if(curBlock.hasSwitch())	{
					curBlock.setSwitchEngagement();
				}
			}
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
>>>>>>> refs/remotes/origin/master
=======
		trainComm.setBlockLength(track.get(blockId-1).getBlockLen());
	*/
>>>>>>> refs/remotes/origin/master
	}

	@Override
	public void setBlockUnoccupied(int blockId) {
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
			trainComm.setStation(station);
>>>>>>> refs/remotes/origin/master
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


	@Override
	public void setUI(TrackModelUI tmUI) {
		this.tmUI = tmUI;
		
	}

	@Override
	public LinkedList<Block> getTrack() {
		// TODO Auto-generated method stub
		return track;
	}
	
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


	

	
}
<<<<<<< HEAD
>>>>>>> branch 'master' of https://github.com/PeanutButterCookies/TrainSystem
>>>>>>> refs/remotes/origin/master
=======
>>>>>>> refs/remotes/origin/master
