package com.peanutbuttercookies.trainsystem.ctctest;

import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.ctc.CTCBlock;
import com.peanutbuttercookies.trainsystem.ctc.CTCModule;
import com.peanutbuttercookies.trainsystem.ctc.CTCModuleUI;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackControllerStaticModule;

public class CTCUITest {
	public static final String LINE = "TestLine";
	public static final String SECTION = "TestSection";
	public static final int BLOCK_LENGTH = 10;
	public static final float GRADE = 0;
	public static final int SPEED_LIMIT = 70;
	
	public static void main(String[] args) throws InterruptedException {
		LinkedList<Block> blocks = new LinkedList<Block>();
		Block prev = null;
		Block next = initBlock(0);
		Block curr = null;
		for(int i=1; i<10; i++) {
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
		System.out.println(blocks.size());
		TestTrackController tc = new TestTrackController(blocks);
		TrackControllerStaticModule trackController = new TrackControllerStaticModule();
		trackController.setTrackControllers(line);
		CTCModule ctc = new CTCModule();
		CTCModuleUI ui = new CTCModuleUI(ctc);
		ctc.setUi(ui);
		ctc.importLine(line);
		line = new Line("Test2", blocks);
		ctc.importLine(line);
		ctc.setBlockOccupied(LINE, 0);
		for(int i=1; i<9; i++) {
			Thread.sleep(500);
			ctc.setBlockOccupied(LINE, i);
			Thread.sleep(500);
			ctc.setBlockUnoccupied(LINE, i-1);
		}
		
		
	}
	
	private static Block initBlock(int blockNum) {
		return new Block(LINE, SECTION, blockNum, BLOCK_LENGTH, 0, SPEED_LIMIT, 0, 0, false, false, false, false, false, false, null, 0, 0, 0);

	}
}
