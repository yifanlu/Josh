package com.yifanlu.Josh.Examples;

import com.yifanlu.Josh.*;

// Change the font
public class FontExamples {

    public static void main(String[] args) {
        for (int x = 0; x < 10; x++) // There are 10 different fonts
        {
            Josh.outBuffer.setCurrentConsoleFont(x); // Set the font
            Josh.out.println("This is font #" + x);
            Josh.in.readLine(); // Press enter to continue
        }
    }
}
