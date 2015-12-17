package com.peanutbuttercookies.trainsystem.ctc;

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;

public interface Command {
	public String toString();
	public boolean send(CTCModuleInterface ctc, String line);
}
