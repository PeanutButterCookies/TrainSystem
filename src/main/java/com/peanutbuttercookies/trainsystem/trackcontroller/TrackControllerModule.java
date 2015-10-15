package com.peanutbuttercookies.trainsystem.trackcontroller;

import com.peanutbuttercookies.trainsystem.ui.TrackControllerUI;

import java.util.Vector;

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;

/**
 * 
 * @author Chris Good
 *
 */
public class TrackControllerModule implements TrackControllerInterface {
	
	private CTCModuleInterface ctc;
	private TrackModelInterface trackModel;
	private TrackControllerUI ui;
	
	private Vector<TC_Train> trainList=new Vector<TC_Train>();
	
	@Override
	public boolean setSpeedAuthority(int trainId, int suggestedSpeed, int authority) {
		// TODO Auto-generated method stub
		
		if(trainId>trainList.size()){
			trainList.addElement(new TC_Train(trainId,authority,suggestedSpeed,0,"Red"));
			trackModel.setSpeedAuthority(trainId, suggestedSpeed, authority);
		}
		else{
			TC_Train temp =trainList.get(trainId-1);
			temp.setAuthority(authority);
			temp.setCommandedSpeed(suggestedSpeed);
			
			trackModel.setSpeedAuthority(trainId, suggestedSpeed, authority);
		}
		return true;
	}

	@Override
	public boolean setTrainPresence(int trainId, int blockNum) {
		System.out.println("train id setTrain presence: " + trainId);
		//temp hack
		trainId += 1;
		// temp hack
		if(trainId<trainList.size()){
			trainList.get(trainId-1).setPresence(blockNum);
			ctc.setBlockOccupied(blockNum);
			ui.updateTable();
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public Vector<TC_Train> getLineInfo(String line) {
		// TODO Auto-generated method stub
		
		Vector<TC_Train> lineInfo = new Vector<TC_Train>();
		if(trainList.size()>0){
			for(int i=0; i<trainList.size(); i++){
				TC_Train temp=trainList.get(i);
				if(temp.getLine().equals(line)){
					lineInfo.addElement(temp);
				}
			}
			return lineInfo;
		}
		else{
			return null;
		}
	}

	@Override
	public void setCTC(CTCModuleInterface ctc) {
		this.ctc = ctc;
	}

	@Override
	public void setTrackModel(TrackModelInterface trackModel) {
		this.trackModel = trackModel;
	}

	@Override
	public void setTrackControllerUI(TrackControllerUI trackControllerUI) {
		ui=trackControllerUI;
		
	}

}
