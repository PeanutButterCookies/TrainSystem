package com.peanutbuttercookies.trainsystem.interfaces;

import java.util.HashMap;
import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;

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
	public void engageSwitch(String switchName, boolean engagement);
	public void setPLCProgram(String plcProgramFileLocation);
	public void setSpeedAuthority(int blockId, int speed, int authority);
	public HashMap<String, LinkedList<Block>> getSwitchList();
}
