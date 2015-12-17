/*
* TrainModelInterface
*
* 1.2, 12/16/15
*
* Autumn Good
*/
package com.peanutbuttercookies.trainsystem.train;

import com.peanutbuttercookies.trainsystem.commonresources.Block;

public interface TrainModelInterface extends Runnable {
	void setBlock(Block block);
	void setAngle(double angle);
	void setSpeedLimits(double limit);
	void setLights(String lights);
	void setDoors(boolean doors);
	void setPower(double power);
	void setSpeedAndAuth(double speed, double auth);
	void setBrakes(boolean brakes);
	void setEmergencyBrakes(boolean eBrakes);
	void setCurrentlySelected(boolean b);
	double getLength();
	double getWidth();
	double getHeight();
	double getMass();
	double getPower();
	double getNumPassengers();
	double getNumCars();
	double getCurrentSpeed();
	double getAcceleration();
	
}


