package com.peanutbuttercookies.trainsystem.interfaces;
/*
*	Kevin Nash
*	10/12/2015
*/

public interface CTCModuleInterface {
	
	/*
	*	Using an int for blockId and trainId because we have not yet decided how they will be identified.
	*/

	public void setSpeed(int trainId, int speed);
	public void setAuthority(int trainId, int authority);
	public void setBlockOccupied(int blockId);
	public void setBlockUnoccupied(int blockId);
	public void markBlockForRepairs(int blockId);
}