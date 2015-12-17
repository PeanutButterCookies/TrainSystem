/*
 * Kevin Nash
 * 12/10/15
 */
package com.peanutbuttercookies.trainsystem.ctc;

import com.peanutbuttercookies.trainsystem.interfaces.CTCModuleInterface;

public interface Command {
	public String toString();
	public boolean send(CTCModuleInterface ctc, String line);
}
