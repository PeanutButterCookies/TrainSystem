/*
	 * Kevin Nash
 * 10/15/2015
 */


package com.peanutbuttercookies.trainsystem.systemwrapper;

import java.io.IOException;

import com.peanutbuttercookies.trainsystem.ctc.CTCModule;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackControllerStaticModule;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackControllerUI;
import com.peanutbuttercookies.trainsystem.trackmodel.TrackModel;
import com.peanutbuttercookies.trainsystem.train.TrainModel;
import com.peanutbuttercookies.trainsystem.train.TrainModelInterface;
import com.peanutbuttercookies.trainsystem.ui.CTCModuleUI;
import com.peanutbuttercookies.trainsystem.ui.TrackModelUI;

public class MainApp {
	public static void main(String[] args) throws IOException {
		CTCModuleInterface ctc = new CTCModule();
		TrackControllerStaticModule trackController = new TrackControllerStaticModule();
		TrackModelInterface trackModel = new TrackModel();
		TrainModelInterface trainModel = new TrainModel();
		
		trackController.setCTC(ctc);
		
		CTCModuleUI ctcUI = new CTCModuleUI(ctc);
		TrackModelUI tmUI = new TrackModelUI(trackModel);
		TrackControllerUI tcUI = new TrackControllerUI();

	}
}
