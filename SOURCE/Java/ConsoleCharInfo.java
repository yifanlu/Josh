package com.yifanlu.Josh;

import com.yifanlu.Josh.ConsoleAttribute;

/**
 * Specifies a character and its attributes.
 * This structure is used by console functions to read from and write to a console screen buffer.
 *
 * @see <a href="http://msdn.microsoft.com/en-us/library/ms682013(v=VS.85).aspx">Windows' CHAR_INFO structure</a>
 * @see JoshOutBuffer
 * @author Yifan Lu
 * @version 1.0, 04/24/10
 * @since 0.1
 */
public class ConsoleCharInfo extends ConsoleAttribute {
	private char character;
	
	/**
     * Creates a new character with attributes.
	 * An attribute can be a color, or byte change. You can mix and match 
	 * different attributes with the OR ( | ) operator. For example the attribute 
	 * <em>ConsoleColor.FOREGROUND_BLUE | ConsoleColor.BACKGROUND_GREEN | 
	 * ConsoleAttribute.COMMON_LVB_LEADING_BYTE</em> is valid for the int attribute
	 * parameter. Note the you can use constants from both {@link ConsoleAttribute} 
	 * and it's subclass {@link ConsoleColor}.
     *
     * @param character A char to represent.
     * @param attribute The attribute(s) of the character which can be mixed with the OR operator.
     */
    public ConsoleCharInfo(char character, ConsoleAttribute attribute) {
		super(attribute.getAttributes());
    	this.character = character;
    }

	/**
     * Gets the char stored in the object.
	 * 
	 * @return The character stored.
     */
	public char getCharacter() {
		return this.character;
	}
	

	/**
     * Sets the character
     *
     * @param character A char to represent.
     */
	public void setCharacter(char character) {
		this.character = character;
	}
	
	/** 
	 * Compares two Objects, if both are ConsoleCharInfo and have the same character and attributes, they are equal.
     *
     * @param anObject Another ConsoleCharInfo object.
	 * @return true if both the character and its attributes are equal.
	 */
	public boolean equals(Object anObject) {
		if(anObject instanceof ConsoleCharInfo){
			ConsoleCharInfo otherChar = (ConsoleCharInfo)anObject;
			return (this.getCharacter() == otherChar.getCharacter()) && (this.getAttributes() == otherChar.getAttributes());
		}else
			return false;
	}
	
	/** 
	 * A {@link java.lang.String} representation of this object.
	 * 
	 * @return A {@link java.lang.String}
	 */
	public String toString(){
		return Character.toString(this.character);
	}
}