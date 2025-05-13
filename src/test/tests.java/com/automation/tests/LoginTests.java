package com.automation.tests;

import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import com.automation.pages.TestData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.automation.pages.TestData.*;


public class LoginTests {
    public WebDriver driver;
    public WebDriverWait wait;

    LoginPage loginPage;
    InventoryPage inventoryPage;


    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(TestData.url);
        loginPage = new LoginPage(driver, wait);
    }

    @Test
    public void successfulLoginTest() {
    loginPage.enterCredentials(standardUsername, password);
    inventoryPage = loginPage.loginWitValidCredentials();
        Assert.assertEquals("the URL is wrong", expectedInventoryUrl, inventoryPage.getUrl());
        Assert.assertTrue("the title is wrong", inventoryPage.getHeading().equalsIgnoreCase(expectedInventoryTitle));
    }

    @Test
    public void unsuccessfulLoginWithLockedUser() {
    loginPage.enterCredentials(lockedUser, password);
    Assert.assertTrue("the error message for locked user is wrong", loginPage.loginWithInvalidCredentials().equals("Sorry, this user has been locked out."));
        }


    @After
    public void tearDown() {
        driver.quit();
    }

}