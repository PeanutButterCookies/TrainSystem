package com.peanutbuttercookies.trainsystem.interfaces;

import java.util.LinkedList;

import com.peanutbuttercookies.trainsystem.commonresources.Line;

public interface ExcelFileDownloaderInterface {

	public boolean setExcelFile(String fileLocation);
	public LinkedList<Line> getLines();
}
