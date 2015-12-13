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

import org.neo4j.graphdb.Direction;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;

public class CTCBlockModel extends AbstractTableModel {

	private static final long serialVersionUID = -3573813996444899446L;

	private Map<Integer, CTCBlock> switchMap;
	private Map<String, CTCSection> sections;
	private Map<Integer, TrackControllerInterface> tcMap;
	private Thread update;
	private Neo4JBlockGraph neo4j;
	private String line;

	public CTCBlockModel(String line, Neo4JBlockGraph neo4j) {
		this.neo4j = neo4j;
		switchMap = new LinkedHashMap<Integer, CTCBlock>();
		sections = new LinkedHashMap<String, CTCSection>();
		tcMap = new LinkedHashMap<Integer, TrackControllerInterface>();
		update = new Thread(new TableUpdateThread(this));
		update.setDaemon(true);
		update.start();
		this.line = line;
	}
	
	public void addTc(TrackControllerInterface tc) {
		if(!tcMap.containsKey(tc.getControllerId())) {
			tcMap.put(tc.getControllerId(), tc);
		}
	}

	public void addBlock(Block block, TrackControllerInterface tc) {
		//TODO manage sections
		neo4j.addBlock(line, block);
		fireTableDataChanged();
	}

	public Collection<CTCSection> getSections() {
		return sections.values();
	}

	public List<CTCBlock> getBlocks(CTCSection section) {
		//TODO
//		return sections.get(section.getName()).getBlocks();
		return null;
	}

	public boolean setOccupied(int blockId) {
		if(!neo4j.setBlockOccupied(line, blockId)) {
			System.out.println("Block not initialized");
			return false;
		}
		fireTableDataChanged();
		if(blockId == 0) {
			CTCBlock outgoing = neo4j.getAdjacentNode(line, 0, Direction.OUTGOING);
			if(!outgoing.isOccupied()) {
				return true;
			} 
		}
		
		return false;
	}

	public boolean setUnoccupied(int blockId) {
		if(!neo4j.setBlockOccupied(line, blockId)) {
			System.out.println("Block not initialized");
			return false;
		}
		fireTableDataChanged();
		if(blockId == 0) {
			CTCBlock incoming = neo4j.getAdjacentNode(line, 0, Direction.INCOMING);
			if(!incoming.isOccupied()) {
				return true;
			} 
		}
		
		return false;
	}

	@Override
	public int getColumnCount() {
		return CTCBlock.getFieldsSize();
	}

	@Override
	public int getRowCount() {
		return neo4j.getBlockCount(line);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		CTCBlock block = neo4j.getBlock(line, rowIndex);
		if(block == null) {
			return null;
		}

		switch (columnIndex) {
		case 0:
			return block.getBlockNumber();
		case 1:
			return block.getSection();
		case 2:
			return block.isOccupied();
		case 3:
			return block.isASwitch();
		case 4:
			return block.getThroughput();
		default:
			return null;
		}

	}

	@Override
	public String getColumnName(int column) {
		return CTCBlock.getField(column);
	}

}
