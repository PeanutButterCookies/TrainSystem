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
			"Section",
			"Occupied",
			"Switch"
		});
	}
	
	private Integer blockNumber;
	private String line;
	private String section;
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

	public CTCBlock(int blockNumber, String line, String section) {
		this();
		setBlockNumber(blockNumber);
		setLine(line);
		setSection(section);
	}
	
	public CTCBlock(Block block) {
		this(block.getBlockNumber(), block.getLine(), block.getSection());
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

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	
	public String toString() {
		return blockNumber.toString();
	}
	
}
