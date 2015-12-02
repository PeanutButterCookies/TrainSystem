package com.peanutbuttercookies.trainsystem.trackcontrollertest;

import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerStaticInterface;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackControllerAuthentication;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackControllerStaticModule;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackControllerUI;

public class TrackControllerSubsystemTest {

	public static void main(String[] args) {
		TrackControllerStaticInterface TCModule = new TrackControllerStaticModule();
		//TrackControllerUI ui = new TrackControllerUI();
		//ui.setVisible(false);
		//TrackControllerAuthentication login = new TrackControllerAuthentication("chris","12345",ui);
		TrackControllerAuthentication login = new TrackControllerAuthentication("chris","12345",new TrackControllerUI());
	}

}
