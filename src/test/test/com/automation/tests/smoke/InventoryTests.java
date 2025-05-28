package com.automation.tests.smoke;
import com.automation.tests.BaseTest;

import com.automation.pages.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import static com.automation.pages.TestData.*;

public class InventoryTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        loginPage.enterCredentials(standardUsername, password);
        inventoryPage = loginPage.loginWithValidCredentials();
    }

    @Test
    public void checkProductListPresenceAfterLogin() {
        Assertions.assertTrue(inventoryPage.displayedMoreThanOneProduct(), "displayed less than 2 products");
        Assertions.assertTrue(inventoryPage.areProductsContainNameAndPrice(), "some product has not name or/and price");
    }

    @Test
    public void logoutFromTheWebsite() {
        LoginPage loginPageAfterLogout = inventoryPage.logOutAction();
        String actualUrl = loginPageAfterLogout.driver.getCurrentUrl();
        Assertions.assertEquals(url, actualUrl, "User is redirected to login page");
    }

    @Test
    public void addItemToCartAndCheckBadge() {
        inventoryPage.addToCartAction();
        Assertions.assertEquals(1, inventoryPage.getItemAmountFromCart(), "Cart icon doesn't show badge with '1'");
    }

}