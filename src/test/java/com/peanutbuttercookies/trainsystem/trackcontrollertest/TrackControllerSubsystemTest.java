package com.peanutbuttercookies.trainsystem.trackcontrollertest;

import java.io.IOException;

import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerStaticInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackControllerStaticModule;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackControllerUI;
import com.peanutbuttercookies.trainsystem.trackmodel.TrackModel;

public class TrackControllerSubsystemTest {

	public static void main(String[] args) throws IOException {
		TrackControllerStaticInterface TCModule = new TrackControllerStaticModule();
		TrackControllerUI ui = new TrackControllerUI();
		TrackModelInterface trackModel = new TrackModel();
		trackModel.fileRead();
	}

}
