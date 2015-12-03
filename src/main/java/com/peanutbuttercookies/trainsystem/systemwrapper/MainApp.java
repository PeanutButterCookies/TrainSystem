/*
	 * Kevin Nash
 * 10/15/2015
 */


package com.peanutbuttercookies.trainsystem.systemwrapper;

import java.io.IOException;
import java.util.List;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.ctc.CTCModule;
import com.peanutbuttercookies.trainsystem.ctc.CTCModuleUI;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackControllerStaticModule;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackControllerUI;
import com.peanutbuttercookies.trainsystem.trackmodel.TrackModel;
import com.peanutbuttercookies.trainsystem.ui.TrackModelUI;

public class MainApp {
	public static void main(String[] args) throws IOException {
		CTCModuleInterface ctc = new CTCModule();
		TrackControllerStaticModule trackController = new TrackControllerStaticModule();
		TrackModelInterface trackModel = new TrackModel();
		//TODO
		trackModel.fileRead("C:/Users/Kevin/Downloads/ModifiedTrackLayout.xlsx");
		
		trackController.setCTC(ctc);
		List<Line> lines = trackModel.getLines();
		for(Line line : lines) {
			trackController.setTrackControllers(line);
			ctc.importLine(line);
		}
		
		TrackControllerUI tcUI = new TrackControllerUI();
		tcUI.setLines(lines);
		CTCModuleUI ctcUI = new CTCModuleUI(ctc);
		ctc.setUi(ctcUI);
		TrackModelUI tmUI = new TrackModelUI(trackModel);
		tmUI.display(0);

	}
}
