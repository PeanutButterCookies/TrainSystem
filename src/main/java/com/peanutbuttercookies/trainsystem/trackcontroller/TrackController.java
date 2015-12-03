package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.util.HashMap;
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
	private final HashMap switchList;
	private PLCProgram plcProgram;
	
	public TrackController(String initLine, int initControllerId, LinkedList<Block> initSection,
			int initStartBlock, int initEndBlock, int initOverlapBlock, CTCModuleInterface initCtc,
			TrackModelInterface initTrackModel){
		line			=initLine;
		controllerId	=initControllerId;
		section			=initSection;
		startBlock		=initStartBlock;
		endBlock		=initEndBlock;
		overlapBlock	=initOverlapBlock;
		plcProgram		=null;
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
		int i=index-this.startBlock+1;
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
	public void engageSwitch(int switchNum){
		
	}
	
	@Override
	public void setPLCProgram(PLCProgram newPlcProgram){
		this.plcProgram=newPlcProgram;
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
		if(blockId>=this.startBlock && blockId<=this.endBlock && this.startBlock!=this.overlapBlock){
			boolean occupied=section.get(blockId-startBlock+1).isBlockOccupied();
			if(occupied){
				this.ctc.setBlockOccupied(this.line,blockId);
			}
			else{
				this.ctc.setBlockUnoccupied(this.line,blockId);
			}
		}
	}
	
	private HashMap setSwitchList(LinkedList<Block> blockSection){
		return new HashMap();
	}

}
