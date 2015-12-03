
package com.peanutbuttercookies.trainsystem.traincontroller;

import com.peanutbuttercookies.trainsystem.train.TrainModelInterface;

public class TrainController implements TrainControllerInterface {

	String station;
	SpeedControl control = new SpeedControl(this);
	TrainModelInterface train;
	//loginInfo login;
	double speed;
	double commandSpeed;
	int auth;
	String beacon="";
	int blockId;
	double mass;
	double speedLimit;
	boolean doorsOpen;
	double power;
	String lights="";
	TrainControllerUI gui = new TrainControllerUI(this);
	public TrainController(TrainModelInterface train) {
		// TODO Auto-generated constructor stub
		this.train = train;
		setSpeedLimit(20);
	}

	@Override
	public void setSpeedAndAuth(double speed, int auth) {
		// TODO Auto-generated method stub
		commandSpeed = speed;
		this.auth = auth;
		System.out.println(auth);
		control.commandSpeed = speed;
		//power = control.calcPower(speed);

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
	}

	@Override
	public void setLights(String lights) {
		// TODO Auto-generated method stub
		this.lights = lights;
	}
	
	@Override
	public void setBlockId(int id){
		blockId = id;
	}

	public void run(){
		while(true){
			power = control.calcPower(speed);
			System.out.println(power);
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
