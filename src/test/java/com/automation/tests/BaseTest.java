package com.automation.tests;

import com.automation.pages.*;
import static com.automation.pages.TestData.*;

import com.automation.tests.utils.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.PrintWriter;
import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    protected WebDriverWait wait;
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected CartPage cartPage;
    protected CheckoutStep1Page checkoutStep1Page;
    protected CheckoutStep2Page checkoutStep2Page;
    protected InventoryProductDetails inventoryProductDetails;
    protected CheckoutStep3CompletePage checkoutStep3CompletePage;

  //  @BeforeAll

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = DriverFactory.createDriver(browser);
  //      DriverFactory.generateAllureEnvironment(driver); // ✅ ПРАВИЛЬНО

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(url);
        loginPage = new LoginPage(driver, wait);

    //    generateEnvironment(driver);
    }

  /*  protected void generateEnvironment(WebDriver driver) {
        if (driver == null) {
            System.err.println("⚠  WebDriver is null. Skipping environment generation");
        return;
        }
        try {
            Capabilities capabilities = ((HasCapabilities) driver).getCapabilities();
            //                    .getBrowserVersion();
            String browserName = capabilities.getBrowserName();
            String browserVersion = capabilities.getBrowserVersion();
            //                     System.getProperty("browser", "chrome");

        try (PrintWriter writer = new PrintWriter("allure-results/environment.properties")) {
            writer.println("Browser=" + browserName);
            writer.println("Browser.Version=" + browserVersion);
        }
        } catch (Exception e) {
            System.err.println("❌ Could not generate Allure environment: " + e.getMessage());
        }
    }
*/

    @AfterEach
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}