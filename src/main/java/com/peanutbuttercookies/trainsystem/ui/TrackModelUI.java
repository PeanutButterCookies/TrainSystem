<<<<<<< HEAD
package com.peanutbuttercookies.trainsystem.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
=======
package com.peanutbuttercookies.trainsystem.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EmptyBorder;

import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.trackmodel.Block;
>>>>>>> refs/remotes/origin/master
import com.peanutbuttercookies.trainsystem.trackmodel.Block;

public class TrackModelUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5053069285583258597L;
	private JPanel contentPane;
	private ArrayList<Block> track;
	private TrackModelInterface module;
<<<<<<< HEAD
=======
	private DefaultListModel<String> infoList;
	private JList list;
	private JList list_2;
>>>>>>> refs/remotes/origin/master
	

	/**
	 * Create the frame.
	 */
	public TrackModelUI(TrackModelInterface module) throws IOException {
		super("TrackModel");
		this.module = module;
<<<<<<< HEAD
=======
		module.setUI(this);
>>>>>>> refs/remotes/origin/master
		track = module.getTrack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnBrowse = new JButton("Browse");
		
		JTextPane textPane = new JTextPane();
		DefaultListModel<String> sectionList = new DefaultListModel<String>();
		DefaultListModel<Integer> blockList = new DefaultListModel<Integer>();
		for(int i = 0; i < track.size(); i++)	{
			if(!sectionList.contains(track.get(i).getSection()))
				sectionList.addElement(track.get(i).getSection());
			blockList.addElement(track.get(i).getBlockId());
		}
				
		JList list_1 = new JList(sectionList);
		
<<<<<<< HEAD
		JList list_2 = new JList(blockList);
		
        
        DefaultListModel<String> infoList = new DefaultListModel<String>();
=======
		list_2 = new JList(blockList);
		
        
        infoList = new DefaultListModel<String>();
>>>>>>> refs/remotes/origin/master
        infoList.addElement("Section " + track.get(0).getSection());
        infoList.addElement("Block " + track.get(0).getBlockId());
        infoList.addElement("Block Length " + track.get(0).getBlockLen());
        infoList.addElement("Speed Limit " + track.get(0).getSpeedLim());
        infoList.addElement("Station " + track.get(0).getInfra());
        infoList.addElement("Occupancy 0");
        
        list_1.addMouseListener(new MouseAdapter()	{
        	public void mouseClicked(MouseEvent e)	{
				for(int i = 0; i < track.size(); i++)	{
					if(track.get(i).getSection().equals(list_1.getSelectedValue()))	{
						infoList.clear();
						infoList.addElement("Section " + track.get(i).getSection());
				        infoList.addElement("Block " + track.get(i).getBlockId());
				        infoList.addElement("Block Length " + track.get(i).getBlockLen());
				        infoList.addElement("Speed Limit " + track.get(i).getSpeedLim());
				        infoList.addElement("Station " + track.get(i).getInfra());
<<<<<<< HEAD
				        infoList.addElement("Occupancy 0");
=======
				        infoList.addElement("Occupancy " + track.get(i).getOccupancy());
>>>>>>> refs/remotes/origin/master
				        break;
					}
				}
			}
		});
        
        list_2.addMouseListener(new MouseAdapter()	{
        	public void mouseClicked(MouseEvent e)	{
        		
<<<<<<< HEAD
					Integer blockVal = (Integer)list_2.getSelectedValue();
					
					if(blockVal.equals(list_2.getSelectedValue()))	{
						infoList.clear();
						infoList.addElement("Section " + track.get(blockVal-1).getSection());
				        infoList.addElement("Block " + track.get(blockVal-1).getBlockId());
				        infoList.addElement("Block Length " + track.get(blockVal-1).getBlockLen());
				        infoList.addElement("Speed Limit " + track.get(blockVal-1).getSpeedLim());
				        infoList.addElement("Station " + track.get(blockVal-1).getInfra());
				        infoList.addElement("Occupancy " + track.get(blockVal-1).getOccupancy());
				    
=======
        		Integer blockVal = (Integer)list_2.getSelectedValue();
				
				if(blockVal.equals(list_2.getSelectedValue()))	{
					infoList.clear();
					infoList.addElement("Section " + track.get(blockVal-1).getSection());
			        infoList.addElement("Block " + track.get(blockVal-1).getBlockId());
			        infoList.addElement("Block Length " + track.get(blockVal-1).getBlockLen());
			        infoList.addElement("Speed Limit " + track.get(blockVal-1).getSpeedLim());
			        infoList.addElement("Station " + track.get(blockVal-1).getInfra());
			        infoList.addElement("Occupancy " + track.get(blockVal-1).getOccupancy());
			    
>>>>>>> refs/remotes/origin/master
				}
			}
		});
		
<<<<<<< HEAD
		JList list = new JList(infoList);
=======
		list = new JList(infoList);
>>>>>>> refs/remotes/origin/master
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(list_2, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(list, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBrowse)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowse))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(list, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(list_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(list_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
	
<<<<<<< HEAD
	public void loadTrack(ArrayList<Block> newTrack)	{
		track = newTrack;
	}
}
=======
	private void loadTrack(ArrayList<Block> newTrack)	{
		track = newTrack;
	}
	
	public void display(int blockVal)	{
		infoList.clear();
		infoList.addElement("Section " + track.get(blockVal-1).getSection());
        infoList.addElement("Block " + track.get(blockVal-1).getBlockId());
        infoList.addElement("Block Length " + track.get(blockVal-1).getBlockLen());
        infoList.addElement("Speed Limit " + track.get(blockVal-1).getSpeedLim());
        infoList.addElement("Station " + track.get(blockVal-1).getInfra());
        infoList.addElement("Occupancy " + track.get(blockVal-1).getOccupancy());
        list = new JList(infoList);
	}
	
	public boolean currentView(Integer blockId)
	{
		Integer blockVal = (Integer) list_2.getSelectedValue();
		if(blockId.equals(blockVal))
			return true;
		else 
			return false;
	}
}
>>>>>>> refs/remotes/origin/master
