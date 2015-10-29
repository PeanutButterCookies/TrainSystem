package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Line;

/**
 * 
 * @author Chris Good
 * 
 * This class is to store individual lines and their block structures
 *
 */
public class TC_Line extends Line {

	private final String lineName;
	private final LinkedList<TC_Block> blocks;
	
	//public TC_Line(String initLine, LinkedList<TC_Block> initBlocks) {
		//super(initLine, initBlocks);
		// TODO Auto-generated constructor stub
		
	}
	
	
	
	public TC_Line(LinkedList<TC_Block> initBlocks){
		super(initLineName);
		lineName=initLineName;
		blocks=initBlocks;
	}
	
	public String getLineName(){
		return lineName;
	}
	
	public LinkedList<TC_Block> getAllBlocks(){
		return blocks;
	}
	
	public TC_Block getBlock(int index){
		return (TC_Block)blocks.get(index);
	}
	
}
