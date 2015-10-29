package com.peanutbuttercookies.trainsystem.interfaces;
/*
*	Fauzul Azim
*	10/14/2015
*/

import java.io.IOException;
import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.ui.TrackModelUI;

public interface TrackModelInterface {
	//for train access, but should not need it
	public int getSpeed(int trainId);
	public int getAuthority(int trainId);
	public int getBeacon(int trainId);
	
	
	//trackController uses these; assuming prototype does not care for switches and lights
	//both go directly to train
	public void setSpeedAuthority(int trainId, int speed, int authority); //sent to train controller
	
	//train model uses
	public void setBlockOccupied(int blockId, int trainId);
	public void setBlockUnoccupied(int blockId);
	
	
	//only handled by track model
	public void setUI(TrackModelUI tmUI);
	public LinkedList<Block> getTrack();
	public void setTC(TrackControllerInterface trackComm);
	public void setTI(TrainInterface trainComm);
	public void setBeacon(); //sent to train controller
	public void setStation(String station); //sent to train controller
	public void excelReader() throws IOException;
	public void fileRead(String filename) throws IOException;
	public LinkedList<Line> getLines();
}
