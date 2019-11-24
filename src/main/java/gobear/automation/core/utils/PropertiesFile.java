/*
 * Copyright (c) 2019.
 * @author: Cuong Nguyen
 */

package gobear.automation.core.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type Properties Utils.
 */
public class PropertiesFile {

    private Properties properties;

    public PropertiesFile() {
        properties = new Properties();
    }

    /**
     * Instantiates a new Properties helper.
     *
     * @param path the path
     */
    public PropertiesFile(String path) throws IOException {
        properties = readProperties(path);
    }

    /**
     * Instantiates a new Properties helper.
     *
     * @param inputStream the input stream
     * @throws IOException the io exception
     */
    public PropertiesFile(InputStream inputStream) throws IOException {
        properties = readProperties(inputStream);
    }

    private Properties readProperties(String path) throws IOException {
        return readProperties(new FileInputStream(path));
    }

    private Properties readProperties(InputStream stream) throws IOException {
        Properties properties = new Properties();
        properties.load(stream);
        return properties;
    }

    /**
     * Get property string.
     *
     * @param key the key
     * @return the string
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get property as int int.
     *
     * @param key the key
     * @return the int
     */
    public int getPropertyAsInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    /**
     * Add property.
     *
     * @param key   the key
     * @param value the value
     */
    public void addProperty(Object key, Object value) {
        properties.put(key, value);
    }

    /**
     * Gets properties.
     *
     * @return the properties
     */
    public Properties getProperties() {
        return properties;
    }
}

