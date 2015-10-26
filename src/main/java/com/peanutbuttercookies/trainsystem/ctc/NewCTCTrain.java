package com.peanutbuttercookies.trainsystem.ctc;

public class NewCTCTrain extends CTCTrain {

	@Override
	public boolean equals(Object other) {
		return other instanceof NewCTCTrain;
	}
	
	@Override
	public String toString() {
		return "New Train";
	}
}
