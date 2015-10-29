/*
 * Kevin Nash
 * 10/15/2015
 */


package com.peanutbuttercookies.trainsystem.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.peanutbuttercookies.trainsystem.ctc.CTCBlock;
import com.peanutbuttercookies.trainsystem.ctc.CTCModule;
import com.peanutbuttercookies.trainsystem.ctc.CTCSection;
import com.peanutbuttercookies.trainsystem.ctc.CTCTrain;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;

public class CTCModuleUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6499144261081785066L;
	private static final Vector<String> uses = new Vector<String>(Arrays.asList(new String[] {
			"Dispatch",
			"Mark for Repair",
			"Change switch",
			"Set schedule"
	}));

	private CTCModuleInterface module;

	private JTextField speed;
	private JTabbedPane lineTabs;
	private JComboBox<String> usesCombo;
	private JButton browse;
	private String selectedFile = null;
	
	public static void main(String[] args) {
		CTCModule ctc = new CTCModule();
		CTCModuleUI ui = new CTCModuleUI(ctc);
	}
	
	
	public CTCModuleUI(CTCModuleInterface module) {
		super("CTCModule");
		this.module = module;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(600, 600));
		lineTabs = new JTabbedPane(JTabbedPane.TOP);
		lineTabs.setLayout(new BoxLayout(lineTabs, BoxLayout.Y_AXIS));
		lineTabs.setPreferredSize(new Dimension(600, 600));
		
		usesCombo = new JComboBox<String>(uses);
		speed = new JTextField();
		speed.setPreferredSize(new Dimension(150, 30));
		initBrowse();

		setContentPane(lineTabs);
		pack();
		setLocationByPlatform(true);
		setVisible(true);
	}
	
	private void initBrowse() {
		browse = new JButton("Browse");
	    browse.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ae) {
	          JFileChooser chooser = new JFileChooser();
	          JFrame frame = new JFrame("File Chooser");
	          frame.setPreferredSize(new Dimension(400, 400));
	          chooser.setPreferredSize(new Dimension(400, 400));
	          int option = chooser.showOpenDialog(frame);
	          frame.setVisible(true);;
	          if (option == JFileChooser.APPROVE_OPTION) {
	            selectedFile = chooser.getSelectedFile().getAbsolutePath();
	          }
	        }
	      });
	}
	
	public void addLine(String line) {
		//top panel
		JScrollPane scroll1 = new JScrollPane();
		JScrollPane scroll2 = new JScrollPane();
		JTable blocks = new JTable();
		blocks.setModel(module.newBlockModel(line, blocks));
		JTable trains = new JTable();
		trains.setModel(module.newTrainModel(line, trains));
		JPanel top = new JPanel();
		scroll1.add(trains);
		scroll2.add(blocks);
		top.add(scroll1);
		top.add(scroll2);

		JPanel middle = new JPanel();
		JPanel bottom = new JPanel();
		JComboBox<CTCTrain> trainCBox = new JComboBox<CTCTrain>(module.newTrainCombo(line));
		JComboBox<CTCSection> sectionCBox = new JComboBox<CTCSection>(module.newSectionCombo(line));
		JComboBox<CTCBlock> blockCBox = new JComboBox<CTCBlock>(module.newBlockCombo(line, sectionCBox.getItemAt(0)));
		JButton button = new JButton("Send");
		button.addActionListener(this);
		trainCBox.setPreferredSize(new Dimension(150, 30));
		blockCBox.setPreferredSize(new Dimension(150, 30));
		middle.add(trainCBox);
		middle.add(blockCBox);
		middle.add(sectionCBox);
		bottom.add(speed);
		bottom.add(usesCombo);
		bottom.add(button);
		JPanel panel = new JPanel();
		
		top.setPreferredSize(new Dimension(600,500));
		middle.setPreferredSize(new Dimension(600,50));
		bottom.setPreferredSize(new Dimension(600,50));
		panel.add(top);
		panel.add(bottom);
		lineTabs.addTab(line, panel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String line = lineTabs.getTitleAt(lineTabs.getSelectedIndex());
		String use = usesCombo.getItemAt(usesCombo.getSelectedIndex());
		module.perform(line, use, selectedFile, speed.getText());

	}

}
