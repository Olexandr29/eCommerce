package com.automation.tests.sanity;

import com.automation.tests.BaseTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import static com.automation.pages.TestData.*;

public class SanityCartTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        inventoryPage = loginPage.successfulLoginAsStandardUser();
    }

    @Test
    public void navigateToCartPage() {
        cartPage = inventoryPage.openCartByClickOnCartIcon();
        Assertions.assertEquals(expectedCartPageUrl, cartPage.getUrl(), "the url of the cart page is wrong");
    }

    @Test
    public void removeItemFromCart() {
        inventoryPage.addToCartAction();
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        cartPage.removeFromTheCart();
        Assertions.assertTrue(cartPage.isRemoveButtonDisappeared(), "cart is not empty and the item is not removed");
        Assertions.assertTrue(cartPage.isCartBadgeDisappeared(), "the cart badge is not disappeared");
    }

}