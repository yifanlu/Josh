package com.yifanlu.Josh;

/**
 * Stores the attribute of a character.
 * The attribute is composed of the text color (foreground color), 
 * the highlighting (background color), and it's byte changes.
 * If you are just going to modify color attributes, we suggest using 
 * the superclass {@link ConsoleColor}.
 *
 * @see JoshOutBuffer
 * @author Yifan Lu
 * @version 1.3, 05/29/10
 * @since 1.0
 */
public class ConsoleAttribute {

    protected int attributes;
    /**
     * Character byte change.
     *
     * @see <a href="http://msdn.microsoft.com/en-us/library/ms682088(v=VS.85).aspx#_win32_character_attributes">Windows console attributes constants</a>
     */
    public static final int COMMON_LVB_LEADING_BYTE = 0x0100, COMMON_LVB_TRAILING_BYTE = 0x0200, COMMON_LVB_GRID_HORIZONTAL = 0x0400, COMMON_LVB_GRID_LVERTICAL = 0x0800, COMMON_LVB_GRID_RVERTICAL = 0x1000, COMMON_LVB_REVERSE_VIDEO = 0x4000, COMMON_LVB_UNDERSCORE = 0x8000;

    /**
     * Create a new object with no attributes.
     */
    public ConsoleAttribute() {
        this.attributes = 0x0000;
    }

    /**
     * Create a new attribute object with an int.
     * An attribute is an hex int value. You can mix and match
     * different attributes with the OR ( | ) operator. For example the attribute
     * {@code ConsoleAttribute.COMMON_LVB_LEADING_BYTE | ConsoleAttribute.COMMON_LVB_TRAILING_BYTE |
     * ConsoleAttribute.COMMON_LVB_GRID_LVERTICAL} is valid for the int attribute
     * parameter.
     *
     * @param attributes The attribute(s) of the character which can be mixed with the OR operator.
     */
    public ConsoleAttribute(int attributes) {
        this.attributes = attributes;
    }

    /**
     * Mixes another attribute
     * Use the constants like {@link ConsoleAttribute#COMMON_LVB_LEADING_BYTE} for the attribute and this
     * will use the OR operator to add the attribute to the object.
     * This is equivalent to {@code setAttributes(getAttributes() | attribute) }.
     *
     * @param attribute The attribute to mix.
     */
    public void addAttributes(int attribute) {
        this.attributes |= attribute;
    }

    /**
     * Gets the attributes stored in the object.
     *
     * @return The attributes stored.
     */
    public int getAttributes() {
        return this.attributes;
    }

    /**
     * Sets the attributes
     *
     * @param attributes The attribute(s) of the character which can be mixed with the OR operator.
     */
    public void setAttributes(int attributes) {
        this.attributes = attributes;
    }

    /**
     * Compares two Objects, if both are ConsoleAttribute and have the same attributes, they are equal.
     *
     * @param anObject Another ConsoleAttribute object.
     * @return true if both attributes are equal.
     */
    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof ConsoleAttribute) {
            ConsoleAttribute otherAttr = (ConsoleAttribute) anObject;
            return this.getAttributes() == otherAttr.getAttributes();
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
        int hash = 5;
        hash = 53 * hash + this.attributes;
        return hash;
    }

    /**
     * A {@link java.lang.String} representation of this object.
     *
     * @return The attributes as a number.
     */
    @Override
    public String toString() {
        return Integer.toString(this.attributes);
    }
}
