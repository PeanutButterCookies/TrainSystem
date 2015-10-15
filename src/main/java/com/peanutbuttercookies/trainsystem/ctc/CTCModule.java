package com.peanutbuttercookies.trainsystem.ctc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;

public class CTCModule implements CTCModuleInterface {

//	private TrackControllerModuleInterface trackCtrl;
	
	private List<CTCBlock> blocks;
	private List<Integer> trains;
	private HashMap<Integer, Integer> trainToBlock;
	private HashMap<Integer, Integer> blockToTrain;
	private HashSet<Integer> blockSet;

	private int maxTrain = 0;
	private int speed = 0;
	private int authority = 0;
	private int train = 1;

	public CTCModule() throws IOException {
		blocks = new ArrayList<CTCBlock>();
		trainToBlock = new HashMap<Integer, Integer>();
		blockToTrain = new HashMap<Integer, Integer>();
		trains = new ArrayList<Integer>();

		// for prototype
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(CTCModule.class.getResourceAsStream("/trackLayout.txt")));
		reader.readLine();
		while (reader.ready()) {
			String[] line = reader.readLine().split(" ");
			addBlock(Integer.parseInt(line[2]));
		}

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
		// TODO Auto-generated method stub
		if(train < 1 || train > maxTrain + 1) {
			return false;
		} else if(train == maxTrain + 1) {
			maxTrain++;
		}
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
