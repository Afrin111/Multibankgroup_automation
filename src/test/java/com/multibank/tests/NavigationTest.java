package com.multibank.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.multibank.pages.HomePage;
import com.multibank.utils.ConfigReader;
import com.multibank.utils.DataProviderUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class NavigationTest extends BaseTest {

    private HomePage homePage;

    // ── Runs before every single test method ──────────────────────
    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        homePage = new HomePage(driver);
        driver.get(ConfigReader.getMarketingUrl()); // https://mb.io/en-AE
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }

    // ================================================================
    //  PAGE TITLE TEST
    // ================================================================

    @Test(priority = 1, description = "Verify page title is correct")
    public void testPageTitle() {
        String expectedTitle = DataProviderUtil
                .getNode("content.json", "homePage")
                .get("title").asText();
        String actualTitle = driver.getTitle();

        test.info("Expected : " + expectedTitle);
        test.info("Actual   : " + actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle,
                "Page title mismatch!");
    }

    // ================================================================
    //  HEADER BUTTON VISIBILITY TESTS
    // ================================================================

    @Test(priority = 2, description = "Verify Sign In button is visible in header")
    public void testSignInButtonDisplayed() {
        test.info("Checking Sign In button visibility...");
        Assert.assertTrue(homePage.isSignInButtonDisplayed(),
                "Sign In button is NOT visible in header!");
    }

    @Test(priority = 3, description = "Verify Sign Up button is visible in header")
    public void testSignUpButtonDisplayed() {
        test.info("Checking Sign Up button visibility...");
        Assert.assertTrue(homePage.isSignUpButtonDisplayed(),
                "Sign Up button is NOT visible in header!");
    }

    // ================================================================
    //  NAVIGATION TESTS
    // ================================================================

    @Test(priority = 4, description = "Verify Sign In navigates to trade.mb.io/login")
    public void testSignInNavigation() {
        test.info("Starting URL : " + driver.getCurrentUrl());

        // Scroll to top so header buttons are visible
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}

        // Click Sign In
        homePage.clickSignIn();
        test.info("Clicked Sign In button");

        // Wait for URL to contain 'login'
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("login"));

        String currentUrl = driver.getCurrentUrl();
        test.info("Landed on : " + currentUrl);

        // ✅ Matches: https://trade.mb.io/login?CookieConsent=...
        Assert.assertTrue(currentUrl.contains("login"),
                "Did not navigate to login page! URL: " + currentUrl);
    }

    @Test(priority = 5, description = "Verify Sign Up navigates to trade.mb.io/register")
    public void testSignUpNavigation() {
        test.info("Starting URL : " + driver.getCurrentUrl());

        // Scroll to top so header buttons are visible
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}

        // Click Sign Up
        homePage.clickSignUp();
        test.info("Clicked Sign Up button");

        // Wait for URL to contain 'register'
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("register"));

        String currentUrl = driver.getCurrentUrl();
        test.info("Landed on : " + currentUrl);

        // ✅ Matches: https://trade.mb.io/register?original_ref=mb
        Assert.assertTrue(currentUrl.contains("register"),
                "Did not navigate to register page! URL: " + currentUrl);
    }

    @Test(priority = 6, description = "Verify Explore nav link navigates correctly")
    public void testExploreNavigation() {
        test.info("Clicking Explore nav link...");

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        homePage.clickExplore();

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("/explore"));

        String currentUrl = driver.getCurrentUrl();
        test.info("Landed on : " + currentUrl);

        // ✅ Matches: https://mb.io/en-AE/explore
        Assert.assertTrue(currentUrl.contains("/explore"),
                "Did not navigate to Explore page! URL: " + currentUrl);
    }

    @Test(priority = 7, description = "Verify Features nav link navigates correctly")
    public void testFeaturesNavigation() {
        test.info("Clicking Features nav link...");

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        homePage.clickFeatures();

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("/features"));

        String currentUrl = driver.getCurrentUrl();
        test.info("Landed on : " + currentUrl);

        // ✅ Matches: https://mb.io/en-AE/features
        Assert.assertTrue(currentUrl.contains("/features"),
                "Did not navigate to Features page! URL: " + currentUrl);
    }

    @Test(priority = 8, description = "Verify Company nav link navigates correctly")
    public void testCompanyNavigation() {
        test.info("Clicking Company nav link...");

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

        homePage.clickCompany();

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("/company"));

        String currentUrl = driver.getCurrentUrl();
        test.info("Landed on : " + currentUrl);

        // ✅ Matches: https://mb.io/en-AE/company
        Assert.assertTrue(currentUrl.contains("/company"),
                "Did not navigate to Company page! URL: " + currentUrl);
    }

    // ================================================================
    //  NAV LINKS PRESENCE TESTS
    // ================================================================

    @Test(priority = 9, description = "Verify all main nav links are present in header")
    public void testMainNavLinksPresent() {
        JsonNode navItems = DataProviderUtil
                .getNode("navigation.json", "mainNavItems");

        List<String> actualLinks = homePage.getNavLinkTexts();
        test.info("Nav links found : " + actualLinks);

        for (JsonNode item : navItems) {
            String expected = item.get("name").asText();
            test.info("Checking nav link : " + expected);
            Assert.assertTrue(
                    actualLinks.stream().anyMatch(l -> l.contains(expected)),
                    "Nav link NOT found in header: " + expected);
        }
    }

    // ================================================================
    //  HERO SECTION TESTS
    // ================================================================

    @Test(priority = 10, description = "Verify hero heading: 'Crypto for everyone'")
    public void testHeroHeading() {
        String expected = DataProviderUtil
                .getNode("content.json", "homePage")
                .get("heroHeading").asText();
        String actual = homePage.getHeroHeading();

        test.info("Expected : " + expected);
        test.info("Actual   : " + actual);

        Assert.assertEquals(actual, expected, "Hero heading mismatch!");
    }

    @Test(priority = 11, description = "Verify hero subtext contains 'Simple, secure and speedy'")
    public void testHeroSubText() {
        String actual = homePage.getHeroSubText();
        test.info("Hero subtext : " + actual);
        Assert.assertTrue(actual.contains("Simple, secure and speedy"),
                "Hero subtext mismatch! Actual: " + actual);
    }

    @Test(priority = 12, description = "Verify 'Download the app' button is displayed")
    public void testDownloadAppButton() {
        Assert.assertTrue(homePage.isDownloadAppButtonDisplayed(),
                "'Download the app' button NOT visible!");
    }

    @Test(priority = 13, description = "Verify 'Open an account' button is displayed")
    public void testOpenAccountButton() {
        Assert.assertTrue(homePage.isOpenAccountButtonDisplayed(),
                "'Open an account' button NOT visible!");
    }

    // ================================================================
    //  MARKET SECTIONS TESTS
    // ================================================================

    @Test(priority = 14, description = "Verify Top Gainers section is displayed")
    public void testTopGainersSectionDisplayed() {
        Assert.assertTrue(homePage.isTopGainersSectionDisplayed(),
                "Top Gainers section NOT visible!");
    }

    @Test(priority = 15, description = "Verify Trending Now section is displayed")
    public void testTrendingNowSectionDisplayed() {
        Assert.assertTrue(homePage.isTrendingNowSectionDisplayed(),
                "Trending Now section NOT visible!");
    }

    @Test(priority = 16, description = "Verify Top Losers section is displayed")
    public void testTopLosersSectionDisplayed() {
        Assert.assertTrue(homePage.isTopLosersSectionDisplayed(),
                "Top Losers section NOT visible!");
    }

    // ================================================================
    //  FOOTER TESTS
    // ================================================================

    @Test(priority = 17, description = "Verify footer copyright text is displayed")
    public void testFooterCopyrightDisplayed() {
        test.info("Scrolling to footer...");
        Assert.assertTrue(homePage.isFooterCopyrightDisplayed(),
                "Footer copyright NOT visible!");
    }

    @Test(priority = 18, description = "Verify all footer legal links are present")
    public void testFooterLinksPresent() {
        JsonNode footerLinks = DataProviderUtil
                .getNode("navigation.json", "footerLinks");

        List<String> actualLinks = homePage.getFooterLinkTexts();
        test.info("Footer links found : " + actualLinks);

        for (JsonNode link : footerLinks) {
            String expected = link.get("name").asText();
            test.info("Checking footer link : " + expected);
            Assert.assertTrue(
                    actualLinks.stream().anyMatch(l -> l.contains(expected)),
                    "Footer link NOT found: " + expected);
        }
    }
}