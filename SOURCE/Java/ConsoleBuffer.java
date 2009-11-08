/**
 * Josh - Java Output Shell enHanced
 * The non-static version of Josh. Allows multiple screen buffers.
 * By Yifan Lu
*/

package com.yifanlu;

public class ConsoleBuffer extends Josh {
	public ConsoleHandle handle;
	public JoshOutput out;
	public JoshOutBuffer outBuffer;
	public boolean inBuffer = false; // No input in hidden buffers!
	
	public ConsoleBuffer(){ newConsoleBuffer(true,true); }
	public ConsoleBuffer(boolean read, boolean write){ newConsoleBuffer(read,write); }
	void newConsoleBuffer(boolean read, boolean write)
	{
		long pointer = super.CREATECONSOLESCREENBUFFER(read,write,0);
		handle = new ConsoleHandle(pointer);
		out = new JoshOutput(handle);
		outBuffer = new JoshOutBuffer(handle);
	}
}