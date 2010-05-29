package com.yifanlu.Josh.Examples;

import com.yifanlu.Josh.*;

// This example shows creating and switching screen buffers
public class ScreenBuffers {

    public static void main(String[] args) {
        // Set the output color to be green on blue background
        Josh.outBuffer.setConsoleTextAttribute(new ConsoleAttribute(ConsoleColor.BACKGROUND_BLUE | ConsoleColor.FOREGROUND_GREEN));

        // Write some lines of data
        for (int x = 0; x < 26; x++) {
            Josh.out.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        }

        // Create a new screen buffer
        ConsoleBuffer newBuff = new ConsoleBuffer();

        // Set the output color for the new buffer to be blue on red background
        newBuff.outBuffer.setConsoleTextAttribute(new ConsoleAttribute(ConsoleColor.BACKGROUND_RED | ConsoleColor.FOREGROUND_BLUE));

        // Write some lines of data in the new buffer
        for (int x = 0; x < 26; x++) {
            newBuff.out.println("ZYXWVUTSRQPONMLKJIHGFEDCBA");
        }

        // Press enter to continue
        Josh.in.readLine();

        // Show the new screen buffer
        Josh.setConsoleActiveScreenBuffer(newBuff);

        // Press enter to continue
        Josh.in.readLine();


        /* Scrolling screen buffer */
        // Takes one block of data and shifts it to another location
        Josh.outBuffer.scrollConsoleScreenBuffer(
                new ConsoleSmallRect(0, 0, 5, 5), // Start block
                new ConsoleSmallRect(0, 0, 10, 10), // Move limit (if moved outside this area, it'll be truncated
                new ConsoleCoord(8, 8), // Where to move block to
                new ConsoleCharInfo('X', ConsoleColor.YELLOW(false)) // What to fill the empty space with
                );
    }
}
