package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutStep3CompletePage extends BasePage {

    public CheckoutStep3CompletePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    By completeHeaderLocator = By.xpath("//h2[@class='complete-header']");

    public String getCompleteMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeaderLocator)).getText();
    }

}