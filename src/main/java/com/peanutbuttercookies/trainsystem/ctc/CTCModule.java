/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctc;

import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;

public class CTCModule implements CTCModuleInterface {

	private TrackControllerInterface tc;
	
	private Map<String, CTCBlockModel> lineBlockMap;
	private Map<String, CTCTrainModel> lineTrainMap;
	private Map<String, ComboBoxModelContainer> comboMap;
	private int maxTrain = 0;

	public CTCModule() {
		lineBlockMap = new HashMap<String, CTCBlockModel>();
		lineTrainMap = new HashMap<String, CTCTrainModel>();
		comboMap = new HashMap<String, ComboBoxModelContainer>();
	}
	
	@Override
	public void setTC(TrackControllerInterface tc) {
		this .tc = tc;
	}

	//TODO train logic
	@Override
	public void setBlockOccupied(String line, int blockId) {
		CTCBlockModel model = lineBlockMap.get(line);
		model.setOccupied(true, blockId);
		lineTrainMap.get(line).moveTrain(model.getPrevBlock(blockId), blockId, CTCTrainModel.Side.HEAD);
	}

	@Override
	public void setBlockUnoccupied(String line, int blockId) {
		CTCBlockModel model = lineBlockMap.get(line);
		model.setOccupied(true, blockId);
		lineTrainMap.get(line).moveTrain(model.getPrevBlock(blockId), blockId, CTCTrainModel.Side.TAIL);
	}

// TODO
//	@Override
//	public boolean send(String speed, Integer train, Integer authority) {
//		int speedInt = 0;
//		try {
//			speedInt = Integer.parseInt(speed);
//		} catch(NumberFormatException e) {
//			System.out.println("Not a number");
//			return false;
//		}
//		if(train < 0 || train > maxTrain) {
//			return false;
//		} else if(train == 0) {
//			maxTrain++;
//		}
//		if(tc.setSpeedAuthority(line , train + 1, speedInt, authority)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	public Integer getMaxTrain() {
		return maxTrain;
	}

	@Override
	public void importLine(Line line) {
		addLine(line.getLine());
		for(Block block : line.getAllBlocks()) {
			lineBlockMap.get(line.getLine()).addBlock(block);
		}
		
	}

	@Override
	public AbstractTableModel newBlockModel(String line, JTable table) {
		return lineBlockMap.get(line);
	}

	@Override
	public AbstractTableModel newTrainModel(String line, JTable table) {
		return lineTrainMap.get(line);
	}

	@Override
	public DefaultComboBoxModel<CTCTrain> newTrainCombo(String line) {
		DefaultComboBoxModel<CTCTrain> model = new DefaultComboBoxModel<CTCTrain>();
		model.addElement(new NewCTCTrain());
		comboMap.get(line).setTrainCombo(model);
		return model;
	}

	@Override
	public DefaultComboBoxModel<CTCBlock> newBlockCombo(String line, CTCSection section) {
		if(checkLineInit(line)) {
			System.out.println("Line : " + line + ", not initialized");
			return null;
		}
		DefaultComboBoxModel<CTCBlock> model = new DefaultComboBoxModel<CTCBlock>();
		CTCBlockModel blockModel = lineBlockMap.get(line);
		for(CTCBlock block: blockModel.getBlocks(section)) {
			model.addElement(block);
		}
		comboMap.get(line).addBlockCombo(section, model);
		return model;
	}
	
	@Override
	public DefaultComboBoxModel<CTCSection> newSectionCombo(String line) {
		if(checkLineInit(line)) {
			System.out.println("Line : " + line + ", not initialized");
			return null;
		}
		DefaultComboBoxModel<CTCSection> model = new DefaultComboBoxModel<CTCSection>();
		for(CTCSection section : lineBlockMap.get(line).getSections()) {
			model.addElement(section);
		}
		comboMap.get(line).setSectionCombo(model);
		return model;
	}

	@Override
	public boolean perform(String line, String use, String filename, String speed) {
		//TODO
		switch(use) {
		case "Dispatch":
		case "Mark for Repair":
		case "Change switch":
		case "Set schedule":
		default:
			return false;
		}
	}
	
	private void addLine(String line) {
		if(!lineBlockMap.containsKey(line)) {
			lineBlockMap.put(line, new CTCBlockModel());
		}
		if(!lineTrainMap.containsKey(line)) {
			lineTrainMap.put(line, new CTCTrainModel());
		}
		if(!comboMap.containsKey(line)) {
			comboMap.put(line, new ComboBoxModelContainer());
		}
	}
	
	private boolean checkLineInit(String line) {
		return comboMap.containsKey(line);
	}

}
