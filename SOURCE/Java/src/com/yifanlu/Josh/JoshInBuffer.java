package com.yifanlu.Josh;

/**
 * Provides native access to the input buffer.
 * This allows access to events in the console.<br />
 * Do not call this class directly, use {@link Josh#inBuffer}.
 *
 * @author Yifan Lu
 * @version 1.3, 05/29/10
 * @since 0.1
 */
public class JoshInBuffer extends JoshBuffer {

    /**
     * Creates a new input buffer for reading console events.
     * This class should, except in rare cases, never be called alone. Use {@link Josh#inBuffer} to access, as
     * there is only one input buffer needed for a console.
     * You must have the GENERIC_READ permission in the handle for the buffer to use the methods below.
     *
     * @param handle A memory location that this buffer represents. Usually the STD_INPUT_HANDLE from {@link Josh}.
     */
    public JoshInBuffer(ConsoleHandle handle) {
        this.setHandle(handle);
    }

    /**
     * Retrieves the number of unread input records in the console's input buffer.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms683207(v=VS.85).aspx">Windows' GetNumberOfConsoleInputEvents function</a>
     * @return The number of unread input records in the console's input buffer.
     */
    public int getNumberOfConsoleInputEvents() {
        return Josh.GETNUMBEROFCONSOLEINPUTEVENTS(getHandle().getMemoryLocation());
    }

    /**
     * Reads data from the specified console input buffer without removing it from the buffer.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms684344(v=VS.85).aspx">Windows' PeekConsoleInput function</a>
     * @return The top-most console event, without removing it from the stack.
     */
    public ConsoleEvent peekConsoleInput() {
        return peekConsoleInput(1);
    }

    private ConsoleEvent peekConsoleInput(int length) {
        int[] rawData = Josh.PEEKCONSOLEINPUT(getHandle().getMemoryLocation(), length);
        switch (rawData[0]) {
            case ConsoleEvent.FOCUS_EVENT:
                return new ConsoleEventFocus(rawData[1] == 1 ? true : false);
            case ConsoleEvent.KEY_EVENT:
                return new ConsoleEventKeyboard(rawData[1] == 1 ? true : false, rawData[2], rawData[3], rawData[4], (char) rawData[5], rawData[6]);
            case ConsoleEvent.MENU_EVENT:
                return new ConsoleEventMenu(rawData[1]);
            case ConsoleEvent.MOUSE_EVENT:
                return new ConsoleEventMouse(new ConsoleCoord(rawData[1], rawData[2]), rawData[3], rawData[4], rawData[5]);
            case ConsoleEvent.WINDOW_BUFFER_SIZE_EVENT:
                return new ConsoleEventBufferChanged(new ConsoleCoord(rawData[1], rawData[2]));
            default:
                return null;
        }
    }

    /**
     * Reads data from a console input buffer and removes it from the buffer.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms684961(v=VS.85).aspx">Windows' ReadConsoleInput function</a>
     * @return The top-most console event, and pops it off the stack.
     */
    public ConsoleEvent readConsoleInput() {
        return readConsoleInput(1);
    }

    private ConsoleEvent readConsoleInput(int length) {
        int[] rawData = Josh.READCONSOLEINPUT(getHandle().getMemoryLocation(), length);
        switch (rawData[0]) {
            case ConsoleEvent.FOCUS_EVENT:
                return new ConsoleEventFocus(rawData[1] == 1 ? true : false);
            case ConsoleEvent.KEY_EVENT:
                return new ConsoleEventKeyboard(rawData[1] == 1 ? true : false, rawData[2], rawData[3], rawData[4], (char) rawData[5], rawData[6]);
            case ConsoleEvent.MENU_EVENT:
                return new ConsoleEventMenu(rawData[1]);
            case ConsoleEvent.MOUSE_EVENT:
                return new ConsoleEventMouse(new ConsoleCoord(rawData[1], rawData[2]), rawData[3], rawData[4], rawData[5]);
            case ConsoleEvent.WINDOW_BUFFER_SIZE_EVENT:
                return new ConsoleEventBufferChanged(new ConsoleCoord(rawData[1], rawData[2]));
            default:
                return null;
        }
    }

    /**
     * Writes data directly to the console input buffer.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms687403(v=VS.85).aspx">Windows' WriteConsoleInput function</a>
     * @param event Contains data to be written to the input buffer
     */
    public void writeConsoleInput(ConsoleEvent event) {
        writeConsoleInput(event, 1);
    }

    private void writeConsoleInput(ConsoleEvent event, int length) {
        int[] data = null;
        switch (event.getEventType()) {
            case ConsoleEvent.FOCUS_EVENT:
                data = new int[2];
                data[0] = ((ConsoleEventFocus) event).getEventType();
                data[1] = ((ConsoleEventFocus) event).getSetFocus() ? 1 : 0;
                break;
            case ConsoleEvent.KEY_EVENT:
                data = new int[7];
                data[0] = ((ConsoleEventKeyboard) event).getEventType();
                data[1] = ((ConsoleEventKeyboard) event).getKeyDown() ? 1 : 0;
                data[2] = ((ConsoleEventKeyboard) event).getRepeatCount();
                data[3] = ((ConsoleEventKeyboard) event).getVirtualKeyCode();
                data[4] = ((ConsoleEventKeyboard) event).getVirtualScanCode();
                data[5] = (int) ((ConsoleEventKeyboard) event).getChar();
                data[6] = ((ConsoleEventKeyboard) event).getControlKeyState();
                break;
            case ConsoleEvent.MENU_EVENT:
                data = new int[2];
                data[0] = ((ConsoleEventMenu) event).getEventType();
                data[1] = ((ConsoleEventMenu) event).getCommandId();
                break;
            case ConsoleEvent.MOUSE_EVENT:
                data = new int[6];
                data[0] = ((ConsoleEventMouse) event).getEventType();
                data[1] = ((ConsoleEventMouse) event).getMousePosition().getX();
                data[2] = ((ConsoleEventMouse) event).getMousePosition().getY();
                data[3] = ((ConsoleEventMouse) event).getButtonState();
                data[4] = ((ConsoleEventMouse) event).getControlKeyState();
                data[5] = ((ConsoleEventMouse) event).getEventFlags();
                break;
            case ConsoleEvent.WINDOW_BUFFER_SIZE_EVENT:
                data = new int[3];
                data[0] = ((ConsoleEventBufferChanged) event).getEventType();
                data[1] = ((ConsoleEventBufferChanged) event).getSize().getX();
                data[2] = ((ConsoleEventBufferChanged) event).getSize().getY();
                break;
        }
        Josh.WRITECONSOLEINPUT(getHandle().getMemoryLocation(), event.getEventType(), data, length);
    }
}
