package com.multibank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

public class TradingPage extends BasePage {

    private By pageHeading       = By.xpath("//h1[normalize-space()='Markets at your fingertips']");
    private By spotMarketSection = By.xpath("//h2[normalize-space()='Spot market']");
    private By sentimentLabel    = By.xpath("//h3[normalize-space()='Market sentiment']");
    private By topGainers        = By.xpath("//h2[normalize-space()='Top Gainers']");
    private By trendingNow       = By.xpath("//h2[normalize-space()='Trending Now']");
    private By topLosers         = By.xpath("//h2[normalize-space()='Top Losers']");
    private By earnPromo         = By.xpath("//*[contains(text(),'Up to 35% APY')]");
    private By instantBuyPromo   = By.xpath("//*[contains(text(),'Instant buy')]");
    private By depositPromo      = By.xpath("//*[contains(text(),'Top up today')]");
    private By downloadAppLink   = By.linkText("Download the app");

    public TradingPage(WebDriver driver) { super(driver); }

    public void open(String marketingUrl) {
        driver.get(marketingUrl + "/explore");  // ✅ no change needed
    }
    public String getPageHeading()        { return getText(pageHeading); }
    public boolean isSpotMarketDisplayed(){ return isDisplayed(spotMarketSection); }
    public boolean isSentimentDisplayed() { return isDisplayed(sentimentLabel); }
    public boolean isTopGainersDisplayed(){ return isDisplayed(topGainers); }
    public boolean isTrendingNowDisplayed(){ return isDisplayed(trendingNow); }
    public boolean isTopLosersDisplayed() { return isDisplayed(topLosers); }
    public boolean isEarnPromoDisplayed() { return isDisplayed(earnPromo); }
    public boolean isInstantBuyDisplayed(){ return isDisplayed(instantBuyPromo); }
    public boolean isDepositPromoDisplayed(){ return isDisplayed(depositPromo); }
    public boolean isDownloadAppLinkDisplayed(){ return isDisplayed(downloadAppLink); }
}