
package com.peanutbuttercookies.trainsystem.train;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.traincontroller.TrainController;
import com.peanutbuttercookies.trainsystem.traincontroller.TrainControllerInterface;

public class TrainModel implements TrainModelInterface, Runnable {

	public int id;
	public double distanceTraveled;
	private double currentSpeed = 0;
	private double currentAccleration;
	public boolean currentlySelected = false;
	public Block currentBlock;
	double power;
	private Engine engine = new Engine(this);
	public double auth;
	private TrainSpecs specs = new TrainSpecs();
	public TrainControllerInterface controller;
	private Passengers passengers = new Passengers();
	private boolean doors;
	private double grade;
	private double mass;
	//TrainUI gui = new TrainUI(this);
	//private double power;
	
	public TrainModel() {
		// TODO Auto-generated constructor stub
		//this.id = id;

		setMass();
		
	}
	
	public TrainModel(int id){
		this.id = id;
		setMass();
	}

	public void setId(int id){
		this.id = id;
		controller.setId(id);
	}
	@Override
	public void setAngle(double angle) {
		// TODO Auto-generated method stub
		
		grade = angle;
		engine.grade = angle;
	}

	@Override
	public void setBrakes(boolean brakes) {
		// TODO Auto-generated method stub
		engine.brakes = brakes;
		
	}

	@Override
	public void setSpeedLimits(double limit) {
		// TODO Auto-generated method stub
		limit = limit*.27777778;
		controller.setSpeedLimit(limit);
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
		this.power = power;
		System.out.println("Current: " + currentBlock.getBlockNumber() + ", Auth: " + auth);
		//if(auth == currentBlock.getBlockNumber()){
		//	System.out.println("what");
		//	brakes();
		//} else
			//engine.brakes = false;
		engine.applyPower(power, grade, mass);
	}

	@Override
	public void setEmergencyBrakes(boolean eBrakes) {
		// TODO Auto-generated method stub
		engine.emergencyBrakes = eBrakes;
	}

	@Override
	public void setSpeedAndAuth(double speed, double auth) {
		// TODO Auto-generated method stub
		this.auth = auth;
		controller.setSpeedAndAuth(speed, auth);
	}
	
	public void setMass(){
		mass = specs.getEmptyMass() + passengers.getPassMass(specs.getMaxCapac());
		engine.mass = mass;
	}

	
	public void setBlock(Block block) {
		// TODO Auto-generated method stub
		currentBlock = block;
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
		return power;
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
	
	public void setCurrentlySelected(boolean selected){
		currentlySelected = selected;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		controller.run();
	}

	public TrainControllerInterface getController() {
		return controller;
	}

	public void setController(TrainControllerInterface controller) {
		this.controller = controller;
	}

	
	public String toString(){
		String out = "";
		out += id;
		return out;
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
