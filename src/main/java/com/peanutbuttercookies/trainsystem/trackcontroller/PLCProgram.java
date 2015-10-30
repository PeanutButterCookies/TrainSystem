package com.peanutbuttercookies.trainsystem.trackcontroller;

public class PLCProgram implements PLCProgramInterface {

	@Override
	public boolean proceed(int currentBlockId, int nextBlockId, int authority) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean slowDown(int currentBlockId, int nextBlockId, int authority) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean engageSwitch(int currentBlockId, int nextBlockId, int authority) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean engageRRCrossing(int currentBlockId, int nextBlockId) {
		// TODO Auto-generated method stub
		return false;
	}

}
