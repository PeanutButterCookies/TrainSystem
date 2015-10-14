package com.peanutbuttercookies.trainsystem.ui;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.ui.models.CTCBlockModel;
import com.peanutbuttercookies.trainsystem.ui.models.CTCTrainModel;

public class CTCModuleUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6499144261081785066L;

	private CTCModuleInterface module;
	
	private JTable blockTable;
	private JTable trainTable;
	private JButton sendButton;
	private JComboBox<Integer> trainCBox;
	private JComboBox<Integer> blockCBox;
	private CTCBlockModel blockModel;
	private CTCTrainModel trainModel;
	private JScrollPane trainSP;
	private JScrollPane blockSP;
	
	public CTCModuleUI(CTCModuleInterface module) throws IOException {
		super("CTCModule");
		this.module = module;
	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(600, 300));
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setPreferredSize(new Dimension(600, 600));
        JPanel topPane = new JPanel();
        JPanel bottomPane = new JPanel();
        topPane.setPreferredSize(new Dimension(600, 550));
        bottomPane.setPreferredSize(new Dimension(600, 50));
//        JPanel middlePane = new JPanel();
        topPane.setLayout(new BoxLayout(topPane, BoxLayout.X_AXIS));
//        middlePane.setLayout(new FlowLayout());
        bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.X_AXIS));

		blockModel = new CTCBlockModel();
		trainModel = new CTCTrainModel();
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
		
		blockTable = new JTable(blockModel);
		trainTable = new JTable(trainModel);
		trainSP = new JScrollPane(trainTable);
		blockSP = new JScrollPane(blockTable);
		top.add(trainSP);
		top.add(blockSP);
	}
	
	private void initializeBottom(JPanel bottom) {
		trainCBox = new JComboBox<Integer>(trainModel);
		blockCBox = new JComboBox<Integer>(blockModel);
		sendButton = new JButton("Send");
		trainCBox.setPreferredSize(new Dimension(200, 30));
		blockCBox.setPreferredSize(new Dimension(200, 30));
		bottom.add(trainCBox);
		bottom.add(blockCBox);
		bottom.add(sendButton);
	}
}
