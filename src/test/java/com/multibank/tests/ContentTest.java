package com.multibank.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.multibank.pages.AboutPage;
import com.multibank.pages.HomePage;
import com.multibank.utils.ConfigReader;
import com.multibank.utils.DataProviderUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContentTest extends BaseTest {

    private HomePage  homePage;
    private AboutPage featuresPage;

    @BeforeMethod(alwaysRun = true)
    public void initPages() {
        homePage     = new HomePage(driver);
        featuresPage = new AboutPage(driver);
    }

    // ── Home page content ─────────────────────────────────────────

    @Test(description = "Verify hero heading: 'Crypto for everyone'")
    public void testHeroHeading() {
        String expected = DataProviderUtil.getNode("content.json", "homePage")
                .get("heroHeading").asText();
        Assert.assertEquals(homePage.getHeroHeading(), expected,
                "Hero heading mismatch!");
    }

    @Test(description = "Verify hero subtext contains 'Simple, secure and speedy'")
    public void testHeroSubText() {
        String actual = homePage.getHeroSubText();
        test.info("Hero subtext: " + actual);
        Assert.assertTrue(actual.contains("Simple, secure and speedy"),
                "Hero subtext mismatch!");
    }

    @Test(description = "Verify 'Download the app' CTA button is displayed")
    public void testDownloadAppButtonDisplayed() {
        Assert.assertTrue(homePage.isDownloadAppButtonDisplayed(),
                "'Download the app' button not visible!");
    }

    @Test(description = "Verify 'Open an account' CTA button is displayed")
    public void testOpenAccountButtonDisplayed() {
        Assert.assertTrue(homePage.isOpenAccountButtonDisplayed(),
                "'Open an account' button not visible!");
    }

    @Test(description = "Verify Top Gainers market section is on home page")
    public void testHomeTopGainersDisplayed() {
        Assert.assertTrue(homePage.isTopGainersSectionDisplayed(),
                "Top Gainers section not on home page!");
    }

    @Test(description = "Verify Trending Now market section is on home page")
    public void testHomeTrendingNowDisplayed() {
        Assert.assertTrue(homePage.isTrendingNowSectionDisplayed(),
                "Trending Now section not on home page!");
    }

    @Test(description = "Verify Top Losers market section is on home page")
    public void testHomeTopLosersDisplayed() {
        Assert.assertTrue(homePage.isTopLosersSectionDisplayed(),
                "Top Losers section not on home page!");
    }

    // ── Features page content ─────────────────────────────────────

    @Test(description = "Verify Features page heading: 'The power of crypto is yours'")
    public void testFeaturesPageHeading() {
        featuresPage.open(ConfigReader.getMarketingUrl());
        String expected = DataProviderUtil.getNode("content.json", "featuresPage")
                .get("heading").asText();
        Assert.assertEquals(featuresPage.getPageHeading(), expected,
                "Features page heading mismatch!");
    }

    @Test(description = "Verify 'Easily explore opportunities' section is displayed")
    public void testExploreOpportunitiesSectionDisplayed() {
        featuresPage.open(ConfigReader.getMarketingUrl());
        Assert.assertTrue(featuresPage.isExploreOpportunitiesDisplayed(),
                "'Easily explore opportunities' section not visible!");
    }

    @Test(description = "Verify 'Buy, sell, convert in three taps' section is displayed")
    public void testBuySellConvertSectionDisplayed() {
        featuresPage.open(ConfigReader.getMarketingUrl());
        Assert.assertTrue(featuresPage.isBuySellConvertDisplayed(),
                "'Buy, sell, convert in three taps' section not visible!");
    }

    @Test(description = "Verify 'Earn yield that feels rewarding' section is displayed")
    public void testEarnYieldSectionDisplayed() {
        featuresPage.open(ConfigReader.getMarketingUrl());
        Assert.assertTrue(featuresPage.isEarnYieldDisplayed(),
                "'Earn yield' section not visible!");
    }

    @Test(description = "Verify '$MBG unlocks mb.io benefits' section is displayed")
    public void testMBGBenefitsSectionDisplayed() {
        featuresPage.open(ConfigReader.getMarketingUrl());
        Assert.assertTrue(featuresPage.isMBGBenefitsDisplayed(),
                "'$MBG unlocks mb.io benefits' section not visible!");
    }

    @Test(description = "Verify 'Fiat on/off ramps' solution card is displayed")
    public void testFiatRampsCardDisplayed() {
        featuresPage.open(ConfigReader.getMarketingUrl());
        Assert.assertTrue(featuresPage.isFiatRampsDisplayed(),
                "'Fiat on/off ramps' card not visible!");
    }

    @Test(description = "Verify 'Heavily regulated' solution card is displayed")
    public void testHeavilyRegulatedCardDisplayed() {
        featuresPage.open(ConfigReader.getMarketingUrl());
        Assert.assertTrue(featuresPage.isHeavilyRegulatedDisplayed(),
                "'Heavily regulated' card not visible!");
    }

    @Test(description = "Verify 'Seamless OTC execution' solution card is displayed")
    public void testOTCExecutionCardDisplayed() {
        featuresPage.open(ConfigReader.getMarketingUrl());
        Assert.assertTrue(featuresPage.isOTCExecutionDisplayed(),
                "'Seamless OTC execution' card not visible!");
    }
}