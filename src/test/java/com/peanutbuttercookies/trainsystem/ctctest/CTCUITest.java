package com.peanutbuttercookies.trainsystem.ctctest;

import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.ctc.CTCBlock;
import com.peanutbuttercookies.trainsystem.ctc.CTCBlockModel;
import com.peanutbuttercookies.trainsystem.ctc.CTCModule;
import com.peanutbuttercookies.trainsystem.ui.CTCModuleUI;

public class CTCUITest {
	public static final String LINE = "TestLine";
	public static final String SECTION = "TestSection";
	public static final int BLOCK_LENGTH = 10;
	public static final float GRADE = 0;
	public static final int SPEED_LIMIT = 70;
	
	public static void main(String[] args) throws InterruptedException {
		LinkedList<Block> blocks = new LinkedList<Block>();
		Block block1 = initBlock(0);
		Block block2 = initBlock(1);
		Block block3 = initBlock(2);
		block2.setNext(block2);
		block2.setPrev(block1);
		block3.setPrev(block2);
		block2.setNext(block3);
		blocks.add(block1);
		blocks.add(block2);
		blocks.add(block3);
		
		Line line = new Line(LINE, blocks);
		CTCModule ctc = new CTCModule();
		CTCModuleUI ui = new CTCModuleUI(ctc);
		ctc.setUi(ui);
		ctc.importLine(line);
//		line = new Line("Test2", blocks);
//		ctc.importLine(line);
		Thread.sleep(1000);
		ctc.setBlockOccupied(LINE, 0);

		Thread.sleep(1000);
		ctc.setBlockOccupied(LINE, 1);
		ctc.setBlockUnoccupied(LINE, 0);

		Thread.sleep(1000);
		ctc.setBlockOccupied(LINE, 2);
		ctc.setBlockUnoccupied(LINE, 1);

		CTCBlockModel model = ctc.getBlockModel(LINE);
		for(CTCBlock block : model.getBlockMap().values()) {
			System.out.println(block);
		}
	}
	
	private static Block initBlock(int blockNum) {
		return new Block(LINE, SECTION, blockNum, BLOCK_LENGTH, 0, SPEED_LIMIT, 0, 0, false, false, false, false, false, false, null, 0, 0, 0);

	}
}
