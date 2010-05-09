package com.yifanlu.Josh;

import com.yifanlu.Josh.ConsoleEvent;

/**
 * Describes a menu event.
 * These events are used internally and should be ignored.
 *
 * @see <a href="http://msdn.microsoft.com/en-us/library/ms684213(v=VS.85).aspx">Windows' MENU_EVENT_RECORD structure</a>
 * @see JoshInBuffer
 * @author Yifan Lu
 * @version 1.1, 05/09/10
 * @since 1.0
 */
public class ConsoleEventMenu extends ConsoleEvent {
	private int commandId;
	
	/**
     * Creates a new console menu event.
	 * 
     * @param commandId Reserved.
     */
	public ConsoleEventMenu(int commandId) {
		this.eventType = ConsoleEvent.MENU_EVENT;
		this.commandId = commandId;
	}
	
	/**
     * Returns the command id.
	 * 
	 * @return Reserved.
     */
	public int getCommandId(){
		return this.commandId;
	}
	
	/**
     * Sets the command id.
	 * 
	 * @param commandId Reserved.
     */
	public void setSetFocus(int commandId){
		this.commandId = commandId;
	}
	
	/** 
	 * Compares two Objects, if both are ConsoleEventMenu and have the same values, they are equal.
     *
     * @param anObject Another ConsoleEventMenu object.
	 * @return true if all values are equal.
	 */
	public boolean equals(Object anObject) {
		if(anObject instanceof ConsoleEventMenu){
			ConsoleEventMenu otherObject = (ConsoleEventMenu)anObject;
			return this.getCommandId() == otherObject.getCommandId();
		}else
			return false;
	}
	
	/** 
	 * A {@link java.lang.String} representation of this object.
	 * 
	 * @return The command id.
	 */
	public String toString(){
		return Integer.toString(commandId);
	}    
}