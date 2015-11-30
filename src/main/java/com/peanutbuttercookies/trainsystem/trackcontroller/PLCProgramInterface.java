package com.peanutbuttercookies.trainsystem.trackcontroller;

public interface PLCProgramInterface {
	boolean stop(int currentBlockId,int nextBlockId);
	boolean slowDown(int currentBlockId,int nextBlockId);
	boolean engageSwitch(int currentBlockId,int nextBlockId);
	boolean engageRRCrossing(int currentBlockId,int nextBlockId);
	boolean loadPLCProgram(String fileLocation, int loadType);
}
