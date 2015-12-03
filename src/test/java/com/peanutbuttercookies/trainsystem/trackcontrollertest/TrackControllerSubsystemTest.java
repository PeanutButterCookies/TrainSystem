package com.peanutbuttercookies.trainsystem.trackcontrollertest;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerStaticInterface;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackControllerStaticModule;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackControllerUI;

public class TrackControllerSubsystemTest {

	public static final String LINE = "TestLine";
	public static final String SECTION = "TestSection";
	public static final int BLOCK_LENGTH = 10;
	public static final float GRADE = 0;
	public static final int SPEED_LIMIT = 70;
	
	public static void main(String[] args) throws IOException {
		TrackControllerStaticInterface TCModule = new TrackControllerStaticModule();
		TrackControllerUI ui = new TrackControllerUI();
		TCModule.setTrackControllerUI(ui);
		//TrackModelInterface trackModel = new TrackModel();
		
		LinkedList<Block> blocks = new LinkedList<Block>();
		Block prev = null;
		Block next = initBlock(0);
		Block curr = null;
		for(int i=1; i<11; i++) {
			curr = next;
			curr.setPrev(prev);
			next = initBlock(i);
			curr.setNext(next);
			curr.setNextPossible(next);
			prev = curr;
			blocks.add(curr);
		}
		curr.setNext(null);
		
		Line line = new Line(LINE, blocks);
		TCModule.setTrackControllers(line);
		Iterator<TrackControllerInterface> tcIterator=line.getAllTrackControllers().iterator();
		while(tcIterator.hasNext()){
			TrackControllerInterface currTC=tcIterator.next();
			Iterator<Block> blockIterator=currTC.getSection().iterator();
			while(blockIterator.hasNext()){
				Block currBlock=blockIterator.next();
				System.out.println("Controller: "+currTC.getControllerId()+"\tBlock ID: "+currBlock.getBlockNumber());
			}
		}
		
		//TestTrackController tc = new TestTrackController(blocks);
		//line.setTrackControllers(tc, new TestTrackController(new LinkedList<Block>()));
		
		
		
		/*
		trackModel.fileRead("C:\\Users\\Chris\\Documents\\University of Pittsburgh\\Junior Year\\Software Engineering\\testtrack.xlsx");
		Iterator<Line> lineIterator=trackModel.getLines().iterator();
		while(lineIterator.hasNext()){
			Line currLine=lineIterator.next();
			TCModule.setTrackControllers(currLine);
		}
		*/
		
	}
	
	private static Block initBlock(int blockNum) {
		return new Block(LINE, SECTION, blockNum, BLOCK_LENGTH, 0, SPEED_LIMIT, 0, 0, false, false, false, false, false, false, null, -1, 0, 0);

	}

}
