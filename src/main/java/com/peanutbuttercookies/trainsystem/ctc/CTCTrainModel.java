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
			return train.getTrainId();
		case 1:
			return train.getHead();
		case 2:
			return train.getTail();
		case 3:
			return train.getDirection();
		default:
			return null;
		}
		
	}
	
	public void addTrain(CTCTrain train) {

		trainList.add(train);
		fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	}
	
	public void moveTrain(int blockId) {
		//TODO
//		fireTableCellUpdated(rowIndex, 1);

	}

}
