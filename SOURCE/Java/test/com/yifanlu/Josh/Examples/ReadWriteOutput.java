package com.yifanlu.Josh.Examples;

import com.yifanlu.Josh.*;

// This example shows outputing data in advanced ways
public class ReadWriteOutput {

    public static void main(String[] args) {
        /* Setting output attributes */

        // Set attributes (output color) to blue background & bright green text
        Josh.outBuffer.setConsoleTextAttribute(new ConsoleAttribute(ConsoleColor.BACKGROUND_BLUE | ConsoleColor.FOREGROUND_GREEN | ConsoleColor.FOREGROUND_INTENSITY));
        // Output text
        Josh.out.println("Hello!"); // Method 1
        System.out.println("Hello!"); // Method 2

        // Press enter to continue
        Josh.in.readLine();



        /* Copying and pasting attribute data */

        // Set the output color to be green on blue background
        Josh.outBuffer.setConsoleTextAttribute(new ConsoleAttribute(ConsoleColor.BACKGROUND_BLUE | ConsoleColor.FOREGROUND_GREEN));

        // Read the attribute data
        ConsoleAttribute[] temp = Josh.outBuffer.readConsoleOutputAttribute(new ConsoleCoord(1, 1), 9);

        // Set the output color for the new buffer to be blue on red background
        Josh.outBuffer.setConsoleTextAttribute(new ConsoleAttribute(ConsoleColor.BACKGROUND_RED | ConsoleColor.FOREGROUND_BLUE));

        // Write the copied attributes
        Josh.outBuffer.writeConsoleOutputAttribute(temp, new ConsoleCoord(5, 5), 9);

        // Press enter to continue
        Josh.in.readLine();



        /* Copying and pasting characters */

        // Read the characters
        char[] chars = Josh.outBuffer.readConsoleOutputChar(new ConsoleCoord(1, 1), 9);

        // Write the characters
        Josh.outBuffer.writeConsoleOutputChar(chars, new ConsoleCoord(5, 5), 9);

        // Press enter to continue
        Josh.in.readLine();



        /* Copying and pasting character & attribute at the same time */

        // Read the characters
        ConsoleCharInfo[] toPaste = Josh.outBuffer.readConsoleOutput(new ConsoleCoord(9, 1), new ConsoleCoord(0, 0), new ConsoleSmallRect(0, 0, 8, 0));

        // Write the characters
        Josh.outBuffer.writeConsoleOutput(toPaste, new ConsoleCoord(9, 1), new ConsoleCoord(3, 3), new ConsoleSmallRect(3, 3, 10, 3));
    }
}
