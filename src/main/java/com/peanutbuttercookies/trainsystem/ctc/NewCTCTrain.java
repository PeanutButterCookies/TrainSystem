/*
 * Kevin Nash
 * 10/26/15
 */
package com.peanutbuttercookies.trainsystem.ctc;

public class NewCTCTrain extends CTCTrain {

	public NewCTCTrain() {
		setHead(0);
		setTail(0);
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof NewCTCTrain;
	}
	
	@Override
	public String toString() {
		return "New Train";
	}
}
