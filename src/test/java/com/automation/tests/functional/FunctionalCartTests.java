package com.automation.tests.functional;

import com.automation.tests.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Regression") @Tag("Functional")
@Epic("Cart")
@Feature("Add/Remove items")
public class FunctionalCartTests extends BaseTest {


    @BeforeEach
    public void setUp() {
        super.setUp();
        inventoryPage = loginPage.successfulLoginAsStandardUser();
    }

    @Story("Remove one item from cart")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-018")
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
    @TmsLink("TC-019")
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