package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InventoryProductDetails extends BasePage{

    By productContainer = By.id("inventory_item_container");
    By nameLocator = By.xpath("//div[@data-test='inventory-item-name']");
    By imageLocator = By.className("inventory_details_img");
    By descriptionLocator = By.xpath("//div[@data-test='inventory-item-desc']");
    By priceLocator = By.className("inventory_details_price");

    By backToProductsBtb = By.id("back-to-products");

    public InventoryProductDetails(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public List<WebElement> getAllProducts() {
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productContainer));
        return products;
    }

    public boolean isNamePresence() {
    return driver.findElement(nameLocator).isDisplayed();
    }

    public boolean isImagePresence() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(imageLocator)).isDisplayed();
    }

    public boolean isDescriptionPresence() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionLocator)).isDisplayed();
    }

    public boolean isPricePresence() {
       return wait.until(ExpectedConditions.visibilityOfElementLocated(priceLocator)).isDisplayed();
    }

    public InventoryPage returnToInventoryByClickBackToProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(backToProductsBtb)).click();
        return new InventoryPage(driver, wait);
    }

    public InventoryPage returnToInventoryByClickBrowserBackBtn() {
        driver.navigate().back();
        return new InventoryPage(driver, wait);
    }

}
