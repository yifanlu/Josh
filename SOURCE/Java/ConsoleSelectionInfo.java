/**
 * Josh - Java Output Shell enHanced
 * Psudostructure: Represents a console selection
 * By Yifan Lu
*/

package com.yifanlu;

public class ConsoleSelectionInfo {
	
	public int dwFlags;
	public ConsoleCoord dwSelectionAnchor;
	public ConsoleSmallRect srSelection;
	
	public static final int CONSOLE_MOUSE_DOWN = 0x0008, CONSOLE_MOUSE_SELECTION = 0x0004, CONSOLE_NO_SELECTION = 0x0000, CONSOLE_SELECTION_IN_PROGRESS = 0x0001, CONSOLE_SELECTION_NOT_EMPTY = 0x0002;

    public ConsoleSelectionInfo(int dwFlags, ConsoleCoord dwSelectionAnchor, ConsoleSmallRect srSelection) {
    	this.dwFlags = dwFlags;
    	this.dwSelectionAnchor = dwSelectionAnchor;
    	this.srSelection = srSelection;
    }
    
}