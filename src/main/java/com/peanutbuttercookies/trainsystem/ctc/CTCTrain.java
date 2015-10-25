package com.peanutbuttercookies.trainsystem.ctc;

import java.util.Arrays;

public class CTCTrain extends AbstractCTCBean {
	
	static {
		fields = Arrays.asList(new String[]{
				"Train ID",
				"Front Block",
				"Tail Block",
				"Direction"
		});
	}
	
	private int trainId;
	private int head;
	private int tail;
	private String direction;
	
	public int getTrainId() {
		return trainId;
	}
	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}
	public int getHead() {
		return head;
	}
	public void setHead(int head) {
		this.head = head;
	}
	public int getTail() {
		return tail;
	}
	public void setTail(int tail) {
		this.tail = tail;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
}
