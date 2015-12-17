package com.peanutbuttercookies.trainsystem.trackmodeltest;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.train.TrainModelInterface;

public class TestTrainModel implements TrainModelInterface {

	private double auth;
	private double speed; 
	
	
	public void setSpeedAndAuth(double speed, double auth){
		this.speed = speed;
		this.auth = auth;
	}
	
	public Double getSpeed()	{
		return this.speed;
	}
	
	public Double getAuth()	{
		return this.auth;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBlock(Block block) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAngle(double angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void brakes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSpeedLimits(double limit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLights(String lights) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDoors(boolean doors) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPower(double power) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void emergencyBrakes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMass() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPower() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getNumPassengers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getNumCars() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCurrentSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAcceleration() {
		// TODO Auto-generated method stub
		return 0;
	}
}
