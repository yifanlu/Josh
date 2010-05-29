package com.yifanlu.Josh;

/**
 * Provides native access to the console.
 * The functions defined here are generic for any buffer, for more commands, see 
 * {@link Josh#out}, {@link Josh#outBuffer}, {@link Josh#in}, and {@link Josh#inBuffer} for functions specific to 
 * output/input buffers.
 *
 * @author Yifan Lu
 * @version 1.3, 05/29/10
 * @since 0.1
 */
public class Josh extends JoshBuffer {

    static {
        LibraryLoader.loadJosh();
    }
    /**
     * Used in {@link Josh#getStdHandle} and {@link Josh#setStdHandle}. Different handles to get/set.
     */
    public static final int STD_INPUT_HANDLE = -10, STD_OUTPUT_HANDLE = -11, STD_ERROR_HANDLE = -12;
    private static ConsoleHandle outHandle = new ConsoleHandle(GETSTDHANDLE(STD_OUTPUT_HANDLE));
    private static ConsoleHandle inHandle = new ConsoleHandle(GETSTDHANDLE(STD_INPUT_HANDLE));
    private static ConsoleHandle errorHandle = new ConsoleHandle(GETSTDHANDLE(STD_ERROR_HANDLE));
    /**
     * Contains simplified output functions for the standard output buffer.
     */
    public static final JoshOutput out = new JoshOutput(outHandle);
    /**
     * Contains native output functions for the standard output buffer.
     */
    public static final JoshOutBuffer outBuffer = new JoshOutBuffer(outHandle);
    /**
     * Contains functions for reading keyboard input from the standard input buffer.
     */
    public static final JoshInput in = new JoshInput(inHandle);
    /**
     * Contains functions for reading events from the standard input buffer.
     */
    public static final JoshInBuffer inBuffer = new JoshInBuffer(inHandle);

    native static long CREATECONSOLESCREENBUFFER(boolean read, boolean write, int shared);

    native static void FILLCONSOLEOUTPUTATTRIBUTE(long pointer, int attribute, int length, int x, int y);

    native static void FILLCONSOLEOUTPUTCHARACTER(long pointer, char character, int length, int x, int y);

    native static int[] GETCONSOLECURSORINFO(long pointer);

    native static int GETCONSOLEDISPLAYMODE() throws OSNotSupportedException;

    native static int[] GETCONSOLEFONTSIZE(long pointer) throws OSNotSupportedException;

    native static int[] GETCONSOLEHISTORYINFO() throws OSNotSupportedException;

    native static int GETCONSOLEMODE(long pointer);

    native static String GETCONSOLEORGINIALTITLE() throws OSNotSupportedException;

    native static int[] GETCONSOLESCREENBUFFERINFO(long pointer);

    native static int[] GETCONSOLESELECTIONINFO() throws OSNotSupportedException;

    native static String GETCONSOLETITLE();

    native static long GETCONSOLEWINDOW();

    native static int[] GETCURRENTCONSOLEFONT(long pointer, boolean maximumWindow) throws OSNotSupportedException;

    native static int[] GETLARGESTCONSOLEWINDOWSIZE(long pointer);

    native static int GETNUMBEROFCONSOLEINPUTEVENTS(long pointer);

    native static int GETNUMBEROFCONSOLEMOUSEBUTTONS();

    native static long GETSTDHANDLE(int handle);

    native static int[] PEEKCONSOLEINPUT(long pointer, int length);

    native static int[] READCONSOLEINPUT(long pointer, int length);

    native static String READCONSOLE(long pointer);

    native static int[][] READCONSOLEOUTPUT(long pointer, int sizeX, int sizeY, int coordX, int coordY, int left, int top, int right, int bottom);

    native static int[] READCONSOLEOUTPUTATTRIBUTE(long pointer, int x, int y, int length);

    native static int[] READCONSOLEOUTPUTCHARACTER(long pointer, int x, int y, int length);

    native static void SCROLLCONSOLESCREENBUFFER(long pointer, int scrollLeft, int scrollTop, int scrollRight, int scrollBottom, int clipLeft, int clipTop, int clipRight, int clipBottom, int toX, int toY, char fillChar, int fillAttribute);

    native static void SETCONSOLEACTIVESCREENBUFFER(long pointer);

    native static void SETCONSOLECURSORINFO(long pointer, int size, boolean visible);

    native static void SETCONSOLECURSORPOSITION(long pointer, int x, int y);

    native static void SETCONSOLEDISPLAYMODE(long pointer, int mode) throws OSNotSupportedException;

    native static void SETCONSOLEHISTORYINFO(int bufferSize, int numberOfBuffers, int flags) throws OSNotSupportedException;

    native static void SETCONSOLEMODE(long pointer, int flags);

    native static void SETCONSOLESCREENBUFFERINFOEX(long pointer, int sizeX, int sizeY, int cursorX, int cursorY, int attributes, int windowLeft, int windowTop, int windowRight, int windowBottom, int maxX, int maxY) throws OSNotSupportedException;

    native static void SETCONSOLESCREENBUFFERSIZE(long pointer, int X, int Y);

    native static void SETTEXTATTRIBUTE(long pointer, int attributes);

    native static void SETCONSOLETITLE(String title);

    native static void SETCONSOLEWINDOWINFO(long pointer, boolean absolute, int windowLeft, int windowTop, int windowRight, int windowBottom);

    native static void SETCURRENTCONSOLEFONTEX(long pointer, boolean maximumWindow, int font, int sizeX, int sizeY) throws OSNotSupportedException;

    native static void SETSTDHANDLE(int handle, long pointer);

    native static void WRITECONSOLE(long pointer, String output);

    native static void WRITECONSOLEINPUT(long pointer, int eventType, int[] data, int length);

    native static void WRITECONSOLEOUTPUT(long pointer, int[][] data, int sizeX, int sizeY, int coordX, int coordY, int left, int top, int right, int bottom);

    native static void WRITECONSOLEOUTPUTATTRIBUTE(long pointer, int[] data, int length, int x, int y);

    native static void WRITECONSOLEOUTPUTCHARACTER(long pointer, int[] data, int length, int x, int y);

    native static void BEEP(int frequency, int duration);

    native static void CLEARSCREEN(long pointer, int background);

    native static void SETCONSOLEFONT(long pointer, int fontIndex) throws OSNotSupportedException;

    /**
     * Generates a single beep of 800Hz for 200 miliseconds.
     */
    public static void Beep() {
        Beep(800, 200);
    }

    /**
     * Generates simple tones on the speaker.
     * The function is synchronous; it performs an alertable wait and does not return control to its caller until the sound finishes.
     *
     * @param frequency The frequency of the sound, in hertz. This parameter must be in the range 37 through 32,767 (0x25 through 0x7FFF).
     * @param duration The duration of the sound, in milliseconds.
     */
    public static void Beep(int frequency, int duration) {
        BEEP(frequency, duration);
    }

    /**
     * Sets the specified screen buffer to be the currently displayed console screen buffer.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms686010(v=VS.85).aspx">Windows' SetConsoleActiveScreenBuffer function</a>
     * @param screen A {@link ConsoleBuffer} that should be displayed.
     */
    public static void setConsoleActiveScreenBuffer(ConsoleBuffer screen) {
        Josh.SETCONSOLEACTIVESCREENBUFFER(screen.getHandle().getMemoryLocation());
    }

    /**
     * Retrieves the title for the current console window.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms683174(v=VS.85).aspx">Windows' GetConsoleTitle function</a>
     * @return The current title.
     */
    public static String getConsoleTitle() {
        return GETCONSOLETITLE();
    }

    /**
     * Sets the title for the current console window.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms686050(v=VS.85).aspx">Windows' SetConsoleTitle function</a>
     * @param title The string to be displayed in the title bar of the console window.
     */
    public static void setConsoleTitle(String title) {
        SETCONSOLETITLE(title);
    }

    /**
     * <strong>Vista Only</strong>: Retrieves the original title for the current console window.
     * This method only works with Windows Vista or higher.<br />
     * This is the title of the Window when the application first launched.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms683168(v=VS.85).aspx">Windows' GetConsoleOriginalTitle function</a>
     * @return The original title.
     * @throws OSNotSupportedException If the current OS is not Vista or higher.
     */
    public static String getConsoleOrginialTitle() throws OSNotSupportedException {
        return GETCONSOLEORGINIALTITLE();
    }

    /**
     * <strong>Vista Only</strong>: Retrieves the history settings for the calling process's console.
     * This method only works with Windows Vista or higher.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms683166(v=VS.85).aspx">Windows' GetConsoleHistoryInfo function</a>
     * @return The history settings for the calling process's console.
     * @throws OSNotSupportedException If the current OS is not Vista or higher.
     */
    public static ConsoleHistoryInfo getConsoleHistoryInfo() throws OSNotSupportedException {
        int[] rawData = GETCONSOLEHISTORYINFO();
        return new ConsoleHistoryInfo(rawData[0], rawData[1], rawData[2]);
    }

    /**
     * <strong>Vista Only</strong>: Sets the history settings for the calling process's console.
     * This method only works with Windows Vista or higher.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms686031(v=VS.85).aspx">Windows' SetConsoleHistoryInfo function</a>
     * @param info The history settings for the calling process's console.
     * @throws OSNotSupportedException If the current OS is not Vista or higher.
     */
    public static void setConsoleHistoryInfo(ConsoleHistoryInfo info) throws OSNotSupportedException {
        SETCONSOLEHISTORYINFO(info.getHistoryBufferSize(), info.getNumberOfHistoryBuffers(), info.getFlags());
    }

    /**
     * <strong>XP Only</strong>: Retrieves information about the current console selection.
     * This method only works with Windows XP or higher.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms683173(v=VS.85).aspx">Windows' GetConsoleSelectionInfo function</a>
     * @return The selection information.
     * @throws OSNotSupportedException If the current OS is not XP or higher.
     */
    public static ConsoleSelectionInfo getConsoleSelectionInfo() throws OSNotSupportedException {
        int[] rawData = GETCONSOLESELECTIONINFO();
        return new ConsoleSelectionInfo(rawData[0], new ConsoleCoord(rawData[1], rawData[2]), new ConsoleSmallRect(rawData[3], rawData[4], rawData[5], rawData[6]));
    }

    /**
     * Retrieves the window handle used by the console associated with the calling process.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms683175(v=VS.85).aspx">Windows' GetConsoleWindow function</a>
     * @return The window used by the console associated with the calling process.
     */
    public static ConsoleHandle getConsoleWindow() {
        return new ConsoleHandle(GETCONSOLEWINDOW());
    }

    /**
     * Retrieves the number of buttons on the mouse used by the current console.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms683208(v=VS.85).aspx">Windows' GetNumberOfConsoleMouseButtons function</a>
     * @return The number of mouse buttons.
     */
    public static int getNumberOfConsoleMouseButtons() {
        return GETNUMBEROFCONSOLEMOUSEBUTTONS();
    }

    /**
     * Retrieves a handle to the specified standard device (standard input, standard output, or standard error).
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms683231(v=VS.85).aspx">Windows' GetStdHandle function</a>
     * @param type The standard device which is a constant defined in this class.
     * @return The specified device, or a redirected handle set by a previous call to SetStdHandle.
     *	The handle has GENERIC_READ and GENERIC_WRITE access rights, unless the application has used SetStdHandle to set a
     *	standard handle with lesser access.
     */
    public static ConsoleHandle getStdHandle(int type) {
        return new ConsoleHandle(GETSTDHANDLE(type));
    }

    /**
     * Sets the handle for the specified standard device (standard input, standard output, or standard error).
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms686244(v=VS.85).aspx">Windows' SetStdHandle function</a>
     * @param buffer What to set as the standard handle.
     * @param forHandle The standard device which is a constant defined in this class.
     */
    public static void setStdHandle(ConsoleBuffer buffer, int forHandle) {
        SETSTDHANDLE(forHandle, buffer.getHandle().getMemoryLocation());
    }

    /**
     * <strong>XP Only</strong>: Sees if the current console window is in fullscreen mode.
     * This method only works with Windows XP or higher.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms683164(v=VS.85).aspx">Windows' GetConsoleDisplayMode function</a>
     * @return True if the console is in fullscreen.
     * @throws OSNotSupportedException If the current OS is not XP or higher.
     */
    public static boolean isFullScreen() throws OSNotSupportedException {
        return Josh.GETCONSOLEDISPLAYMODE() == 2 ? true : false;
    }
}
