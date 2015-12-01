/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctc;

import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.ui.CTCModuleUI;

public class CTCModule implements CTCModuleInterface {

	private TrackControllerInterface tc;
	private CTCModuleUI ui;

	private Map<String, CTCBlockModel> lineBlockMap;
	private Map<String, CTCTrainModel> lineTrainMap;
	private int maxTrain = 0;

	public CTCModule() {
		lineBlockMap = new HashMap<String, CTCBlockModel>();
		lineTrainMap = new HashMap<String, CTCTrainModel>();
	}

	@Override
	public void setTC(TrackControllerInterface tc) {
		this.tc = tc;
	}

	@Override
	public void setBlockOccupied(String line, int blockId) {
		CTCBlockModel model = lineBlockMap.get(line);
		boolean newTrain = model.setOccupied(true, blockId);
		if (newTrain) {
			lineTrainMap.get(line).addTrain(new NewCTCTrain());
		} else {
			lineTrainMap.get(line).moveTrain(model.getPrevBlock(blockId), blockId, true);
		}
	}

	@Override
	public void setBlockUnoccupied(String line, int blockId) {
		CTCBlockModel model = lineBlockMap.get(line);
		boolean removeTrain = model.setOccupied(false, blockId);
		lineTrainMap.get(line).moveTrain(model.getPrevBlock(blockId), blockId, false);
	}

	public Integer getMaxTrain() {
		return maxTrain;
	}

	@Override
	public void importLine(Line line) {

		if (!lineBlockMap.containsKey(line.getLine())) {
			lineBlockMap.put(line.getLine(), new CTCBlockModel());
		}
		if (!lineTrainMap.containsKey(line.getLine())) {
			lineTrainMap.put(line.getLine(), new CTCTrainModel());
		}

		for (Block block : line.getAllBlocks()) {
			lineBlockMap.get(line.getLine()).addBlock(block);
		}
		if (ui != null) {
			ui.addLine(line.getLine());
		}
	}

	@Override
	public CTCBlockModel newBlockModel(String line) {
		return lineBlockMap.get(line);
	}

	@Override
	public CTCTrainModel newTrainModel(String line) {
		return lineTrainMap.get(line);
	}

	@Override
	public DefaultComboBoxModel<CTCTrain> newTrainCombo(String line) {
		DefaultComboBoxModel<CTCTrain> model = new DefaultComboBoxModel<CTCTrain>();
		model.addElement(new NewCTCTrain());
		return model;
	}

	@Override
	public DefaultComboBoxModel<CTCBlock> newBlockCombo(String line, CTCSection section) {
		if (!lineBlockMap.containsKey(line)) {
			System.out.println("Line : " + line + ", not initialized");
			return null;
		}
		DefaultComboBoxModel<CTCBlock> model = new DefaultComboBoxModel<CTCBlock>();
		CTCBlockModel blockModel = lineBlockMap.get(line);
		for (CTCBlock block : blockModel.getBlocks(section)) {
			model.addElement(block);
		}
		return model;
	}

	@Override
	public DefaultComboBoxModel<CTCSection> newSectionCombo(String line) {
		if (!lineBlockMap.containsKey(line)) {
			System.out.println("Line : " + line + ", not initialized");
			return null;
		}
		DefaultComboBoxModel<CTCSection> model = new DefaultComboBoxModel<CTCSection>();
		for (CTCSection section : lineBlockMap.get(line).getSections()) {
			model.addElement(section);
		}
		return model;
	}

	public void setUi(CTCModuleUI ui) {
		this.ui = ui;
	}

	@Override
	public boolean dispatch(String line, String speed, CTCBlock block, CTCTrain train) {

		int speedInt = 0;
		try {
			speedInt = Integer.parseInt(speed.replaceAll("[^\\d]", ""));
		} catch (Exception e) {
			return false;
		}
		return tc.setSpeedAuthority(line, train.getHead(), speedInt, block.getBlockNumber());
	}

	@Override
	public boolean repair(String line, CTCBlock block) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeSwitch(String line, CTCBlock block) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setSchedule(String line, String filename, ScheduleModel model) {
		// TODO
		return false;
	}

	@Override
	public ScheduleModel newScheduleModel(String line) {
		return new ScheduleModel();
	}

	@Override
	public boolean setTrainComponent(ComponentContainer container) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setClockSpeed(double clockSpeed) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean engageRRCrossing(String line, int blockId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void switchChanged(String line, int switchId, int blockId) {
		// TODO Auto-generated method stub

	}

	// FOR TESTING ONLY

	public CTCTrainModel getTrainModel(String line) {
		return lineTrainMap.get(line);
	}

	public CTCBlockModel getBlockModel(String line) {
		return lineBlockMap.get(line);
	}

}
