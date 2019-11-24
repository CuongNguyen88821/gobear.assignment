/*
 * Copyright (c) 2019.
 * @author: Cuong Nguyen
 */

package gobear.automation.core.utils;

import java.io.IOException;

public class TestSettings {
    public final static String browser;

    static {
        PropertiesFile globalSettings;
        try {
            globalSettings = new PropertiesFile(Constants.DEFAULT_GLOBAL_SETTINGS_FILE);
        } catch (IOException e) {
            e.printStackTrace();
            globalSettings = new PropertiesFile();
        }
        String overwrittenBrowser = SystemUtils.getSystemProperty(Constants.BROWSER_KEYWORD);
        if (overwrittenBrowser != null)
            browser = overwrittenBrowser;
        else
            browser = globalSettings.getProperty(Constants.BROWSER_KEYWORD) != null
                    ? globalSettings.getProperty(Constants.BROWSER_KEYWORD)
                    : Constants.DEFAULT_BROWSER;
    }

}
