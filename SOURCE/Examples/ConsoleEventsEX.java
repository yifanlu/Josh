// A simple example showing events

import com.yifanlu.*;

public class ConsoleEventsEX {
    public static void main(String[] args) {
    	boolean keepLooping = true;
    	int numOfEvents = 0;
    	while(keepLooping)
    	{
    		numOfEvents = Josh.inBuffer.getNumberOfConsoleInputEvents();
    		while(numOfEvents > 0)
    		{
	    		ConsoleEvent event = Josh.inBuffer.readConsoleInput();
	    		if(event.eventType == event.KEY_EVENT)
	    		{
	    			Josh.out.setConsoleCursorPosition(0,0);
	    			System.out.println ("Keyboard: " + event.keyboardEvent.uChar + "\t|\tState: " + (event.keyboardEvent.bKeyDown ? "Pressed" : "Let Go") + "\t|\tKey Code: " + event.keyboardEvent.wVirtualKeyCode + "\t|\tControl Keys:\t" + event.keyboardEvent.dwControlKeyState + "\t\t\t");
	    		}
	    		else if(event.eventType == event.MOUSE_EVENT)
	    		{
	    			Josh.out.setConsoleCursorPosition(0,1);
	    			System.out.println ("Mouse   : " + event.mouseEvent.dwMousePosition.X + "," + event.mouseEvent.dwMousePosition.Y + "\t|\tClicked: " + event.mouseEvent.dwButtonState + "\t|\tFlags: " + event.mouseEvent.dwEventFlags + "\t|\tControl Keys:\t" + event.mouseEvent.dwControlKeyState + "\t\t\t");
	    		}
	    		numOfEvents = Josh.inBuffer.getNumberOfConsoleInputEvents();
    		}
    	}
    }
}
