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
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackControllerStaticModule;
import com.peanutbuttercookies.trainsystem.trackcontroller.TrackControllerUI;
import com.peanutbuttercookies.trainsystem.trackmodel.TrackModel;
import com.peanutbuttercookies.trainsystem.trackmodel.TrackModelUI;
import com.peanutbuttercookies.trainsystem.train.TrainWrapper;

public class MainApp {
	public static void main(String[] args) throws IOException {
		CTCModuleInterface ctc = new CTCModule();
		TrackControllerStaticModule trackController = new TrackControllerStaticModule();
		TrackModelInterface trackModel = new TrackModel();
		//TODO

		//trackModel.fileRead("C:/Users/Fauzul/Documents/COE1186/trackTest.xlsx");

		//trackModel.fileRead("C:/Users/Kevin/Downloads/Track Layout & Vehicle Data vF1.xlsx");
		//trackModel.fileRead("C:/Users/Chris/Documents/University of Pittsburgh/Junior Year/Software Engineering/ModifiedTrackLayout.xlsx");
		trackModel.fileRead("C:/Users/Chris/Documents/University of Pittsburgh/Junior Year/Software Engineering/Track Layout & Vehicle Data vF1.xlsx");

		
		trackController.setCTC(ctc);
		TrackControllerUI tcUI = new TrackControllerUI();
		trackController.setTrackControllerUI(tcUI);
		List<Line> lines = trackModel.getTrack();
		for(Line line : lines) {
			trackController.setTrackControllers(line);
			ctc.importLine(line);
		}

		

		tcUI.setLines(lines);
		Block.setTrainWrapper(new TrainWrapper());
		CTCModuleUI ctcUI = new CTCModuleUI(ctc);
		ctc.setUi(ctcUI);
		TrackModelUI tmUI = new TrackModelUI(trackModel);



	}
}
