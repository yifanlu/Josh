package com.yifanlu.Josh;

/**
 * Defines the coordinates of the upper left and lower right corners of a rectangle.
 *
 * @see <a href="http://msdn.microsoft.com/en-us/library/ms686311(v=VS.85).aspx">Windows' SMALL_RECT structure</a>
 * @author Yifan Lu
 * @version 1.0, 04/24/10
 * @since 0.1
 */
public class ConsoleSmallRect {
	private short left;
	private short top;
	private short right;
	private short bottom;
	
	/**
     * Creates a new small rectangle. (int values are automatically casted to short)
	 * 
     * @param left The x-coordinate of the upper left corner of the rectangle.
     * @param top The y-coordinate of the upper left corner of the rectangle.
     * @param right The x-coordinate of the lower right corner of the rectangle.
	 * @param bottom The y-coordinate of the lower right corner of the rectangle.
	 */
    public ConsoleSmallRect(int left, int top, int right, int bottom) { this((short)left,(short)top,(short)right,(short)bottom); }
	
	/**
     * Creates a new small rectangle.
	 * 
     * @param left The x-coordinate of the upper left corner of the rectangle.
     * @param top The y-coordinate of the upper left corner of the rectangle.
     * @param right The x-coordinate of the lower right corner of the rectangle.
	 * @param bottom The y-coordinate of the lower right corner of the rectangle.
	 */
	public ConsoleSmallRect(short left, short top, short right, short bottom) {
    	this.left = left;
    	this.top = top;
    	this.right = right;
    	this.bottom = bottom;
    }
	
	/**
     * Creates a new small rectangle. (Using coordinates of two corners)
	 * 
     * @param upperLeft The x,y-coordinates of the upper left corner of the rectangle.
     * @param lowerRight The x,y-coordinates of the lower right corner of the rectangle.
	 */
	public ConsoleSmallRect(ConsoleCoord upperLeft, ConsoleCoord lowerRight){
		this.left = (short)upperLeft.getX();
		this.top = (short)upperLeft.getY();
		this.right = (short)lowerRight.getX();
		this.bottom = (short)lowerRight.getY();
	}
	
	/**
     * Gets the upper left x-coordinate of the rectangle.
	 * 
     * @return The x-coordinate of the upper left corner of the rectangle.
     */
	public short getLeft(){
		return this.left;
	}
	
	/**
     * Gets the upper left y-coordinate of the rectangle.
	 * 
     * @return The y-coordinate of the upper left corner of the rectangle.
     */
	public short getTop(){
		return this.top;
	}
	
	/**
     * Gets the lower right x-coordinate of the rectangle.
	 * 
     * @return The x-coordinate of the lower right corner of the rectangle.
     */
	public short getRight(){
		return this.right;
	}
	
	/**
     * Gets the lower right y-coordinate of the rectangle.
	 * 
     * @return The y-coordinate of the lower right corner of the rectangle.
     */
	public short getBottom(){
		return this.bottom;
	}
	
	/**
     * Sets the upper left x-coordinate of the rectangle.
	 * 
     * @param left The x-coordinate of the upper left corner of the rectangle.
     */
	public void setLeft(short left){
		this.left = left;
	}
	
	/**
     * Sets the upper left y-coordinate of the rectangle.
	 * 
     * @param top The y-coordinate of the upper left corner of the rectangle.
     */
	public void setTop(short top){
		this.top = top;
	}
	
	/**
     * Sets the lower right x-coordinate of the rectangle.
	 * 
     * @param right The x-coordinate of the lower right corner of the rectangle.
     */
	public void setRight(short right){
		this.right = right;
	}
	
	/**
     * Sets the lower right y-coordinate of the rectangle.
	 * 
     * @param bottom The y-coordinate of the lower right corner of the rectangle.
     */
	public void setBottom(short bottom){
		this.bottom = bottom;
	}
	
	/** 
	 * Compares two Objects, if both are ConsoleSmallRect and have the same values, they are equal.
     *
     * @param anObject Another ConsoleSmallRect object.
	 * @return true if all values are equal.
	 */
	public boolean equals(Object anObject) {
		if(anObject instanceof ConsoleSmallRect){
			ConsoleSmallRect otherObject = (ConsoleSmallRect)anObject;
			return (this.left == otherObject.left) && (this.top == otherObject.top) && (this.right == otherObject.right) && (this.bottom == otherObject.bottom);
		}else
			return false;
	}
	
	/** 
	 * A {@link java.lang.String} representation of this object.
	 * 
	 * @return The flag, the selection anchor location, and the selection box.
	 */
	public String toString(){
		return (new ConsoleCoord(this.left, this.top)).toString() + ", " + (new ConsoleCoord(this.right, this.bottom)).toString();
	}
}