package com.peanutbuttercookies.trainsystem.trackcontroller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoadPLCSuccessFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblPlcProgramLoad;
	JFrame self;

	/**
	 * Create the frame.
	 */
	public LoadPLCSuccessFrame(boolean success) {
		self=this;
		
		setTitle("PLC Program Loader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 230, 120);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String displayString=null;
		if(success){
			displayString="PLC Program Load Success!";
		}
		else{
			displayString="Error: Invalid Program";
		}
		
		lblPlcProgramLoad = new JLabel(displayString);
		lblPlcProgramLoad.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlcProgramLoad.setFont(new Font("Tahoma", Font.BOLD, 12));
		if(!success){
			lblPlcProgramLoad.setForeground(Color.red);
		}
		
		
		JButton btnOkButton = new JButton("Ok");
		btnOkButton.addActionListener(new ActionListener(){
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
						.addComponent(btnOkButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
						.addComponent(lblPlcProgramLoad, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblPlcProgramLoad)
					.addGap(18)
					.addComponent(btnOkButton)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
