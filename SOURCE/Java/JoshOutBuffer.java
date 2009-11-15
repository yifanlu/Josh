/**
 * Josh - Java Output Shell enHanced
 * Console Output Buffer Methods
 * By Yifan Lu
*/

package com.yifanlu;

import com.yifanlu.Josh;

public class JoshOutBuffer {
	private ConsoleHandle handle;
	public JoshOutBuffer(ConsoleHandle handle){ this.handle = handle; }
	
	public ConsoleCursorInfo getConsoleCursorInfo()
	{
		int[] rawData = Josh.GETCONSOLECURSORINFO(handle.HANDLE);
		return new ConsoleCursorInfo(rawData[0], rawData[1] == 1 ? true : false);
	}
	public void setConsoleCursorInfo(ConsoleCursorInfo info)
	{
		Josh.SETCONSOLECURSORINFO(handle.HANDLE, info.size, info.visible);
	}
	
	public ConsoleCoord getConsoleFontSize()
	{
		int[] rawData = Josh.GETCONSOLEFONTSIZE(handle.HANDLE);
		return new ConsoleCoord(rawData[0], rawData[1]);
	}
	
	public static final int ENABLE_ECHO_INPUT = 0x0004, ENABLE_INSERT_MODE = 0x0020, ENABLE_LINE_INPUT = 0x0002, ENABLE_MOUSE_INPUT = 0x0010, ENABLE_PROCESSED_INPUT = 0x0001, ENABLE_QUICK_EDIT_MODE = 0x0040, ENABLE_WINDOW_INPUT = 0x0008, ENABLE_PROCESSED_OUTPUT = 0x0001, ENABLE_WRAP_AT_EOL_OUTPUT = 0x0002;
	public int getConsoleMode()
	{
		return Josh.GETCONSOLEMODE(handle.HANDLE);
	}
	public void setConsoleMode(int flags)
	{
		Josh.SETCONSOLEMODE(handle.HANDLE, flags);
	}
	
	public ConsoleScreenBufferInfo getConsoleScreenBufferInfo()
	{
		int[] rawData = Josh.GETCONSOLESCREENBUFFERINFO(handle.HANDLE);
		return new ConsoleScreenBufferInfo(new ConsoleCoord(rawData[0], rawData[1]), new ConsoleCoord(rawData[2], rawData[3]), rawData[4], new ConsoleSmallRect(rawData[5], rawData[6], rawData[7], rawData[8]), new ConsoleCoord(rawData[9], rawData[10]));
	}
	public void setConsoleScreenBufferInfo(ConsoleScreenBufferInfo info)
	{
		Josh.SETCONSOLESCREENBUFFERINFOEX(handle.HANDLE, info.dwSize.X, info.dwSize.Y, info.dwCursorPosition.X, info.dwCursorPosition.Y, info.wAttributes, info.srWindow.Left, info.srWindow.Top, info.srWindow.Right, info.srWindow.Bottom, info.dwMaximumWindowSize.X, info.dwMaximumWindowSize.Y);
	}
	
	public ConsoleFontInfo getCurrentConsoleFont(boolean forMaximumWindowSize)
	{
		int[] rawData = Josh.GETCURRENTCONSOLEFONT(handle.HANDLE, forMaximumWindowSize);
		return new ConsoleFontInfo(rawData[0], new ConsoleCoord(rawData[1], rawData[2]));
	}
	public void setCurrentConsoleFontEx(ConsoleFontInfo font, boolean forMaximumWindowSize)
	{
		Josh.SETCURRENTCONSOLEFONTEX(handle.HANDLE, forMaximumWindowSize, font.nFont, font.dwFontSize.X, font.dwFontSize.Y);
	}
	public void setCurrentConsoleFont(int fontIndex)
	{
		Josh.SETCONSOLEFONT(handle.HANDLE, fontIndex);
	}
	
	public ConsoleCoord getLargestConsoleWindowSize()
	{
		int[] rawData = Josh.GETLARGESTCONSOLEWINDOWSIZE(handle.HANDLE);
		return new ConsoleCoord(rawData[0], rawData[1]);
	}
	
	public ConsoleCharInfo[] readConsoleOutput(ConsoleCoord size, ConsoleCoord coord)
	{
		int[][] rawData = Josh.READCONSOLEOUTPUT(handle.HANDLE, size.X, size.Y, coord.X, coord.Y);
		ConsoleCharInfo[] data = new ConsoleCharInfo[rawData.length];
		int i = 0;
		for(int[] x : rawData)
		{
			data[i] = new ConsoleCharInfo((char)x[0], x[1]);
			i++;
		}
		return data;
	}
	
	public ConsoleColor[] readConsoleOutputAttribute(ConsoleCoord coord, int length)
	{
		int[] rawData = Josh.READCONSOLEOUTPUTATTRIBUTE(handle.HANDLE, coord.X, coord.Y, length);
		ConsoleColor[] attributes = new ConsoleColor[rawData.length];
		int i = 0;
		for(int x : rawData)
		{
			attributes[i] = new ConsoleColor(x);
			i++;
		}
		return attributes;
	}
	public void writeConsoleOutputAttribute(ConsoleColor[] attributes, ConsoleCoord coord, int length)
	{
		int[] data = new int[attributes.length];
		for(int x = 0; x < attributes.length; x++)
			data[x] = attributes[x].color;
		Josh.WRITECONSOLEOUTPUTATTRIBUTE(handle.HANDLE, data, length, coord.X, coord.Y);
	}
	
	public char[] readConsoleOutputChar(ConsoleCoord coord, int length)
	{
		int[] rawData = Josh.READCONSOLEOUTPUTCHARACTER(handle.HANDLE, coord.X, coord.Y, length);
		char[] chars = new char[rawData.length];
		int i = 0;
		for(int x : rawData)
		{
			chars[i] = (char)x;
			i++;
		}
		return chars;
	}
	public void writeConsoleOutputChar(char[] chars, ConsoleCoord coord, int length)
	{
		int[] data = new int[chars.length];
		for(int x = 0; x < chars.length; x++)
			data[x] = (int)chars[x];
		Josh.WRITECONSOLEOUTPUTCHARACTER(handle.HANDLE, data, length, coord.X, coord.Y);
	}
	
	public void scrollConsoleScreenBuffer(ConsoleSmallRect block, ConsoleCoord destination){ scrollConsoleScreenBuffer(block, destination, new ConsoleCharInfo(' ',0)); }
	public void scrollConsoleScreenBuffer(ConsoleSmallRect block, ConsoleCoord destination, ConsoleCharInfo fill){ scrollConsoleScreenBuffer(block, new ConsoleSmallRect(-1,-1,-1,-1), destination, fill); }
	public void scrollConsoleScreenBuffer(ConsoleSmallRect block, ConsoleSmallRect clip, ConsoleCoord destination, ConsoleCharInfo fill)
	{
		Josh.SCROLLCONSOLESCREENBUFFER(handle.HANDLE, block.Left, block.Top, block.Right, block.Bottom, clip.Left, clip.Top, clip.Right, clip.Bottom, destination.X, destination.Y, fill.Char, fill.Attributes);
	}
	
	public void setFullScreen(boolean fullScreen)
	{
		final int CONSOLE_FULLSCREEN_MODE = 1;
		final int CONSOLE_WINDOWED_MODE = 2;
		if(fullScreen)
			Josh.SETCONSOLEDISPLAYMODE(handle.HANDLE, CONSOLE_FULLSCREEN_MODE);
		else
			Josh.SETCONSOLEDISPLAYMODE(handle.HANDLE, CONSOLE_WINDOWED_MODE);
	}
	
	public void setConsoleWindowInfo(boolean absolute, ConsoleSmallRect newSize)
	{
		Josh.SETCONSOLEWINDOWINFO(handle.HANDLE, absolute, newSize.Left, newSize.Top, newSize.Right, newSize.Bottom);
	}
}