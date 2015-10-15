package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.util.Vector;

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;

public class TrackControllerModule implements TrackControllerInterface {
	
	private CTCModuleInterface ctc;
	private TrackModelInterface trackModel;
	
	private Vector<TC_Train> trainList=new Vector<TC_Train>();
	
	@Override
	public boolean setSpeedAuthority(int trainId, int suggestedSpeed, int authority) {
		// TODO Auto-generated method stub
		
		if(trainId>=trainList.size()){
			trainList.addElement(new TC_Train(trainId,authority,suggestedSpeed,0,"Red"));
			trackModel.setAuthority(trainId, authority);
			trackModel.setSpeed(trainId, suggestedSpeed);
		}
		else{
			TC_Train temp =trainList.get(trainId);
			temp.setAuthority(authority);
			temp.setCommandedSpeed(suggestedSpeed);
			
			trackModel.setAuthority(trainId, authority);
			trackModel.setSpeed(trainId, suggestedSpeed);
		}
		return true;
	}

	@Override
	public boolean setTrainPresence(int trainId, int blockNum) {
		// TODO Auto-generated method stub
		if(trainId<trainList.size()){
			trainList.get(trainId).setPresence(blockNum);
			ctc.setBlockOccupied(blockNum);
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

}
