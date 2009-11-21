import com.yifanlu.*;
import java.util.ArrayList;

public class MouseCursor {
    public static void main(String[] args) {
    	boolean keepLooping = true;
    	int numOfEvents = 0;
    	ArrayList<ConsoleCoord> clickedLocations = new ArrayList<ConsoleCoord>();
    	ConsoleCoord current = new ConsoleCoord(0,0);
    	ConsoleCoord previous = new ConsoleCoord(0,0);
    	while(keepLooping)
    	{
    		while(Josh.inBuffer.getNumberOfConsoleInputEvents() > 0)
    		{
		    	ConsoleEvent event = Josh.inBuffer.readConsoleInput();
	    		if(event.eventType == event.MOUSE_EVENT)
	    		{
	    			previous = current;
	    			current = event.mouseEvent.dwMousePosition;
	    			if(event.mouseEvent.dwButtonState > 0)
	    			{
	    				if(clickedLocations.contains(current))
	    					clickedLocations.remove(clickedLocations.indexOf(current));
	    				else
	    					clickedLocations.add(current);
			    		for(ConsoleCoord coord : clickedLocations)
				    		Josh.out.fillWithChar(coord,1,(char)((221 * Math.random()) + 179));
	    			}
		    		Josh.out.fillWithChar(previous,1,(char)0);
		    		Josh.out.fillWithChar(current,1,(char)219);
	    		}
    		}
    	}
    }
}
