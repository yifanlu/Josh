package com.yifanlu.Josh;

/**
 * Describes a focus event.
 * These events are used internally and should be ignored.
 *
 * @see <a href="http://msdn.microsoft.com/en-us/library/ms683149(v=VS.85).aspx">Windows' FOCUS_EVENT_RECORD structure</a>
 * @see JoshInBuffer
 * @author Yifan Lu
 * @version 1.3, 05/29/10
 * @since 1.0
 */
public class ConsoleEventFocus extends ConsoleEvent {

    private boolean setFocus;

    /**
     * Creates a new console focus change event.
     *
     * @param setFocus Reserved.
     */
    public ConsoleEventFocus(boolean setFocus) {
        this.eventType = ConsoleEvent.FOCUS_EVENT;
        this.setFocus = setFocus;
    }

    /**
     * Returns the focus state.
     *
     * @return Reserved.
     */
    public boolean getSetFocus() {
        return this.setFocus;
    }

    /**
     * Sets the focus state.
     *
     * @param setFocus Reserved.
     */
    public void setSetFocus(boolean setFocus) {
        this.setFocus = setFocus;
    }

    /**
     * Compares two Objects, if both are ConsoleEventFocus and have the same values, they are equal.
     *
     * @param anObject Another ConsoleEventFocus object.
     * @return true if all values are equal.
     */
    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof ConsoleEventFocus) {
            ConsoleEventFocus otherObject = (ConsoleEventFocus) anObject;
            return this.getSetFocus() == otherObject.getSetFocus();
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
        int hash = 7;
        hash = 29 * hash + (this.setFocus ? 1 : 0);
        return hash;
    }

    /**
     * A {@link java.lang.String} representation of this object.
     *
     * @return The set focus state.
     */
    @Override
    public String toString() {
        return Boolean.toString(this.setFocus);
    }
}
