# Multibankgroup_automation

A Selenium TestNG automation framework for testing the [mb.io](https://mb.io/en-AE) crypto trading platform built with Java, Maven, and ExtentReports.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Setup & Installation](#setup--installation)
- [Configuration](#configuration)
- [Test Data](#test-data)
- [Running Tests](#running-tests)
- [Page Objects](#page-objects)
- [Test Classes](#test-classes)
- [Reports](#reports)
- [Troubleshooting](#troubleshooting)

---

## Project Overview

This framework automates functional testing of the mb.io crypto trading platform covering:

- **Navigation** — Header links, Sign In/Sign Up buttons, footer links
- **Trading/Markets** — Explore page, market sections (Top Gainers, Trending Now, Top Losers)
- **Content** — Page headings, hero text, features page, solution cards

**Target URLs:**

| Environment | URL |
|---|---|
| Marketing Site | `https://mb.io/en-AE` |
| Trading Platform | `https://trade.mb.io` |
| Login Page | `https://trade.mb.io/login` |
| Register Page | `https://trade.mb.io/register` |

---

## Tech Stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 11 | Programming language |
| Selenium WebDriver | 4.18.1 | Browser automation |
| TestNG | 7.9.0 | Test framework |
| Maven | 3.x | Build & dependency management |
| ExtentReports | 5.1.1 | HTML test reporting |
| WebDriverManager | 5.7.0 | Auto browser driver setup |
| Jackson Databind | 2.16.1 | JSON test data parsing |
| Apache Commons IO | 2.15.1 | Screenshot file handling |
| Eclipse IDE | Latest | Development environment |

---

## Project Structure

```
multibank-automation/
├── pom.xml                                   ← Maven dependencies & build config
├── testng.xml                                ← TestNG suite configuration
├── src/
│   ├── main/java/com/multibank/
│   │   ├── driver/
│   │   │   └── DriverFactory.java            ← Browser factory (Chrome/Firefox/Edge)
│   │   ├── pages/
│   │   │   ├── BasePage.java                 ← Shared waits, scroll, screenshot
│   │   │   ├── HomePage.java                 ← Nav, hero, banners, footer
│   │   │   ├── TradingPage.java              ← Explore/Markets page
│   │   │   └── AboutPage.java                ← Features page
│   │   ├── utils/
│   │   │   ├── ConfigReader.java             ← Reads config.properties
│   │   │   ├── DataProviderUtil.java         ← Loads JSON test data
│   │   │   ├── ScreenshotUtil.java           ← Captures screenshots on failure
│   │   │   └── WaitUtil.java                 ← Smart explicit waits
│   │   └── reporting/
│   │       └── ExtentReportManager.java      ← Singleton report setup
│   └── test/java/com/multibank/
│       ├── main/
│       │   └── Main.java                     ← Java app entry point
│       └── tests/
│           ├── BaseTest.java                 ← @BeforeSuite/@BeforeMethod/@AfterMethod
│           ├── NavigationTest.java           ← 18 navigation tests
│           ├── TradingTest.java              ← 10 trading/markets tests
│           └── ContentTest.java              ← 13 content/features tests
├── src/test/resources/
│   ├── config.properties                     ← URLs, browser, timeouts
│   └── testdata/
│       ├── navigation.json                   ← Expected nav items & footer links
│       ├── trading.json                      ← Expected market sections & pairs
│       └── content.json                      ← Expected text, headings & titles
└── test-output/
    ├── screenshots/                          ← Auto-captured on test failure
    └── reports/                              ← ExtentReport HTML output
```

---

## Prerequisites

Make sure the following are installed before running the project:

- **Java JDK 11** — [Download](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- **Maven 3.x** — [Download](https://maven.apache.org/download.cgi)
- **Eclipse IDE** — [Download](https://www.eclipse.org/downloads/)
- **Google Chrome** — Latest version (WebDriverManager handles the driver automatically)
- **Git** *(optional)* — For version control

Verify installations:
```cmd
java -version
mvn -version
```

---

## Setup & Installation

### Step 1: Create Project Structure
```cmd
mkdir multibank-automation
cd multibank-automation
mkdir -p src\main\java\com\multibank\driver
mkdir -p src\main\java\com\multibank\pages
mkdir -p src\main\java\com\multibank\utils
mkdir -p src\main\java\com\multibank\reporting
mkdir -p src\test\java\com\multibank\main
mkdir -p src\test\java\com\multibank\tests
mkdir -p src\test\resources\testdata
mkdir -p test-output\reports
mkdir -p test-output\screenshots
```

### Step 2: Import into Eclipse
1. Open Eclipse → **File** → **Import**
2. Select **Maven** → **Existing Maven Projects**
3. Browse to the `multibank-automation` folder
4. Click **Finish**

### Step 3: Update Maven
Right-click project → **Maven** → **Update Project** → check **Force Update** → **OK**

---

## Configuration

Edit `src/test/resources/config.properties` to change browser or URLs:

```properties
# Browser: chrome | firefox | edge | headless
browser=chrome

# URLs
baseUrl=https://trade.mb.io
marketingUrl=https://mb.io/en-AE
loginUrl=https://trade.mb.io/login
registerUrl=https://trade.mb.io/register
loginDomain=trade.mb.io

# Timeouts (seconds)
implicitWait=10
explicitWait=20
pageLoadTimeout=30

# Output paths
screenshotPath=test-output/screenshots/
reportPath=test-output/reports/
```

---

## Test Data

All test data is stored as JSON files in `src/test/resources/testdata/`:

### `navigation.json`
Contains expected navigation links and footer links:
```json
{
  "mainNavItems": [
    { "name": "Explore",  "url": "https://mb.io/en-AE/explore" },
    { "name": "Features", "url": "https://mb.io/en-AE/features" }
  ],
  "footerLinks": [
    { "name": "Terms & Conditions", "url": "/en-AE/about/terms-conditions" }
  ]
}
```

### `trading.json`
Contains expected market sections and trading pairs:
```json
{
  "marketSections": [
    { "section": "Top Gainers" },
    { "section": "Trending Now" },
    { "section": "Top Losers" }
  ]
}
```

### `content.json`
Contains expected page titles, headings, and feature section names:
```json
{
  "homePage": {
    "title": "Trade Crypto Fast & Secure | Low Fees Exchange | mb.io",
    "heroHeading": "Crypto for everyone"
  }
}
```

---

## Running Tests

### Option 1: Run All Tests via `Main.java` *(Recommended)*
Right-click `Main.java` → **Run As** → **Java Application**

### Option 2: Run via `testng.xml`
Right-click `testng.xml` → **Run As** → **TestNG Suite**

### Option 3: Run via Maven
```cmd
mvn test
```

### Option 4: Run a Single Test Class
Right-click any test class → **Run As** → **TestNG Test**

### Option 5: Run with Different Browser
```cmd
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
mvn test -Dbrowser=headless
```

### Option 6: Run Specific Test in `Main.java`
To run only one test class, update `Main.java`:
```java
// Navigation only
classes.add(new XmlClass("com.multibank.tests.NavigationTest"));

// Trading only
classes.add(new XmlClass("com.multibank.tests.TradingTest"));

// Content only
classes.add(new XmlClass("com.multibank.tests.ContentTest"));
```

---

## Page Objects

| Class | URL Tested | Responsibility |
|---|---|---|
| `HomePage.java` | `https://mb.io/en-AE` | Header nav, hero section, market sections, footer |
| `TradingPage.java` | `https://mb.io/en-AE/explore` | Markets page, spot market, Top Gainers/Losers |
| `AboutPage.java` | `https://mb.io/en-AE/features` | Features page, solution cards, MBG benefits |
| `BasePage.java` | All pages | Shared utilities: click, getText, scroll, screenshot |

---

## Test Classes

### `NavigationTest.java` — 18 Tests

| # | Test Method | What It Validates |
|---|---|---|
| 1 | `testPageTitle` | Page title matches `content.json` |
| 2 | `testSignInButtonDisplayed` | Sign In button visible in header |
| 3 | `testSignUpButtonDisplayed` | Sign Up button visible in header |
| 4 | `testSignInNavigation` | Sign In → navigates to `trade.mb.io/login` |
| 5 | `testSignUpNavigation` | Sign Up → navigates to `trade.mb.io/register` |
| 6 | `testExploreNavigation` | Explore link → navigates to `/explore` |
| 7 | `testFeaturesNavigation` | Features link → navigates to `/features` |
| 8 | `testCompanyNavigation` | Company link → navigates to `/company` |
| 9 | `testMainNavLinksPresent` | All nav links from `navigation.json` present |
| 10 | `testHeroHeading` | Hero heading = `"Crypto for everyone"` |
| 11 | `testHeroSubText` | Hero subtext contains `"Simple, secure and speedy"` |
| 12 | `testDownloadAppButton` | `"Download the app"` button visible |
| 13 | `testOpenAccountButton` | `"Open an account"` button visible |
| 14 | `testTopGainersSectionDisplayed` | Top Gainers section visible |
| 15 | `testTrendingNowSectionDisplayed` | Trending Now section visible |
| 16 | `testTopLosersSectionDisplayed` | Top Losers section visible |
| 17 | `testFooterCopyrightDisplayed` | Footer copyright text visible |
| 18 | `testFooterLinksPresent` | All footer links from `navigation.json` present |

### `TradingTest.java` — 10 Tests

| # | Test Method | What It Validates |
|---|---|---|
| 1 | `testExplorePageHeading` | Heading = `"Markets at your fingertips"` |
| 2 | `testSpotMarketSectionDisplayed` | Spot Market section visible |
| 3 | `testMarketSentimentDisplayed` | Market Sentiment section visible |
| 4 | `testTopGainersSectionDisplayed` | Top Gainers section visible |
| 5 | `testTrendingNowSectionDisplayed` | Trending Now section visible |
| 6 | `testTopLosersSectionDisplayed` | Top Losers section visible |
| 7 | `testEarnPromoDisplayed` | `"Up to 35% APY"` promo visible |
| 8 | `testInstantBuyPromoDisplayed` | Instant Buy promo visible |
| 9 | `testDepositPromoDisplayed` | Deposit promo visible |
| 10 | `testDownloadAppLinkDisplayed` | `"Download the app"` link visible |

### `ContentTest.java` — 13 Tests

| # | Test Method | What It Validates |
|---|---|---|
| 1 | `testHeroHeading` | Hero heading matches `content.json` |
| 2 | `testHeroSubText` | Hero subtext contains expected text |
| 3 | `testDownloadAppButtonDisplayed` | CTA button visible |
| 4 | `testOpenAccountButtonDisplayed` | CTA button visible |
| 5 | `testHomeTopGainersDisplayed` | Top Gainers on home page |
| 6 | `testHomeTrendingNowDisplayed` | Trending Now on home page |
| 7 | `testHomeTopLosersDisplayed` | Top Losers on home page |
| 8 | `testFeaturesPageHeading` | Features heading = `"The power of crypto is yours"` |
| 9 | `testExploreOpportunitiesSectionDisplayed` | Section visible on Features page |
| 10 | `testBuySellConvertSectionDisplayed` | Section visible on Features page |
| 11 | `testEarnYieldSectionDisplayed` | Section visible on Features page |
| 12 | `testMBGBenefitsSectionDisplayed` | `"$MBG unlocks mb.io benefits"` visible |
| 13 | `testFiatRampsCardDisplayed` | `"Fiat on/off ramps"` solution card visible |

**Total: 41 automated test cases**

---

## Reports

After test execution, the HTML report is generated at:
```
test-output/reports/MB_IO_TestReport.html
```

Open this file in any browser to view:
- ✅ Passed tests
- ❌ Failed tests with screenshots
- ⚠️ Skipped tests
- Execution time per test
- System info (browser, environment, tester)

Screenshots on failure are saved at:
```
test-output/screenshots/<TestName>_<timestamp>.png
```

---

## Troubleshooting

| Error | Cause | Fix |
|---|---|---|
| `TestNG cannot be resolved` | TestNG scope is `test` in pom.xml | Remove `<scope>test</scope>` from TestNG dependency |
| `Cannot find class in classpath` | Main.java in wrong source folder | Move `Main.java` to `src/test/java` |
| `XmlTest not associated with XmlSuite` | Wrong XmlTest construction | Use `new XmlTest(xmlSuite)` not `new XmlTest()` |
| `SLF4J: Failed to load StaticLoggerBinder` | Missing SLF4J implementation | Add `slf4j-simple` dependency to pom.xml |
| Sign In/Up click not working | Wrong locator or click intercepted | Use XPath with `href` fallback + JS click |
| URL assertion fails | URL has cookie/tracking params | Use `contains("login")` not full URL match |
| `WebDriverWait` timeout | Page loads slowly | Increase `explicitWait` in `config.properties` |
| Red errors after Maven import | Dependencies not downloaded | Right-click → Maven → Update Project → Force Update |

---

## Author
AFRIN AMEER KHAN
**QA Team**
Project: mb.io Crypto Platform Automation
Environment: Production (`https://mb.io/en-AE`)
Last Updated: March 2026
