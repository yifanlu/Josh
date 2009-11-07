/**
 * Josh - Java Output Shell enHanced
 * Central Class
 * By Yifan Lu
*/

package com.yifanlu;

public class Josh {
	static { System.loadLibrary("JoshCore"); }
	
	private static ConsoleHandle handle = new ConsoleHandle(GETSTDHANDLE());
	
	public static JoshOut out = new JoshOut(handle);
	public static ConsoleBufferManager buffer = new ConsoleBufferManager(handle);
	
	native static void ADDCONSOLEALIAS(String source, String target, String exename);
	native static long CREATECONSOLESCREENBUFFER(boolean read, boolean write, int shared);
	native static void FILLCONSOLEOUTPUTATTRIBUTE(long pointer, int attribute, int length, int x, int y);
	native static void FILLCONSOLEOUTPUTCHARACTER(long pointer, char character, int length, int x, int y);
	native static int[] GETCONSOLECURSORINFO(long pointer);
	native static int GETCONSOLEDISPLAYMODE(long pointer);
	native static int[] GETCONSOLEFONTSIZE(long pointer);
	native static int[] GETCONSOLEHISTORYINFO();
	native static int GETCONSOLEMODE(long pointer);
	native static int[] GETCONSOLESCREENBUFFERSIZE(long pointer);
	native static void SETCONSOLETITLE(String title);
	native static void SETTEXTATTRIBUTE(long pointer, int foreground, int background);
	native static void CLEARSCREEN(long pointer, int background);
	native static void SETCONSOLECURSORPOSITION(long pointer, int x, int y); 
	native static long GETSTDHANDLE();
	native static void WRITECONSOLE(long pointer, String output);

	public static void setConsoleTitle(String title)
	{
		SETCONSOLETITLE(title);
	}
	public static ConsoleHandle getStdHandle()
	{
		return handle;
	}
}