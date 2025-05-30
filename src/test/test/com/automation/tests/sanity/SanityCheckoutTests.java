package com.automation.tests.sanity;

import com.automation.tests.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import static com.automation.pages.TestData.*;
@Epic("Checkout")
@Tag("Regression") @Tag("Sanity")
public class SanityCheckoutTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        inventoryPage = loginPage.successfulLoginAsStandardUser();
        inventoryPage.addToCartAction();
    }

    @Test
    @Feature("Checkout navigation")
    @Story("Proceed to checkout page 1 to begin purchase")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("TC-011")
    public void proceedToCheckout() {
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        checkoutStep1Page = cartPage.navigateToCheckout();
        Assertions.assertEquals(checkoutStep1Page.getUrl(), expectedCheckoutStep1Url, "user is not redirected to checkout");
    }

    @Test
    @Feature("Checkout form")
    @Story("Enter personal info to proceed to checkout page 2")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("TC-012")
    public void proceedToCheckoutStep2() {
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        checkoutStep1Page = cartPage.navigateToCheckout();
        checkoutStep1Page.enterCheckoutInfo();
        checkoutStep2Page = checkoutStep1Page.clickContinueAndNavigateToNextStep();
        Assertions.assertEquals(expectedCheckoutStep2Url, checkoutStep2Page.getUrl(), "the url is wrong fro checkout step 2 page");
    }

    @Test
    @Feature("Checkout cancellation")
    @Story("Cancel checkout and return to PLP")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-013")
    public void cancelFromOverviewPage() {
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        checkoutStep1Page = cartPage.navigateToCheckout();
        checkoutStep1Page.enterCheckoutInfo();
        checkoutStep2Page = checkoutStep1Page.clickContinueAndNavigateToNextStep();
        inventoryPage = checkoutStep2Page.clickCancel();
        Assertions.assertEquals(expectedInventoryUrl, inventoryPage.getUrl(), "user is not redirected from checkout to cart page");
    }

}