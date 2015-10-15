package com.peanutbuttercookies.trainsystem.train;

import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrainControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrainInterface;
import com.peanutbuttercookies.trainsystem.traincontroller.TrainController;

public class Train implements TrainInterface {

	public double width=2.65;
	public double length = 32.2;
	public double height=3.42;
	public double speed;
	public double currentSpeed;
	public double acceleration=.5;
	public double power;
	public double trainSpeedLimit = 70;
	public double serviceBrakeRate = 1.2;
	public double speedLimit;
	public String station;
	public double blockLength;
	public int blockId;
	public int id;
	public double emergencyBrakeRate = 2.73;
	public double mass=56.7*907.185;
	public int numCars;
	public int numPassengers;
	public int authority;
	TrainControllerInterface trainController;
	TrackModelInterface trackModel;
	public Train() {
		// TODO Auto-generated constructor stub
		
	}
	

	public void setSpeed(double speed){
		this.speed=speed;
	}
	public void setCurrentSpeed(double speed){
		this.currentSpeed = speed;
	}
	public double getCurrentSpeed(){
		return currentSpeed;
	}
	public void setAcceleration(double acceleration){
		this.acceleration = acceleration;
	}
	public void setSpeedLimit(double speedLimit){
		trainController.setSpeedLimit(speedLimit);
		this.speedLimit = speedLimit;
	}
	public void setStation(String station){
		trainController.setStation(station);
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
	public void setNumPassengers(int numPassengers){
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
	public void setPower(double mass, double speed, double acceleration){
		power = trainController.calculatePower(mass,speed,acceleration);
	}
	public double getPower(){
		return power;
	}
	public void setAuthority(int authority){
		trainController.setAuthority(authority);
		this.authority = authority;
	}
	public void run(){
		trainController.setAcceleration(0.0);
		trainController.setSpeed(getSpeed());
		while(blockId<=authority){
			trackModel.setBlockOccupied(blockId, id);
			//setSpeed(70*1000/3600);
			double distance = 0;
			while(distance <= blockLength){
				distance+=speed*60;
				setPower(mass,speed,0);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			trackModel.setBlockUnoccupied(blockId);
			blockId++;
		}
		setSpeed(0);
		trainController.openDoors();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		trainController.closeDoors();
	}


	@Override
	public void setTrainController(TrainController tc) {
		this.trainController = tc;
	}


	@Override
	public void setTrackModel(TrackModelInterface tm) {
		trackModel = tm;
	}
	

}
