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
	
	private Integer trainId;
	private Integer head;
	private Integer tail;
	private String direction;
	
	public Integer getTrainId() {
		return trainId;
	}
	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}
	public Integer getHead() {
		return head;
	}
	public void setHead(Integer head) {
		this.head = head;
	}
	public Integer getTail() {
		return tail;
	}
	public void setTail(Integer tail) {
		this.tail = tail;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public String toString() {
		return trainId.toString();
	}
}
