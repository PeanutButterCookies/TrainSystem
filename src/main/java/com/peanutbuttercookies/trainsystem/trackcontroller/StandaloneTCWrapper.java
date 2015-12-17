package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.io.IOException;

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;

public class StandaloneTCWrapper {

	public static void main(String[] args) throws IOException{
		
		CTCModuleInterface ctc = new StandaloneCTCModule();
		TrackControllerStaticModule trackController = new TrackControllerStaticModule();
		TrackModelInterface trackModel = new StandaloneTrackLoader();
		new StandaloneUI(ctc,trackController,trackModel);
	}

}
