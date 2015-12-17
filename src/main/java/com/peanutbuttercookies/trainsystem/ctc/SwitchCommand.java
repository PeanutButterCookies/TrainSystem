/*
 * Kevin Nash
 * 12/10/15
 */
package com.peanutbuttercookies.trainsystem.ctc;

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;

public class SwitchCommand implements Command {
	private int switchId;
	private boolean engaged;
	
	public SwitchCommand(int switchId, boolean engaged) {
		this.switchId = switchId;
		this.engaged = engaged;
	}
	
	public int getSwitchId() {
		return switchId;
	}
	
	public void setSwitchId(int switchId) {
		this.switchId = switchId;
	}
	public boolean isEngaged() {
		return engaged;
	}
	public void setEngaged(boolean engaged) {
		this.engaged = engaged;
	}
	
	@Override
	public String toString() {
		return "Switch at block " + switchId + " set to " + ((engaged)? "engaged" : "disengaged");
	}

	@Override
	public boolean send(CTCModuleInterface ctc, String line) {
		ctc.changeSwitch(line, switchId, engaged);
		return true;
	}
}
