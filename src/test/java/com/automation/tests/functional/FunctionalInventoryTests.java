package com.automation.tests.functional;

import com.automation.tests.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.stream.Collectors;

@Tag("Regression") @Tag("Functional")
public class FunctionalInventoryTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        inventoryPage = loginPage.successfulLoginAsStandardUser();
    }

    @Test
    @Epic("Product List Page (PLP)")
    @Feature("Sorting and filtering")
    @Story("Sort product by price ascending")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-014")
    public void sortProductsByPriceAscending() {
        inventoryPage.sortByPriceFromLowToHigh();
        List<Double> actualSortedPrices = inventoryPage.getAllProductPrices();
        List<Double> expectedSortedPrices = actualSortedPrices.stream()
                .sorted().collect(Collectors.toList());
        Assertions.assertEquals(expectedSortedPrices, actualSortedPrices, "products are not sorted correctly from cheapest to most expensive");
    }

    @Test
    @Epic("Product List Page (PLP)")
    @Feature("Sorting and filtering")
    @Story("Sort product by name descending")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("TC-015")
    public void sortProductsByNameDescending() {
        inventoryPage.sortByNameFromZtoA();
        List<String> actualSortedNames = inventoryPage.getAllNames();
        List<String> expectedSortedNames = actualSortedNames.stream()
                .sorted((a, b) -> b.compareToIgnoreCase(a)).toList();
        Assertions.assertEquals(expectedSortedNames, actualSortedNames, "products are not sorted in reverse alphabetical order");
    }

    @Test
    @Epic("Product Details Page (PDP)")
    @Feature("Product info display")
    @Story("View product details")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-016")
    public void productDetailsView() {
        inventoryProductDetails = inventoryPage.openProductDetailList();
        Assertions.assertTrue(inventoryProductDetails.isNamePresence(), "product name is not present");
        Assertions.assertTrue(inventoryProductDetails.isImagePresence(), "image is not present");
        Assertions.assertTrue(inventoryProductDetails.isDescriptionPresence(), "description is not present");
        Assertions.assertTrue(inventoryProductDetails.isPricePresence(), "price is not present");
    }

    @Test
    @Epic("Cart")
    @Feature("Add/Remove items")
    @Story("Add several items to cart")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-017")
    public void addMultipleItemsToCart() {
        inventoryPage.add3ItemsToTheCart();
        Assertions.assertEquals(3, inventoryPage.getItemAmountFromCart(), "there were added 3 items but displayed not 3");
    }

}