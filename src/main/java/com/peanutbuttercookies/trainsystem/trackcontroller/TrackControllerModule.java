package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.util.Vector;

import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;

public class TrackControllerModule implements TrackControllerInterface {
	
	Vector<Train> trainList=new Vector<Train>();
	
	@Override
	public boolean setSpeedAuthority(int trainId, int suggestedSpeed, int authority) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setTrainPresence(int trainId, int blockNum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getTrainPresence(int trainId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCommandedSpeed(int trainID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAuthority(int trainID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getBeacon(int blockNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSignal(int trainId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getRRCrossing(int trainId) {
		// TODO Auto-generated method stub
		return false;
	}

}
