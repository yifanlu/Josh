package com.yifanlu.Josh;

/**
 * Provides simplified access to the output buffer.
 * This is recommended for users who just want to change text colors and other popular console controls.<br />
 * Objects from this class are immutable.
 *
 * @author Yifan Lu
 * @version 1.3, 05/29/10
 * @since 0.1
 */
public class JoshOutput extends JoshBuffer {

    private JoshOutBuffer methods;

    /**
     * Creates a new output buffer for simplified manipulation of the output buffer.
     *
     * @param handle A memory location that this buffer represents.
     */
    public JoshOutput(ConsoleHandle handle) {
        this.setHandle(handle);
        methods = new JoshOutBuffer(handle);
    }

    /**
     * Clears the console buffer to a blank black screen.
     */
    public void clearConsole() {
        clearConsole(ConsoleColor.BLACK(false));
    }

    /**
     * Clears the console buffer and sets the background color.
     *
     * @param background The background color of the console buffer.
     */
    public void clearConsole(ConsoleColor background) {
        background.makeBackground();
        Josh.CLEARSCREEN(getHandle().getMemoryLocation(), background.getAttributes());
    }

    /**
     * Print a String and then terminate the line.
     *
     * @param output The String to be printed.
     */
    public void println(String output) {
        print(output + "\n");
    }

    /**
     * Print a string.
     *
     * @param output The String to be printed.
     */
    public void print(String output) {
        Josh.WRITECONSOLE(getHandle().getMemoryLocation(), output);
    }

    /**
     * Print a String with a background color and then change the character color back to defaults and terminate the line.
     * Call the function like this: {@code printhightln(ConsoleColor.BLUE(), "This is blue text", ConsoleColor.GREEN(), "This is green text");}
     * Note that this method accepts unlimited number of paramaters and interprets it as an array or objects. The array MUST be in the order of
     * {@link ConsoleColor}, then {@link java.lang.String}.
     *
     * @param objects An array of Object in the order of {@link ConsoleColor}, then {@link java.lang.String} in that order.
     * @throws java.lang.IllegalArgumentException If the order of the paramaters is not correct.
     */
    public void printhighlightln(Object... objects) throws IllegalArgumentException {
        final ConsoleColor black = ConsoleColor.BLACK(false);
        black.makeBackground();
        int count = 0;
        for (Object obj : objects) {
            if (count % 2 == 0) {
                if (!(obj instanceof ConsoleColor)) {
                    throw new IllegalArgumentException("This parameter must be a ConsoleColor.");
                }
                ((ConsoleColor) obj).makeBackground();
                methods.setConsoleTextAttribute((ConsoleAttribute) obj);
            } else {
                if (!(obj instanceof String)) {
                    throw new IllegalArgumentException("This parameter must be a String.");
                }
                print((String) obj);
            }
            count++;
        }
        if (count % 2 == 0) {
            methods.setConsoleTextAttribute((ConsoleAttribute) black);
        }
    }

    /**
     * Print a String with a foreground color and then change the character color back to defaults and terminate the line.
     * Call the function like this: {@code printcolorln(ConsoleColor.BLUE(), "This is blue colored text", ConsoleColor.GREEN(), "This is green colored text");}
     * Note that this method accepts unlimited number of paramaters and interprets it as an array or objects. The array MUST be in the order of
     * {@link ConsoleColor}, then {@link java.lang.String}.
     *
     * @param objects An array of Object in the order of {@link ConsoleColor}, then {@link java.lang.String} in that order.
     * @throws java.lang.IllegalArgumentException If the order of the paramaters is not correct.
     */
    public void printcolorln(Object... objects) throws IllegalArgumentException {
        int count = 0;
        for (Object obj : objects) {
            if (count % 2 == 0) {
                if (!(obj instanceof ConsoleColor)) {
                    throw new IllegalArgumentException("This parameter must be a ConsoleColor.");
                }
                ((ConsoleColor) obj).makeForeground();
                methods.setConsoleTextAttribute((ConsoleAttribute) obj);
            } else {
                if (!(obj instanceof String)) {
                    throw new IllegalArgumentException("This parameter must be a String.");
                }
                print((String) obj);
            }
            count++;
        }
        if (count % 2 == 0) {
            methods.setConsoleTextAttribute((ConsoleAttribute) ConsoleColor.WHITE(false));
        }
    }
}
