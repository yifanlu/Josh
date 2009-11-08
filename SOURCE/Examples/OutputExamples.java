import com.yifanlu.*;

// This examples demostrates output & buffers

public class OutputExamples {
    public static void main(String[] args) {
        // Function out.setOutputColor(ConsoleColor foreground [, ConsoleColor highlight])
        Josh.out.setOutputColor(ConsoleColor.BLUE());
        System.out.println ("Blue text"); // Standard out: blue text in regular background
        Josh.out.setOutputColor(ConsoleColor.BLUE(), ConsoleColor.RED(true));
        System.out.println ("Red text"); // Standard out: blue text in bright red background
        
    	// Function out.printcolorln(ConsoleColor, String, ...):
    	// Allows shorthand setting of color and printing of string in one line
    	// You can keep changing color and printing in the order of ConsoleColor, String, ConsoleColor, String ...
    	// The foreground will always change back to white at the end UNLESS you end with a ConsoleColor()
        Josh.out.printcolorln(ConsoleColor.YELLOW(), "Some Text in yellow ", ConsoleColor.BLUE(), "Some text in blue ", ConsoleColor.MAGENTA(), "Some text in magenta\n");
        
    	// Function out.printhighlightln(ConsoleColor, String, ...):
    	// Same as printcolorln(), but sets background (highlight) instead of foreground
    	// You can keep changing color and printing in the order of ConsoleColor, String, ConsoleColor, String ...
    	// The background will always change back to black at the end UNLESS you end with a ConsoleColor()
        Josh.out.printhighlightln(ConsoleColor.YELLOW(), "Some Text highlighted yellow ", ConsoleColor.BLUE(), "Some text highlighted blue ", ConsoleColor.MAGENTA(), "Some text highlighted magenta\n");
    	
    	// HINT: You can use any string functions such as String.format to emulate out.printf()
        Josh.out.printcolorln(ConsoleColor.BLUE(), String.format("String Formatter %.2f\n", (float)123.456789f), ConsoleColor.MAGENTA()); // Last argument is ConsoleColor, so sets to it at end
    
    	// Function out.clearConsole([ConsoleColor background]):
    	// Allows you to clear the console to a blank black screen OR if you want a colored screen of your choice. Couldn't be simplier
    	System.out.println ("Press enter to clear screen!");
    	System.console().readLine();
    	Josh.out.clearConsole();
    	System.out.println ("Press enter to clear to a blue screen!");
    	System.console().readLine();
    	Josh.out.clearConsole(ConsoleColor.BLUE());
    	
    	// Function outBuffer.getConsoleScreenBufferInfo():
    	// Returns a ConsoleScreenBufferInfo structure containing the buffer's info
    	ConsoleScreenBufferInfo info = Josh.outBuffer.getConsoleScreenBufferInfo();
    	System.out.println ("The size of this window is: " + info.dwSize.X + " x " + info.dwSize.Y);
    	System.out.println ("The cursor is at: " + info.dwCursorPosition.X + " x " + info.dwCursorPosition.Y);
    	
    	// Function out.setConsoleCursorPosition(int x, int y) OR setConsoleCursorPosition(int[2] coord):
    	// Moves the console pointer to this position for inputting or outputting.
    	Josh.out.setConsoleCursorPosition(10, 10);
    	System.out.print ("Please type something: ");
    	System.console().readLine();
    	Josh.out.setConsoleCursorPosition(15,15);
    	
    	// ConsoleBuffers
    	// Windows allows only one buffer to be visible at a time. However, you can have as many invisible buffers as you want.
    	// These buffers act just like the visible one and you can switch between them.
    	ConsoleBuffer newBuff = new ConsoleBuffer(); // Class ConsoleBuffer([boolean readPremission = true], [boolean readPremission = true])
    	
    	// Function out.highlight(int x, int y [OR ConsoleCoord], int length, ConsoleColor foreground [, ConsoleColor background])
    	// Allows you to select any piece of output starting at your coordinates x, y and goes for the amount in length. It will modify this piece of output
    	// and change it to whatever color your foreground and background is.
    	Josh.out.highlight(15, 15, 3, ConsoleColor.YELLOW(), ConsoleColor.GREEN());
    	
    	// Function outBuffer.getConsoleCursorInfo()
    	// Just returns the size and visibility of your cursor in a 1x2 array
    	ConsoleCursorInfo cursor = Josh.outBuffer.getConsoleCursorInfo();
    	Josh.out.println("Your cursor size: " + cursor.size + " & visibility: " + cursor.visible);
    	
    	// Function out.fillWithChar([ConsoleCoord coord OR int x, int y], int length, char character)
    	// Fills "length" amount of "character"(s) starting at x, y
    	Josh.out.fillWithChar(new ConsoleCoord(2,2),10,(char)219); // Fills with the character 219 for 10 blocks starting at 2,2
    }
}
