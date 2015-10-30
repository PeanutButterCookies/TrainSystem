package com.peanutbuttercookies.trainsystem.trackcontroller;

public interface PLCProgramInterface {
	boolean proceed(int currentBlockId,int nextBlockId, int authority);
	boolean slowDown(int currentBlockId,int nextBlockId, int authority);
	boolean engageSwitch(int currentBlockId,int nextBlockId, int authority);
	boolean engageRRCrossing(int currentBlockId,int nextBlockId);
}
