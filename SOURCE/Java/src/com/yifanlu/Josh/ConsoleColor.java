package com.yifanlu.Josh;

/**
 * Stores the color attributes of console output.
 * This is just {@link ConsoleAttribute} with color constants 
 * and methods.
 *
 * @see JoshOutput
 * @author Yifan Lu
 * @version 1.3, 05/29/10
 * @since 0.1
 */
public class ConsoleColor extends ConsoleAttribute {

    private boolean isBackground;
    /**
     * Forground color constant.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms682088(v=VS.85).aspx#_win32_character_attributes">Windows console attributes constants</a>
     */
    public static final int FOREGROUND_BLUE = 0x0001, FOREGROUND_GREEN = 0x0002, FOREGROUND_RED = 0x0004, FOREGROUND_INTENSITY = 0x0008;
    /**
     * Background color constant.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms682088(v=VS.85).aspx#_win32_character_attributes">Windows console attributes constants</a>
     */
    public static final int BACKGROUND_BLUE = 0x0010, BACKGROUND_GREEN = 0x0020, BACKGROUND_RED = 0x0040, BACKGROUND_INTENSITY = 0x0080;

    /**
     * Create a new color object by specifying which colors to show.
     * This constructor creates a color by mixing primary colors (red, blue, green),
     * think of the boolean paramaters as colors to mix together.
     *
     * @param red Mix red.
     * @param blue Mix blue.
     * @param green Mix green.
     * @param bright Makes color bright.
     * @param background This color is a background color (foreground is the text color, 
     *		  background is the color behind the text, like a highlight).
     */
    public ConsoleColor(boolean red, boolean blue, boolean green, boolean bright, boolean background) {
        if (red) {
            addAttributes(ConsoleColor.FOREGROUND_RED);
        }
        if (blue) {
            addAttributes(ConsoleColor.FOREGROUND_BLUE);
        }
        if (green) {
            addAttributes(ConsoleColor.FOREGROUND_GREEN);
        }
        if (bright) {
            addAttributes(ConsoleColor.FOREGROUND_INTENSITY);
        }
        if (background) {
            makeBackground();
        }
    }

    /**
     * Makes any color in the attribute a background color (highlight).
     */
    public void makeBackground() {
        if (!isBackground) {
            this.attributes *= 0x0010;
        }
        isBackground = true;
    }

    /**
     * Makes any color in the attribute a foreground color (text color).
     */
    public void makeForeground() {
        if (isBackground) {
            this.attributes /= 0x0010;
        }
        isBackground = false;
    }

    /**
     * Creates and returns a red color attribute.
     * The boolean bright parameter is optional.
     *
     * @param bright <em>Optional</em>: Returns bright red.
     * @return A red ConsoleColor object.
     */
    public static final ConsoleColor RED(boolean bright) {
        return new ConsoleColor(true, false, false, bright, false);
    }

    /**
     * @deprecated Same as {@code RED(false)}
     * @see #RED(boolean)
     */
    public static final ConsoleColor RED() {
        return RED(false);
    }

    /**
     * Creates and returns a blue color attribute.
     * The boolean bright parameter is optional.
     *
     * @param bright <em>Optional</em>: Returns bright blue.
     * @return A blue ConsoleColor object.
     */
    public static final ConsoleColor BLUE(boolean bright) {
        return new ConsoleColor(false, true, false, bright, false);
    }

    /**
     * @deprecated Same as {@code BLUE(false)}
     * @see #BLUE(boolean)
     */
    public static final ConsoleColor BLUE() {
        return BLUE(false);
    }

    /**
     * Creates and returns a green color attribute.
     * The boolean bright parameter is optional.
     *
     * @param bright <em>Optional</em>: Returns bright green.
     * @return A green ConsoleColor object.
     */
    public static final ConsoleColor GREEN(boolean bright) {
        return new ConsoleColor(false, false, true, bright, false);
    }

    /**
     * @deprecated Same as {@code GREEN(false)}
     * @see #GREEN(boolean)
     */
    public static final ConsoleColor GREEN() {
        return GREEN(false);
    }

    /**
     * Creates and returns a yellow color attribute.
     * The boolean bright parameter is optional.
     *
     * @param bright <em>Optional</em>: Returns bright yellow.
     * @return A yellow ConsoleColor object.
     */
    public static final ConsoleColor YELLOW(boolean bright) {
        return new ConsoleColor(true, false, true, bright, false);
    }

    /**
     * @deprecated Same as {@code YELLOW(false)}
     * @see #YELLOW(boolean)
     */
    public static final ConsoleColor YELLOW() {
        return YELLOW(false);
    }

    /**
     * Creates and returns a cryan color attribute.
     * The boolean bright parameter is optional.
     *
     * @param bright <em>Optional</em>: Returns bright cryan.
     * @return A cryan ConsoleColor object.
     */
    public static final ConsoleColor CRYAN(boolean bright) {
        return new ConsoleColor(false, true, true, bright, false);
    }

    /**
     * @deprecated Same as {@code CRYAN(false)}
     * @see #CRYAN(boolean)
     */
    public static final ConsoleColor CRYAN() {
        return CRYAN(false);
    }

    /**
     * Creates and returns a magenta color attribute.
     * The boolean bright parameter is optional.
     *
     * @param bright <em>Optional</em>: Returns bright magenta.
     * @return A magenta ConsoleColor object.
     */
    public static final ConsoleColor MAGENTA(boolean bright) {
        return new ConsoleColor(true, true, false, bright, false);
    }

    /**
     * @deprecated Same as {@code MAGENTA(false)}
     * @see #MAGENTA(boolean)
     */
    public static final ConsoleColor MAGENTA() {
        return MAGENTA(false);
    }

    /**
     * Creates and returns a white color attribute.
     * The boolean bright parameter is optional.
     *
     * @param bright <em>Optional</em>: Returns bright white.
     * @return A white ConsoleColor object.
     */
    public static final ConsoleColor WHITE(boolean bright) {
        return new ConsoleColor(true, true, true, bright, false);
    }

    /**
     * @deprecated Same as {@code WHITE(false)}
     * @see #WHITE(boolean)
     */
    public static final ConsoleColor WHITE() {
        return WHITE(false);
    }

    /**
     * Creates and returns a black color attribute.
     * The boolean bright parameter is optional.
     *
     * @param bright <em>Optional</em>: Returns bright black.
     * @return A black ConsoleColor object.
     */
    public static final ConsoleColor BLACK(boolean bright) {
        return new ConsoleColor(false, false, false, bright, false);
    }

    /**
     * @deprecated Same as {@code BLACK(false)}
     * @see #BLACK(boolean)
     */
    public static final ConsoleColor BLACK() {
        return BLACK(false);
    }
}
