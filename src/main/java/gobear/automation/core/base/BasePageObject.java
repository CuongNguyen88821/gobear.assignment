/*
 * Copyright (c) 2019.
 * @author: Cuong Nguyen
 */

package gobear.automation.core.base;

import gobear.automation.core.utils.Constants;
import gobear.automation.core.utils.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class BasePageObject {
    private WebDriver driver;
    private Actions actions;
    private Wait<WebDriver> wait;

    private final Map<By, Object> elementCache = new HashMap<>();

    public BasePageObject() {
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICITLY_WAIT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        actions = new Actions(driver);
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(Constants.EXPLICITLY_WAIT_TIMEOUT))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        clearElementCache();
    }

    public void navigate(String url) {
        driver.get(url);
        clearElementCache();
    }

    protected WebElement getElement(By by) {
        return (WebElement) elementCache.computeIfAbsent(by,
                (byKey -> driver.findElement(byKey)));
    }

    protected WebElement getElement(By by, int index) {
        return getElements(by).get(index);
    }

    @SuppressWarnings("unchecked")
    protected List<WebElement> getElements(By by) {
        return (List<WebElement>) elementCache.computeIfAbsent(by,
                (byKey -> driver.findElements(byKey)));
    }

    protected void click(By by) {
        waitElementEnabled(by);
        getElement(by).click();
    }

    protected void click(By by, int index) {
        getElement(by, index).click();
    }

    protected void actions_clickAndHold(By by) {
        actions.clickAndHold(getElement(by)).perform();
    }

    protected void actions_moveByOffset(int x, int y) {
        actions.moveByOffset(x, y).perform();
    }

    protected void actions_release() {
        actions.release().perform();
    }

    protected void setCheckBox(By by, boolean value) {
        if (checkElementSelected(by, !value))
            click(by);
    }

    protected void setCheckBoxByLabel(By checkBox, By label, boolean value) {
        if (checkElementSelected(checkBox, !value))
            click(label);
    }

    protected void setCheckBoxByLabel(By checkBox, int checkBoxIndex, By label, int labelIndex, boolean value) {
        if (checkElementSelected(checkBox, checkBoxIndex, !value))
            click(label, labelIndex);
    }

    protected String getElementAttribute(By by, String attributeName) {
        return getElement(by).getAttribute(attributeName);
    }

    protected String getElementAttribute(By by, int index, String attributeName) {
        return getElement(by, index).getAttribute(attributeName);
    }

    protected String getElementText(By by) {
        return getElement(by).getText();
    }

    protected String getElementText(By by, int index) {
        return getElement(by, index).getText();
    }

    protected Dimension getElementSize(By by) {
        return getElement(by).getSize();
    }

    public void pause(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected WebElement waitForElement(By by) {
        try {
            return wait.until(wd -> wd.findElement(by));
        } catch (TimeoutException e) {
            return null;
        }
    }

    protected boolean checkElementEnable(By by) {
        return getElement(by).isEnabled();
    }

    protected void waitElementEnabled(By by) {
        wait.until(wd -> {
            removeElementCache(by);
            return checkElementEnable(by) && getElement(by).isDisplayed();
        });
    }

    protected void waitElementsCountChange(int oldCount, By by) {
        wait.until(wd -> {
            removeElementCache(by);
            return countElements(by) != oldCount;
        });
    }

    protected void waitForElementTextContains(By by, String expected) {
        wait.until(wd -> {
            String elementText = getElementText(by);
            return elementText.contains(expected);
        });
    }

    protected void waitForElementTextNotContains(By by, String expected) {
        wait.until(wd -> {
            String elementText = getElementText(by);
            return !elementText.contains(expected);
        });
    }

    protected int countElements(By by) {
        return getElements(by).size();
    }

    protected boolean checkElementSelected(By by, boolean selected) {
        return getElement(by).isSelected() == selected;
    }

    protected boolean checkElementSelected(By by, int index, boolean selected) {
        return getElement(by, index).isSelected() == selected;
    }

    protected void clearElementCache() {
        elementCache.clear();
    }

    protected void removeElementCache(By by) {
        elementCache.remove(by);
    }
}