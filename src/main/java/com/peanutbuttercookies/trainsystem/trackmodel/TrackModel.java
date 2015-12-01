<<<<<<< HEAD
=======
<<<<<<< HEAD
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
	private TrackModelUI tmUI;
	
	public TrackModel() {
		fileRead();
	}
	
	
	public void fileRead()	{
		track = new ArrayList<Block>();
		
		try	{
			BufferedReader br = new BufferedReader(new InputStreamReader(TrackModel.class.getResourceAsStream("/trackLayout2.txt")));	
			while(br.ready()) {
				String line = br.readLine();
				String delims = "[ ]+";
				String[] tokens = line.split(delims);
				System.out.println(line);
				if(!tokens[0].equals("Line"))	{
					setBlock(tokens[0], tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Double.parseDouble(tokens[4]), 
							Integer.parseInt(tokens[5]), tokens[6], Double.parseDouble(tokens[7]), Double.parseDouble(tokens[8]), 
							tokens[9], tokens[10], 0);
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
 		if(tmUI.currentView(blockId))
 			tmUI.display(blockId);
		trackComm.setTrainPresence(trainId, blockId);
		trainComm.setSpeedLimit(track.get(blockId-1).getSpeedLim());
		if(!track.get(blockId-1).getInfra().equals("none"))
			trainComm.setStation(track.get(blockId-1).getInfra());
		trainComm.setBlockId(blockId);
		trainComm.setBlockLength(track.get(blockId-1).getBlockLen());
	}

	@Override
	public void setBlockUnoccupied(int blockId) {
		for(int i =0; i<track.size(); i++)	{
			if(track.get(i).getBlockId() == blockId)	{
				track.get(i).setOccupancy();
				if(tmUI.currentView(blockId))
		 			tmUI.display(blockId);
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
	public void setBlock(String line, String section, int blockId, int blockLen, double blockGrade, int speedLim, String infra, double elevation, double cumElev, String switchId, String direction, int occupancy) {
		Block newBlock = new Block(line, section, blockId, blockLen, blockGrade, speedLim, infra, elevation, cumElev, switchId, direction, occupancy);
		setLayout(newBlock);
	}
	
	@Override
	public void setSpeed(int trainId, int speed)	{
		trainComm.setSpeed((double)speed);
		//trainComm.run();
	}
	
	@Override
	public void setAuthority(int trainId, int authority)	{
		trainComm.setAuthority(authority);
	}

	@Override
	public int getBeacon(int trainId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setStation(String station) {
		if(!station.equals("none"))	{
			setBeacon();
			trainComm.setStation(station);
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


	@Override
	public void setUI(TrackModelUI tmUI) {
		this.tmUI = tmUI;
		
	}
}
=======
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
	private TrackModelUI newUI;
=======
	private TrackModelUI tmUI;
>>>>>>> refs/remotes/origin/master
	
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
<<<<<<< HEAD
		track.get(blockId-1).setOccupancy();
		trackComm.setTrainPresence(trainId, blockId);
		//trainComm.setSpeedLimit(track.get(blockId-1).getSpeedLim());
		//trainComm.setStation(track.get(blockId-1).getInfra());
		//trainComm.setBlockId(blockId);
		//trainComm.setBlockLength(track.get(blockId-1).getBlockLen());
=======
		
		// TEMP HACK
//		blockId += 1;
		// TEMP HACK
		
		System.out.println("setBlockOccupied blockId: " + blockId + " trainId: " + trainId);
		
		track.get(blockId-1).setOccupancy();
 		if(tmUI.currentView(blockId))
 			tmUI.display(blockId);
		trackComm.setTrainPresence(trainId, blockId);
		trainComm.setSpeedLimit(track.get(blockId-1).getSpeedLim());
		if(!track.get(blockId-1).getInfra().equals("none")) {
			trainComm.setStation(track.get(blockId-1).getInfra());
		}
		trainComm.setBlockId(blockId);
		trainComm.setBlockLength(track.get(blockId-1).getBlockLen());
>>>>>>> refs/remotes/origin/master
	}

	@Override
	public void setBlockUnoccupied(int blockId) {
		for(int i =0; i<track.size(); i++)	{
			if(track.get(i).getBlockId() == blockId)	{
				track.get(i).setOccupancy();
<<<<<<< HEAD
=======
				if(tmUI.currentView(blockId))
		 			tmUI.display(blockId);
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
		// TODO Auto-generated method stub
		//@SuppressWarnings("unused")
=======
>>>>>>> refs/remotes/origin/master
		Block newBlock = new Block(line, section, blockId, blockLen, speedLim, infra, occupancy);
		setLayout(newBlock);
	}
	
	@Override
<<<<<<< HEAD
	public void setSpeed(int trainId, int speed)	{
		//trainComm.setSpeed(trainId, speed);
	}
	
	@Override
	public void setAuthority(int trainId, int authority)	{
		//trainComm.setAuthority(trainId, authority);
	}

	@Override
=======
	public void setSpeedAuthority(int trainId, int speed, int authority)	{
		trainComm.setSpeed(speed);
		trainComm.setAuthority(authority);
		trainComm.run();
		System.out.println("Speed Received " + trainId + " " + speed + "  authority: " + authority + " -Track Model");

	}
	
	@Override
>>>>>>> refs/remotes/origin/master
	public int getBeacon(int trainId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setStation(String station) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
		if(!station.equals("none"))	{
			setBeacon();
			//trainComm.getStation(station);
=======
		if(!station.equals("none"))	{
			setBeacon();
			trainComm.setStation(station);
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
}
=======


	@Override
	public void setUI(TrackModelUI tmUI) {
		this.tmUI = tmUI;
		
	}
}
>>>>>>> branch 'master' of https://github.com/PeanutButterCookies/TrainSystem
>>>>>>> refs/remotes/origin/master
