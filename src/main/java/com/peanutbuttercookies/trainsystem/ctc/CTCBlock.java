/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.peanutbuttercookies.trainsystem.commonresources.Block;

public class CTCBlock extends AbstractCTCBean {
	
	static {
		fields = Arrays.asList(new String[] {
			"Block Number",
			"Occupied",
			"Switch"
		});
	}
	
	private int blockNumber;
	private String line;
	private boolean occupied;
	private CTCBlock nextBlock;
	private CTCBlock prevBlock;
	private List<CTCBlock> possibleNext;
	
	public CTCBlock() {
		possibleNext = new ArrayList<CTCBlock>();
		nextBlock = null;
		prevBlock = null;
		setOccupied(false);
	}

	public CTCBlock(int blockNumber, String line) {
		this();
		setBlockNumber(blockNumber);
		setLine(line);
	}
	
	public CTCBlock(Block block) {
		this(block.getBlockNumber(), block.getLine());
	}
	
	public int getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
	
	public void addPossible(CTCBlock block) {
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
	
}
