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

}
