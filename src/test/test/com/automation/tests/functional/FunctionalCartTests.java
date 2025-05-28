package com.automation.tests.functional;

import com.automation.tests.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Epic("Cart functionality")
@Feature("Cart management")
public class FunctionalCartTests extends BaseTest {


    @BeforeEach
    public void setUp() {
        super.setUp();
        inventoryPage = loginPage.successfulLoginAsStandardUser();
    }

    @Story("Remove item from cart")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that removing one item updates the cart correctly")
    @Test
    public void removeOneItemFromCart() {
        inventoryPage.add3ItemsToTheCart();
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        cartPage.removeOneItem();
        Assertions.assertEquals(2, cartPage.getItemAmountFromCart(), "badge doesn't show 2 items");
    }

    @Story("Cart state persistence")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the cart state persists after navigation to checkout")
    @Test
    public void cartStatePersistsAcrossNavigation() {
        inventoryPage.addToCartAction();
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        checkoutStep1Page = cartPage.navigateToCheckout();
        cartPage = checkoutStep1Page.openCartByClickOnCartBadge();
        Assertions.assertEquals(1, cartPage.getItemAmountFromCart(), "the badge amount is changed after");
        Assertions.assertFalse(cartPage.isCartEmpty(), "item disappeared from the cart");
    }

}