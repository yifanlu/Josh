/**
 * Josh - Java Output Shell enHanced
 * Psudostructure: Represents keyboard console event
 * By Yifan Lu
*/

package com.yifanlu;

public class ConsoleEventKeyboard {
	
	public boolean bKeyDown;
	public int wRepeatCount;
	public int wVirtualKeyCode;
	public int wVirtualScanCode;
	public char uChar;
	public int dwControlKeyState;
	
	public ConsoleEventKeyboard(boolean bKeyDown, int wRepeatCount, int wVirtualKeyCode, int wVirtualScanCode, char uChar, int dwControlKeyState) {
		this.bKeyDown = bKeyDown;
		this.wRepeatCount = wRepeatCount;
		this.wVirtualKeyCode = wVirtualKeyCode;
		this.wVirtualScanCode = wVirtualScanCode;
		this.uChar = uChar;
		this.dwControlKeyState = dwControlKeyState;
	}
    
}