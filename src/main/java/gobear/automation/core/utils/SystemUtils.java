/*
 * Copyright (c) 2019.
 * @author: Cuong Nguyen
 */

package gobear.automation.core.utils;

public class SystemUtils {
    private static final String OS = getSystemProperty("os.name").toLowerCase();

    private SystemUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getSystemProperty(String propertyName) {
        return System.getProperty(propertyName);
    }

    public static void setSystemProperty(String propertyName, String value) {
        System.setProperty(propertyName, value);
    }

    public static boolean isWindows() {
        return (OS.contains("win"));
    }

    public static boolean isMac() {
        return (OS.contains("mac"));
    }
}
