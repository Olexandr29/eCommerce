package com.automation.tests.sanity;

import com.automation.tests.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static com.automation.pages.TestData.*;

public class SanityCartTests extends BaseTest {

    @Before
    public void setUp() {
        super.setUp();
        inventoryPage = loginPage.successfulLoginAsStandardUser();
    }

    @Test
    public void navigateToCartPage() {
        cartPage = inventoryPage.openCartByClickOnCartIcon();
        Assert.assertEquals("the url of the cart page is wrong",
                expectedCartPageUrl, cartPage.getUrl() );
    }

    @Test
    public void removeItemFromCart() {
        inventoryPage.addToCartAction();
        cartPage = inventoryPage.openCartByClickOnCartBadge();
        cartPage.removeFromTheCart();
        Assert.assertTrue("cart is not empty and the item is not removed",
                cartPage.isRemoveButtonDisappeared());
        Assert.assertTrue("the cart badge is not disappeared",
                cartPage.isCartBadgeDisappeared());
    }

}