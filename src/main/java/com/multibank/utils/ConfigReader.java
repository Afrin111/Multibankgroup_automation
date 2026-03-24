package com.multibank.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_PATH = "src/test/resources/config.properties";

    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_PATH);
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) throw new RuntimeException("Key not found: " + key);
        return value.trim();
    }

    public static String getBrowser()          { return get("browser"); }
    public static String getBaseUrl()          { return get("baseUrl"); }
    public static String getMarketingUrl()     { return get("marketingUrl"); }
    public static String getLoginUrl()         { return get("loginUrl"); }
    public static String getRegisterUrl()      { return get("registerUrl"); }
    public static int    getImplicitWait()     { return Integer.parseInt(get("implicitWait")); }
    public static int    getExplicitWait()     { return Integer.parseInt(get("explicitWait")); }
    public static int    getPageLoadTimeout()  { return Integer.parseInt(get("pageLoadTimeout")); }
    public static String getScreenshotPath()   { return get("screenshotPath"); }
    public static String getReportPath()       { return get("reportPath"); }
}