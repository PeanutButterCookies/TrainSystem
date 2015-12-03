package com.peanutbuttercookies.trainsystem.interfaces;
/*
*	Fauzul Azim
*	10/14/2015
*/

import java.io.IOException;
import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.trackmodel.TrackModelUI;

public interface TrackModelInterface {
	//for train access, but should not need it
	public int getSpeed(int trainId);
	public int getAuthority(int trainId);
	public int getBeacon(int trainId);
	
	
	//trackController uses these; assuming prototype does not care for switches and lights
	//both go directly to train
	public void setSpeedAuthority(int blockId, int speed, int authority); //sent to train controller
	
	//train model uses
	public void setBlockUnoccupied(String line, int blockId);
	
	
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
	public void setSwitch(int blockId);
	public void setSpeedAuthority(String line, int blockId, int speed, int authority);
	public void setBlockOccupied(String line, int blockId);
}
