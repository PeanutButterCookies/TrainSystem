/*
 * Kevin Nash
 * 10/15/2015
 */


package com.peanutbuttercookies.trainsystem.interfaces;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.ctc.CTCBlock;
import com.peanutbuttercookies.trainsystem.ctc.CTCSection;
import com.peanutbuttercookies.trainsystem.ctc.CTCTrain;

public interface CTCModuleInterface {
	
	/*
	*	Using an int for blockId and trainId because we have not yet decided how they will be identified.
	*/

	// for use by wrapper
	public void setTC(TrackControllerInterface tc);

	// for use by the CTCUI
	public void markBlockForRepairs(Integer blockId);
	public boolean perform(String line, String use, String filename, String speed);
	public AbstractTableModel newBlockModel(String line, JTable table);
	public AbstractTableModel newTrainModel(String line, JTable table);
	public DefaultComboBoxModel<CTCSection> newSectionCombo(String line);
	public DefaultComboBoxModel<CTCTrain> newTrainCombo(String line);
	public DefaultComboBoxModel<CTCBlock> newBlockCombo(String line, CTCSection section);
	
	// for use by the track controller
	public void setBlockOccupied(int blockId);
	public void setBlockUnoccupied(int blockId);
	public void importTrack(List<Block> blocks);
}