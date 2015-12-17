/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.ctctest.TestTrackController;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;

public class CTCModule implements CTCModuleInterface {

	private CTCModuleUI ui;
	private Neo4JBlockGraph neo4j;

	private Map<String, CTCBlockModel> lineBlockMap;
	private Map<String, CTCTrainModel> lineTrainMap;
	private Map<String, ScheduleModel> lineScheduleMap;

	public CTCModule() {
		lineBlockMap = new HashMap<String, CTCBlockModel>();
		lineTrainMap = new HashMap<String, CTCTrainModel>();
		lineScheduleMap = new HashMap<String, ScheduleModel>();
		neo4j = new Neo4JBlockGraph();
	}

	@Override
	public void setBlockOccupied(String line, int blockId) {
		System.out.println("Setting block occupied: " + blockId);
		CTCBlockModel model = lineBlockMap.get(line);
		boolean newTrain = model.setOccupied(blockId);
		if (newTrain) {
			lineTrainMap.get(line).addTrain();
		} else {
			System.out.println("Block ID: " + blockId);
			lineTrainMap.get(line).moveHead(model.getBlock(blockId).getBlockNumber(),
					model.getPrevBlock(blockId).getBlockNumber());

		}
	}

	@Override
	public void setBlockUnoccupied(String line, int blockId) {
		CTCBlockModel model = lineBlockMap.get(line);
		boolean removeTrain = model.setUnoccupied(blockId);
		if (!removeTrain) {
			lineTrainMap.get(line).moveTail(model.getBlock(blockId).getBlockNumber(),
					model.getNextBlock(blockId).getBlockNumber());
		} else {
			lineTrainMap.get(line).removeTrain();
		}
	}

	@Override
	public boolean importLine(Line line) {

		if (line == null) {
			return false;
		}

		if (!lineBlockMap.containsKey(line.getLine())) {
			lineBlockMap.put(line.getLine(), new CTCBlockModel(line.getLine(), neo4j));
		}
		if (!lineTrainMap.containsKey(line.getLine())) {
			lineTrainMap.put(line.getLine(), new CTCTrainModel());
		}

		for (TrackControllerInterface tc : line.getAllTrackControllers()) {
			for (Block block : tc.getSection()) {
				if (block == null) {
					return false;
				}
				lineBlockMap.get(line.getLine()).addBlock(block, tc);
			}
		}

		if (ui != null) {
			ui.addLine(line.getLine());
		}

		return true;
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
		lineTrainMap.get(line).setComboModel(model);
		return model;
	}

	@Override
	public DefaultComboBoxModel<Integer> newBlockCombo(String line, CTCSection section) {
		if (!lineBlockMap.containsKey(line)) {
			System.out.println("Line : " + line + ", not initialized");
			return null;
		}
		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<Integer>();
		CTCBlockModel blockModel = lineBlockMap.get(line);
		for (Integer block : blockModel.getBlocks(section)) {
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
		System.out.println("Size: " + lineBlockMap.get(line).getSections().size());
		for (CTCSection section : lineBlockMap.get(line).getSections()) {
			model.addElement(section);
		}
		return model;
	}

	public void setUi(CTCModuleUI ui) {
		this.ui = ui;
		for (String line : lineBlockMap.keySet()) {
			ui.addLine(line);
		}
	}

	@Override
	public boolean dispatch(String line, int speed, int train, int end) {

		CTCBlockModel model = lineBlockMap.get(line);
		int authority = model.getAuthority(train, end);
		CTCBlock start = model.getBlock(train);
		TrackControllerInterface tc = lineBlockMap.get(line).getTC(start);
		tc.setSpeedAuthority(train, speed, authority);
		return true;
	}

	@Override
	public boolean repair(String line, CTCBlock block) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeSwitch(String line, int blockId, boolean engaged) {
		CTCBlock block = neo4j.getBlock(line, blockId);
		TrackControllerInterface tc = lineBlockMap.get(line).getTC(block);
		tc.engageSwitch(Integer.toString(block.getSwitchNum()), engaged);
		return false;
	}

	@Override
	public boolean setSchedule(String line, String filename) {
		try {
			lineScheduleMap.get(line).importSchedule(filename);
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public ScheduleModel newScheduleModel(String line) {
		ScheduleModel model = new ScheduleModel(lineBlockMap.get(line), this, line);
		lineScheduleMap.put(line, model);
		return model;
	}

	@Override
	public boolean engageRRCrossing(String line, int blockId, boolean engaged) {
		CTCBlockModel model = lineBlockMap.get(line);
		CTCBlock block = model.getBlock(blockId);
		TrackControllerInterface tc = model.getTC(block);
		// TODO
		return false;
	}

	@Override
	public void switchChanged(String line, int blockId, boolean engaged) {
		neo4j.engageSwitch(line, blockId, engaged);
	}

	@Override
	public DefaultComboBoxModel<Integer> newSwitchCombo(String line) {
		Vector<Integer> switches = lineBlockMap.get(line).getSwitches();
		return new DefaultComboBoxModel<Integer>(switches);
	}

	@Override
	public DefaultComboBoxModel<Integer> newSwitchDestCombo(String line, int switchBlock) {
		Vector<Integer> possible = neo4j.getSwitchNext(line, switchBlock);
		return new DefaultComboBoxModel<Integer>(possible);
	}

	@Override
	public void setRRCrossingEngaged(String line, int blockId, boolean engaged) {
		// TODO Auto-generated method stub

	}

}
