package com.peanutbuttercookies.trainsystem.ctc;

import java.util.Arrays;

public class CTCTrain extends AbstractCTCBean {
	
	static {
		fields = Arrays.asList(new String[]{
				"Train ID",
				"Front Block",
				"Tail Block"
		});
	}
	
	private int trainId;
	private int head;
	private int tail;
	private boolean direction;
}
