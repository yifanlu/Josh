package com.yifanlu.Josh;

import com.yifanlu.Josh.ConsoleCoord;
import com.yifanlu.Josh.ConsoleSmallRect;

/**
 * Contains information for a console selection.
 * Objects created from this class are immutable.
 *
 * @see Josh#getConsoleSelectionInfo
 * @author Yifan Lu
 * @version 1.0, 04/24/10
 * @since 0.1
 */
public class ConsoleSelectionInfo {
	private final int flags;
	private final ConsoleCoord selectionAnchor;
	private final ConsoleSmallRect selection;
	
	/**
     * The flag of the selection.
     */
	public static final int CONSOLE_MOUSE_DOWN = 0x0008, CONSOLE_MOUSE_SELECTION = 0x0004, CONSOLE_NO_SELECTION = 0x0000, CONSOLE_SELECTION_IN_PROGRESS = 0x0001, CONSOLE_SELECTION_NOT_EMPTY = 0x0002;

	/**
     * Creates a new console selection infomation.
	 * 
     * @param flags The selection indicator. Mix and match constants from this class with the OR operator.
	 * @param selectionAnchor A {@link ConsoleCoord} that specifies the selection anchor, in characters.
	 * @param selection A {@link ConsoleSmallRect} that specifies the selection rectangle.
     */
    public ConsoleSelectionInfo(int flags, ConsoleCoord selectionAnchor, ConsoleSmallRect selection) {
    	this.flags = flags;
    	this.selectionAnchor = selectionAnchor;
    	this.selection = selection;
    }
    
	/**
     * Gets the flags of the selection.
	 * 
     * @return The selection indicator. Mix and match constants from this class with the OR operator.
     */
	public int getFlags(){
		return this.flags;
	}
	
	/**
     * Gets the anchor coordinates for the selection. 
	 * 
     * @return The selection anchor, in characters.
     */
	public ConsoleCoord getSelectionAnchor(){
		return this.selectionAnchor;
	}
	
	/**
     * Gets the size of the selection.
	 * 
     * @return The selection rectangle.
     */
	public ConsoleSmallRect getSelection(){
		return this.selection;
	}
	
	/** 
	 * Compares two Objects, if both are ConsoleSelectionInfo and have the same values, they are equal.
     *
     * @param anObject Another ConsoleSelectionInfo object.
	 * @return true if all values are equal.
	 */
	public boolean equals(Object anObject) {
		if(anObject instanceof ConsoleSelectionInfo){
			ConsoleSelectionInfo otherObject = (ConsoleSelectionInfo)anObject;
			return (this.getFlags() == otherObject.getFlags()) && (this.getSelectionAnchor().equals(otherObject.getSelectionAnchor())) && (this.getSelection().equals(otherObject.getSelection()));
		}else
			return false;
	}
	
	/** 
	 * A {@link java.lang.String} representation of this object.
	 * 
	 * @return The flag, the selection anchor location, and the selection box.
	 */
	public String toString(){
		return flags + ", " + this.selectionAnchor.toString() + ", " + this.selection.toString();
	}
}