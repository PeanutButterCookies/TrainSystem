package com.peanutbuttercookies.trainsystem.train;

import java.util.concurrent.TimeUnit;

import com.peanutbuttercookies.trainsystem.commonresources.Block;


public class Engine {
	private double currentSpeed = 0;
	private double commandedSpeed = 0;
	private double currentAccel = 0;
	private double mass = 0;
	private double power = 0;
	private double distance = 0;
	private double grade = 0;
	private TrainModel train = null;
	private boolean brakes = false;
	private boolean emergencyBrakes = false;
	private final double MAX_POWER = 120000; // W
	private final double BRAKE_ACC = -1.2; // m/s^2
	private final double E_BRAKE_ACC = -2.73; // m/s^2
	private final double GRAVITY = -9.81; // m/s^2
	private final double ROLLING_COEFFICIENT = .001;
	private final double MAX_ACCELERATION = 2.73;
	
	public Engine(TrainModel train) {
		// TODO Auto-generated constructor stub
		this.train = train;
	}
	
	public void applyPower(double power, double grade, double mass ){
		// Take power and update speed and acceleration.
				double frictionForce = 0;
				double gravityForce = 0;
				double engineForce = 0;

				//double deltaT = 0;

				// Radians
				double theta = Math.atan2(grade, 100);

				//long current = System.currentTimeMillis();

				/*if (lastUpdate == 0)
				{
					deltaT = 0;
				}
				else
				{
					deltaT = (current - lastUpdate) / 1000.0;
				}
				
				lastUpdate = current;*/

				// Limit power
				if (power > MAX_POWER)
				{
					power = MAX_POWER;
				}
				
				
				if (emergencyBrakes)
				{
					currentAccel = E_BRAKE_ACC;
				}
				else if (brakes)
				{
					currentAccel = BRAKE_ACC;
				}

				double oldSpeed = currentSpeed;

				// vf = vi + at;
				currentSpeed += currentAccel*1;

				
				// If the brakes are on, the train stops at 0
				if ((brakes || emergencyBrakes) && (currentSpeed < 0.05))
				{
					currentSpeed = 0;
					currentAccel = 0;
				}
				// Else do the power calculation
				else if(!emergencyBrakes && !brakes)
				{
					//frictionForce = curBlock.getFrictionCoefficient() * mass * GRAVITY * Math.cos(theta);
					frictionForce = ROLLING_COEFFICIENT * mass * GRAVITY * Math.cos(theta);
					gravityForce = mass * GRAVITY * Math.sin(theta);

					engineForce = power / (currentSpeed + 0.00001);
					
					double totalForce = engineForce + gravityForce + frictionForce;

					// Engine overcomes gravity and friction
					if (totalForce > 0)
					{
						currentAccel = totalForce / mass;
					}
					// Engine overcomes gravity, but not friction
					else if (engineForce > Math.abs(gravityForce))
					{
						currentAccel = totalForce / mass;
					}
					// Gravity overcomes both engine and friction and slides backwards
					else if (Math.abs((engineForce - gravityForce)) > Math.abs(frictionForce))
					{
						currentAccel = totalForce / mass;
					}
					else
					{
						currentAccel = 0;
					}
				}
				
				
				currentAccel = Math.min(currentAccel, MAX_ACCELERATION);
				System.out.println("Current Accel: " + currentAccel);
				distance += (currentSpeed) + ( (1.0/2.0)*(currentAccel) );

				try {
					TimeUnit.SECONDS.sleep((long) .1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Populate train values and return them
				train.setDistanceTraveled(this.distance);
				System.out.println("Distance: " + distance);
				if(distance >= train.getCurrentBlock().getBlockLength()){
					System.out.println("\n\n\nSwitching block\n\n\n");
					distance = distance - train.getCurrentBlock().getBlockLength();
					Block old = train.getCurrentBlock();
					train.setBlock(train.getCurrentBlock().getNext());
					train.getCurrentBlock().setTrainOccupation(true,old,train);
					old.setTrainOccupation(false,null, null);
					train.setSpeedLimits(train.getCurrentBlock().getSpeedLimit());
					train.setAngle(train.getCurrentBlock().getBlockGrade());
					train.controller.setBlockId(train.getCurrentBlock().getBlockNumber());
					if(train.getCurrentBlock().hasBeacon()){//to set a beacon
						train.controller.beaconInfo(train.getCurrentBlock().beacon);
					}
				}
				train.controller.setCurrentVelocity(currentSpeed);
				train.gui.updateUI();
				System.out.println("CurrentSpeed: "+currentSpeed);
				//train.controller.calcPower(currentSpeed);

				//return currentSpeed;
				
	}

	public double getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(double currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public double getCommandedSpeed() {
		return commandedSpeed;
	}

	public void setCommandedSpeed(double commandedSpeed) {
		this.commandedSpeed = commandedSpeed;
	}

	public double getCurrentAccel() {
		return currentAccel;
	}

	public void setCurrentAccel(double currentAccel) {
		this.currentAccel = currentAccel;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
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

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public TrainModel getTrain() {
		return train;
	}

	public void setTrain(TrainModel train) {
		this.train = train;
	}

	public boolean isBrakes() {
		return brakes;
	}

	public void setBrakes(boolean brakes) {
		this.brakes = brakes;
	}

	public boolean isEmergencyBrakes() {
		return emergencyBrakes;
	}

	public void setEmergencyBrakes(boolean emergencyBrakes) {
		this.emergencyBrakes = emergencyBrakes;
	}

	public double getMAX_POWER() {
		return MAX_POWER;
	}

	public double getBRAKE_ACC() {
		return BRAKE_ACC;
	}

	public double getE_BRAKE_ACC() {
		return E_BRAKE_ACC;
	}

	public double getGRAVITY() {
		return GRAVITY;
	}

	public double getROLLING_COEFFICIENT() {
		return ROLLING_COEFFICIENT;
	}

	public double getMAX_ACCELERATION() {
		return MAX_ACCELERATION;
	}

}
