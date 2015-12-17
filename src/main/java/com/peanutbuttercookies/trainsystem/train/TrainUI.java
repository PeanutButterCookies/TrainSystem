/*
* TrainUI
*
* 2.2, 12/17/15
*
* Autumn Good
*/
package com.peanutbuttercookies.trainsystem.train;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrainUI extends JFrame {

	private JPanel contentPane;
	private JTextField length;
	private JTextField width;
	private JTextField height;
	private JTextField passengerCount;
	private JTextField mass;
	private JTextField speed;
	private JTextField acceleration;
	private JTextField power;
	private JComboBox<TrainModel> selectTrain;
	private TrainModel train;
	private JLabel lblFeet;
	private JLabel lblFeet_1;
	private JLabel lblFeet_2;
	private JLabel lblMph;
	private JLabel lblMph_1;
	private JLabel lblWatts;
	private JLabel lblTons;

	/**
	 * Create the frame.
	 */
	public TrainUI() {
		//train = trainModel;
		train = (TrainModel) selectTrain.getSelectedItem();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{66, 86, 0, 89, 86, 0, 0};
		gbl_contentPane.rowHeights = new int[]{20, 20, 20, 20, 20, 25, 20, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblSelectTrain = new JLabel("Select Train: ");
		GridBagConstraints gbc_lblSelectTrain = new GridBagConstraints();
		gbc_lblSelectTrain.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblSelectTrain.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectTrain.gridx = 0;
		gbc_lblSelectTrain.gridy = 0;
		contentPane.add(lblSelectTrain, gbc_lblSelectTrain);
		
		selectTrain = new JComboBox<TrainModel>();
		GridBagConstraints gbc_selectTrain = new GridBagConstraints();
		gbc_selectTrain.anchor = GridBagConstraints.NORTHWEST;
		gbc_selectTrain.insets = new Insets(0, 0, 5, 5);
		gbc_selectTrain.gridx = 1;
		gbc_selectTrain.gridy = 0;
		contentPane.add(selectTrain, gbc_selectTrain);
		selectTrain.addActionListener(new ActionListener() {
			 
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        JComboBox<TrainModel> combo = (JComboBox<TrainModel>) event.getSource();
		        train.setCurrentlySelected(false);
		       	train = (TrainModel) combo.getSelectedItem();
		       	train.setCurrentlySelected(true);
		       	updateUI();
		    }
		});
		
		JLabel lblLength = new JLabel("Length: ");
		GridBagConstraints gbc_lblLength = new GridBagConstraints();
		gbc_lblLength.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblLength.insets = new Insets(0, 0, 5, 5);
		gbc_lblLength.gridx = 0;
		gbc_lblLength.gridy = 1;
		contentPane.add(lblLength, gbc_lblLength);
		
		length = new JTextField();
		length.setEditable(false);
		GridBagConstraints gbc_length = new GridBagConstraints();
		gbc_length.anchor = GridBagConstraints.NORTHWEST;
		gbc_length.insets = new Insets(0, 0, 5, 5);
		gbc_length.gridx = 1;
		gbc_length.gridy = 1;
		contentPane.add(length, gbc_length);
		length.setColumns(10);
		if(train != null){
			length.setText(new String(""+train.getLength()));
		}
		
		lblFeet = new JLabel("Feet");
		GridBagConstraints gbc_lblFeet = new GridBagConstraints();
		gbc_lblFeet.insets = new Insets(0, 0, 5, 5);
		gbc_lblFeet.gridx = 2;
		gbc_lblFeet.gridy = 1;
		contentPane.add(lblFeet, gbc_lblFeet);
		
		JLabel lblPassengerCount = new JLabel("Passenger Count: ");
		GridBagConstraints gbc_lblPassengerCount = new GridBagConstraints();
		gbc_lblPassengerCount.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblPassengerCount.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassengerCount.gridx = 3;
		gbc_lblPassengerCount.gridy = 1;
		contentPane.add(lblPassengerCount, gbc_lblPassengerCount);
		
		passengerCount = new JTextField();
		passengerCount.setEditable(false);
		GridBagConstraints gbc_passengerCount = new GridBagConstraints();
		gbc_passengerCount.anchor = GridBagConstraints.NORTHWEST;
		gbc_passengerCount.insets = new Insets(0, 0, 5, 5);
		gbc_passengerCount.gridx = 4;
		gbc_passengerCount.gridy = 1;
		contentPane.add(passengerCount, gbc_passengerCount);
		passengerCount.setColumns(10);
		if(train != null){
			passengerCount.setText(""+train.getNumPassengers());
		}
		
		JLabel lblWidth = new JLabel("Width: ");
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
		gbc_lblWidth.gridx = 0;
		gbc_lblWidth.gridy = 2;
		contentPane.add(lblWidth, gbc_lblWidth);
		
		width = new JTextField();
		width.setEditable(false);
		GridBagConstraints gbc_width = new GridBagConstraints();
		gbc_width.anchor = GridBagConstraints.NORTHWEST;
		gbc_width.insets = new Insets(0, 0, 5, 5);
		gbc_width.gridx = 1;
		gbc_width.gridy = 2;
		contentPane.add(width, gbc_width);
		width.setColumns(10);
		if(train != null){
			width.setText(""+train.getWidth());
		}
		
		lblFeet_1 = new JLabel("Feet");
		GridBagConstraints gbc_lblFeet_1 = new GridBagConstraints();
		gbc_lblFeet_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblFeet_1.gridx = 2;
		gbc_lblFeet_1.gridy = 2;
		contentPane.add(lblFeet_1, gbc_lblFeet_1);
		
		JLabel lblMass = new JLabel("Mass:");
		GridBagConstraints gbc_lblMass = new GridBagConstraints();
		gbc_lblMass.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblMass.insets = new Insets(0, 0, 5, 5);
		gbc_lblMass.gridx = 3;
		gbc_lblMass.gridy = 2;
		contentPane.add(lblMass, gbc_lblMass);
		
		mass = new JTextField();
		mass.setEditable(false);
		GridBagConstraints gbc_mass = new GridBagConstraints();
		gbc_mass.anchor = GridBagConstraints.NORTHWEST;
		gbc_mass.insets = new Insets(0, 0, 5, 5);
		gbc_mass.gridx = 4;
		gbc_mass.gridy = 2;
		contentPane.add(mass, gbc_mass);
		mass.setColumns(10);
		if(train != null){
			mass.setText(""+train.getMass());
		}
		
		lblTons = new JLabel("Tons");
		GridBagConstraints gbc_lblTons = new GridBagConstraints();
		gbc_lblTons.insets = new Insets(0, 0, 5, 0);
		gbc_lblTons.gridx = 5;
		gbc_lblTons.gridy = 2;
		contentPane.add(lblTons, gbc_lblTons);
		
		JLabel lblHeight = new JLabel("Height: ");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 0;
		gbc_lblHeight.gridy = 3;
		contentPane.add(lblHeight, gbc_lblHeight);
		
		height = new JTextField();
		height.setEditable(false);
		GridBagConstraints gbc_height = new GridBagConstraints();
		gbc_height.anchor = GridBagConstraints.NORTHWEST;
		gbc_height.insets = new Insets(0, 0, 5, 5);
		gbc_height.gridx = 1;
		gbc_height.gridy = 3;
		contentPane.add(height, gbc_height);
		height.setColumns(10);
		if(train != null){
			height.setText(""+train.getHeight());
		}
		
		lblFeet_2 = new JLabel("Feet");
		GridBagConstraints gbc_lblFeet_2 = new GridBagConstraints();
		gbc_lblFeet_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblFeet_2.gridx = 2;
		gbc_lblFeet_2.gridy = 3;
		contentPane.add(lblFeet_2, gbc_lblFeet_2);
		
		JLabel lblSpeed = new JLabel("Speed: ");
		GridBagConstraints gbc_lblSpeed = new GridBagConstraints();
		gbc_lblSpeed.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblSpeed.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpeed.gridx = 0;
		gbc_lblSpeed.gridy = 4;
		contentPane.add(lblSpeed, gbc_lblSpeed);
		
		speed = new JTextField();
		speed.setEditable(false);
		GridBagConstraints gbc_speed = new GridBagConstraints();
		gbc_speed.anchor = GridBagConstraints.NORTHWEST;
		gbc_speed.insets = new Insets(0, 0, 5, 5);
		gbc_speed.gridx = 1;
		gbc_speed.gridy = 4;
		contentPane.add(speed, gbc_speed);
		speed.setColumns(10);
		if(train != null){
			speed.setText(""+train.getCurrentSpeed()*2.23694); //convert to mph
		}
		
		lblMph = new JLabel("mph");
		GridBagConstraints gbc_lblMph = new GridBagConstraints();
		gbc_lblMph.insets = new Insets(0, 0, 5, 5);
		gbc_lblMph.gridx = 2;
		gbc_lblMph.gridy = 4;
		contentPane.add(lblMph, gbc_lblMph);
		
		JLabel lblAcceleration = new JLabel("Acceleration: ");
		GridBagConstraints gbc_lblAcceleration = new GridBagConstraints();
		gbc_lblAcceleration.anchor = GridBagConstraints.WEST;
		gbc_lblAcceleration.insets = new Insets(0, 0, 5, 5);
		gbc_lblAcceleration.gridx = 0;
		gbc_lblAcceleration.gridy = 5;
		contentPane.add(lblAcceleration, gbc_lblAcceleration);
		
		acceleration = new JTextField();
		acceleration.setEditable(false);
		GridBagConstraints gbc_acceleration = new GridBagConstraints();
		gbc_acceleration.anchor = GridBagConstraints.NORTHWEST;
		gbc_acceleration.insets = new Insets(0, 0, 5, 5);
		gbc_acceleration.gridx = 1;
		gbc_acceleration.gridy = 5;
		contentPane.add(acceleration, gbc_acceleration);
		acceleration.setColumns(10);
		if(train!= null){
			acceleration.setText(""+train.getAcceleration());
		}
		
		
		JButton btnEmergencyBrake = new JButton("Emergency Brake");
		btnEmergencyBrake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(train != null){
					train.controller.setEmergencyBrakes(true);
				}
			}
		});
		
		lblMph_1 = new JLabel("mph^2");
		GridBagConstraints gbc_lblMph_1 = new GridBagConstraints();
		gbc_lblMph_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblMph_1.gridx = 2;
		gbc_lblMph_1.gridy = 5;
		contentPane.add(lblMph_1, gbc_lblMph_1);
		GridBagConstraints gbc_btnEmergencyBrake = new GridBagConstraints();
		gbc_btnEmergencyBrake.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnEmergencyBrake.insets = new Insets(0, 0, 5, 5);
		gbc_btnEmergencyBrake.gridwidth = 2;
		gbc_btnEmergencyBrake.gridx = 3;
		gbc_btnEmergencyBrake.gridy = 5;
		contentPane.add(btnEmergencyBrake, gbc_btnEmergencyBrake);
		
		JLabel lblPower = new JLabel("Power: ");
		GridBagConstraints gbc_lblPower = new GridBagConstraints();
		gbc_lblPower.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblPower.insets = new Insets(0, 0, 0, 5);
		gbc_lblPower.gridx = 0;
		gbc_lblPower.gridy = 6;
		contentPane.add(lblPower, gbc_lblPower);
		
		power = new JTextField();
		power.setEditable(false);
		GridBagConstraints gbc_power = new GridBagConstraints();
		gbc_power.anchor = GridBagConstraints.NORTHWEST;
		gbc_power.insets = new Insets(0, 0, 0, 5);
		gbc_power.gridx = 1;
		gbc_power.gridy = 6;
		contentPane.add(power, gbc_power);
		power.setColumns(10);
		if(train != null){
			power.setText(""+train.getPower());
		}
		
		
		lblWatts = new JLabel("Watts");
		GridBagConstraints gbc_lblWatts = new GridBagConstraints();
		gbc_lblWatts.insets = new Insets(0, 0, 0, 5);
		gbc_lblWatts.gridx = 2;
		gbc_lblWatts.gridy = 6;
		contentPane.add(lblWatts, gbc_lblWatts);
		//selectTrain.addItem(1);
		//add(contentPane);
		setVisible(true);
	}
	/**
	 * Adds a new train model to the UI from the TrainWrapper
	 * @param train New train model from TrainWrapper
	 */
	public void addTrainToList(TrainModel train){
		selectTrain.addItem(train);
		if(selectTrain.getItemCount() == 1){
			selectTrain.setSelectedItem(train);
			updateUI();
		}
	}
	
	/**
	 * Removes a train from the UI
	 * @param train Train to be removed
	 */
	public void removeTrainFromList(TrainModel train){
		selectTrain.removeItem(train);
	}
	
	/**
	 * Is called every run iteration to update the UI with new values
	 */
	public synchronized void updateUI(){
		height.setText(""+train.getHeight()*3.28084);
		power.setText(""+train.getPower()*3.28084);
		acceleration.setText(""+train.getAcceleration()* 8052.9706513958);
		speed.setText(""+train.getCurrentSpeed()*2.23694);
		mass.setText(""+train.getMass()*0.00110231);
		passengerCount.setText(""+train.getNumPassengers());
		width.setText(""+train.getWidth()*3.28084);
		length.setText(new String(""+train.getLength()*3.28084));
	}
}
