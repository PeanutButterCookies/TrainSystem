package com.peanutbuttercookies.trainsystem.trackcontroller;

/**
 * 
 * @author Chris Good
 * 
 * This class is to store individual lines and their block structures
 *
 */
public class TC_Line {
	private final String lineName;
	//private final Vector<TC_Block> blocks;
	private final TC_Block[] blocks;
	
	public TC_Line(String initLineName, TC_Block[] initBlocks){
		lineName=initLineName;
		blocks=initBlocks;
	}
	
	public String getLineName(){
		return lineName;
	}
	
	public TC_Block[] getBlocks(){
		return blocks;
	}
}
