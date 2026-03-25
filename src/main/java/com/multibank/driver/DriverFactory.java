package com.multibank.driver;

import com.multibank.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.time.Duration;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    // ── Called with browser parameter from TestNG ──────────
    public static WebDriver initDriver(String browser) {
        WebDriver driver;

        switch (browser.toLowerCase()) {

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--disable-notifications");
                driver = new EdgeDriver(edgeOptions);
                break;

            case "headless":
                // ✅ Used by GitHub Actions CI/CD
                WebDriverManager.chromedriver().setup();
                ChromeOptions headlessOptions = new ChromeOptions();
                headlessOptions.addArguments("--headless");
                headlessOptions.addArguments("--no-sandbox");
                headlessOptions.addArguments("--disable-dev-shm-usage");
                headlessOptions.addArguments("--disable-gpu");
                headlessOptions.addArguments("--window-size=1920,1080");
                headlessOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(headlessOptions);
                break;

            default:
                // chrome
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                driver = new ChromeDriver(chromeOptions);
        }

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));
        driverThread.set(driver);
        return driver;
    }

    // ── Called without parameter (uses config.properties) ──
    public static WebDriver initDriver() {
        return initDriver(ConfigReader.getBrowser());
    }

    public static WebDriver getDriver()  { return driverThread.get(); }

    public static void quitDriver() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
        }
    }
}
