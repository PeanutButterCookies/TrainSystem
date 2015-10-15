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

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;

public class CTCModule implements CTCModuleInterface {

	private TrackControllerInterface tc;
	
	private List<CTCBlock> blocks;
	private HashMap<Integer, Integer> blockToTrain;

	private int maxTrain = 0;

	public CTCModule() throws IOException {
		blocks = new ArrayList<CTCBlock>();
		blockToTrain = new HashMap<Integer, Integer>();

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
		} else if(train == maxTrain + 1) {
			maxTrain++;
		}
		tc.setSpeedAuthority(train, speedInt, authority);
		return true;
	}

	@Override
	public Integer getMaxTrain() {
		return maxTrain;
	}

	@Override
	public List<CTCBlock> getBlocks() {
		return blocks;
	}

}
