package com.yifanlu.Josh;

import com.yifanlu.Josh.JoshBuffer;
import com.yifanlu.Josh.ConsoleHandle;
import com.yifanlu.Josh.ConsoleCoord;
import com.yifanlu.Josh.ConsoleCursorInfo;
import com.yifanlu.Josh.ConsoleScreenBufferInfo;
import com.yifanlu.Josh.ConsoleFontInfo;
import com.yifanlu.Josh.ConsoleCharInfo;
import com.yifanlu.Josh.ConsoleAttribute;
import com.yifanlu.Josh.ConsoleSmallRect;
import com.yifanlu.Josh.OSNotSupportedException;

/**
 * Provides native access to the output buffer.
 * This allows direct manipulation of the console screen buffer.<br />
 * Objects from this class are immutable.
 *
 * @author Yifan Lu
 * @version 1.2, 05/16/10
 * @since 0.1
 */
public class JoshOutBuffer extends JoshBuffer {
	/**
     * Used in {@link JoshOutBuffer#getConsoleMode} and {@link JoshOutBuffer#setConsoleMode} to modify the console mode.
     */
	public static final int ENABLE_ECHO_INPUT = 0x0004, ENABLE_INSERT_MODE = 0x0020, ENABLE_LINE_INPUT = 0x0002, ENABLE_MOUSE_INPUT = 0x0010, ENABLE_PROCESSED_INPUT = 0x0001, ENABLE_QUICK_EDIT_MODE = 0x0040, ENABLE_WINDOW_INPUT = 0x0008, ENABLE_PROCESSED_OUTPUT = 0x0001, ENABLE_WRAP_AT_EOL_OUTPUT = 0x0002;
	
	/**
     * Creates a new output buffer for direct manipulation of the output buffer.
	 * 
	 * @param handle A memory location that this buffer represents.
     */
	public JoshOutBuffer(ConsoleHandle handle){ this.setHandle(handle); }
	
	/** 
	 * Retrieves information about the size and visibility of the cursor for the specified console screen buffer.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms683163(v=VS.85).aspx">Windows' GetConsoleCursorInfo function</a>
	 * @return Information about the console's cursor.
	 */
	public ConsoleCursorInfo getConsoleCursorInfo(){
		int[] rawData = Josh.GETCONSOLECURSORINFO(getHandle().getMemoryLocation());
		return new ConsoleCursorInfo(rawData[0], rawData[1] == 1 ? true : false);
	}
	
	/** 
	 * Sets the size and visibility of the cursor for the specified console screen buffer.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms686019(v=VS.85).aspx">Windows' SetConsoleCursorInfo function</a>
	 * @param info Provides the new specifications for the console screen buffer's cursor.
	 */
	public void setConsoleCursorInfo(ConsoleCursorInfo info){
		Josh.SETCONSOLECURSORINFO(getHandle().getMemoryLocation(), info.getSize(), info.getVisible());
	}
	
	/** 
	 * Sets the cursor position in the specified console screen buffer.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms686025(v=VS.85).aspx">Windows' SetConsoleCursorPosition function</a>
	 * @param cursorPosition The new cursor position, in characters. The coordinates are the column and row of a screen buffer character cell. 
	 * The coordinates must be within the boundaries of the console screen buffer.
	 */
	public void setConsoleCursorPosition(ConsoleCoord cursorPosition){
		Josh.SETCONSOLECURSORPOSITION(getHandle().getMemoryLocation(), cursorPosition.getX(), cursorPosition.getY());
	}
	
	/** 
	 * <strong>XP Only</strong>: Retrieves the size of the font used by the specified console screen buffer.
	 * This method only works with Windows XP or higher.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms683165(v=VS.85).aspx">Windows' GetConsoleFontSize function</a>
	 * @return The width and height of each character in the font, in logical units. 
	 * The X member contains the width, while the Y member contains the height.
	 * @throws OSNotSupportedException If the current OS is not XP or higher.
	 */
	public ConsoleCoord getConsoleFontSize() throws OSNotSupportedException{
		int[] rawData = Josh.GETCONSOLEFONTSIZE(getHandle().getMemoryLocation());
		return new ConsoleCoord(rawData[0], rawData[1]);
	}
	
	/** 
	 * Retrieves the current input mode of a console's input buffer or the current output mode of a console screen buffer.
	 * Use the constants defined in this class to check the console mode, mix and match with the OR operator.
	 * When a console is created, all input modes except ENABLE_WINDOW_INPUT are enabled by default.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms683167(v=VS.85).aspx">Windows' GetConsoleMode function</a>
	 * @return The mode of the console.
	 */
	public int getConsoleMode(){
		return Josh.GETCONSOLEMODE(getHandle().getMemoryLocation());
	}
	
	/** 
	 * Sets the input mode of a console's input buffer or the output mode of a console screen buffer.
	 * When a console is created, all input modes except ENABLE_WINDOW_INPUT are enabled by default.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms686033(v=VS.85).aspx">Windows' SetConsoleMode function</a>
	 * @param flags Use the constants defined in this class to set the console mode, mix and match with the OR operator.
	 */
	public void setConsoleMode(int flags){
		Josh.SETCONSOLEMODE(getHandle().getMemoryLocation(), flags);
	}
	
	/** 
	 * Retrieves information about the specified console screen buffer.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms683171(v=VS.85).aspx">Windows' GetConsoleScreenBufferInfo function</a>
	 * @return The console screen buffer information.
	 */
	public ConsoleScreenBufferInfo getConsoleScreenBufferInfo(){
		int[] rawData = Josh.GETCONSOLESCREENBUFFERINFO(getHandle().getMemoryLocation());
		return new ConsoleScreenBufferInfo(new ConsoleCoord(rawData[0], rawData[1]), new ConsoleCoord(rawData[2], rawData[3]), rawData[4], new ConsoleSmallRect(rawData[5], rawData[6], rawData[7], rawData[8]), new ConsoleCoord(rawData[9], rawData[10]));
	}
	
	/** 
	 * <strong>Vista Only</strong>: Sets extended information about the specified console screen buffer.
	 * This method only works with Windows Vista or higher.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms686039(v=VS.85).aspx">Windows' SetConsoleScreenBufferInfoEx function</a>
	 * @param info The console screen buffer information.
	 * @throws OSNotSupportedException If the current OS is not Vista or higher.
	 */
	public void setConsoleScreenBufferInfo(ConsoleScreenBufferInfo info) throws OSNotSupportedException{
		Josh.SETCONSOLESCREENBUFFERINFOEX(getHandle().getMemoryLocation(), info.getSize().getX(), info.getSize().getY(), info.getCursorPosition().getX(), info.getCursorPosition().getY(), info.getAttributes(), info.getWindow().getLeft(), info.getWindow().getTop(), info.getWindow().getRight(), info.getWindow().getBottom(), info.getMaximumWindowSize().getX(), info.getMaximumWindowSize().getY());
	}
	
	/** 
	 * Sets the attributes of characters written or echoed to the console screen buffer.
	 * This function affects text written after the function call.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms686047(v=VS.85).aspx">Windows' SetConsoleTextAttribute function</a>
	 * @param attribute The character attributes.
	 */
	public void setConsoleTextAttribute(ConsoleAttribute attribute){
		Josh.SETTEXTATTRIBUTE(getHandle().getMemoryLocation(), attribute.getAttributes());
	}
	
	/** 
	 * <strong>XP Only</strong>: Retrieves information about the current console font.
	 * This method only works with Windows XP or higher.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms683176(v=VS.85).aspx">Windows' GetCurrentConsoleFont function</a>
	 * @param forMaximumWindowSize If this parameter is true, font information is retrieved for the maximum window size. If this parameter is false, 
	 * font information is retrieved for the current window size.
	 * @return The requested font information.
	 * @throws OSNotSupportedException If the current OS is not XP or higher.
	 */
	public ConsoleFontInfo getCurrentConsoleFont(boolean forMaximumWindowSize) throws OSNotSupportedException{
		int[] rawData = Josh.GETCURRENTCONSOLEFONT(getHandle().getMemoryLocation(), forMaximumWindowSize);
		return new ConsoleFontInfo(rawData[0], new ConsoleCoord(rawData[1], rawData[2]));
	}
	
	/** 
	 * <strong>Vista Only</strong>: Sets extended information about the current console font.
	 * This method only works with Windows Vista or higher.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms686200(v=VS.85).aspx">Windows' GetCurrentConsoleFont function</a>
	 * @param forMaximumWindowSize If this parameter is true, font information is set for the maximum window size. If this parameter is false, 
	 * font information is set for the current window size.
	 * @param font The font information.
	 * @throws OSNotSupportedException If the current OS is not Vista or higher.
	 */
	public void setCurrentConsoleFontEx(ConsoleFontInfo font, boolean forMaximumWindowSize) throws OSNotSupportedException{
		Josh.SETCURRENTCONSOLEFONTEX(getHandle().getMemoryLocation(), forMaximumWindowSize, font.getFont(), font.getFontSize().getX(), font.getFontSize().getY());
	}
	
	/** 
	 * <strong>XP Only</strong>: Sets the font index.
	 * This method only works with Windows XP or higher.
	 * 
	 * @param fontIndex The index of the font in the system's console font table.
	 * @throws OSNotSupportedException If the current OS is not XP or higher.
	 */
	public void setCurrentConsoleFont(int fontIndex) throws OSNotSupportedException{
		Josh.SETCONSOLEFONT(getHandle().getMemoryLocation(), fontIndex);
	}
	
	/** 
	 * Retrieves the size of the largest possible console window, based on the current font and the size of the display.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms683193(v=VS.85).aspx">Windows' GetLargestConsoleWindowSize function</a>
	 * @return The number of character cell rows (X member) and columns (Y member) in the largest possible console window.
	 */
	public ConsoleCoord getLargestConsoleWindowSize(){
		int[] rawData = Josh.GETLARGESTCONSOLEWINDOWSIZE(getHandle().getMemoryLocation());
		return new ConsoleCoord(rawData[0], rawData[1]);
	}
	
	/** 
	 * Moves a block of data in a screen buffer without a clip block or intersection fill.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms685107(v=VS.85).aspx">Windows' ScrollConsoleScreenBuffer function</a>
	 * @param block The upper-left and lower-right coordinates of the console screen buffer rectangle to be moved.
	 * @param destination The upper-left corner of the new location of the block contents, in characters.
	 */
	public void scrollConsoleScreenBuffer(ConsoleSmallRect block, ConsoleCoord destination){ scrollConsoleScreenBuffer(block, destination, new ConsoleCharInfo(' ',new ConsoleAttribute(0))); }
	
	/** 
	 * Moves a block of data in a screen buffer without a clip block.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms685107(v=VS.85).aspx">Windows' ScrollConsoleScreenBuffer function</a>
	 * @param block The upper-left and lower-right coordinates of the console screen buffer rectangle to be moved.
	 * @param destination The upper-left corner of the new location of the block contents, in characters.
	 * @param fill The character and color attributes to be used in filling the cells within the intersection of block and clip that were left empty as a result of the move.
	 */
	public void scrollConsoleScreenBuffer(ConsoleSmallRect block, ConsoleCoord destination, ConsoleCharInfo fill){ scrollConsoleScreenBuffer(block, new ConsoleSmallRect(-1,-1,-1,-1), destination, fill); }
	
	/** 
	 * Moves a block of data in a screen buffer.
	 * The effects of the move can be limited by specifying a clipping rectangle, so the contents of the console screen buffer outside the clipping rectangle are unchanged.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms685107(v=VS.85).aspx">Windows' ScrollConsoleScreenBuffer function</a>
	 * @param block The upper-left and lower-right coordinates of the console screen buffer rectangle to be moved.
	 * @param clip The upper-left and lower-right coordinates of the console screen buffer rectangle that is affected by the scrolling.
	 * @param destination The upper-left corner of the new location of the block contents, in characters.
	 * @param fill The character and color attributes to be used in filling the cells within the intersection of block and clip that were left empty as a result of the move.
	 */
	public void scrollConsoleScreenBuffer(ConsoleSmallRect block, ConsoleSmallRect clip, ConsoleCoord destination, ConsoleCharInfo fill){
		Josh.SCROLLCONSOLESCREENBUFFER(getHandle().getMemoryLocation(), block.getLeft(), block.getTop(), block.getRight(), block.getBottom(), clip.getLeft(), clip.getTop(), clip.getRight(), clip.getBottom(), destination.getX(), destination.getY(), fill.getCharacter(), fill.getAttributes());
	}
	
	/** 
	 * <strong>XP Only</strong>: Sets the display mode of the specified console screen buffer to fullscreen.
	 * This method only works with Windows XP or higher.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms686028(v=VS.85).aspx">Windows' SetConsoleDisplayMode function</a>
	 * @param fullScreen Sets screen buffer to full screen if true, windowed mode if false.
	 * @throws OSNotSupportedException If the current OS is not XP or higher.
	 */
	public void setFullScreen(boolean fullScreen) throws OSNotSupportedException{
		final int CONSOLE_FULLSCREEN_MODE = 1;
		final int CONSOLE_WINDOWED_MODE = 2;
		if(fullScreen)
			Josh.SETCONSOLEDISPLAYMODE(getHandle().getMemoryLocation(), CONSOLE_FULLSCREEN_MODE);
		else
			Josh.SETCONSOLEDISPLAYMODE(getHandle().getMemoryLocation(), CONSOLE_WINDOWED_MODE);
	}
	
	/** 
	 * Sets the current size and position of a console screen buffer's window.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms686125(v=VS.85).aspx">Windows' SetConsoleWindowInfo function</a>
	 * @param absolute If this parameter is true, the coordinates specify the new upper-left and lower-right corners of the window. 
	 * If it is false, the coordinates are relative to the current window-corner coordinates.
	 * @param newSize Specifies the new upper-left and lower-right corners of the window.
	 */
	public void setConsoleWindowInfo(boolean absolute, ConsoleSmallRect newSize){
		Josh.SETCONSOLEWINDOWINFO(getHandle().getMemoryLocation(), absolute, newSize.getLeft(), newSize.getTop(), newSize.getRight(), newSize.getBottom());
	}
	
	/** 
	 * Sets the character attributes for a specified number of character cells, beginning at the specified coordinates in a screen buffer.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms682662(v=VS.85).aspx">Windows' FillConsoleOutputAttribute function</a>
	 * @param coord The character coordinates of the first cell whose attributes are to be set.
	 * @param length The number of character cells to be set to the specified color attributes.
	 * @param attribute The attributes to use when writing to the console screen buffer.
	 */
	public void fillConsoleOutputAttribute(ConsoleCoord coord, int length, ConsoleAttribute attribute){
		Josh.FILLCONSOLEOUTPUTATTRIBUTE(getHandle().getMemoryLocation(), attribute.getAttributes(), length, coord.getX(), coord.getY());
	}
	
	/** 
	 * Writes a character to the console screen buffer a specified number of times, beginning at the specified coordinates.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms682663(v=VS.85).aspx">Windows' FillConsoleOutputCharacter function</a>
	 * @param coord The character coordinates of the first cell to which the character is to be written.
	 * @param length The number of character cells to which the character should be written.
	 * @param character The character to be written to the console screen buffer.
	 */
	public void fillConsoleOutputCharacter(ConsoleCoord coord, int length, char character){
		Josh.FILLCONSOLEOUTPUTCHARACTER(getHandle().getMemoryLocation(), character, length, coord.getX(), coord.getY());
	}
	
	/** 
	 * Reads character and color attribute data from a rectangular block of character cells in a console screen buffer, 
	 * and the function writes the data to a rectangular block at a specified location in the destination buffer.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms684965(v=VS.85).aspx">Windows' ReadConsoleOutput function</a>
	 * @param size The size to read, in character cells. The X member is the number of columns; the Y member is the number of rows.
	 * @param coord The coordinates of the upper-left cell to start reading. The X member is the column, and the Y member is the row.
	 * @param readRegion The structure members specify the upper-left and lower-right coordinates of the console screen buffer rectangle from which the function is to read.
	 * @return An array that contains the character and it's attributes.
	 */
	public ConsoleCharInfo[] readConsoleOutput(ConsoleCoord size, ConsoleCoord coord, ConsoleSmallRect readRegion){
		int[][] rawData = Josh.READCONSOLEOUTPUT(getHandle().getMemoryLocation(), size.getX(), size.getY(), coord.getX(), coord.getY(), readRegion.getLeft(), readRegion.getTop(), readRegion.getRight(), readRegion.getBottom());
		ConsoleCharInfo[] data = new ConsoleCharInfo[rawData.length];
		int i = 0;
		for(int[] x : rawData)
		{
			data[i] = new ConsoleCharInfo((char)x[0], new ConsoleAttribute(x[1]));
			i++;
		}
		return data;
	}
	
	/** 
	 * Writes character and color attribute data to a specified rectangular block of character cells in a console screen buffer.
	 * The data to be written is taken from a correspondingly sized rectangular block at a specified location in the source buffer.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms687404(v=VS.85).aspx">Windows' WriteConsoleOutput function</a>
	 * @param buffer The data to be written to the console screen buffer.
	 * @param size The size of the buffer, in character cells. The X member is the number of columns; the Y member is the number of rows.
	 * @param coord The coordinates of the upper-left cell in the buffer. The X member is the column, and the Y member is the row.
	 * @param writeRegion The upper-left and lower-right coordinates of the console screen buffer rectangle to write to.
	 */
	public void writeConsoleOutput(ConsoleCharInfo[] buffer, ConsoleCoord size, ConsoleCoord coord, ConsoleSmallRect writeRegion){
		int[][] rawData = new int[buffer.length][2];
		for(int i = 0; i < buffer.length; i++)
		{
			rawData[i][0] = (int)buffer[i].getCharacter();
			rawData[i][1] = (int)buffer[i].getAttributes();
		}
		Josh.WRITECONSOLEOUTPUT(getHandle().getMemoryLocation(), rawData, size.getX(), size.getY(), coord.getX(), coord.getY(), writeRegion.getLeft(), writeRegion.getTop(), writeRegion.getRight(), writeRegion.getBottom());
	}
	
	/** 
	 * Reads a specified number of character attributes from consecutive cells of a console screen buffer, beginning at a specified location.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms684968(v=VS.85).aspx">Windows' ReadConsoleOutputAttribute function</a>
	 * @param coord The coordinates of the first cell in the console screen buffer from which to read, in characters. The X member is the column, and the Y member is the row.
	 * @param length The number of screen buffer character cells from which to read.
	 * @return An array of the attributes being used by the console screen buffer.
	 */
	public ConsoleAttribute[] readConsoleOutputAttribute(ConsoleCoord coord, int length){
		int[] rawData = Josh.READCONSOLEOUTPUTATTRIBUTE(getHandle().getMemoryLocation(), coord.getX(), coord.getY(), length);
		ConsoleAttribute[] attributes = new ConsoleAttribute[rawData.length];
		int i = 0;
		for(int x : rawData)
		{
			attributes[i] = new ConsoleAttribute(x);
			i++;
		}
		return attributes;
	}
	
	/** 
	 * Writes a number of character attributes to consecutive cells of a console screen buffer, beginning at a specified location.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms687407(v=VS.85).aspx">Windows' WriteConsoleOutputAttribute function</a>
	 * @param attributes An array that contains the character attributes.
	 * @param coord The character coordinates of the first cell in the console screen buffer to which the attributes will be written.
	 * @param length The number of screen buffer character cells to which the attributes will be copied.
	 */
	public void writeConsoleOutputAttribute(ConsoleAttribute[] attributes, ConsoleCoord coord, int length){
		int[] data = new int[attributes.length];
		for(int x = 0; x < attributes.length; x++)
			data[x] = attributes[x].getAttributes();
		Josh.WRITECONSOLEOUTPUTATTRIBUTE(getHandle().getMemoryLocation(), data, length, coord.getX(), coord.getY());
	}
	
	/** 
	 * Reads a number of characters from consecutive cells of a console screen buffer, beginning at a specified location.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms684969(v=VS.85).aspx">Windows' ReadConsoleOutputCharacter function</a>
	 * @param coord The coordinates of the first cell in the console screen buffer from which to read, in characters. The X member is the column, and the Y member is the row.
	 * @param length The number of screen buffer character cells from which to read.
	 * @return The characters read from the console screen buffer.
	 */
	public char[] readConsoleOutputChar(ConsoleCoord coord, int length){
		int[] rawData = Josh.READCONSOLEOUTPUTCHARACTER(getHandle().getMemoryLocation(), coord.getX(), coord.getY(), length);
		char[] chars = new char[rawData.length];
		int i = 0;
		for(int x : rawData)
		{
			chars[i] = (char)x;
			i++;
		}
		return chars;
	}
	
	/** 
	 * Writes a number of characters to consecutive cells of a console screen buffer, beginning at a specified location.
	 * 
	 * @see <a href="http://msdn.microsoft.com/en-us/library/ms687410(v=VS.85).aspx">Windows' WriteConsoleOutputCharacter function</a>
	 * @param chars The characters to write to the console screen buffer.
	 * @param coord The character coordinates of the first cell in the console screen buffer to which characters will be written.
	 */
	public void writeConsoleOutputChar(char[] chars, ConsoleCoord coord, int length){
		int[] data = new int[chars.length];
		for(int x = 0; x < chars.length; x++)
			data[x] = (int)chars[x];
		Josh.WRITECONSOLEOUTPUTCHARACTER(getHandle().getMemoryLocation(), data, length, coord.getX(), coord.getY());
	}
}