package com.peanutbuttercookies.trainsystem.ctc;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.AbstractTableModel;

public class CTCTrainModel extends AbstractTableModel {

	private static final long serialVersionUID = 3648136737558041721L;

	private List<CTCTrain> trains;
	private DefaultComboBoxModel<CTCTrain> comboModel;
	private int max = 1;
	
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
	
	public void addNewTrain() {
		comboModel.addElement(new NewCTCTrain());
	}
	public void addTrain() {
		CTCTrain train = new CTCTrain();
		train.setHead(0);
		train.setTail(0);
		train.setTrainId(max++);
		trains.add(train);
		comboModel.addElement(train);
		fireTableDataChanged();
	}
	
	public void moveHead(Integer prevBlock, Integer newBlock) {
		for(CTCTrain train : trains) {
			if(train.getHead().equals(prevBlock)) {
				train.setHead(newBlock);
			}
		}
		fireTableDataChanged();
	}
	
	public void moveTail(Integer prevBlock, Integer newBlock) {
		for(CTCTrain train : trains) {
			if(train.getTail().equals(prevBlock)) {
				train.setTail(newBlock);
			}
		}
		fireTableDataChanged();
	}
	
	public void removeTrain() {
		Iterator<CTCTrain> iterator = trains.iterator();
		while(iterator.hasNext()) {
			CTCTrain train = iterator.next();
			if(train.getTail().equals(0)) {
				comboModel.removeElement(train);
				iterator.remove();
				break;
			}
		}
		fireTableDataChanged();
	}

	public DefaultComboBoxModel<CTCTrain> getComboModel() {
		return comboModel;
	}

	public void setComboModel(DefaultComboBoxModel<CTCTrain> comboModel) {
		this.comboModel = comboModel;
	}
}
