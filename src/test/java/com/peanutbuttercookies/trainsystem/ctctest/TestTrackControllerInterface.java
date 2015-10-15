/*
 * Kevin Nash
 * 10/15/2015
 */


package com.peanutbuttercookies.trainsystem.ctctest;

import java.util.Vector;

import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.trackcontroller.TC_Train;

public class TestTrackControllerInterface implements TrackControllerInterface {

	@Override
	public boolean setSpeedAuthority(int trainId, int suggestedSpeed, int authority) {
		return true;
	}

	// rest are unnecessary for ctc testing
	@Override
	public boolean setTrainPresence(int trainId, int blockNum) {
		return false;
	}

	@Override
	public Vector<TC_Train> getLineInfo(String line) {
		// TODO Auto-generated method stub
		return null;
	}

}
