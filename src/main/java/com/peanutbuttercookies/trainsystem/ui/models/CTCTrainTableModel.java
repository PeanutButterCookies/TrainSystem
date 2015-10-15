package com.peanutbuttercookies.trainsystem.ui.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CTCTrainTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 330746980610177300L;

	List<Integer> trains;
	HashMap<Integer, Integer> trainToBlock;
	HashMap<Integer, Integer> blockToTrain;
	
	public CTCTrainTableModel() {
		trainToBlock = new HashMap<Integer, Integer>();
		blockToTrain = new HashMap<Integer, Integer>();
		trains = new ArrayList<Integer>();
	}
	
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return trains.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0: 
			return trains.get(rowIndex);
		case 1: 
			return trainToBlock.get(trains.get(rowIndex));
		}
		return "error";
	}
	
	public void addTrain(int train, int block) {
		trains.add(train);
		trainToBlock.put(train, block);
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex) {
		case 0:
			return "Train Number";
		case 1:
			return "Block Number";
		default:
			return "error";
		}
	}
}
