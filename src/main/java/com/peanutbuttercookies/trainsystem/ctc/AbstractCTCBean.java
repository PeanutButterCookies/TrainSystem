package com.peanutbuttercookies.trainsystem.ctc;

import java.util.List;

public abstract class AbstractCTCBean {

	protected static List<String> fields;
	
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

	public static int getFieldsSize() {
		return fields.size();
	}
	
	public static String getField(int index) {
		return fields.get(index);
	}
}
