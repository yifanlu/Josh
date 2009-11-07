/**
 * Josh - Java Output Shell enHanced
 * Console Buffer Manager
 * By Yifan Lu
*/

package com.yifanlu;

import com.yifanlu.Josh;

public class ConsoleBufferManager {
	private ConsoleHandle handle;
	public ConsoleBufferManager(ConsoleHandle handle){ this.handle = handle; }
	
	public void clearConsole(){ clearConsole(ConsoleColor.BLACK()); }
	public void clearConsole(ConsoleColor background)
	{
		Josh.CLEARSCREEN(handle.HANDLE, new ConsoleColor(background.red, background.blue, background.green, background.bright, true).color);
	}
	public int[] getConsoleScreenBufferSize()
	{
		return Josh.GETCONSOLESCREENBUFFERSIZE(handle.HANDLE);
	}
	public void setConsoleCursorPosition(int[] coord){ setConsoleCursorPosition(coord[0], coord[1]); }
	public void setConsoleCursorPosition(int x, int y){ setConsoleCursorPosition(handle, x, y); }
	public void setConsoleCursorPosition(ConsoleHandle handle, int x, int y)
	{
		Josh.SETCONSOLECURSORPOSITION(handle.HANDLE, x, y);
	}
	public void highlight(int[] coords, int length, ConsoleColor foreground){ highlight(coords[0], coords[1], length, foreground, new ConsoleColor()); }
	public void highlight(int x, int y, int length, ConsoleColor foreground){ highlight(x, y, length, foreground, new ConsoleColor()); }
	public void highlight(int[] coords, int length, ConsoleColor foreground, ConsoleColor background){ highlight(coords[0], coords[1], length, foreground, background); }
	public void highlight(int x, int y, int length, ConsoleColor foreground, ConsoleColor background)
	{
		background = new ConsoleColor(background.red, background.blue, background.green, background.bright, true);
		int attribute = foreground.color | background.color;
		Josh.FILLCONSOLEOUTPUTATTRIBUTE(handle.HANDLE, attribute, length, x, y);
	}
	public int[] getConsoleCursorInfo()
	{
		return Josh.GETCONSOLECURSORINFO(handle.HANDLE);
	}
}