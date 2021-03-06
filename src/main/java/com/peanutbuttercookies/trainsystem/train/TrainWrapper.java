/*
* TrainWrapper
*
* 1.1, 12/17/15
*
* Autumn Good
*/
package com.peanutbuttercookies.trainsystem.train;

import java.util.ArrayList;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.traincontroller.TrainController;
import com.peanutbuttercookies.trainsystem.traincontroller.TrainControllerAuthentication;
import com.peanutbuttercookies.trainsystem.traincontroller.TrainControllerUI;

public class TrainWrapper {
	
	private ArrayList<TrainModel> trains = new ArrayList<TrainModel>();
	private ArrayList<TrainController> controllers = new ArrayList<TrainController>();
	private TrainUI trainUi = new TrainUI();
	private TrainControllerUI controlUi = new TrainControllerUI();
	private TrainControllerAuthentication authenticate = new TrainControllerAuthentication("admin","password",controlUi);

	public TrainWrapper() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Creates each train as a thread and initializes UI's, sends first speed, authority, and yard block
	 * @param speed Commanded speed
	 * @param auth  Command authority
	 * @param block Yard Block
	 * @return
	 */
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

	/**
	 * Stops a train thread when it goes back into the yard
	 * @param trainComm
	 */
	public void destroyTrain(TrainModelInterface trainComm){
		TrainModel train = (TrainModel)trainComm;
		trainUi.removeTrainFromList(train);
		controlUi.removeControllerFromList((TrainController)train.getController());
		train.controller.setAlive(false);
		trains.remove(train);
		controllers.remove(train.controller);
	}
}
