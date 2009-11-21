/**
 * Josh - Java Output Shell enHanced
 * Console Output
 * By Yifan Lu
*/

package com.yifanlu;

public class JoshOutput {
	private ConsoleHandle handle;
	public JoshOutput(ConsoleHandle handle){ this.handle = handle; }
	
	public void clearConsole(){ clearConsole(ConsoleColor.BLACK()); }
	public void clearConsole(ConsoleColor background)
	{
		Josh.CLEARSCREEN(handle.HANDLE, new ConsoleColor(background.red, background.blue, background.green, background.bright, true).color);
	}
	public void setConsoleCursorPosition(ConsoleCoord coord){ setConsoleCursorPosition(coord.X, coord.Y); }
	public void setConsoleCursorPosition(int x, int y){ setConsoleCursorPosition(handle, x, y); }
	public void setConsoleCursorPosition(ConsoleHandle handle, int x, int y)
	{
		Josh.SETCONSOLECURSORPOSITION(handle.HANDLE, x, y);
	}
	public void highlight(ConsoleCoord coord, int length, ConsoleColor foreground){ highlight(coord.X, coord.Y, length, foreground, new ConsoleColor()); }
	public void highlight(int x, int y, int length, ConsoleColor foreground){ highlight(x, y, length, foreground, new ConsoleColor()); }
	public void highlight(ConsoleCoord coord, int length, ConsoleColor foreground, ConsoleColor background){ highlight(coord.X, coord.Y, length, foreground, background); }
	public void highlight(int x, int y, int length, ConsoleColor foreground, ConsoleColor background)
	{
		if(!background.background)
			background = new ConsoleColor(background.red, background.blue, background.green, background.bright, true);
		int attribute = foreground.color | background.color;
		Josh.FILLCONSOLEOUTPUTATTRIBUTE(handle.HANDLE, attribute, length, x, y);
	}
	
	public void setOutputColor(ConsoleColor foreground){ setOutputColor(foreground,ConsoleColor.BLACK()); }
	public void setOutputColor(ConsoleColor foreground, ConsoleColor background)
	{
		if(!background.background)
			background = new ConsoleColor(background.red, background.blue, background.green, background.bright, true);
		Josh.SETTEXTATTRIBUTE(handle.HANDLE, foreground.color, background.color);
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
	public void fillWithChar(ConsoleCoord coord, int length, char character){ fillWithChar(coord.X, coord.Y, length, character); }
	public void fillWithChar(int x, int y, int length, char character)
	{
		Josh.FILLCONSOLEOUTPUTCHARACTER(handle.HANDLE, character, length, x, y);
	}
}