package com.peanutbuttercookies.trainsystem.ctc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;

public class ComponentContainer extends JPanel implements ActionListener {

	private static final long serialVersionUID = -2735446569205978989L;

	private static int width = 900;
	private static final Dimension TAB_DIM = new Dimension(width / 3, 450);
	private static final Dimension BUTTON_DIM = new Dimension(width / 4, 30);
	private static final Dimension COMBO_DIM = new Dimension(width / 4, 30);
	private static final Dimension ONE_DIM = new Dimension(width, 450);
	private static final Dimension SECONDARY_DIM = new Dimension(width, 50);

	private static final Vector<String> uses = new Vector<String>(Arrays.asList(
			new String[] { "Dispatch", "Mark for Repair", "Engage RR Crossing", "Change Switch", "Set Schedule" }));

	private CTCModuleInterface module;
	private String line;

	// Shared components
	private JComboBox<String> usesCombo;
	private JButton button;

	// Dispatch panel and components
	private JPanel dispatchPanel;
	private JComboBox<CTCSection> allSections;
	private Map<CTCSection, DefaultComboBoxModel<Integer>> allBlocks;
	private JTextField speed;
	private JComboBox<CTCTrain> trainCBox;
	private JComboBox<Integer> blockCBox;

	// Repair panel 
	private JPanel repairPanel;
	private JComboBox<CTCSection> allSections2;
	private Map<CTCSection, DefaultComboBoxModel<Integer>> allBlocks2;
	private JComboBox<Integer> blockCBox2;

	// Switch panel and components
	private JPanel switchPanel;
	private JComboBox<Integer> switches;
	private Map<Integer, DefaultComboBoxModel<Integer>> switchDests;
	private JComboBox<Integer> switchDestCombo;

	// Schedule panel and components
	private JPanel schedulePanel;
	private JTextField fileDisplay;
	private JButton browse;
	private String selectedFile;

	// RR Crossing panel and components
	private JPanel rrPanel;
	private JComboBox<CTCSection> rrSections;
	private Map<CTCSection, DefaultComboBoxModel<Integer>> rrBlocks;

	public ComponentContainer(String line, CTCModuleInterface module) {
		// Initialize shared panels/components
		this.line = line;
		this.module = module;
		setPreferredSize(TAB_DIM);

		JTable blocks = new JTable(module.newBlockModel(line));
		JTable trains = new JTable(module.newTrainModel(line));
		JTable schedule = new JTable(module.newScheduleModel(line));
		JScrollPane scroll1 = new JScrollPane(trains);
		JScrollPane scroll2 = new JScrollPane(blocks);
		JScrollPane scroll3 = new JScrollPane(schedule);

		JPanel one = new JPanel();
		one.setLayout(new BoxLayout(one, BoxLayout.X_AXIS));
		one.setPreferredSize(ONE_DIM);

		one.add(scroll1);
		one.add(scroll2);
		one.add(scroll3);

		add(one);

		usesCombo = new JComboBox<String>(uses);
		usesCombo.setPreferredSize(COMBO_DIM);
		usesCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				remove(1);
				switch ((String) usesCombo.getSelectedItem()) {
				case "Dispatch":
					add(dispatchPanel, 1);
					break;
				case "Set Schedule":
					add(schedulePanel, 1);
					break;
				case "Change Switch":
					add(switchPanel, 1);
					break;
				case "Mark for Repair":
					add(repairPanel, 1);
					break;
				default:
					add(dispatchPanel, 1);
				}
				revalidate();
			}
		});

		button = new JButton("Send");
		button.addActionListener(this);
		button.setPreferredSize(BUTTON_DIM);
		JPanel sharedPanel = new JPanel();
		sharedPanel.setPreferredSize(SECONDARY_DIM);
		sharedPanel.setLayout(new BoxLayout(sharedPanel, BoxLayout.X_AXIS));

		sharedPanel.add(usesCombo);
		sharedPanel.add(button);

		// Init dispatch panel and components
		dispatchPanel = new JPanel();
		dispatchPanel.setLayout(new BoxLayout(dispatchPanel, BoxLayout.X_AXIS));
		dispatchPanel.setPreferredSize(SECONDARY_DIM);

		DefaultComboBoxModel<CTCTrain> trainComboModel = module.newTrainCombo(line);
		trainCBox = new JComboBox<CTCTrain>(trainComboModel);
		DefaultComboBoxModel<CTCSection> sectionModel = module.newSectionCombo(line);
		allSections = new JComboBox<CTCSection>(sectionModel);
		allBlocks = new HashMap<CTCSection, DefaultComboBoxModel<Integer>>();
		allSections.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				blockCBox.setModel(allBlocks.get((allSections).getItemAt(0)));
			}
		});

		for (int i = 0; i < sectionModel.getSize(); i++) {
			CTCSection section = sectionModel.getElementAt(i);
			allBlocks.put(section, module.newBlockCombo(line, section));
		}

		blockCBox = new JComboBox<Integer>(allBlocks.get((CTCSection) allSections.getItemAt(0)));

		speed = new JTextField();
		speed.setPreferredSize(COMBO_DIM);

		dispatchPanel.add(trainCBox);
		dispatchPanel.add(allSections);
		dispatchPanel.add(blockCBox);
		dispatchPanel.add(speed);

		// Switch panel and components init
		switchPanel = new JPanel();
		switchPanel.setPreferredSize(SECONDARY_DIM);
		switchPanel.setLayout(new BoxLayout(switchPanel, BoxLayout.X_AXIS));

		DefaultComboBoxModel<Integer> switchModel = module.newSwitchCombo(line);
		switchDests = new LinkedHashMap<Integer, DefaultComboBoxModel<Integer>>();
		switches = new JComboBox<Integer>(switchModel);
		switches.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				switchDestCombo.setModel(switchDests.get((Integer) switches.getItemAt(0)));
			}
		});

		for (int i = 0; i < switchModel.getSize(); i++) {
			Integer sw = switchModel.getElementAt(i);
			switchDests.put(sw, module.newSwitchDestCombo(line, sw));
		}

		if (switchModel.getSize() > 0) {
			switchDestCombo = new JComboBox<Integer>(switchDests.get(switchModel.getElementAt(0)));
		} else {
			switchDestCombo = new JComboBox<Integer>();
		}

		switchPanel.add(switches);
		switchPanel.add(switchDestCombo);

		// Schedule panel and components init
		schedulePanel = new JPanel();
		schedulePanel.setPreferredSize(SECONDARY_DIM);
		schedulePanel.setLayout(new BoxLayout(schedulePanel, BoxLayout.X_AXIS));
		browse = new JButton("Browse");
		browse.setPreferredSize(BUTTON_DIM);
		browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser chooser = new JFileChooser();
				JFrame frame = new JFrame("File Chooser");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setPreferredSize(new Dimension(400, 400));
				chooser.setPreferredSize(new Dimension(400, 400));
				int option = chooser.showOpenDialog(frame);
				frame.setVisible(true);
				;
				if (option == JFileChooser.APPROVE_OPTION) {
					selectedFile = chooser.getSelectedFile().getAbsolutePath();
					fileDisplay.setText(selectedFile);
					frame.dispose();
				}
			}
		});

		fileDisplay = new JTextField(20);
		fileDisplay.setEditable(false);
		schedulePanel.add(fileDisplay);
		schedulePanel.add(browse);

		// Mark for repair panel and component init
		repairPanel = new JPanel();
		repairPanel.setPreferredSize(SECONDARY_DIM);
		sectionModel = module.newSectionCombo(line);
		allSections2 = new JComboBox<CTCSection>(sectionModel);
		allBlocks2 = new HashMap<CTCSection, DefaultComboBoxModel<Integer>>();
		allSections2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				blockCBox2.setModel(allBlocks.get((allSections).getItemAt(0)));
			}
		});
		blockCBox2 = new JComboBox<Integer>();
		repairPanel.add(allSections2);
		repairPanel.add(blockCBox2);
		
		// RR Crossing panel and components TODO
		

		// Add all to panel
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(one);
		add(dispatchPanel);
		add(sharedPanel);

	}
	
	public String getLine() {
		return line;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch ((String) usesCombo.getSelectedItem()) {
		case "Dispatch":
			int speedInt = 0;
			try {
				speedInt = Integer.parseInt(speed.getText().replaceAll("[^\\d]", ""));
			} catch (Exception e) {
				return;
			}
			speedInt = (int) (1609.34 * speedInt / 3600); 
			module.dispatch(line, speedInt, ((CTCTrain)trainCBox.getSelectedItem()).getHead(),
					(Integer) blockCBox.getSelectedItem());
		}
	}

}
