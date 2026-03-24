package com.multibank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage {

    // ── Header nav ──────────────────────────────────────────────
    private By navExplore    = By.linkText("Explore");
    private By navFeatures   = By.linkText("Features");
    private By navCompany    = By.linkText("Company");
    private By navMBG        = By.linkText("$MBG\uD83D\uDD25");   // "$MBG🔥"
    private By btnSignIn     = By.linkText("Sign in");
    private By btnSignUp     = By.linkText("Sign up");

    // ── Hero section ─────────────────────────────────────────────
    private By heroHeading   = By.xpath("//h3[normalize-space()='Crypto for everyone']");
    private By heroSubText   = By.xpath("//*[contains(text(),'Simple, secure and speedy')]");
    private By btnDownloadApp   = By.linkText("Download the app");
    private By btnOpenAccount   = By.linkText("Open an account");

    // ── Market sections ──────────────────────────────────────────
    private By topGainersSection  = By.xpath("//h2[normalize-space()='Top Gainers']");
    private By trendingNowSection = By.xpath("//h2[normalize-space()='Trending Now']");
    private By topLosersSection   = By.xpath("//h2[normalize-space()='Top Losers']");

    // ── Footer ───────────────────────────────────────────────────
    private By footerLegal        = By.cssSelector("footer a");
    private By footerCopyright    = By.xpath("//*[contains(text(),'2026 Copyright. All Rights Reserved. mb.io')]");

    public HomePage(WebDriver driver) { super(driver); }

    public void open(String url) { driver.get(url); }

    // Header
    public boolean isSignInButtonDisplayed()  { return isDisplayed(btnSignIn); }
    public boolean isSignUpButtonDisplayed()  { return isDisplayed(btnSignUp); }
    public void clickSignIn()  { click(btnSignIn); }
    public void clickSignUp()  { click(btnSignUp); }
    public void clickExplore() { click(navExplore); }
    public void clickFeatures(){ click(navFeatures); }
    public void clickCompany() { click(navCompany); }

    // Hero
    public String getHeroHeading()  { return getText(heroHeading); }
    public String getHeroSubText()  { return getText(heroSubText); }
    public boolean isDownloadAppButtonDisplayed() { return isDisplayed(btnDownloadApp); }
    public boolean isOpenAccountButtonDisplayed() { return isDisplayed(btnOpenAccount); }

    // Market sections
    public boolean isTopGainersSectionDisplayed()  { return isDisplayed(topGainersSection); }
    public boolean isTrendingNowSectionDisplayed() { return isDisplayed(trendingNowSection); }
    public boolean isTopLosersSectionDisplayed()   { return isDisplayed(topLosersSection); }

    // Nav link texts
    public List<String> getNavLinkTexts() {
        return getElements(By.cssSelector("header nav a, header a")).stream()
                .map(WebElement::getText)
                .filter(t -> !t.isBlank())
                .collect(Collectors.toList());
    }

    // Footer
    public boolean isFooterCopyrightDisplayed() {
        scrollToBottom();
        return isDisplayed(footerCopyright);
    }

    public List<String> getFooterLinkTexts() {
        scrollToBottom();
        return getElements(footerLegal).stream()
                .map(WebElement::getText)
                .filter(t -> !t.isBlank())
                .collect(Collectors.toList());
    }
}