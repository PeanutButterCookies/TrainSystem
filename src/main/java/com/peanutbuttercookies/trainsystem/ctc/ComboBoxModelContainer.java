package com.peanutbuttercookies.trainsystem.ctc;

import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;

public class ComboBoxModelContainer {
	private DefaultComboBoxModel<CTCTrain> trainCombo;
	private DefaultComboBoxModel<CTCSection> sectionCombo;
	private Map<CTCSection, DefaultComboBoxModel<CTCBlock>> blockCombos;

	public ComboBoxModelContainer() {
		trainCombo = null;
		sectionCombo = null;
		blockCombos = new HashMap<CTCSection, DefaultComboBoxModel<CTCBlock>>();
	}

	public DefaultComboBoxModel<CTCTrain> getTrainCombo() {
		return trainCombo;
	}
	public void setTrainCombo(DefaultComboBoxModel<CTCTrain> trainCombo) {
		this.trainCombo = trainCombo;
	}
	public DefaultComboBoxModel<CTCBlock> getBlockCombo(CTCSection section) {
		return blockCombos.get(section);
	}
	public void addBlockCombo(CTCSection section, DefaultComboBoxModel<CTCBlock> blockCombo) {
		blockCombos.put(section, blockCombo);
	}
	public DefaultComboBoxModel<CTCSection> getSectionCombo() {
		return sectionCombo;
	}
	public void setSectionCombo(DefaultComboBoxModel<CTCSection> sectionCombo) {
		this.sectionCombo = sectionCombo;
	}


}
