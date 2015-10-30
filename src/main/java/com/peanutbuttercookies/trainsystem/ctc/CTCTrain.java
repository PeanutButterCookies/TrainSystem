package com.peanutbuttercookies.trainsystem.ctc;

import java.util.Arrays;
import java.util.List;

public class CTCTrain extends AbstractCTCBean {
	
	private static List<String> fields;
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
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof CTCTrain)) {
			return false;
		}
		
		return trainId.equals(((CTCTrain)other).getTrainId());
	}
	
	@Override
	public int hashCode() {
		return trainId;
	}
	
	public static int getFieldsSize() {
		return fields.size();
	}
	
	public static String getField(int index) {
		return fields.get(index);
	}
}
