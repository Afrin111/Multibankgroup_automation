package com.multibank.driver;

import com.multibank.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver initDriver() {
        String browser = ConfigReader.getBrowser().toLowerCase();
        WebDriver driver;

        switch (browser) {

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
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
                headlessOptions.addArguments("--disable-notifications");
                headlessOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(headlessOptions);
                break;

            default:
                // chrome
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--disable-notifications");
                driver = new ChromeDriver(options);
        }

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));
        driver.manage().window().maximize();
        driverThread.set(driver);
        return driver;
    }

    public static WebDriver getDriver()  { return driverThread.get(); }

    public static void quitDriver() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
        }
    }
}
