/*
	 * Kevin Nash
 * 10/15/2015
 */


package com.peanutbuttercookies.trainsystem.systemwrapper;

import java.io.IOException;

import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.trackmodel.TrackModel;
import com.peanutbuttercookies.trainsystem.ui.TrackModelUI;

public class MainApp {
	public static void main(String[] args) throws IOException {
		//CTCModuleInterface ctc = new CTCModule();
		//TrackControllerInterface tc = new TrackControllerModule();
		TrackModelInterface tm = new TrackModel();
		//TrainInterface ti = new Train();
		//TrainController trainController = new TrainController();
		
		//ctc.setTC(tc);
		//tc.setCTC(ctc);
		//tc.setTrackModel(tm);
		//tm.setTC(tc);
		//tm.setTI(ti);
		//ti.setTrainController(trainController);
		//ti.setTrackModel(tm);
		//trainController.setTrainModel(ti);
		
		//CTCModuleUI ctcUI = new CTCModuleUI(ctc);
		TrackModelUI tmUI = new TrackModelUI(tm);
		//TrackControllerUI tcUI = new TrackControllerUI(tc);
		//TrainUI tUI = new TrainUI(ti);
		//TrainControllerUI trainControllerUI = new TrainControllerUI(trainController);

	}
}
