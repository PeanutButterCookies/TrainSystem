package com.peanutbuttercookies.trainsystem.ctc;

import java.util.Arrays;

public class CTCTrain extends AbstractCTCBean {
	
	static {
		fields = Arrays.asList(new String[]{
				"Train ID",
				"Block Number"
		});
	}
	
	private int trainId;
	private int block;
}
