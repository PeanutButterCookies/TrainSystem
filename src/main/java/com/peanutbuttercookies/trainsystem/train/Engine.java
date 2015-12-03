package com.peanutbuttercookies.trainsystem.train;

import java.util.concurrent.TimeUnit;


public class Engine {
	public double currentSpeed;
	public double commandedSpeed;
	public double currentAccel;
	public double mass;
	//public double power;
	public double distance;
	public double clockspeed = .1;
	public double grade;
	TrainModel train;
	public boolean brakes = false;
	public boolean emergencyBrakes = false;
	private final double WHEELRADIUS = 270; // mm
	private final double MAXPOWER = 120000; // W
	private final double BRAKEACC = -1.2; // m/s^2
	private final double EBRAKEACC = -2.73; // m/s^2
	private final double GRAVITY = -9.81; // m/s^2
	private final double ROLLINGCOEFFICIENT = .001;
	private final double KINETICCOEFFICIENT = 0.58; // Sliding
	private final double MAXACCELERATION = 2.73;
	public Engine(TrainModel train) {
		// TODO Auto-generated constructor stub
		this.train = train;
	}
	/*public void updateVals(double speed, double accel, double distance){
		train.setcurrentSpeed = speed;
		currentAccel = accel;
		commandedSpeed =setSpeed;
		this.power = power;
		this.distance = distance;
		this.grade = grade;
	}*/
	//public void setVals(double power,double grade)
	
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
				if (power > MAXPOWER)
				{
					power = MAXPOWER;
				}
				
				
				if (emergencyBrakes)
				{
					currentAccel = EBRAKEACC;
				}
				else if (brakes)
				{
					currentAccel = BRAKEACC;
				}

				double oldSpeed = currentSpeed;

				// vf = vi + at;
				currentSpeed += currentAccel*clockspeed;

				
				// If the brakes are on, the train stops at 0
				if ((brakes || emergencyBrakes) && (currentSpeed < 0.05))
				{
					System.out.println("wrong if");
					currentSpeed = 0;
					currentAccel = 0;
				}
				// Else do the power calculation
				else if(!emergencyBrakes && !brakes)
				{
					System.out.println("In else");
					//frictionForce = curBlock.getFrictionCoefficient() * mass * GRAVITY * Math.cos(theta);
					frictionForce = ROLLINGCOEFFICIENT * mass * GRAVITY * Math.cos(theta);
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
				
				
				currentAccel = Math.min(currentAccel, MAXACCELERATION);
				distance += (currentSpeed * clockspeed) + ( (1.0/2.0)*(currentAccel) );

				try {
					TimeUnit.SECONDS.sleep((long) .1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Populate train values and return them
				train.distanceTraveled = this.distance;
				System.out.println("Distance: " + distance);
				if(distance >= train.currentBlock.getBlockLength()){
					distance = distance - train.currentBlock.getBlockLength();
					train.currentBlock.setBlockOccupation(false, train);
					train.setBlock(train.currentBlock.getNext());
					train.currentBlock.setBlockOccupation(true,train);
					train.setSpeedLimits(train.currentBlock.getSpeedLimit());
					train.setAngle(train.currentBlock.getBlockGrade());
				}
				train.controller.setCurrentVelocity(currentSpeed);
				train.gui.updateUI();
				System.out.println("CurrentSpeed: "+currentSpeed);
				//train.controller.calcPower(currentSpeed);

				//return currentSpeed;
				
	}

}
