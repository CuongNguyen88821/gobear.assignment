/*
 * Copyright (c) 2019.
 * @author: Cuong Nguyen
 */

package gobear.automation.core.utils;

public final class Constants {

    public static final String DEFAULT_GLOBAL_SETTINGS_FILE = "configs/globalSettings.properties";

    public static final String CHROME_DRIVER_PATH_ENVIRONMENT_VARIABLE = "webdriver.chrome.driver";
    public static final String GECKO_DRIVER_PATH_ENVIRONMENT_VARIABLE = "webdriver.gecko.driver";

    public static final String BROWSER_KEYWORD = "browser";

    public static final String CHROME_KEYWORD = "chrome";
    public static final String FIREFOX_KEYWORD = "firefox";
    public static final String SAFARI_KEYWORD = "safari";

    public static final String DEFAULT_BROWSER = CHROME_KEYWORD;

    public static final int EXPLICITLY_WAIT_TIMEOUT = 10;
    public static final int IMPLICITLY_WAIT_TIMEOUT = 10;
    public static final int PAGE_LOAD_TIMEOUT = 10;

}
