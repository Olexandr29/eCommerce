package com.automation.tests.functional;

import com.automation.tests.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.automation.pages.TestData.expectedInventoryUrl;

public class FunctionalNavigationTests extends BaseTest {

    @Before
    public void setUp() {
        super.setUp();
        inventoryPage = loginPage.successfulLoginAsStandardUser();
        inventoryProductDetails = inventoryPage.openProductDetailList();
    }

    @Test
    public void backFromPDPtoPLP() {
        inventoryPage = inventoryProductDetails.returnToInventoryByClickBackToProduct();
        Assert.assertTrue("not returned to the inventory page",
                driver.getCurrentUrl().contains(expectedInventoryUrl));
    }

    @Test
    public void backFromPDPtoPLPbyUsingBrowserBackBtn() {
    inventoryPage = inventoryProductDetails.returnToInventoryByClickBrowserBackBtn();
    Assert.assertTrue("not returned to the product list page (inventory page)",
            driver.getCurrentUrl().equals(expectedInventoryUrl));
    }

}