package com.automation.tests.smoke;

import com.automation.tests.BaseTest;
import com.automation.pages.*;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static com.automation.pages.TestData.*;

@Tag("Regression") @Tag("Smoke")
public class SmokeInventoryTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        loginPage.enterCredentials(standardUsername, password);
        inventoryPage = loginPage.loginWithValidCredentials();
    }

    @Test
    @Epic("Product List Page (PLP)")
    @Feature("Inventory")
    @Story("Check product list is displayed after login")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-003")
    @DisplayName("Check presence of product list after login")
    public void checkProductListPresenceAfterLogin() {
        Assertions.assertTrue(inventoryPage.displayedMoreThanOneProduct(), "Expected more than 1 product to be displayed after login");
        Assertions.assertTrue(inventoryPage.areProductsContainNameAndPrice(), "Some product is missing name and/or price");
    }

    @Test
    @Epic("Authentication")
    @Feature("Logout")
    @Story("Logout to end session")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-004")
    @DisplayName("Logout from application")
    public void logoutFromTheWebsite() {
        LoginPage loginPageAfterLogout = inventoryPage.logOutAction();
        String actualUrl = loginPageAfterLogout.driver.getCurrentUrl();
        Assertions.assertEquals(url, actualUrl, "User is not redirected to login page");
    }

    @Test
    @Epic("Cart")
    @Feature("Add/Remove items")
    @Story("Cart badge reflects added items")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-005")
    @DisplayName("Add item to cart and check badge")
    public void addItemToCartAndCheckBadge() {
        inventoryPage.addToCartAction();
        Assertions.assertEquals(1, inventoryPage.getItemAmountFromCart(), "Cart icon doesn't show badge with '1'");
    }

}