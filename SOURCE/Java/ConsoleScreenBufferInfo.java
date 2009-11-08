/**
 * Josh - Java Output Shell enHanced
 * Psudostructure: Represents a buffer info
 * By Yifan Lu
*/

package com.yifanlu;

public class ConsoleScreenBufferInfo {
	
	public ConsoleCoord dwSize;
	public ConsoleCoord dwCursorPosition;
	public int wAttributes;
	public ConsoleSmallRect srWindow;
	public ConsoleCoord dwMaximumWindowSize;

    public ConsoleScreenBufferInfo(ConsoleCoord dwSize, ConsoleCoord dwCursorPosition, int wAttributes, ConsoleSmallRect srWindow, ConsoleCoord dwMaximumWindowSize) {
    	this.dwSize = dwSize;
    	this.dwCursorPosition = dwCursorPosition;
    	this.wAttributes = wAttributes;
    	this.srWindow = srWindow;
    	this.dwMaximumWindowSize = dwMaximumWindowSize;
    }
    
}