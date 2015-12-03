package com.peanutbuttercookies.trainsystem.train;

import train.test.Block;

public interface TrainModelInterface {
	void setBlock(Block block);
	void setAngle(double angle);
	void brakes();
	void setSpeedLimits(double limit);
	void setLights(String lights);
	void setDoors(boolean doors);
	void setPower(double power);
	void emergencyBrakes();
	void setSpeedAndAuth(double speed, int auth);
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
