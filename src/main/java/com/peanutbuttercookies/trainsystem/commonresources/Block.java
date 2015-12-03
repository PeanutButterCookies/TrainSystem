package com.peanutbuttercookies.trainsystem.commonresources;

import java.util.LinkedList;
import java.util.List;

import com.peanutbuttercookies.trainsystem.interfaces.BlockOccupationListener;
import com.peanutbuttercookies.trainsystem.train.TrainModel;
import com.peanutbuttercookies.trainsystem.train.TrainModelInterface;

public class Block {
	private final String line;
	private final String section;
	private final int blockNumber;
	private final int blockLength;
	private final float blockGrade;
	private final int speedLimit;
	private final float elevation;
	private final float cumulativeElevation;
	private final boolean switchToYard;
	private final boolean switchFromYard;
	private final boolean infrastructureSwitch;
	private final boolean infrastructureUnderground;
	private final boolean infrastructureRRCrossing;
	private final boolean infrastructureStation;
	private boolean masterSwitch;
	private final String stationName;
	private final int switchBlockId; // switchBlockId=-1 for block without
										// switch
	private final int arrowDirectionA; // head=1, none=0, tail=-1, both = 2
	private final int arrowDirectionB; // head=1, none=0, tail=-1
	private boolean twoWay;

	private List<Block> switchList;
	private List<Block> nextPossible; // list of ALL next possible blocks
	private Block next;
	private Block prev; // previous block train was on

	private boolean blockOccupied;
	private boolean switchEngaged;
	private boolean rrCrossingEngaged;
	private LinkedList<BlockOccupationListener> listeners;
	private TrainModelInterface trainComm = null;

	public Block(String initLine, String initSection, int initBlockNumber, int initBlockLength, float initBlockGrade,
			int initSpeedLimit, float initElevation, float initCumulativeElevation, boolean initSwitchToYard,
			boolean initSwitchFromYard, boolean initInfrastructureSwitch, boolean initInfrastructureUnderground,
			boolean initInfrastructureRRCrossing, boolean initInfrastructureStation, String initStationName,
			int initSwitchBlockId, int initArrowDirectionA, int initArrowDirectionB) {

		this.line = initLine;
		this.section = initSection;
		this.blockNumber = initBlockNumber;
		this.blockLength = initBlockLength;
		this.blockGrade = initBlockGrade;
		this.speedLimit = initSpeedLimit;
		this.elevation = initElevation;
		this.cumulativeElevation = initCumulativeElevation;
		this.switchToYard = initSwitchToYard;
		this.switchFromYard = initSwitchFromYard;
		this.infrastructureSwitch = initInfrastructureSwitch;
		this.infrastructureUnderground = initInfrastructureUnderground;
		this.infrastructureRRCrossing = initInfrastructureRRCrossing;
		this.infrastructureStation = initInfrastructureStation;
		this.stationName = initStationName;
		this.switchBlockId = initSwitchBlockId;
		this.arrowDirectionA = initArrowDirectionA;
		this.arrowDirectionB = initArrowDirectionB;
		this.blockOccupied = false;
		this.switchEngaged = false;
		this.rrCrossingEngaged = false;
		this.twoWay = false;
		this.listeners = new LinkedList<BlockOccupationListener>();
		this.nextPossible = new LinkedList<Block>();
		this.switchList = new LinkedList<Block>();
		if(blockNumber == 0) {
			System.out.println("BLOCK IS 0 THAT IS BAD");
		}
	}

	public String getLine() {
		return line;
	}

	public String getSection() {
		return section;
	}

	public int getBlockNumber() {
		return blockNumber;
	}

	public int getBlockLength() {
		return blockLength;
	}

	public float getBlockGrade() {
		return blockGrade;
	}

	public int getSpeedLimit() {
		return speedLimit;
	}

	public float getElevation() {
		return elevation;
	}

	public float getCumulativeElevation() {
		return cumulativeElevation;
	}

	public boolean isSwitchToYard() {
		return switchToYard;
	}

	public boolean isSwitchFromYard() {
		return switchFromYard;
	}

	public boolean hasSwitch() {
		return infrastructureSwitch;
	}

	public boolean isUnderground() {
		return infrastructureUnderground;
	}

	public boolean hasRRCrossing() {
		return infrastructureRRCrossing;
	}

	public boolean hasStation() {
		return infrastructureStation;
	}

	public String getStationName() {
		return stationName;
	}

	public int getSwitchBlockId() {
		return switchBlockId;
	}

	public int getArrowDirectionA() {
		return arrowDirectionA;
	}

	public String getArrowDirectionAString() {
		switch (arrowDirectionA) {
		case -1: {
			return "Tail";
		}

		case 0: {
			return "None";
		}

		case 1: {
			return "Head";
		}
		default: {
			return null;
		}
		}
	}

	public int getArrowDirectionB() {
		return arrowDirectionB;
	}

	public String getArrowDirectionBString() {
		switch (arrowDirectionB) {
		case -1: {
			return "Tail";
		}

		case 0: {
			return "None";
		}

		case 1: {
			return "Head";
		}
		default: {
			return null;
		}
		}
	}

	public boolean isBlockOccupied() {
		return blockOccupied;
	}

	public void setBlockOccupation(boolean occupied, TrainModelInterface trainComm) {
		if (occupied) {
			this.trainComm = trainComm;
		} else {
			trainComm = null;
		}
		blockOccupied = occupied;
		for (BlockOccupationListener i : listeners) {
			i.blockOccupied(this.blockNumber);
		}
	}

	public boolean isSwitchEngaged() {
		return switchEngaged;
	}

	public void setSwitchEngagement() {
		if (masterSwitch) {
			if (this.switchEngaged) {
				switchEngaged = false;
				for (int i = 0; i < nextPossible.size(); i++) {
					if (nextPossible.get(i).getBlockNumber() == switchList.get(0).getBlockNumber()) {
						nextPossible.remove(i);
					}
				}
				setNextPossible(switchList.get(1));
				switchList.get(1).setNextPossible(this);
				switchList.get(0).removeNextPossible(this);
				switchList.get(0).setSwitchEngagement();
				switchList.get(1).setSwitchEngagement();
			} else {
				switchEngaged = true;
				for (int i = 0; i < nextPossible.size(); i++) {
					if (nextPossible.get(i).getBlockNumber() == switchList.get(1).getBlockNumber()) {
						nextPossible.remove(i);
					}
				}
				setNextPossible(switchList.get(0));
				switchList.get(0).setNextPossible(this);
				switchList.get(1).removeNextPossible(this);
				switchList.get(0).setSwitchEngagement();
				switchList.get(1).setSwitchEngagement();
			}
		} else {
			if (this.switchEngaged) {
				switchEngaged = false;
			} else {
				switchEngaged = true;
			}
		}
	}

	public void setNextPossible(Block newBlock) {
		boolean add = true;
		for (int i = 0; i < nextPossible.size(); i++) {
			if (nextPossible.get(i).getBlockNumber() == newBlock.getBlockNumber())
				add = false;
		}
		if (add)
			nextPossible.add(newBlock);
	}

	public void removeNextPossible(Block newBlock) {
		for (int i = 0; i < nextPossible.size(); i++) {
			if (nextPossible.get(i).getBlockNumber() == newBlock.getBlockNumber())
				nextPossible.remove(i);
		}
	}

	public void setSwitchList(Block newBlock) {
		boolean add = true;
		for (int i = 0; i < switchList.size(); i++) {
			if (switchList.get(i).getBlockNumber() == newBlock.getBlockNumber())
				add = false;
		}
		if (add)
			switchList.add(newBlock);
	}

	public List<Block> getNextPossible() {
		return nextPossible;
	}

	public List<Block> getSwitchList() {
		return switchList;
	}

	public void setPrev(Block newBlock) {
		this.prev = newBlock;
	}

	public void unsetPrev() {
		this.prev = null;
	}

	public void setTwoWay(boolean twoWayIn) {
		this.twoWay = twoWayIn;
	}

	public boolean getTwoWay() {
		return twoWay;
	}

	public void setMasterSwitch(boolean input) {
		this.masterSwitch = input;
	}

	public boolean getMasterSwitch() {
		return this.masterSwitch;
	}

	public Block getPrevBlock() {
		return this.prev;
	}

	public void setNext() {
		for (int i = 0; i < nextPossible.size(); i++) {
			if (nextPossible.get(i) != prev) {
				next = nextPossible.get(i);
			}
		}
	}

	public Block getNext() {
		return next;
	}

	public void setNext(Block next) {
		this.next = next;
	}

	public boolean isRRCrossingEngaged() {
		return rrCrossingEngaged;
	}

	public void setRRCrossingEngagement(boolean engaged) {
		rrCrossingEngaged = engaged;
	}

	public void getTwoWay(boolean twoWayIn) {
		this.twoWay = twoWayIn;
	}

	public void addListener(BlockOccupationListener newListener) {
		listeners.add(newListener);
	}

	public void setSpeedAuthority(int speed, int authority) {
		System.out.println("Block Number: " + blockNumber + ", Speed: " + speed +", Authority: " + authority);
		if (blockNumber == 1) {
			System.out.println("new train");
			trainComm = new TrainModel();
			trainComm.setBlock(this);
			trainComm.run();
		}
		if (trainComm != null) {
			this.trainComm.setSpeedAndAuth(speed, authority);
		}
	}
}
