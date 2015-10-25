/*
 * Kevin Nash
 * 10/15/2015
 */


package com.peanutbuttercookies.trainsystem.interfaces;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.ctc.CTCBlock;

public interface CTCModuleInterface {
	
	/*
	*	Using an int for blockId and trainId because we have not yet decided how they will be identified.
	*/

	// for use by wrapper
	public void setTC(TrackControllerInterface tc);

	// for use by the CTCUI
	public void markBlockForRepairs(Integer blockId);
	public boolean send(String speed, Integer train, Integer authority);
	public List<CTCBlock> getBlocks();
	public AbstractTableModel getBlockModel();
	public AbstractTableModel getTableModel();
	
	// for use by the track controller
	public void setBlockOccupied(int blockId);
	public void setBlockUnoccupied(int blockId);
	public void importTrack(List<Block> blocks);
}