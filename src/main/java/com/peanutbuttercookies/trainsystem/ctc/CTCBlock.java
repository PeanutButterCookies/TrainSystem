/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctc;

public class CTCBlock {
	int blockNumber;
	boolean occupied;
	
	public CTCBlock(int blockNumber) {
		setBlockNumber(blockNumber);
		setOccupied(false);
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
}
