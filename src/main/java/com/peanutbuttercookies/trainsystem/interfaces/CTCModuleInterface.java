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
import com.peanutbuttercookies.trainsystem.ctc.ScheduleModel;

/**
 * Interface for the CTC Module
 * @author Kevin
 *
 */
public interface CTCModuleInterface {
	
	/*
	*	Using an int for blockId and trainId because we have not yet decided how they will be identified.
	*/

	// for use by wrapper
	public void setUi(CTCModuleUI ui);

	// for use by the CTCUI	
	/**
	 * Sends speed in m/s and authority in meters to a block
	 * @param line
	 * @param speed
	 * @param train
	 * @param end
	 * @return
	 */
	public boolean dispatch(String line, int speed, int train, int end);
	
	/**
	 * Tells the track controller to mark a block for repair
	 * @param line
	 * @param block
	 * @return
	 */
	public boolean repair(String line, CTCBlock block);
	
	/**
	 * tells the track controller to engage or disengage a switch
	 * @param line
	 * @param block
	 * @param engaged
	 * @return
	 */
	public boolean changeSwitch(String line, int block, boolean engaged);
	
	/**
	 * Sets a schedule from a space delimited text file
	 * @param line
	 * @param filename
	 * @return
	 */
	public boolean setSchedule(String line, String filename);
	
	/**
	 * Engages or disengages a rr crossing
	 * @param line
	 * @param blockId
	 * @param engaged
	 * @return
	 */
	public boolean engageRRCrossing(String line, int blockId, boolean engaged);

	public ScheduleModel newScheduleModel(String line);
	public CTCBlockModel newBlockModel(String line);
	public CTCTrainModel newTrainModel(String line);
	public DefaultComboBoxModel<CTCTrain> newTrainCombo(String line);
	public DefaultComboBoxModel<CTCSection> newSectionCombo(String line);
	public DefaultComboBoxModel<Integer> newBlockCombo(String line, CTCSection section);
	public DefaultComboBoxModel<Integer> newSwitchCombo(String line);
	public DefaultComboBoxModel<Integer> newSwitchDestCombo(String line, int switchBlock);
	
	// for use by the track controller
	
	/**
	 * Updates the ctc's version of the track with block occupation
	 * @param line
	 * @param blockId
	 */
	public void setBlockOccupied(String line, int blockId);

	/**
	 * Updates the ctc's version of the track with block occupation
	 * @param line
	 * @param blockId
	 */
	public void setBlockUnoccupied(String line, int blockId);
	
	/**
	 * Adds a new line to the ctc
	 * @param line
	 * @return
	 */
	public boolean importLine(Line line);
	
	/**
	 * Updates the ctc's version of the track with switch states
	 * @param line
	 * @param blockId
	 * @param engaged
	 */
	public void switchChanged(String line, int blockId, boolean engaged);
	
	/**
	 * Updates the ctc's version of the track with rr crossings
	 * @param line
	 * @param blockId
	 * @param engaged
	 */
	public void setRRCrossingEngaged(String line, int blockId, boolean engaged);

}