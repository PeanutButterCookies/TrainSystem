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
	TrainController control;
	private final double MAXSPEED = 19;
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
		ek = commandSpeed - speed;
		uk = uk_prev + .01/2 * (ek + ek_prev);
		power = KI*(ek) + KP*(uk);
		if(power > maxPower){
			uk = uk_prev;
			power = KP*(ek) + KI*(uk);
			//power = maxPower;
		}
		//System.out.println("ek: " + ek + " uk: " + uk);
		
		ek_prev = ek;
		uk_prev = uk;
		if(verifyPower(power,speed)){
			control.gui.update();
			//control.train.setPower(power);

			return power;}
		else
			control.gui.update();
			//control.train.setPower(0);
			return 0;
	}
	
	public boolean verifyPower(double calcPower, double speed){
		ek = speedLimit - speed;
		uk = uk_prev + .01/2 * (ek + ek_prev);
		power = KI*(ek) + KP*(uk);
		if(power > maxPower){
			uk = uk_prev;
			power = KP*(ek) + KI*(uk);
		}
		
		if(calcPower - power>0)
			return false;
		else
			return true;
	}
}
