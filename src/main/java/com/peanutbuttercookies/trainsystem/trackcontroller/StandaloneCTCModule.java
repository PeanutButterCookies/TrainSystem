package com.peanutbuttercookies.trainsystem.trackcontroller;

import javax.swing.DefaultComboBoxModel;

import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.ctc.CTCBlock;
import com.peanutbuttercookies.trainsystem.ctc.CTCBlockModel;
import com.peanutbuttercookies.trainsystem.ctc.CTCModuleUI;
import com.peanutbuttercookies.trainsystem.ctc.CTCSection;
import com.peanutbuttercookies.trainsystem.ctc.CTCTrain;
import com.peanutbuttercookies.trainsystem.ctc.CTCTrainModel;
import com.peanutbuttercookies.trainsystem.ctc.ScheduleModel;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;

public class StandaloneCTCModule implements CTCModuleInterface {

	@Override
	public void setUi(CTCModuleUI ui) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean dispatch(String line, int speed, int train, int end) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean repair(String line, CTCBlock block) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeSwitch(String line, int block, boolean engaged) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setSchedule(String line, String filename) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ScheduleModel newScheduleModel(String line) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CTCBlockModel newBlockModel(String line) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CTCTrainModel newTrainModel(String line) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultComboBoxModel<CTCTrain> newTrainCombo(String line) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultComboBoxModel<CTCSection> newSectionCombo(String line) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultComboBoxModel<Integer> newBlockCombo(String line, CTCSection section) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultComboBoxModel<Integer> newSwitchCombo(String line) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultComboBoxModel<Integer> newSwitchDestCombo(String line, int switchBlock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean engageRRCrossing(String line, int blockId, boolean engaged) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setBlockOccupied(String line, int blockId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBlockUnoccupied(String line, int blockId) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean importLine(Line line) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void switchChanged(String line, int blockId, boolean engaged) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRRCrossingEngaged(String line, int blockId, boolean engaged) {
		// TODO Auto-generated method stub

	}

}
