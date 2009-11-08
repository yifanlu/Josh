/**
 * Josh - Java Output Shell enHanced
 * Psudostructure: Represents a rectangle
 * By Yifan Lu
*/

package com.yifanlu;

public class ConsoleSmallRect {
	
	public short Left;
	public short Top;
	public short Right;
	public short Bottom;
	
    public ConsoleSmallRect(int Left, int Top, int Right, int Bottom) {
    	this.Left = (short)Left;
    	this.Top = (short)Top;
    	this.Right = (short)Right;
    	this.Bottom = (short)Bottom;
    }
    public ConsoleSmallRect(short Left, short Top, short Right, short Bottom) {
    	this.Left = Left;
    	this.Top = Top;
    	this.Right = Right;
    	this.Bottom = Bottom;
    }
    
}