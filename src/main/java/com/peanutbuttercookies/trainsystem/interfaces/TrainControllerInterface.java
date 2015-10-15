package com.peanutbuttercookies.trainsystem.interfaces;


public interface TrainControllerInterface {
	public void setSpeedLimit(double speedLimit);
	public void setSpeed(double speed);
	public void setAcceleration(double acceleration);
	public void setAuthority(int authority);
	public void setStation(String station);
	public void openDoors();
	public void closeDoors();
	public String announceStation();
	public void setMass(double mass);
	public double calculatePower(double mass,double velocity,double acceleration);
	public double calculateVelocity(double velocity, double acceleration, double time);
	public void atStation();
	public void leaveStation();
}
