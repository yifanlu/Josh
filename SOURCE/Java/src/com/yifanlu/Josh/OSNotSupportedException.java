package com.yifanlu.Josh;

/**
 * This exception is thrown if a method calls a native function in Windows that isn't supported by the current version.
 *
 * @author Yifan Lu
 * @version 1.3, 05/29/10
 * @since 1.0
 */
public class OSNotSupportedException extends RuntimeException {

    /**
     * OS Versions
     */
    public static final int WINDOWS_2000 = 0x0500, WINDOWS_XP = 0x0501, WINDOWS_VISTA = 0x0600;

    /**
     * Creates a new exception with the required OS version as a String (JNI likes this).
     *
     * @param requiredVersion The OS Version that the method requires.
     */
    public OSNotSupportedException(String requiredVersion) {
        super(requiredVersion);
    }

    /**
     * Creates a new exception with the required OS version.
     *
     * @param requiredVersion An int (from one of the constant values in this class) that defines what the minimum OS version required is.
     */
    public OSNotSupportedException(int requiredVersion) {
        super("Sorry, this method requires an OS version of " + intToOSString(requiredVersion) + " or higher to use.");
    }

    private static String intToOSString(int version) {
        switch (version) {
            case WINDOWS_2000:
                return "Windows 2000";
            case WINDOWS_XP:
                return "Windows XP";
            case WINDOWS_VISTA:
                return "Windows Vista";
            default:
                return "Unknown OS";
        }
    }
}
