/*
 * Kevin Nash
 * 10/15/2015
 */

package com.peanutbuttercookies.trainsystem.ctc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.neo4j.graphdb.GraphDatabaseService;

public class BlockModel {
	
	
	// Neo4J Variables
	
	private GraphDatabaseService gds;
	
	// end neo4j
	private List<CTCBlock> blocks;
	private List<CTCTrain> trains;

	private HashMap<Integer, CTCBlock> blockMap;
	private HashMap<Integer, CTCTrain> trainMap;

	private HashMap<Integer, Integer> trainToBlock;
	
	public BlockModel() {
		blocks = new ArrayList<CTCBlock>();
		trains = new LinkedList<CTCTrain>();
		blockMap = new HashMap<Integer, CTCBlock>();
		trainToBlock = new HashMap<Integer, Integer>();
	}
	
	public boolean addBlock(int block) {
		return false;
	}
	
	public boolean removeBlock(int block) {
		return false;
	}
	
	public boolean addTrain(int train) {
		return false;
	}
	
	public boolean removeTrain(int train) {
		return false;
	}
	
	public boolean importTrack(String filename, Integer sheetNum) throws IOException {
		FileInputStream fileStream = new FileInputStream(new File(filename));
		HSSFWorkbook book = new HSSFWorkbook(fileStream);
		HSSFSheet sheet = book.getSheetAt(sheetNum);
		
		return false;
	}
}




