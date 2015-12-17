package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
	private static final long 			serialVersionUID = -730006420600296656L;
	private JPanel 						contentPane;
	private JTextField 					textFieldFileLocation;
	private JTable 						tableVariableDisplay;
	private JTable						tableSwitches;
	private JComboBox<String> 			comboBoxLine_1;
	private JComboBox<String>  			comboBoxLine_2;
	private JComboBox<String> 			comboBoxTrackController_1;
	private JComboBox<String> 			comboBoxTrackController_2;
	private JComboBox<String> 			comboBoxSwitchList;
	
	private HashMap<String,HashMap<String,LinkedList<Block>>> switchList;
	
	private String 						displayedLine=null;
	private String						displayedController=null;
	private LinkedList<String>			displayedSwitches=new LinkedList<String>();
	private List<Line>					lines;
	
	//USERNAME AND PASSWORD FOR LOGIN
	private static final String username="admin";
	private static final String password="password";
	
	public TrackControllerAuthentication authentication = new TrackControllerAuthentication(username,password,this);
	
	
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
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Line", "Block ID", "Track Controller ID", "Switch No.", "Prev. Block", "Next Block (Disengaged)", "Next Block (Engaged)", "Engaged?"
			}
		));
		scrollPane_1.setViewportView(tableSwitches);
		
		tableVariableDisplay = new JTable();
		scrollPane.setViewportView(tableVariableDisplay);
		tableVariableDisplay.setRowSelectionAllowed(false);
		tableVariableDisplay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableVariableDisplay.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "", "", "", null, null, "", null, ""},
			},
			new String[] {
				"Line", "Block ID", "Track Controller ID", "Switch ID", "Block Occupied", "Switch Engaged", "RR Crossing", "Crossing Engaged", "Lights"
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
		tableVariableDisplay.getColumnModel().getColumn(4).setPreferredWidth(85);
		tableVariableDisplay.getColumnModel().getColumn(5).setResizable(false);
		tableVariableDisplay.getColumnModel().getColumn(5).setPreferredWidth(86);
		tableVariableDisplay.getColumnModel().getColumn(6).setResizable(false);
		tableVariableDisplay.getColumnModel().getColumn(7).setPreferredWidth(101);
		tableVariableDisplay.getColumnModel().getColumn(8).setResizable(false);
		tableVariableDisplay.getColumnModel().getColumn(8).setPreferredWidth(39);
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
		
		JLabel lblTrackControllerSelection1 = new JLabel("Track Controller");
		lblTrackControllerSelection1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrackControllerSelection1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		JLabel lblTrackControllerSelection2 = new JLabel("Selection");
		lblTrackControllerSelection2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrackControllerSelection2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		/*
		 ******************************************************************************
		 * LINE DISPLAY SELECTION
		 ******************************************************************************
		 */
		
		comboBoxLine_2 = new JComboBox<String> ();
		comboBoxLine_2.setModel(new DefaultComboBoxModel<String> (new String[] {"All"}));
		comboBoxLine_2.setSelectedIndex(0);
		displayedLine=(String)comboBoxLine_2.getSelectedItem();
		
		comboBoxLine_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<String>  comboBox=(JComboBox<String>)e.getSource();
				displayedLine = (String)comboBox.getSelectedItem();
				
				if(comboBox.getSelectedIndex()!=-1){
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
				
				
				updateSwitchComboBox();
				updateVariableTable();
			}
		});
		
		JLabel lblSelectLine = new JLabel("Select Line");
		lblSelectLine.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectLine.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnPrevLine = new JButton("Prev.");
		btnPrevLine.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int current = comboBoxLine_2.getSelectedIndex();
				if(current!=0){
					comboBoxLine_2.setSelectedIndex(current-1);
				}
				else{
					comboBoxLine_2.setSelectedIndex(comboBoxLine_2.getItemCount()-1);
				}
				displayedLine=comboBoxLine_2.getSelectedItem().toString();
				updateSwitchComboBox();
				updateVariableTable();
			}
		});
		
		JButton btnNextLine = new JButton("Next");
		btnNextLine.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int current = comboBoxLine_2.getSelectedIndex();
				if(current!=comboBoxLine_2.getItemCount()-1){
					comboBoxLine_2.setSelectedIndex(current+1);
				}
				else{
					comboBoxLine_2.setSelectedIndex(0);
				}
				displayedLine=comboBoxLine_2.getSelectedItem().toString();
				updateSwitchComboBox();
				updateVariableTable();
			}
		});
		
		/*
		 ******************************************************************************
		 * TRACK CONTROLLER DISPLAY SELECTION
		 ******************************************************************************
		 */
		
		JLabel lblSelectTrackController = new JLabel("Select Track Controller");
		lblSelectTrackController.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectTrackController.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		comboBoxTrackController_2 = new JComboBox<String> ();
		comboBoxTrackController_2.setModel(new DefaultComboBoxModel(new String[] {"All", "1", "2"}));
		comboBoxTrackController_2.setSelectedIndex(0);
		displayedController=(String)comboBoxTrackController_2.getSelectedItem();
		
		comboBoxTrackController_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<String>  comboBox=(JComboBox<String> )e.getSource();
				displayedController = (String)comboBox.getSelectedItem();
				updateSwitchComboBox();
				updateVariableTable();
			}
		});
		
		JButton btnPrevController = new JButton("Prev.");
		btnPrevController.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!displayedLine.equals("All")){
					int current = comboBoxTrackController_2.getSelectedIndex();
					if(current!=0){
						comboBoxTrackController_2.setSelectedIndex(current-1);
					}
					else{
						comboBoxTrackController_2.setSelectedIndex(comboBoxTrackController_2.getItemCount()-1);
					}
					displayedController=comboBoxTrackController_2.getSelectedItem().toString();
					updateSwitchComboBox();
					updateVariableTable();
				}
			}
		});
		
		JButton btnNextController = new JButton("Next");
		btnNextController.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!displayedLine.equals("All")){
					int current = comboBoxTrackController_2.getSelectedIndex();
					if(current!=comboBoxTrackController_2.getItemCount()-1){
						comboBoxTrackController_2.setSelectedIndex(current+1);
					}
					else{
						comboBoxTrackController_2.setSelectedIndex(0);
					}
					displayedController=comboBoxTrackController_2.getSelectedItem().toString();
					updateSwitchComboBox();
					updateVariableTable();
				}
			}
		});
		
		JSeparator separator_2 = new JSeparator();
		
		JLabel lblTrackControllerOptions = new JLabel("Options");
		lblTrackControllerOptions.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTrackControllerOptions.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSeparator separator_3 = new JSeparator();
		
		/*
		 ******************************************************************************
		 * MANUAL SWITCHING
		 ******************************************************************************
		 */
		
		JLabel lblManuallyEngageSwitch = new JLabel("Manually Engage Switch");
		lblManuallyEngageSwitch.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblManuallyEngageSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		
		comboBoxSwitchList = new JComboBox<String> ();
		comboBoxSwitchList.setModel(new DefaultComboBoxModel(new String[] {"None"}));
		//comboBoxSwitchList.setSelectedItem("None");
		comboBoxSwitchList.setSelectedIndex(0);
		
		//Changes the engagement of the switch selected in comboBoxSwitchList
		ActionListener switchEngage = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JRadioButton button = (JRadioButton) e.getSource();
				String selectedItem=comboBoxSwitchList.getSelectedItem().toString();
				boolean engagement=false;
				
				if(button.getText().equals("Engage")){
					engagement=true;
				}
				else if(button.getText().equals("Disengage")){
					engagement=false;
				}
				
				String[] tokens=selectedItem.split(" ");
				String switchName=tokens[1];
				
				Line correctLine=null;
				
				if(displayedLine.equals("All")){
					Iterator<Line> lineIterator=lines.iterator();
					Line currLine=lineIterator.next();
					
					while(!(selectedItem.contains(currLine.getLine())) && lineIterator.hasNext()){
						currLine=lineIterator.next();
					}
					correctLine=currLine;
				}
				else{
					Iterator<Line> lineIterator=lines.iterator();
					Line currLine=lineIterator.next();
					while(!currLine.getLine().equals(displayedLine) && lineIterator.hasNext()){
						currLine=lineIterator.next();
					}
					correctLine=currLine;
				}
				
				TrackControllerInterface correctTC =findCorrectTC(correctLine,switchName);
				
				//TEST ONLY
				System.out.println(">>Checkpoint: switchEngage: switchName="+switchName+"\tTCLine="+correctTC.getLine()+"\tTCID="+correctTC.getControllerId());
				//TEST ONLY
				
				correctTC.engageSwitch(switchName, !engagement);
				updateVariableTable();
			}
		};
		
		
		ButtonGroup switchGroup = new ButtonGroup();
		JRadioButton rdbtnEngageSwitch = new JRadioButton("Engage");	
		JRadioButton rdbtnDisengageSwitch = new JRadioButton("Disengage");
		rdbtnEngageSwitch.addActionListener(switchEngage);
		rdbtnDisengageSwitch.addActionListener(switchEngage);
		switchGroup.add(rdbtnEngageSwitch);
		switchGroup.add(rdbtnDisengageSwitch);
		rdbtnEngageSwitch.setEnabled(false);
		rdbtnDisengageSwitch.setEnabled(false);
		
		comboBoxSwitchList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<String>  comboBox=(JComboBox<String>)e.getSource();
				if(comboBox.getSelectedIndex()!=-1){
					String selected = comboBox.getSelectedItem().toString();
					if(selected.equals("None")){
						rdbtnEngageSwitch.setEnabled(false);
						rdbtnDisengageSwitch.setEnabled(false);
					}
					else{
						rdbtnEngageSwitch.setEnabled(true);
						rdbtnDisengageSwitch.setEnabled(true);
						
						String[] tokens=selected.split(" ");
						String switchName=tokens[1];
						
						Line correctLine=null;
						
						if(displayedLine.equals("All")){
							Iterator<Line> lineIterator=lines.iterator();
							Line currLine=lineIterator.next();
							
							while(!(selected.contains(currLine.getLine())) && lineIterator.hasNext()){
								currLine=lineIterator.next();
							}
							correctLine=currLine;
						}
						else{
							Iterator<Line> lineIterator=lines.iterator();
							Line currLine=lineIterator.next();
							while(!currLine.getLine().equals(displayedLine) && lineIterator.hasNext()){
								currLine=lineIterator.next();
							}
							correctLine=currLine;
						}
						
						TrackControllerInterface correctTC =findCorrectTC(correctLine,switchName);
						
						Iterator<Block> switchBlockIterator = switchList.get(correctLine.getLine()).get(Integer.toString(correctTC.getControllerId())).iterator();
						while(switchBlockIterator.hasNext()){
							Block switchBlock=switchBlockIterator.next();
							if(Integer.toString(switchBlock.getSwitchNum()).equals(switchName)){
								rdbtnEngageSwitch.setSelected(switchBlock.isSwitchEngaged());
								break;
							}
						}
					}
				}		
			}
		});
		
		JLabel lblManuallyEngageRr_1 = new JLabel("Manually Engage");
		lblManuallyEngageRr_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblManuallyEngageRr_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblManuallyEngageRr_2 = new JLabel("RR Crossing");
		lblManuallyEngageRr_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblManuallyEngageRr_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JComboBox comboBoxRR = new JComboBox();
		comboBoxRR.setModel(new DefaultComboBoxModel(new String[] {"None"}));
		
		ButtonGroup rrGroup = new ButtonGroup();
		JRadioButton rdbtnEngageRR = new JRadioButton("Engage");
		JRadioButton rdbtnDisengageRR = new JRadioButton("Disengage");
		rrGroup.add(rdbtnEngageRR);
		rrGroup.add(rdbtnDisengageRR);
		rdbtnEngageRR.setEnabled(false);
		rdbtnDisengageRR.setEnabled(false);
		
		ActionListener rrEngageAction= new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JRadioButton switchBtn=(JRadioButton)e.getSource();
				String selectedSwitch=comboBoxRR.getSelectedItem().toString();
				
				/*
				if(switchMap.containsKey(selectedSwitch)){
					if(switchBtn.getText().equals("Engage")){
						switchMap.get(selectedSwitch).engageSwitch(selectedSwitch, true);
					}
					else if(switchBtn.getText().equals("Disengage")){
						switchMap.get(selectedSwitch).engageSwitch(selectedSwitch, false);
					}
				}
				*/
			}
		};
		rdbtnEngageRR.addActionListener(rrEngageAction);
		rdbtnEngageRR.addActionListener(rrEngageAction);
		
		comboBoxRR.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JComboBox<String>  comboBox=(JComboBox<String>)e.getSource();
				String selected = (String)comboBox.getSelectedItem();
				if(selected.equals("None")){
					rdbtnEngageRR.setEnabled(false);
					rdbtnDisengageRR.setEnabled(false);
				}
				else{
					rdbtnEngageRR.setEnabled(true);
					rdbtnDisengageRR.setEnabled(true);
				}
			}
		});
		
		GroupLayout gl_panel_ControllerSelection = new GroupLayout(panel_ControllerSelection);
		gl_panel_ControllerSelection.setHorizontalGroup(
			gl_panel_ControllerSelection.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_ControllerSelection.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_ControllerSelection.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_ControllerSelection.createSequentialGroup()
							.addComponent(rdbtnDisengageRR, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel_ControllerSelection.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_ControllerSelection.createSequentialGroup()
								.addComponent(rdbtnEngageRR, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_panel_ControllerSelection.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_ControllerSelection.createSequentialGroup()
									.addComponent(rdbtnDisengageSwitch)
									.addContainerGap())
								.addGroup(gl_panel_ControllerSelection.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_ControllerSelection.createSequentialGroup()
										.addComponent(rdbtnEngageSwitch)
										.addContainerGap())
									.addGroup(gl_panel_ControllerSelection.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel_ControllerSelection.createSequentialGroup()
											.addGroup(gl_panel_ControllerSelection.createParallelGroup(Alignment.TRAILING)
												.addComponent(comboBoxLine_2, 0, 153, Short.MAX_VALUE)
												.addComponent(lblTrackControllerSelection2, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
												.addComponent(lblTrackControllerSelection1, GroupLayout.PREFERRED_SIZE, 153, Short.MAX_VALUE)
												.addComponent(separator, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
												.addComponent(lblSelectLine, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_panel_ControllerSelection.createSequentialGroup()
													.addComponent(btnPrevLine)
													.addGap(35)
													.addComponent(btnNextLine, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
												.addComponent(lblSelectTrackController, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
												.addComponent(comboBoxTrackController_2, 0, 153, Short.MAX_VALUE)
												.addGroup(gl_panel_ControllerSelection.createSequentialGroup()
													.addComponent(btnPrevController, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addGap(39)
													.addComponent(btnNextController))
												.addComponent(separator_2, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
												.addComponent(lblTrackControllerOptions, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
												.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblManuallyEngageSwitch, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
												.addComponent(comboBoxSwitchList, 0, 153, Short.MAX_VALUE)
												.addComponent(lblManuallyEngageRr_1, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
											.addContainerGap())
										.addGroup(gl_panel_ControllerSelection.createSequentialGroup()
											.addGroup(gl_panel_ControllerSelection.createParallelGroup(Alignment.TRAILING)
												.addComponent(comboBoxRR, Alignment.LEADING, 0, 147, Short.MAX_VALUE)
												.addComponent(lblManuallyEngageRr_2, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
											.addGap(16))))))))
		);
		gl_panel_ControllerSelection.setVerticalGroup(
			gl_panel_ControllerSelection.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_ControllerSelection.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTrackControllerSelection1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTrackControllerSelection2)
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
					.addGap(19)
					.addComponent(lblSelectTrackController)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxTrackController_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_ControllerSelection.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNextController)
						.addComponent(btnPrevController))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTrackControllerOptions, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblManuallyEngageSwitch, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxSwitchList, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnEngageSwitch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnDisengageSwitch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblManuallyEngageRr_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblManuallyEngageRr_2, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxRR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnEngageRR)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnDisengageRR)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		panel_ControllerSelection.setLayout(gl_panel_ControllerSelection);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblEnterPlcProgram = new JLabel("Enter PLC Program File Location:");
		
		textFieldFileLocation = new JTextField();
		textFieldFileLocation.setColumns(10);
		
		JLabel lblEnterTrackController = new JLabel("Track Controller ID:");
		
		JButton btnLoadProgram = new JButton("Load Program");
		btnLoadProgram.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(textFieldFileLocation.getText()!=null && comboBoxTrackController_1.getSelectedIndex()!=-1 && comboBoxLine_1.getSelectedIndex()!=-1){
					Iterator<Line> lineIterator=lines.iterator();
					Line currLine=lineIterator.next();
					while(!currLine.getLine().equals(comboBoxLine_1.getSelectedItem().toString()) && lineIterator.hasNext()){
						currLine=lineIterator.next();
					}
					
					Iterator<TrackControllerInterface> tcIterator = currLine.getAllTrackControllers().iterator();
					TrackControllerInterface currTC=tcIterator.next();
					while(!(Integer.toString(currTC.getControllerId()).equals(comboBoxTrackController_1.getSelectedItem().toString())) && tcIterator.hasNext()){
						currTC=tcIterator.next();
					}
					
					currTC.setPLCProgram(textFieldFileLocation.getText());
				}
			}
		});
		
		JLabel lblEnterLineName = new JLabel("Line:");
		
		comboBoxTrackController_1 = new JComboBox<String> ();
		comboBoxTrackController_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		comboBoxTrackController_1.setSelectedIndex(0);
		
		comboBoxLine_1 = new JComboBox<String> ();
		
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
					
					textFieldFileLocation.setText(chooser.getSelectedFile().getPath());
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
						.addComponent(textFieldFileLocation, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(comboBoxTrackController_1, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
						.addComponent(textFieldFileLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
								.addComponent(comboBoxTrackController_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoadProgram)
							.addContainerGap())))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
		setVisible(false);
		authentication.setVisible(true);
	}
	
	/***** End of TrackControllerUI() ******/
	
	
	/***** Start of TrackControllerUI Methods *****/
	
	public void updateVariableTable(){
		
		if(this.displayedLine!=null && this.displayedController!=null && this.displayedLine.equals("All")){
			Iterator<Line> lineIterator=lines.iterator();
			int counter=0;
			while(lineIterator.hasNext()){
				Line currLine=lineIterator.next();
				Iterator<TrackControllerInterface> tcIterator=currLine.getAllTrackControllers().iterator();
				
				while(tcIterator.hasNext()){
					TrackControllerInterface currTC=tcIterator.next();
					
					DefaultTableModel dtm= (DefaultTableModel)tableVariableDisplay.getModel();
					dtm.setRowCount(currTC.getSection().size()+counter);
					/*if(!tcIterator.hasNext()){
						dtm.setRowCount(tableVariableDisplay.getRowCount()-1);
					}*/
					counter=setVariableTableValues(currLine,currTC,counter);
				}
			}
		}
		else if(this.displayedLine!=null && this.displayedController!=null){
			Iterator<Line> lineIterator=lines.iterator();
			Line currLine=lineIterator.next();
			while(!currLine.getLine().equals(this.displayedLine) && lineIterator.hasNext()){
				currLine=lineIterator.next();
			}
			
			
			if(this.displayedController.equals("All")){
				Iterator<TrackControllerInterface> tcIterator=currLine.getAllTrackControllers().iterator();
				
				int counter=0;
				while(tcIterator.hasNext()){
					TrackControllerInterface currTC=tcIterator.next();
					
					DefaultTableModel dtm= (DefaultTableModel)tableVariableDisplay.getModel();
					dtm.setRowCount(currTC.getSection().size()+counter);
					/*if(!tcIterator.hasNext()){
						dtm.setRowCount(tableVariableDisplay.getRowCount()-1);
					}*/
					counter=setVariableTableValues(currLine,currTC,counter);
				}
			}
			else{
				Iterator<TrackControllerInterface> tcIterator = currLine.getAllTrackControllers().iterator();
				TrackControllerInterface currTC=tcIterator.next();
				while(!(Integer.toString(currTC.getControllerId()).equals(this.displayedController)) && tcIterator.hasNext()){
					currTC=tcIterator.next();
				}
				int counter=0;
				DefaultTableModel dtm= (DefaultTableModel)tableVariableDisplay.getModel();
				dtm.setRowCount(currTC.getSection().size());
				setVariableTableValues(currLine,currTC,counter);
				
			}
		}
		this.repaint();
		updateSwitchesTable();
	}
	
	private int setVariableTableValues(Line currLine, TrackControllerInterface currTC, int counter){
		Iterator<Block> blockIterator=currTC.getSection().iterator();
		while(blockIterator.hasNext()){
			Block currBlock=blockIterator.next();
			if(currBlock.getBlockNumber()==currTC.getOverlapBlock() && currBlock.getBlockNumber()==currTC.getStartBlock() && counter+1>tableVariableDisplay.getRowCount()/2){
				continue;
			}
			tableVariableDisplay.setValueAt(currBlock.getLine(), counter, 0);				//Line
			tableVariableDisplay.setValueAt(currBlock.getBlockNumber(), counter, 1);		//Block ID
			tableVariableDisplay.setValueAt(currTC.getControllerId(), counter, 2);			//Track Controller ID
			tableVariableDisplay.setValueAt(currBlock.getSwitchNum(), counter, 3);		//Switch ID
			tableVariableDisplay.setValueAt(currBlock.isBlockOccupied(), counter, 4);		//Block Occupied
			tableVariableDisplay.setValueAt(currBlock.isSwitchEngaged(), counter, 5);		//Switch Engaged
			tableVariableDisplay.setValueAt(currBlock.hasRRCrossing(), counter, 6);			//RR Crossing
			tableVariableDisplay.setValueAt(currBlock.isRRCrossingEngaged(), counter, 7);	//Crossing Engaged
			tableVariableDisplay.setValueAt("N/A", counter, 8);								//Lights
			counter++;
			
		}
		return counter;
	}
	
	public void updateSwitchesTable(){
		if(this.displayedLine!=null && this.displayedController!=null && this.displayedLine.equals("All")){
			Iterator<Line> lineIterator=lines.iterator();
			int counter=0;
			while(lineIterator.hasNext()){
				Line currLine=lineIterator.next();
				Iterator<TrackControllerInterface> tcIterator=currLine.getAllTrackControllers().iterator();
				
				while(tcIterator.hasNext()){
					TrackControllerInterface currTC=tcIterator.next();
					
					DefaultTableModel dtm= (DefaultTableModel)tableSwitches.getModel();
					dtm.setRowCount(currTC.getSwitchList().size()+counter);
					counter=setSwitchTableValues(currLine,currTC,counter);
				}
			}
		}
		else if(this.displayedLine!=null && this.displayedController!=null){
			Iterator<Line> lineIterator=lines.iterator();
			Line currLine=lineIterator.next();
			while(!currLine.getLine().equals(this.displayedLine) && lineIterator.hasNext()){
				currLine=lineIterator.next();
			}
			
			
			if(this.displayedController.equals("All")){
				Iterator<TrackControllerInterface> tcIterator=currLine.getAllTrackControllers().iterator();
				
				int counter=0;
				while(tcIterator.hasNext()){
					TrackControllerInterface currTC=tcIterator.next();
					
					DefaultTableModel dtm= (DefaultTableModel)tableSwitches.getModel();
					dtm.setRowCount(currTC.getSwitchList().size()+counter);
					counter=setSwitchTableValues(currLine,currTC,counter);
				}
			}
			else{
				Iterator<TrackControllerInterface> tcIterator = currLine.getAllTrackControllers().iterator();
				TrackControllerInterface currTC=tcIterator.next();
				while(!(Integer.toString(currTC.getControllerId()).equals(this.displayedController)) && tcIterator.hasNext()){
					currTC=tcIterator.next();
				}
				int counter=0;
				DefaultTableModel dtm= (DefaultTableModel)tableSwitches.getModel();
				dtm.setRowCount(currTC.getSwitchList().size());
				setSwitchTableValues(currLine,currTC,counter);
				
			}
		}
		this.repaint();
	}
	
	public int setSwitchTableValues(Line currLine, TrackControllerInterface currTC, int counter){
		Iterator<Block> blockIterator=currTC.getSection().iterator();
		while(blockIterator.hasNext()){
			Block currBlock=blockIterator.next();
			if(currBlock.getBlockNumber()==currTC.getOverlapBlock() && currBlock.getBlockNumber()==currTC.getStartBlock() && counter+1>tableVariableDisplay.getRowCount()/2 || currBlock.getSwitchNum()==-1 || !currBlock.getMasterSwitch()){
				continue;
			}
			
			Block disengagedBlock=null;
			Iterator<Block> nextPossibleIterator=currBlock.getNextPossible().iterator();
			while(nextPossibleIterator.hasNext()){
				Block nextPossible=nextPossibleIterator.next();
				if(currBlock.getNext()!=null && nextPossible!=null && !currBlock.getNext().equals(nextPossible)){
					disengagedBlock=nextPossible;
				}
			}
			
			tableSwitches.setValueAt(currBlock.getLine(), counter, 0);								//Line
			tableSwitches.setValueAt(currBlock.getBlockNumber(), counter, 1);						//Block ID
			tableSwitches.setValueAt(currTC.getControllerId(), counter, 2);							//Track Controller ID
			tableSwitches.setValueAt(currBlock.getSwitchNum(), counter, 3);						//Switch ID
			if(currBlock.getPrevBlock()!=null){
				tableSwitches.setValueAt(currBlock.getPrevBlock().getBlockNumber(), counter, 4);		//Previous Block
			}
			if(disengagedBlock!=null){
				tableSwitches.setValueAt(disengagedBlock, counter, 5);									//Next Block w/disengaged
			}
			if(currBlock.getNext()!=null){
				tableSwitches.setValueAt(currBlock.getNext(), counter, 6);								//Next Block w/engaged
			}
			tableSwitches.setValueAt(currBlock.isSwitchEngaged(), counter, 7);						//Engaged?
			counter++;
			
		}
		return counter;
	}
	
	public void setLines(List<Line> lines){
		this.lines=lines;
		
		comboBoxLine_1.removeAllItems();
		comboBoxLine_2.removeAllItems();
		comboBoxLine_2.addItem("All");
		
		Iterator<Line> lineIterator=lines.iterator();
		while(lineIterator.hasNext()){
			Line currLine=lineIterator.next();
			comboBoxLine_1.addItem(currLine.getLine());
			comboBoxLine_2.addItem(currLine.getLine());
		}
		
		comboBoxLine_1.setSelectedIndex(0);
		comboBoxLine_2.setSelectedIndex(0);
		
		updateSwitchComboBox();
	}
	
	public void setSwitchList(HashMap<String,HashMap<String,LinkedList<Block>>> switchList){
		this.switchList=switchList;
	}
	
	private void updateSwitchComboBox(){
		displayedSwitches.clear();
		if(this.displayedLine!=null && this.displayedController!=null && this.displayedLine.equals("All")){
			Iterator<Line> lineIterator=lines.iterator();
			while(lineIterator.hasNext()){
				Line currLine=lineIterator.next();
				Iterator<TrackControllerInterface> tcIterator=currLine.getAllTrackControllers().iterator();
				
				while(tcIterator.hasNext()){
					TrackControllerInterface currTC=tcIterator.next();
					Iterator<Block> tcSwitchIterator=switchList.get(currLine.getLine()).get(Integer.toString(currTC.getControllerId())).iterator();
					while(tcSwitchIterator.hasNext()){
						displayedSwitches.add("Switch " + Integer.toString(tcSwitchIterator.next().getSwitchNum()) + " (" + currLine.getLine()+" Line)");
					}
				}
			}
		}
		else if(this.displayedLine!=null && this.displayedController!=null){
			Iterator<Line> lineIterator=lines.iterator();
			Line currLine=lineIterator.next();
			while(!currLine.getLine().equals(this.displayedLine) && lineIterator.hasNext()){
				currLine=lineIterator.next();
			}
			
			
			if(this.displayedController.equals("All")){
				Iterator<TrackControllerInterface> tcIterator=currLine.getAllTrackControllers().iterator();
				
				while(tcIterator.hasNext()){
					TrackControllerInterface currTC=tcIterator.next();
					Iterator<Block> tcSwitchIterator=switchList.get(currLine.getLine()).get(Integer.toString(currTC.getControllerId())).iterator();
					while(tcSwitchIterator.hasNext()){
						displayedSwitches.add("Switch " + Integer.toString(tcSwitchIterator.next().getSwitchNum()));
					}
				}
			}
			else{
				
				Iterator<TrackControllerInterface> tcIterator = currLine.getAllTrackControllers().iterator();
				TrackControllerInterface currTC=tcIterator.next();
				while(!(Integer.toString(currTC.getControllerId()).equals(this.displayedController)) && tcIterator.hasNext()){
					currTC=tcIterator.next();
				}
				Iterator<Block> tcSwitchIterator=switchList.get(currLine.getLine()).get(Integer.toString(currTC.getControllerId())).iterator();
				while(tcSwitchIterator.hasNext()){
					displayedSwitches.add("Switch " + Integer.toString(tcSwitchIterator.next().getSwitchNum()));
				}
				
			}
		}
		
		Collections.sort(displayedSwitches);
		comboBoxSwitchList.removeAllItems();
		comboBoxSwitchList.addItem("None");
		comboBoxSwitchList.setSelectedIndex(0);
		
		Iterator<String> switchListIterator = displayedSwitches.iterator();
		while(switchListIterator.hasNext()){
			comboBoxSwitchList.addItem(switchListIterator.next());
		}
		updateSwitchesTable();
		this.repaint();
	}
	
	TrackControllerInterface findCorrectTC(Line currLine,String switchName){
		Iterator<TrackControllerInterface> tcIterator = currLine.getAllTrackControllers().iterator();;
		while(tcIterator.hasNext()){
			TrackControllerInterface currTC=tcIterator.next();
			if(currTC.getSwitchList().containsKey(switchName)){
				return currTC;
			}
		}
		return null;
	}
}
