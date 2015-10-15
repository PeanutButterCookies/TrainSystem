package com.peanutbuttercookies.trainsystem.interfaces;

import java.util.Vector;

import com.peanutbuttercookies.trainsystem.trackcontroller.TC_Train;

/**
 * 
 * 		@author Chris Good
 * 		10/14/2015
 *
 */

public interface TrackControllerInterface {
	/*
	 * 		Using an int for trainId for prototype simplicity
	 */
	
	public boolean 	setSpeedAuthority(int trainId, int suggestedSpeed, int authority);		//called by CTC module
	public boolean 	setTrainPresence(int trainId, int blockNum);							//called by track model module
	
	public Vector<TC_Train>	getLineInfo(String line);
	
	//public int 		getTrainPresence(int trainId);		//Used by the CTC module
	//public int 		getCommandedSpeed(int trainID);		//Used by track model module
	//public int 		getAuthority(int trainID);			//Used by track model module, maybe CTC if necessary

	//Says in notes that Track Controller deals with this, not sure how though
	//public String 	getBeacon(int blockNum);		//should send 128 bit signal, or 32 character string correlating to hex code
	//public int 		getSignal(int trainId);			//or maybe blockId?
	//public boolean 	getRRCrossing(int trainId);		//no idea how this works
	
}
