/*
 * Kevin Nash
 * 11/5/15
 */

package com.peanutbuttercookies.trainsystem.ctc;

import java.util.ArrayList;
import java.util.List;

public class CTCSection {
	private List<Integer> blocks;
	private String name;
	
	public CTCSection(String name) {
		setName(name);
		blocks = new ArrayList<Integer>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addBlock(Integer block) {
		if(!blocks.contains(block)) {
			blocks.add(block);
		}
	}
	
	public List<Integer> getBlocks() {
		return blocks;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof CTCSection)) {
			return false;
		}
		
		return name.equals(((CTCSection)other).getName());
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
