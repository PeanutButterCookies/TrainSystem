package com.peanutbuttercookies.trainsystem.interfaces;

import com.peanutbuttercookies.trainsystem.traincontroller.TrainController;

public interface TrainInterface {
	public void setSpeed(double speed);
	public void setAuthority(int authority);
	public void setAcceleration(double acceleration);
	public void setSpeedLimit(double speedLimit);
	public void setStation(String station);
	public void setBlockLength(double blockLength);
	public void setBlockId(int blockId);
	public double getSpeed();
	public double getAcceleration();
	public void setNumPassengers(int numPassengers);
	public int getNumPassengers();
	public double getMass();
	public double getHeight();
	public double getWidth();
	public double getLength();
	public int getNumCars();
	public void pullEmergencyBrake();
	public double getBrakeLimit();
	public double getSpeedLimit();
	public void setPower(double mass, double velocity, double acceleration);
	public double getPower();
	public double getCurrentSpeed();
	public void setTrainController(TrainController tc);
	public void setTrackModel(TrackModelInterface tm);
}
