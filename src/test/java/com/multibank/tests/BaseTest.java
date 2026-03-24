package com.multibank.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.multibank.driver.DriverFactory;
import com.multibank.reporting.ExtentReportManager;
import com.multibank.utils.ConfigReader;
import com.multibank.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.lang.reflect.Method;

public class BaseTest {

    protected WebDriver    driver;
    protected ExtentReports extent;
    protected ExtentTest   test;

    @BeforeSuite
    public void setUpSuite() {
        extent = ExtentReportManager.getInstance();
    }

    @BeforeMethod
    public void setUp(Method method) {
        driver = DriverFactory.initDriver();
        test   = extent.createTest(method.getName());
        driver.get(ConfigReader.getMarketingUrl());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String path = ScreenshotUtil.capture(driver, result.getName());
            test.fail(result.getThrowable());
            test.addScreenCaptureFromPath(path);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else {
            test.skip("Test skipped");
        }
        DriverFactory.quitDriver();
    }

    @AfterSuite
    public void tearDownSuite() {
        if (extent != null) extent.flush();
    }
}