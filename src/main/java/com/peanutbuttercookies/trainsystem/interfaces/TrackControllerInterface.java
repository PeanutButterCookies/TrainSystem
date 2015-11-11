package com.peanutbuttercookies.trainsystem.interfaces;

import java.util.ArrayList;

import com.peanutbuttercookies.trainsystem.trackcontroller.TC_Block;
import com.peanutbuttercookies.trainsystem.ui.TrackControllerUI;

/**
 * 
 * 		@author Chris Good
 * 		10/14/2015
 *
 */

public interface TrackControllerInterface {
	
	public boolean 	setSpeedAuthority(String line, int blockNum, int suggestedSpeed, int authority);	//called by CTC module
	public boolean 	setBlockOccupied(String line, int blockNum);										//called by track model module
	public boolean 	setSwitchEngaged(int blockNum, String line);										//called by CTC module
	
	public ArrayList<TC_Block>	getControllerInfo(String line, String controller);						//called by TC UI
	public void 				setSwitchEngaged(int switchNum, String line, String controller);		//called by TC UI
	public ArrayList<TC_Block>	getControllerInfo();													//called by TC UI
	public ArrayList			getSwitchInfo();														//called by TC UI
	public void					setRRCrossingEngaged(String line, int blockNum, String controller);		//called by TC UI
	public boolean 				setPLCFileLocation(String file);										//called by TC UI

	public void setCTC(CTCModuleInterface ctc);
	public void setTrackModel(TrackModelInterface trackModel);
	public void setTrackControllerUI(TrackControllerUI trackControllerUI);
	
}
