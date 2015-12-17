package com.peanutbuttercookies.trainsystem.ctctest;

import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.trackcontroller.PLCProgram;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackController;

public class TestTrackController implements TrackControllerInterface {

	private LinkedList<Block> section;

	public TestTrackController(LinkedList<Block> section) {
		this.section = section;
	}

	@Override
	public String getLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getControllerId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStartBlock() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEndBlock() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOverlapBlock() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LinkedList<Block> getSection() {
		return section;
	}

	@Override
	public Block getBlock(int index) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setPLCProgram(PLCProgram newPlcProgram) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSpeedAuthority(int blockId, int speed, int authority) {
		System.out.println("Block Id: " + blockId + ", Speed: " + speed + ", Authority: " + authority);
	}

	@Override
	public void engageSwitch(String switchName, boolean engagement) {
		// TODO Auto-generated method stub
		
	}

}
