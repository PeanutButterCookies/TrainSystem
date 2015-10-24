package com.peanutbuttercookies.trainsystem.traincontroller;

import com.peanutbuttercookies.trainsystem.interfaces.TrainControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrainInterface;
import com.peanutbuttercookies.trainsystem.ui.TrainControllerUI;

public class TrainController implements TrainControllerInterface{
	public double speedLimit;
	public double speed;
	public double acceleration;
	public double mass;
	public int authority;
	public double power;
	public String station;
	public boolean doorsOpen = false;
	TrainInterface train;
	TrainControllerUI ui; 

	public TrainController() {
		// TODO Auto-generated constructor stub
	}
	
	public void setSpeedLimit(double speedLimit){
		System.out.println("Speed Limit " + speedLimit);
		this.speedLimit = speedLimit;
	}
	public void setSpeed(double speed){
		System.out.println("Speed " + speed);
		this.speed = speed;
	}
	public void setAcceleration(double acceleration){
		System.out.println("Aceeleration " + acceleration);
		this.acceleration = acceleration;
	}
	public void setAuthority(int authority){
		this.authority = authority;
	}
	public void setStation(String station){
		this.station = station;
	}
	public void openDoors(){
		doorsOpen = true;
	}
	public void closeDoors(){
		doorsOpen = false;
	}
	public String announceStation(){
		return station;
	}
	
	public void setMass(double mass){
		this.mass = mass;
	}
	
	public double calculatePower(double mass,double velocity,double acceleration){
		power = mass*acceleration*velocity;
		return power;
	}
	
	public double calculateVelocity(double velocity, double acceleration, double time){
		return 0;
	}
	
	public void atStation(){
		openDoors();
		System.out.println(station);
		System.out.println("Open Doors");
	}
	
	public void leaveStation(){
		closeDoors();
		System.out.println("Close doors");
	}

	@Override
	public void setTrainModel(TrainInterface trainModel) {
		this.train = trainModel;
	}
	
	

}
