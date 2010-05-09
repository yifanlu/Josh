package com.yifanlu.Josh;

import com.yifanlu.Josh.ConsoleCoord;
import com.yifanlu.Josh.ConsoleSmallRect;

/**
 * Contains information about a console screen buffer.
 *
 * @see <a href="http://msdn.microsoft.com/en-us/library/ms682093(v=VS.85).aspx">Windows' CONSOLE_SCREEN_BUFFER_INFO structure</a>
 * @see JoshOutBuffer#getConsoleScreenBufferInfo
 * @see JoshOutBuffer#setConsoleScreenBufferInfo
 * @author Yifan Lu
 * @version 1.0, 04/24/10
 * @since 0.1
 */
public class ConsoleScreenBufferInfo extends ConsoleAttribute {
	private ConsoleCoord size;
	private ConsoleCoord cursorPosition;
	private ConsoleSmallRect window;
	private ConsoleCoord maximumWindowSize;

	/**
     * Creates a new console screen buffer infomation.
	 * 
     * @param size The size of the console screen buffer, in character columns and rows.
     * @param cursorPosition The column and row coordinates of the cursor in the console screen buffer.
     * @param window The console screen buffer coordinates of the upper-left and lower-right corners of the display window.
	 * @param attributes The attributes of the characters written to a screen buffer by the {@link JoshOutBuffer#writeConsoleOutput} function, or echoed 
	 *	to a screen buffer by the {@link JoshOutBuffer#readConsoleOutput} functions. For more information, see 
	 *	<a href="http://msdn.microsoft.com/en-us/library/ms682088(v=VS.85).aspx#_win32_character_attributes">Character Attributes</a>.
     * @param maximumWindowSize The maximum size of the console window, in character columns and rows, given the current screen buffer size and font and the screen size.
	 */
    public ConsoleScreenBufferInfo(ConsoleCoord size, ConsoleCoord cursorPosition, int attributes, ConsoleSmallRect window, ConsoleCoord maximumWindowSize) {
    	this.size = size;
    	this.cursorPosition = cursorPosition;
    	this.attributes = attributes;
    	this.window = window;
    	this.maximumWindowSize = maximumWindowSize;
    }
    
	/**
     * Gets the size of the console buffer in the buffer info.
	 * 
     * @return The size of the console screen buffer, in character columns and rows.
     */
	public ConsoleCoord getSize(){
		return this.size;
	}
	
	/**
     * Gets the location of the cursor in the buffer info.
	 * 
     * @return The column and row coordinates of the cursor in the console screen buffer.
     */
	public ConsoleCoord getCursorPosition(){
		return this.cursorPosition;
	}
	
	/**
     * Gets the size of the window in the buffer info.
	 * 
     * @return The console screen buffer coordinates of the upper-left and lower-right corners of the display window.
     */
	public ConsoleSmallRect getWindow(){
		return this.window;
	}
	
	/**
     * Gets the largest size of the buffer info.
	 * 
     * @return The maximum size of the console window, in character columns and rows, given the current screen buffer size and font and the screen size.
     */
	public ConsoleCoord getMaximumWindowSize(){
		return this.maximumWindowSize;
	}
	
	/**
     * Sets the size of the console buffer in the buffer info.
	 * 
     * @param size The size of the console screen buffer, in character columns and rows.
     */
	public void setSize(ConsoleCoord size){
		this.size = size;
	}
	
	/**
     * Sets the location of the cursor in the buffer info.
	 * 
     * @param cursorPosition The column and row coordinates of the cursor in the console screen buffer.
     */
	public void setCursorPosition(ConsoleCoord cursorPosition){
		this.cursorPosition = cursorPosition;
	}
	
	/**
     * Sets the size of the window in the buffer info.
	 * 
     * @param window The console screen buffer coordinates of the upper-left and lower-right corners of the display window.
     */
	public void setWindow(ConsoleSmallRect window){
		this.window = window;
	}
	
	/**
     * Sets the largest size of the buffer info.
	 * 
     * @param maximumWindowSize The maximum size of the console window, in character columns and rows, given the current screen buffer size and font and the screen size.
     */
	public void setMaximumWindowSize(ConsoleCoord maximumWindowSize){
		this.maximumWindowSize = maximumWindowSize;
	}
	
	/** 
	 * Compares two Objects, if both are ConsoleScreenBufferInfo and have the same values, they are equal.
     *
     * @param anObject Another ConsoleScreenBufferInfo object.
	 * @return true if all values are equal.
	 */
	public boolean equals(Object anObject) {
		if(anObject instanceof ConsoleScreenBufferInfo){
			ConsoleScreenBufferInfo otherObject = (ConsoleScreenBufferInfo)anObject;
			return (this.getSize().equals(otherObject.getSize())) && (this.getCursorPosition().equals(otherObject.getCursorPosition())) && (this.getWindow().equals(otherObject.getWindow())) && (this.getMaximumWindowSize().equals(otherObject.getMaximumWindowSize()));
		}else
			return false;
	}
	
	/** 
	 * A {@link java.lang.String} representation of this object.
	 * 
	 * @return The size, the cursor position, the window size, and the maximum window size.
	 */
	public String toString(){
		return size.toString() + ", " + cursorPosition.toString() + ", " + window.toString() + ", " + maximumWindowSize.toString();
	}
}