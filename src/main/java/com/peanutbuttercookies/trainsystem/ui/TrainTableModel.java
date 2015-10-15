package com.peanutbuttercookies.trainsystem.ui;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TrainTableModel extends AbstractTableModel {

	private List<Integer> trainList;
	private HashMap<Integer, Integer> trainMap;
	
	public TrainTableModel() {
		trainList = new LinkedList<Integer>();
		trainMap = new HashMap<Integer, Integer>();
	}

	@Override
	public String getColumnName(int col) {
		switch(col) {
		case 0:
			return "Train ID";
		case 1:
			return "Block Number";
		}
		return "error";
	}
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return trainList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return trainList.get(rowIndex);
		case 1:
			return trainMap.get(trainList.get(rowIndex));
		}
		
		return "error";
	}
	
	public void moveTrain(int blockId) {
		int min = Integer.MAX_VALUE;
		int trainId = -1;
		int rowIndex = -1;
		for(int i=0; i<trainList.size(); i++) {
			int train = trainList.get(i);
			int diff = blockId - train;
			if(diff < 0) {
				diff *= -1;
			}
			if(diff < min) {
				trainId = train;
				min = diff;
				rowIndex = i;
			}
		}
		int current = trainMap.get(trainId);
		if(current < blockId) {
			trainMap.put(trainId, min);
		}
		
		fireTableCellUpdated(rowIndex, 1);

	}

}
