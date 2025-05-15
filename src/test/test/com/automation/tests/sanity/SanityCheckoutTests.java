package com.automation.tests.sanity;

import com.automation.tests.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.automation.pages.TestData.*;

public class SanityCheckoutTests extends BaseTest {

    @Before
    public void setUp() {
        super.setUp();
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

}