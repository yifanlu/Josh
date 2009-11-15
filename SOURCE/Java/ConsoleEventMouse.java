/**
 * Josh - Java Output Shell enHanced
 * Psudostructure: Represents mouse console event
 * By Yifan Lu
*/

package com.yifanlu;

public class ConsoleEventMouse {
	
	public ConsoleCoord dwMousePosition;
	public int dwButtonState;
	public int dwControlKeyState;
	public int dwEventFlags;
	
	public ConsoleEventMouse(ConsoleCoord dwMousePosition, int dwButtonState, int dwControlKeyState, int dwEventFlags) {
		this.dwMousePosition = dwMousePosition;
		this.dwButtonState = dwButtonState;
		this.dwControlKeyState = dwControlKeyState;
		this.dwEventFlags = dwEventFlags;
	}
    
}