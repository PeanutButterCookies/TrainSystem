/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctctest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.peanutbuttercookies.trainsystem.ctc.CTCBlock;
import com.peanutbuttercookies.trainsystem.ctc.CTCModule;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;

@RunWith(value = Parameterized.class)
public class CTCOccupiedBlockTest {

	private static TrackControllerInterface tc;
	private static CTCModuleInterface ctc;
	private static List<CTCBlock> blocks;
	private static Random rand;

	int blockId;
	boolean expected;

	public CTCOccupiedBlockTest(int blockId, boolean expected) {
		this.blockId = blockId;
		this.expected = expected;
	}

	@Before
	public void setup() throws IOException {
		tc = new TestTrackControllerInterface();
		ctc = new CTCModule();
		ctc.setTC(tc);
		blocks = ctc.getBlocks();
		rand = new Random(System.currentTimeMillis());

	}

	@Parameters(name = "{index}: CTCBlock({0}).isOccupied() = {1} ")
	public static Iterable<Object[]> data() {

		return Arrays.asList(new Object[][] {
			{1,  true},
			{2,  true},
			{3,  true},
			{4,  true},
			{5,  true},
			{6,  true},
		});
	}

	@Test
	public void test() {
		if(rand.nextInt(2) == 0) {
			ctc.setBlockUnoccupied(blockId);
		}
		ctc.setBlockOccupied(blockId);
		CTCBlock block = null;
		for (CTCBlock b : blocks) {
			if (b.getBlockNumber() == blockId) {
				block = b;
				break;
			}
		}

		assertEquals(expected, block.isOccupied());
	}

}
