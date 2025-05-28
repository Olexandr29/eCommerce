package com.automation.tests.ui_ux;

import com.automation.tests.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.Dimension;
import static com.automation.pages.TestData.*;

public class UI_UXtests extends BaseTest {
    @BeforeEach
    public void setUp() {
    super.setUp();
    inventoryPage = loginPage.successfulLoginAsStandardUser();
    }

    @Test
    public void logoAndHeaderVisibility() {
        Assertions.assertTrue(inventoryPage.isLogoVisible(), "logo is not visible on the page");
        Assertions.assertTrue(inventoryPage.isHeaderVisible(), "the header title is not visible");
        Assertions.assertEquals(expectedInventoryTitle, inventoryPage.getHeading(), "the header title is wrong");
    }

    @Test
    public void browserWindowResizing() throws InterruptedException {
        Assertions.assertTrue(inventoryPage.isInventoryContainerVisible(), "Inventory list is not displayed");

        driver.manage().window().setSize(new Dimension(375, 667));
        Assertions.assertTrue(inventoryPage.isBurgerMenuVisible(), "burger menu like a button should be visible in mobile view");
        Assertions.assertTrue(inventoryPage.isInventoryContainerVisible(), "Inventory list is not displayed");
        Assertions.assertTrue(inventoryPage.isAddToCartButtonClickable(), "add to cart button is not clickable");

        driver.manage().window().setSize(new Dimension(1280, 800));
        Assertions.assertTrue(inventoryPage.isBurgerMenuVisible(), "burger menu is not visible after resizing");
        Assertions.assertTrue(inventoryPage.isInventoryContainerVisible(), "Inventory list is not displayed after resizing");
        Assertions.assertTrue(inventoryPage.isAddToCartButtonClickable(), "add to cart button is not clickable after resizing");
    }

    @Test
    public void buttonStateChangeOnInteraction() {
        String beforeText = inventoryPage.getTextOfTheButton(specificProductName);
        String beforeColor = inventoryPage.getButtonClass(specificProductName);
        System.out.println("Before adding to cart: " + beforeText + ", " + beforeColor);

        inventoryPage.addSpecificItemToCart(specificProductName);

        String afterText = inventoryPage.getTextOfTheButton(specificProductName);
        String afterColor = inventoryPage.getButtonClass(specificProductName);
        System.out.println("After adding to cart: " + afterText + ", " + afterColor);

        Assertions.assertEquals(afterText, "Remove");
        Assertions.assertNotEquals("Button color should change after adding to cart", beforeColor, afterColor);

        cartPage = inventoryPage.openCartByClickOnCartBadge();
        cartPage.removeFromTheCart();
        driver.navigate().back();

        String finalText = inventoryPage.getTextOfTheButton(specificProductName);
        String finalColor = inventoryPage.getButtonClass(specificProductName);
        System.out.println("After removing: " + finalText + ", " + finalColor);

        Assertions.assertEquals(finalText, "Add to cart");
        Assertions.assertEquals(beforeColor, finalColor, "Button color should return to original");

    }

}