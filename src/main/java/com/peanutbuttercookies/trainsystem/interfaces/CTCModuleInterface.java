/*
 * Kevin Nash
 * 10/15/2015
 */


package com.peanutbuttercookies.trainsystem.interfaces;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.ctc.CTCBlock;
import com.peanutbuttercookies.trainsystem.ctc.CTCBlockModel;
import com.peanutbuttercookies.trainsystem.ctc.CTCSection;
import com.peanutbuttercookies.trainsystem.ctc.CTCTrain;
import com.peanutbuttercookies.trainsystem.ctc.CTCTrainModel;
import com.peanutbuttercookies.trainsystem.ui.CTCModuleUI;

public interface CTCModuleInterface {
	
	/*
	*	Using an int for blockId and trainId because we have not yet decided how they will be identified.
	*/

	// for use by wrapper
	public void setTC(TrackControllerInterface tc);
	public void setUi(CTCModuleUI ui);

	// for use by the CTCUI
	public boolean perform(String line, String use, String filename, String speed);
	public CTCBlockModel newBlockModel(String line);
	public CTCTrainModel newTrainModel(String line);
	public DefaultComboBoxModel<CTCSection> newSectionCombo(String line);
	public DefaultComboBoxModel<CTCTrain> newTrainCombo(String line);
	public DefaultComboBoxModel<CTCBlock> newBlockCombo(String line, CTCSection section);
	
	// for use by the track controller
	public void setBlockOccupied(String line, int blockId);
	public void setBlockUnoccupied(String line, int blockId);
	public void importLine(Line line);
}