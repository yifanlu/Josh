/**
 * Josh - Java Output Shell enHanced
 * Controls Output
 * By Yifan Lu
*/

package com.yifanlu;

import com.yifanlu.Josh;

public class JoshOut {
	private ConsoleHandle handle;
	public JoshOut(ConsoleHandle handle){ this.handle = handle; }
	
	public void setOutputColor(ConsoleColor foreground){ setOutputColor(foreground,ConsoleColor.BLACK()); }
	public void setOutputColor(ConsoleColor foreground, ConsoleColor background)
	{
		Josh.SETTEXTATTRIBUTE(handle.HANDLE, foreground.color, new ConsoleColor(background.red, background.blue, background.green, background.bright, true).color);
	}
	public void println(String output){ print(output + "\n"); }
	public void print(String output)
	{
		Josh.WRITECONSOLE(handle.HANDLE,output);
	}
	public void printhighlightln(Object ... objects)
	{
		int count = 0;
		for(Object obj : objects)
		{
			if(count % 2 == 0)
				setOutputColor(ConsoleColor.WHITE(),(ConsoleColor)obj);
			else
				print((String)obj);
			count++;
		}
		if(count % 2 == 0) setOutputColor(ConsoleColor.WHITE(),ConsoleColor.BLACK());
	}
	public void printcolorln(Object ... objects)
	{
		int count = 0;
		for(Object obj : objects)
		{
			if(count % 2 == 0)
				setOutputColor((ConsoleColor)obj,ConsoleColor.BLACK());
			else
				print((String)obj);
			count++;
		}
		if(count % 2 == 0) setOutputColor(ConsoleColor.WHITE());
	}
	public void fillWithChar(int[] coords, int length, char character){ fillWithChar(coords[0], coords[1], length, character); }
	public void fillWithChar(int x, int y, int length, char character)
	{
		Josh.FILLCONSOLEOUTPUTCHARACTER(handle.HANDLE, character, length, x, y);
	}
}