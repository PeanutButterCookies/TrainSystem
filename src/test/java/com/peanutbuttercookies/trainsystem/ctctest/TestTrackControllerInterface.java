/*
 * Kevin Nash
 * 10/15/2015
 */


package com.peanutbuttercookies.trainsystem.ctctest;

import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;

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
	public int getTrainPresence(int trainId) {
		return 0;
	}

	@Override
	public int getCommandedSpeed(int trainID) {
		return 0;
	}

	@Override
	public int getAuthority(int trainID) {
		return 0;
	}

	@Override
	public String getBeacon(int blockNum) {
		return null;
	}

	@Override
	public int getSignal(int trainId) {
		return 0;
	}

	@Override
	public boolean getRRCrossing(int trainId) {
		return false;
	}

}
