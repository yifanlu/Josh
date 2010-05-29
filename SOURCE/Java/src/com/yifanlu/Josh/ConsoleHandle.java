package com.yifanlu.Josh;

/**
 * Contains a memory location, where a buffer is located.
 * Represents a Windows HANDLE.<br />
 * Note objects created from this class are immutable, 
 * so they cannot be modified but can be replaced.
 *
 * @see ConsoleBuffer
 * @see JoshOutBuffer
 * @see JoshInBuffer
 * @author Yifan Lu
 * @version 1.3, 05/29/10
 * @since 0.1
 */
public class ConsoleHandle {

    private final long handle;

    /**
     * Creates a handle.
     *
     * @param handle The memory location.
     */
    public ConsoleHandle(long handle) {
        this.handle = handle;
    }

    /**
     * Gets the memory location.
     *
     * @return A memory address.
     */
    public long getMemoryLocation() {
        return this.handle;
    }

    /**
     * Compares two Objects, if both are ConsoleHandle and have the same values, they are equal.
     *
     * @param anObject Another ConsoleHandle object.
     * @return true if all values are equal.
     */
    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof ConsoleHandle) {
            ConsoleHandle otherObject = (ConsoleHandle) anObject;
            return this.getMemoryLocation() == otherObject.getMemoryLocation();
        } else {
            return false;
        }
    }

    /**
     * Returns a hash code.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + (int) (this.handle ^ (this.handle >>> 32));
        return hash;
    }

    /**
     * A {@link java.lang.String} representation of this object.
     *
     * @return The memory address.
     */
    @Override
    public String toString() {
        return Long.toString(handle);
    }
}
