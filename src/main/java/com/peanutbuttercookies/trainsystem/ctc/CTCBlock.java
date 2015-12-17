/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctc;

import java.util.Arrays;
import java.util.List;

import org.neo4j.graphdb.Node;

public class CTCBlock {
	private static List<String> fields;
	private static double ratio = 1.0;

	static {
		fields = Arrays.asList(new String[] {
			"Block Number",
			"Section",
			"Station",
			"Occupied",
			"Current Switch Block",
			"Throughput",
			"RailRoad"
		});
	}
	
	private int blockNumber;
	private int tc;
	private int length;
	private String section;
	private boolean aSwitch;
	private boolean occupied;
	private int numOccupied;
	private long startTime;
	private String station;
	private int switchNum;
	private Boolean rr = null;
	private boolean hasRR;
	
	public CTCBlock(Node node) {
		setAll(node);
	}
	
	public static void setRatio(double aRatio) {
		ratio = aRatio;
	}
	
	public void setAll(Node node) {
		setBlockNumber((int)node.getProperty("blockNumber"));
		setLength((int)node.getProperty("length"));
		setASwitch((boolean)node.getProperty("aSwitch"));
		setOccupied((boolean)node.getProperty("occupied"));
		setSection((String)node.getProperty("section"));
		setNumOccupied((int)node.getProperty("numOccupied"));
		setTc((int)node.getProperty("tc"));
		setStartTime((long)node.getProperty("starttime"));
		setStation((String)node.getProperty("station"));
		setSwitchNum((int)node.getProperty("switchNum"));
		setHasRR((boolean)node.getProperty("hasRR"));
		if(hasRR) {
			setRR((boolean)node.getProperty("rr"));
		}
	}
	
	
	public Integer getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(Integer blockNumber) {
		this.blockNumber = blockNumber;
	}

	@Override
	public boolean equals(Object other) {
		if(!(other instanceof CTCBlock)) {
			return false;
		}
		
		return blockNumber == ((CTCBlock)other).getBlockNumber();
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
	
	@Override
	public String toString() {
		if(blockNumber == 0) {
			return "Yard";
		}
		return Integer.toString(blockNumber);
	}

	public int getTc() {
		return tc;
	}

	public void setTc(int tc) {
		this.tc = tc;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public boolean isASwitch() {
		return aSwitch;
	}

	public void setASwitch(boolean aSwitch) {
		this.aSwitch = aSwitch;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public int getNumOccupied() {
		return numOccupied;
	}

	public void setNumOccupied(int numOccupied) {
		this.numOccupied = numOccupied;
	}

	public void setBlockNumber(int blockNumber) {
		this.blockNumber = blockNumber;
	}
	
	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public double getThroughput() {
		long current = System.nanoTime();
		long time = current - startTime;
		double dTime = time/ratio;
		dTime = dTime/1000/1000/1000/60/60;
		return numOccupied/dTime;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public int getSwitchNum() {
		return switchNum;
	}

	public void setSwitchNum(int switchNum) {
		this.switchNum = switchNum;
	}

	public Boolean getRR() {
		return rr;
	}

	public void setRR(Boolean rr) {
		this.rr = rr;
	}

	public boolean isHasRR() {
		return hasRR;
	}

	public void setHasRR(boolean hasRR) {
		this.hasRR = hasRR;
	}

}
