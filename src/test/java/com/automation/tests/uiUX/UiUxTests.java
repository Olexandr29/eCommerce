package com.automation.tests.uiUX;

import com.automation.tests.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Dimension;
import static com.automation.pages.TestData.*;

@Epic("UI and UX validation")
@Tag("Regression") @Tag("uiUx")
public class UiUxTests extends BaseTest {
    @BeforeEach
    public void setUp() {
    super.setUp();
    inventoryPage = loginPage.successfulLoginAsStandardUser();
    }

    @Test
    @Feature("Element visibility")
    @Story("Header and branding elements must be clearly visible to user")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-025")
    public void logoAndHeaderVisibility() {
        Assertions.assertTrue(inventoryPage.isLogoVisible(), "logo is not visible on the page");
        Assertions.assertTrue(inventoryPage.isHeaderVisible(), "the header title is not visible");
        Assertions.assertEquals(expectedInventoryTitle, inventoryPage.getHeading(), "the header title is wrong");
    }

    @Test
    @Feature("Responsive layout")
    @Story("Layout must adapt between desktop and mobile views without breaking functionality")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("TC-026")
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
    @Feature("Button interaction")
    @Story("Add to cart button should reflect product status to the user visually")
    @Severity(SeverityLevel.TRIVIAL)
    @TmsLink("TC-027")
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