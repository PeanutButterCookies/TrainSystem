package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.interfaces.BlockOccupationListener;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;

public class TrackController implements TrackControllerInterface,BlockOccupationListener {
	private final CTCModuleInterface ctc;
	private final TrackModelInterface trackModel;
	
	private final String line;
	private final int controllerId;
	private final LinkedList<Block> section;
	private final int startBlock;
	private final int endBlock;
	private final int overlapBlock;
	private final HashMap<String,LinkedList<Block>> switchList;
	private PLCProgram plcProgramA;
	private PLCProgram plcProgramB;
	
	public TrackController(String initLine, int initControllerId, LinkedList<Block> initSection,
			int initStartBlock, int initEndBlock, int initOverlapBlock, CTCModuleInterface initCtc,
			TrackModelInterface initTrackModel){
		line			=initLine;
		controllerId	=initControllerId;
		section			=initSection;
		startBlock		=initStartBlock;
		endBlock		=initEndBlock;
		overlapBlock	=initOverlapBlock;
		plcProgramA		=new PLCProgram();
		plcProgramB		=new PLCProgram();
		this.ctc		=initCtc;
		this.trackModel	=initTrackModel;
		switchList		=setSwitchList(section);
	}
	
	@Override
	public String getLine(){
		return this.line;
	}
	
	@Override
	public int getControllerId(){
		return this.controllerId;
	}
	
	@Override
	public int getStartBlock(){
		return this.startBlock;
	}
	
	@Override
	public int getEndBlock(){
		return this.endBlock;
	}
	
	@Override
	public int getOverlapBlock(){
		return this.overlapBlock;
	}
	
	@Override
	public LinkedList<Block> getSection(){
		return this.section;
	}
	
	@Override
	public Block getBlock(int index){
		int i=index-this.startBlock;
		if(i>0){
			return this.section.get(i);
		}
		else
		{
			System.err.println("ERROR: THIS TC DOES NOT HANDLE THAT BLOCK");
			return null;
		}
	}
	
	@Override
	public void engageSwitch(String switchName, boolean engagement){
		if(this.switchList.containsKey(switchName)){
			Iterator<Block> switchBlockIterator=this.switchList.get(switchName).iterator();
			while(switchBlockIterator.hasNext()){
				Block currBlock=switchBlockIterator.next();
				if(currBlock.hasSwitch()){
					if(currBlock.isSwitchEngaged()!=engagement){
						currBlock.setSwitchEngagement();
					}
				}
			}	
		}
		else{
			System.err.println("ERROR: THIS TC DOES NOT CONTAIN SWITCH  "+switchName);
		}
	}
	
	@Override
	public void setPLCProgram(String plcProgramFileLocation){
		this.plcProgramA.loadPLCProgram(plcProgramFileLocation, 0);
		this.plcProgramB.loadPLCProgram(plcProgramFileLocation, 1);
	}
	
	@Override
	public void setSpeedAuthority(int blockId, int speed, int authority){
		System.out.println("TrackController block Id: " + blockId);
		System.out.println(section.get(blockId-startBlock).getBlockNumber());
		section.get(blockId-startBlock).setSpeedAuthority(speed,authority);
		if(blockId<=endBlock && blockId>=startBlock){
			section.get(blockId-startBlock).setSpeedAuthority(speed,authority);
		}
		else{
			System.err.println("ERROR: BLOCKID NOT CONTAINED WITHIN THIS CONTROLLER");
		}
	}

	@Override
	public void blockOccupied(int blockId) {
		System.out.println("Block Occupied track controller");
		if(blockId>=this.startBlock && blockId<=this.endBlock && this.startBlock!=this.overlapBlock){
			boolean occupied=section.get(blockId-startBlock).isBlockOccupied();
			if(occupied){
				System.out.println("Setting ctc block occupied");
				this.ctc.setBlockOccupied(this.line,blockId);
			}
			else{
				this.ctc.setBlockUnoccupied(this.line,blockId);
			}
		}
	}
	
	private HashMap<String,LinkedList<Block>> setSwitchList(LinkedList<Block> blockSection){
		Iterator<Block> blockIterator = this.section.iterator();
		HashMap<String,LinkedList<Block>> switchMap= new HashMap<String,LinkedList<Block>>();
		while(blockIterator.hasNext()){
			Block currBlock = blockIterator.next();
			if(currBlock.hasSwitch()){
				switchMap.put(Integer.toString(currBlock.getSwitchBlockId()), (LinkedList<Block>)currBlock.getSwitchList());
			}
		}
		return switchMap;
	}

	@Override
	public HashMap<String, LinkedList<Block>> getSwitchList() {
		return this.switchList;
	}

}
