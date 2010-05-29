package com.yifanlu.Josh;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Used to load the JoshCore library.
 * Internal use only, you don't need this.
 *
 * @author Yifan Lu
 * @version 1.3, 05/29/10
 * @since 1.3
 */
public class LibraryLoader {

    /**
     * Loads JoshCore.dll to memory.
     * There are three possible locations for the JoshCore.dll library. First, it looks in Java's {@code java.library.path},
     * if that fails, it will look in the system temporary folder, and if that fails, it will extract the correct library
     * from the JAR file.
     *
     * @throws OSNotSupportedException If the OS is not Windows or Java is not 32-bit.
     */
    public static void loadJosh() throws OSNotSupportedException {
        try {
            System.loadLibrary("JoshCore"); // First attempt to load from library path
        } catch (UnsatisfiedLinkError ex1) {
            try {
                System.load(System.getProperty("java.io.tmpdir") + "/JoshCore.dll"); // Next, try loading from temp folder
            } catch (UnsatisfiedLinkError ex2) {
                try {
                    System.load(extractLibrary()); // Last attempt, extract library to temp location and load it
                } catch (Exception ex3) {
                    ex3.printStackTrace(); // Fail
                    System.exit(1); // Josh was not loaded propertly, the program will end
                }
            }
        }
    }

    /**
     * Finds the correct version of JoshCore and extract it.
     *
     * @return The file name of the extracted library.
     * @throws OSNotSupportedException If the OS is not Windows or Java is not 32-bit.
     * @throws FileNotFoundException If the DLL cannot be found.
     */
    private static String extractLibrary() throws OSNotSupportedException, FileNotFoundException {
        String os = System.getProperty("os.name");
        String loadName;

        if (os.indexOf("Windows") == -1) {
            throw new OSNotSupportedException("Sorry, Josh is only supported on Windows systems.");
        }

        if (os.indexOf("2000") > -1) {
            loadName = "JoshCore_2000";
        } else if (os.indexOf("XP") > -1) {
            loadName = "JoshCore_XP";
        } else {
            loadName = "JoshCore_Vista";
        }

        return getLibrary(loadName);
    }

    /**
     * Extracts the correct JoshCore.dll from the JAR file and copies it to the system temporary location.
     *
     * @param name The name of the library to extract.
     * @return The file name of the extracted library.
     * @throws FileNotFoundException If the DLL cannot be found.
     */
    private static String getLibrary(String name) throws FileNotFoundException {
        InputStream in = null;
        OutputStream out = null;
        File fileOut = null;
        try {
            in = LibraryLoader.class.getResourceAsStream("/lib/" + name + ".dll");
            if (in == null) {
                throw new FileNotFoundException("Cannot find: " + name);
            }
            fileOut = new File(System.getProperty("java.io.tmpdir") + "/JoshCore.dll");
            out = new FileOutputStream(fileOut);
            copy(in, out);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return fileOut.toString();
    }

    /**
     * Performs a buffered copy operation between two streams.
     *
     * @param from The stream to copy from.
     * @param to The stream to copy to.
     * @throws IOException If the input cannot be read or the output cannot be written.
     */
    private static void copy(InputStream from, OutputStream to) throws IOException {
        InputStream in = from;
        OutputStream out = to;
        try {
            int length = in.available(); // danger!
            byte[] bytes = new byte[length];
            in.read(bytes);
            out.write(bytes);
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
