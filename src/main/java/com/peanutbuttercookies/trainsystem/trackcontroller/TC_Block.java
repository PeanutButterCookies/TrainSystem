package com.peanutbuttercookies.trainsystem.trackcontroller;

/**
 * 
 * @author Chris Good
 *
 */
public class TC_Block {
	
	private final String line;
	private final int waysideControllerId;	//1=1st controller, 2=2nd controller, 3=1st&2nd controller
	private final int blockNumber;
	private final String section;
	private final int blockLength;
	private final boolean trackSwitch;
	private final boolean underground;
	private final boolean station;
	private final String stationName;
	private final int switchBlockId;		//switchBlockId=-1 for block without switch
	private final int arrowHead;			//head=1, neutral=0, tail=-1
	
	private boolean blockOccupied;
	private boolean switchEngaged;
	
	
	public TC_Block(String initLine, int initWaysideControllerId, int initBlockNumber, String initSection, int initBlockLength, 
			boolean initTrackSwitch, boolean initUnderground, boolean initStation, 
			String initStationName, int initSwitchBlockId, int initArrowHead){
		
		line				=initLine;
		waysideControllerId	=initWaysideControllerId;
		blockNumber			=initBlockNumber;
		section				=initSection;
		blockLength			=initBlockLength;
		trackSwitch			=initTrackSwitch;
		underground			=initUnderground;
		station				=initStation;
		stationName			=initStationName;
		switchBlockId		=initSwitchBlockId;
		arrowHead			=initArrowHead;
		blockOccupied		=false;
		switchEngaged		=false;
	}
	
	public String getLine(){
		return line;
	}
	
	public int getWaysideControllerId(){
		return waysideControllerId;
	}
	
	public int getBlockNumber(){
		return blockNumber;
	}
	
	public String getSection(){
		return section;
	}
	
	public int getBlockLength(){
		return blockLength;
	}
	
	public boolean isSwitch(){
		return trackSwitch;
	}
	
	public boolean isUnderground(){
		return underground;
	}
	
	public boolean isStation(){
		return station;
	}
	
	public String getStationName(){
		return stationName;
	}
	
	public int getSwitchBlockId(){
		return switchBlockId;
	}
	
	public int getArrowHead(){
		return arrowHead;
	}
	
	public String getArrowHeadString(){
		switch(arrowHead){
		case -1:{
			return "Tail";
		}
		
		case 0:{
			return "Neutral";
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
