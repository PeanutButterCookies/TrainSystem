package com.peanutbuttercookies.trainsystem.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.peanutbuttercookies.trainsystem.trackmodel.Block;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JTable;

public class TrackModelUI extends JFrame {

	private JPanel contentPane;
	public ArrayList<Block> track;

	/**
	 * Create the frame.
	 */
	public TrackModelUI(ArrayList<Block> newTrack) {
		track = newTrack;

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
		
		JList list_2 = new JList(blockList);
		
        
        DefaultListModel<String> infoList = new DefaultListModel<String>();
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
				        infoList.addElement("Occupancy 0");
				        break;
					}
				}
			}
		});
        
        list_2.addMouseListener(new MouseAdapter()	{
        	public void mouseClicked(MouseEvent e)	{
        		
					Integer blockVal = (Integer)list_2.getSelectedValue();
					
					if(blockVal.equals(list_2.getSelectedValue()))	{
						infoList.clear();
						infoList.addElement("Section " + track.get(blockVal-1).getSection());
				        infoList.addElement("Block " + track.get(blockVal-1).getBlockId());
				        infoList.addElement("Block Length " + track.get(blockVal-1).getBlockLen());
				        infoList.addElement("Speed Limit " + track.get(blockVal-1).getSpeedLim());
				        infoList.addElement("Station " + track.get(blockVal-1).getInfra());
				        infoList.addElement("Occupancy " + track.get(blockVal-1).getOccupancy());
				    
				}
			}
		});
		
		JList list = new JList(infoList);
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
	}
	
	public void loadTrack(ArrayList<Block> newTrack)	{
		track = newTrack;
	}
}
