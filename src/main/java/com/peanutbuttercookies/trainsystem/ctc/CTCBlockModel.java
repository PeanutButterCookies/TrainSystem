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
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;

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
		update.setDaemon(true);
		update.start();
	}
	
	public CTCBlock getBlock(int blockId) {
		return blockMap.get(blockId);
	}

	public void addBlock(Block block, TrackControllerInterface tc) {
		if (block == null) {
			return;
		}
		CTCBlock ctcBlock = null;
		if (blockMap.containsKey(block.getBlockNumber())) {
			ctcBlock = blockMap.get(block.getBlockNumber());
			ctcBlock.setAll(block);
		} else {
			ctcBlock = new CTCBlock(block);
		}
		ctcBlock.setTc(tc);

		if (!sections.containsKey(ctcBlock.getSection())) {
			sections.put(ctcBlock.getSection(), new CTCSection(ctcBlock.getSection()));
		}

		sections.get(ctcBlock.getSection()).addBlock(ctcBlock);
		if (ctcBlock.isSwitch()) {
			switchMap.put(ctcBlock.getBlockNumber(), ctcBlock);
		}

		if (block.getBlockNumber() == 1) {
			System.out.println("Prev block set to null");
			ctcBlock.setPrevBlock(null);
		} else {
			int index = (block.getNextPossible().size() > 1)? 1:0;
			Integer prev = block.getNextPossible().get(index).getBlockNumber();
			if (blockMap.containsKey(prev)) {
				System.out.println("Used an old CTCBlock");
				ctcBlock.setPrevBlock(blockMap.get(prev));
			} else {
				System.out.println("Made a new CTCBlock");
				CTCBlock newBlock = new CTCBlock(block.getNextPossible().get(index));
				blockMap.put(prev, newBlock);
				ctcBlock.setPrevBlock(newBlock);
			}
		}

		if(block.getBlockNumber() != 1 && block.getNextPossible().size() == 1) {
			ctcBlock.setNextBlock(null);
		} else {
			Integer next = block.getNextPossible().get(0).getBlockNumber();
			if (blockMap.containsKey(next)) {
				ctcBlock.setNextBlock(blockMap.get(next));
			} else {
				CTCBlock newBlock = new CTCBlock(block.getNextPossible().get(0));
				blockMap.put(next, newBlock);
				ctcBlock.setNextBlock(newBlock);
			}
		}
		if (ctcBlock.getBlockNumber() > 9)
			System.out.println("Bad block");
		if (!blockMap.containsKey(ctcBlock.getBlockNumber())) {
			blockMap.put(ctcBlock.getBlockNumber(), ctcBlock);
		}
		
		System.out.println("This: " +ctcBlock);
		System.out.println("Next: " + ctcBlock.getNextBlock());
		System.out.println("Prev: " + ctcBlock.getPrevBlock());
		
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
		if (blockMap.containsKey(blockId)) {
			blockMap.get(blockId).setOccupied(true);
			fireTableDataChanged();
			if (blockId == 1 && !blockMap.get(blockMap.get(1).getNextBlock().getBlockNumber()).getNextBlock().isOccupied()) {
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
		if (blockMap.containsKey(blockId)) {
			blockMap.get(blockId).setOccupied(false);
			fireTableDataChanged();
			if (blockId == 1 && !blockMap.get(2).getNextBlock().isOccupied()) {
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
		if (block == null) {
			return null;
		} else if (block.getPrevBlock() == null) {
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
		if (!blockMap.containsKey(rowIndex)) {
			return null;
		}

		switch (columnIndex) {
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

	// FOR TESTING
	public Map<Integer, CTCBlock> getBlockMap() {
		return blockMap;
	}
}
