package com.peanutbuttercookies.trainsystem.ctc;

import java.util.List;

public abstract class AbstractCTCBean {

	protected static List<String> fields;

	public static int getFieldsSize() {
		return fields.size();
	}
	
	public static String getField(int index) {
		return fields .get(index);
	}
}
