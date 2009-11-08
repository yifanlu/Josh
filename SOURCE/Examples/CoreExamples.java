import com.yifanlu.*;

// This example demostrates the core functionalities of Josh (without getting into buffers, output, input, etc)

public class CoreExamples {

    public static void main(String[] args) throws InterruptedException {
        // Function setConsoleTitle(String title)
        // Sets the title of the console window
        Josh.setConsoleTitle("Hello World");
        
        // Function getConsoleTitle()
        // Returns a String with the console's current title
        String title = Josh.getConsoleTitle();
        System.out.println ("The console's title is: " + title);
        
        // Function getConsoleOrginialTitle()
        // Returns a String with the console's ORGINAL title
        String orginalTitle = Josh.getConsoleOrginialTitle();
        System.out.println ("The console's orginal title is: " + orginalTitle);
        
        // Function isFullScreen()
        // Returns a boolean if the console window is full screen or not
        boolean isFull = Josh.isFullScreen();
        if(isFull)
        	System.out.println ("You're in full screen mode!");
        else
        	System.out.println ("You're not in full screen mode!");
        	
        // Function getConsoleSelectionInfo()
        // Returns a ConsoleSelectionInfo structure containing information about the selection
        System.out.println ("Select something. You have 5 seconds.");
        Thread.currentThread().sleep(5000);
        ConsoleSelectionInfo selection = Josh.getConsoleSelectionInfo();
        System.out.println ("Selection started at: " + selection.dwSelectionAnchor.X + ", " + selection.dwSelectionAnchor.Y);
    }
}
