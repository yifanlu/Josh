package com.yifanlu.Josh.Examples;

import com.yifanlu.Josh.*;

// Flashes the screen with colors.
public class ColorLoop {

    public static void main(String[] args) {
        // This will make pretty rainbow bars loop across your screen
        // This demostrates the MINIMUM power of Josh.
        // For maximum effect, make the console full screen via ALT+ENTER
        while (true) {
            Josh.out.clearConsole(ConsoleColor.BLUE(false));
            Josh.out.clearConsole(ConsoleColor.BLUE(true));
            Josh.out.clearConsole(ConsoleColor.CRYAN(false));
            Josh.out.clearConsole(ConsoleColor.CRYAN(true));
            Josh.out.clearConsole(ConsoleColor.GREEN(false));
            Josh.out.clearConsole(ConsoleColor.GREEN(true));
            Josh.out.clearConsole(ConsoleColor.MAGENTA(false));
            Josh.out.clearConsole(ConsoleColor.MAGENTA(true));
            Josh.out.clearConsole(ConsoleColor.RED(false));
            Josh.out.clearConsole(ConsoleColor.RED(true));
            Josh.out.clearConsole(ConsoleColor.WHITE(false));
            Josh.out.clearConsole(ConsoleColor.WHITE(true));
        }
    }
}
