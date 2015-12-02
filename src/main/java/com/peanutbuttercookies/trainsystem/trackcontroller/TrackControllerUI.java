package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;

public class TrackControllerUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -730006420600296656L;
	private JPanel 		contentPane;
	private JTextField 	textField;
	private JTable 		tableVariableDisplay;
	
	private String 						displayedLine=null;
	private String						displayedController=null;
	private LinkedList<Line>			lines;
	private JTable tableSwitches;
	
	/**
	 * Create the frame.
	 */
	public TrackControllerUI() {
		setTitle("Track Controller Module");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		this.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel panelColor = new JPanel();
		panelColor.setBackground(Color.WHITE);
		panelColor.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 622, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panelColor, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(30))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(8)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(40)
							.addComponent(panelColor, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel panel_ControllerSelection = new JPanel();
		panel_ControllerSelection.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JPanel panel_ControllerDisplay = new JPanel();
		panel_ControllerDisplay.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JLabel lblDisplayingController = new JLabel("- Displaying Unidentified Track Controller -");
		lblDisplayingController.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayingController.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblSwitches = new JLabel("- Switches -");
		lblSwitches.setHorizontalAlignment(SwingConstants.CENTER);
		lblSwitches.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JSeparator separator_4 = new JSeparator();
		
		JSeparator separator_5 = new JSeparator();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_panel_ControllerDisplay = new GroupLayout(panel_ControllerDisplay);
		gl_panel_ControllerDisplay.setHorizontalGroup(
			gl_panel_ControllerDisplay.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_ControllerDisplay.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_ControllerDisplay.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_ControllerDisplay.createSequentialGroup()
							.addGroup(gl_panel_ControllerDisplay.createParallelGroup(Alignment.LEADING)
								.addComponent(separator_5, GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE)
								.addComponent(separator_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE)
								.addComponent(lblDisplayingController, GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(gl_panel_ControllerDisplay.createSequentialGroup()
							.addComponent(lblSwitches, GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
							.addGap(8))
						.addGroup(gl_panel_ControllerDisplay.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_ControllerDisplay.createSequentialGroup()
							.addGroup(gl_panel_ControllerDisplay.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE)
								.addComponent(separator_4, GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_panel_ControllerDisplay.setVerticalGroup(
			gl_panel_ControllerDisplay.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_ControllerDisplay.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDisplayingController)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSwitches)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		tableSwitches = new JTable();
		tableSwitches.setRowSelectionAllowed(false);
		tableSwitches.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Line", "Block ID", "Track Controller ID", "Switch No.", "Prev. Block", "Next Block (Disengaged)", "Next Block (Engaged)", "Corresponding Switch Block", "Engaged?"
			}
		));
		scrollPane_1.setViewportView(tableSwitches);
		
		tableVariableDisplay = new JTable();
		scrollPane.setViewportView(tableVariableDisplay);
		tableVariableDisplay.setRowSelectionAllowed(false);
		tableVariableDisplay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableVariableDisplay.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "", "", "", null, "", null, ""},
			},
			new String[] {
				"Line", "Block ID", "Track Controller ID", "Switch ID", "Switch Engaged", "RR Crossing", "Crossing Engaged", "Lights"
			}
		));
		tableVariableDisplay.getColumnModel().getColumn(0).setResizable(false);
		tableVariableDisplay.getColumnModel().getColumn(0).setPreferredWidth(43);
		tableVariableDisplay.getColumnModel().getColumn(1).setResizable(false);
		tableVariableDisplay.getColumnModel().getColumn(1).setPreferredWidth(55);
		tableVariableDisplay.getColumnModel().getColumn(2).setResizable(false);
		tableVariableDisplay.getColumnModel().getColumn(2).setPreferredWidth(116);
		tableVariableDisplay.getColumnModel().getColumn(3).setResizable(false);
		tableVariableDisplay.getColumnModel().getColumn(3).setPreferredWidth(57);
		tableVariableDisplay.getColumnModel().getColumn(4).setResizable(false);
		tableVariableDisplay.getColumnModel().getColumn(4).setPreferredWidth(86);
		tableVariableDisplay.getColumnModel().getColumn(5).setResizable(false);
		tableVariableDisplay.getColumnModel().getColumn(6).setPreferredWidth(101);
		tableVariableDisplay.getColumnModel().getColumn(7).setResizable(false);
		tableVariableDisplay.getColumnModel().getColumn(7).setPreferredWidth(39);
		panel_ControllerDisplay.setLayout(gl_panel_ControllerDisplay);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_ControllerSelection, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_ControllerDisplay, GroupLayout.PREFERRED_SIZE, 1017, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_ControllerDisplay, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
						.addComponent(panel_ControllerSelection, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblTrackControllerSelection = new JLabel("Track Controller");
		lblTrackControllerSelection.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrackControllerSelection.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblWaysideController = new JLabel("Selection");
		lblWaysideController.setHorizontalAlignment(SwingConstants.CENTER);
		lblWaysideController.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JComboBox comboBoxLine_2 = new JComboBox();
		comboBoxLine_2.setModel(new DefaultComboBoxModel(new String[] {"All"}));
		displayedLine=(String)comboBoxLine_2.getSelectedItem();
		
		comboBoxLine_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox comboBox=(JComboBox)e.getSource();
				displayedLine = (String)comboBox.getSelectedItem();
				
				switch(displayedLine.toLowerCase()){
				case "red":{
					panelColor.setBackground(Color.red);
				}break;
				case "blue":{
					panelColor.setBackground(Color.blue);
				}break;
				case "green":{
					panelColor.setBackground(Color.green);
				}break;
				case "yellow":{
					panelColor.setBackground(Color.yellow);
				}break;
				case "orange":{
					panelColor.setBackground(Color.orange);
				}break;
				case "purple":{
					panelColor.setBackground(Color.magenta);
				}break;
				case "violet":{
					panelColor.setBackground(Color.magenta);
				}break;
				case "gray":{
					panelColor.setBackground(Color.darkGray);
				}break;
				case "black":{
					panelColor.setBackground(Color.black);
				}break;
				default:{
					panelColor.setBackground(Color.white);
				}
				}
			}
		});
		
		JLabel lblSelectLine = new JLabel("Select Line");
		lblSelectLine.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectLine.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnPrevLine = new JButton("Prev.");
		
		JButton btnNextLine = new JButton("Next");
		
		JLabel lblSelectTrackController = new JLabel("Select Track Controller");
		lblSelectTrackController.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectTrackController.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JComboBox comboBoxWaysideController_2 = new JComboBox();
		comboBoxWaysideController_2.setModel(new DefaultComboBoxModel(new String[] {"All"}));
		displayedController=(String)comboBoxWaysideController_2.getSelectedItem();
		
		comboBoxWaysideController_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox comboBox=(JComboBox)e.getSource();
				displayedController = (String)comboBox.getSelectedItem();
			}
		});
		
		JButton btnPrevController = new JButton("Prev.");
		
		JButton btnNextController = new JButton("Next");
		
		JSeparator separator_2 = new JSeparator();
		
		JLabel lblTrackControllerOptions = new JLabel("Options");
		lblTrackControllerOptions.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTrackControllerOptions.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSeparator separator_3 = new JSeparator();
		
		JLabel lblManuallyEngageSwitch = new JLabel("Manually Engage Switch");
		lblManuallyEngageSwitch.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblManuallyEngageSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		
		JComboBox comboBoxSwitchList = new JComboBox();
		comboBoxSwitchList.setModel(new DefaultComboBoxModel(new String[] {"None"}));
		
		
		//Changes the engagement of the switch selected in comboBoxSwitchList
		ActionListener switchEngage = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JRadioButton button = (JRadioButton) e.getSource();
				String line=null;
				int switchNum;
				
				if(displayedLine.equals("All")){
					Iterator<Line> lineIterator=lines.iterator();
					Line currLine=lineIterator.next();
					String selectedItem=comboBoxSwitchList.getSelectedItem().toString();
					
					while(!(selectedItem.contains(currLine.getLine())) && lineIterator.hasNext()){
						currLine=lineIterator.next();
					}
					line=currLine.getLine();
					
					String[] tokens=selectedItem.split(" ");
					switchNum=Integer.decode(tokens[0]);
					
					//**** How do I know which TC the switch is a part of?
					
				}
				else{
					line=displayedLine;
					switchNum=Integer.decode(comboBoxSwitchList.getSelectedItem().toString());
				}
				
				if(button.getText().equals("Engage")){
					
				}
			}
		};
		
		ButtonGroup group = new ButtonGroup();
		JRadioButton rdbtnEngage = new JRadioButton("Engage");	
		JRadioButton rdbtnDisengage = new JRadioButton("Disengage");
		rdbtnEngage.addActionListener(switchEngage);
		rdbtnDisengage.addActionListener(switchEngage);
		group.add(rdbtnEngage);
		group.add(rdbtnDisengage);
		rdbtnEngage.setEnabled(false);
		rdbtnDisengage.setEnabled(false);
		
		
		comboBoxSwitchList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox comboBox=(JComboBox)e.getSource();
				String selected = (String)comboBox.getSelectedItem();
				if(selected.equals("None")){
					rdbtnEngage.setEnabled(false);
					rdbtnDisengage.setEnabled(false);
				}
				else{
					rdbtnEngage.setEnabled(true);
					rdbtnDisengage.setEnabled(true);
				}
			}
		});
		
		GroupLayout gl_panel_ControllerSelection = new GroupLayout(panel_ControllerSelection);
		gl_panel_ControllerSelection.setHorizontalGroup(
			gl_panel_ControllerSelection.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_ControllerSelection.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_ControllerSelection.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnDisengage)
						.addComponent(rdbtnEngage)
						.addComponent(comboBoxSwitchList, 0, 153, Short.MAX_VALUE)
						.addComponent(separator_2, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
						.addComponent(comboBoxLine_2, 0, 153, Short.MAX_VALUE)
						.addComponent(lblSelectTrackController, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
						.addComponent(lblWaysideController, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
						.addComponent(lblTrackControllerSelection, GroupLayout.PREFERRED_SIZE, 153, Short.MAX_VALUE)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
						.addComponent(comboBoxWaysideController_2, 0, 153, Short.MAX_VALUE)
						.addComponent(lblSelectLine, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_ControllerSelection.createSequentialGroup()
							.addComponent(btnPrevLine)
							.addGap(35)
							.addComponent(btnNextLine, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
						.addGroup(gl_panel_ControllerSelection.createSequentialGroup()
							.addComponent(btnPrevController, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(39)
							.addComponent(btnNextController))
						.addComponent(lblTrackControllerOptions, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
						.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblManuallyEngageSwitch, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_ControllerSelection.setVerticalGroup(
			gl_panel_ControllerSelection.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_ControllerSelection.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTrackControllerSelection, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblWaysideController)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblSelectLine, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxLine_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_ControllerSelection.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPrevLine)
						.addComponent(btnNextLine))
					.addGap(49)
					.addComponent(lblSelectTrackController)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxWaysideController_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_ControllerSelection.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPrevController)
						.addComponent(btnNextController))
					.addGap(13)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTrackControllerOptions, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblManuallyEngageSwitch, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxSwitchList, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnEngage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnDisengage)
					.addContainerGap(96, Short.MAX_VALUE))
		);
		panel_ControllerSelection.setLayout(gl_panel_ControllerSelection);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblEnterPlcProgram = new JLabel("Enter PLC Program File Location:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblEnterTrackController = new JLabel("Track Controller ID:");
		
		JButton btnLoadProgram = new JButton("Load Program");
		
		JLabel lblEnterLineName = new JLabel("Line:");
		
		JComboBox comboBoxWaysideController_1 = new JComboBox();
		
		JComboBox comboBoxLine_1 = new JComboBox();
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Choose PLC file to load");
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setAcceptAllFileFilterUsed(false);
				
				if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					System.out.println("getCurrentDirectory(): "+chooser.getCurrentDirectory());
					System.out.println("getSelectedFile(): "+chooser.getSelectedFile());
				}
				else{
					System.out.println("No Selection");
				}
			}
		});
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEnterPlcProgram)
						.addComponent(lblEnterLineName)
						.addComponent(lblEnterTrackController))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(comboBoxWaysideController_1, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(comboBoxLine_1, Alignment.LEADING, 0, 246, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnBrowse, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
						.addComponent(btnLoadProgram, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterPlcProgram)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowse))
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEnterLineName, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxLine_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEnterTrackController)
								.addComponent(comboBoxWaysideController_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoadProgram)
							.addContainerGap())))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
	
	public void updateVariableTable(){
		
		if(this.displayedLine.equals("All")){
			Iterator<Line> lineIterator=lines.iterator();
			int counter=-1;
			while(lineIterator.hasNext()){
				Line currLine=lineIterator.next();
				Iterator<TrackControllerInterface> tcIterator=currLine.getAllTrackControllers().iterator();
				
				while(tcIterator.hasNext()){
					TrackControllerInterface currTC=tcIterator.next();
					Iterator<Block> blockIterator=currTC.getSection().iterator();
					
					counter=setVariableTableValues(currLine,currTC,blockIterator,counter);
				}
			}
		}
		else{
			Iterator<Line> lineIterator=lines.iterator();
			Line currLine=lineIterator.next();
			while(!currLine.getLine().equals(this.displayedLine) && lineIterator.hasNext()){
				currLine=lineIterator.next();
			}
			
			int counter=-1;
			if(this.displayedController.equals("All")){
				Iterator<TrackControllerInterface> tcIterator=currLine.getAllTrackControllers().iterator();
				
				while(tcIterator.hasNext()){
					TrackControllerInterface currTC=tcIterator.next();
					Iterator<Block> blockIterator=currTC.getSection().iterator();
					
					counter=setVariableTableValues(currLine,currTC,blockIterator,counter);
				}
			}
			else{
				Iterator<TrackControllerInterface> tcIterator = currLine.getAllTrackControllers().iterator();
				TrackControllerInterface currTC=tcIterator.next();
				while(!(Integer.toString(currTC.getControllerId()).equals(this.displayedController))){
					currTC=tcIterator.next();
				}
				
				Iterator<Block> blockIterator=currTC.getSection().iterator();
				setVariableTableValues(currLine,currTC,blockIterator,counter);
				
			}
		}
		updateSwitchesTable();
	}
	
	private int setVariableTableValues(Line currLine, TrackControllerInterface currTC, Iterator<Block> blockIterator, int counter){
		while(blockIterator.hasNext()){
			Block currBlock=blockIterator.next();
			if(currBlock.getBlockNumber()==currTC.getOverlapBlock() && currBlock.getBlockNumber()==currTC.getStartBlock()){
				break;
			}
			counter++;
			tableVariableDisplay.setValueAt(currBlock.getLine(), counter, 0);				//Line
			tableVariableDisplay.setValueAt(currBlock.getBlockNumber(), counter, 1);		//Block ID
			tableVariableDisplay.setValueAt(currTC.getControllerId(), counter, 2);			//Track Controller ID
			tableVariableDisplay.setValueAt(currBlock.getSwitchBlockId(), counter, 3);		//Switch ID
			tableVariableDisplay.setValueAt(currBlock.isSwitchEngaged(), counter, 4);		//Switch Engaged
			tableVariableDisplay.setValueAt(currBlock.hasRRCrossing(), counter, 5);			//RR Crossing
			tableVariableDisplay.setValueAt(currBlock.isRRCrossingEngaged(), counter, 6);	//Crossing Engaged
			tableVariableDisplay.setValueAt("N/A", counter, 7);								//Lights
			
		}
		return counter;
	}
	
	public void updateSwitchesTable(){
		
	}
	
	public void setLines(LinkedList<Line> lines){
		this.lines=lines;
	}
}
