package com.peanutbuttercookies.trainsystem.trackmodeltest;

import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.trackcontroller.PLCProgram;

public class TestTrackController implements TrackControllerInterface {
	private LinkedList<Line> track;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getBlock(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void engageSwitch(int switchNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPLCProgram(PLCProgram newPlcProgram) {
		// TODO Auto-generated method stub
		
	}

	public void setSpeedAuthority(String line, int blockId, double speed, double authority) {
		for(int i = 0; i < track.size(); i++)	{
			if(track.get(i).getLine().equals(line)){
				LinkedList<Block> newLine = track.get(i).getAllBlocks();
				newLine.get(blockId).setSpeedAuthority(speed, authority);
			}
		}
	}
	
	public void setTrack(LinkedList<Line> track)	{
		this.track = track;
	}

	@Override
	public void setSpeedAuthority(int blockId, int speed, int authority) {
		// TODO Auto-generated method stub
		
	}
}
