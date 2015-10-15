package com.peanutbuttercookies.trainsystem.trackmodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrainInterface;
import com.peanutbuttercookies.trainsystem.ui.TrackModelUI;

public class TrackModel implements TrackModelInterface {
	private TrackControllerInterface trackComm;
	private TrainInterface trainComm;
	private ArrayList<Block> track;
	private TrackModelUI newUI;
	
	public TrackModel() {
		fileRead();
	}
	
	
	public void fileRead()	{
		track = new ArrayList<Block>();
		
		try	{
			BufferedReader br = new BufferedReader(new InputStreamReader(TrackModel.class.getResourceAsStream("/trackLayout.txt")));	
			while(br.ready()) {
				String line = br.readLine();
				String delims = "[ ]+";
				String[] tokens = line.split(delims);
				if(!tokens[0].equals("Line"))	{
					setBlock(tokens[0], tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), tokens[5], 0);
				}
			}
		}
		catch(IOException e) {
			System.out.println("Unable to open file.");
		}
	}
	@Override
	public int getSpeed(int trainId) {
		// TODO Auto-generated method stub
		return 0;
		
	}

	@Override
	public int getAuthority(int trainId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBlockOccupied(int blockId, int trainId) {
		track.get(blockId-1).setOccupancy();
		trackComm.setTrainPresence(trainId, blockId);
		//trainComm.setSpeedLimit(track.get(blockId-1).getSpeedLim());
		//trainComm.setStation(track.get(blockId-1).getInfra());
		//trainComm.setBlockId(blockId);
		//trainComm.setBlockLength(track.get(blockId-1).getBlockLen());
	}

	@Override
	public void setBlockUnoccupied(int blockId) {
		for(int i =0; i<track.size(); i++)	{
			if(track.get(i).getBlockId() == blockId)	{
				track.get(i).setOccupancy();
			}
		}
	}

	@Override
	public void setBeacon() {
		//trainComm.setBeaconInfo("1");
	}
	
	@Override
	public void setLayout(Block newBlock) {
		track.add(newBlock);
	}

	@Override
	public void setBlock(String line, String section, int blockId, int blockLen, int speedLim, String infra, int occupancy) {
		// TODO Auto-generated method stub
		//@SuppressWarnings("unused")
		Block newBlock = new Block(line, section, blockId, blockLen, speedLim, infra, occupancy);
		setLayout(newBlock);
	}
	
	@Override
	public void setSpeed(int trainId, int speed)	{
		//trainComm.setSpeed(trainId, speed);
	}
	
	@Override
	public void setAuthority(int trainId, int authority)	{
		//trainComm.setAuthority(trainId, authority);
	}

	@Override
	public int getBeacon(int trainId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setStation(String station) {
		// TODO Auto-generated method stub
		if(!station.equals("none"))	{
			setBeacon();
			//trainComm.getStation(station);
		}
	}



	@Override
	public void setTC(TrackControllerInterface trackComm) {
		this.trackComm = trackComm;
		
	}



	@Override
	public void setTI(TrainInterface trainComm) {
		this.trainComm = trainComm;
	}
	@Override
	public ArrayList<Block> getTrack() {
		return track;
	}
}
