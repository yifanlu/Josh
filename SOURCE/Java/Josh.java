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
	native static int[] GETLARGESTCONSOLEWINDOWSIZE(long pointer);
	native static int GETNUMBEROFCONSOLEINPUTEVENTS(long pointer);
	native static int GETNUMBEROFCONSOLEMOUSEBUTTONS();
	native static long GETSTDHANDLE(int handle);
	native static void SETCONSOLETITLE(String title);
	native static void SETTEXTATTRIBUTE(long pointer, int foreground, int background);
	native static void CLEARSCREEN(long pointer, int background);
	native static void SETCONSOLECURSORPOSITION(long pointer, int x, int y); 
	native static void WRITECONSOLE(long pointer, String output);

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
}