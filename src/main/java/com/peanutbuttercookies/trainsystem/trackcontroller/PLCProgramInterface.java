package com.peanutbuttercookies.trainsystem.trackcontroller;

import com.peanutbuttercookies.trainsystem.commonresources.Block;

public interface PLCProgramInterface {
	boolean stop(Block currBlock);
	boolean slowDown(Block currBlock);
	boolean engageSwitch(Block currBlock);
	boolean engageRRCrossing(Block currBlock);
	boolean loadPLCProgram(String fileLocation, int loadType);
}
