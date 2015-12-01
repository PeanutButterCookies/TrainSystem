package com.peanutbuttercookies.trainsystem.commonresources;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.peanutbuttercookies.trainsystem.interfaces.ExcelFileDownloaderInterface;

public class ExcelFileDownloader implements ExcelFileDownloaderInterface {

	private LinkedList<Line> lines;
	
	@Override
	public boolean setExcelFile(String fileLocation) {
		try{
			boolean success;
			FileInputStream file=new FileInputStream(new File(fileLocation));
			success=loadXlsxFile(file);
			file.close();
			return success;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	private boolean loadXlsxFile(FileInputStream file){
		try{
			
			XSSFWorkbook excelFile=new XSSFWorkbook(file);
			
			int sheetNum		=excelFile.getNumberOfSheets();	//Gets number of sheets to iterate through
			boolean hasALine	=false;		//Variable to check that file has line information
			
			for(int i=0; i<sheetNum; i++){
				XSSFSheet sheet=excelFile.getSheetAt(i);
				String lineName=sheet.getSheetName();
				
				//Doesn't count pages without a "line" keyword in the name
				if(!lineName.contains("Line") && !lineName.contains("line") && !lineName.contains("LINE")){
					continue;
				}
				else{
					if(!hasALine){
						lines.clear();
						hasALine=true;
					}
				}
				
				Iterator<Row> rowIterator	=sheet.iterator();
				rowIterator.next();
				
				LinkedList<Block> tempBlocks = new LinkedList<Block>();
				
				//Iterates through the rows of the excel worksheet and scrapes the variable values
				while(rowIterator.hasNext()){
					Row row = rowIterator.next();
					Iterator<Cell> cellIterator	=row.cellIterator();
					
					//Initialize the variables collected from the sheet
					String line							=cellIterator.next().getStringCellValue();
					String section						=cellIterator.next().getStringCellValue();
					int blockNumber 					=(int)cellIterator.next().getNumericCellValue();
					int blockLength 					=(int)cellIterator.next().getNumericCellValue();
					float blockGrade					=(float)cellIterator.next().getNumericCellValue();
					int speedLimit						=(int)cellIterator.next().getNumericCellValue();
					String infrastructure				=cellIterator.next().getStringCellValue();
					cellIterator.next();
					float elevation						=(float)cellIterator.next().getNumericCellValue();
					float cumaltiveElevation			=(float)cellIterator.next().getNumericCellValue();
					String switchBlock					=cellIterator.next().getStringCellValue();
					String arrowDirection				=cellIterator.next().getStringCellValue();
					
					boolean infrastructureSwitch		=infrastructure.contains("SWITCH");
					boolean infrastructureUnderground	=infrastructure.contains("UNDERGROUND");
					boolean infrastructureStation		=infrastructure.contains("STATION");
					boolean infrastructureRRCrossing	=infrastructure.contains("RAILWAY CROSSING");
					boolean switchToYard				=false;
					boolean switchFromYard				=false;
					String stationName					=null;
					int switchBlockId					=-1;
					int arrowDirectionAId				=0;
					int arrowDirectionBId				=0;
					//***End variable initialization
					
					//Checks to see if this block has the switch to/from the yard
					if(infrastructureSwitch){
						if(infrastructure.contains("SWITCH TO/FROM YARD") || infrastructure.contains("SWITCH FROM/TO YARD")){
							switchToYard=true;
							switchFromYard=true;
						}
						else if(infrastructure.contains("SWITCH TO YARD")){
							switchToYard=true;
						}
						else if(infrastructure.contains("SWITCH FROM YARD")){
							switchFromYard=true;
						}
					}
					
					//Checks to see if there is a station on the block, and then parses infrastructure
					//for the station name
					if(infrastructureStation){
						
						boolean nextStringIsStationName=false;
						String[] tempStrings=infrastructure.split(";");
						
						for(int j=0; j<tempStrings.length; j++){
							
							//double checks to make sure that the delimiter wasn't different
							if(tempStrings[j].contains(":")){
								String[] moreTempStrings=tempStrings[j].split(":");
								for(int k=0; k<moreTempStrings.length; k++){
									if(moreTempStrings[k].contains("STATION")){
										nextStringIsStationName=true;
									}
									else if(nextStringIsStationName){
										stationName=moreTempStrings[k];
										break;
									}
								}
								if(nextStringIsStationName){
									break;
								}
							}
							else{
								if(tempStrings[j].contains("STATION")){
									nextStringIsStationName=true;
								}
								else if(nextStringIsStationName){
									stationName=tempStrings[j];
									break;
								}
							}
						}
						
						if(!nextStringIsStationName){
							excelFile.close();
							return false;		//Error has occurred if no station name is read but there is a station in the infrastructure string
						}
						
					}
					
					//Parses the switch block string for the Id number
					if(switchBlock.contains("Switch")){
						String[] tempStrings=switchBlock.split(" ");
						switchBlockId=Integer.parseInt(tempStrings[1]);
					}
					
					//Reads the arrow directions of the block, checks if there are one or two arrow directions
					if(arrowDirection.contains("/")){
						String[] arrowDirectionStrings=arrowDirection.split("/");
						if(arrowDirectionStrings[0].equals("Head")){
							arrowDirectionAId=1;
						}
						else{
							arrowDirectionAId=-1;
						}
						
						if(arrowDirectionStrings[1].equals("Head")){
							arrowDirectionBId=1;
						}
						else{
							arrowDirectionBId=-1;
						}
					}
					else if(arrowDirection.equals("Head")){
						arrowDirectionAId=1;
						arrowDirectionBId=0;
					}
					else if(arrowDirection.equals("Tail")){
						arrowDirectionAId=-1;
						arrowDirectionBId=0;
					}
					else{
						arrowDirectionAId=0;
						arrowDirectionBId=0;
					}
					
					//Adds line variables to new block in the temporary block list
					tempBlocks.add(new Block(line,section,blockNumber,blockLength,blockGrade,speedLimit,
							elevation, cumaltiveElevation,switchToYard,switchFromYard,infrastructureSwitch,
							infrastructureUnderground,infrastructureRRCrossing,infrastructureStation,
							stationName,switchBlockId,arrowDirectionAId,arrowDirectionBId));
				}
				
				lines.add(new Line(lineName,(LinkedList<Block>)tempBlocks.clone()));
			}
			
			excelFile.close();
			return hasALine;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public LinkedList<Line> getLines() {
		return lines;
	}

}
