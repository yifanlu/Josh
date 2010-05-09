package com.yifanlu.Josh;

import com.yifanlu.Josh.ConsoleCoord;
import com.yifanlu.Josh.ConsoleEvent;

/**
 * Describes a change in the size of the console screen buffer.
 *
 * @see <a href="http://msdn.microsoft.com/en-us/library/ms687093(v=VS.85).aspx">Windows' WINDOW_BUFFER_SIZE_RECORD structure</a>
 * @see JoshInBuffer
 * @author Yifan Lu
 * @version 1.1, 05/09/10
 * @since 0.1
 */
public class ConsoleEventBufferChanged extends ConsoleEvent {
	private ConsoleCoord size;
	
	/**
     * Creates a new window buffer size change event.
	 * 
     * @param size The size of the console screen buffer, in character cell columns and rows.
     */
	public ConsoleEventBufferChanged(ConsoleCoord size) {
		this.eventType = ConsoleEvent.WINDOW_BUFFER_SIZE_EVENT;
		this.size = size;
	}
	
	/**
     * Gets the size of the buffer.
	 * 
	 * @return The size of the console screen buffer, in character cell columns and rows.
     */
	public ConsoleCoord getSize(){
		return this.size;
	}
	
	/**
     * Sets the size of the buffer.
	 * 
	 * @param size The size of the console screen buffer, in character cell columns and rows.
     */
	public void setSize(ConsoleCoord size){
		this.size = size;
	}
	
	/** 
	 * Compares two Objects, if both are ConsoleEventBufferChanged and have the same values, they are equal.
     *
     * @param anObject Another ConsoleEventBufferChanged object.
	 * @return true if all values are equal.
	 */
	public boolean equals(Object anObject) {
		if(anObject instanceof ConsoleEventBufferChanged){
			ConsoleEventBufferChanged otherObject = (ConsoleEventBufferChanged)anObject;
			return this.getSize() == otherObject.getSize();
		}else
			return false;
	}
	
	/** 
	 * A {@link java.lang.String} representation of this object.
	 * 
	 * @return The coordinates of the new window buffer size. Format: (Count of columns, count of rows)
	 */
	public String toString(){
		return size.toString();
	}    
}