/*
 * Copyright (c) 2019.
 * @author: Cuong Nguyen
 */

package gobear.automation.core.utils;

import org.openqa.selenium.WebDriver;

/**
 * The type Web driver factory.
 */
public class WebDriverFactory {
    private static WebDriver driver;
    private static final String UNSUPPORTED_BROWSER_MESSAGE = "Unsupported browser: %s. Supported browsers: Chrome, Firefox, Safari (MacOS only).";

    private WebDriverFactory() {
        throw new IllegalStateException("Utility class");
    }

    public static WebDriver getDriver() {
        if (driver != null) return driver;
        switch (TestSettings.browser.toLowerCase()) {
            case (Constants.CHROME_KEYWORD):
                driver = LocalWebDriverProvider.getChromeDriver();
                return driver;
            case (Constants.FIREFOX_KEYWORD):
                driver = LocalWebDriverProvider.getFirefoxDriver();
                return driver;
            case (Constants.SAFARI_KEYWORD):
                driver = LocalWebDriverProvider.getSafariDriver();
                return driver;
            default:
                throw new IllegalStateException(String.format(UNSUPPORTED_BROWSER_MESSAGE, TestSettings.browser));
        }
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
