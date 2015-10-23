package com.peanutbuttercookies.trainsystem.trackcontroller;

import com.peanutbuttercookies.trainsystem.commonresources.Block;

/**
 * 
 * @author Chris Good
 *
 */
public class TC_Block extends Block{
	//super(Block);
	
	private final int waysideControllerId;	//1=1st controller, 2=2nd controller, 3=1st&2nd controller
	
	public TC_Block(String initLine, String initSection, int initBlockNumber, int initBlockLength, float initBlockGrade,
			int initSpeedLimit, float initElevation, float initCumaltiveElevation, boolean initSwitchToYard,
			boolean initSwitchFromYard, boolean initInfrastructureSwitch, boolean initInfrastructureUnderground,
			boolean initInfrastructureRRCrossing, boolean initInfrastructureStation, String initStationName,
			int initSwitchBlockId, int initArrowDirectionA, int initArrowDirectionB,int initWaysideControllerId) {
		
		super(initLine, initSection, initBlockNumber, initBlockLength, initBlockGrade, initSpeedLimit, initElevation,
				initCumaltiveElevation, initSwitchToYard, initSwitchFromYard, initInfrastructureSwitch,
				initInfrastructureUnderground, initInfrastructureRRCrossing, initInfrastructureStation, initStationName,
				initSwitchBlockId, initArrowDirectionA, initArrowDirectionB);
		waysideControllerId			=initWaysideControllerId;
	}	
	
	public int getWaysideControllerId(){
		return waysideControllerId;
	}
}
