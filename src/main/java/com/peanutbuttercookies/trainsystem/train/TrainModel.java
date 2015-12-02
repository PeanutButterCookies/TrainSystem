package com.peanutbuttercookies.trainsystem.train;

import com.peanutbuttercookies.trainsystem.commonresources.Block;

public class TrainModel implements TrainModelInterface {

	public int id;
	public double distanceTraveled;
	private double currentSpeed;
	private double currentAccleration;
	private Block currentBlock;
	private Engine engine;
	private TrainSpecs specs;
	public TrainControllerInterface controller;
	private Passengers passengers;
	private boolean doors;
	private double grade;
	private double mass;
	TrainUI gui;
	//private double power;
	
	public TrainModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setAngle(double angle) {
		// TODO Auto-generated method stub
		
		grade = angle;
	}

	@Override
	public void brakes() {
		// TODO Auto-generated method stub
		engine.brakes = true;
		
	}

	@Override
	public void setSpeedLimits(double limit) {
		// TODO Auto-generated method stub
		controller.setSpeedLimits(limit);
	}

	@Override
	public void setLights(String lights) {
		// TODO Auto-generated method stub
		controller.setLights(lights);
	}

	@Override
	public void setDoors(boolean doors) {
		// TODO Auto-generated method stub
		this.doors = doors;
	}

	@Override
	public void setPower(double power) {
		// TODO Auto-generated method stub
		engine.applyPower(power, grade, mass);
	}

	@Override
	public void emergencyBrakes() {
		// TODO Auto-generated method stub
		engine.emergencyBrakes = true;
	}

	@Override
	public void setSpeedAndAuth(double speed, int auth) {
		// TODO Auto-generated method stub
		controller.setSpeedAndAuth(speed, auth);
	}
	
	public void setMass(){
		mass = specs.getEmptyMass() + passengers.getPassMass(specs.getMaxCapac());
	}

	@Override
	public void setBlock(Block block) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getLength() {
		// TODO Auto-generated method stub
		return specs.getLength();
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return specs.getWidth();
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return specs.getHeight();
	}

	@Override
	public double getMass() {
		// TODO Auto-generated method stub
		return mass;
	}

	@Override
	public double getPower() {
		// TODO Auto-generated method stub
		return engine.power;
	}

	@Override
	public double getNumPassengers() {
		// TODO Auto-generated method stub
		return passengers.count;
	}

	@Override
	public double getNumCars() {
		// TODO Auto-generated method stub
		return specs.getNumCars();
	}

	@Override
	public double getCurrentSpeed() {
		// TODO Auto-generated method stub
		return engine.currentSpeed;
	}

	@Override
	public double getAcceleration() {
		// TODO Auto-generated method stub
		return engine.currentAccel;
	}

}

class Passengers{
	public int count;
	public double passMass;
	
	public Passengers(){
		
	}
	
	public void generatePassengers(int maxCapac){
		count = (int) (Math.random()*maxCapac);
	}
	
	public double getPassMass(int maxCapac){
		generatePassengers(maxCapac);
		passMass = count*80.7;
		return passMass;
	}
}
