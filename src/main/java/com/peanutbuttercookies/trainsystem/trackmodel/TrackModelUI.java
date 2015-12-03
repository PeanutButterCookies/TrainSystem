package com.peanutbuttercookies.trainsystem.trackmodel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.peanutbuttercookies.trainsystem.commonresources.Block;
import com.peanutbuttercookies.trainsystem.commonresources.Line;
import com.peanutbuttercookies.trainsystem.interfaces.TrackModelInterface;

public class TrackModelUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5053069285583258597L;
	private JPanel contentPane;
	private LinkedList<Block> track;
	private LinkedList<Line> lines;
	private int curView;
	private int curLine;

	private TrackModelInterface module;
	private DefaultListModel<String> infoList;
	private DefaultListModel<Integer> blockList;
	private DefaultListModel<String> sectionList;
	private DefaultListModel<String> lineList;
	private JList list;
	private JList list_1;
	private JList list_2;
	private JList list_3;
	private JLabel lblTemperatureController;
	

	/**
	 * Create the frame.
	 */
	public TrackModelUI(TrackModelInterface module) throws IOException {
		super("TrackModel");
		this.module = module;
		module.setUI(this);
		track = new LinkedList<Block>();
		lines = new LinkedList<Line>();
		int curLine = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		
		JTextPane textPane = new JTextPane();
		sectionList = new DefaultListModel<String>();
		blockList = new DefaultListModel<Integer>();
        infoList = new DefaultListModel<String>();
        lineList = new DefaultListModel<String>();
		
		JSlider slider = new JSlider();
		slider.setMaximum(120);
		slider.setMinimum(-20);
			
		lblTemperatureController = new JLabel("Temperature Controller: " + slider.getValue());
		
		slider.addChangeListener(new ChangeListener() {
		      public void stateChanged(ChangeEvent e) {
		    	  lblTemperatureController.setText("Temperature Controller: " + slider.getValue());		      }
		    });	
		
		JScrollPane scrollPane = new JScrollPane(list_1);
		
		JScrollPane scrollPane_1 = new JScrollPane(list_2);
		
		JScrollPane scrollPane_2 = new JScrollPane(list_3);
		
		JScrollPane scrollPane_3 = new JScrollPane(list);
		
		list_1 = new JList(sectionList);
		scrollPane.setViewportView(list_1);
		
		list = new JList(lineList);
		scrollPane_3.setViewportView(list);
		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int ret = chooser.showOpenDialog(null);
                if (ret== JFileChooser.APPROVE_OPTION) 
                {
                    File file = chooser.getSelectedFile();
                    String filename= file.getAbsolutePath();
					try {
						module.fileRead(filename);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					};
                    display(0);
                }
            }
        });
		
		JButton btnBreakBlock = new JButton("Break Block");
		btnBreakBlock.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	for(int i = 0; i < track.size(); i++)	{
            		if(i == curView)
            			track.get(i).setBlockOccupation(true, null);
            	}
            	update(curView);
            }
        });
		
		JButton btnSwitchEngage = new JButton("Switch Engage");
		btnSwitchEngage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	for(int i = 0; i < track.size(); i++)	{
            		if(i == curView)
            			track.get(i).setSwitchEngagement();
            	}
            	update(curView);
            }
        });
		
		JButton btnFixBlock = new JButton("Fix Block");
		btnFixBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < track.size(); i++)	{
            		if(i == curView)
            			track.get(i).setBlockOccupation(false, null);
            	}
            	update(curView);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBrowse)
							.addGap(20)
							.addComponent(lblTemperatureController)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(slider, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnFixBlock)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnBreakBlock)
								.addGap(14))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnSwitchEngage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap()))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(textPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnBrowse))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblTemperatureController)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnBreakBlock)
								.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
									.addComponent(btnFixBlock))
								.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(34)
							.addComponent(btnSwitchEngage)
							.addContainerGap())))
		);
		
		list_2 = new JList(blockList);
		scrollPane_1.setViewportView(list_2);
		
		 list_2.addMouseListener(new MouseAdapter()	{
	        	public void mouseClicked(MouseEvent e)	{

	        		Integer blockVal = (Integer)list_2.getSelectedValue();
					if(blockVal.equals(list_2.getSelectedValue()))	{
							update(blockVal-1);
				        }
					}
			});
		list_3 = new JList(infoList);
		scrollPane_2.setViewportView(list_3);
		
		
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
		
		list_1.addMouseListener(new MouseAdapter()	{
        	public void mouseClicked(MouseEvent e)	{
				for(int i = 0; i < track.size(); i++)	{
					if(track.get(i).getSection().equals(list_1.getSelectedValue()))	{
						update(i);
					}
				}
			}
		});
		 
		 list.addMouseListener(new MouseAdapter()	{
	        	public void mouseClicked(MouseEvent e)	{
					for(int i = 0; i< lines.size(); i++)	{
						if(lines.get(i).getAllBlocks().get(0).getLine().equals(list.getSelectedValue()))	{
							display(i);
							break;
						}
				}
			}
		 });
		 
	}
	
	private void loadTrack(LinkedList<Block> newTrack)	{
		track = newTrack;
	}
	
	public void display(int index)	{
		this.lines = module.getLines();
		this.track = lines.get(index).getAllBlocks();
		
		infoList.clear();
		lineList.clear();
		sectionList.clear(); 
		blockList.clear(); 
		curView = 0;
		infoList.addElement("Line " + track.get(0).getLine());
		infoList.addElement("Section " + track.get(0).getSection());
        infoList.addElement("Block " + track.get(0).getBlockNumber());
        infoList.addElement("Block Length " + track.get(0).getBlockLength());
        infoList.addElement("Block Grade " + track.get(0).getBlockGrade());
        infoList.addElement("Speed Limit " + track.get(0).getSpeedLimit());
        infoList.addElement("Sation " + track.get(0).getStationName());
        infoList.addElement("Elevation " + track.get(0).getElevation());
        infoList.addElement("Cumulative Elevation " + track.get(0).getCumulativeElevation());
        infoList.addElement("Switch " + track.get(0).getSwitchBlockId());
        infoList.addElement("Master Switch: " + track.get(0).getMasterSwitch());
        infoList.addElement("Direction " + track.get(0).getArrowDirectionA());
        infoList.addElement("Direction " + track.get(0).getArrowDirectionB());
        infoList.addElement("Occupancy " + track.get(0).isBlockOccupied());
        infoList.addElement("Two Way: " + track.get(0).getTwoWay());
        infoList.addElement("NEXT POSSIBLE BLOCKS");
        LinkedList<Block> nextBlocks = new LinkedList<Block>(track.get(0).getNextPossible());
        String nextPos = "";
        for(int i = 0; i < nextBlocks.size(); i++)	{
        	nextPos += nextBlocks.get(i).getBlockNumber() + " ";
        }
        infoList.addElement(nextPos);
        if(track.get(0).getSwitchList() != null)	{
	        infoList.addElement("SWITCH LIST");
	        nextBlocks = new LinkedList<Block>(track.get(0).getSwitchList());
	        nextPos = "";
	        for(int i = 0; i < nextBlocks.size(); i++)	{
	        	nextPos += nextBlocks.get(i).getBlockNumber() + " ";
	        }
	        infoList.addElement(nextPos);
        }

       
        for(int i = 0; i < lines.size(); i++)	{
        	if(!lineList.contains(lines.get(i).getAllBlocks().get(0).getLine()))
        		lineList.addElement("" + lines.get(i).getAllBlocks().get(0).getLine());
        }
        
        for(int i = 0; i < track.size(); i++)	{
        	if(!lineList.contains(track.get(i).getLine()))
        		lineList.addElement("" + track.get(i).getLine());
        	if(!sectionList.contains(track.get(i).getSection()))
        		sectionList.addElement("" + track.get(i).getSection());
        	blockList.addElement(track.get(i).getBlockNumber());
        }       
	}
	
	public boolean currentView(Integer blockId)
	{
		Integer blockVal = (Integer) list_2.getSelectedValue();
		if(blockId.equals(blockVal))
			return true;
		else 
			return false;
	}
	
	public void update(int blockNum)
	{
		infoList.clear();
		curView = blockNum;
		infoList.addElement("Line " + track.get(blockNum).getLine());
		infoList.addElement("Section " + track.get(blockNum).getSection());
        infoList.addElement("Block " + track.get(blockNum).getBlockNumber());
        infoList.addElement("Block Length " + track.get(blockNum).getBlockLength());
        infoList.addElement("Block Grade " + track.get(blockNum).getBlockGrade());
        infoList.addElement("Speed Limit " + track.get(blockNum).getSpeedLimit());
        infoList.addElement("Infrastructure " + track.get(blockNum).getStationName());
        infoList.addElement("Elevation " + track.get(blockNum).getElevation());
        infoList.addElement("Cumulative Elevation " + track.get(blockNum).getCumulativeElevation());
        infoList.addElement("Switch " + track.get(blockNum).getSwitchBlockId());
        infoList.addElement("Master Switch: " + track.get(blockNum).getMasterSwitch());
        infoList.addElement("Direction " + track.get(blockNum).getArrowDirectionA());
        infoList.addElement("Direction " + track.get(blockNum).getArrowDirectionB());
        infoList.addElement("Occupancy " + track.get(blockNum).isBlockOccupied());
        infoList.addElement("Two Way: " + track.get(blockNum).getTwoWay());
        infoList.addElement("NEXT POSSIBLE BLOCKS");
        LinkedList<Block> nextBlocks = new LinkedList<Block>(track.get(blockNum).getNextPossible());
        String nextPos = "";
        for(int i = 0; i < nextBlocks.size(); i++)	{
        	nextPos += nextBlocks.get(i).getBlockNumber() + " ";
        }
        infoList.addElement(nextPos);
        if(track.get(blockNum).getSwitchList() != null)	{
	        infoList.addElement("SWITCH LIST");
	        nextBlocks = new LinkedList<Block>(track.get(blockNum).getSwitchList());
	        nextPos = "";
	        for(int i = 0; i < nextBlocks.size(); i++)	{
	        	nextPos += nextBlocks.get(i).getBlockNumber() + " ";
	        }
	        infoList.addElement(nextPos);
        }
        
	}
}