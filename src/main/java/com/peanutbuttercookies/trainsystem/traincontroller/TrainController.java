package com.peanutbuttercookies.trainsystem.traincontroller;

import com.peanutbuttercookies.trainsystem.train.TrainModelInterface;
import com.peanutbuttercookies.trainsystem.traincontroller.SpeedControl;
import com.peanutbuttercookies.trainsystem.traincontroller.TrainControllerInterface;
import com.peanutbuttercookies.trainsystem.traincontroller.TrainControllerUI;

public class TrainController implements TrainControllerInterface {

	private String station;
	private SpeedControl control = new SpeedControl(this);
	private TrainModelInterface train;
	//loginInfo login;
	private int id;
	private double speed;
	private double commandSpeed;
	private double auth;
	private String beacon="";
	private int blockId;
	private double mass;
	private double speedLimit;
	private boolean doorsOpen;
	private boolean currentlySelected = false;
	private double power;
	private double distance;
	private String lights="";
	private boolean brakes = false;
	private boolean approachingStation = false;
	private boolean emergencyBrakes = false;
	private boolean alive = true;
	private TrainControllerUI gui = null;
	
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
		control.setCommandSpeed(speed);
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
		control.setCommandSpeed(speed);
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
		control.setSpeed(speed);
	}

	@Override
	public void setMass(double mass) {
		// TODO Auto-generated method stub
		this.mass = mass;
	}

	@Override
	public String announceStation() {
		// TODO Auto-generated method stub
		return station;
	}


	public void beaconInfo(String info) {
		// TODO Auto-generated method stub
		beacon = info;
		setStation(beacon);
		setApproachingStation(true);
	}


	public void setSpeedLimit(double limit) {
		// TODO Auto-generated method stub
		speedLimit = limit;
		control.setSpeedLimit(limit);
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
		control.setBrakes(brakes);
		train.setBrakes(brakes);
	}

	public boolean getEmergencyBrakes() {
		return emergencyBrakes;
	}

	public void setEmergencyBrakes(boolean emergencyBrakes) {
		this.emergencyBrakes = emergencyBrakes;
		control.setEmergencyBrakes(emergencyBrakes);
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
		setStation("");
		setBrakes(false);
		setApproachingStation(false);
		
	}
	
	public void setCurrentlySelected(boolean selected){
		currentlySelected = selected;
	}
	
	public void setApproachingStation(boolean approaching){
		approachingStation = approaching;
		control.setApproachingStation(approaching);
	}
	
	public void setAlive(boolean alive){
		this.alive = alive;
	}
	
	public boolean isCurrentlySelected() {
		return currentlySelected;
	}

	public boolean isApproachingStation() {
		return approachingStation;
	}

	public boolean isAlive() {
		return alive;
	}

	public String toString(){
		String out = "";
		out += id;
		return out;
	}

	public void run(){
		while(alive){
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
