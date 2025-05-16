package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends BasePage {

    By removeBtn = By.xpath("//button[text()='Remove']");
    By shoppingCartBadge = By.className("shopping_cart_badge");
    By checkoutBtn = By.id("checkout");
    By itemContainerInCart = By.className("cart_item");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void removeFromTheCart() {
        wait.until(ExpectedConditions.elementToBeClickable(removeBtn)).click();
    }

    public boolean isRemoveButtonDisappeared() {
        boolean removeBtnDisappeared = false;
        wait.until(ExpectedConditions.invisibilityOfElementLocated(removeBtn));
        List<WebElement> itemsWithBtns = driver.findElements(removeBtn);
        if (itemsWithBtns.size() == 0) {
            removeBtnDisappeared = true;
        }
        return removeBtnDisappeared;
    }

    public boolean isCartBadgeDisappeared() {
    return wait.until(ExpectedConditions.invisibilityOfElementLocated(shoppingCartBadge));
    }

    public CheckoutStep1Page navigateToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        return new CheckoutStep1Page(driver, wait);
    }

    public void removeOneItem() {
        List<WebElement> allRemoveButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(removeBtn));
        allRemoveButtons.get(0).click();
    }

    public boolean isCartEmpty() {
       return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemContainerInCart)).isEmpty();
    }
}