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
import com.peanutbuttercookies.trainsystem.ctc.CTCSection;
import com.peanutbuttercookies.trainsystem.ctc.CTCTrain;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;

public class CTCModuleUI extends JFrame implements ActionListener {

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
	
	public CTCModuleUI(CTCModuleInterface module) {
		super("CTC Module");
		this.module = module;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(600, 600));
		lineTabs = new JTabbedPane();
		
		usesCombo = new JComboBox<String>(uses);
		usesCombo.setPreferredSize(new Dimension(150, 30));
		speed = new JTextField();
		speed.setPreferredSize(new Dimension(150, 30));
		initBrowse();

		add(lineTabs);
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

		JTable blocks = new JTable(module.newBlockModel(line));
		JTable trains = new JTable(module.newTrainModel(line));
		JScrollPane scroll1 = new JScrollPane(trains);
		JScrollPane scroll2 = new JScrollPane(blocks);

		JPanel one = new JPanel();
		JPanel two = new JPanel();
		JPanel three = new JPanel();
		JPanel four = new JPanel();
		one.setLayout(new BoxLayout(one, BoxLayout.X_AXIS));
		two.setLayout(new BoxLayout(two, BoxLayout.X_AXIS));
		three.setLayout(new BoxLayout(three, BoxLayout.X_AXIS));
		four.setLayout(new BoxLayout(four, BoxLayout.X_AXIS));
		one.setPreferredSize(new Dimension(600,450));
		two.setPreferredSize(new Dimension(600,50));
		three.setPreferredSize(new Dimension(600,50));
		four.setPreferredSize(new Dimension(600,50));

		one.add(scroll1);
		one.add(scroll2);
		two.add(browse);

		JComboBox<CTCTrain> trainCBox = new JComboBox<CTCTrain>(module.newTrainCombo(line));
		JComboBox<CTCSection> sectionCBox = new JComboBox<CTCSection>(module.newSectionCombo(line));
		JComboBox<CTCBlock> blockCBox = new JComboBox<CTCBlock>(module.newBlockCombo(line, sectionCBox.getItemAt(0)));
		JButton button = new JButton("Send");
		button.addActionListener(this);

		Dimension cBoxDim = new Dimension(150, 30);
		trainCBox.setPreferredSize(cBoxDim);
		blockCBox.setPreferredSize(cBoxDim);
		sectionCBox.setPreferredSize(cBoxDim);
		button.setPreferredSize(cBoxDim);

		three.add(trainCBox);
		three.add(sectionCBox);
		three.add(blockCBox);
		four.add(speed);
		four.add(usesCombo);
		four.add(button);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(one);
		panel.add(two);
		panel.add(three);
		panel.add(four);
		lineTabs.addTab(line, panel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String line = lineTabs.getTitleAt(lineTabs.getSelectedIndex());
		String use = usesCombo.getItemAt(usesCombo.getSelectedIndex());
		module.perform(line, use, selectedFile, speed.getText());
	}

}
