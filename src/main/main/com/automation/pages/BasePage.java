package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    By shoppingCartBadge = By.className("shopping_cart_badge");

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public CartPage openCartByClickOnCartBadge() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartBadge)).click();
        return new CartPage(driver, wait);
    }

    public int getItemAmountFromCart() {
        String actualText = wait.until(ExpectedConditions.elementToBeClickable(shoppingCartBadge)).getText();
        int actualAmount = Integer.parseInt(actualText);
        return actualAmount;
    }

}