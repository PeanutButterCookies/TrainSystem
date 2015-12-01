package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.util.HashMap;
import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.interfaces.BlockOccupationListener;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;

public class TrackController implements BlockOccupationListener {
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
	
	public String getLine(){
		return line;
	}
	
	public int getControllerId(){
		return controllerId;
	}
	
	public int getStartBlock(){
		return startBlock;
	}
	
	public int getEndBlock(){
		return endBlock;
	}
	
	public int getOverlapBlock(){
		return overlapBlock;
	}
	
	public LinkedList getSection(){
		return section;
	}
	
	public Block getBlock(int index){
		int i=index-startBlock;
		if(i>0){
			return section.get(i);
		}
		else
		{
			return null;
		}
	}
	
	public void engageSwitch(){
		
	}
	
	public void setPLCProgram(PLCProgram newPlcProgram){
		plcProgram=newPlcProgram;
	}

	@Override
	public void blockOccupied() {
		// TODO Auto-generated method stub
		
	}
	
	private HashMap setSwitchList(LinkedList<Block> blockSection){
		return new HashMap();
	}

}
