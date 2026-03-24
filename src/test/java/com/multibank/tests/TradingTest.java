package com.multibank.tests;

import com.multibank.pages.TradingPage;
import com.multibank.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TradingTest extends BaseTest {

    private TradingPage tradingPage;

    @BeforeMethod(alwaysRun = true)
    public void initPage() {
        tradingPage = new TradingPage(driver);
        tradingPage.open(ConfigReader.getMarketingUrl());
    }

    @Test(description = "Verify Explore page heading is 'Markets at your fingertips'")
    public void testExplorePageHeading() {
        String heading = tradingPage.getPageHeading();
        test.info("Actual heading: " + heading);
        Assert.assertEquals(heading, "Markets at your fingertips",
                "Explore page heading mismatch!");
    }

    @Test(description = "Verify Spot Market section is displayed")
    public void testSpotMarketSectionDisplayed() {
        Assert.assertTrue(tradingPage.isSpotMarketDisplayed(),
                "Spot Market section not visible!");
    }

    @Test(description = "Verify Market Sentiment section is displayed")
    public void testMarketSentimentDisplayed() {
        Assert.assertTrue(tradingPage.isSentimentDisplayed(),
                "Market Sentiment not visible!");
    }

    @Test(description = "Verify Top Gainers section is displayed")
    public void testTopGainersSectionDisplayed() {
        Assert.assertTrue(tradingPage.isTopGainersDisplayed(),
                "Top Gainers section not visible!");
    }

    @Test(description = "Verify Trending Now section is displayed")
    public void testTrendingNowSectionDisplayed() {
        Assert.assertTrue(tradingPage.isTrendingNowDisplayed(),
                "Trending Now section not visible!");
    }

    @Test(description = "Verify Top Losers section is displayed")
    public void testTopLosersSectionDisplayed() {
        Assert.assertTrue(tradingPage.isTopLosersDisplayed(),
                "Top Losers section not visible!");
    }

    @Test(description = "Verify Earn promo (Up to 35% APY) is displayed")
    public void testEarnPromoDisplayed() {
        Assert.assertTrue(tradingPage.isEarnPromoDisplayed(),
                "Earn promo not visible!");
    }

    @Test(description = "Verify Instant Buy promo is displayed")
    public void testInstantBuyPromoDisplayed() {
        Assert.assertTrue(tradingPage.isInstantBuyDisplayed(),
                "Instant Buy promo not visible!");
    }

    @Test(description = "Verify Deposit promo is displayed")
    public void testDepositPromoDisplayed() {
        Assert.assertTrue(tradingPage.isDepositPromoDisplayed(),
                "Deposit promo not visible!");
    }

    @Test(description = "Verify Download the app link is displayed")
    public void testDownloadAppLinkDisplayed() {
        Assert.assertTrue(tradingPage.isDownloadAppLinkDisplayed(),
                "Download app link not visible!");
    }
}