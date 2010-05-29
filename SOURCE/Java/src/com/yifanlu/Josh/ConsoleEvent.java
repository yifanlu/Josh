package com.yifanlu.Josh;

/**
 * Describes an input event in the console input buffer.
 * These records can be read from the input buffer by using the ReadConsoleInput or PeekConsoleInput function, 
 * or written to the input buffer by using the WriteConsoleInput function.<br />
 * Note that you can not create an object directly from this class. You must define the event. For example 
 * (assuming all variables are declared):<br />
 * {@code ConsoleEvent keyEvent = new ConsoleEventKeyboard((boolean)bKeyDown, (int)wRepeatCount, (int)wVirtualKeyCode, 
 *	(int)wVirtualScanCode, (char)uChar, (int)dwControlKeyState);}
 *
 * @see JoshInBuffer
 * @author Yifan Lu
 * @version 1.0, 04/24/10
 * @since 0.1
 */
public abstract class ConsoleEvent {
	protected int eventType;
	
	/**
     * Different type of event.
     */
	public static final int FOCUS_EVENT = 0x0010, KEY_EVENT = 0x0001, MENU_EVENT = 0x0008, MOUSE_EVENT = 0x0002, WINDOW_BUFFER_SIZE_EVENT = 0x0004;
	
	/**
     * Constant value for key held.
     */
	public static final int CAPSLOCK_ON = 0x0080, ENHANCED_KEY = 0x0100, LEFT_ALT_PRESSED = 0x0002, LEFT_CTRL_PRESSED = 0x0008, NUMLOCK_ON = 0x0020, RIGHT_ALT_PRESSED = 0x0001, RIGHT_CTRL_PRESSED = 0x0004, SCROLLLOCK_ON = 0x0040, SHIFT_PRESSED = 0x0010;
	
	/**
     * Gets the event type.
	 * 
	 * @return An hex value of the event, test it with the event constants (ConsoleEvent.KEY_EVENT).
     */
	public int getEventType(){
		return this.eventType;
	}
}