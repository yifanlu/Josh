package com.yifanlu.Josh;

/**
 * Contains a handle to a memory location.
 * Note that subclasses of JoshBuffer are immutable, 
 * therefore objects created cannot be modified, but 
 * can be replaced.
 *
 * @author Yifan Lu
 * @version 1.0, 04/24/10
 * @since 1.0
 */
public abstract class JoshBuffer {
	private ConsoleHandle handle;
	
	/** 
	 * Gets the {@link ConsoleHandle memory location} of this buffer.
	 * 
	 * @return The memory location.
	 */
	public ConsoleHandle getHandle() {
		return this.handle;
	}
	
	protected void setHandle(ConsoleHandle handle) {
		this.handle = handle;
	}
	
	/** 
	 * Compares two Objects, if both are JoshBuffer and have the same handle, they are equal.
     *
     * @param anObject Another JoshBuffer object.
	 * @return true if both the memory handles are equal.
	 */
	public boolean equals(Object anObject) {
		Class theClass = anObject.getClass().getSuperclass();
		if(theClass != null && theClass.equals(JoshBuffer.class))
			return this.getHandle().equals(((JoshBuffer)anObject).getHandle());
		else
			return false;
	}
}