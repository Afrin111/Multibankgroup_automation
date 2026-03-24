package com.multibank.main;

import com.multibank.driver.DriverFactory;
import com.multibank.reporting.ExtentReportManager;
import com.multibank.utils.ConfigReader;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("   mb.io Automation Suite Starting...  ");
        System.out.println("========================================");
        System.out.println("Browser  : " + ConfigReader.getBrowser());
        System.out.println("Base URL : " + ConfigReader.getBaseUrl());
        System.out.println("========================================");

        try {
            // ── Step 1: Create Suite FIRST ────────────────────────
            XmlSuite xmlSuite = new XmlSuite();
            xmlSuite.setName("mb.io Full Automation Suite");

            // ── Step 2: Navigation Test ───────────────────────────
            XmlTest navigationTest = new XmlTest(xmlSuite);
            navigationTest.setName("Navigation Tests");
            List<XmlClass> navigationClasses = new ArrayList<>();
            navigationClasses.add(new XmlClass("com.multibank.tests.NavigationTest"));
            navigationTest.setXmlClasses(navigationClasses);

            // ── Step 3: Trading Test ──────────────────────────────
            XmlTest tradingTest = new XmlTest(xmlSuite);
            tradingTest.setName("Trading Tests");
            List<XmlClass> tradingClasses = new ArrayList<>();
            tradingClasses.add(new XmlClass("com.multibank.tests.TradingTest"));
            tradingTest.setXmlClasses(tradingClasses);

            // ── Step 4: Content Test ──────────────────────────────
            XmlTest contentTest = new XmlTest(xmlSuite);
            contentTest.setName("Content Tests");
            List<XmlClass> contentClasses = new ArrayList<>();
            contentClasses.add(new XmlClass("com.multibank.tests.ContentTest"));
            contentTest.setXmlClasses(contentClasses);

            // ── Step 5: Run via TestNG ────────────────────────────
            TestNG testng = new TestNG();
            testng.setXmlSuites(Collections.singletonList(xmlSuite));
            testng.run();

            System.out.println("========================================");
            System.out.println("   All Tests Completed!                ");
            System.out.println("   Report : " + ConfigReader.getReportPath()
                    + "MB_IO_TestReport.html");
            System.out.println("========================================");

        } catch (Exception e) {
            System.err.println("Test execution failed: " + e.getMessage());
            e.printStackTrace();

        } finally {
            DriverFactory.quitDriver();
            ExtentReportManager.getInstance().flush();
        }
    }
}
