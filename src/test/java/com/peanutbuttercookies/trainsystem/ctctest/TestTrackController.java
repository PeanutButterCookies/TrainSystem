package com.peanutbuttercookies.trainsystem.ctctest;

import java.util.HashMap;
import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.trackcontroller.PLCProgram_OLD;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackController;

public class TestTrackController implements TrackControllerInterface {

	private LinkedList<Block> section;
	private int rec;
	private int speed;
	private int authority;

	public TestTrackController(LinkedList<Block> section) {
		this.section = section;
	}

	@Override
	public String getLine() {
		return null;
	}

	@Override
	public int getControllerId() {
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
	public void setSpeedAuthority(int blockId, int speed, int authority) {
		rec = blockId;
		this.speed = speed;
		this.authority = authority;
	}


	@Override
	public boolean setPLCProgram(String plcProgramFileLocation) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HashMap<String, LinkedList<Block>> getSwitchList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean engageSwitch(String switchName, boolean engagement) {
		return false;
	}

	@Override
	public boolean engageRRCrossing(int blockId, boolean engagement) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean markBlockForMaintanence(int blockId, boolean needsRepair) {
		// TODO Auto-generated method stub
		return false;
	}

}
