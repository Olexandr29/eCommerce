package com.automation.tests;

import com.automation.pages.*;
import static com.automation.pages.TestData.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected CartPage cartPage;
    protected CheckoutStep1Page checkoutStep1Page;
    protected CheckoutStep2Page checkoutStep2Page;
    protected InventoryProductDetails inventoryProductDetails;
    protected CheckoutStep3CompletePage checkoutStep3CompletePage;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(url);
        loginPage = new LoginPage(driver, wait);
    }

    @AfterEach
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}