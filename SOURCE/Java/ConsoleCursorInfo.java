/**
 * Josh - Java Output Shell enHanced
 * Psudostructure: Represents a cursor info
 * By Yifan Lu
*/

package com.yifanlu;

public class ConsoleCursorInfo {
	
	public int size = 25;
	public boolean visible = true;

    public ConsoleCursorInfo(){}
    public ConsoleCursorInfo(int size, boolean visible) {
    	this.size = size;
    	this.visible = visible;
    }
    
}