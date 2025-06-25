package com.automation.tests.sanity;

import com.automation.tests.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static com.automation.pages.TestData.*;

@Epic("Cart")
@Tag("Regression") @Tag("Sanity")
public class SanityCartTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        inventoryPage = loginPage.successfulLoginAsStandardUser();
    }

    @Test
    @Feature("Cart navigation")
    @Story("Open cart by click cart icon")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-009")
    @DisplayName("Navigate to the cart page")
    public void navigateToCartPage() {
        cartPage = inventoryPage.openCartByClickOnCartIcon();
        Assertions.assertEquals(expectedCartPageUrl, cartPage.getUrl(), "the url of the cart page is wrong");
    }

    @Test
    @Feature("Add/Remove items")
    @Story("Remove item from cart")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-010")
    @DisplayName("Remove item from cart")
    public void removeItemFromCart() {
        inventoryPage.addToCartAction();
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        cartPage.removeFromTheCart();
        Assertions.assertTrue(cartPage.isRemoveButtonDisappeared(), "cart is not empty and the item is not removed");
        Assertions.assertTrue(cartPage.isCartBadgeDisappeared(), "the cart badge is not disappeared");
    }

}