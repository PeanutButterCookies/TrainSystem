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
	
	private static final long serialVersionUID = -3573813996444899446L;

	private Map<Integer, CTCBlock> blockMap;
	private Map<Integer, CTCBlock> switchMap;
	private Map<String, CTCSection> sections;
	private Thread update;
	
	public CTCBlockModel() {
		blockMap = new LinkedHashMap<Integer, CTCBlock>();
		switchMap = new LinkedHashMap<Integer, CTCBlock>();
		sections = new LinkedHashMap<String, CTCSection>();
		update = new Thread(new TableUpdateThread(this));
		update.start();
	}
	
	public void addBlock(Block block) {
		CTCBlock ctcBlock = null;
		if(blockMap.containsKey(block.getBlockNumber())) {
			ctcBlock = blockMap.get(block.getBlockNumber());
			ctcBlock.setAll(block);
		} else {
			ctcBlock = new CTCBlock(block);
		}
		
		if(!sections.containsKey(ctcBlock.getSection())) {
			sections.put(ctcBlock.getSection(), new CTCSection(ctcBlock.getSection()));
		}
		
		sections.get(ctcBlock.getSection()).addBlock(ctcBlock);
		if(ctcBlock.isSwitch()) {
			switchMap.put(ctcBlock.getBlockNumber(), ctcBlock); 
		}
		
		for(Block b : block.getNextPossible()) {
			int num = b.getBlockNumber();
			if(blockMap.containsKey(num)) {
				ctcBlock.addPossible(blockMap.get(num));
			} else {
				CTCBlock newBlock = new CTCBlock();
				blockMap.put(num, newBlock);
				ctcBlock.addPossible(newBlock);
			}
		}
		
		if(block.getPrev() == null) {
			System.out.println("Previous block is null");
			ctcBlock.setPrevBlock(null);
		} else {
			Integer prev = block.getPrev().getBlockNumber();
			if(blockMap.containsKey(prev)) {
				ctcBlock.setPrevBlock(blockMap.get(prev));
			} else {
				CTCBlock newBlock = new CTCBlock();
				blockMap.put(prev, newBlock);
				ctcBlock.setPrevBlock(newBlock);
			}
		}
		
		if(block.getNext() == null) {
			System.out.println("Next block is null");
			ctcBlock.setNextBlock(null);
		} else {
			Integer next = block.getNext().getBlockNumber();
			if(blockMap.containsKey(next)) {
				ctcBlock.setNextBlock(blockMap.get(next));
			} else {
				CTCBlock newBlock = new CTCBlock();
				blockMap.put(next, newBlock);
				ctcBlock.setNextBlock(newBlock);
			}
		}
		blockMap.put(ctcBlock.getBlockNumber(), ctcBlock);
		fireTableDataChanged();
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
		fireTableDataChanged();
	}
	
	public boolean setOccupied(int blockId) {
		if(blockMap.containsKey(blockId)) {
			blockMap.get(blockId).setOccupied(true);
			fireTableDataChanged();
			if(blockId == 0 && !blockMap.get(0).getNextBlock().isOccupied()) {
				return true;
			} else {
				return false;
			}
		} else {
			System.out.println("That block is not initialized");
			return false;
		}
	}
	
	public boolean setUnoccupied(int blockId) {
		if(blockMap.containsKey(blockId)) {
			blockMap.get(blockId).setOccupied(false);
			fireTableDataChanged();
			if(blockId == 0 && !blockMap.get(0).getNextBlock().isOccupied()) {
				return true;
			} else {
				return false;
			}
		} else {
			System.out.println("That block is not initialized");
			return false;
		}
	}
	
	public Integer getPrevBlock(int blockId) {
		CTCBlock block = blockMap.get(blockId);
		if(block == null) {
			return null;
		} else if(block.getPrevBlock() == null) {
			return null;
		} else {
			return block.getPrevBlock().getBlockNumber();
		}
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
	
	@Override
	public String getColumnName(int column) {
		return CTCBlock.getField(column);
	}
	
	//FOR TESTING
	public Map<Integer, CTCBlock> getBlockMap() {
		return blockMap;
	}
}




