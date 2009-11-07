/**
 * Color Loop - A simple Josh based app
 */
 
import com.yifanlu.*;

public class JoshExample {
    public static void main(String[] args) {
    	// This will make pretty rainbow bars loop across your screen
    	// This demostrates the MINIMUM power of Josh.
    	// For maximum effect, make the console full screen via ALT+ENTER
    	while(true){
	    	Josh.buffer.clearConsole(ConsoleColor.BLUE());
	    	Josh.buffer.clearConsole(ConsoleColor.BLUE(true));
	    	Josh.buffer.clearConsole(ConsoleColor.CRYAN());
	    	Josh.buffer.clearConsole(ConsoleColor.CRYAN(true));
	    	Josh.buffer.clearConsole(ConsoleColor.GREEN());
	    	Josh.buffer.clearConsole(ConsoleColor.GREEN(true));
	    	Josh.buffer.clearConsole(ConsoleColor.MAGENTA());
	    	Josh.buffer.clearConsole(ConsoleColor.MAGENTA(true));
	    	Josh.buffer.clearConsole(ConsoleColor.RED());
	    	Josh.buffer.clearConsole(ConsoleColor.RED(true));
	    	Josh.buffer.clearConsole(ConsoleColor.WHITE());
	    	Josh.buffer.clearConsole(ConsoleColor.WHITE(true));
    	}
    }
}
