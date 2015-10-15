package com.peanutbuttercookies.trainsystem.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.trackcontroller.TC_Train;

public class TrackControllerUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable tableVariableDisplay;
	private JTextField textField_2;
	private JTable tableLineSelection;
	
	private TrackControllerInterface trackController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrackControllerUI frame = new TrackControllerUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TrackControllerUI() {
		setTitle("Track Controller Module");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 556, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(30))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(8)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel panel_LineSelection = new JPanel();
		panel_LineSelection.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JPanel panel_LineDisplay = new JPanel();
		panel_LineDisplay.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		JLabel lblDisplayingLine = new JLabel("- Displaying Unidentified Line -");
		lblDisplayingLine.setHorizontalAlignment(SwingConstants.CENTER);
		lblDisplayingLine.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		tableVariableDisplay = new JTable();
		tableVariableDisplay.setRowSelectionAllowed(false);
		tableVariableDisplay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableVariableDisplay.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tableVariableDisplay.setModel(new DefaultTableModel(
			new Object[][] {
				{" Train ID", "Authority Recieved (CTC)", "Speed Recieved (CTC)", "Train Presence", "Authority", "Commanded Speed", "RR Crossing", "Lights"},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Train ID", "Authority Recieved (CTC)", "Speed Recieved (CTC)", "Train Presence", "Authority", "Commanded Speed", "RR Crossing", "Lights"
			}
		));
		tableVariableDisplay.getColumnModel().getColumn(0).setPreferredWidth(55);
		tableVariableDisplay.getColumnModel().getColumn(1).setPreferredWidth(139);
		tableVariableDisplay.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableVariableDisplay.getColumnModel().getColumn(3).setPreferredWidth(92);
		tableVariableDisplay.getColumnModel().getColumn(5).setPreferredWidth(105);
		
		JSeparator separator_1 = new JSeparator();
		GroupLayout gl_panel_LineDisplay = new GroupLayout(panel_LineDisplay);
		gl_panel_LineDisplay.setHorizontalGroup(
			gl_panel_LineDisplay.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_LineDisplay.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_LineDisplay.createParallelGroup(Alignment.LEADING)
						.addComponent(separator_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
						.addComponent(lblDisplayingLine, GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE)
						.addComponent(tableVariableDisplay, GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_LineDisplay.setVerticalGroup(
			gl_panel_LineDisplay.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_LineDisplay.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblDisplayingLine)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tableVariableDisplay, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_LineDisplay.setLayout(gl_panel_LineDisplay);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_LineSelection, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_LineDisplay, GroupLayout.PREFERRED_SIZE, 1017, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_LineDisplay, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
						.addComponent(panel_LineSelection, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		JLabel lblLineSelection = new JLabel("Line Selection");
		lblLineSelection.setHorizontalAlignment(SwingConstants.CENTER);
		lblLineSelection.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JSeparator separator = new JSeparator();
		
		tableLineSelection = new JTable();
		tableLineSelection.setShowGrid(false);
		tableLineSelection.setCellSelectionEnabled(true);
		tableLineSelection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		//centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		//tableLineSelection.setDefaultRenderer(String.class, centerRenderer);
		//tableLineSelection.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		
		tableLineSelection.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String selectedLine=null;
				int[] selectedRow=tableLineSelection.getSelectedRows();
				
				for(int i=0; i<selectedRow.length;i++){
					selectedLine=(String) tableLineSelection.getValueAt(selectedRow[i], 0);
				}
				
				//*** Figure out tableVariableDisplay logic ***
				if(selectedLine!=null && trackController.getLineInfo(selectedLine)!=null){
					Vector<TC_Train> tableInfo=trackController.getLineInfo(selectedLine);
					lblDisplayingLine.setText("- Displaying "+selectedLine+" Line -");
					for(int i=0; i<tableInfo.size(); i++){
						TC_Train temp=tableInfo.get(i);
						tableVariableDisplay.setValueAt(temp.getTrainId(), i+1, 0);
						tableVariableDisplay.setValueAt(temp.getAuthority(), i+1, 1);		//MUST BE CHANGED AFTER PROTOTYPE
						tableVariableDisplay.setValueAt(temp.getCommandedSpeed(), i+1, 2);	//MUST BE CHANGED AFTER PROTOTYPE
						tableVariableDisplay.setValueAt(temp.getPresence(), i+1, 3);
						tableVariableDisplay.setValueAt(temp.getAuthority(), i+1, 4);
						tableVariableDisplay.setValueAt(temp.getCommandedSpeed(), i+1, 5);
						tableVariableDisplay.setValueAt(false, i+1, 6);						//MUST BE CHANGED AFTER PROTOTYPE
						tableVariableDisplay.setValueAt(false, i+1, 7);						//MUST BE CHANGED AFTER PROTOTYPE
						
					}
				}
				
			}
		});
		
		tableLineSelection.setModel(new DefaultTableModel(
			new Object[][] {
				{"Red"},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"Line Selection"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableLineSelection.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_panel_LineSelection = new GroupLayout(panel_LineSelection);
		gl_panel_LineSelection.setHorizontalGroup(
			gl_panel_LineSelection.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_LineSelection.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_LineSelection.createParallelGroup(Alignment.TRAILING)
						.addComponent(tableLineSelection, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
						.addComponent(lblLineSelection, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_LineSelection.setVerticalGroup(
			gl_panel_LineSelection.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_LineSelection.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLineSelection)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tableLineSelection, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_LineSelection.setLayout(gl_panel_LineSelection);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblEnterPlcProgram = new JLabel("Enter PLC Program File Location:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblEnterWaysideController = new JLabel("Enter Wayside Controller ID:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnLoadProgram = new JButton("Load Program");
		
		JLabel lblEnterLineName = new JLabel("Enter Line Name:");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblEnterPlcProgram)
							.addGap(10)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblEnterLineName)
								.addComponent(lblEnterWaysideController))
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnLoadProgram))
								.addComponent(textField_2))))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(lblEnterPlcProgram))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(4)
							.addComponent(lblEnterWaysideController))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnLoadProgram))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterLineName, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
