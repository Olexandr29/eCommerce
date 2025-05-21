package com.automation.tests.ui_ux;

import com.automation.tests.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import static com.automation.pages.TestData.*;

public class UI_UXtests extends BaseTest {
    @Before
    public void setUp() {
    super.setUp();
    inventoryPage = loginPage.successfulLoginAsStandardUser();
    }

    @Test
    public void logoAndHeaderVisibility() {
        Assert.assertTrue("logo is not visible on the page",
                inventoryPage.isLogoVisible());
        Assert.assertTrue("the header title is not visible",
                inventoryPage.isHeaderVisible());
        Assert.assertEquals("the header title is wrong",
                expectedInventoryTitle, inventoryPage.getHeading());
    }

    @Test
    public void browserWindowResizing() throws InterruptedException {
    Assert.assertTrue("Inventory list is not displayed",
            inventoryPage.isInventoryContainerVisible());

    driver.manage().window().setSize(new Dimension(375, 667));
    Assert.assertTrue("burger menu like a button should be visible in mobile view",
            inventoryPage.isBurgerMenuVisible());
    Assert.assertTrue("Inventory list is not displayed",
                inventoryPage.isInventoryContainerVisible());
    Assert.assertTrue("add to cart button is not clickable",
            inventoryPage.isAddToCartButtonClickable());

    driver.manage().window().setSize(new Dimension(1280, 800));
        Assert.assertTrue("burger menu is not visible after resizing",
                inventoryPage.isBurgerMenuVisible());
        Assert.assertTrue("Inventory list is not displayed after resizing",
                inventoryPage.isInventoryContainerVisible());
        Assert.assertTrue("add to cart button is not clickable after resizing",
                inventoryPage.isAddToCartButtonClickable());
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

        Assert.assertEquals("Remove", afterText);
        Assert.assertNotEquals("Button color should change after adding to cart", beforeColor, afterColor);

        cartPage = inventoryPage.openCartByClickOnCartBadge();
        cartPage.removeFromTheCart();
        driver.navigate().back();

        String finalText = inventoryPage.getTextOfTheButton(specificProductName);
        String finalColor = inventoryPage.getButtonClass(specificProductName);
        System.out.println("After removing: " + finalText + ", " + finalColor);

        Assert.assertEquals("Add to cart", finalText);
        Assert.assertEquals("Button color should return to original", beforeColor, finalColor);

    }

}