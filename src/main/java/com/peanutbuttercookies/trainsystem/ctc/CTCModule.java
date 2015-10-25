/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;

public class CTCModule implements CTCModuleInterface {

	private TrackControllerInterface tc;
	
	private CTCBlockModel blockModel;
	private CTCTrainModel trainModel;

	private int maxTrain = 0;

	public CTCModule() throws IOException {
		blockModel = new CTCBlockModel();
		trainModel = new CTCTrainModel();

		// for prototype
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(CTCModule.class.getResourceAsStream("/trackLayout.txt")));
		reader.readLine();
		while (reader.ready()) {
			String[] line = reader.readLine().split(" ");
			addBlock(Integer.parseInt(line[2]));
		}

	}
	
	@Override
	public void setTC(TrackControllerInterface tc) {
		this .tc = tc;
	}

	private void addBlock(int blockId) {
		if (!blockToTrain.containsKey(blockId)) {
			blockToTrain.put(blockId, 0);
			blocks.add(new CTCBlock(blockId));
		}
	}

	@Override
	public void setBlockOccupied(int blockId) {
		System.out.println("CTC setBlockOccupied blockId : " + blockId);
		setBlockStatus(blockId, true);
	}

	private void setBlockStatus(int blockId, boolean occupied) {
		for (CTCBlock block : blocks) {
			if (block.getBlockNumber() == blockId) {
				block.setOccupied(occupied);
				break;
			}
		}
	}

	@Override
	public void setBlockUnoccupied(int blockId) {
		setBlockStatus(blockId, false);
	}

	@Override
	public void markBlockForRepairs(Integer blockId) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean send(String speed, Integer train, Integer authority) {
		int speedInt = 0;
		try {
			speedInt = Integer.parseInt(speed);
		} catch(NumberFormatException e) {
			System.out.println("Not a number");
			return false;
		}
		if(train < 0 || train > maxTrain) {
			return false;
		} else if(train == 0) {
			maxTrain++;
		}
		if(tc.setSpeedAuthority(train + 1, speedInt, authority)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Integer getMaxTrain() {
		return maxTrain;
	}

	@Override
	public List<CTCBlock> getBlocks() {
		return blocks;
	}

	@Override
	public AbstractTableModel getBlockModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractTableModel getTableModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
