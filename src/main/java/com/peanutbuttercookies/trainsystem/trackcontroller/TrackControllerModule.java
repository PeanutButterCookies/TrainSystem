package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.util.ArrayList;
import java.util.LinkedList;

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
	public ArrayList<TC_Block> getControllerInfo(String line, String controller) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSwitchEngaged(int switchNum, String line, String controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean setSwitchEngaged(int blockNum, String line) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean setPLCFileLocation(String file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<TC_Block> getControllerInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList getSwitchInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRRCrossingEngaged(String line, int blockNum, String controller) {
		// TODO Auto-generated method stub
		
	}

}
