
package com.peanutbuttercookies.trainsystem.traincontroller;



public class SpeedControl {
	double mass;
	double commandSpeed;
	double speed;
	double acceleration;
	double force;
	double power;
	double friction;
	double speedLimit;
	double auth;
	double distance;
	boolean brakes;
	boolean emergencyBrakes;
	double maxAllowedPower;
	boolean approachingStation;
	long arriveTime;
	long endTime;
	private final long DWELL_TIME = 60*1000;
	TrainController control;
	private final double BRAKE_ACCEL = -1.2;
	private final double E_BRAKE_ACCEL= -2.73;
	private final double MAX_SPEED = 19;
	private final double KI = 300;
	private final double KP = 70000;
	private double uk = 0;
	private double uk_prev = 0;
	private double ek = 0;
	private double ek_prev = 0;
	private double maxPower = 120000; //in W
	public SpeedControl(TrainController con) {
		// TODO Auto-generated constructor stub
		control = con;
	}
	
	public double calcPower(double speed){
		//System.out.println(commandSpeed);
		if(commandSpeed > speedLimit){
			commandSpeed = speedLimit;
		}
		
		brakeCheck();
		ek = commandSpeed - speed;
		uk = uk_prev + 1/2 * (ek + ek_prev);
		power = KI*(ek) + KP*(uk);
		if(power > maxPower){
			uk = uk_prev;
			power = KP*(ek) + KI*(uk);
			//power = maxPower;
		}
		//System.out.println("ek: " + ek + " uk: " + uk);
		if(brakes || emergencyBrakes){
			power = 0;
		}
		
		if(brakes && approachingStation && speed==0){//station sequences
			if(arriveTime == 0){
				arriveTime = System.currentTimeMillis();
				control.arriveSequence();
			}
			if(System.currentTimeMillis()-arriveTime >= DWELL_TIME*Clock.getRatio()){
				arriveTime = 0;
				control.departSequence();
			}
		}
		
		if(power < 0){
			power = 0;
		}
		
		ek_prev = ek;
		uk_prev = uk;
		maxAllowedPower = verifyPower(speed);
		if(maxAllowedPower>power){
			control.gui.update();
			//control.train.setPower(power);
			return power;}
		else
			control.gui.update();
			//control.train.setPower(0);
			return maxAllowedPower;
	}
	
	public void brakeCheck(){
		double brakeDistance = Math.pow(speed,2)/(2.0*BRAKE_ACCEL);
		double eBrakeDistance = Math.pow(speed,2)/(2.0*E_BRAKE_ACCEL);
		if(auth-distance <= brakeDistance){
			control.setBrakes(true);
		}
		if(auth-distance <= eBrakeDistance){
			control.setEmergencyBrakes(true);
		}
	}
	
	public double verifyPower(double speed){
		ek = MAX_SPEED - speed;
		uk = uk_prev + .01/2 * (ek + ek_prev);
		power = KI*(ek) + KP*(uk);
		if(power > maxPower){
			uk = uk_prev;
			power = KP*(ek) + KI*(uk);
		}
		
		return power;
	}
}
