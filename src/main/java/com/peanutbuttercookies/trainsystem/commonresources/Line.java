package com.peanutbuttercookies.trainsystem.commonresources;

import java.util.LinkedList;


public class Line {
	private LinkedList<Block> blocks;
	private String line;
	
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
}
