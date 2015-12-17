package com.peanutbuttercookies.trainsystem.trackmodeltest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Test;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.trackmodel.TrackModel;
import com.peanutbuttercookies.trainsystem.train.TrainModelInterface;

public class excelTest {

	private TrackModelInterface trackModel;

	@Test
	public void testSetTrack() throws IOException {
		TrackModelInterface trackModel = new TrackModel();
		trackModel.fileRead("C:/Users/Fauzul/Documents/COE1186/TrackTest.xlsx");
		LinkedList<Line> track = trackModel.getTrack();
		LinkedList<Block> redLine = track.get(0).getAllBlocks();
		LinkedList<Block> greenLine = track.get(1).getAllBlocks();
		for (int i = 0; i < redLine.size() - 1; i++) {
			Block newBlock = redLine.get(i);
			assertEquals(i + 1, newBlock.getBlockNumber());
			assertEquals(true, newBlock.getTwoWay());

			if (i == 0 || i == 5 || i == 8) {
				assertEquals(3, newBlock.getNextPossible().size());
			} else {
				assertEquals(2, newBlock.getNextPossible().size());
			}

		}
		// checks for next of test track
		assertEquals(2, redLine.get(0).getNext().getBlockNumber());
		assertEquals(3, redLine.get(1).getNext().getBlockNumber());
		assertEquals(4, redLine.get(2).getNext().getBlockNumber());
		assertEquals(5, redLine.get(3).getNext().getBlockNumber());
		assertEquals(6, redLine.get(4).getNext().getBlockNumber());
		assertEquals(7, redLine.get(5).getNext().getBlockNumber());
		assertEquals(8, redLine.get(6).getNext().getBlockNumber());
		assertEquals(9, redLine.get(7).getNext().getBlockNumber());
		assertEquals(0, redLine.get(8).getNext().getBlockNumber());

		// checks for prev of test track
		assertEquals(9, redLine.get(0).getPrev().getBlockNumber());
		assertEquals(1, redLine.get(1).getPrev().getBlockNumber());
		assertEquals(2, redLine.get(2).getPrev().getBlockNumber());
		assertEquals(3, redLine.get(3).getPrev().getBlockNumber());
		assertEquals(4, redLine.get(4).getPrev().getBlockNumber());
		assertEquals(1, redLine.get(5).getPrev().getBlockNumber());
		assertEquals(6, redLine.get(6).getPrev().getBlockNumber());
		assertEquals(7, redLine.get(7).getPrev().getBlockNumber());
		assertEquals(0, redLine.get(8).getPrev().getBlockNumber());

		// checks for station and beacon
		assertEquals("SHADYSIDE", redLine.get(6).getStationName());
		assertEquals("SHADYSIDE", redLine.get(6).getPrev().getBeacon());
		assertEquals("SHADYSIDE", redLine.get(6).getNext().getBeacon());

		for (int i = 0; i < greenLine.size() - 1; i++) {
			Block newBlock = greenLine.get(i);
			assertEquals(i + 1, newBlock.getBlockNumber());
			assertEquals(false, newBlock.getTwoWay());
			if (i != 0) {
				assertEquals(1, newBlock.getNextPossible().size());
			}
		}

		assertEquals(0, greenLine.get(0).getNext().getBlockNumber());
		assertEquals(1, greenLine.get(1).getNext().getBlockNumber());
		assertEquals(2, greenLine.get(2).getNext().getBlockNumber());
		assertEquals(3, greenLine.get(3).getNext().getBlockNumber());
		assertEquals(4, greenLine.get(4).getNext().getBlockNumber());
		assertEquals(5, greenLine.get(5).getNext().getBlockNumber());
		assertEquals(6, greenLine.get(6).getNext().getBlockNumber());
		assertEquals(7, greenLine.get(7).getNext().getBlockNumber());
		assertEquals(8, greenLine.get(8).getNext().getBlockNumber());

		assertEquals(2, greenLine.get(0).getPrev().getBlockNumber());
		assertEquals(3, greenLine.get(1).getPrev().getBlockNumber());
		assertEquals(4, greenLine.get(2).getPrev().getBlockNumber());
		assertEquals(5, greenLine.get(3).getPrev().getBlockNumber());
		assertEquals(6, greenLine.get(4).getPrev().getBlockNumber());
		assertEquals(7, greenLine.get(5).getPrev().getBlockNumber());
		assertEquals(8, greenLine.get(6).getPrev().getBlockNumber());
		assertEquals(9, greenLine.get(7).getPrev().getBlockNumber());
		assertEquals(0, greenLine.get(8).getPrev().getBlockNumber());
	}

	@Test
	public void testTemperature() throws IOException {
		TrackModelInterface trackModel = new TrackModel();
		trackModel.fileRead("C:/Users/Fauzul/Documents/COE1186/TrackLayout.xlsx");
		LinkedList<Line> track = trackModel.getTrack();
		LinkedList<Block> redLine = track.get(0).getAllBlocks();
		LinkedList<Block> greenLine = track.get(1).getAllBlocks();
		
		Block newBlock = redLine.get(0);
		for(int i = 0; i<100; i++)	{
			newBlock.setTemp(i);
			assertEquals(i, newBlock.getTemp());
		}
	}
	
	public void testOccupied() throws IOException {
		TrackModelInterface trackModel = new TrackModel();
		trackModel.fileRead("C:/Users/Fauzul/Documents/COE1186/TrackLayout.xlsx");
		LinkedList<Line> track = trackModel.getTrack();
		LinkedList<Block> redLine = track.get(0).getAllBlocks();
		
		for(int i = 0; i<redLine.size(); i++)	{
			Block newBlock = redLine.get(i);
			if(i%2 == 1){
				newBlock.setBlockOccupation(true);
			}
			else	{
				newBlock.setBlockOccupation(false);
			}
		}
		
		for(int i = 0; i<redLine.size(); i++)	{
			Block newBlock = redLine.get(i);
			if(i%2 == 1){
				assertEquals(newBlock.isBlockOccupied(), true);
			}
			else	{
				assertEquals(newBlock.isBlockOccupied(), false);
			}
		}
		
		for(int i = 0; i<redLine.size(); i++)	{
			Block newBlock = redLine.get(i);
			if(i%2 == 0){
				newBlock.setBlockOccupation(true);
			}
			else	{
				newBlock.setBlockOccupation(false);
			}
		}
		
		for(int i = 0; i<redLine.size(); i++)	{
			Block newBlock = redLine.get(i);
			if(i%2 == 1){
				assertEquals(newBlock.isBlockOccupied(), true);
			}
			else	{
				assertEquals(newBlock.isBlockOccupied(), false);
			}
		}
	}
}