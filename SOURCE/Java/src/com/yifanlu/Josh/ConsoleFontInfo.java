package com.yifanlu.Josh;

/**
 * Contains information for a console font.
 *
 * @see JoshOutBuffer#getCurrentConsoleFont
 * @see JoshOutBuffer#setCurrentConsoleFont
 * @author Yifan Lu
 * @version 1.3, 05/29/10
 * @since 0.1
 */
public class ConsoleFontInfo {

    private int font;
    private ConsoleCoord fontSize;

    /**
     * Creates a new console font info.
     *
     * @param font The index of the font in the system's console font table.
     * @param fontSize The width and height of each character in the font, in logical units.
     *	The <strong>X</strong> member contains the width, while the <strong>Y</strong> member contains the height.
     */
    public ConsoleFontInfo(int font, ConsoleCoord fontSize) {
        this.font = font;
        this.fontSize = fontSize;
    }

    /**
     * Gets the font.
     *
     * @return The index of the font in the system's console font table.
     */
    public int getFont() {
        return this.font;
    }

    /**
     * Gets the font size.
     *
     * @return The width and height of each character in the font, in logical units.
     *	The <strong>X</strong> member contains the width, while the <strong>Y</strong> member contains the height.
     */
    public ConsoleCoord getFontSize() {
        return this.fontSize;
    }

    /**
     * Sets the font.
     *
     * @param font The index of the font in the system's console font table.
     */
    public void setFont(int font) {
        this.font = font;
    }

    /**
     * Sets the font size.
     *
     * @param fontSize The width and height of each character in the font, in logical units.
     *	The <strong>X</strong> member contains the width, while the <strong>Y</strong> member contains the height.
     */
    public void setFontSize(ConsoleCoord fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * Compares two Objects, if both are ConsoleFontInfo and have the same values, they are equal.
     *
     * @param anObject Another ConsoleFontInfo object.
     * @return true if all values are equal.
     */
    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof ConsoleFontInfo) {
            ConsoleFontInfo otherObject = (ConsoleFontInfo) anObject;
            return (this.getFont() == otherObject.getFont()) && (this.getFontSize().equals(otherObject.getFontSize()));
        } else {
            return false;
        }
    }

    /**
     * Returns a hash code.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.font;
        hash = 97 * hash + (this.fontSize != null ? this.fontSize.hashCode() : 0);
        return hash;
    }

    /**
     * A {@link java.lang.String} representation of this object.
     *
     * @return The font index, and font size.
     */
    @Override
    public String toString() {
        return font + ", " + fontSize;
    }
}
