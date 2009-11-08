/**
 * Josh - Java Output Shell enHanced
 * Console Input Buffer Methods
 * By Yifan Lu
*/

package com.yifanlu;

import com.yifanlu.Josh;

public class JoshInBuffer {
	private ConsoleHandle handle;
	public JoshInBuffer(ConsoleHandle handle){ this.handle = handle; }
	
	public int getNumberOfConsoleInputEvents()
	{
		return Josh.GETNUMBEROFCONSOLEINPUTEVENTS(handle.HANDLE);
	}
}