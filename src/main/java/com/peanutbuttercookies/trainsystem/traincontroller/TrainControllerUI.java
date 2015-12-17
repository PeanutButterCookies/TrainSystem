/*
* TrainControllerUI
*
* 2.2, 12/17/15
*
* Autumn Good
*/
package com.peanutbuttercookies.trainsystem.traincontroller;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.peanutbuttercookies.trainsystem.train.TrainModel;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TrainControllerUI extends JFrame {

	private JPanel contentPane;
	private JTextField speedText;
	private JTextField authText;
	private JTextField powerText;
	private JTextField doorsText;
	private JTextField stationText;
	TrainController trainController;
	private JTextField currentBlock;
	private JTextField enterSpeedText;
	private JComboBox selectController;


	/**
	 * Create the frame.
	 */
	public TrainControllerUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 447, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{10, 86, 60, 60, 0, 86, 60, 60, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{20, 20, 33, 23, 20, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblSelectTrain = new JLabel("Select Train:");
		GridBagConstraints gbc_lblSelectTrain = new GridBagConstraints();
		gbc_lblSelectTrain.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblSelectTrain.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectTrain.gridx = 2;
		gbc_lblSelectTrain.gridy = 0;
		contentPane.add(lblSelectTrain, gbc_lblSelectTrain);
		
		
		JComboBox<Integer> selectController_1 = new JComboBox<Integer>();
		selectController_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox<TrainController> combo = (JComboBox<TrainController>) e.getSource();
		        trainController.setCurrentlySelected(false);
		       	trainController = (TrainController) combo.getSelectedItem();
		       	trainController.setCurrentlySelected(true);
		       	updateUI();
			}
		});
		GridBagConstraints gbc_selectController_1 = new GridBagConstraints();
		gbc_selectController_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_selectController_1.insets = new Insets(0, 0, 5, 5);
		gbc_selectController_1.gridx = 3;
		gbc_selectController_1.gridy = 0;
		contentPane.add(selectController_1, gbc_selectController_1);
		selectController_1.addItem(1);
		if(trainController != null){
			trainController = (TrainController) selectController_1.getSelectedItem();
		}
		
		JLabel lblSpeedLimit = new JLabel("Speed:");
		GridBagConstraints gbc_lblSpeedLimit = new GridBagConstraints();
		gbc_lblSpeedLimit.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblSpeedLimit.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpeedLimit.gridx = 2;
		gbc_lblSpeedLimit.gridy = 1;
		contentPane.add(lblSpeedLimit, gbc_lblSpeedLimit);
		
		speedText = new JTextField();
		speedText.setEditable(false);
		GridBagConstraints gbc_speedText = new GridBagConstraints();
		gbc_speedText.anchor = GridBagConstraints.NORTHWEST;
		gbc_speedText.fill = GridBagConstraints.HORIZONTAL;
		gbc_speedText.insets = new Insets(0, 0, 5, 5);
		gbc_speedText.gridx = 3;
		gbc_speedText.gridy = 1;
		contentPane.add(speedText, gbc_speedText);
		speedText.setColumns(10);
		if(trainController != null){
			speedText.setText(new String(""+trainController.getCommandSpeed()));
		}
		
		JLabel lblMph = new JLabel("mph");
		GridBagConstraints gbc_lblMph = new GridBagConstraints();
		gbc_lblMph.insets = new Insets(0, 0, 5, 5);
		gbc_lblMph.gridx = 4;
		gbc_lblMph.gridy = 1;
		contentPane.add(lblMph, gbc_lblMph);
		
		JLabel lblDoors = new JLabel("Doors: ");
		GridBagConstraints gbc_lblDoors = new GridBagConstraints();
		gbc_lblDoors.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblDoors.insets = new Insets(0, 0, 5, 5);
		gbc_lblDoors.gridx = 6;
		gbc_lblDoors.gridy = 1;
		contentPane.add(lblDoors, gbc_lblDoors);
		
		doorsText = new JTextField();
		doorsText.setEditable(false);
		GridBagConstraints gbc_doorsText = new GridBagConstraints();
		gbc_doorsText.anchor = GridBagConstraints.NORTHWEST;
		gbc_doorsText.fill = GridBagConstraints.HORIZONTAL;
		gbc_doorsText.insets = new Insets(0, 0, 5, 5);
		gbc_doorsText.gridx = 7;
		gbc_doorsText.gridy = 1;
		contentPane.add(doorsText, gbc_doorsText);
		doorsText.setColumns(10);
		if(trainController != null){
			if(trainController.isDoorsOpen() == false)
				doorsText.setText("Closed");
			else
				doorsText.setText("Open");
		}
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					double speed = Double.parseDouble(enterSpeedText.getText());
					trainController.setSpeed(speed*0.44704);
				} catch(NumberFormatException n) {	
					System.out.println("Not valid");
				} catch(NullPointerException p) {
					System.out.println("controller null");
				}
			}
		});
		
		JLabel lblNewSpeed = new JLabel("New Speed:");
		GridBagConstraints gbc_lblNewSpeed = new GridBagConstraints();
		gbc_lblNewSpeed.anchor = GridBagConstraints.EAST;
		gbc_lblNewSpeed.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewSpeed.gridx = 2;
		gbc_lblNewSpeed.gridy = 2;
		contentPane.add(lblNewSpeed, gbc_lblNewSpeed);
		
		enterSpeedText = new JTextField();
		GridBagConstraints gbc_enterSpeedText = new GridBagConstraints();
		gbc_enterSpeedText.insets = new Insets(0, 0, 5, 5);
		gbc_enterSpeedText.fill = GridBagConstraints.HORIZONTAL;
		gbc_enterSpeedText.gridx = 3;
		gbc_enterSpeedText.gridy = 2;
		contentPane.add(enterSpeedText, gbc_enterSpeedText);
		enterSpeedText.setColumns(10);
		
		JLabel lblMph_1 = new JLabel("mph");
		GridBagConstraints gbc_lblMph_1 = new GridBagConstraints();
		gbc_lblMph_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblMph_1.gridx = 4;
		gbc_lblMph_1.gridy = 2;
		contentPane.add(lblMph_1, gbc_lblMph_1);
		GridBagConstraints gbc_btnEnter = new GridBagConstraints();
		gbc_btnEnter.insets = new Insets(0, 0, 5, 5);
		gbc_btnEnter.gridx = 5;
		gbc_btnEnter.gridy = 2;
		contentPane.add(btnEnter, gbc_btnEnter);
		
		JLabel lblAuth = new JLabel("Authority:");
		GridBagConstraints gbc_lblAuth = new GridBagConstraints();
		gbc_lblAuth.anchor = GridBagConstraints.EAST;
		gbc_lblAuth.insets = new Insets(0, 0, 5, 5);
		gbc_lblAuth.gridx = 2;
		gbc_lblAuth.gridy = 3;
		contentPane.add(lblAuth, gbc_lblAuth);
		
		authText = new JTextField();
		authText.setEditable(false);
		GridBagConstraints gbc_authText = new GridBagConstraints();
		gbc_authText.anchor = GridBagConstraints.NORTHWEST;
		gbc_authText.fill = GridBagConstraints.HORIZONTAL;
		gbc_authText.insets = new Insets(0, 0, 5, 5);
		gbc_authText.gridx = 3;
		gbc_authText.gridy = 3;
		contentPane.add(authText, gbc_authText);
		authText.setColumns(10);
		if(trainController != null){
			authText.setText(new String(""+trainController.getAuth()));
		}
		
		JLabel lblMiles = new JLabel("miles");
		GridBagConstraints gbc_lblMiles = new GridBagConstraints();
		gbc_lblMiles.insets = new Insets(0, 0, 5, 5);
		gbc_lblMiles.gridx = 4;
		gbc_lblMiles.gridy = 3;
		contentPane.add(lblMiles, gbc_lblMiles);
		
		JLabel lblNextStation = new JLabel("Station: ");
		GridBagConstraints gbc_lblNextStation = new GridBagConstraints();
		gbc_lblNextStation.anchor = GridBagConstraints.EAST;
		gbc_lblNextStation.insets = new Insets(0, 0, 5, 5);
		gbc_lblNextStation.gridx = 6;
		gbc_lblNextStation.gridy = 3;
		contentPane.add(lblNextStation, gbc_lblNextStation);
		
		stationText = new JTextField();
		stationText.setEditable(false);
		GridBagConstraints gbc_stationText = new GridBagConstraints();
		gbc_stationText.anchor = GridBagConstraints.NORTHWEST;
		gbc_stationText.fill = GridBagConstraints.HORIZONTAL;
		gbc_stationText.insets = new Insets(0, 0, 5, 5);
		gbc_stationText.gridx = 7;
		gbc_stationText.gridy = 3;
		contentPane.add(stationText, gbc_stationText);
		stationText.setColumns(10);
		if(trainController != null){
			stationText.setText(trainController.getStation());
		}
		
		JLabel lblPower = new JLabel("Power:");
		GridBagConstraints gbc_lblPower = new GridBagConstraints();
		gbc_lblPower.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblPower.insets = new Insets(0, 0, 5, 5);
		gbc_lblPower.gridx = 2;
		gbc_lblPower.gridy = 4;
		contentPane.add(lblPower, gbc_lblPower);
		
		powerText = new JTextField();
		powerText.setEditable(false);
		GridBagConstraints gbc_powerText = new GridBagConstraints();
		gbc_powerText.anchor = GridBagConstraints.NORTHWEST;
		gbc_powerText.fill = GridBagConstraints.HORIZONTAL;
		gbc_powerText.insets = new Insets(0, 0, 5, 5);
		gbc_powerText.gridx = 3;
		gbc_powerText.gridy = 4;
		contentPane.add(powerText, gbc_powerText);
		powerText.setColumns(10);
		if(trainController!= null){
			powerText.setText(new String(""+trainController.getPower()));
		}
		
		JLabel lblWatts = new JLabel("watts");
		GridBagConstraints gbc_lblWatts = new GridBagConstraints();
		gbc_lblWatts.insets = new Insets(0, 0, 5, 5);
		gbc_lblWatts.gridx = 4;
		gbc_lblWatts.gridy = 4;
		contentPane.add(lblWatts, gbc_lblWatts);
		
		JLabel lblCurrentBlock = new JLabel("Current Block: ");
		GridBagConstraints gbc_lblCurrentBlock = new GridBagConstraints();
		gbc_lblCurrentBlock.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblCurrentBlock.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentBlock.gridx = 6;
		gbc_lblCurrentBlock.gridy = 4;
		contentPane.add(lblCurrentBlock, gbc_lblCurrentBlock);
		
		currentBlock = new JTextField();
		currentBlock.setEditable(false);
		GridBagConstraints gbc_currentBlock = new GridBagConstraints();
		gbc_currentBlock.insets = new Insets(0, 0, 5, 5);
		gbc_currentBlock.anchor = GridBagConstraints.NORTHWEST;
		gbc_currentBlock.fill = GridBagConstraints.HORIZONTAL;
		gbc_currentBlock.gridx = 7;
		gbc_currentBlock.gridy = 4;
		contentPane.add(currentBlock, gbc_currentBlock);
		currentBlock.setColumns(10);
		if(trainController != null){
			currentBlock.setText(""+trainController.getBlockId());
		}
		
		JButton brakesButton = new JButton("Brakes");
		brakesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(trainController != null){
					trainController.setBrakes(true);
				}
			}
		});
		GridBagConstraints gbc_brakesButton = new GridBagConstraints();
		gbc_brakesButton.insets = new Insets(0, 0, 0, 5);
		gbc_brakesButton.gridx = 2;
		gbc_brakesButton.gridy = 6;
		contentPane.add(brakesButton, gbc_brakesButton);
		
		JButton eBrakesButton = new JButton("E Brakes");
		eBrakesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(trainController != null){
					trainController.setEmergencyBrakes(true);
				}
			}
		});
		GridBagConstraints gbc_eBrakesButton = new GridBagConstraints();
		gbc_eBrakesButton.insets = new Insets(0, 0, 0, 5);
		gbc_eBrakesButton.gridx = 6;
		gbc_eBrakesButton.gridy = 6;
		contentPane.add(eBrakesButton, gbc_eBrakesButton);
	}
	
	/**
	 * Adds a new train controller to the UI
	 * @param controller The new controller
	 */
	public void addControllerToList(TrainController controller){
		selectController.addItem(controller);
		if(selectController.getItemCount() == 1){
			selectController.setSelectedItem(controller);
			updateUI();
		}
	}
	
	/**
	 * Removes a train controller from the UI
	 * @param control The controller to be removed
	 */
	public void removeControllerFromList(TrainController control){
		selectController.removeItem(control);
	}
	
	public void updateUI(){
		speedText.setText(new String(""+trainController.getSpeedLimit()*2.23694));
		authText.setText(new String(""+trainController.getAuth()*0.000621371));
		powerText.setText(new String(""+trainController.getPower()));
		if(trainController.isDoorsOpen() == false)
			doorsText.setText("Closed");
		else
			doorsText.setText("Open");
		stationText.setText(trainController.getStation());
		currentBlock.setText(""+trainController.getBlockId());
	}
}
