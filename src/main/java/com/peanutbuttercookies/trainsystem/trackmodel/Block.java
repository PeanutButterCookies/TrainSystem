<<<<<<< HEAD
=======
<<<<<<< HEAD
package com.peanutbuttercookies.trainsystem.trackmodel;

public class Block {
	private String line;
	private String section;
	private int blockId;
	private int blockLen;
	private double blockGrade;
	private int speedLim;
	private String infra;
	private double elevation;
	private double cumElev;
	private String switchId;
	private String direction;
	private int occupancy;
	
	public Block(String lineIn, String sectionIn, int blockIdIn, int blockLenIn, double blockGradeIn, int speedLimIn, String infraIn, double elevationIn, double cumElevIn, String switchIdIn, String directionIn, int occupancyIn)	{
		this.line = lineIn;
		this.section = sectionIn;
		this.blockId = blockIdIn;
		this.blockLen = blockLenIn;
		this.blockGrade = blockGradeIn;
		this.speedLim = speedLimIn;		
		this.infra = infraIn;
		this.elevation = elevationIn;
		this.cumElev = cumElevIn;
		this.switchId = switchIdIn;
		this.direction = directionIn;
		this.occupancy = occupancyIn;
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
	
	public double getBlockGrade()	{
		return blockGrade;
	}
	
	public int getSpeedLim()	{
		return speedLim;
	}
	
	public String getInfra()	{
		return infra;
	}
	
	public double getElevation()	{
		return elevation;
	}
	
	public double getCumElev()	{
		return cumElev;
	}
	
	public String getSwitchId()	{
		return switchId;
	}
	
	public String getDirection()	{
		return direction;
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
=======
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
=======
>>>>>>> branch 'master' of https://github.com/PeanutButterCookies/TrainSystem
>>>>>>> refs/remotes/origin/master
