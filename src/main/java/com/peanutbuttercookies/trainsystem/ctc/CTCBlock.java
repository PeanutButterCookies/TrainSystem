/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;

@NodeEntity(label="Class")
public class CTCBlock {
	private static List<String> fields;

	static {
		fields = Arrays.asList(new String[] {
			"Block Number",
			"Section",
			"Occupied",
			"Switch",
			"Throughput"
		});
	}
	
	private Integer blockNumber;
	private String section;
	private boolean occupied;
	private boolean aSwitch;

	private long starttime;
	private int numTrains;
	private TrackControllerInterface tc;
	
	public TrackControllerInterface getTc() {
		return tc;
	}

	public void setTc(TrackControllerInterface tc) {
		this.tc = tc;
	}

	public CTCBlock() {
		setOccupied(false);
		starttime = System.currentTimeMillis();
	}

	public CTCBlock(int blockNumber, String line, String section) {
		this();
		setBlockNumber(blockNumber);
		setLine(line);
		setSection(section);

	}
	
	public CTCBlock(Block block) {
		this();
		setAll(block);
	}
	
	public void setAll(Block block) {
		setBlockNumber(block.getBlockNumber());
		setLine(block.getLine());
		setSection(block.getSection());
	}
	
	public Integer getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(Integer blockNumber) {
		this.blockNumber = blockNumber;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		if(occupied) {
			numTrains++;
		}
		this.occupied = occupied;
	}


	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	
	//TODO change this to work with scaling simulation time
	public double getThroughput() {
		long time = System.currentTimeMillis();
		long diff = time - starttime;
		double seconds = diff/1000;
		double minutes = seconds/60;
		double hours = minutes/60;
		double throughput = numTrains/hours;
		return throughput;
		
		
	}
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof CTCBlock)) {
			return false;
		}
		
		return blockNumber.equals(((CTCBlock)other).getBlockNumber());
	}
	
	@Override
	public int hashCode() {
		return blockNumber;
	}
	
	public static int getFieldsSize() {
		return fields.size();
	}
	
	public static String getField(int index) {
		return fields.get(index);
	}
	
	public String toString() {
		return blockNumber.toString();
	}

	public boolean isaSwitch() {
		return aSwitch;
	}

	public void setaSwitch(boolean aSwitch) {
		this.aSwitch = aSwitch;
	}
}
