package com.automation.tests.functional;

import com.automation.tests.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.automation.pages.TestData.*;

public class FunctionalCheckoutFlowTests extends BaseTest {

    @Before
    public void setUp() {
        super.setUp();
        inventoryPage = loginPage.successfulLoginAsStandardUser();
    }

    @Test
    public void fullPurchaseFlow() {
        inventoryPage.addToCartAction();
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        checkoutStep1Page = cartPage.navigateToCheckout();
        checkoutStep1Page.enterCheckoutInfo();
        checkoutStep2Page = checkoutStep1Page.clickContinueAndNavigateToNextStep();
        checkoutStep3CompletePage = checkoutStep2Page.clickFinish();
        Assert.assertEquals("the message on the confirmation page is wrong",
                expectedCheckoutStep3Confirmation,
                checkoutStep3CompletePage.getCompleteMessage());
    }

    @Test
    public void checkoutFormValidation() {
        inventoryPage.addToCartAction();
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        checkoutStep1Page = cartPage.navigateToCheckout();
        Assert.assertTrue("the error message is wrong",
                checkoutStep1Page.getErrorMessage().
                        contains(expectedCheckoutStep1NameIsEmpty));
    }

    @Test
    public void totalPriceWithTaxIsCalculatedCorrectly() {
        inventoryPage.add3ItemsToTheCart();
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        checkoutStep1Page = cartPage.navigateToCheckout();
        checkoutStep1Page.enterCheckoutInfo();
        checkoutStep2Page = checkoutStep1Page.clickContinueAndNavigateToNextStep();
        List<String> productNamesOnCheckoutPage = checkoutStep2Page.getCheckoutStep2ItemNames();
        for (String itemName : productNamesOnCheckoutPage) {
            double itemPriceOnCheckoutPage = checkoutStep2Page.getPriceByItemName(itemName);
            cartPage = checkoutStep2Page.openCartByClickOnCartBadge();
            double itemPriceOnCartPage = cartPage.getPriceByItemName(itemName);
            Assert.assertEquals("prices for specific item is not equals on the different pages",
                    itemPriceOnCartPage, itemPriceOnCheckoutPage, 0.001);
        }
        checkoutStep1Page = cartPage.navigateToCheckout();
        checkoutStep1Page.enterCheckoutInfo();
        checkoutStep2Page = checkoutStep1Page.clickContinueAndNavigateToNextStep();
    Assert.assertEquals("the subtotal price is calculated wrong",
            checkoutStep2Page.sumOfProducts(), checkoutStep2Page.getSubtotal(),
            0.01);
    Assert.assertEquals("the total price is calculated wrong",
            (checkoutStep2Page.getSubtotal() + checkoutStep2Page.getTax()),
            checkoutStep2Page.getTotal(), 0.01);
    }

    }