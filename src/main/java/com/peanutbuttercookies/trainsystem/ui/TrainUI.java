package com.peanutbuttercookies.trainsystem.ui;

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
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainUI frame = new TrainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TrainUI() {
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
		
		textField = new JTextField();
		textField.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblLength);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, lblLength);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField_1, 0, SpringLayout.SOUTH, lblWidth);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_1, 0, SpringLayout.EAST, textField);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField_2, 0, SpringLayout.SOUTH, lblHeight);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_2, 0, SpringLayout.EAST, textField);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_3, 6, SpringLayout.EAST, lblPassengerCount);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField_3, 0, SpringLayout.SOUTH, lblLength);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField_4, 0, SpringLayout.SOUTH, lblWidth);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_4, 0, SpringLayout.EAST, textField_3);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField_5, 0, SpringLayout.SOUTH, lblHeight);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_5, 0, SpringLayout.EAST, textField_3);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField_6, 0, SpringLayout.SOUTH, lblSpeed);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_6, 0, SpringLayout.EAST, textField);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField_7, 0, SpringLayout.SOUTH, lblAcceleration);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_7, 0, SpringLayout.EAST, textField);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField_8, 0, SpringLayout.SOUTH, lblPower);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_8, 0, SpringLayout.EAST, textField);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		JButton btnEmergencyBrake = new JButton("Emergency Brake");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnEmergencyBrake, -4, SpringLayout.NORTH, lblAcceleration);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnEmergencyBrake, -73, SpringLayout.EAST, contentPane);
		contentPane.add(btnEmergencyBrake);
		
		JComboBox comboBox = new JComboBox();
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBox, 6, SpringLayout.EAST, lblSelectTrain);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, comboBox, 0, SpringLayout.SOUTH, lblSelectTrain);
		contentPane.add(comboBox);
	}
}
