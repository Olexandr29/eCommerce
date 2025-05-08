package com.automation.tests;

import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTests {
    public WebDriver driver;
    public WebDriverWait wait;

    LoginPage loginPage;
    InventoryPage inventoryPage;

    String url = "https://www.saucedemo.com/";
    String standardUsername = "standard_user";
    String password = "secret_sauce";
    String expectedInventoryUrl = url + "inventory.html";
    String expectedInventoryTitle = "Products";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test
    public void successfulLoginTest() {
    loginPage = new LoginPage(driver, wait);
    loginPage.enterCredentials(standardUsername, password);
    inventoryPage = loginPage.clickLoginBtn();
        Assert.assertEquals("the URL is wrong", expectedInventoryUrl, inventoryPage.getUrl());
        Assert.assertTrue("the title is wrong", inventoryPage.getHeading().equalsIgnoreCase(expectedInventoryTitle));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}