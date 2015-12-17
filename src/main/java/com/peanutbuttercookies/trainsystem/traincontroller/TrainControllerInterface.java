package com.peanutbuttercookies.trainsystem.traincontroller;

public interface TrainControllerInterface {
	void setSpeedAndAuth(double speed, double auth);
	void setSpeed(double speed);
	void openDoors();
	void closeDoors();
	void setCurrentVelocity(double speed);
	void setEmergencyBrakes(boolean eBrakes);
	void setBrakes(boolean brakes);
	void setMass(double mass);
	String announceStation();
	void beaconInfo(String info);
	void setSpeedLimit(double limit);
	void calcPower(double currentSpeed);
	void setLights(String lights);
	void run();
	void setBlockId(int id);
	void setId(int id);
	void setAlive(boolean b);

}
