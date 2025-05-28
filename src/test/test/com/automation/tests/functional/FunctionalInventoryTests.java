package com.automation.tests.functional;

import com.automation.tests.BaseTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionalInventoryTests extends BaseTest {

    @BeforeEach
    public void setUp() {
        super.setUp();
        inventoryPage = loginPage.successfulLoginAsStandardUser();
    }

    @Test
    public void sortProductsByPriceAscending() {
        inventoryPage.sortByPriceFromLowToHigh();
        List<Double> actualSortedPrices = inventoryPage.getAllProductPrices();
        List<Double> expectedSortedPrices = actualSortedPrices.stream()
                .sorted().collect(Collectors.toList());
        Assertions.assertEquals(expectedSortedPrices, actualSortedPrices, "products are not sorted correctly from cheapest to most expensive");
    }

    @Test
    public void sortProductsByNameDescending() {
        inventoryPage.sortByNameFromZtoA();
        List<String> actualSortedNames = inventoryPage.getAllNames();
        List<String> expectedSortedNames = actualSortedNames.stream()
                .sorted((a, b) -> b.compareToIgnoreCase(a)).toList();
        Assertions.assertEquals(expectedSortedNames, actualSortedNames, "products are not sorted in reverse alphabetical order");
    }

    @Test
    public void productDetailsView() {
        inventoryProductDetails = inventoryPage.openProductDetailList();
        Assertions.assertTrue(inventoryProductDetails.isNamePresence(), "product name is not present");
        Assertions.assertTrue(inventoryProductDetails.isImagePresence(), "image is not present");
        Assertions.assertTrue(inventoryProductDetails.isDescriptionPresence(), "description is not present");
        Assertions.assertTrue(inventoryProductDetails.isPricePresence(), "price is not present");
    }

    @Test
    public void addMultipleItemsToCart() {
        inventoryPage.add3ItemsToTheCart();
        Assertions.assertEquals(3, inventoryPage.getItemAmountFromCart(), "there were added 3 items but displayed not 3");
    }

}