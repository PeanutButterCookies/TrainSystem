
package com.peanutbuttercookies.trainsystem.train;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class TrainUI extends JFrame {

	private JPanel contentPane;
	private JTextField length;
	private JTextField width;
	private JTextField height;
	private JTextField passengerCount;
	private JTextField numCars;
	private JTextField mass;
	private JTextField speed;
	private JTextField acceleration;
	private JTextField power;
	private TrainModelInterface train;

	/**
	 * Create the frame.
	 */
	public TrainUI(TrainModelInterface trainModel) {
		train = trainModel;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblSelectTrain = new JLabel("Select Train: ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSelectTrain, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSelectTrain, 0, SpringLayout.WEST, contentPane);
		contentPane.add(lblSelectTrain);
		
		JLabel lblLength = new JLabel("Length: ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblLength, 23, SpringLayout.SOUTH, lblSelectTrain);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblLength, 0, SpringLayout.EAST, lblSelectTrain);
		contentPane.add(lblLength);
		
		JLabel lblWidth = new JLabel("Width: ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblWidth, 16, SpringLayout.SOUTH, lblLength);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblWidth, 0, SpringLayout.EAST, lblSelectTrain);
		contentPane.add(lblWidth);
		
		JLabel lblHeight = new JLabel("Height: ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblHeight, 16, SpringLayout.SOUTH, lblWidth);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblHeight, 0, SpringLayout.EAST, lblSelectTrain);
		contentPane.add(lblHeight);
		
		JLabel lblSpeed = new JLabel("Speed: ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSpeed, 22, SpringLayout.SOUTH, lblHeight);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblSpeed, 0, SpringLayout.EAST, lblSelectTrain);
		contentPane.add(lblSpeed);
		
		JLabel lblAcceleration = new JLabel("Acceleration: ");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblAcceleration, 0, SpringLayout.WEST, lblSelectTrain);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblAcceleration, -64, SpringLayout.SOUTH, contentPane);
		contentPane.add(lblAcceleration);
		
		JLabel lblPower = new JLabel("Power: ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPower, 14, SpringLayout.SOUTH, lblAcceleration);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblPower, 0, SpringLayout.EAST, lblSelectTrain);
		contentPane.add(lblPower);
		
		JLabel lblPassengerCount = new JLabel("Passenger Count: ");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblPassengerCount, 0, SpringLayout.SOUTH, lblLength);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblPassengerCount, -153, SpringLayout.EAST, contentPane);
		contentPane.add(lblPassengerCount);
		
		JLabel lblNumberOfCars = new JLabel("Number of Cars: ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNumberOfCars, 0, SpringLayout.NORTH, lblWidth);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNumberOfCars, 0, SpringLayout.EAST, lblPassengerCount);
		contentPane.add(lblNumberOfCars);
		
		JLabel lblMass = new JLabel("Mass:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblMass, 0, SpringLayout.NORTH, lblHeight);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblMass, 0, SpringLayout.EAST, lblPassengerCount);
		contentPane.add(lblMass);
		
		length = new JTextField();
		length.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.WEST, length, 6, SpringLayout.EAST, lblLength);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, length, 0, SpringLayout.SOUTH, lblLength);
		contentPane.add(length);
		length.setColumns(10);
		length.setText(new String(""+train.getLength()));
		
		width = new JTextField();
		width.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, width, 0, SpringLayout.SOUTH, lblWidth);
		sl_contentPane.putConstraint(SpringLayout.EAST, width, 0, SpringLayout.EAST, length);
		contentPane.add(width);
		width.setColumns(10);
		width.setText(""+train.getWidth());
		
		height = new JTextField();
		height.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, height, 0, SpringLayout.SOUTH, lblHeight);
		sl_contentPane.putConstraint(SpringLayout.EAST, height, 0, SpringLayout.EAST, length);
		contentPane.add(height);
		height.setColumns(10);
		height.setText(""+train.getHeight());
		
		passengerCount = new JTextField();
		passengerCount.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.WEST, passengerCount, 6, SpringLayout.EAST, lblPassengerCount);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, passengerCount, 0, SpringLayout.SOUTH, lblLength);
		contentPane.add(passengerCount);
		passengerCount.setColumns(10);
		passengerCount.setText(""+train.getNumPassengers());
		
		numCars = new JTextField();
		numCars.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, numCars, 0, SpringLayout.SOUTH, lblWidth);
		sl_contentPane.putConstraint(SpringLayout.EAST, numCars, 0, SpringLayout.EAST, passengerCount);
		contentPane.add(numCars);
		numCars.setColumns(10);
		numCars.setText(""+train.getNumCars());
		
		mass = new JTextField();
		mass.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, mass, 0, SpringLayout.SOUTH, lblHeight);
		sl_contentPane.putConstraint(SpringLayout.EAST, mass, 0, SpringLayout.EAST, passengerCount);
		contentPane.add(mass);
		mass.setColumns(10);
		mass.setText(""+train.getMass());
		
		speed = new JTextField();
		speed.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, speed, 0, SpringLayout.SOUTH, lblSpeed);
		sl_contentPane.putConstraint(SpringLayout.EAST, speed, 0, SpringLayout.EAST, length);
		contentPane.add(speed);
		speed.setColumns(10);
		speed.setText(""+train.getCurrentSpeed());
		
		acceleration = new JTextField();
		acceleration.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, acceleration, 0, SpringLayout.SOUTH, lblAcceleration);
		sl_contentPane.putConstraint(SpringLayout.EAST, acceleration, 0, SpringLayout.EAST, length);
		contentPane.add(acceleration);
		acceleration.setColumns(10);
		acceleration.setText(""+train.getAcceleration());
		
		power = new JTextField();
		power.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, power, 0, SpringLayout.SOUTH, lblPower);
		sl_contentPane.putConstraint(SpringLayout.EAST, power, 0, SpringLayout.EAST, length);
		contentPane.add(power);
		power.setColumns(10);
		power.setText(""+train.getPower());
		
		JButton btnEmergencyBrake = new JButton("Emergency Brake");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnEmergencyBrake, -4, SpringLayout.NORTH, lblAcceleration);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnEmergencyBrake, -73, SpringLayout.EAST, contentPane);
		contentPane.add(btnEmergencyBrake);
		
		JComboBox<Integer> selectTrain = new JComboBox<Integer>();
		sl_contentPane.putConstraint(SpringLayout.WEST, selectTrain, 6, SpringLayout.EAST, lblSelectTrain);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, selectTrain, 0, SpringLayout.SOUTH, lblSelectTrain);
		contentPane.add(selectTrain);
		selectTrain.addItem(1);
		setVisible(true);
	}
	
	public void updateUI(){
		numCars.setText(""+train.getNumCars());
		height.setText(""+train.getHeight());
		power.setText(""+train.getPower());
		acceleration.setText(""+train.getAcceleration());
		speed.setText(""+train.getCurrentSpeed());
		mass.setText(""+train.getMass());
		passengerCount.setText(""+train.getNumPassengers());
		width.setText(""+train.getWidth());
		length.setText(new String(""+train.getLength()));
	}
}
