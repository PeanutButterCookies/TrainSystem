package com.peanutbuttercookies.trainsystem.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;
import com.peanutbuttercookies.trainsystem.trackmodel.Block;

public class TrackModelUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5053069285583258597L;
	private JPanel contentPane;
	private ArrayList<Block> track;
	private TrackModelInterface module;
	private DefaultListModel<String> infoList;
	private JList list;
	private JList list_2;
	

	/**
	 * Create the frame.
	 */
	public TrackModelUI(TrackModelInterface module) throws IOException {
		super("TrackModel");
		this.module = module;
		module.setUI(this);
		track = module.getTrack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JTextPane textPane = new JTextPane();
		DefaultListModel<String> sectionList = new DefaultListModel<String>();
		DefaultListModel<Integer> blockList = new DefaultListModel<Integer>();
		sectionList.addElement("SECTION");
		for(int i = 0; i < track.size(); i++)	{
			if(!sectionList.contains(track.get(i).getSection()))
				sectionList.addElement(track.get(i).getSection());
			blockList.addElement(track.get(i).getBlockId());
		}
				
		JList sectionListJ = new JList(sectionList);
		JScrollPane list_1 = new JScrollPane(sectionListJ);

		
		JList blockListJ = new JList(blockList);
		JScrollPane list_2 = new JScrollPane(blockListJ);
		
        
        infoList = new DefaultListModel<String>();
		infoList.addElement("INFORMATION");
        infoList.addElement("Section " + track.get(0).getSection());
        infoList.addElement("Block " + track.get(0).getBlockId());
        infoList.addElement("Block Length " + track.get(0).getBlockLen());
        infoList.addElement("Block Grade " + track.get(0).getBlockGrade());
        infoList.addElement("Speed Limit " + track.get(0).getSpeedLim());
        infoList.addElement("Station " + track.get(0).getInfra());
        infoList.addElement("Elevation " + track.get(0).getElevation());
        infoList.addElement("Cumulative Elevation " + track.get(0).getCumElev());
        infoList.addElement("SwitchBlock " + track.get(0).getSwitchId());
        infoList.addElement("Arrow Direction " + track.get(0).getDirection());
        infoList.addElement("Occupancy 0");
        
        list_1.addMouseListener(new MouseAdapter()	{
        	public void mouseClicked(MouseEvent e)	{
				for(int i = 0; i < track.size(); i++)	{
					if(track.get(i).getSection().equals(sectionListJ.getSelectedValue()))	{
						infoList.clear();
						infoList.addElement("INFORMATION");
						infoList.addElement("Section " + track.get(i).getSection());
				        infoList.addElement("Block " + track.get(i).getBlockId());
				        infoList.addElement("Block Grade " + track.get(i).getBlockGrade());
				        infoList.addElement("Block Length " + track.get(i).getBlockLen());
				        infoList.addElement("Speed Limit " + track.get(i).getSpeedLim());
				        infoList.addElement("Station " + track.get(i).getInfra());
				        infoList.addElement("Elevation " + track.get(i).getElevation());
				        infoList.addElement("Cumulative Elevation " + track.get(i).getCumElev());
				        infoList.addElement("SwitchBlock " + track.get(i).getSwitchId());
				        infoList.addElement("Arrow Direction " + track.get(i).getDirection());
				        infoList.addElement("Occupancy " + track.get(i).getOccupancy());
				        break;
					}
				}
			}
		});
        
        list_2.addMouseListener(new MouseAdapter()	{
        	public void mouseClicked(MouseEvent e)	{
        		
        		Integer blockVal = (Integer)blockListJ.getSelectedValue();
				
				if(blockVal.equals(blockListJ.getSelectedValue()))	{
					infoList.clear();
					infoList.addElement("INFORMATION");
					infoList.addElement("Section " + track.get(blockVal-1).getSection());
			        infoList.addElement("Block " + track.get(blockVal-1).getBlockId());
			        infoList.addElement("Block Grade " + track.get(blockVal-1).getBlockGrade());
			        infoList.addElement("Block Length " + track.get(blockVal-1).getBlockLen());
			        infoList.addElement("Speed Limit " + track.get(blockVal-1).getSpeedLim());
			        infoList.addElement("Station " + track.get(blockVal-1).getInfra());
			        infoList.addElement("Elevation " + track.get(blockVal-1).getElevation());
			        infoList.addElement("Cumulative Elevation " + track.get(blockVal-1).getCumElev());
			        infoList.addElement("SwitchBlock " + track.get(blockVal-1).getSwitchId());
			        infoList.addElement("Arrow Direction " + track.get(blockVal-1).getDirection());
			        infoList.addElement("Occupancy " + track.get(blockVal-1).getOccupancy());
				}
			}
		});
		
		JList infoListJ = new JList(infoList);
		JScrollPane list = new JScrollPane(infoListJ);
		DefaultListModel<String> lineList = new DefaultListModel<String>();
		lineList.addElement("LINE");
		lineList.addElement(track.get(0).getLine());
		JList lineListJ = new JList(lineList);
		JScrollPane list_3 = new JScrollPane(lineListJ);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(list_3, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(list_2, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnBrowse)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE))
					.addGap(10))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowse))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(list_1, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(list_3, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
							.addComponent(list, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
							.addComponent(list_2, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))))
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
	
	private void loadTrack(ArrayList<Block> newTrack)	{
		track = newTrack;
	}
	
	public void display(int blockVal)	{
		infoList.clear();
		infoList.addElement("Section " + track.get(blockVal-1).getSection());
        infoList.addElement("Block " + track.get(blockVal-1).getBlockId());
        infoList.addElement("Block Grade " + track.get(blockVal-1).getBlockGrade());
        infoList.addElement("Block Length " + track.get(blockVal-1).getBlockLen());
        infoList.addElement("Speed Limit " + track.get(blockVal-1).getSpeedLim());
        infoList.addElement("Station " + track.get(blockVal-1).getInfra());
        infoList.addElement("Elevation " + track.get(blockVal-1).getElevation());
        infoList.addElement("Cumulative Elevation " + track.get(blockVal-1).getCumElev());
        infoList.addElement("SwitchBlock " + track.get(blockVal-1).getSwitchId());
        infoList.addElement("Arrow Direction " + track.get(blockVal-1).getDirection());
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
