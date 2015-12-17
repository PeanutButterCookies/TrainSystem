/*
 * Kevin Nash
 * 10/15/2015
 */


package com.peanutbuttercookies.trainsystem.interfaces;

import javax.swing.DefaultComboBoxModel;

import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.ctc.CTCBlock;
import com.peanutbuttercookies.trainsystem.ctc.CTCBlockModel;
import com.peanutbuttercookies.trainsystem.ctc.CTCModuleUI;
import com.peanutbuttercookies.trainsystem.ctc.CTCSection;
import com.peanutbuttercookies.trainsystem.ctc.CTCTrain;
import com.peanutbuttercookies.trainsystem.ctc.CTCTrainModel;
import com.peanutbuttercookies.trainsystem.ctc.ComponentContainer;
import com.peanutbuttercookies.trainsystem.ctc.ScheduleModel;

public interface CTCModuleInterface {
	
	/*
	*	Using an int for blockId and trainId because we have not yet decided how they will be identified.
	*/

	// for use by wrapper
	public void setUi(CTCModuleUI ui);

	// for use by the CTCUI	
	public boolean dispatch(String line, int speed, int train, int end);
	public boolean repair(String line, CTCBlock block);
	public boolean changeSwitch(String line, int block, boolean engaged);
	public boolean setSchedule(String line, String filename);
	public ScheduleModel newScheduleModel(String line);
	public CTCBlockModel newBlockModel(String line);
	public CTCTrainModel newTrainModel(String line);
	public DefaultComboBoxModel<CTCTrain> newTrainCombo(String line);
	public DefaultComboBoxModel<CTCSection> newSectionCombo(String line);
	public DefaultComboBoxModel<Integer> newBlockCombo(String line, CTCSection section);
	public DefaultComboBoxModel<Integer> newSwitchCombo(String line);
	public DefaultComboBoxModel<Integer> newSwitchDestCombo(String line, int switchBlock);
	public boolean engageRRCrossing(String line, int blockId);
	
	// for use by the track controller
	public void setBlockOccupied(String line, int blockId);
	public void setBlockUnoccupied(String line, int blockId);
	public void importLine(Line line);
	public void switchChanged(String line, int blockId, boolean engaged);

}