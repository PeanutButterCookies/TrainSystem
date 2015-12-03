package com.peanutbuttercookies.trainsystem.ctc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Command {
	private static List<String> fields;

	static {
		fields = Arrays.asList(new String[] {
			"Receiving Block",
			"Destination",
			"Speed",
			"Time"
		});
	}
	
	private int rec;
	private int dest;
	private int speed;
	private Date time;
	
	public Command(int rec, int dest, int speed, Date time) {
		setRec(rec);
		setDest(dest);
		setSpeed(speed);
		setTime(time);
	}
	
	public int getRec() {
		return rec;
	}
	public void setRec(int rec) {
		this.rec = rec;
	}
	public int getDest() {
		return dest;
	}
	public void setDest(int dest) {
		this.dest = dest;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	public static int getFieldsCount() {
		return fields.size();
	}
	
	public static String getField(int col) {
		return fields.get(col);
	}
}
