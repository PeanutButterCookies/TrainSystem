package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.interfaces.BlockOccupationListener;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;

/**
 * 
 * @author chris
 *
 * This is the main track controller object class. There are 2 TC's per line
 */
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
		overlapBlock	=initOverlapBlock;		//lists block that is shared with other TC
		plcProgramA		=new PLCProgram();
		plcProgramB		=new PLCProgram();
		this.ctc		=initCtc;
		this.trackModel	=initTrackModel;
		switchList		=setSwitchList(initSection);
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
	
	/**
	 * checks PLC logic and current switch engagement, switches engagement if different
	 */
	@Override
	public boolean engageSwitch(String switchName, boolean engagement){
		if(this.switchList.containsKey(switchName)){
			Iterator<Block> switchBlockIterator=this.switchList.get(switchName).iterator();		
			while(switchBlockIterator.hasNext()){
				Block currBlock=switchBlockIterator.next();
				if(currBlock.getMasterSwitch()){
					if(currBlock.isSwitchEngaged()!=engagement && plcProgramA!=null && plcProgramB!=null
							&& plcProgramA.engageSwitch(currBlock) && plcProgramB.engageSwitch(currBlock)){
						currBlock.setSwitchEngagement();
						this.ctc.switchChanged(switchName, currBlock.getBlockNumber(), engagement);
						return true;
					}
				}
			}
			return false;
		}
		else{
			System.err.println("ERROR: THIS TC DOES NOT CONTAIN SWITCH  "+switchName);
			return false;
		}
	}
	
	@Override
	public boolean engageRRCrossing(int blockId, boolean engagement) {
		if(blockId>=startBlock && blockId<=endBlock && section.get(blockId-this.startBlock).hasRRCrossing()
				&& plcProgramA!=null && plcProgramB!=null
				&& plcProgramA.engageRRCrossing(section.get(blockId-startBlock))
				&& plcProgramB.engageRRCrossing(section.get(blockId-startBlock))){
			section.get(blockId-startBlock).setRRCrossingEngagement(engagement);
			return true;
		}
		else{
			System.err.println("ERROR: "+this.line+" line TC#"+this.controllerId+"cannot engage crossing on block #"+blockId);
			return false;
		}
	}
	
	@Override
	public boolean setPLCProgram(String file){
		boolean success=true;
		success&=this.plcProgramA.loadPLCProgram(file);
		success&=this.plcProgramB.loadPLCProgram(file);
		return success;
	}
	
	@Override
	public boolean markBlockForMaintanence(int blockId, boolean needsRepair) {
		if(blockId>=this.startBlock && blockId<=this.endBlock && this.startBlock!=this.overlapBlock
				&& plcProgramA!=null && plcProgramB!=null
				&& plcProgramA.maintenance(section.get(blockId-this.startBlock))
				&& plcProgramB.maintenance(section.get(blockId-this.startBlock))){
			
			section.get(blockId-startBlock).setBlockOccupation(needsRepair);;
			if(needsRepair){
				this.ctc.setBlockOccupied(this.line,blockId);
			}
			else{
				this.ctc.setBlockUnoccupied(this.line,blockId);
			}
			return true;
		}
		return false;
	}
	
	@Override
	public void setSpeedAuthority(int blockId, int speed, int authority){
		if(plcProgramA!=null && plcProgramB!=null){
			if(blockId<=endBlock && blockId>=startBlock){
				if(!(plcProgramA.stop(section.get(blockId-startBlock)) && plcProgramB.stop(section.get(blockId-startBlock)))){
					section.get(blockId-startBlock).setSpeedAuthority(speed,authority);
					if(section.get(blockId-startBlock).getMasterSwitch() || section.get(blockId-startBlock).hasRRCrossing()){
						if(!(plcProgramA.slowDown(section.get(blockId-startBlock))
								&& plcProgramB.slowDown(section.get(blockId-startBlock)))){
							section.get(blockId-startBlock).setLight(1);
						}
						else{
							section.get(blockId-startBlock).setLight(2);
						}
					}
				}
				else{
					System.out.println("SAFETY CRITICAL: TRAIN MUST STOP");
					if(section.get(blockId-startBlock).getMasterSwitch() || section.get(blockId-startBlock).hasRRCrossing()){
							section.get(blockId-startBlock).setLight(3);
						
					}
					section.get(blockId-startBlock).setSpeedAuthority(0,0);
				}
			}
			else{
				System.err.println("ERROR: BLOCKID NOT CONTAINED WITHIN THIS CONTROLLER");
			}
		}
		else{
			System.err.println("ERROR: LOAD PLC PROGRAM");
		}
	}

	@Override
	public void blockOccupied(int blockId) {
		if(blockId>=this.startBlock && blockId<=this.endBlock && this.startBlock!=this.overlapBlock){
			boolean occupied=section.get(blockId-startBlock).isBlockOccupied();
			if(occupied){
				this.ctc.setBlockOccupied(this.line,blockId);
			}
			else{
				this.ctc.setBlockUnoccupied(this.line,blockId);
			}
		}
	}
	
	@Override
	public HashMap<String, LinkedList<Block>> getSwitchList() {
		return this.switchList;
	}
	
	private HashMap<String,LinkedList<Block>> setSwitchList(LinkedList<Block> blockSection){
		
		Iterator<Block> blockIterator = this.section.iterator();
		HashMap<String,LinkedList<Block>> switchMap= new HashMap<String,LinkedList<Block>>();
		
		while(blockIterator.hasNext()){
			Block currBlock = blockIterator.next();
			if(currBlock.getMasterSwitch()){
				LinkedList<Block> blocks=(LinkedList<Block>)currBlock.getSwitchList();
				blocks.addFirst(currBlock);
				switchMap.put(Integer.toString(currBlock.getSwitchNum()), blocks);
			}
		}
		
		return switchMap;
	}
}
