package com.yifanlu.Josh;

/**
 * Contains information about the console cursor.
 *
 * @see <a href="http://msdn.microsoft.com/en-us/library/ms682068(v=VS.85).aspx">Windows' CONSOLE_CURSOR_INFO structure</a>
 * @see JoshOutBuffer#getConsoleCursorInfo
 * @see JoshOutBuffer#setConsoleCursorInfo
 * @author Yifan Lu
 * @version 1.3, 05/29/10
 * @since 0.1
 */
public class ConsoleCursorInfo {

    private int size;
    private boolean visible;

    /**
     * Creates a default cursor size of 25 that is visible.
     */
    public ConsoleCursorInfo() {
        this.size = 25;
        this.visible = true;
    }

    /**
     * Creates a new cursor info object.
     *
     * @param size The percentage of the character cell that is filled by the cursor. This value is between 1 and 100. 
     *	The cursor appearance varies, ranging from completely filling the cell to showing up as a horizontal line at
     *	the bottom of the cell.
     * @param visible The visibility of the cursor. If the cursor is visible, this member is true.
     */
    public ConsoleCursorInfo(int size, boolean visible) {
        this.size = size;
        this.visible = visible;
    }

    /**
     * Gets the size.
     *
     * @return The percentage of the character cell that is filled by the cursor. This value is between 1 and 100.
     *	The cursor appearance varies, ranging from completely filling the cell to showing up as a horizontal line at
     *	the bottom of the cell.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Gets the visibility.
     *
     * @return The visibility of the cursor. If the cursor is visible, this member is true.
     */
    public boolean getVisible() {
        return this.visible;
    }

    /**
     * Sets the size.
     *
     * @param size The percentage of the character cell that is filled by the cursor. This value is between 1 and 100. 
     *	The cursor appearance varies, ranging from completely filling the cell to showing up as a horizontal line at
     *	the bottom of the cell.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Sets the visibility.
     *
     * @param visible The visibility of the cursor. If the cursor is visible, this member is true.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Compares two Objects, if both are ConsoleCursorInfo and have the same values, they are equal.
     *
     * @param anObject Another ConsoleCursorInfo object.
     * @return true if all values are equal.
     */
    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof ConsoleCursorInfo) {
            ConsoleCursorInfo otherObject = (ConsoleCursorInfo) anObject;
            return (this.getSize() == otherObject.getSize()) && (this.getVisible() == otherObject.getVisible());
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
        hash = 37 * hash + this.size;
        hash = 37 * hash + (this.visible ? 1 : 0);
        return hash;
    }

    /**
     * A {@link java.lang.String} representation of this object.
     *
     * @return The size of the console cursor.
     */
    @Override
    public String toString() {
        return Integer.toString(this.size);
    }
}
