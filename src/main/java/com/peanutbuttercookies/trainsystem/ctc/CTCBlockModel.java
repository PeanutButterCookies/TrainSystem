/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import org.neo4j.graphdb.Direction;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;

public class CTCBlockModel extends AbstractTableModel {

	private static final long serialVersionUID = -3573813996444899446L;

	private Set<Integer> switches;
	private Map<String, CTCSection> sections;
	private Map<Integer, TrackControllerInterface> tcMap;
	private Thread update;
	private Neo4JBlockGraph neo4j;
	private String line;
	private int scheduleBlock = -1;
	private ScheduleModel model;

	public CTCBlockModel(String line, Neo4JBlockGraph neo4j) {
		this.neo4j = neo4j;
		switches = new LinkedHashSet<Integer>();
		sections = new LinkedHashMap<String, CTCSection>();
		tcMap = new LinkedHashMap<Integer, TrackControllerInterface>();
		update = new Thread(new TableUpdateThread(this));
		update.setDaemon(true);
		update.start();
		this.line = line;
	}
	
	public CTCBlock getNextBlock(int blockId) {
		return neo4j.getNextBlock(line, blockId);
	}
	
	public CTCBlock getPrevBlock(int blockId) {
		return neo4j.getPrevBlock(line, blockId);
	}

	public void addBlock(Block block, TrackControllerInterface tc) {
		if (!sections.containsKey(block.getSection())) {
			System.out.println("Section added: " + block.getSection());
			CTCSection section = new CTCSection(block.getSection());
			sections.put(block.getSection(), section);
		}

		if (!tcMap.containsKey(tc.getControllerId())) {
			tcMap.put(tc.getControllerId(), tc);
		}

		sections.get(block.getSection()).addBlock(block.getBlockNumber());
		if (block.hasSwitch()) {
			switches.add(block.getBlockNumber());
		}

		neo4j.addBlock(line, block, tc.getControllerId());
		fireTableDataChanged();
	}

	public CTCBlock getBlock(int blockId) {
		return neo4j.getBlock(line, blockId);
	}

	public Collection<CTCSection> getSections() {
		return sections.values();
	}

	public List<Integer> getBlocks(CTCSection section) {
		return sections.get(section.getName()).getBlocks();
	}

	public boolean setOccupied(int blockId) {
		if (!neo4j.setBlockOccupied(line, blockId, true)) {
			System.out.println("Block not initialized");
			return false;
		}
		fireTableDataChanged();
		if (blockId == 0) {
			CTCBlock outgoing = neo4j.getNextBlock(line, blockId);
			System.out.println(outgoing.getBlockNumber() + " " + outgoing.isOccupied());
			if (!outgoing.isOccupied()) {
				return true;
			}
		}
		
		if(blockId == scheduleBlock) {
			model.next();
		}

		return false;
	}
	
	public void setModel(ScheduleModel model) {
		this.model = model;
	}

	public void setScheduleBlock(int scheduleBlock) {
		this.scheduleBlock = scheduleBlock;
	}

	public boolean setUnoccupied(int blockId) {
		if (!neo4j.setBlockOccupied(line, blockId, false)) {
			System.out.println("Block not initialized");
			return false;
		}
		fireTableDataChanged();
		if (blockId == 0) {
			CTCBlock incoming = neo4j.getAdjacentNode(line, 0, Direction.INCOMING);
			if (!incoming.isOccupied()) {
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
		if (block == null) {
			return null;
		}

		switch (columnIndex) {
		case 0:
			return block.getBlockNumber();
		case 1:
			return block.getSection();
		case 2:
			return (block.getStation() == null) ? "" : block.getStation();
		case 3:
			return block.isOccupied();
		case 4:
			if(!block.isASwitch()) {
				return "";
			} else {
				return neo4j.getCurrentSwitch(line, rowIndex).getBlockNumber();
			}
		case 5:
			return block.getThroughput();
		case 6:
			return (block.getRR() == null)? "" : block.getRR();
		default:
			return null;
		}

	}

	@Override
	public String getColumnName(int column) {
		return CTCBlock.getField(column);
	}

	public TrackControllerInterface getTC(CTCBlock block) {
		return tcMap.get(block.getTc());
	}

	public int getAuthority(int start, int end) {
		List<Integer> nodes = neo4j.getShortestPath(line, start, end);
		int authority = neo4j.getAuthority(line, nodes);
		return authority;
	}

	public Vector<Integer> getSwitches() {
		Vector<Integer> switchList = new Vector<Integer>();
		for (Integer i : switches) {
			switchList.add(i);
		}
		return switchList;
	}

	public List<Command> getCommands(String start, String end, double time) {
		CTCBlock one = neo4j.getBlock(line, start);
		CTCBlock two = neo4j.getBlock(line, end);
		List<Integer> blocks = neo4j.getShortestPath(line, one.getBlockNumber(), two.getBlockNumber());
		List<Command> commands = new ArrayList<Command>();
		for (int i = 0; i < blocks.size(); i++) {
			int id = blocks.get(i);
			CTCBlock block = neo4j.getBlock(line, id);
			if (block.isASwitch()) {
				commands.add(neo4j.getSwitchCommand(line, id, blocks.get(i + 1)));
			}
		}
		int authority = neo4j.getAuthority(line, blocks);
		int speed = (int) (authority/time/60);
		commands.add(new DispatchCommand(one.getBlockNumber(), authority, speed));
		return commands;
	}
}
