package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {
    WebDriver driver;
    WebDriverWait wait;

    By headingLocator = By.className("title");

    public InventoryPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getUrl() {
    return driver.getCurrentUrl();
}

    public String getHeading() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(headingLocator));
    return driver.findElement(headingLocator).getText();
}

}