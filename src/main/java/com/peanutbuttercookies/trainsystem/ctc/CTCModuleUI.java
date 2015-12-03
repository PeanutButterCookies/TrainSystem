/*
 * Kevin Nash
 * 10/15/2015
 */


package com.peanutbuttercookies.trainsystem.ctc;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;

public class CTCModuleUI extends JFrame {

	private static final long serialVersionUID = 6499144261081785066L;
	private static final Dimension FRAME_DIM = new Dimension(900, 600);


	private CTCModuleInterface module;

	private JTabbedPane lineTabs;
	
	public CTCModuleUI(CTCModuleInterface module) {
		super("CTC Module");
		this.module = module;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(FRAME_DIM);
		lineTabs = new JTabbedPane();
		
		
		add(lineTabs);
		setContentPane(lineTabs);
		pack();
		setLocationByPlatform(true);
		setVisible(true);
	}

	
	public void addLine(String line) {
		ComponentContainer panel = new ComponentContainer(line, module);
		lineTabs.addTab(line, panel);
	}

}
