package src.test.tests.java.com.automation.tests.smoke.sanity;

import com.automation.pages.CartPage;
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

import static com.automation.pages.TestData.*;

public class SanityCartTests {
    WebDriver driver;
    WebDriverWait wait;

    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(url);
        loginPage = new LoginPage(driver, wait);
        inventoryPage = loginPage.successfulLoginAsStandardUser();

    }

    @Test
    public void navigateToCartPage() {
        cartPage = inventoryPage.openCartByClickOnCartIcon();
        Assert.assertEquals("the url of the cart page is wrong",
                expectedCartPageUrl, cartPage.getUrl() );
    }

    @Test
    public void removeItemFromCart() {
        inventoryPage.addToCartAction();
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        cartPage.removeFromTheCart();
        Assert.assertTrue("cart is not empty and the item is not removed",
                cartPage.isRemoveButtonDisappeared());
        Assert.assertTrue("the cart badge is not disappeared",
                cartPage.isCartBadgeDisappeared());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}