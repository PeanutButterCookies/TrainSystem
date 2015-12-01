package com.peanutbuttercookies.trainsystem.interfaces;

import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.trackcontroller.PLCProgram;

/**
 * 
 * 		@author Chris Good
 * 		10/14/2015
 *
 */

public interface TrackControllerInterface {
	
	public String getLine();
	public int getControllerId();
	public int getStartBlock();
	public int getEndBlock();
	public int getOverlapBlock();
	public LinkedList<Block> getSection();
	public Block getBlock(int index);
	public void engageSwitch(int switchNum);
	public void setPLCProgram(PLCProgram newPlcProgram);
	public void setSpeedAuthority(int speed, int authority);
}
