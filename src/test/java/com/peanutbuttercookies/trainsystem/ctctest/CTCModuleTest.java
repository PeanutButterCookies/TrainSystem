package com.peanutbuttercookies.trainsystem.ctctest;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.ctc.CTCModule;

public class CTCModuleTest {

	private static final String LINE = "TestLine";
	private static final String SECTION = "TestSection";
	private static final String STATION = "TestStation";
	private static final int BLOCK_LENGTH = 10;
	private static final int SPEED_LIMIT = 40;

	public void setUp(CTCModule ctc, TestTrackController test) {
		LinkedList<Block> blocks = new LinkedList<Block>();
		Block prev = null;
		Block next = new Block(LINE, true);
		Block curr = null;
		for (int i = 1; i < 30; i++) {
			curr = next;
			curr.setPrev(prev);
			next = initBlock(i);
			curr.setNext(next);
			curr.setNextPossible(next);
			prev = curr;
			blocks.add(curr);
		}
		Block station = new Block(LINE, SECTION, 30, BLOCK_LENGTH, 0, SPEED_LIMIT, 0, 0, false, false, true ,
				false, false, true, STATION, 0, 2);
		curr.setNext(station);
		
		Block a = initBlock(31);
		Block b = initBlock(32);
		station.setNext(a);
		station.setNextPossible(a);
		station.setNextPossible(b);
		
		a.setNext(blocks.get(0));
		b.setNext(blocks.get(20));
		
		blocks.add(a);
		blocks.add(b);
		blocks.add(station);

		Line line = new Line(LINE, blocks);
		test = new TestTrackController(blocks);
		line.setTrackControllers(test, new TestTrackController(new LinkedList<Block>()));
		ctc = new CTCModule();
		ctc.importLine(line);
	}
	
	@Test
	public void dispatchTest() {
		CTCModule ctc = null;
		TestTrackController test = null;
		setUp(ctc, test);
	}
	
	@Test
	public void nullTest() {
		
		CTCModule ctc = new CTCModule();
		boolean success = ctc.importLine(null);
		assertEquals(success, false);
	}

	private static Block initBlock(int blockNum) {
		return new Block(LINE, SECTION, blockNum, BLOCK_LENGTH, 0, SPEED_LIMIT, 0, 0, false, false, false, false, false,
				false, null, -1, 2);

	}
}
