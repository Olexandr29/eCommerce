package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CheckoutStep2Page extends BasePage {

    By cancelBtn = By.id("cancel");
    By finishBtb = By.id("finish");

    By itemContainer = By.className("cart_item");

    By subtotalPriceLocator = By.className("summary_subtotal_label");
    By taxLocator = By.className("summary_tax_label");
    By totalPriceLocator = By.className("summary_total_label");

    public CheckoutStep2Page(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public InventoryPage clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();
        return new InventoryPage(driver, wait);
    }

    public CheckoutStep3CompletePage clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishBtb)).click();
        return new CheckoutStep3CompletePage(driver, wait);
    }


    public double getSubtotal() {
        String subtotalText = wait.until(ExpectedConditions.visibilityOfElementLocated(subtotalPriceLocator)).getText();
        double subtotalPrice = Double.parseDouble((subtotalText.replace("Item total: $", "")));
        return subtotalPrice;
    }

    public double getTax() {
        String taxText = wait.until(ExpectedConditions.visibilityOfElementLocated(taxLocator)).getText();
        double taxValue = Double.parseDouble((taxText.replace("Tax: $", "")));
        return taxValue;
    }

    public double getTotal() {
        String totalText = wait.until(ExpectedConditions.visibilityOfElementLocated(totalPriceLocator)).getText();
        double totalPrice = Double.parseDouble((totalText.replace("Total: $", "")));
        return totalPrice;
    }

    public Double sumOfProducts() {
        List<Double> allPrices = getAllProductPrices();
        Double sum = 0.0;
        for (Double price : allPrices) {
            sum += price;
        }
        return sum;
    }

    public List<String> getCheckoutStep2ItemNames() {
        List<String> names = new ArrayList<>();
        List<WebElement> items = driver.findElements(itemContainer);
        for (WebElement item : items) {
            names.add(item.findElement(productNameLocator).getText());
        }
        return names;
    }

}