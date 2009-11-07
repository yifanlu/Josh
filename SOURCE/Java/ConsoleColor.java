/**
 * Josh - Java Output Shell enHanced
 * Console Color
 * By Yifan Lu
*/

package com.yifanlu;

public class ConsoleColor {
	final static int FOREGROUND_BLUE = 0x0001;
	final static int FOREGROUND_GREEN = 0x0002;
	final static int FOREGROUND_RED = 0x0004;
	final static int FOREGROUND_INTENSITY = 0x0008;
	final static int BACKGROUND_BLUE = 0x0010;
	final static int BACKGROUND_GREEN = 0x0020;
	final static int BACKGROUND_RED = 0x0040;
	final static int BACKGROUND_INTENSITY = 0x0080;
	static boolean red = false, blue = false, green = false, bright = false, background = false;
	public int color = 0x0000;
	ConsoleColor(){ color = 0x0000; }
	ConsoleColor(boolean red, boolean blue, boolean green, boolean bright, boolean background){
		int color = 0x0000;
		this.red = red; this.blue = blue; this.green = green; this.bright = bright; this.background = background;
		if(background)
		{
			if(red)
				color |= BACKGROUND_RED;
			if(blue)
				color |= BACKGROUND_BLUE;
			if(green)
				color |= BACKGROUND_GREEN;
			if(bright)
				color |= BACKGROUND_INTENSITY;
		}else{
			if(red)
				color |= FOREGROUND_RED;
			if(blue)
				color |= FOREGROUND_BLUE;
			if(green)
				color |= FOREGROUND_GREEN;
			if(bright)
				color |= FOREGROUND_INTENSITY;
		}
		this.color = color;
	}
	public static ConsoleColor RED(){ return RED(false); }
	public static ConsoleColor RED(boolean bright){ return new ConsoleColor(true,false,false,bright,false); }
	public static ConsoleColor BLUE(){ return BLUE(false); }
	public static ConsoleColor BLUE(boolean bright){ return new ConsoleColor(false,true,false,bright,false); }
	public static ConsoleColor GREEN(){ return GREEN(false); }
	public static ConsoleColor GREEN(boolean bright){ return new ConsoleColor(false,false,true,bright,false); }
	public static ConsoleColor YELLOW(){ return YELLOW(false); }
	public static ConsoleColor YELLOW(boolean bright){ return new ConsoleColor(true,false,true,bright,false); }
	public static ConsoleColor CRYAN(){ return CRYAN(false); }
	public static ConsoleColor CRYAN(boolean bright){ return new ConsoleColor(false,true,true,bright,false); }
	public static ConsoleColor MAGENTA(){ return MAGENTA(false); }
	public static ConsoleColor MAGENTA(boolean bright){ return new ConsoleColor(true,true,false,bright,false); }
	public static ConsoleColor WHITE(){ return WHITE(false); }
	public static ConsoleColor WHITE(boolean bright){ return new ConsoleColor(true,true,true,bright,false); }
	public static ConsoleColor BLACK(){ return BLACK(false); }
	public static ConsoleColor BLACK(boolean bright){ return new ConsoleColor(false,false,false,bright,false); }
}