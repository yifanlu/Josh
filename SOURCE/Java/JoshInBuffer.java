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
	
	public ConsoleEvent peekConsoleInput(){ return peekConsoleInput(1); }
	public ConsoleEvent peekConsoleInput(int length)
	{
		int[] rawData = Josh.PEEKCONSOLEINPUT(handle.HANDLE, length);
		if(rawData[0] == ConsoleEvent.KEY_EVENT)
			return new ConsoleEvent(rawData[0], rawData[1] == 1 ? true : false, rawData[2], rawData[3], rawData[4], (char)rawData[5], rawData[6]);
		else if(rawData[0] == ConsoleEvent.MOUSE_EVENT)
			return new ConsoleEvent(rawData[0], new ConsoleCoord(rawData[1], rawData[2]), rawData[3], rawData[4], rawData[5]);
		else if(rawData[0] == ConsoleEvent.WINDOW_BUFFER_SIZE_EVENT)
			return new ConsoleEvent(rawData[0], new ConsoleCoord(rawData[1], rawData[2]));
		else
			return new ConsoleEvent(rawData[0]);
	}
	public ConsoleEvent readConsoleInput()
	{
		int[] rawData = Josh.READCONSOLEINPUT(handle.HANDLE, 1);
		if(rawData[0] == ConsoleEvent.KEY_EVENT)
			return new ConsoleEvent(rawData[0], rawData[1] == 1 ? true : false, rawData[2], rawData[3], rawData[4], (char)rawData[5], rawData[6]);
		else if(rawData[0] == ConsoleEvent.MOUSE_EVENT)
			return new ConsoleEvent(rawData[0], new ConsoleCoord(rawData[1], rawData[2]), rawData[3], rawData[4], rawData[5]);
		else if(rawData[0] == ConsoleEvent.WINDOW_BUFFER_SIZE_EVENT)
			return new ConsoleEvent(rawData[0], new ConsoleCoord(rawData[1], rawData[2]));
		else
			return new ConsoleEvent(rawData[0]);
	}
	public void writeConsoleInput(ConsoleEvent event)
	{
		int[] data;
		if(event.eventType == ConsoleEvent.KEY_EVENT)
		{
			data = new int[7];
			data[0] = event.eventType;
			data[1] = event.keyboardEvent.bKeyDown ? 1 : 0;
			data[2] = event.keyboardEvent.wRepeatCount;
			data[3] = event.keyboardEvent.wVirtualKeyCode;
			data[4] = event.keyboardEvent.wVirtualScanCode;
			data[5] = (int)event.keyboardEvent.uChar;
			data[6] = event.keyboardEvent.dwControlKeyState;
		}
		else if(event.eventType == ConsoleEvent.MOUSE_EVENT)
		{
			data = new int[6];
			data[0] = event.eventType;
			data[1] = event.mouseEvent.dwMousePosition.X;
			data[2] = event.mouseEvent.dwMousePosition.Y;
			data[3] = event.mouseEvent.dwButtonState;
			data[4] = event.mouseEvent.dwControlKeyState;
			data[5] = event.mouseEvent.dwEventFlags;
		}
		else if(event.eventType == ConsoleEvent.WINDOW_BUFFER_SIZE_EVENT)
		{
			data = new int[3];
			data[0] = event.eventType;
			data[1] = event.bufferChangedEvent.dwSize.X;
			data[1] = event.bufferChangedEvent.dwSize.Y;
		Josh.WRITECONSOLEINPUT(handle.HANDLE, event.eventType, data, 1);
		}
	}
}