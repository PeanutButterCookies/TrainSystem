package com.peanutbuttercookies.trainsystem.ui.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;

import com.peanutbuttercookies.trainsystem.ctc.CTCBlock;

public class CTCBlockModel extends AbstractTableModel implements ComboBoxModel<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6251625849254865807L;

	private List<CTCBlock> blocks;
	
	public CTCBlockModel() throws IOException {
		blocks = new ArrayList<CTCBlock>();
		
//		For prototype only
		BufferedReader reader = new BufferedReader(new InputStreamReader(CTCBlockModel.class.getResourceAsStream("/trackLayout.txt")));
		reader.readLine();
		while(reader.ready()) {
			String[] line = reader.readLine().split(" ");
			System.out.println(line[2]);
			addBlock(new CTCBlock(Integer.parseInt(line[2])));
		}
	}
	
	public CTCBlockModel(List<CTCBlock> blocks) {
		this.blocks = blocks;
	}
	
	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return blocks.size();
	}

	public Object getValueAt(int row, int col) {
		if(row > getRowCount() || col > getColumnCount()) {
			return null;
		}

		switch (col) {
		case 0:
			return blocks.get(row).getBlockNumber();
		case 1:
			return blocks.get(row).isOccupied();
		}
		return "error";
	}
	
	public void addBlock(CTCBlock block) {
		blocks.add(block);
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		switch(columnIndex) {
		case 0:
			return "Block Number";
		case 1:
			return "Occupied";
		default:
			return "error";
		}
	}

	@Override
	public void addListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getElementAt(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeListDataListener(ListDataListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getSelectedItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSelectedItem(Object arg0) {
		// TODO Auto-generated method stub
		
	}

}
