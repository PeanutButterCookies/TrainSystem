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

	public void setUI(TrackModelUI tmUI);

	public LinkedList<Line> getTrack();

	public void fileRead(String filename) throws IOException;

}