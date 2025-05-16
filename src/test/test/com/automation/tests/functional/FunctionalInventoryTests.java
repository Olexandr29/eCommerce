package com.automation.tests.functional;

import com.automation.tests.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionalInventoryTests extends BaseTest {

    @Before
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
        Assert.assertEquals("products are not sorted correctly from cheapest to most expensive",
                expectedSortedPrices, actualSortedPrices);
    }

    @Test
    public void sortProductsByNameDescending() {
    inventoryPage.sortByNameFromZtoA();
    List<String> actualSortedNames = inventoryPage.getAllNames();
    List<String> expectedSortedNames = actualSortedNames.stream()
            .sorted((a, b) -> b.compareToIgnoreCase(a)).toList();
    Assert.assertEquals("products are not sorted in reverse alphabetical order",
            expectedSortedNames, actualSortedNames);
    }

    @Test
    public void productDetailsView() {
        inventoryProductDetails = inventoryPage.openProductDetailList();
        Assert.assertTrue("product name is not present", inventoryProductDetails.isNamePresence());
        Assert.assertTrue("image is not present", inventoryProductDetails.isImagePresence());
        Assert.assertTrue("description is not present", inventoryProductDetails.isDescriptionPresence());
        Assert.assertTrue("price is not present", inventoryProductDetails.isPricePresence());
    }

    @Test
    public void addMultipleItemsToCart() {
    inventoryPage.add3ItemsToTheCart();
    Assert.assertEquals("there were added 3 items but displayed not 3",
            3, inventoryPage.getItemAmountFromCart());
    }

}