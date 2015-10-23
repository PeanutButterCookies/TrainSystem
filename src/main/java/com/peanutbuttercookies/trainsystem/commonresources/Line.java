package com.peanutbuttercookies.trainsystem.commonresources;

import java.util.LinkedList;

public class Line {
	private final LinkedList<Block> blocks;
	private final String line;
	
	public Line(String initLine, LinkedList<Block> initBlocks){
		line	=initLine;
		blocks	=initBlocks;
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
}
