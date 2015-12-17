package com.peanutbuttercookies.trainsystem.trackmodel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;

public class TrackModelUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5053069285583258597L;
	private JPanel contentPane;
	private JSlider slider;
	private LinkedList<Block> line;
	private LinkedList<Line> track;
	private int curView;
	private Block curViewBlock;
	private int curLine;

	private TrackModelInterface module;
	private DefaultListModel<String> infoList;
	private DefaultListModel<Integer> blockList;
	private DefaultListModel<String> sectionList;
	private DefaultListModel<String> lineList;
	private JList list;
	private JList list_1;
	private JList list_2;
	private JList list_3;
	private JLabel lblTemperatureController;
	private boolean validUser;

	/**
	 * Create the frame.
	 */
	public TrackModelUI(TrackModelInterface module) throws IOException {
		super("TrackModel");
		this.module = module;
		module.setUI(this);
		line = new LinkedList<Block>();
		track = new LinkedList<Line>();
		int curLine = 0;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 653, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		validUser = false;

		JTextPane textPane = new JTextPane();

		contentPane = new JPanel();
		setContentPane(contentPane);

		sectionList = new DefaultListModel<String>();
		blockList = new DefaultListModel<Integer>();
		infoList = new DefaultListModel<String>();
		lineList = new DefaultListModel<String>();

		slider = new JSlider();
		slider.setMaximum(120);
		slider.setMinimum(-20);
		if (curViewBlock != null) {
			slider.setValue(curViewBlock.getTemp());
		}
		lblTemperatureController = new JLabel("Temperature Controller: " + slider.getValue());

		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (curViewBlock != null) {
					curViewBlock.setTemp(slider.getValue());
					lblTemperatureController.setText("Temperature Controller: " + slider.getValue());
				}
			}

		});

		JScrollPane scrollPane = new JScrollPane(list_1);

		JScrollPane scrollPane_1 = new JScrollPane(list_2);

		JScrollPane scrollPane_2 = new JScrollPane(list_3);

		JScrollPane scrollPane_3 = new JScrollPane(list);

		list_1 = new JList(sectionList);
		scrollPane.setViewportView(list_1);

		list = new JList(lineList);
		scrollPane_3.setViewportView(list);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("XLSX files", "xlsx");
				chooser.setFileFilter(filter);
				int ret = chooser.showOpenDialog(null);
				if (ret == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					String filename = file.getAbsolutePath();
					try {
						module.fileRead(filename);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					;
					initDisplay(0);
				}
			}
		});

		JButton btnBreakBlock = new JButton("Break Block");
		btnBreakBlock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				line.get(curView).setBlockOccupation(true);
				update(curView);
			}
		});

		JButton btnSwitchEngage = new JButton("Switch Engage");
		btnSwitchEngage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				line.get(curView).setSwitchEngagement();
				update(curView);
			}
		});

		JButton btnFixBlock = new JButton("Fix Block");
		btnFixBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				line.get(curView).setBlockOccupation(false);
				update(curView);
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 80,
												GroupLayout.PREFERRED_SIZE)
										.addGap(20)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 80,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 80,
												GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(scrollPane_2,
										GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
						.addGroup(
								gl_contentPane.createSequentialGroup()
										.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 102,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnBrowse).addGap(20)
										.addComponent(lblTemperatureController)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(slider, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnFixBlock).addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnBreakBlock)
										.addContainerGap())
								.addGroup(
										gl_contentPane
												.createSequentialGroup().addComponent(btnSwitchEngage,
														GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, 8)
												.addContainerGap())))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnBrowse))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTemperatureController)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnBreakBlock).addComponent(slider, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(scrollPane_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 377,
												Short.MAX_VALUE)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 377,
										Short.MAX_VALUE)
								.addGroup(Alignment.LEADING,
										gl_contentPane.createParallelGroup(Alignment.BASELINE)
												.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 377,
														Short.MAX_VALUE)
												.addComponent(btnFixBlock))
										.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 377,
												Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(34).addComponent(btnSwitchEngage)
								.addContainerGap()))));

		list_2 = new JList(blockList);
		scrollPane_1.setViewportView(list_2);

		list_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				Integer blockVal = (Integer) list_2.getSelectedValue();
				if (blockVal.equals(list_2.getSelectedValue())) {
					update(blockVal - 1);
				}
			}
		});
		list_3 = new JList(infoList);
		scrollPane_2.setViewportView(list_3);

		contentPane.setLayout(gl_contentPane);
		setVisible(true);

		list_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < line.size(); i++) {
					if (line.get(i).getSection().equals(list_1.getSelectedValue())) {
						update(i);
					}
				}
			}
		});

		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < track.size(); i++) {
					if (track.get(i).getAllBlocks().get(0).getLine().equals(list.getSelectedValue())) {
						initDisplay(i);
						break;
					}
				}
			}
		});
	}

	private void loadTrack(LinkedList<Block> newLine) {
		line = newLine;
	}

	public void initDisplay(int index) {
		this.track = module.getTrack();
		this.line = track.get(index).getAllBlocks();

		infoList.clear();
		lineList.clear();
		sectionList.clear();
		blockList.clear();
		curView = 0;
		curViewBlock = line.get(0);
		slider.setValue(line.get(0).getTemp());
		infoList.addElement("Line " + line.get(0).getLine());
		infoList.addElement("Section " + line.get(0).getSection());
		infoList.addElement("Block " + line.get(0).getBlockNumber());
		infoList.addElement("Block Length " + line.get(0).getBlockLength());
		infoList.addElement("Block Grade " + line.get(0).getBlockGrade());
		infoList.addElement("Speed Limit " + line.get(0).getSpeedLimit());
		infoList.addElement("INFRASTRUCTURE ");
		if (line.get(0).hasStation()) {
			infoList.addElement("Station " + line.get(0).getStationName());
		}
		if (line.get(0).hasRRCrossing()) {
			infoList.addElement("Railroad Crossing");
		}
		if (line.get(0).isUnderground()) {
			infoList.addElement("Railroad Crossing");
		}
		if (line.get(0).getBeacon() != null) {
			infoList.addElement("Beacon: " + line.get(0).getBeacon());
		}
		infoList.addElement("Elevation " + line.get(0).getElevation());
		infoList.addElement("Cumulative Elevation " + line.get(0).getCumulativeElevation());
		if (line.get(0).hasSwitch()) {
			infoList.addElement("Switch: " + line.get(0).getSwitchNum());
		}
		// infoList.addElement("Master Switch: " +
		// line.get(0).getMasterSwitch());
		// infoList.addElement("Direction " + line.get(0).getArrowDirection());
		infoList.addElement("Occupancy " + line.get(0).isBlockOccupied());
		// infoList.addElement("Two Way: " + line.get(0).getTwoWay());
		// infoList.addElement("Next: " +
		// line.get(0).getNext().getBlockNumber());
		// infoList.addElement("Prev: " +
		// line.get(0).getPrev().getBlockNumber());
		/*
		 * infoList.addElement("NEXT POSSIBLE BLOCKS"); LinkedList<Block>
		 * nextBlocks = new LinkedList<Block>(line.get(0).getNextPossible());
		 * String nextPos = ""; for (int i = 0; i < nextBlocks.size(); i++) {
		 * nextPos += nextBlocks.get(i).getBlockNumber() + " "; }
		 * infoList.addElement(nextPos); if (line.get(0).getSwitchList() !=
		 * null) { infoList.addElement("SWITCH LIST"); nextBlocks = new
		 * LinkedList<Block>(line.get(0).getSwitchList()); nextPos = ""; for
		 * (int i = 0; i < nextBlocks.size(); i++) { nextPos +=
		 * nextBlocks.get(i).getBlockNumber() + " "; }
		 * infoList.addElement(nextPos); }
		 */

		for (int i = 0; i < track.size(); i++) {
			if (!lineList.contains(track.get(i).getAllBlocks().get(0).getLine()))
				lineList.addElement("" + track.get(i).getAllBlocks().get(0).getLine());
		}

		for (int i = 0; i < line.size(); i++) {
			if (!lineList.contains(line.get(i).getLine()))
				lineList.addElement("" + line.get(i).getLine());
			if (!sectionList.contains(line.get(i).getSection()))
				sectionList.addElement("" + line.get(i).getSection());
			if (line.get(i).getBlockNumber() != 0)
				blockList.addElement(line.get(i).getBlockNumber());
		}
	}

	public boolean currentView(Integer blockId) {
		Integer blockVal = (Integer) list_2.getSelectedValue();
		if (blockId.equals(blockVal))
			return true;
		else
			return false;
	}

	public void update(int blockNum) {
		infoList.clear();
		curView = blockNum;
		curViewBlock = line.get(blockNum);
		slider.setValue(line.get(blockNum).getTemp());
		infoList.addElement("Line " + line.get(blockNum).getLine());
		infoList.addElement("Section " + line.get(blockNum).getSection());
		infoList.addElement("Block " + line.get(blockNum).getBlockNumber());
		infoList.addElement("Block Length " + line.get(blockNum).getBlockLength());
		infoList.addElement("Block Grade " + line.get(blockNum).getBlockGrade());
		infoList.addElement("Speed Limit " + line.get(blockNum).getSpeedLimit());
		infoList.addElement("INFRASTRUCTURE ");
		if (line.get(blockNum).hasStation()) {
			infoList.addElement("Station " + line.get(blockNum).getStationName());
		}
		if (line.get(blockNum).hasRRCrossing()) {
			infoList.addElement("Railroad Crossing");
		}
		if (line.get(blockNum).isUnderground()) {
			infoList.addElement("Railroad Crossing");
		}
		if (line.get(blockNum).getBeacon() != null) {
			infoList.addElement("Beacon: " + line.get(blockNum).getBeacon());
		}
		infoList.addElement("Elevation " + line.get(blockNum).getElevation());
		infoList.addElement("Cumulative Elevation " + line.get(blockNum).getCumulativeElevation());
		if (line.get(blockNum).hasSwitch()) {
			infoList.addElement("Switch: " + line.get(blockNum).getSwitchNum());
		}
		// infoList.addElement("Master Switch: " +
		// line.get(blockNum).getMasterSwitch());
		// infoList.addElement("Direction " +
		// line.get(blockNum).getArrowDirection());
		infoList.addElement("Occupancy " + line.get(blockNum).isBlockOccupied());
		// infoList.addElement("Two Way: " + line.get(blockNum).getTwoWay());
		// infoList.addElement("Next: " +
		// line.get(blockNum).getNext().getBlockNumber());
		// infoList.addElement("Prev: " +
		// line.get(blockNum).getPrev().getBlockNumber());
		// infoList.addElement("NEXT POSSIBLE BLOCKS");
		/*
		 * LinkedList<Block> nextBlocks = new
		 * LinkedList<Block>(line.get(blockNum).getNextPossible()); String
		 * nextPos = ""; for (int i = 0; i < nextBlocks.size(); i++) { nextPos
		 * += nextBlocks.get(i).getBlockNumber() + " "; }
		 * infoList.addElement(nextPos); if (line.get(blockNum).getSwitchList()
		 * != null) { infoList.addElement("SWITCH LIST"); nextBlocks = new
		 * LinkedList<Block>(line.get(blockNum).getSwitchList()); nextPos = "";
		 * for (int i = 0; i < nextBlocks.size(); i++) { nextPos +=
		 * nextBlocks.get(i).getBlockNumber() + " "; }
		 * infoList.addElement(nextPos); }
		 */

	}
}