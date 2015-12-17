
package com.peanutbuttercookies.trainsystem.traincontroller;

import com.peanutbuttercookies.trainsystem.train.TrainModelInterface;

public class TrainController implements TrainControllerInterface {

	String station;
	SpeedControl control = new SpeedControl(this);
	TrainModelInterface train;
	//loginInfo login;
	int id;
	double speed;
	double commandSpeed;
	double auth;
	String beacon="";
	int blockId;
	double mass;
	double speedLimit;
	boolean doorsOpen;
	boolean currentlySelected = false;
	double power;
	double distance;
	String lights="";
	boolean brakes = false;
	boolean approachingStation = false;
	boolean emergencyBrakes = false;
	TrainControllerUI gui = new TrainControllerUI();
	public TrainController(TrainModelInterface train) {
		// TODO Auto-generated constructor stub
		this.train = train;
		setSpeedLimit(20);
	}

	@Override
	public void setSpeedAndAuth(double speed, double auth) {
		// TODO Auto-generated method stub
		commandSpeed = speed;
		this.auth = auth;
		System.out.println(auth);
		control.commandSpeed = speed;
		//power = control.calcPower(speed);

	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public SpeedControl getControl() {
		return control;
	}

	public void setControl(SpeedControl control) {
		this.control = control;
	}

	public TrainModelInterface getTrain() {
		return train;
	}

	public void setTrain(TrainModelInterface train) {
		this.train = train;
	}

	public double getCommandSpeed() {
		return commandSpeed;
	}

	public void setCommandSpeed(double commandSpeed) {
		this.commandSpeed = commandSpeed;
	}

	public double getAuth() {
		return auth;
	}

	public void setAuth(double auth) {
		this.auth = auth;
	}

	public String getBeacon() {
		return beacon;
	}

	public void setBeacon(String beacon) {
		this.beacon = beacon;
	}

	public boolean isDoorsOpen() {
		return doorsOpen;
	}

	public void setDoorsOpen(boolean doorsOpen) {
		this.doorsOpen = doorsOpen;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public TrainControllerUI getGui() {
		return gui;
	}

	public void setGui(TrainControllerUI gui) {
		this.gui = gui;
	}

	public double getSpeed() {
		return speed;
	}

	public int getBlockId() {
		return blockId;
	}

	public double getMass() {
		return mass;
	}

	public double getSpeedLimit() {
		return speedLimit;
	}

	public String getLights() {
		return lights;
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
		setApproachingStation(true);
	}


	public void setSpeedLimit(double limit) {
		// TODO Auto-generated method stub
		speedLimit = limit;
		control.speedLimit = limit;
	}

	@Override
	public void setLights(String lights) {
		// TODO Auto-generated method stub
		this.lights = lights;
	}
	
	@Override
	public void setBlockId(int id){
		blockId = id;
		System.out.println("Block: "+blockId);
	}

	public boolean getBrakes() {
		return brakes;
	}

	public void setBrakes(boolean brakes) {
		this.brakes = brakes;
		control.brakes = brakes;
		train.setBrakes(brakes);
	}

	public boolean getEmergencyBrakes() {
		return emergencyBrakes;
	}

	public void setEmergencyBrakes(boolean emergencyBrakes) {
		this.emergencyBrakes = emergencyBrakes;
		control.emergencyBrakes = emergencyBrakes;
		train.setBrakes(emergencyBrakes);
	}
	
	public void arriveSequence(){ //opens doors and resets the number of passengers
		openDoors();
		train.getMass();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public void departSequence(){
		closeDoors();
		setBrakes(false);
		setApproachingStation(false);
		
	}
	
	public void setCurrentlySelected(boolean selected){
		currentlySelected = selected;
	}
	
	public void setApproachingStation(boolean approaching){
		approachingStation = approaching;
		control.approachingStation = approaching;
	}
	
	public String toString(){
		String out = "";
		out += id;
		return out;
	}

	public void run(){
		while(true){
			power = control.calcPower(speed);
			System.out.println("Power: "+power);
			train.setPower(power);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
