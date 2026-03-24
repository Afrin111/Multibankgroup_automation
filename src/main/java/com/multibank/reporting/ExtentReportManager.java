package com.multibank.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.multibank.utils.ConfigReader;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String path = ConfigReader.getReportPath() + "MB_IO_TestReport.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(path);
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("mb.io Automation Report");
            spark.config().setReportName("mb.io Regression Suite");
            spark.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Project",     "mb.io Crypto Platform");
            extent.setSystemInfo("URL",         "https://trade.mb.io");
            extent.setSystemInfo("Tester",      "QA Team");
            extent.setSystemInfo("Environment", "Production");
        }
        return extent;
    }
}