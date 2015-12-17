
package com.peanutbuttercookies.trainsystem.train;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.traincontroller.TrainController;
import com.peanutbuttercookies.trainsystem.traincontroller.TrainControllerInterface;

public class TrainModel implements TrainModelInterface, Runnable {

	public TrainControllerInterface controller = null;
	private int id = 0;
	private double distanceTraveled = 0;
	private double currentSpeed = 0;
	private double currentAccleration = 0;
	private boolean currentlySelected = false;
	private Block currentBlock = null;
	private double power = 0;
	private Engine engine = new Engine(this);
	private double auth = 0;
	private TrainSpecs specs = new TrainSpecs();
	private Passengers passengers = new Passengers();
	private boolean doors = false;
	private double grade = 0;
	private double mass = 0;
	private TrainUI gui = null;
	//private double power;
	
	
	public TrainModel() {
		// TODO Auto-generated constructor stub
		//this.id = id;

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
		engine.setGrade(angle);
	}

	@Override
	public void setBrakes(boolean brakes) {
		// TODO Auto-generated method stub
		engine.setBrakes(brakes);
		
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
		engine.setEmergencyBrakes(eBrakes);
	}

	@Override
	public void setSpeedAndAuth(double speed, double auth) {
		// TODO Auto-generated method stub
		this.auth = auth;
		controller.setSpeedAndAuth(speed, auth);
	}
	
	public void setMass(){
		mass = specs.getEmptyMass() + passengers.getPassMass(specs.getMaxCapac());
		engine.setMass(mass);
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
		return engine.getCurrentSpeed();
	}

	@Override
	public double getAcceleration() {
		// TODO Auto-generated method stub
		return engine.getCurrentAccel();
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
	
	public double getDistanceTraveled() {
		return distanceTraveled;
	}

	public void setDistanceTraveled(double distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
	}

	public double getCurrentAccleration() {
		return currentAccleration;
	}

	public void setCurrentAccleration(double currentAccleration) {
		this.currentAccleration = currentAccleration;
	}

	public Block getCurrentBlock() {
		return currentBlock;
	}

	public void setCurrentBlock(Block currentBlock) {
		this.currentBlock = currentBlock;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public double getAuth() {
		return auth;
	}

	public void setAuth(double auth) {
		this.auth = auth;
	}

	public TrainSpecs getSpecs() {
		return specs;
	}

	public void setSpecs(TrainSpecs specs) {
		this.specs = specs;
	}

	public Passengers getPassengers() {
		return passengers;
	}

	public void setPassengers(Passengers passengers) {
		this.passengers = passengers;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public TrainUI getGui() {
		return gui;
	}

	public void setGui(TrainUI gui) {
		this.gui = gui;
	}

	public int getId() {
		return id;
	}

	public boolean isCurrentlySelected() {
		return currentlySelected;
	}

	public boolean isDoors() {
		return doors;
	}

	public void setCurrentSpeed(double currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public void setMass(double mass) {
		this.mass = mass;
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
