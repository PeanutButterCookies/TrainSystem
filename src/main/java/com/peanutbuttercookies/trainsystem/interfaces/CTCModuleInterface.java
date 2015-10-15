package com.peanutbuttercookies.trainsystem.interfaces;
/*
*	Kevin Nash
*	10/12/2015
*/

import java.util.List;

import com.peanutbuttercookies.trainsystem.ctc.CTCBlock;

public interface CTCModuleInterface {
	
	/*
	*	Using an int for blockId and trainId because we have not yet decided how they will be identified.
	*/

	// for use by the CTCUI
	public void markBlockForRepairs(Integer blockId);
	public boolean send(String speed, Integer train, Integer authority);
	public Integer getMaxTrain();
	public List<CTCBlock> getBlocks();
	
	// for use by the track controller
	public void setBlockOccupied(int blockId);
	public void setBlockUnoccupied(int blockId);
}