package com.peanutbuttercookies.trainsystem.ctc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CTCTrainModel extends AbstractTableModel {

	/**
	 * I hate this shit
	 */
	private static final long serialVersionUID = 3648136737558041721L;

	private List<CTCTrain> trainList;
	
	public CTCTrainModel() {
		trainList = new LinkedList<CTCTrain>();
	}

	@Override
	public String getColumnName(int col) {
		return CTCTrain.getField(col);
	}

	@Override
	public int getColumnCount() {
		return CTCTrain.getFieldsSize();
	}

	@Override
	public int getRowCount() {
		return trainList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex > trainList.size()) {
			return null;
		}
		CTCTrain train = trainList.get(rowIndex);

		switch(columnIndex) {
		case 0:
			return train.;
		case 1:
			return trainList.get(rowIndex)
		}
		
		return "error";
	}
	
	public void addTrain(int trainId) {
		if(trainMap.containsKey(trainId)) {
			return;
		}

		trainList.add(trainId);
		trainMap.put(trainId, 0);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
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
