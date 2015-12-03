package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.interfaces.BlockOccupationListener;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerStaticInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;

public class TrackControllerStaticModule implements TrackControllerStaticInterface,BlockOccupationListener {
	private TrackControllerUI ui;
	private CTCModuleInterface ctc;
	private TrackModelInterface trackModel;
	private LinkedList<Line> lines;
	
	public TrackControllerStaticModule() {
		lines = new LinkedList<Line>();
	}

	@Override
	public boolean setTrackControllers(Line line){
		if(line.getAllBlocks().size()>1){
			//alter private global variables
			lines.add(line);
			
			//initialize all local variables
			int divider;
			int offset=0;
			boolean split=false;
			final LinkedList<Block> blocks = line.getAllBlocks();
			LinkedList<Block> section_1 = new LinkedList<Block>();
			LinkedList<Block> section_2 = new LinkedList<Block>();
			
			//sets the dividing point to separate the line into two sections controlled by the two TCs
			divider=blocks.size()/2;
			while(!split){
				if(blocks.get(divider+offset).getSwitchBlockId()==-1 && blocks.get(divider+offset+1).getSwitchBlockId()==-1){
					split=true;
					divider+=offset;
				}
				else if(blocks.get(divider-offset).getSwitchBlockId()==-1 && blocks.get(divider-offset-1).getSwitchBlockId()==-1){
					split=true;
					divider-=(offset+1);
				}
				else{
					offset++;
				}
			}
			
			//Sets up the TC sections
			for(int i=0;i<divider;i++){
				section_1.add(blocks.get(i));
			}
			for(int i=divider;i<blocks.size();i++){
				section_2.add(blocks.get(i));
			}
			
			//Assigns the track controller objects to the line
			line.setTrackControllers(new TrackController(line.getLine(),1,section_1,1,divider,divider,ctc,trackModel),
					new TrackController(line.getLine(),2,section_2,divider,blocks.size(),divider,ctc,trackModel));
			
			return true;
		}
		else{
			return false;
		}		
	}

	@Override
	public void setTrackControllerUI(TrackControllerUI trackControllerUI) {
		this.ui=trackControllerUI;
	}
	
	@Override
	public void blockOccupied(int blockId) {
		this.ui.updateVariableTable();
	}

	@Override
	public void setCTC(CTCModuleInterface initCtc) {
		this.ctc=initCtc;
	}

	@Override
	public void setTrackModel(TrackModelInterface initTrackModel) {
		this.trackModel=initTrackModel;
	}

}
