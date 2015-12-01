package com.peanutbuttercookies.trainsystem.ctc;

import javax.swing.table.AbstractTableModel;

public class TableUpdateThread implements Runnable {

	AbstractTableModel model;
	public TableUpdateThread(AbstractTableModel model) {
		this.model = model;
	}
	@Override
	public void run() {
		for(;;) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				System.out.println("Table update thread stopped");
			}
			model.fireTableDataChanged();
		}
	}

}
