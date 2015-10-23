package com.peanutbuttercookies.trainsystem.trackmodel;

public class Block {
	private String line;
	private String section;
	private int blockId;
	private int blockLen;
	private int speedLim;
	private String infra;
	private int occupancy;
	
	public Block(String lineIn, String sectionIn, int blockIdIn, int blockLenIn, int speedLimIn, String infraIn, int occupancy)	{
		line = lineIn;
		section = sectionIn;
		blockId = blockIdIn;
		blockLen = blockLenIn;
		speedLim = speedLimIn;
		infra = infraIn;
	}
	
	public String getLine()	{
		return line;
	}
	
	public String getSection()	{
		return section;
	}
	
	public int getBlockId()	{
		return blockId;
	}
	
	public int getBlockLen()	{
		return blockLen;
	}
	
	public int getSpeedLim()	{
		return speedLim;
	}
	
	public String getInfra()	{
		return infra;
	}
	
	public int getOccupancy()	{
		return occupancy;
	}
	
	public void setOccupancy()	{
		if(occupancy == 1)
			occupancy = 0;
		else
			occupancy = 1;
	}

}
