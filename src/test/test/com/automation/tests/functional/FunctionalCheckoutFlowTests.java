package com.automation.tests.functional;

import com.automation.tests.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.List;
import static com.automation.pages.TestData.*;

@Epic("Checkout")
@Tag("Regression") @Tag("Functional")
public class FunctionalCheckoutFlowTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        inventoryPage = loginPage.successfulLoginAsStandardUser();
    }

    @Test
    @Feature("Order completion")
    @Story("Display thank you message after purchase")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("TC-020")
    public void fullPurchaseFlow() {
        inventoryPage.addToCartAction();
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        checkoutStep1Page = cartPage.navigateToCheckout();
        checkoutStep1Page.enterCheckoutInfo();
        checkoutStep2Page = checkoutStep1Page.clickContinueAndNavigateToNextStep();
        checkoutStep3CompletePage = checkoutStep2Page.clickFinish();
        Assertions.assertEquals(checkoutStep3CompletePage.getCompleteMessage(), expectedCheckoutStep3Confirmation,
                "the message on the confirmation page is wrong");
    }

    @Test
    @Feature("Form validation")
    @Story("Show error message for empty checkout fields")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-021")
    public void checkoutFormValidation() {
        inventoryPage.addToCartAction();
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        checkoutStep1Page = cartPage.navigateToCheckout();
        Assertions.assertTrue(checkoutStep1Page.getErrorMessage().
                contains(expectedCheckoutStep1NameIsEmpty), "the error message is wrong");
    }

    @Test
    @Feature("Order summary")
    @Story("Validations for subtotal, tax and total calculations")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-22")
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
            Assertions.assertEquals(itemPriceOnCartPage, itemPriceOnCheckoutPage, 0.001,
                    "prices for specific item is not equals on the different pages");
        }
        checkoutStep1Page = cartPage.navigateToCheckout();
        checkoutStep1Page.enterCheckoutInfo();
        checkoutStep2Page = checkoutStep1Page.clickContinueAndNavigateToNextStep();
    Assertions.assertEquals(checkoutStep2Page.sumOfProducts(), checkoutStep2Page.getSubtotal(),
            0.01, "the subtotal price is calculated wrong");
    Assertions.assertEquals((checkoutStep2Page.getSubtotal() + checkoutStep2Page.getTax()),
            checkoutStep2Page.getTotal(), 0.01, "the total price is calculated wrong");
    }

    }