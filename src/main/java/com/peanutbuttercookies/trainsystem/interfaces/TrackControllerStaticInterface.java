package com.peanutbuttercookies.trainsystem.interfaces;

import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.ui.TrackControllerUI;

public interface TrackControllerStaticInterface {
	
	/**
	 * This method takes in a line data structure reference and extracts the block data to distribute to two
	 * track controllers. The line reference is stored in the static TC module and passed to the TC UI for it
	 * to reference when updating tables.
	 * @param line - the line data structure reference
	 * @return a boolean value indicating whether the operation was successful
	 */
	public boolean setTrackControllers(Line line);
	
	/**
	 * This method sets a reference to the track controller UI for the static interface to pass the required
	 * information to
	 * @param trackControllerUI
	 */
	public void setTrackControllerUI(TrackControllerUI trackControllerUI);
	
	public void setCTC(CTCModuleInterface initCtc);
	public void setTrackModel(TrackModelInterface initTrackModel);
}
