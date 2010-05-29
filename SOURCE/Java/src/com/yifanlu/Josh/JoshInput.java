package com.yifanlu.Josh;

/**
 * Provides simplified access to the input buffer.
 * Allow reading of user keyboard input in the console.
 * Do not call this class directly, use {@link Josh#in}.
 *
 * @author Yifan Lu
 * @version 1.3, 05/29/10
 * @since 0.1
 */
public class JoshInput extends JoshBuffer {

    /**
     * Creates a new input buffer for reading keyboard inputs.
     * This class should, except in rare cases, never be called alone. Use {@link Josh#in} to access, as
     * there is only one input buffer needed for a console.
     * You must have the GENERIC_READ permission in the handle for the buffer to use the methods below.
     *
     * @param handle A memory location that this buffer represents. Usually the STD_INPUT_HANDLE from {@link Josh}.
     */
    public JoshInput(ConsoleHandle handle) {
        this.setHandle(handle);
    }

    /**
     * Reads character input from the console input buffer and removes it from the buffer.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms684958(v=VS.85).aspx">Windows' ReadConsole function</a>
     * @return The user's keyboard input.
     */
    public String readLine() {
        return Josh.READCONSOLE(getHandle().getMemoryLocation());
    }

    /**
     * Reads the first character from the console input buffer and removes it from the buffer.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms684958(v=VS.85).aspx">Windows' ReadConsole function</a>
     * @return The user's keyboard input.
     */
    public char readChar() {
        return readLine().length() < 1 ? 0 : readLine().charAt(0);
    }

    /**
     * Reads the first character from the console input buffer and removes it from the buffer.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms684958(v=VS.85).aspx">Windows' ReadConsole function</a>
     * @return The user's keyboard input.
     * @throws java.lang.NumberFormatException If the input is not an integer.
     */
    public int readInt() throws NumberFormatException {
        return Integer.parseInt(readLine());
    }

    /**
     * Reads the first character from the console input buffer and removes it from the buffer.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms684958(v=VS.85).aspx">Windows' ReadConsole function</a>
     * @return The user's keyboard input.
     * @throws java.lang.NumberFormatException If the input is not a double.
     */
    public double readDouble() throws NumberFormatException {
        return Double.parseDouble(readLine());
    }
}
