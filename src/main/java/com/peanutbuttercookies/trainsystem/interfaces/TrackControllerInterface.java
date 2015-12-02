package com.peanutbuttercookies.trainsystem.interfaces;

import java.util.ArrayList;

import com.peanutbuttercookies.trainsystem.ui.TrackControllerUI;

/**
 * 
 * 		@author Chris Good
 * 		10/14/2015
 *
 */

public interface TrackControllerInterface {
	
	public boolean 	setSpeedAuthority(int blockNum, int suggestedSpeed, int authority);	//called by CTC module
	//public boolean 	setBlockOccupied(String line, int blockNum);										//called by track model module
	public boolean 	setSwitchEngaged(int controllerID, int blockNum);										//called by CTC module
	
	public boolean 			setXlsxFileLocation(String fileLocation);
	public void 				setSwitchEngaged(int switchNum, String line, String controller);		//called by TC UI												//called by TC UI
	public ArrayList			getSwitchInfo(String line, String controller);														//called by TC UI
	public void					setRRCrossingEngaged(String line, int blockNum, String controller);		//called by TC UI
	public boolean 				setPLCFileLocation(String file);										//called by TC UI

	public void setCTC(CTCModuleInterface ctc);
	public void setTrackModel(TrackModelInterface trackModel);
	public void setTrackControllerUI(TrackControllerUI trackControllerUI);

	
	//public int 		getTrainPresence(int trainId);		//Used by the CTC module
	//public int 		getCommandedSpeed(int trainID);		//Used by track model module
	//public int 		getAuthority(int trainID);			//Used by track model module, maybe CTC if necessary

	//Says in notes that Track Controller deals with this, not sure how though
	//public String 	getBeacon(int blockNum);		//should send 128 bit signal, or 32 character string correlating to hex code
	//public int 		getSignal(int trainId);			//or maybe blockId?
	//public boolean 	getRRCrossing(int trainId);		//no idea how this works
}
