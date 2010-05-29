package com.yifanlu.Josh;

/**
 * Contains information about the console history.
 *
 * @see <a href="http://msdn.microsoft.com/en-us/library/ms682077(v=VS.85).aspx">Windows' CONSOLE_HISTORY_INFO structure</a>
 * @see Josh#getConsoleHistoryInfo
 * @see Josh#setConsoleHistoryInfo
 * @author Yifan Lu
 * @version 1.0, 04/24/10
 * @since 0.1
 */
public class ConsoleHistoryInfo {
	private int historyBufferSize;
	private int numberOfHistoryBuffers;
	private int flags;
	
	/**
     * Flag value: Duplicate entries will not be stored in the history buffer.
     */
	public static final int HISTORY_NO_DUP_FLAG = 0x1;

	/**
     * Creates a new console history infomation.
	 * 
     * @param historyBufferSize The number of commands kept in each history buffer.
	 * @param numberOfHistoryBuffers The number of history buffers kept for this console process.
	 * @param flags Set using constants defined in this class.
     */
    public ConsoleHistoryInfo(int historyBufferSize, int numberOfHistoryBuffers, int flags) {
    	this.historyBufferSize = historyBufferSize;
    	this.numberOfHistoryBuffers = numberOfHistoryBuffers;
    	this.flags = flags;
    }
	
	/**
     * Gets the size of the history buffer.
	 * 
     * @return The number of commands kept in each history buffer.
     */
	public int getHistoryBufferSize(){
		return this.historyBufferSize;
	}
	
	/**
     * Gets the number of history buffers.
	 * 
     * @return The number of history buffers kept for this console process.
     */
	public int getNumberOfHistoryBuffers(){
		return this.numberOfHistoryBuffers;
	}
	
	/**
     * Gets the history buffer flags.
	 * Compare with the constants in this class.
	 * 
     * @return The flags as an int.
     */
	public int getFlags(){
		return this.flags;
	}
	
	/**
     * Sets the size of the history buffer.
	 * 
     * @param historyBufferSize The number of commands kept in each history buffer.
     */
	public void setHistoryBufferSize(int historyBufferSize){
		this.historyBufferSize = historyBufferSize;
	}
	
	/**
     * Sets the number of history buffers.
	 * 
     * @param numberOfHistoryBuffers The number of history buffers kept for this console process.
     */
	public void setNumberOfHistoryBuffers(int numberOfHistoryBuffers){
		this.numberOfHistoryBuffers = numberOfHistoryBuffers;
	}
	
	/**
     * Sets the history buffer flags.
	 * Use the constants in this class.
	 * 
     * @param flags The flags as an int.
     */
	public void setFlags(int flags){
		this.flags = flags;
	}
	
	/** 
	 * Compares two Objects, if both are ConsoleHistoryInfo and have the same values, they are equal.
     *
     * @param anObject Another ConsoleHistoryInfo object.
	 * @return true if all values are equal.
	 */
	public boolean equals(Object anObject) {
		if(anObject instanceof ConsoleHistoryInfo){
			ConsoleHistoryInfo otherObject = (ConsoleHistoryInfo)anObject;
			return (this.getHistoryBufferSize() == otherObject.getHistoryBufferSize()) && (this.getNumberOfHistoryBuffers() == otherObject.getNumberOfHistoryBuffers()) && (this.getFlags() == otherObject.getFlags());
		}else
			return false;
	}
	
	/** 
	 * A {@link java.lang.String} representation of this object.
	 * 
	 * @return The history buffer size, the number of history buffers, and the flags
	 */
	public String toString(){
		return historyBufferSize + ", " + numberOfHistoryBuffers + ", " + flags;
	}
}