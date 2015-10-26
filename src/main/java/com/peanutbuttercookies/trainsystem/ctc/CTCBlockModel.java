/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctc;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import com.peanutbuttercookies.trainsystem.commonresources.Block;

public class CTCBlockModel extends AbstractTableModel {
	
	
	/**
	 * Fuck eclipse idk what this does
	 */
	private static final long serialVersionUID = -3573813996444899446L;

	private Map<Integer, CTCBlock> blockMap;
	private Map<Integer, CTCBlock> switchMap;
	private Map<String, CTCSection> sections;
	
	public CTCBlockModel() {
		blockMap = new LinkedHashMap<Integer, CTCBlock>();
		switchMap = new LinkedHashMap<Integer, CTCBlock>();
		sections = new LinkedHashMap<String, CTCSection>();
	}
	
	public boolean addBlock(Block block) {
		CTCBlock ctcBlock = new CTCBlock(block);
		
		
		if(blockMap.containsKey(ctcBlock.getBlockNumber())) {
			System.out.println("Duplicate block number");
			return false;
		}
		if(!sections.containsKey(ctcBlock.getSection())) {
			sections.put(ctcBlock.getSection(), new CTCSection(ctcBlock.getSection()));
		}
		
		sections.get(ctcBlock.getSection()).addBlock(ctcBlock);
		if(ctcBlock.isSwitch()) {
			switchMap.put(ctcBlock.getBlockNumber(), ctcBlock); 
		}
		
		return true;
	}
	
	public Collection<CTCSection> getSections() {
		return sections.values();
	}
	
	public List<CTCBlock> getBlocks(CTCSection section) {
		return sections.get(section.getName()).getBlocks();
	}
	
	public void removeBlock(int block, String line) {
		blockMap.remove(block);
		switchMap.remove(block);
		
	}
	
	@Override
	public int getColumnCount() {
		return CTCBlock.getFieldsSize();
	}

	@Override
	public int getRowCount() {
		return blockMap.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(!blockMap.containsKey(rowIndex)) {
			return null;
		}

		switch(columnIndex) {
		case 0:
			return blockMap.get(rowIndex).getBlockNumber();
		case 1:
			return blockMap.get(rowIndex).getSection();
		case 2:
			return blockMap.get(rowIndex).isOccupied();
		case 3:
			return blockMap.get(rowIndex).isSwitch();
		case 4:
			return blockMap.get(rowIndex).getThroughput();
		default:
			return null;
		}

	}
}




