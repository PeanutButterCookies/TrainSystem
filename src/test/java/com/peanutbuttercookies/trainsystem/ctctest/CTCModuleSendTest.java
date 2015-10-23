/*
 * Kevin Nash
 * 10/15/2015
 */


package com.peanutbuttercookies.trainsystem.ctctest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.peanutbuttercookies.trainsystem.ctc.CTCModule;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;

@RunWith(value = Parameterized.class)
public class CTCModuleSendTest {

	private static TrackControllerInterface tc;
	private static CTCModuleInterface ctc;

	private boolean expected;
	private String speed;
	private int authority;
	private int trainId;

	public CTCModuleSendTest(String speed,  int trainId, int authority, boolean expected) {
		this.expected = expected;
		this.speed = speed;
		this.authority = authority;
		this.trainId = trainId;
	}

	@Before
	public void setup() throws IOException {
		tc = new TestTrackControllerInterface();
		ctc = new CTCModule();
		ctc.setTC(tc);
	
	}

	@Parameters(name = "{index}: send({0}, {1}, {2})={3}")
	public static Iterable<Object[]> data() {
		
		return Arrays.asList(new Object[][] {
			{"70", 1, 5, false},
			{"agsdgf", 0, 2, false },
			{"70", 0, 4, true },
			{"70", 5, 6, false}
		});
	}

	@Test
	public void test() {
		assertEquals(expected,ctc.send(speed, trainId, authority));
	}

}
