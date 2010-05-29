package com.yifanlu.JoshExamples;

import com.yifanlu.Josh.*;

// Shows what key is pressed and where the mouse is at
public class ConsoleEvents {

    public static void main(String[] args) {
        boolean keepLooping = true;
        int numOfEvents = 0;
        while (keepLooping) {
            numOfEvents = Josh.inBuffer.getNumberOfConsoleInputEvents(); // Count the number of events in the buffer
            while (numOfEvents > 0) {
                ConsoleEvent event = Josh.inBuffer.readConsoleInput();
                if (event.getEventType() == event.KEY_EVENT) // Key pressed
                {
                    ConsoleEventKeyboard keyEvent = (ConsoleEventKeyboard) event;
                    Josh.outBuffer.setConsoleCursorPosition(new ConsoleCoord(0, 0)); // Output only at the beginning
                    // Output information
                    System.out.println("Keyboard: " + keyEvent.getChar() + "\t|\tState: " + (keyEvent.getKeyDown() ? "Pressed" : "Let Go") + "\t|\tKey Code: " + keyEvent.getVirtualKeyCode() + "\t|\tControl Keys:\t" + keyEvent.getControlKeyState() + "\t\t\t");
                } else if (event.getEventType() == event.MOUSE_EVENT) // Mouse moved/clicked
                {
                    ConsoleEventMouse mouseEvent = (ConsoleEventMouse) event;
                    Josh.outBuffer.setConsoleCursorPosition(new ConsoleCoord(0, 1)); // Output only at the beginning
                    // Output information
                    System.out.println("Mouse   : " + mouseEvent.getMousePosition().getX() + "," + mouseEvent.getMousePosition().getY() + "\t|\tClicked: " + mouseEvent.getButtonState() + "\t|\tFlags: " + mouseEvent.getEventFlags() + "\t|\tControl Keys:\t" + mouseEvent.getControlKeyState() + "\t\t\t");
                }
                numOfEvents = Josh.inBuffer.getNumberOfConsoleInputEvents(); // Check how many events are left
            }
        }
    }
}
