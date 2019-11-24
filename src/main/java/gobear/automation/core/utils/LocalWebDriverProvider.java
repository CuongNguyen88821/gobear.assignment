/*
 * Copyright (c) 2019.
 * @author: Cuong Nguyen
 */

package gobear.automation.core.utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

class LocalWebDriverProvider {

    private LocalWebDriverProvider() {
        throw new IllegalStateException("Utility class");
    }

    static ChromeDriver getChromeDriver() {
        if (SystemUtils.isMac())
            SystemUtils.setSystemProperty(Constants.CHROME_DRIVER_PATH_ENVIRONMENT_VARIABLE, "drivers/chromedriver");
        else if (SystemUtils.isWindows())
            SystemUtils.setSystemProperty(Constants.CHROME_DRIVER_PATH_ENVIRONMENT_VARIABLE, "drivers/chromedriver.exe");
        return new ChromeDriver();
    }

    static FirefoxDriver getFirefoxDriver() {
        if (SystemUtils.isMac())
            SystemUtils.setSystemProperty(Constants.GECKO_DRIVER_PATH_ENVIRONMENT_VARIABLE, "drivers/geckodriver");
        else if (SystemUtils.isWindows())
            SystemUtils.setSystemProperty(Constants.GECKO_DRIVER_PATH_ENVIRONMENT_VARIABLE, "drivers/geckodriver.exe");
        return new FirefoxDriver();
    }

    static SafariDriver getSafariDriver() {
        if (!SystemUtils.isMac())
            throw new IllegalStateException("Safari driver is only supported on MacOS");
        return new SafariDriver();
    }
}