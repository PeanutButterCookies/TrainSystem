/*
 * Kevin Nash
 * 10/15/2015
 */


package com.peanutbuttercookies.trainsystem.ctctest;

import java.util.Vector;

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.trackcontroller.TC_Train;
import com.peanutbuttercookies.trainsystem.ui.TrackControllerUI;

public class TestTrackControllerInterface implements TrackControllerInterface {

	@Override
	public boolean setSpeedAuthority(int trainId, int suggestedSpeed, int authority) {
		return true;
	}

	// rest are unnecessary for ctc testing
	//@Override
	/*public boolean setTrainPresence(int trainId, int blockNum) {
		return false;
	}*/

	

	@Override
	public void setCTC(CTCModuleInterface ctc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTrackModel(TrackModelInterface trackModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTrackControllerUI(TrackControllerUI trackControllerUI) {
		// TODO Auto-generated method stub
		
	}

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

}
