package com.yifanlu.Josh;

import com.yifanlu.Josh.ConsoleCoord;
import com.yifanlu.Josh.ConsoleEvent;

/**
 * Describes a mouse input event.
 *
 * @see <a href="http://msdn.microsoft.com/en-us/library/ms684239(v=VS.85).aspx">Windows' MOUSE_EVENT_RECORD structure</a>
 * @see JoshInBuffer
 * @author Yifan Lu
 * @version 1.1, 05/09/10
 * @since 0.1
 */
public class ConsoleEventMouse extends ConsoleEvent {
	private ConsoleCoord mousePosition;
	private int buttonState;
	private int controlKeyState;
	private int eventFlags;
	
	/**
     * Constant value for mouse button.
     */
	public static final int FROM_LEFT_1ST_BUTTON_PRESSED = 0x0001, FROM_LEFT_2ND_BUTTON_PRESSED = 0x0004, FROM_LEFT_3RD_BUTTON_PRESSED = 0x0008, FROM_LEFT_4TH_BUTTON_PRESSED = 0x0010, RIGHTMOST_BUTTON_PRESSED = 0x0002;	

	/**
     * Constant value for mouse event flag.
     */
	public static final int DOUBLE_CLICK = 0x0002, MOUSE_HWHEELED = 0x0008, MOUSE_MOVED = 0x0001, MOUSE_WHEELED = 0x0004;
	
	/**
     * Creates a mouse event.
	 * 
     * @param mousePosition The location of the cursor, 
	 *	in terms of the console screen buffer's character-cell coordinates.
	 * @param buttonState The status of the mouse buttons. The least significant bit corresponds 
	 *	to the leftmost mouse button. The next least significant bit corresponds to the rightmost 
	 *	mouse button. The next bit indicates the next-to-leftmost mouse button. The bits then 
	 *	correspond left to right to the mouse buttons. A bit is 1 if the button was pressed.
	 *	Use the constants defined in this class to set.
	 * @param controlKeyState The state of the control keys. Use the constants defined in ConsoleEvent 
	 *	to set.
	 * @param eventFlags The type of mouse event. If this value is zero, it indicates a mouse 
	 *	button being pressed or released. Use the constants defined in this class to set.
     */
	public ConsoleEventMouse(ConsoleCoord mousePosition, int buttonState, int controlKeyState, int eventFlags) {
		this.eventType = ConsoleEvent.MOUSE_EVENT;
		this.mousePosition = mousePosition;
		this.buttonState = buttonState;
		this.controlKeyState = controlKeyState;
		this.eventFlags = eventFlags;
	}
	
	/**
     * Gets the current mouse position.
	 * 
	 * @return The location of the cursor, 
	 *	in terms of the console screen buffer's character-cell coordinates.
     */
	public ConsoleCoord getMousePosition(){
		return this.mousePosition;
	}
	
	/**
     * Gets the mouse button pressed.
	 * 
	 * @return The status of the mouse buttons. The least significant bit corresponds 
	 *	to the leftmost mouse button. The next least significant bit corresponds to the rightmost 
	 *	mouse button. The next bit indicates the next-to-leftmost mouse button. The bits then 
	 *	correspond left to right to the mouse buttons. A bit is 1 if the button was pressed.
	 *	Use the constants defined in this class to set.
     */
	public int getButtonState(){
		return this.buttonState;
	}
	
	/**
     * Gets any control keys pressed.
	 * Use constants in {@link ConsoleEvent} to compare, and the OR operator to mix and match. For example:<br />
	 * {@code if((ConsoleEventKeyboard)keyEvent.getControlKeyState() == (ConsoleEvent.CAPSLOCK_ON | LEFT_ALT_PRESSED)); }<br />
	 * would check if the caps lock is on AND left alt key is held.
	 * 
	 * @return The state of the control keys.
     */
	public int getControlKeyState(){
		return this.controlKeyState;
	}
	
	/**
     * Gets the type of mouse input (click, double click, etc).
	 * 
	 * @return The type of mouse event. If this value is zero, it indicates a mouse 
	 *	button being pressed or released. Use the constants defined in this class to set.
     */
	public int getEventFlags(){
		return this.eventFlags;
	}
	
	/**
     * Sets the current mouse position.
	 * 
	 * @param mousePosition The location of the cursor, 
	 *	in terms of the console screen buffer's character-cell coordinates.
     */
	public void setMousePosition(ConsoleCoord mousePosition){
		this.mousePosition = mousePosition;
	}
	
	/**
     * Sets the mouse button pressed.
	 * 
	 * @param buttonState The status of the mouse buttons. The least significant bit corresponds 
	 *	to the leftmost mouse button. The next least significant bit corresponds to the rightmost 
	 *	mouse button. The next bit indicates the next-to-leftmost mouse button. The bits then 
	 *	correspond left to right to the mouse buttons. A bit is 1 if the button was pressed.
	 *	Use the constants defined in this class to set.
     */
	public void setButtonState(int buttonState){
		this.buttonState = buttonState;
	}
	
	/**
     * Sets any control keys pressed.
	 * Use constants from {@link ConsoleEvent}.
	 * 
	 * @param controlKeyState The state of the control keys.
     */
	public void setControlKeyState(int controlKeyState){
		this.controlKeyState = controlKeyState;
	}
	
	/**
     * Gets the type of mouse input (click, double click, etc).
	 * 
	 * @param eventFlags The type of mouse event. If this value is zero, it indicates a mouse 
	 *	button being pressed or released. Use the constants defined in this class to set.
     */
	public void setEventFlags(int eventFlags){
		this.eventFlags = eventFlags;
	}
	
	/** 
	 * Compares two Objects, if both are ConsoleEventMouse and have the same values, they are equal.
     *
     * @param anObject Another ConsoleEventMouse object.
	 * @return true if all values are equal.
	 */
	public boolean equals(Object anObject) {
		if(anObject instanceof ConsoleEventMouse){
			ConsoleEventMouse otherObject = (ConsoleEventMouse)anObject;
			return (this.getMousePosition().equals(otherObject.getMousePosition())) && (this.getButtonState() == otherObject.getButtonState()) && (this.getControlKeyState() == otherObject.getControlKeyState()) && (this.getEventFlags() == otherObject.getEventFlags());
		}else
			return false;
	}
	
	/** 
	 * A {@link java.lang.String} representation of this object.
	 * 
	 * @return The mouse position, button clicked, and the type of click.
	 */
	public String toString(){
		return mousePosition.toString() + ", " + buttonState + ", " + eventFlags;
	}
}