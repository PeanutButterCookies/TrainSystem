<<<<<<< HEAD
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
	public String station="";
	public boolean doorsOpen = false;
	TrainInterface train;
	TrainControllerUI ui; 

	public TrainController() {
		// TODO Auto-generated constructor stub
	}
	
	public void setSpeedLimit(double speedLimit){
		this.speedLimit = speedLimit;
	}
	public void setSpeed(double speed){
		this.speed = speed;
	}
	public void setAcceleration(double acceleration){
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
		
	}
	
	public void leaveStation(){
		
	}
	
	

}
=======
package com.peanutbuttercookies.trainsystem.traincontroller;

import train.model.TrainModelInterface;

public class TrainController implements TrainControllerInterface {

	String station;
	SpeedControl control;
	TrainModelInterface train;
	//loginInfo login;
	double speed;
	double commandSpeed;
	int auth;
	String beacon;
	double mass;
	double speedLimit;
	boolean doorsOpen;
	double power;
	String lights;
	TrainControllerUI gui;
	public TrainController() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void setSpeedAndAuth(double speed, int auth) {
		// TODO Auto-generated method stub
		commandSpeed = speed;
		this.auth = auth;
		control.commandSpeed = speed;
		power = control.calcPower(speed);


	
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

	public void calcPower(double currentVelocity){
		control.calcPower(currentVelocity);
	}
	@Override
	public void setSpeed(double speed) {
		// TODO Auto-generated method stub
		commandSpeed = speed;
		control.commandSpeed = speed;
	}

	@Override
	public void openDoors() {
		// TODO Auto-generated method stub
		doorsOpen = true;
		train.setDoors(doorsOpen);
	}

	@Override
	public void closeDoors() {
		// TODO Auto-generated method stub
		doorsOpen = false;
		train.setDoors(doorsOpen);
	}

	@Override
	public void setCurrentVelocity(double speed) {
		// TODO Auto-generated method stub
		this.speed = speed;
		control.speed = speed;
	}

	@Override
	public void setMass(double mass) {
		// TODO Auto-generated method stub
		this.mass = mass;
		control.mass = mass;
	}

	@Override
	public String announceStation() {
		// TODO Auto-generated method stub
		return station;
	}



	public void beaconInfo(String info) {
		// TODO Auto-generated method stub
		beacon = info;
	}


	public void setSpeedLimit(double limit) {
		// TODO Auto-generated method stub
		speedLimit = limit;
		control.speedLimit = limit;

	
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
	public void setLights(String lights) {
		// TODO Auto-generated method stub
		this.lights = lights;
	}


	
}
>>>>>>> refs/remotes/origin/master
