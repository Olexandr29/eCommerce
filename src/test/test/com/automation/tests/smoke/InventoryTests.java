package com.automation.tests.smoke;
import com.automation.tests.BaseTest;

import com.automation.pages.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static com.automation.pages.TestData.*;

public class InventoryTests extends BaseTest {

    @Before
    public void setUp() {
        super.setUp();
        loginPage.enterCredentials(standardUsername, password);
        inventoryPage = loginPage.loginWithValidCredentials();
    }

    @Test
    public void checkProductListPresenceAfterLogin() {
        Assert.assertTrue("displayed less than 2 products",
                inventoryPage.displayedMoreThanOneProduct());
       Assert.assertTrue("some product has not name or/and price",
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
                1, inventoryPage.getItemAmountFromCart());
    }

}