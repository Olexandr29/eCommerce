package com.automation.tests;

import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.automation.pages.TestData.password;
import static com.automation.pages.TestData.standardUsername;
import static com.automation.pages.TestData.url;

// import static com.automation.pages.TestData.*;


public class InventoryTests {
    WebDriver driver;
    WebDriverWait wait;

    LoginPage loginPage;
    InventoryPage inventoryPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(url);
        loginPage = new LoginPage(driver, wait);
        loginPage.enterCredentials(standardUsername, password);
        inventoryPage = loginPage.loginWitValidCredentials();
    }

    @Test
    public void checkProductListPresenceAfterLogin() {
        Assert.assertTrue("displayed less than 2 products",
                inventoryPage.displayedMoreThanOneProduct());
       Assert.assertTrue("some product has not name or/and name",
               inventoryPage.areProductsContainNameAndPrice());
    }

    @Test
    public void logoutFromTheWebsite() {
        LoginPage loginPageAfterLogout = inventoryPage.logOutAction();
        String actualUrl = loginPageAfterLogout.driver.getCurrentUrl();
        Assert.assertEquals("User is redirected to login page",
                url, actualUrl);
    }

    @Test
    public void addItemToCartAndCheckBadge() {
        inventoryPage.addToCartAction();
        Assert.assertEquals("Cart icon doesn't show badge with '1'",
                1, inventoryPage.getItemAmount());
    }

    @After
    public void tearDown() {
    driver.quit();
    }

}