package com.peanutbuttercookies.trainsystem.train;

import com.peanutbuttercookies.trainsystem.interfaces.TrainInterface;

public class Train implements TrainInterface {

	double width=2.65;
	double length = 32.2;
	double height=3.42;
	double speed;
	double acceleration;
	double power;
	double trainSpeedLimit = 70;
	double serviceBrakeRate = 1.2;
	double speedLimit;
	String station;
	double blockLength;
	int blockId;
	double emergencyBrakeRate = 2.73;
	double mass=56.7*907.185;
	int numCars;
	int numPassengers;
	public Train() {
		// TODO Auto-generated constructor stub
		
	}
	

	public void setSpeed(double speed){
		this.speed=speed;
	}
	
	public void setAcceleration(double acceleration){
		this.acceleration = acceleration;
	}
	public void setSpeedLimit(double speedLimit){
		this.speedLimit = speedLimit;
	}
	public void setStation(String station){
		this.station = station;
	}
	public void setBlockLength(double blockLength){
		this.blockLength = blockLength;
	}
	public void setBlockId(int blockId){
		this.blockId = blockId;
	}
	public double getSpeed(){
		return speed;
	}
	public double getAcceleration(){
		return acceleration;
	}
	public int setNumPassengers(int numPassengers){
		this.numPassengers = numPassengers;
	}
	public int getNumPassengers(){
		return numPassengers;
	}
	public double getMass(){
		return mass;
	}
	public double getHeight(){
		return height;
	}
	public double getWidth(){
		return width;
	}
	public double getLength(){
		return length;
	}
	public int getNumCars(){
		return numCars;
	}
	public void pullEmergencyBrake(){
	}
	public double getBrakeLimit(){
		return serviceBrakeRate;
	}
	public double getSpeedLimit(){
		return speedLimit;	
	}
	public double getPower(){
		return power;
	}
	

}
