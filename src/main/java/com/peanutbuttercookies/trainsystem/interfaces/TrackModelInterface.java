package com.peanutbuttercookies.trainsystem.interfaces;
/*
*	Fauzul Azim
*	10/14/2015
*/

import com.peanutbuttercookies.trainsystem.trackmodel.Block;

public interface TrackModelInterface {
	//for train access, but should not need it
	public int getSpeed(int trainId);
	public int getAuthority(int trainId);
	public int getBeacon(int trainId);
	
	
	//trackController uses these; assuming prototype does not care for switches and lights
	//both go directly to train
	public void setSpeed(int trainId, int speed); //sent to train controller
	public void setAuthority(int trainId, int authority); //sent to train controller
	
	//train model uses
	public void setBlockOccupied(int blockId, int trainId);
	public void setBlockUnoccupied(int blockId);
	
	
	//only handled by track model
	public void setBeacon(); //sent to train controller
	public void setStation(String station); //sent to train controller
	public void setLayout(Block newBlock);
	public void setBlock(String line, String section, int blockId, int blockLength, int speedLim, String infra, int occupancy);
}
