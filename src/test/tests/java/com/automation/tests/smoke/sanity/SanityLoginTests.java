package src.test.tests.java.com.automation.tests.smoke.sanity;

import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.automation.pages.TestData.*;

public class SanityLoginTests {
    WebDriver driver;
    WebDriverWait wait;

LoginPage loginPage;
InventoryPage inventoryPage;

@Before
    public void setUp() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--incognito");
    driver = new ChromeDriver(options);
     wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    driver.get(url);
    loginPage = new LoginPage(driver, wait);

}

    @Test
    public void successfulLoginAsPerformanceGlitchUser() {
    loginPage.enterCredentials(performanceUser, password);
        inventoryPage = loginPage.loginWithValidCredentials();
        Assert.assertEquals("the URL is wrong", expectedInventoryUrl, inventoryPage.getUrl());
        Assert.assertTrue("the title is wrong", inventoryPage.getHeading().equalsIgnoreCase(expectedInventoryTitle));
    }

    @Test
    public void unsuccessfulLoginWithEmptyFields() {
    Assert.assertTrue("the error message for Login with empty fields is wrong",
            loginPage.loginWithoutCredentials().contains(expectedErrMsgForLoginWithEmptyFields));
    }

    @Test
    public void unsuccessfulLoginWithNonExistentUser() {
    Assert.assertTrue("the error message for non-existent user is wrong",
            loginPage.loginWithNonExistingCredentials().contains(expectedErrMsgForNonExistentUser));
    }
    
    @After
    public void tearDown() {
    driver.quit();
    }

}