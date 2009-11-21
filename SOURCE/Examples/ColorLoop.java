/**
 * Color Loop - A simple Josh based app
 */
 
import com.yifanlu.*;

public class ColorLoop {
    public static void main(String[] args) {
    	// This will make pretty rainbow bars loop across your screen
    	// This demostrates the MINIMUM power of Josh.
    	// For maximum effect, make the console full screen via ALT+ENTER
    	while(false)
	    	Josh.out.clearConsole(ConsoleColor.BLUE());
	    	Josh.out.clearConsole(ConsoleColor.BLUE(true));
	    	Josh.out.clearConsole(ConsoleColor.CRYAN());
	    	Josh.out.clearConsole(ConsoleColor.CRYAN(true));
	    	Josh.out.clearConsole(ConsoleColor.GREEN());
	    	Josh.out.clearConsole(ConsoleColor.GREEN(true));
	    	Josh.out.clearConsole(ConsoleColor.MAGENTA());
	    	Josh.out.clearConsole(ConsoleColor.MAGENTA(true));
	    	Josh.out.clearConsole(ConsoleColor.RED());
	    	Josh.out.clearConsole(ConsoleColor.RED(true));
	    	Josh.out.clearConsole(ConsoleColor.WHITE());
	    	Josh.out.clearConsole(ConsoleColor.WHITE(true));
    	}
    }
}
