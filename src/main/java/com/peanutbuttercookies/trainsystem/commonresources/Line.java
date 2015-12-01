package com.peanutbuttercookies.trainsystem.commonresources;

import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.trackcontroller.TrackController;

public class Line {

	private final LinkedList<Block> blocks;
	private final String line;
	private LinkedList<TrackController> controllers;
	
	public Line(String initLine, LinkedList<Block> initBlocks){
		this.line	=initLine;
		this.blocks	=initBlocks;
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
	
	public LinkedList<TrackController> getAllTrackControllers(){
		return controllers;
	}
	
	public TrackController getTrackController(int index){
		return controllers.get(index);
	}
}
