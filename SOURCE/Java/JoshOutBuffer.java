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
	
	public ConsoleScreenBufferInfo getConsoleScreenBufferInfo()
	{
		int[] rawData = Josh.GETCONSOLESCREENBUFFERINFO(handle.HANDLE);
		return new ConsoleScreenBufferInfo(new ConsoleCoord(rawData[0], rawData[1]), new ConsoleCoord(rawData[2], rawData[3]), rawData[4], new ConsoleSmallRect(rawData[5], rawData[6], rawData[7], rawData[8]), new ConsoleCoord(rawData[9], rawData[10]));
	}
	
	public ConsoleCoord getLargestConsoleWindowSize()
	{
		int[] rawData = Josh.GETLARGESTCONSOLEWINDOWSIZE(handle.HANDLE);
		return new ConsoleCoord(rawData[0], rawData[1]);
	}
}