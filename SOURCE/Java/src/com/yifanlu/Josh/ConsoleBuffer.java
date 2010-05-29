package com.yifanlu.Josh;

/**
 * Represents a new screen buffer.
 * In a single console application, you can have as many buffers as the memory can handle 
 * the limitation of the Windows Console is that only one buffer can be shown at a time, 
 * therefore when you need to call {@link Josh#setConsoleActiveScreenBuffer(ConsoleBuffer)}
 * method to switch to a new screen buffer.<br />
 * The standard/default buffer (the one you see when the console first opens) is represented 
 * by the static class {@link Josh}.<br />
 *
 * @author Yifan Lu
 * @version 1.3, 05/29/10
 * @since 0.1
 */
public class ConsoleBuffer extends Josh {

    /**
     * Contains output functions.
     */
    public JoshOutput out;
    /**
     * Provides native access to the output buffer in this screen buffer.
     */
    public JoshOutBuffer outBuffer;

    /**
     * Provides simplified access to the output buffer in this screen buffer.
     */
    public ConsoleBuffer() {
        this.newConsoleBuffer(true, true);
    }

    /**
     * Creates a new console buffer.
     *
     * @param read Does the user have read access to this buffer?
     * @param write Does the user have write access to this buffer?
     */
    public ConsoleBuffer(boolean read, boolean write) {
        this.newConsoleBuffer(read, write);
    }

    private void newConsoleBuffer(boolean read, boolean write) {
        long pointer = Josh.CREATECONSOLESCREENBUFFER(read, write, 0);
        this.setHandle(new ConsoleHandle(pointer));
        this.out = new JoshOutput(this.getHandle());
        this.outBuffer = new JoshOutBuffer(this.getHandle());
    }
    /**
     * Gets the {@link JoshOutput} for output functions.
     *
     * @return A {@link JoshOutput}.
    public JoshOutput getOut() {
    return this.out;
    }
     */
    /**
     * Gets the {@link JoshOutBuffer} for working directly with the output buffer.
     *
     * @return A {@link JoshOutBuffer}.
     * @see JoshOutBuffer
    public JoshOutBuffer getOutBuffer() {
    return this.outBuffer;
    }
     */
}
