/**
 * Josh - Java Output Shell enHanced
 * Console Input Buffer Methods
 * By Yifan Lu
*/

package com.yifanlu;

import com.yifanlu.Josh;

public class JoshInput {
	private ConsoleHandle handle;
	public JoshInput(ConsoleHandle handle){ this.handle = handle; }
	
	public String readLine()
	{
		return Josh.READCONSOLE(handle.HANDLE);
	}
	public char readChar()
	{
		return Josh.READCONSOLE(handle.HANDLE).charAt(0);
	}
	public int readInt()
	{
		String line = readLine();
		return Integer.parseInt(line);
	}
	public double readDouble()
	{
		String line = readLine();
		return Double.parseDouble(line);
	}
}