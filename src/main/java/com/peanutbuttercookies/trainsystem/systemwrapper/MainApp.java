/*
 * Kevin Nash
 * 10/15/2015
 */


package com.peanutbuttercookies.trainsystem.systemwrapper;

import java.io.IOException;

import com.peanutbuttercookies.trainsystem.ctc.CTCModule;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrainInterface;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackControllerModule;
import com.peanutbuttercookies.trainsystem.trackmodel.TrackModel;
import com.peanutbuttercookies.trainsystem.trainmodel.TrainModel;
import com.peanutbuttercookies.trainsystem.ui.CTCModuleUI;

public class MainApp {
	public static void main(String[] args) throws IOException {
		CTCModuleInterface ctc = new CTCModule();
		TrackControllerInterface tc = new TrackControllerModule();
//		TrackModelInterface tm = new TrackModel();
//		TrainInterface ti = new TrainModel();
		
		ctc.setTC(tc);
//		tm.setTC(tc);
//		tm.setTI(ti);
		
		CTCModuleUI ctcUI = new CTCModuleUI(ctc);
		
	}
}
