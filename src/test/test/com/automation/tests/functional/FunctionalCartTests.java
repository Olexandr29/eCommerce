package com.automation.tests.functional;

import com.automation.tests.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FunctionalCartTests extends BaseTest {

    @Before
    public void setUp() {
        super.setUp();
    inventoryPage = loginPage.successfulLoginAsStandardUser();
    }

    @Test
    public void removeOneItemFromCart() {
        inventoryPage.add3ItemsToTheCart();
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        cartPage.removeOneItem();
        Assert.assertEquals("badge doesn't show 2 items",
                2, cartPage.getItemAmountFromCart());
    }

    @Test
    public void cartStatePersistsAcrossNavigation() {
        inventoryPage.addToCartAction();
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        checkoutStep1Page = cartPage.navigateToCheckout();
        cartPage = checkoutStep1Page.openCartByClickOnCartBadge();
        Assert.assertEquals("the badge amount is changed after",
                1, cartPage.getItemAmountFromCart());
        Assert.assertFalse("item disappeared from the cart",
                cartPage.isCartEmpty());
    }

}