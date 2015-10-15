package com.peanutbuttercookies.trainsystem.interfaces;

public interface TrainInterface {
	public void setSpeed(double speed);
	public void setAcceleration(double acceleration);
	public double getSpeed();
	public double getAcceleration();
	public int setNumPassengers(int numPassengers);
	public int getNumPassengers();
	public double getMass();
	public double getHeight();
	public double getWidth();
	public double getLength();
	public int getNumCars();
	public void pullEmergencyBrake();
	public double getBrakeLimit();
	public double getAccelerationLimit();
	public double getSpeedLimit();
	public double getPower();
}
