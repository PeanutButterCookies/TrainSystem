package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.interfaces.BlockOccupationListener;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerStaticInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;

public class TrackControllerStaticModule implements TrackControllerStaticInterface, BlockOccupationListener {
	private TrackControllerUI ui;
	private CTCModuleInterface ctc;
	private TrackModelInterface trackModel;
	private LinkedList<Line> lines = new LinkedList<Line>();
	private HashMap<String,HashMap<String,LinkedList<Block>>> switchList = new HashMap<String,HashMap<String,LinkedList<Block>>>();
	
	public TrackControllerStaticModule() {
		lines = new LinkedList<Line>();
		
	}

	@Override
	public boolean setTrackControllers(Line line) {
		
		if (line.getAllBlocks().size() > 1) {
			// initialize all local variables
			int divider;
			int offset = 0;
			boolean split = false;
			final LinkedList<Block> blocks = line.getAllBlocks();
			LinkedList<Block> section_1 = new LinkedList<Block>();
			LinkedList<Block> section_2 = new LinkedList<Block>();

			// sets the dividing point to separate the line into two sections
			// controlled by the two TCs
			divider = blocks.size() / 2;
			while (!split) {
				if (divider == offset) {
					System.err.println("ERROR: COULD NOT FIND A VIABLE SPLITTING POINT");
					return false;
				}
				if (blocks.get(divider + offset - 1).getSwitchNum() == -1
						&& blocks.get(divider + offset).getSwitchNum() == -1) {
					split = true;
					divider += offset;
				} else if (blocks.get(divider - offset - 1).getSwitchNum() == -1
						&& blocks.get(divider - offset).getSwitchNum() == -1) {
					split = true;
					divider -= (offset + 1);
				} else {
					offset++;
				}
			}
			
			// Sets up the TC sections
			for (int i = 0; i <= divider; i++) {
				section_1.add(blocks.get(i));
			}
			for (int i = divider; i < blocks.size(); i++) {
				section_2.add(blocks.get(i));
				}
			TrackController tc1 = new TrackController(line.getLine(), 1, section_1, 1, divider, divider, ctc,
					trackModel);
			TrackController tc2 = new TrackController(line.getLine(), 2, section_2, divider, blocks.size(), divider,
					ctc, trackModel);
			
			// Sets up the TC sections
			for (int i = 0; i <= divider; i++) {
				blocks.get(i).addListener(tc1);
				blocks.get(i).addListener(this);
			}
			for (int i = divider; i < blocks.size(); i++) {
				blocks.get(i).addListener(tc2);
				if(i!=divider){
					blocks.get(i).addListener(this);
				}
			}
			
			// Assigns the track controller objects to the line
			line.setTrackControllers(tc1, tc2);
			// alter private global variables
			
			
			lines.add(line);
			this.setupSwitchMap(lines);
			ui.setSwitchList(switchList);
			ui.setLines(lines);
			ui.updateVariableTable();

			return true;
		} else {
			return false;
		}
	}

	@Override
	public void setTrackControllerUI(TrackControllerUI trackControllerUI) {
		this.ui = trackControllerUI;
	}

	@Override
	public void blockOccupied(int blockId) {
		this.ui.updateVariableTable();
	}

	@Override
	public void setCTC(CTCModuleInterface initCtc) {
		this.ctc = initCtc;
	}

	@Override
	public void setTrackModel(TrackModelInterface initTrackModel) {
		this.trackModel = initTrackModel;
	}
	
	private void setupSwitchMap(LinkedList<Line> line){
		Iterator<Line> lineIterator = line.iterator();
		while(lineIterator.hasNext()){
			
			Line currLine=lineIterator.next();
			
			if(!switchList.containsKey(currLine.getLine())){
				HashMap<String,LinkedList<Block>> tcSwitches = new HashMap<String,LinkedList<Block>>();
				
				Iterator<TrackControllerInterface> tcIterator = currLine.getAllTrackControllers().iterator();
				while(tcIterator.hasNext()){
					TrackControllerInterface currTC = tcIterator.next();
					LinkedList<Block> switchBlocks = new LinkedList<Block>();

					Iterator<Block> blockIterator = currTC.getSection().iterator();
					while(blockIterator.hasNext()){
						Block currBlock = blockIterator.next();
						if(currBlock.getMasterSwitch()){
							switchBlocks.add(currBlock);
						}
					}
					
					tcSwitches.put(Integer.toString(currTC.getControllerId()), switchBlocks);
				}
				
				switchList.put(currLine.getLine(), tcSwitches);
			}
		}
		
	}

}
