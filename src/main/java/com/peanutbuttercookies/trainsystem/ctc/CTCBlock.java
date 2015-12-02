/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;

public class CTCBlock {
	private static List<String> fields;

	static {
		fields = Arrays.asList(new String[] {
			"Block Number",
			"Section",
			"Occupied",
			"Switch",
			"Throughput"
		});
	}
	
	private Integer blockNumber;
	private String line;
	private String section;
	private boolean occupied;
	private CTCBlock nextBlock;
	private CTCBlock prevBlock;
	private List<CTCBlock> possibleNext;
	private long starttime;
	private int numTrains;
	private TrackControllerInterface tc;
	
	public TrackControllerInterface getTc() {
		return tc;
	}

	public void setTc(TrackControllerInterface tc) {
		this.tc = tc;
	}

	public CTCBlock() {
		possibleNext = new ArrayList<CTCBlock>();
		nextBlock = null;
		prevBlock = null;
		setOccupied(false);
		starttime = System.currentTimeMillis();
	}

	public CTCBlock(int blockNumber, String line, String section) {
		this();
		setBlockNumber(blockNumber);
		setLine(line);
		setSection(section);

	}
	
	public CTCBlock(Block block) {
		this();
		setAll(block);
	}
	
	public void setAll(Block block) {
		setBlockNumber(block.getBlockNumber());
		setLine(block.getLine());
		setSection(block.getSection());
	}
	
	public Integer getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(Integer blockNumber) {
		this.blockNumber = blockNumber;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		if(occupied) {
			numTrains++;
		}
		this.occupied = occupied;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
	
	public void addPossible(CTCBlock block) {
		if(nextBlock == null) {
			setNextBlock(block);
			block.setPrevBlock(this);
		}
		possibleNext.add(block);
	}

	public CTCBlock getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(CTCBlock nextBlock) {
		this.nextBlock = nextBlock;
	}

	public CTCBlock getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(CTCBlock prevBlock) {
		this.prevBlock = prevBlock;
	}
	
	public boolean isSwitch() {
		return possibleNext.size() > 1;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	
	//TODO change this to work with scaling simulation time
	public double getThroughput() {
		long time = System.currentTimeMillis();
		long diff = time - starttime;
		double seconds = diff/1000;
		double minutes = seconds/60;
		double hours = minutes/60;
		if(hours < 1) {
			hours = 1;
		}
		double throughput = numTrains/hours;
		return throughput;
		
		
	}
	
	@Override
	/**
	 * Equals makes blocks unique by number and line
	 */
	public boolean equals(Object other) {
		if(!(other instanceof CTCBlock)) {
			return false;
		}
		
		return line.equals(((CTCBlock)other).getLine()) && blockNumber.equals(((CTCBlock)other).getBlockNumber());
	}
	
	@Override
	public int hashCode() {
		String unique = line + blockNumber;
		return unique.hashCode();
	}
	
	public static int getFieldsSize() {
		return fields.size();
	}
	
	public static String getField(int index) {
		return fields.get(index);
	}
	
	public String toString() {
		return blockNumber.toString();
	}
	public String state() {
		return "prev: " + ((prevBlock == null)? "null": prevBlock.getBlockNumber())
				+ "\ncurr: " + blockNumber
				+ "\nnext: " + ((nextBlock == null)? "null": nextBlock.getBlockNumber());
	}
}
