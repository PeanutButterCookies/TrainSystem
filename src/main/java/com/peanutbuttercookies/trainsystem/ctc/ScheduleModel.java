package com.peanutbuttercookies.trainsystem.ctc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;

public class ScheduleModel extends AbstractTableModel {

	private static final long serialVersionUID = 5797601164773736484L;
	private List<Command> commands;
	private CTCBlockModel model;
	private CTCModuleInterface ctc;
	private Iterator<Command> iterator;
	private String line;

	public ScheduleModel(CTCBlockModel model, CTCModuleInterface ctc, String line) {
		this.model = model;
		this.ctc = ctc;
		this.line = line;
		commands = new ArrayList<Command>();
	}
	
	public void importSchedule(String filename) throws IOException, NumberFormatException {
		commands = new ArrayList<Command>();
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String start = "yard";
		while (reader.ready()) {
			start = parseCommands(reader.readLine(), start);
		}
		
		reader.close();
		iterator = commands.iterator();
	}

	private String parseCommands(String wholeLine, String start) throws NumberFormatException {
			String[] line = wholeLine.split("\\s+");
			double time = Double.parseDouble(line[line.length - 1]);
			String station = "";
			for(int i=0 ;i< line.length - 1; i++) {
				station += line[i];
			}
			List<Command> temp = model.getCommands(start, station, time);
			commands.addAll(temp);
			return station;
	}
	
	public void next() {
		boolean two = false;
		if(!iterator.hasNext()) {
			return;
		}
		Command command = null;
		do {
			command = iterator.next();
			two = command.send(ctc, line);
		}while(iterator.hasNext() && two);
	}
	

	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public int getRowCount() {
		return commands.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		Command command = commands.get(rowIndex);
		return command.toString();
	}

	@Override
	public String getColumnName(int column) {
		return "Schedule";
	}
}
