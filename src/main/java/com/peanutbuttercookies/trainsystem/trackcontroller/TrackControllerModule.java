package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.ExcelFileDownloaderInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.ui.TrackControllerUI;

/**
 * 
 * @author Chris Good
 *
 */
public class TrackControllerModule implements TrackControllerInterface {
	
	private CTCModuleInterface ctc;
	private TrackModelInterface trackModel;
	private ExcelFileDownloaderInterface excelDownloader;
	private TrackControllerUI ui;
	
	

	@Override
	public boolean setSpeedAuthority(String line, int blockNum, int suggestedSpeed, int authority) {
		// TODO Auto-generated method stub
		return false;

	}

	@Override
	public boolean setBlockOccupied(String line, int blockNum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setXlsxFileLocation(String fileLocation) {
		boolean success=excelDownloader.setExcelFile(fileLocation);
		if(success){
			//lines=excelDownloader.getLines();
		}
		return success;
	}
	
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
						hasALine=true;
					}
				}
				
				
				XSSFSheet blockCount		=excelFile.getSheetAt(i);
				Iterator<Row> rowIterator	=blockCount.iterator();
				int numBlocks				=0;
				
				//iterates through rows to find number of elements to be in TC_Block array
				while(rowIterator.hasNext()){
					numBlocks++;
					rowIterator.next();
				}
				
				
				rowIterator=sheet.iterator();
				rowIterator.next();
				int counter=0;
				
				while(rowIterator.hasNext()){
					Row row = rowIterator.next();
					Iterator<Cell> cellIterator=row.cellIterator();
					
					String line		=cellIterator.next().getStringCellValue();
					String section	=cellIterator.next().getStringCellValue();
					int blockNumber =(int)cellIterator.next().getNumericCellValue();
					int blockLength =(int)cellIterator.next().getNumericCellValue();
					
					// **** Pick Up Here ****
					
					counter++;
				}
				
				//lines.add(new TC_Line(lineName,tempBlocks.clone()));
			}
			return hasALine;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	

	@Override
	public void setCTC(CTCModuleInterface ctc) {
		this.ctc = ctc;
	}

	@Override
	public void setTrackModel(TrackModelInterface trackModel) {
		this.trackModel = trackModel;
	}

	@Override
	public void setTrackControllerUI(TrackControllerUI trackControllerUI) {
		ui=trackControllerUI;
		
	}

	@Override
	public boolean setSpeedAuthority(int trainId, int suggestedSpeed, int authority) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean setTrainPresence(int trainId, int blockNum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getLineInfo(String selectedLine) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	/*public boolean setTrainPresence(int trainId, int blockNum) {
		// TODO Auto-generated method stub
		return false;
	}*/

}
