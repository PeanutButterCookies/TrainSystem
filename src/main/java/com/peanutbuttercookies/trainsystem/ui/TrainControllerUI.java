package com.peanutbuttercookies.trainsystem.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.peanutbuttercookies.trainsystem.traincontroller.TrainController;

import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;

public class TrainControllerUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrainControllerUI frame = new TrainControllerUI();
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
	public TrainControllerUI(TrainController controller) {
		TrainController trainController = controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblSelectTrain = new JLabel("Select Train:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSelectTrain, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSelectTrain, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblSelectTrain);
		
		JLabel label = new JLabel("");
		sl_contentPane.putConstraint(SpringLayout.NORTH, label, 34, SpringLayout.SOUTH, lblSelectTrain);
		sl_contentPane.putConstraint(SpringLayout.EAST, label, 0, SpringLayout.EAST, lblSelectTrain);
		contentPane.add(label);
		
		JLabel lblSpeedLimit = new JLabel("Speed Limit:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblSpeedLimit, 0, SpringLayout.NORTH, label);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSpeedLimit, 38, SpringLayout.WEST, contentPane);
		contentPane.add(lblSpeedLimit);
		
		JLabel lblAuthority = new JLabel("Authority:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblAuthority, 48, SpringLayout.WEST, contentPane);
		contentPane.add(lblAuthority);
		
		JLabel lblPower = new JLabel("Power:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblAuthority, 41, SpringLayout.SOUTH, lblPower);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPower, 36, SpringLayout.SOUTH, lblSpeedLimit);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblPower, 0, SpringLayout.EAST, lblSpeedLimit);
		contentPane.add(lblPower);
		
		textField = new JTextField();
		textField.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblSpeedLimit);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, lblSpeedLimit);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(new String(""+trainController.speedLimit));
		
		textField_1 = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_1, -3, SpringLayout.NORTH, lblPower);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_1, 6, SpringLayout.EAST, lblPower);
		textField_1.setEditable(false);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(new String(""+trainController.authority));
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField_2, 0, SpringLayout.SOUTH, lblAuthority);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_2, 0, SpringLayout.EAST, textField);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(new String(""+trainController.power));
		
		JLabel lblDoors = new JLabel("Doors: ");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDoors, 67, SpringLayout.EAST, textField);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblDoors, 0, SpringLayout.SOUTH, lblSpeedLimit);
		contentPane.add(lblDoors);
		
		JLabel lblNextStation = new JLabel("Station: ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNextStation, 0, SpringLayout.NORTH, lblPower);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNextStation, 0, SpringLayout.EAST, lblDoors);
		contentPane.add(lblNextStation);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_3, 0, SpringLayout.NORTH, textField);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_3, 6, SpringLayout.EAST, lblDoors);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		if(trainController.doorsOpen == false)
			textField_3.setText("False");
		else
			textField_3.setText("True");
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_4, 0, SpringLayout.NORTH, lblPower);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_4, 6, SpringLayout.EAST, lblNextStation);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setText(trainController.station);
		
		
		JComboBox<TrainController> comboBox = new JComboBox<TrainController>();
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBox, 6, SpringLayout.EAST, lblSelectTrain);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, comboBox, 0, SpringLayout.SOUTH, lblSelectTrain);
		contentPane.add(comboBox);
		comboBox.addItem(trainController);
	}
}
