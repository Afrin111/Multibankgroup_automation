package com.multibank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

public class AboutPage extends BasePage {

    private By pageHeading    = By.xpath("//h1[normalize-space()='The power of crypto is yours']");
    private By subHeading     = By.xpath("//*[contains(text(),'highly regulated platform with segregated funds')]");
    private By featureSections = By.cssSelector("h2");
    private By solutionCards  = By.xpath("//h2[normalize-space()='Solutions with advantages']/following-sibling::*//p[1]");

    // Individual feature sections
    private By exploreOpportunities = By.xpath("//h2[normalize-space()='Easily explore opportunities']");
    private By assetInsights        = By.xpath("//h2[normalize-space()='Smart asset insights at a glance']");
    private By buySellConvert       = By.xpath("//h2[normalize-space()='Buy, sell, convert in three taps']");
    private By earnYield            = By.xpath("//h2[normalize-space()='Earn yield that feels rewarding']");
    private By walletPortfolio      = By.xpath("//h2[normalize-space()='Clearly presented wallet portfolio']");
    private By mbgBenefits          = By.xpath("//h2[normalize-space()='$MBG unlocks mb.io benefits']");
    private By vipBenefits          = By.xpath("//h2[normalize-space()='VIP Experience benefits that scale with you']");

    // Solution cards
    private By fiatRamps    = By.xpath("//*[contains(text(),'Fiat on/off ramps')]");
    private By regulated    = By.xpath("//*[contains(text(),'Heavily regulated')]");
    private By otcExecution = By.xpath("//*[contains(text(),'Seamless OTC execution')]");

    public AboutPage(WebDriver driver) { super(driver); }

    public void open(String marketingUrl) {
        driver.get(marketingUrl + "/features");  // ✅ no change needed
    }
    public String getPageHeading()  { return getText(pageHeading); }
    public boolean isSubHeadingDisplayed() { return isDisplayed(subHeading); }

    public boolean isExploreOpportunitiesDisplayed() { return isDisplayed(exploreOpportunities); }
    public boolean isAssetInsightsDisplayed()        { return isDisplayed(assetInsights); }
    public boolean isBuySellConvertDisplayed()       { return isDisplayed(buySellConvert); }
    public boolean isEarnYieldDisplayed()            { return isDisplayed(earnYield); }
    public boolean isWalletPortfolioDisplayed()      { return isDisplayed(walletPortfolio); }
    public boolean isMBGBenefitsDisplayed()          { return isDisplayed(mbgBenefits); }
    public boolean isVIPBenefitsDisplayed()          { return isDisplayed(vipBenefits); }

    public boolean isFiatRampsDisplayed()    { scrollToBottom(); return isDisplayed(fiatRamps); }
    public boolean isHeavilyRegulatedDisplayed() { return isDisplayed(regulated); }
    public boolean isOTCExecutionDisplayed() { return isDisplayed(otcExecution); }

    public List<String> getAllFeatureSectionTitles() {
        return getElements(featureSections).stream()
                .map(WebElement::getText)
                .filter(t -> !t.isBlank())
                .collect(Collectors.toList());
    }
}