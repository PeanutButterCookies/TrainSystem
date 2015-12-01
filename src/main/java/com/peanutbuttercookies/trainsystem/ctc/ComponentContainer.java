package com.peanutbuttercookies.trainsystem.ctc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
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
	private static final Dimension TWO_DIM = new Dimension(width, 50);
	private static final Dimension THREE_DIM = new Dimension(width, 50);
	private static final Dimension FOUR_DIM = new Dimension(width, 50);

	private static final Vector<String> uses = new Vector<String>(
			Arrays.asList(new String[] { "Dispatch", "Mark for Repair", "Change switch", "Set schedule" }));

	private CTCModuleInterface module;
	private DefaultComboBoxModel<CTCTrain> trainModel;
	private DefaultComboBoxModel<CTCSection> sectionModel;
	private Map<CTCSection, DefaultComboBoxModel<CTCBlock>> blockModels;
	private JTextField speed;
	private JComboBox<String> usesCombo;
	private JButton browse;
	private String selectedFile;
	private String line;
	private JTextField fileDisplay;
	private JComboBox<CTCBlock> blockCBox;
	private JComboBox<CTCTrain> trainCBox;

	public ComponentContainer(String line, CTCModuleInterface module) {
		this.line = line;
		this.module = module;
		blockModels = new HashMap<CTCSection, DefaultComboBoxModel<CTCBlock>>();
		initBrowse();
		usesCombo = new JComboBox<String>(uses);
		usesCombo.setPreferredSize(COMBO_DIM);
		speed = new JTextField();
		speed.setPreferredSize(COMBO_DIM);
		setPreferredSize(TAB_DIM);

		JTable blocks = new JTable(module.newBlockModel(line));
		JTable trains = new JTable(module.newTrainModel(line));
		JTable schedule = new JTable(module.newScheduleModel(line));
		JScrollPane scroll1 = new JScrollPane(trains);
		JScrollPane scroll2 = new JScrollPane(blocks);
		JScrollPane scroll3 = new JScrollPane(schedule);

		JPanel one = new JPanel();
		JPanel two = new JPanel();
		JPanel three = new JPanel();
		JPanel four = new JPanel();
		one.setLayout(new BoxLayout(one, BoxLayout.X_AXIS));
		two.setLayout(new BoxLayout(two, BoxLayout.X_AXIS));
		three.setLayout(new BoxLayout(three, BoxLayout.X_AXIS));
		four.setLayout(new BoxLayout(four, BoxLayout.X_AXIS));
		one.setPreferredSize(ONE_DIM);
		two.setPreferredSize(TWO_DIM);
		three.setPreferredSize(THREE_DIM);
		four.setPreferredSize(FOUR_DIM);

		one.add(scroll1);
		one.add(scroll2);
		one.add(scroll3);
		fileDisplay = new JTextField(20);
		fileDisplay.setEditable(false);
		two.add(fileDisplay);
		two.add(browse);

		trainModel = module.newTrainCombo(line);
		sectionModel = module.newSectionCombo(line);
		trainCBox = new JComboBox<CTCTrain>(module.newTrainCombo(line));
		JComboBox<CTCSection> sectionCBox = new JComboBox<CTCSection>(module.newSectionCombo(line));

		for (int i = 0; i < sectionModel.getSize(); i++) {
			CTCSection section = sectionModel.getElementAt(i);
			blockModels.put(section, module.newBlockCombo(line, section));
		}
		blockCBox = new JComboBox<CTCBlock>(blockModels.get(sectionModel.getSelectedItem()));
		sectionCBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				blockCBox.setModel(blockModels.get((CTCSection) sectionCBox.getSelectedItem()));
			}
		});

		JButton button = new JButton("Send");
		button.addActionListener(this);

		trainCBox.setPreferredSize(COMBO_DIM);
		blockCBox.setPreferredSize(COMBO_DIM);
		sectionCBox.setPreferredSize(COMBO_DIM);
		button.setPreferredSize(BUTTON_DIM);

		three.add(trainCBox);
		three.add(sectionCBox);
		three.add(blockCBox);
		four.add(speed);
		four.add(usesCombo);
		four.add(button);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(one);
		add(two);
		add(three);
		add(four);

	}

	public String getLine() {
		return line;
	}

	private void initBrowse() {
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
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch ((String) usesCombo.getSelectedItem()) {
		case "Dispatch":
			module.dispatch(line, speed.getText(), (CTCBlock) blockCBox.getSelectedItem(),
					(CTCTrain) trainCBox.getSelectedItem());
		}
	}
}
