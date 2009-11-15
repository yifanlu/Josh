/**
 * Josh - Java Output Shell enHanced
 * Psudostructure: Represents a character and it's attribute
 * By Yifan Lu
*/

package com.yifanlu;

public class ConsoleCharInfo {
	
	final public static int FOREGROUND_BLUE = 0x0001, FOREGROUND_GREEN = 0x0002, FOREGROUND_RED = 0x0004, FOREGROUND_INTENSITY = 0x0008;
	final public static int BACKGROUND_BLUE = 0x0010, BACKGROUND_GREEN = 0x0020, BACKGROUND_RED = 0x0040, BACKGROUND_INTENSITY = 0x0080;
	final public static int COMMON_LVB_LEADING_BYTE = 0x0100, COMMON_LVB_TRAILING_BYTE = 0x0200, COMMON_LVB_GRID_HORIZONTAL = 0x0400, COMMON_LVB_GRID_LVERTICAL = 0x0800, COMMON_LVB_GRID_RVERTICAL = 0x1000, COMMON_LVB_REVERSE_VIDEO = 0x4000, COMMON_LVB_UNDERSCORE = 0x8000;
	
	public char Char;
	public int Attributes;

    public ConsoleCharInfo(char Char, int Attributes) {
    	this.Char = Char;
    	this.Attributes = Attributes;
    }
    
}