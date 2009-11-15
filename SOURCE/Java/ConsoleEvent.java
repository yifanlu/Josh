/**
 * Josh - Java Output Shell enHanced
 * Psudostructure: Represents generic console event, extend it!
 * By Yifan Lu
*/

package com.yifanlu;

public class ConsoleEvent {
	
	public static final int FOCUS_EVENT = 0x0010, KEY_EVENT = 0x0001, MENU_EVENT = 0x0008, MOUSE_EVENT = 0x0002, WINDOW_BUFFER_SIZE_EVENT = 0x0004;
	public static final int CAPSLOCK_ON = 0x0080, ENHANCED_KEY = 0x0100, LEFT_ALT_PRESSED = 0x0002, LEFT_CTRL_PRESSED = 0x0008, NUMLOCK_ON = 0x0020, RIGHT_ALT_PRESSED = 0x0001, RIGHT_CTRL_PRESSED = 0x0004, SCROLLLOCK_ON = 0x0040, SHIFT_PRESSED = 0x0010;
	
	public int eventType;
	public ConsoleEventKeyboard keyboardEvent;
	public ConsoleEventMouse mouseEvent;
	public ConsoleEventBufferChanged bufferChangedEvent;
	
	public ConsoleEvent(int eventType)
	{
		this.eventType = eventType;
	}
	public ConsoleEvent(int eventType, boolean bKeyDown, int wRepeatCount, int wVirtualKeyCode, int wVirtualScanCode, char uChar, int dwControlKeyState) {
		this.eventType = eventType;
		this.keyboardEvent = new ConsoleEventKeyboard(bKeyDown, wRepeatCount, wVirtualKeyCode, wVirtualScanCode, uChar, dwControlKeyState);
	}
	public ConsoleEvent(int eventType, ConsoleCoord dwMousePosition, int dwButtonState, int dwControlKeyState, int dwEventFlags) {
		this.eventType = eventType;
		this.mouseEvent = new ConsoleEventMouse(dwMousePosition, dwButtonState, dwControlKeyState, dwEventFlags);
	}
	public ConsoleEvent(int eventType, ConsoleCoord dwSize) {
		this.eventType = eventType;
		this.bufferChangedEvent = new ConsoleEventBufferChanged(dwSize);
	}
	
}