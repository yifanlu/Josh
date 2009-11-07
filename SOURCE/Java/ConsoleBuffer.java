/**
 * Josh - Java Output Shell enHanced
 * Central Class
 * By Yifan Lu
*/

package com.yifanlu;

public class ConsoleBuffer extends Josh {
	public ConsoleHandle handle;
	public static JoshOut out;
	public static ConsoleBufferManager buffer;
	
	public ConsoleBuffer(){ newConsoleBuffer(true,true); }
	public ConsoleBuffer(boolean read, boolean write){ newConsoleBuffer(read,write); }
	void newConsoleBuffer(boolean read, boolean write)
	{
		long pointer = super.CREATECONSOLESCREENBUFFER(read,write,0);
		handle = new ConsoleHandle(pointer);
		out = new JoshOut(handle);
		buffer = new ConsoleBufferManager(handle);
	}
}