package com.peanutbuttercookies.trainsystem.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.peanutbuttercookies.trainsystem.ui.models.CTCBlockTableModel;
import com.peanutbuttercookies.trainsystem.ui.models.CTCTrainTableModel;

public class CTCModuleUI extends JFrame {
	private JTable blockTable;
	private JTable trainTable;
	private JButton sendButton;
	private JComboBox trainCBox;
	private JComboBox blockCBox;
	private CTCBlockTableModel blockModel;
	private CTCTrainTableModel trainModel;
	private JScrollPane trainSP;
	private JScrollPane blockSP;
	
	public CTCModuleUI() throws IOException {
		super("CTCModule");
	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(600, 300));
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setPreferredSize(new Dimension(600, 600));
        contentPane.setBackground(Color.cyan);
        JPanel topPane = new JPanel();
        JPanel bottomPane = new JPanel();
        topPane.setPreferredSize(new Dimension(600, 550));
        bottomPane.setPreferredSize(new Dimension(600, 50));
//        JPanel middlePane = new JPanel();
        topPane.setLayout(new BoxLayout(topPane, BoxLayout.X_AXIS));
//        middlePane.setLayout(new FlowLayout());
        bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.X_AXIS));

        initializeTop(topPane);
        initializeBottom(bottomPane);

        contentPane.add(topPane);
        contentPane.add(bottomPane);
		setContentPane(contentPane);
        pack();
        setLocationByPlatform(true);
        setVisible(true);
    }
	
	private void initializeTop(JPanel top) throws IOException {
		
		blockModel = new CTCBlockTableModel();
		blockTable = new JTable(blockModel);
		trainModel = new CTCTrainTableModel();
		trainTable = new JTable();
//		trainTable.setPreferredSize(new Dimension(300, 500));
//		blockTable.setPreferredSize(new Dimension(300, 500));
		trainSP = new JScrollPane(trainTable);
		blockSP = new JScrollPane(blockTable);
//		trainSP.setPreferredSize(new Dimension(200, 200));
//		blockSP.setPreferredSize(new Dimension(200, 200));
		top.add(trainSP);
		top.add(blockSP);
	}
	
	private void initializeBottom(JPanel bottom) {
		trainCBox = new JComboBox();
		blockCBox = new JComboBox();
		sendButton = new JButton("Send");
		trainCBox.setPreferredSize(new Dimension(200, 30));
		blockCBox.setPreferredSize(new Dimension(200, 30));
		bottom.add(trainCBox);
		bottom.add(blockCBox);
		bottom.add(sendButton);
	}
}
