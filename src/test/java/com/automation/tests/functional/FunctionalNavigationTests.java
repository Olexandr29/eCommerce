package com.automation.tests.functional;

import com.automation.tests.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.automation.pages.TestData.expectedInventoryUrl;

@Epic("Navigation flow")
@Tag("Regression") @Tag("Functional")
public class FunctionalNavigationTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        inventoryPage = loginPage.successfulLoginAsStandardUser();
        inventoryProductDetails = inventoryPage.openProductDetailList();
    }

    @Test
    @Feature("Return navigation")
    @Story("Return from PDP tp PDL by using Back to Products button")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("TC-023")
    public void backFromPDPtoPLP() {
        inventoryPage = inventoryProductDetails.returnToInventoryByClickBackToProduct();
        Assertions.assertTrue(driver.getCurrentUrl().contains(expectedInventoryUrl), "not returned to the inventory page");
    }

    @Test
    @Feature("Return navigation")
    @Story("Return from PDP to PLP by using browser back option")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("TC-024")
    public void backFromPDPtoPLPbyUsingBrowserBackBtn() {
        inventoryPage = inventoryProductDetails.returnToInventoryByClickBrowserBackBtn();
        Assertions.assertTrue(driver.getCurrentUrl().equals(expectedInventoryUrl), "not returned to the product list page (inventory page)");
    }

}