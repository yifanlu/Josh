package com.yifanlu.JoshExamples;

import com.yifanlu.Josh.*;
import java.util.ArrayList;

// Demonstrates mouse events
// Move the mouse around for a cursor, click and drag for a tail

public class MouseCursor {
    public static void main(String[] args) {
    	boolean keepLooping = true;
    	int numOfEvents = 0;
    	ArrayList<ConsoleCoord> clickedLocations = new ArrayList<ConsoleCoord>();
    	ConsoleCoord current = new ConsoleCoord(0,0);
    	ConsoleCoord previous = new ConsoleCoord(0,0);
    	while(keepLooping)
    	{
    		while(Josh.inBuffer.getNumberOfConsoleInputEvents() > 0) // Look for events
    		{
		    	ConsoleEvent event = Josh.inBuffer.readConsoleInput(); // Found one
	    		if(event.getEventType() == event.MOUSE_EVENT) // We are only intrested in mouse events
	    		{
	    			ConsoleEventMouse mEvent = (ConsoleEventMouse)event;
	    			previous = current;
	    			current = mEvent.getMousePosition();
	    			if(mEvent.getButtonState() > 0) // Clicked
	    			{
	    				if(clickedLocations.contains(current))
	    					clickedLocations.remove(clickedLocations.indexOf(current));
	    				else
	    					clickedLocations.add(current);
			    		for(ConsoleCoord coord : clickedLocations)
				    		Josh.outBuffer.fillConsoleOutputCharacter(coord,1,(char)((221 * Math.random()) + 179)); // Make a tail
	    			}
		    		Josh.outBuffer.fillConsoleOutputCharacter(previous,1,(char)0); // Set old location to be blank
		    		Josh.outBuffer.fillConsoleOutputCharacter(current,1,(char)219); // Set new location to have a block
	    		}
    		}
    	}
    }
}
