package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.peanutbuttercookies.trainsystem.ui.TrackControllerUI;

import java.util.Vector;

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.ExcelFileDownloaderInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;

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
	
	private LinkedList<TC_Line> lines;
	

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
			lines=excelDownloader.getLines();
		}
		return success;
	}

	@Override
	public Vector<TC_Block> getLineInfo(String line) {
		// TODO Auto-generated method stub
		return null;
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

}
