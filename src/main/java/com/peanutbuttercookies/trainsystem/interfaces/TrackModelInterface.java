package com.peanutbuttercookies.trainsystem.interfaces;
/*
*	Fauzul Azim
*	10/14/2015
*/

public interface TrackModelInterface {
	public void getSpeed(int trainId, int speed);
	public void getAuthority(int trainId, int authority);
	public void getBlockOccupied(int blockId);
	public void getBlockUnoccupied(int blockId);
	
	public void setSpeed(int trainId, int speed);
	public void setAuthority(int trainId, int authority);
	public void setBeacon(int blockId);
	public void setLayout(int blockId);
	public void setBlock(String line, String section, int blockId, int blockLength, int speedLim, int infra, int occupancy);
}
