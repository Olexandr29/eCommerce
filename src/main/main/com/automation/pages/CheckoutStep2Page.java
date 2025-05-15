package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutStep2Page extends BasePage {

    By cancelBtn = By.id("cancel");

    public CheckoutStep2Page(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public InventoryPage clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
        return new InventoryPage(driver, wait);
    }

}