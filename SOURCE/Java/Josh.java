/**
 * Josh - Java Output Shell enHanced
 * Central Class
 * By Yifan Lu
*/

package com.yifanlu;

public class Josh {
	static { System.loadLibrary("JoshCore"); }
	
	final static int STD_INPUT_HANDLE = -10;
	final static int STD_OUTPUT_HANDLE = -11;
	final static int STD_ERROR_HANDLE = -12;
	
	private static ConsoleHandle outHandle = new ConsoleHandle(GETSTDHANDLE(STD_OUTPUT_HANDLE));
	private static ConsoleHandle inHandle = new ConsoleHandle(GETSTDHANDLE(STD_INPUT_HANDLE));
	private static ConsoleHandle errorHandle = new ConsoleHandle(GETSTDHANDLE(STD_ERROR_HANDLE));
	
	public static JoshOutput out = new JoshOutput(outHandle);
	public static JoshInput in = new JoshInput(inHandle);
	public static JoshOutBuffer outBuffer = new JoshOutBuffer(outHandle);
	public static JoshInBuffer inBuffer = new JoshInBuffer(inHandle);
	
	native static long CREATECONSOLESCREENBUFFER(boolean read, boolean write, int shared);
	native static void FILLCONSOLEOUTPUTATTRIBUTE(long pointer, int attribute, int length, int x, int y);
	native static void FILLCONSOLEOUTPUTCHARACTER(long pointer, char character, int length, int x, int y);
	native static int[] GETCONSOLECURSORINFO(long pointer);
	native static int GETCONSOLEDISPLAYMODE();
	native static int[] GETCONSOLEFONTSIZE(long pointer);
	native static int[] GETCONSOLEHISTORYINFO();
	native static int GETCONSOLEMODE(long pointer);
	native static String GETCONSOLEORGINIALTITLE();
	native static int[] GETCONSOLESCREENBUFFERINFO(long pointer);
	native static int[] GETCONSOLESELECTIONINFO();
	native static String GETCONSOLETITLE();
	native static long GETCONSOLEWINDOW();
	native static int[] GETCURRENTCONSOLEFONT(long pointer, boolean maximumWindow);
	native static int[] GETLARGESTCONSOLEWINDOWSIZE(long pointer);
	native static int GETNUMBEROFCONSOLEINPUTEVENTS(long pointer);
	native static int GETNUMBEROFCONSOLEMOUSEBUTTONS();
	native static long GETSTDHANDLE(int handle);
	native static int[] PEEKCONSOLEINPUT(long pointer, int length);
	native static int[] READCONSOLEINPUT(long pointer, int length);
	native static String READCONSOLE(long pointer);
	native static int[][] READCONSOLEOUTPUT(long pointer, int sizeX, int sizeY, int coordX, int coordY);
	native static int[] READCONSOLEOUTPUTATTRIBUTE(long pointer, int x, int y, int length);
	native static int[] READCONSOLEOUTPUTCHARACTER(long pointer, int x, int y, int length);
	native static void SCROLLCONSOLESCREENBUFFER(long pointer, int scrollLeft, int scrollTop, int scrollRight, int scrollBottom, int clipLeft, int clipTop, int clipRight, int clipBottom, int toX, int toY, char fillChar, int fillAttribute);
	native static void SETCONSOLEACTIVESCREENBUFFER(long pointer);
	native static void SETCONSOLECURSORINFO(long pointer, int size, boolean visible);
	native static void SETCONSOLECURSORPOSITION(long pointer, int x, int y);
	native static void SETCONSOLEDISPLAYMODE(long pointer, int mode);
	native static void SETCONSOLEHISTORYINFO(int bufferSize, int numberOfBuffers, int flags);
	native static void SETCONSOLEMODE(long pointer, int flags);
	native static void SETCONSOLESCREENBUFFERINFOEX(long pointer, int sizeX, int sizeY, int cursorX, int cursorY, int attributes, int windowLeft, int windowTop, int windowRight, int windowBottom, int maxX, int maxY);
	native static void SETCONSOLESCREENBUFFERSIZE(long pointer, int X, int Y);
	native static void SETTEXTATTRIBUTE(long pointer, int foreground, int background);
	native static void SETCONSOLETITLE(String title);
	native static void SETCONSOLEWINDOWINFO(long pointer, boolean absolute, int windowLeft, int windowTop, int windowRight, int windowBottom);
	native static void SETCURRENTCONSOLEFONTEX(long pointer, boolean maximumWindow, int font, int sizeX, int sizeY);
	native static void SETSTDHANDLE(int handle, long pointer);
	native static void WRITECONSOLE(long pointer, String output);
	native static void WRITECONSOLEINPUT(long pointer, int eventType, int[] data, int length);
	native static void WRITECONSOLEOUTPUT(long pointer, int[][] data, int sizeX, int sizeY, int coordX, int coordY);
	native static void WRITECONSOLEOUTPUTATTRIBUTE(long pointer, int[] data, int length, int x, int y);
	native static void WRITECONSOLEOUTPUTCHARACTER(long pointer, int[] data, int length, int x, int y);
	native static void BEEP(int frequency, int duration);
	native static void CLEARSCREEN(long pointer, int background);
	native static void SETCONSOLEFONT(long pointer, int fontIndex);

	public static String getConsoleTitle()
	{
		return GETCONSOLETITLE();
	}
	public static String getConsoleOrginialTitle()
	{
		return GETCONSOLEORGINIALTITLE();
	}
	public static void setConsoleTitle(String title)
	{
		SETCONSOLETITLE(title);
	}
	public static boolean isFullScreen()
	{
		return Josh.GETCONSOLEDISPLAYMODE() == 2 ? true : false;
	}
	public static ConsoleHistoryInfo getConsoleHistoryInfo()
	{
		int[] rawData = GETCONSOLEHISTORYINFO();
		return new ConsoleHistoryInfo(rawData[0], rawData[1], rawData[2]);
	}
	public static void setConsoleHistoryInfo(ConsoleHistoryInfo info)
	{
		SETCONSOLEHISTORYINFO(info.historyBufferSize, info.numberOfHistoryBuffers, info.dwFlags);
	}
	public static ConsoleSelectionInfo getConsoleSelectionInfo()
	{
		int[] rawData = GETCONSOLESELECTIONINFO();
		return new ConsoleSelectionInfo(rawData[0], new ConsoleCoord(rawData[1], rawData[2]), new ConsoleSmallRect(rawData[3], rawData[4], rawData[5], rawData[6]));
	}
	public static ConsoleHandle getConsoleWindow()
	{
		return new ConsoleHandle(GETCONSOLEWINDOW());
	}
	public static int getNumberOfConsoleMouseButtons()
	{
		return GETNUMBEROFCONSOLEMOUSEBUTTONS();
	}
	public static ConsoleHandle getStdHandle(int type)
	{
		return new ConsoleHandle(GETSTDHANDLE(type));
	}
	public static void setStdHandle(ConsoleBuffer buffer, int forHandle)
	{
		SETSTDHANDLE(forHandle, buffer.handle.HANDLE);
	}
	public static void Beep(){ Beep(800, 200); }
	public static void Beep(int frequency, int duration)
	{
		BEEP(frequency, duration);
	}
	public static void setConsoleActiveScreenBuffer(ConsoleBuffer screen)
	{
		Josh.SETCONSOLEACTIVESCREENBUFFER(screen.handle.HANDLE);
	}
}