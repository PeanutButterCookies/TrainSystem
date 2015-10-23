package com.peanutbuttercookies.trainsystem.commonresources;

public class Block {
	private final String line;
	private final String section;
	private final int blockNumber;
	private final int blockLength;
	private final float blockGrade;
	private final int speedLimit;
	private final float elevation;
	private final float cumaltiveElevation;
	private final boolean switchToYard;
	private final boolean switchFromYard;
	private final boolean infrastructureSwitch;
	private final boolean infrastructureUnderground;
	private final boolean infrastructureRRCrossing;
	private final boolean infrastructureStation;
	private final String stationName;
	private final int switchBlockId;		//switchBlockId=-1 for block without switch
	private final int arrowDirectionA;		//head=1, none=0, tail=-1
	private final int arrowDirectionB;		//head=1, none=0, tail=-1
	
	private boolean blockOccupied;
	private boolean switchEngaged;
	
	
	public Block(String initLine, String initSection, int initBlockNumber, int initBlockLength, 
			float initBlockGrade, int initSpeedLimit, float initElevation, float initCumaltiveElevation,
			boolean initSwitchToYard, boolean initSwitchFromYard, boolean initInfrastructureSwitch,
			boolean initInfrastructureUnderground, boolean initInfrastructureRRCrossing,
			boolean initInfrastructureStation,String initStationName, int initSwitchBlockId,
			int initArrowDirectionA, int initArrowDirectionB){
		
		line						=initLine;
		section						=initSection;
		blockNumber					=initBlockNumber;
		blockLength					=initBlockLength;
		blockGrade					=initBlockGrade;
		speedLimit					=initSpeedLimit;
		elevation					=initElevation;
		cumaltiveElevation			=initCumaltiveElevation;
		switchToYard				=initSwitchToYard;
		switchFromYard				=initSwitchFromYard;
		infrastructureSwitch		=initInfrastructureSwitch;
		infrastructureUnderground	=initInfrastructureUnderground;
		infrastructureRRCrossing	=initInfrastructureRRCrossing;
		infrastructureStation		=initInfrastructureStation;
		stationName					=initStationName;
		switchBlockId				=initSwitchBlockId;
		arrowDirectionA				=initArrowDirectionA;
		arrowDirectionB				=initArrowDirectionB;
		blockOccupied				=false;
		switchEngaged				=false;
	}
	
	public String getLine(){
		return line;
	}
	
	public String getSection(){
		return section;
	}
	
	public int getBlockNumber(){
		return blockNumber;
	}
	
	public int getBlockLength(){
		return blockLength;
	}
	
	public float getBlockGrade(){
		return blockGrade;
	}
	
	public int getSpeedLimit(){
		return speedLimit;
	}
	
	public float getElevation(){
		return elevation;
	}
	
	public float getCumaltiveElevation(){
		return cumaltiveElevation;
	}
	
	public boolean isSwitchToYard(){
		return switchToYard;
	}
	
	public boolean isSwitchFromYard(){
		return switchFromYard;
	}
	
	public boolean hasSwitch(){
		return infrastructureSwitch;
	}
	
	public boolean isUnderground(){
		return infrastructureUnderground;
	}
	
	public boolean hasRRCrossing(){
		return infrastructureRRCrossing;
	}
	
	public boolean hasStation(){
		return infrastructureStation;
	}
	
	public String getStationName(){
		return stationName;
	}
	
	public int getSwitchBlockId(){
		return switchBlockId;
	}
	
	public int getArrowDirectionA(){
		return arrowDirectionA;
	}
	
	public String getArrowDirectionAString(){
		switch(arrowDirectionA){
		case -1:{
			return "Tail";
		}
		
		case 0:{
			return "None";
		}
		
		case 1:{
			return "Head";
		}
		default:{
			return null;
		}
		}
	}
	
	public int getArrowDirectionB(){
		return arrowDirectionB;
	}
	
	public String getArrowDirectionBString(){
		switch(arrowDirectionB){
		case -1:{
			return "Tail";
		}
		
		case 0:{
			return "None";
		}
		
		case 1:{
			return "Head";
		}
		default:{
			return null;
		}
		}
	}
	
	public boolean isBlockOccupied(){
		return blockOccupied;
	}
	
	public void setBlockOccupation(boolean occupied){
		blockOccupied=occupied;
	}
	
	public boolean isSwitchEngaged(){
		return switchEngaged;
	}
	
	public void setSwitchEngagement(boolean engaged){
		switchEngaged=engaged;
	}
	
}
