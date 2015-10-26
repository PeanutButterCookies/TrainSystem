/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctc;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;

public class CTCModule implements CTCModuleInterface {

	private TrackControllerInterface tc;
	
	private Map<String, CTCBlockModel> lineBlockMap;
	private Map<String, CTCTrainModel> lineTrainMap;

	private int maxTrain = 0;

	public CTCModule() {
		lineBlockMap = new HashMap<String, CTCBlockModel>();
		lineTrainMap = new HashMap<String, CTCTrainModel>();

	}
	
	@Override
	public void setTC(TrackControllerInterface tc) {
		this .tc = tc;
	}

	@Override
	public void setBlockOccupied(int blockId) {
		//TODO
	}

	@Override
	public void setBlockUnoccupied(int blockId) {
		//TODO
	}

	@Override
	public void markBlockForRepairs(Integer blockId) {
		// TODO Auto-generated method stub

	}
//
//	@Override
//	public boolean send(String speed, Integer train, Integer authority) {
//		int speedInt = 0;
//		try {
//			speedInt = Integer.parseInt(speed);
//		} catch(NumberFormatException e) {
//			System.out.println("Not a number");
//			return false;
//		}
//		if(train < 0 || train > maxTrain) {
//			return false;
//		} else if(train == 0) {
//			maxTrain++;
//		}
//		if(tc.setSpeedAuthority(line , train + 1, speedInt, authority)) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	public Integer getMaxTrain() {
		return maxTrain;
	}

	@Override
	public void importTrack(List<Block> blocks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractTableModel newBlockModel(String line, JTable table) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractTableModel newTrainModel(String line, JTable table) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultComboBoxModel<CTCTrain> newTrainCombo(String line) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DefaultComboBoxModel<CTCBlock> newBlockCombo(String line) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean perform(String line, File file, String speed) {
		// TODO Auto-generated method stub
		return false;
	}

}
