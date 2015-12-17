package com.peanutbuttercookies.trainsystem.ctc;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ScheduleModel extends AbstractTableModel {

	private static final long serialVersionUID = 5797601164773736484L;
	private List<Command> commands;
	
	public ScheduleModel() {
		commands = new ArrayList<Command>();
	}

	@Override
	public int getColumnCount() {
		return Command.getFieldsCount();
	}

	@Override
	public int getRowCount() {
		return commands.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		Command command = commands.get(rowIndex);
		switch(colIndex) {
		case 0:
			return command.getRec();
		case 1:
			return command.getDest();
		case 2:
			return command.getSpeed();
		case 3:
			return command.getTime();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return Command.getField(column);
	}
}
