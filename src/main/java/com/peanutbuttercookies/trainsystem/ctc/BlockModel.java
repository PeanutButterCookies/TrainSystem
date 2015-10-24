/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class BlockModel extends AbstractTableModel {
	
	
	private List<CTCTrain> trains;
	private Map<String, CTCBlock> switchMap;
	private Map<Integer, CTCBlock> blockMap;

	
	public BlockModel() {
		trains = new LinkedList<CTCTrain>();
		blockMap = new HashMap<Integer, Block>();
		switchMap = new HashMap<String, CTCBlock>();
	}
	
	public boolean addBlock(int block) {
		return false;
	}
	
	public boolean removeBlock(int block) {
		return false;
	}
	
	public boolean addTrain(int train) {
		return false;
	}
	
	public boolean removeTrain(int train) {
		return false;
	}
	
	public boolean importTrack(String filename, Integer sheetNum) throws IOException {
		FileInputStream fileStream = new FileInputStream(new File(filename));
		HSSFWorkbook book = new HSSFWorkbook(fileStream);
		HSSFSheet sheet = book.getSheetAt(sheetNum);
		
		return false;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}




