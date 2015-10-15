package com.peanutbuttercookies.trainsystem.systemwrapper;

import java.io.IOException;

import com.peanutbuttercookies.trainsystem.ctc.CTCModule;
import com.peanutbuttercookies.trainsystem.ui.CTCModuleUI;

public class MainApp {
	public static void main(String[] args) throws IOException {
		CTCModuleUI ctcUI = new CTCModuleUI(new CTCModule());
	}
}
