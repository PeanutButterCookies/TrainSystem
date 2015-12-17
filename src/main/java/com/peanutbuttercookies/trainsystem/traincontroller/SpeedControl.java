/*
* SpeedControl
*
* 1.2, 12/17/15
*
* Autumn Good
*/
package com.peanutbuttercookies.trainsystem.traincontroller;


public class SpeedControl {
	//private double mass = 0;
	private double commandSpeed = 0;
	private double speed = 0;
	//private double acceleration = 0;
	private double power = 0;
	private double speedLimit = 0;
	private double auth = 0;
	private double distance = 0;
	private boolean brakes = false;
	private boolean emergencyBrakes = false;
	private double maxAllowedPower = 0;
	private boolean approachingStation = false;
	private long arriveTime = 0;
	private double uk = 0;
	private double uk_prev = 0;
	private double ek = 0;
	private double ek_prev = 0;
	private double maxPower = 120000; //in W
	private TrainController control = null;
	private final long DWELL_TIME = 60*1000;
	private final double BRAKE_ACCEL = -1.2;
	private final double E_BRAKE_ACCEL= -2.73;
	private final double MAX_SPEED = 19;
	private final double KI = 300;
	private final double KP = 70000;

	
	public SpeedControl(TrainController con) {
		// TODO Auto-generated constructor stub
		control = con;
	}
	
	/**
	 * Calculates the necessary power based on the commanded velocity and brake commands
	 * @param speed The current speed
	 * @return power returns the final power calculation
	 */
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
			if(control.isCurrentlySelected()){
				control.getGui().updateUI();
			}
			return power;
		}
		else
			if(control.isCurrentlySelected()){
				control.getGui().updateUI();
			}
			return maxAllowedPower;
	}
	
	/**
	 * Checks if the train should be braking based on the braking distance and authority
	 */
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
	
	/**
	 * double checks power command by comparing the power command for the commanded velocity and
	 * a power command for the maximum velocity of the train
	 * @param speed current speed
	 * @return power the maximum power it can safely give
	 */
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

	public double getCommandSpeed() {
		return commandSpeed;
	}

	public void setCommandSpeed(double commandSpeed) {
		this.commandSpeed = commandSpeed;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public double getSpeedLimit() {
		return speedLimit;
	}

	public void setSpeedLimit(double speedLimit) {
		this.speedLimit = speedLimit;
	}

	public double getAuth() {
		return auth;
	}

	public void setAuth(double auth) {
		this.auth = auth;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
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

	public double getMaxAllowedPower() {
		return maxAllowedPower;
	}

	public void setMaxAllowedPower(double maxAllowedPower) {
		this.maxAllowedPower = maxAllowedPower;
	}

	public boolean isApproachingStation() {
		return approachingStation;
	}

	public void setApproachingStation(boolean approachingStation) {
		this.approachingStation = approachingStation;
	}

	public long getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(long arriveTime) {
		this.arriveTime = arriveTime;
	}

	public double getUk() {
		return uk;
	}

	public void setUk(double uk) {
		this.uk = uk;
	}

	public double getUk_prev() {
		return uk_prev;
	}

	public void setUk_prev(double uk_prev) {
		this.uk_prev = uk_prev;
	}

	public double getEk() {
		return ek;
	}

	public void setEk(double ek) {
		this.ek = ek;
	}

	public double getEk_prev() {
		return ek_prev;
	}

	public void setEk_prev(double ek_prev) {
		this.ek_prev = ek_prev;
	}

	public double getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(double maxPower) {
		this.maxPower = maxPower;
	}

	public TrainController getControl() {
		return control;
	}

	public void setControl(TrainController control) {
		this.control = control;
	}
}
