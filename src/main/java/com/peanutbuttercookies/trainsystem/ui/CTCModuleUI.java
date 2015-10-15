package com.peanutbuttercookies.trainsystem.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.peanutbuttercookies.trainsystem.ctc.CTCBlock;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;

public class CTCModuleUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6499144261081785066L;

	private CTCModuleInterface module;

	private JTextField speed;
	private JTable blockTable;
	private JTable trainTable;
	private JButton sendButton;
	private DefaultComboBoxModel<Integer> trainModel;
	private DefaultComboBoxModel<Integer> blockModel;
	private BlockTableModel blockTableModel;
	private TrainTableModel trainTableModel;
	private JScrollPane trainSP;
	private JScrollPane blockSP;
	
	private HashSet<Integer> trainSet;
	
	public CTCModuleUI(CTCModuleInterface module) throws IOException {
		super("CTCModule");
		this.module = module;
		module.setUI(this);

		trainSet = new HashSet<Integer>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(600, 300));
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.setPreferredSize(new Dimension(600, 600));
		JPanel topPane = new JPanel();
		JPanel bottomPane = new JPanel();
		topPane.setPreferredSize(new Dimension(600, 550));
		bottomPane.setPreferredSize(new Dimension(600, 50));
		// JPanel middlePane = new JPanel();
		topPane.setLayout(new BoxLayout(topPane, BoxLayout.X_AXIS));
		// middlePane.setLayout(new FlowLayout());
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

		blockTable = new JTable();
		trainTable = new JTable();
		blockTableModel = new BlockTableModel();
		trainTableModel = new TrainTableModel();
		for(CTCBlock block : module.getBlocks()) {
			blockTableModel.addBlock(block);
		}
		blockTable.setModel(blockTableModel);
		trainTable.setModel(trainTableModel);
		trainSP = new JScrollPane(trainTable);
		blockSP = new JScrollPane(blockTable);
		top.add(trainSP);
		top.add(blockSP);
	}

	private void initializeBottom(JPanel bottom) {
		trainModel = new DefaultComboBoxModel<Integer>();
		blockModel = new DefaultComboBoxModel<Integer>();
		initializeBlockModel(blockModel);
		initializeTrainModel(trainModel);
		JComboBox<Integer> trainCBox = new JComboBox<Integer>(trainModel);
		JComboBox<Integer> blockCBox = new JComboBox<Integer>(blockModel);
		speed = new JTextField();
		sendButton = new JButton("Send");
		sendButton.addActionListener(this);
		trainCBox.setPreferredSize(new Dimension(150, 30));
		blockCBox.setPreferredSize(new Dimension(150, 30));
		speed.setPreferredSize(new Dimension(150, 30));
		bottom.add(trainCBox);
		bottom.add(blockCBox);
		bottom.add(speed);
		bottom.add(sendButton);
	}
	
	// for prototype
	private void initializeBlockModel(DefaultComboBoxModel<Integer> m) {
		for(CTCBlock block : module.getBlocks()) {
			m.addElement(block.getBlockNumber());
		}
	}
	
	// for prototype
	private void initializeTrainModel(DefaultComboBoxModel<Integer> m) {
		m.addElement(0);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int train = getSelected(trainModel);
		int authority = getSelected(blockModel);
		if(module.send(speed.getText(), train, authority)) {
			if(!trainSet.contains(train)) {
				trainSet.add(train);
				trainModel.addElement(train);
			}
		}
	}

	private Integer getSelected(DefaultComboBoxModel<Integer> m) {
		Object selected = m.getSelectedItem();
		int index = m.getIndexOf(selected);
		return m.getElementAt(index);
	}

}
