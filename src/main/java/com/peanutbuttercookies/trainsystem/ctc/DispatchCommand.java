package com.peanutbuttercookies.trainsystem.ctc;

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;

public class DispatchCommand implements Command {
	
	private int rec;
	private int authority;
	private int speed;
	
	public DispatchCommand(int rec, int authority, int speed ) {
		setRec(rec);
		setAuthority(authority);
		setSpeed(speed);
	}
	
	public int getRec() {
		return rec;
	}
	public void setRec(int rec) {
		this.rec = rec;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	@Override
	public String toString() {
		return "Block " + rec + " to receive " + speed + " speed and " + authority + " authority";
	}

	@Override
	public boolean send(CTCModuleInterface ctc, String line) {
		ctc.dispatch(line, speed, rec, authority);
		return false;
	}
}
