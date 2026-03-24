package com.multibank.pages;

import com.multibank.utils.ScreenshotUtil;
import com.multibank.utils.WaitUtil;
import org.openqa.selenium.*;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WaitUtil  waitUtil;

    public BasePage(WebDriver driver) {
        this.driver   = driver;
        this.waitUtil = new WaitUtil(driver);
    }

    public void click(By locator) {
        waitUtil.waitForClickable(locator).click();
    }

    public String getText(By locator) {
        return waitUtil.waitForVisible(locator).getText();
    }

    public boolean isDisplayed(By locator) {
        try { return driver.findElement(locator).isDisplayed(); }
        catch (NoSuchElementException e) { return false; }
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToElement(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
    }

    public String getPageTitle()   { return driver.getTitle(); }
    public String getCurrentUrl()  { return driver.getCurrentUrl(); }

    public String captureScreenshot(String testName) {
        return ScreenshotUtil.capture(driver, testName);
    }
}