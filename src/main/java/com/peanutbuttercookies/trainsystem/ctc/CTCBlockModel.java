/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import com.peanutbuttercookies.trainsystem.commonresources.Block;

public class CTCBlockModel extends AbstractTableModel {
	
	
	/**
	 * Fuck eclipse idk what this does
	 */
	private static final long serialVersionUID = -3573813996444899446L;

	private Map<String, Map<Integer, CTCBlock>> lineBlockMap;
	private Map<String, Map<Integer, CTCBlock>> lineSwitchMap;
	private String selectedLine = null;

	
	public CTCBlockModel() {
		lineBlockMap = new HashMap<String, Map<Integer, CTCBlock>>();
		lineSwitchMap = new HashMap<String, Map<Integer, CTCBlock>>();
	}
	
	public boolean addBlock(Block block) {
		CTCBlock ctcBlock = new CTCBlock(block);
		if(!lineBlockMap.containsKey(ctcBlock.getLine())) {
			addLine(ctcBlock.getLine());
		}
		
		Map<Integer, CTCBlock> blockMap = lineBlockMap.get(ctcBlock.getLine());
		
		if(blockMap.containsKey(ctcBlock.getBlockNumber())) {
			System.out.println("Duplicate block number");
			return false;
		}
		if(ctcBlock.isSwitch()) {
			lineSwitchMap.get(ctcBlock.getLine()).put(ctcBlock.getBlockNumber(), ctcBlock); 
		}
		
		return true;
	}
	
	public void removeBlock(int block, String line) {
		if(!lineBlockMap.containsKey(line)) {
			return;
		}
		
		lineBlockMap.get(line).remove(block);
		lineSwitchMap.get(line).remove(block);
		
	}
	
	public String getSelectedLine() {
		return selectedLine;
	}
	
	public void setSelectedLine(String selectedLine) {
		this.selectedLine = selectedLine;
	}
	
	@Override
	public int getColumnCount() {
		return CTCBlock.getFieldsSize();
	}

	@Override
	public int getRowCount() {
		if(!lineBlockMap.containsKey(selectedLine)) {
			return 0;
		}
		return lineBlockMap.get(selectedLine).size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(!lineBlockMap.containsKey(selectedLine)) {
			return null;
		}

		Map<Integer, CTCBlock> blockMap = lineBlockMap.get(selectedLine);
		if(!blockMap.containsKey(rowIndex)) {
			return null;
		}

		switch(columnIndex) {
		case 0:
			return blockMap.get(rowIndex).getBlockNumber();
		case 1:
			return blockMap.get(rowIndex).isOccupied();
		case 2:
			return blockMap.get(rowIndex).isSwitch();
		default:
			return null;
		}

	}
	
	private void addLine(String line) {
		lineBlockMap.put(line, new LinkedHashMap<Integer, CTCBlock>());
		lineSwitchMap.put(line, new HashMap<Integer, CTCBlock>());
	}
}




