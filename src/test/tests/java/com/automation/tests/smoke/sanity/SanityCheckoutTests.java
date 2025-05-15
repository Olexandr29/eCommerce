package src.test.tests.java.com.automation.tests.smoke.sanity;

import com.automation.pages.CartPage;
import com.automation.pages.CheckoutStep1Page;
import com.automation.pages.InventoryPage;
import com.automation.pages.LoginPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.main.main.java.com.automation.pages.CheckoutStep2Page;

import static com.automation.pages.TestData.*;

import java.time.Duration;

public class SanityCheckoutTests {
    WebDriver driver;
    WebDriverWait wait;

    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutStep1Page checkoutStep1Page;
    CheckoutStep2Page checkoutStep2Page;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(url);
        loginPage = new LoginPage(driver, wait);
        inventoryPage = loginPage.successfulLoginAsStandardUser();
        inventoryPage.addToCartAction();
    }

    @Test
    public void proceedToCheckout() {
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        checkoutStep1Page = cartPage.navigateToCheckout();
        Assert.assertEquals("user is not redirected to checkout",
                checkoutStep1Page.getUrl(), expectedCheckoutStep1Url);
    }

    @Test
    public void proceedToCheckoutStep2() {
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        checkoutStep1Page = cartPage.navigateToCheckout();
        checkoutStep1Page.enterCheckoutInfo();
        checkoutStep2Page = checkoutStep1Page.clickContinue();
        Assert.assertEquals("the url is wrong fro checkout step 2 page",
                expectedCheckoutStep2Url,
                checkoutStep2Page.getUrl());
        System.out.println(checkoutStep2Page.getUrl());
    }

    @Test
    public void cancelFromOverviewPage() {
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        checkoutStep1Page = cartPage.navigateToCheckout();
        checkoutStep1Page.enterCheckoutInfo();
        checkoutStep2Page = checkoutStep1Page.clickContinue();
        inventoryPage = checkoutStep2Page.clickCancel();
        Assert.assertEquals("user is not redirected from checkout to cart page",
                expectedInventoryUrl, inventoryPage.getUrl() );
    }


    public void tearDown() {
        driver.quit();
    }
}
