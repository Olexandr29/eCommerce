package com.automation.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static com.automation.pages.TestData.*;
import static com.automation.pages.TestData.lastItemName;

@Epic("Cart")
public class OtherTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        inventoryPage = loginPage.successfulLoginAsStandardUser();
    }

    @Story("Persistent added item's amount after logout")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-031")
    @Description("Verify that the added item's amount to the cart is not lost after logout and re-login actions")
    @Test
public void cartPersistAfterLogOut() {
        inventoryPage.addSpecificItemToCart(lastItemName);
    Assertions.assertEquals(1, inventoryPage.getItemAmountFromCart(), "The cart contains not 1 item");
        inventoryPage.logOutAction();
    Assertions.assertEquals(url, driver.getCurrentUrl(), "still on inventory page and not logged out");
        loginPage.successfulLoginAsStandardUser();
    Assertions.assertEquals(expectedInventoryUrl, driver.getCurrentUrl(), "not on the Inventory page");
    Assertions.assertEquals(1, inventoryPage.getItemAmountFromCart(), "The cart contains not 1 item after logout/login actions");
        cartPage = inventoryPage.openCartByClickOnCartBadge();
    Assertions.assertEquals(lastItemName, cartPage.getAllNames().get(0), "the name of added and located at the cart items are not equal after logout/login actions");
    cartPage.removeFromTheCart();
    }

    @Story("Remove added to the Cart items via Inventory page")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("TC-032")
    @Description("Verify that you can remove items from the Cart via Inventory page")
    @Test
    public void removeFromCartViaInventoryPage() {
inventoryPage.addToCartAction();
    Assertions.assertEquals(1, inventoryPage.getItemAmountFromCart());
cartPage = inventoryPage.openCartByClickOnCartBadge();
driver.navigate().back();
    Assertions.assertEquals(expectedInventoryUrl, driver.getCurrentUrl(), "it should be the Inventory page after navigating back from Cart page, but it's not ");
inventoryPage.removeAddedItemFromCartViaInventoryPage();
cartPage = inventoryPage.openCartByClickOnCartIcon();
    Assertions.assertTrue(cartPage.isCartBadgeDisappeared(), "the cart badge is not disappeared");
    }

    @Story("Checkout with empty cart")
    @Tag("Negative")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("TC-033")
    @Description("Verify that system doesn't allow to navigate from empty cart to checkout stage")
    @Test
    public void CheckoutWithEmptyCart(){
        cartPage = inventoryPage.openCartByClickOnCartIcon();
        cartPage.navigateToCheckout();
        Assertions.assertEquals(expectedCartPageUrl, driver.getCurrentUrl(), "it should be still the Cart page, but it's not")
    }
}
