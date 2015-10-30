package com.peanutbuttercookies.trainsystem.ctc;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CTCTrainModel extends AbstractTableModel {

	/**
	 * I hate this shit
	 */
	private static final long serialVersionUID = 3648136737558041721L;

	private List<CTCTrain> trains;
	
	public enum Side {
		HEAD,
		TAIL
	}
	
	public CTCTrainModel() {
		trains = new LinkedList<CTCTrain>();
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
		return trains.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex > trains.size()) {
			return null;
		}
		CTCTrain train = trains.get(rowIndex);

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
		trains.add(train);
		fireTableDataChanged();
	}
	
	public void moveTrain(int prevBlock, int newBlock, Side side) {
		for(CTCTrain train : trains) {
			switch(side) {
			case HEAD:
				if(train.getHead() == prevBlock) {
					train.setHead(newBlock);
				}
				break;
			case TAIL:
				if(train.getTail() == prevBlock) {
					train.setHead(newBlock);
				}
				break;
			}
		}
		fireTableDataChanged();
	}
}
