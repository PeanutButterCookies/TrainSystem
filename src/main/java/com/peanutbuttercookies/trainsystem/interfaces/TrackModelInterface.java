package com.peanutbuttercookies.trainsystem.interfaces;
/*
*	Fauzul Azim
*	10/14/2015
*/

import java.io.IOException;
import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.trackmodel.TrackModelUI;
import com.peanutbuttercookies.trainsystem.train.TrainModelInterface;

public interface TrackModelInterface {

	/**
	 * This method sets the UI for the Track Model
	 * @param tmUI
	 */
	public void setUI(TrackModelUI tmUI);

	/**
	 * This method allows other modules, or the UI, to get the track
	 * @return LinkedList<Line>
	 */
	public LinkedList<Line> getTrack();
	
	/**
	 * This method reads the Excel file
	 * @param filename
	 * @throws IOException
	 */
	public void fileRead(String filename) throws IOException;

}
