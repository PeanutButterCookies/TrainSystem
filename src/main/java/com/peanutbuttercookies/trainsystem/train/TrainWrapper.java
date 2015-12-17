package com.peanutbuttercookies.trainsystem.train;

import java.util.ArrayList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.traincontroller.TrainController;
import com.peanutbuttercookies.trainsystem.traincontroller.TrainControllerUI;

public class TrainWrapper {
	
	private ArrayList<TrainModel> trains = new ArrayList<TrainModel>();
	private ArrayList<TrainController> controllers = new ArrayList<TrainController>();
	private TrainUI trainUi = new TrainUI();
	private TrainControllerUI controlUi = new TrainControllerUI();

	public TrainWrapper() {
		// TODO Auto-generated constructor stub
	}
	
	public TrainModelInterface createTrain(double speed, double auth, Block block){
		TrainModel train = new TrainModel();
		TrainController trainControl = new TrainController(train);
		Thread thread = new Thread(train);
		thread.setDaemon(true);
		train.setId((int) thread.getId());
		trains.add(train);
		controllers.add(trainControl);
		train.setGui(trainUi);
		trainControl.setGui(controlUi);
		trainUi.addTrainToList(train);
		controlUi.addControllerToList(trainControl);
		train.setSpeedAndAuth(speed,auth);
		train.setBlock(block);
		thread.start();
		return train;
	}

	public void destroyTrain(TrainModelInterface trainComm){
		TrainModel train = (TrainModel)trainComm;
		trainUi.removeTrainFromList(train);
		controlUi.removeControllerFromList((TrainController)train.getController());
		train.controller.setAlive(false);
		trains.remove(train);
		controllers.remove(train.controller);
	}
}
