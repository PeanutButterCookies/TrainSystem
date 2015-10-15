/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.peanutbuttercookies.trainsystem.ui.BlockTableModel;
import com.peanutbuttercookies.trainsystem.ui.TrainTableModel;

public class CTCModel {
	
	private BlockTableModel btm;
	private TrainTableModel ttm;
	
	private List<CTCBlock> blocks;
	private List<CTCTrain> trains;

	private HashMap<Integer, CTCBlock> blockMap;
	private HashMap<Integer, CTCTrain> trainMap;

	private HashMap<Integer, Integer> trainToBlock;
	
	public CTCModel() {
		blocks = new ArrayList<CTCBlock>();
		trains = new LinkedList<CTCTrain>();
		blockMap = new HashMap<Integer, CTCBlock>();
		trainToBlock = new HashMap<Integer, Integer>();
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
}
