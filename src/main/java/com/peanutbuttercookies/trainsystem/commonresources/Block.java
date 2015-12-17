package com.peanutbuttercookies.trainsystem.commonresources;

import java.util.LinkedList;
import java.util.List;

import com.peanutbuttercookies.trainsystem.interfaces.BlockOccupationListener;
import com.peanutbuttercookies.trainsystem.train.TrainModelInterface;
import com.peanutbuttercookies.trainsystem.train.TrainWrapper;

public class Block {
	private static TrainWrapper trainWrap = null;
	private final String line;
	private final String section;
	private final int blockNumber;
	private final double blockLength;
	private final double blockGrade;
	private final double speedLimit;
	private final double elevation;
	private final double cumulativeElevation;
	private final boolean toYard;
	private final boolean fromYard;
	private final boolean infrastructureSwitch;
	private final boolean infrastructureUnderground;
	private final boolean infrastructureRRCrossing;
	private final boolean infrastructureStation;
	private boolean hasBeacon;
	private boolean isYard;
	private boolean masterSwitch;
	private final String stationName;
	private String beacon;
	private final int switchNum; // switchNum=-1 for block without
	private int temp;
	private int light;	//green = 1; yellow = 2; red = 3;
	// switch
	private final int arrowDirection; // head=1, none=0, tail=-1, both = 2
	private boolean twoWay;

	private Block trainNext;
	private Block trainPrev;

	private List<Block> switchList;
	private List<Block> nextPossible; // list of ALL next possible blocks
	private Block next;
	private Block prev; // previous block train was on

	private boolean backwards;
	private boolean blockOccupied;
	private boolean switchEngaged;
	private boolean rrCrossingEngaged;
	private LinkedList<BlockOccupationListener> listeners;
	private TrainModelInterface trainComm = null;
	

	public Block(String initLine, boolean isYard) {
		this(initLine, "", 0, 0, 0, 0, 0, 0, false, false, true, false, false, false, null, -2, 0);
		this.blockOccupied = true;
		this.isYard = true;
		this.setTwoWay(true);
	}

	public Block(String initLine, String initSection, int initBlockNumber, double initBlockLength,
			double initBlockGrade, double initSpeedLimit, double initElevation, double initCumulativeElevation,
			boolean initSwitchToYard, boolean initSwitchFromYard, boolean initInfrastructureSwitch,
			boolean initInfrastructureUnderground, boolean initInfrastructureRRCrossing,
			boolean initInfrastructureStation, String initStationName, int initswitchNum, int initArrowDirection) {

		this.line = initLine;
		this.section = initSection;
		this.blockNumber = initBlockNumber;
		this.blockLength = initBlockLength;
		this.blockGrade = initBlockGrade;
		this.speedLimit = initSpeedLimit;
		this.elevation = initElevation;
		this.cumulativeElevation = initCumulativeElevation;
		this.toYard = initSwitchToYard;
		this.fromYard = initSwitchFromYard;
		this.infrastructureSwitch = initInfrastructureSwitch;
		this.infrastructureUnderground = initInfrastructureUnderground;
		this.infrastructureRRCrossing = initInfrastructureRRCrossing;
		this.infrastructureStation = initInfrastructureStation;
		this.stationName = initStationName;
		this.switchNum = initswitchNum;
		this.arrowDirection = initArrowDirection;
		this.blockOccupied = false;
		this.switchEngaged = false;
		this.rrCrossingEngaged = false;
		this.twoWay = false;
		this.listeners = new LinkedList<BlockOccupationListener>();
		this.nextPossible = new LinkedList<Block>();
		this.switchList = new LinkedList<Block>();
		this.backwards = false;
		this.temp = 70;
		this.hasBeacon = false;
		
		if(infrastructureSwitch || infrastructureRRCrossing)	{
			this.setLight(1);
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

	public double getBlockLength() {
		return blockLength;
	}

	public double getBlockGrade() {
		return blockGrade;
	}

	public double getSpeedLimit() {
		return speedLimit;
	}

	public double getElevation() {
		return elevation;
	}

	public double getCumulativeElevation() {
		return cumulativeElevation;
	}

	public boolean isToYard() {
		return toYard;
	}

	public boolean isFromYard() {
		return fromYard;
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

	public int getSwitchNum() {
		return switchNum;
	}

	public int getArrowDirection() {
		return arrowDirection;
	}

	public String getArrowDirectionString() {
		switch (arrowDirection) {
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

	public void setBlockOccupation(boolean occupied) {
		this.blockOccupied = occupied;
	}

	public boolean isSwitchEngaged() {
		return switchEngaged;
	}

	public void setSwitchEngaged(boolean engaged) {
		this.switchEngaged = engaged;
	}

	public void setSwitchEngagement() {
		if (masterSwitch) {
			if (this.getNext().hasSwitch()) {
				if (switchEngaged) {
					setNext(switchList.get(0));
					switchList.get(0).setSwitchEngaged(true);
					switchList.get(1).setSwitchEngaged(false);
					setSwitchEngaged(false);
				} else {
					setNext(switchList.get(1));
					switchList.get(1).setSwitchEngaged(true);
					switchList.get(0).setSwitchEngaged(false);
					setSwitchEngaged(true);
				}
			}
			if (this.getPrev().hasSwitch()) {
				if (switchEngaged) {
					setPrev(switchList.get(0));
					switchList.get(0).setSwitchEngaged(true);
					switchList.get(1).setSwitchEngaged(false);
					setSwitchEngaged(false);
				} else {
					setPrev(switchList.get(1));
					switchList.get(1).setSwitchEngaged(true);
					switchList.get(0).setSwitchEngaged(false);
					setSwitchEngaged(true);
				}
			}
		} else {
			switchList.get(0).setSwitchEngagement();
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
				System.out.println("Next Block: " + next.getBlockNumber() + " ");
			}
		}
	}

	public Block getNext() {
		return next;
	}

	public void setNext(Block next) {
		this.next = next;
		next.setPrev(this);	
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

	public void setSpeedAuthority(double speed, double authority) {
		System.out.println("Block Number: " + blockNumber + ", Speed: " + speed + ", Authority: " + authority);
		if (blockNumber == 0) {
			trainComm = trainWrap.createTrain(speed, authority, this);
		} else {
			trainComm.setSpeedAndAuth(speed, authority);
		}
	}
	
	public static void setTrainWrapper(TrainWrapper trainWrapper) {
		trainWrap = trainWrapper;
	}
	public void setBackwards() {
		this.backwards = true;
	}

	public boolean getBackwards() {
		return backwards;
	}

	public Block getPrev() {
		return prev;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getTemp() {
		return this.temp;
	}

	public void setIsYard(boolean isYard) {
		this.isYard = isYard;
	}

	public boolean getIsYard() {
		return this.isYard;
	}

	public void setTrainPrev(Block trainPrev) {
		this.trainPrev = trainPrev;
	}

	public void setTrainNext() {
		if(next.getBlockNumber() == 0){
			trainWrap.destroyTrain(trainComm);
		}
		if (next == trainPrev) {
			trainNext = prev;
		} else {
			trainNext = next;
		}
	}

	public void setTrainOccupation(boolean occupied, Block prev, TrainModelInterface trainComm) {
		if (occupied) {
			this.trainComm = trainComm;
			setTrainPrev(prev);
			setTrainNext();
		} else {
			this.trainComm = null;
		}
		blockOccupied = occupied;
		for (BlockOccupationListener i : listeners) {
			System.out.println("Block occupation listener found");
			i.blockOccupied(this.blockNumber);
		}
	}

	public void setBeacon(boolean hasBeacon, String beaconName) {
		this.hasBeacon = hasBeacon;
		this.beacon = beaconName;
	}

	public String getBeacon() {
		return beacon;
	}
	
	public TrainModelInterface getTrainComm()	{
		return this.trainComm;
	}
	
	public void setLight(int signal){
		this.light = signal;
	}
	
	public int getLight(){
		return light;
	}
	
	
}
