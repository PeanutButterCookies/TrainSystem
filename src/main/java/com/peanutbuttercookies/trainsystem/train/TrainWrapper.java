package com.peanutbuttercookies.trainsystem.train;

import java.util.ArrayList;

import com.peanutbuttercookies.trainsystem.traincontroller.TrainController;
import com.peanutbuttercookies.trainsystem.traincontroller.TrainControllerUI;

public class TrainWrapper {
	
	public ArrayList<TrainModel> trains = new ArrayList<TrainModel>();
	public ArrayList<TrainController> controllers = new ArrayList<TrainController>();
	public TrainUI trainUi = new TrainUI();
	public TrainControllerUI controlUi = new TrainControllerUI();

	public TrainWrapper() {
		// TODO Auto-generated constructor stub
	}
	
	public TrainModelInterface createTrain(double speed, double auth){
		TrainModel train = new TrainModel();
		TrainController trainControl = new TrainController(train);
		Thread thread = new Thread(train);
		thread.setDaemon(true);
		train.setId((int) thread.getId());
		trains.add(train);
		controllers.add(trainControl);
		trainUi.addTrainToList(train);
		controlUi.addConrollerToList(trainControl);
		train.setSpeedAndAuth(speed,auth);
		thread.start();
		return train;
	}

}
