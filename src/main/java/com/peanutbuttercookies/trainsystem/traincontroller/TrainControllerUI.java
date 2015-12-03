package com.peanutbuttercookies.trainsystem.traincontroller;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



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
	TrainController trainController;
	private JTextField currentBlock;


	/**
	 * Create the frame.
	 */
	public TrainControllerUI(TrainController controller) {
		trainController = controller;
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
		
		JLabel lblPower = new JLabel("Power:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblPower, 48, SpringLayout.WEST, contentPane);
		contentPane.add(lblPower);
		
		JLabel lblAuth = new JLabel("Authority:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblPower, 41, SpringLayout.SOUTH, lblAuth);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblAuth, 36, SpringLayout.SOUTH, lblSpeedLimit);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblAuth, 0, SpringLayout.EAST, lblSpeedLimit);
		contentPane.add(lblAuth);
		
		textField = new JTextField();
		textField.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblSpeedLimit);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, lblSpeedLimit);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(new String(""+trainController.speedLimit));
		
		textField_1 = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_1, -3, SpringLayout.NORTH, lblAuth);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_1, 6, SpringLayout.EAST, lblAuth);
		textField_1.setEditable(false);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setText(new String(""+trainController.auth));
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField_2, 0, SpringLayout.SOUTH, lblPower);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField_2, 0, SpringLayout.EAST, textField);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setText(new String(""+trainController.power));
		
		JLabel lblDoors = new JLabel("Doors: ");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblDoors, 67, SpringLayout.EAST, textField);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblDoors, 0, SpringLayout.SOUTH, lblSpeedLimit);
		contentPane.add(lblDoors);
		
		JLabel lblNextStation = new JLabel("Station: ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNextStation, 0, SpringLayout.NORTH, lblAuth);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNextStation, 0, SpringLayout.EAST, lblDoors);
		contentPane.add(lblNextStation);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_3, 0, SpringLayout.NORTH, textField);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_3, 6, SpringLayout.EAST, lblDoors);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		if(trainController.doorsOpen == false)
			textField_3.setText("Closed");
		else
			textField_3.setText("Open");
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField_4, 0, SpringLayout.NORTH, lblAuth);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField_4, 6, SpringLayout.EAST, lblNextStation);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		textField_4.setText(trainController.station);
		
		
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		sl_contentPane.putConstraint(SpringLayout.WEST, comboBox, 6, SpringLayout.EAST, lblSelectTrain);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, comboBox, 0, SpringLayout.SOUTH, lblSelectTrain);
		contentPane.add(comboBox);
		
		currentBlock = new JTextField();
		currentBlock.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH, currentBlock, 0, SpringLayout.NORTH, textField_2);
		sl_contentPane.putConstraint(SpringLayout.EAST, currentBlock, 0, SpringLayout.EAST, textField_3);
		contentPane.add(currentBlock);
		currentBlock.setColumns(10);
		currentBlock.setText(""+trainController.blockId);
		
		JLabel lblCurrentBlock = new JLabel("Current Block: ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblCurrentBlock, 0, SpringLayout.NORTH, lblPower);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblCurrentBlock, 0, SpringLayout.EAST, lblDoors);
		contentPane.add(lblCurrentBlock);
		comboBox.addItem(1);
		setVisible(true);
		
	}
	
	public void update(){
		textField.setText(new String(""+trainController.speedLimit));
		textField_1.setText(new String(""+trainController.auth));
		textField_2.setText(new String(""+trainController.power));
		if(trainController.doorsOpen == false)
			textField_3.setText("Closed");
		else
			textField_3.setText("Open");
		textField_4.setText(trainController.station);
		currentBlock.setText(""+trainController.blockId);
	}
}
