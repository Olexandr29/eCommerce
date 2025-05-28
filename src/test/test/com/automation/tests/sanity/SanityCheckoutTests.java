package com.automation.tests.sanity;

import com.automation.tests.BaseTest;
import org.junit.jupiter.api.*;
import static com.automation.pages.TestData.*;

public class SanityCheckoutTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        inventoryPage = loginPage.successfulLoginAsStandardUser();
        inventoryPage.addToCartAction();
    }

    @Test
    public void proceedToCheckout() {
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        checkoutStep1Page = cartPage.navigateToCheckout();
        Assertions.assertEquals(checkoutStep1Page.getUrl(), expectedCheckoutStep1Url, "user is not redirected to checkout");
    }

    @Test
    public void proceedToCheckoutStep2() {
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        checkoutStep1Page = cartPage.navigateToCheckout();
        checkoutStep1Page.enterCheckoutInfo();
        checkoutStep2Page = checkoutStep1Page.clickContinueAndNavigateToNextStep();
        Assertions.assertEquals(expectedCheckoutStep2Url, checkoutStep2Page.getUrl(), "the url is wrong fro checkout step 2 page");
        System.out.println(checkoutStep2Page.getUrl());
    }

    @Test
    public void cancelFromOverviewPage() {
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        checkoutStep1Page = cartPage.navigateToCheckout();
        checkoutStep1Page.enterCheckoutInfo();
        checkoutStep2Page = checkoutStep1Page.clickContinueAndNavigateToNextStep();
        inventoryPage = checkoutStep2Page.clickCancel();
        Assertions.assertEquals(expectedInventoryUrl, inventoryPage.getUrl(), "user is not redirected from checkout to cart page");
    }

}