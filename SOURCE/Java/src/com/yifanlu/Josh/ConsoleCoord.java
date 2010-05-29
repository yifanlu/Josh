package com.yifanlu.Josh;

/**
 * Defines the coordinates of a character cell in a console screen buffer.
 * The origin of the coordinate system (0,0) is at the top, left cell of the buffer.
 *
 * @see <a href="http://msdn.microsoft.com/en-us/library/ms682119(v=VS.85).aspx">Windows' COORD structure</a>
 * @author Yifan Lu
 * @version 1.3, 05/29/10
 * @since 0.1
 */
public class ConsoleCoord {

    private int x;
    private int y;

    /**
     * Creates a point that represents a block in the console window.
     *
     * @param x The horizontal coordinate or column value.
     * @param y The vertical coordinate or row value.
     */
    public ConsoleCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the X coordinate.
     *
     * @return The horizontal coordinate or column value.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the Y coordinate.
     *
     * @return The vertical coordinate or row value.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets the X coordinate.
     *
     * @param x The horizontal coordinate or column value.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the Y coordinate.
     *
     * @param y The vertical coordinate or row value.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Compares two Objects, if both are ConsoleCoord and have the same X and Y, they are equal.
     *
     * @param anObject Another ConsoleCoord object.
     * @return true if both the X and Y values are equal.
     */
    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof ConsoleCoord) {
            ConsoleCoord otherObj = (ConsoleCoord) anObject;
            return (this.getX() == otherObj.getX()) && (this.getY() == otherObj.getY());
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
        hash = 67 * hash + this.x;
        hash = 67 * hash + this.y;
        return hash;
    }

    /**
     * A {@link java.lang.String} representation of this object.
     *
     * @return The X and Y values as "(X, Y)".
     */
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
