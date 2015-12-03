package com.peanutbuttercookies.trainsystem.commonresources;

import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackController;

public class Line {
	private final LinkedList<Block> blocks;
	private final String line;
	private LinkedList<TrackControllerInterface> controllers;
	
	public Line(String initLine, LinkedList<Block> initBlocks){
		this.line	=initLine;
		this.blocks	=initBlocks;
		controllers = new LinkedList<TrackControllerInterface>();
	}

	public String getLine(){
		return line;
	}
	
	public LinkedList<Block> getAllBlocks(){
		return blocks;
	}
	
	public Block getBlock(int index){
		return blocks.get(index);
	}
	
	public void setTrackControllers(TrackController tc_1, TrackController tc_2){
		controllers.add(tc_1);
		controllers.add(tc_2);
	}
	
	public LinkedList<TrackControllerInterface> getAllTrackControllers(){
		return controllers;
	}
	
	public TrackControllerInterface getTrackController(int index){
		return controllers.get(index);
	}
}
