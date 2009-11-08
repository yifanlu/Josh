/**
 * Josh - Java Output Shell enHanced
 * Psudostructure: Represents a console history info
 * By Yifan Lu
*/

package com.yifanlu;

public class ConsoleHistoryInfo {
	
	public int historyBufferSize;
	public int numberOfHistoryBuffers;
	public int dwFlags;
	
	public static final int HISTORY_NO_DUP_FLAG = 0x1;

    public ConsoleHistoryInfo(int historyBufferSize, int numberOfHistoryBuffers, int dwFlags) {
    	this.historyBufferSize = historyBufferSize;
    	this.numberOfHistoryBuffers = numberOfHistoryBuffers;
    	this.dwFlags = dwFlags;
    }
    
}