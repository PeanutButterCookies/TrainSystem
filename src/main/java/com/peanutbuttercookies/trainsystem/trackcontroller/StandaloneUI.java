package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackControllerStaticInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class StandaloneUI extends JFrame {

	private CTCModuleInterface ctc;
	private TrackControllerStaticInterface trackController;
	private TrackModelInterface trackModel;
	private String fileLocation;
	
	private JPanel contentPane;
	private JTextField textField;
	private StandaloneUI self;

	/**
	 * Launch the application.
	 */
	public StandaloneUI(CTCModuleInterface ctc, TrackControllerStaticInterface trackController,TrackModelInterface trackModel) {
		setTitle("Track Controller Standalone");
		
		this.ctc=ctc;
		this.trackController=trackController;
		this.trackModel=trackModel;
		this.fileLocation=null;
		self=this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 145);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Enter Excel File Location");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Choose file to load");
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setAcceptAllFileFilterUsed(false);
				
				if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					textField.setText(chooser.getSelectedFile().getPath());
				}
			}
		});
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(textField.getText()!=null){
					fileLocation=textField.getText();
					loadExcel();
				}
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				self.dispose();
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnBrowse)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoad)
							.addPreferredGap(ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
							.addComponent(btnExit)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBrowse)
						.addComponent(btnLoad)
						.addComponent(btnExit))
					.addContainerGap(159, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
	
	private void loadExcel(){
		
		if(fileLocation==null){
			return;
		}
		try {
			trackModel.fileRead(fileLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(trackModel.getTrack().size()==0){
			return;
		}
		
		this.setVisible(false);
		
		trackController.setCTC(ctc);
		TrackControllerUI tcUI = new TrackControllerUI();
		trackController.setTrackControllerUI(tcUI);
		List<Line> lines = trackModel.getTrack();
		for(Line line : lines) {
			trackController.setTrackControllers(line);
			//ctc.importLine(line);
		}
		

		tcUI.setLines(lines);
	}
}
