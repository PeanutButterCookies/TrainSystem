/*
 * Kevin Nash
 * 10/15/2015
 */


package com.peanutbuttercookies.trainsystem.ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.peanutbuttercookies.trainsystem.ctc.CTCBlock;

public class BlockTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -7286763962374146400L;

	private LinkedHashMap<Integer, Boolean> blockMap;
	private List<Integer> blockList;
	
	public BlockTableModel() {
		blockMap = new LinkedHashMap<Integer, Boolean>();
		blockList = new ArrayList<Integer>();

	}
	
	@Override
	public String getColumnName(int col) {
		switch(col) {
		case 0:
			return "Block Number";
		case 1:
			return "Occupied";
		}
		return "error";
	}
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return blockMap.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return blockList.get(rowIndex);
		case 1:
			return blockMap.get(blockList.get(rowIndex));
		}
		return "error";
	}
	
	public void addBlock(CTCBlock block) {
		blockList.add(block.getBlockNumber());
		blockMap.put(block.getBlockNumber(), block.isOccupied());
		fireTableRowsInserted(blockMap.size() - 1, blockMap.size() - 1);
	}

}
