package com.peanutbuttercookies.trainsystem.train;

public class TrainSpecs {
	private double length = 32.2;
	private double width = 2.65; 
	private double height = 3.42;
	private double emptyMass = 36378.1;
	private int numCars = 1;
	private int maxCapac = 222;
	public int getMaxCapac() {
		return maxCapac;
	}
	public void setMaxCapac(int maxCapac) {
		this.maxCapac = maxCapac;
	}
	public TrainSpecs() {
		// TODO Auto-generated constructor stub
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getEmptyMass() {
		return emptyMass;
	}
	public void setEmptyMass(double emptyMass) {
		this.emptyMass = emptyMass;
	}
	public int getNumCars() {
		return numCars;
	}
	public void setNumCars(int numCars) {
		this.numCars = numCars;
	}

	
}
