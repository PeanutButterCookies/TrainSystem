package com.peanutbuttercookies.trainsystem.interfaces;

import java.io.InputStream;
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
	public boolean engageSwitch(String switchName, boolean engagement);
	public boolean engageRRCrossing(int blockId, boolean engagement);
	public boolean setPLCProgram(String file);
	public boolean markBlockForMaintanence(int blockId, boolean needsRepair);
	public void setSpeedAuthority(int blockId, int speed, int authority);
	public HashMap<String, LinkedList<Block>> getSwitchList();
}
