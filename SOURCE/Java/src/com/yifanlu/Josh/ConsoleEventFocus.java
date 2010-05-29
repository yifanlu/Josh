package com.yifanlu.Josh;

import com.yifanlu.Josh.ConsoleEvent;

/**
 * Describes a focus event.
 * These events are used internally and should be ignored.
 *
 * @see <a href="http://msdn.microsoft.com/en-us/library/ms683149(v=VS.85).aspx">Windows' FOCUS_EVENT_RECORD structure</a>
 * @see JoshInBuffer
 * @author Yifan Lu
 * @version 1.1, 05/09/10
 * @since 1.0
 */
public class ConsoleEventFocus extends ConsoleEvent {
	private boolean setFocus;
	
	/**
     * Creates a new console focus change event.
	 * 
     * @param setFocus Reserved.
     */
	public ConsoleEventFocus(boolean setFocus) {
		this.eventType = ConsoleEvent.FOCUS_EVENT;
		this.setFocus = setFocus;
	}
	
	/**
     * Returns the focus state.
	 * 
	 * @return Reserved.
     */
	public boolean getSetFocus(){
		return this.setFocus;
	}
	
	/**
     * Sets the focus state.
	 * 
	 * @param setFocus Reserved.
     */
	public void setSetFocus(boolean setFocus){
		this.setFocus = setFocus;
	}
	
	/** 
	 * Compares two Objects, if both are ConsoleEventFocus and have the same values, they are equal.
     *
     * @param anObject Another ConsoleEventFocus object.
	 * @return true if all values are equal.
	 */
	public boolean equals(Object anObject) {
		if(anObject instanceof ConsoleEventFocus){
			ConsoleEventFocus otherObject = (ConsoleEventFocus)anObject;
			return this.getSetFocus() == otherObject.getSetFocus();
		}else
			return false;
	}
	
	/** 
	 * A {@link java.lang.String} representation of this object.
	 * 
	 * @return The set focus state.
	 */
	public String toString(){
		return Boolean.toString(this.setFocus);
	}
}