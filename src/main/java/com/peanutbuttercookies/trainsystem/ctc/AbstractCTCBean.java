package com.peanutbuttercookies.trainsystem.ctc;

import java.util.List;

public abstract class AbstractCTCBean {

	public static enum Direction {
		POSITIVE {
			public String toString() {
				return "Positive";
			}
		},
		NEGATIVE {
			public String toString() {
				return "Negative";
			}
		}
	}

	
}
