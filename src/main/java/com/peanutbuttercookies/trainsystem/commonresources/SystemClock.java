package com.peanutbuttercookies.trainsystem.commonresources;

public class SystemClock {
	private double ratio;
	
	public SystemClock() {
		ratio = 1;
	}
	
	public double getRatio() {
		return ratio;
	}
	
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	
	public void setRatioBySpeed(int speed) {
		setRatio(1.0/speed);
	}
}
