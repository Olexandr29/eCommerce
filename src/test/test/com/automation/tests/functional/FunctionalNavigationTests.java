package com.automation.tests.functional;

import com.automation.tests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.automation.pages.TestData.expectedInventoryUrl;

public class FunctionalNavigationTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        inventoryPage = loginPage.successfulLoginAsStandardUser();
        inventoryProductDetails = inventoryPage.openProductDetailList();
    }

    @Test
    public void backFromPDPtoPLP() {
        inventoryPage = inventoryProductDetails.returnToInventoryByClickBackToProduct();
        Assertions.assertTrue(driver.getCurrentUrl().contains(expectedInventoryUrl), "not returned to the inventory page");
    }

    @Test
    public void backFromPDPtoPLPbyUsingBrowserBackBtn() {
        inventoryPage = inventoryProductDetails.returnToInventoryByClickBrowserBackBtn();
        Assertions.assertTrue(driver.getCurrentUrl().equals(expectedInventoryUrl), "not returned to the product list page (inventory page)");
    }

}