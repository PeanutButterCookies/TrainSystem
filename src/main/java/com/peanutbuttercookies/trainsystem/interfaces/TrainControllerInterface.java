package com.peanutbuttercookies.trainsystem.interfaces;

public interface TrainControllerInterface {
	public void setSpeedLimit(double speedLimit);
	public void setAccelerationLimit(double accelerationLimit);
	public void setSpeed(double speed);
	public void setAcceleration(double acceleration);
	public void setAuthority(int authority);
	public void beaconMessage(String message);
	public void openDoors();
	public void closeDoors();
	public String announceStation();
}
