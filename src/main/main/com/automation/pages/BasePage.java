package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasePage {
    public WebDriver driver;
    public WebDriverWait wait;

    By shoppingCartBadge = By.className("shopping_cart_badge");

    By productNameLocator = By.className("inventory_item_name");
    By productPriceLocator = By.className("inventory_item_price");

    By itemContainerForCartAndCheckout = By.className("cart_item");
    By itemContainerForInventory = By.className("inventory_item");


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

    public List<String> getAllNames() {
        List<WebElement> allProductsWithNames = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNameLocator));
        List<String> names = new ArrayList<>();
        for (WebElement productName : allProductsWithNames) {
            names.add(productName.getText());
        }
        return names;
    }

    public List<Double> getAllProductPrices() {
        List<WebElement> priceElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productPriceLocator));
        List<Double> prices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    public List<String> namesOfAddedItems() {
        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemContainerForCartAndCheckout));
        List<String> addedItemNames = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            addedItemNames.add(items.get(i).findElement(productNameLocator).getText() );
        }
        System.out.println("addedItemNames = " + addedItemNames);
        return addedItemNames;
    }

    public double getPrice() {
        String actualTextPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(productPriceLocator)).getText();
        double actualDigitPrice = Double.parseDouble((actualTextPrice.replace("$", "") ) );
        System.out.println("actualDigitPrice without sign '$' = " + actualDigitPrice);
    return actualDigitPrice;
    }

//    public double getPriceForSpecificItem(List<String> names) {
//        List<String> itemNames = namesOfAddedItems();
//        for (String justOneName : itemNames) {
//
//        }
//        return
//    }

//    public double getPriceForSpecificItem(String itemName) {
//        List<WebElement> items = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemContainer));
//        for (WebElement item : items) {
//            String name = item.findElement(productNameLocator).getText();
//            if (name.equals(itemName)) {
//                String priceText = item.findElement(productPriceLocator).getText().replace("$", "");
//                return Double.parseDouble(priceText);
//            }
//        }
//        throw new RuntimeException(("item with name '" + itemName + "' not found on the page"));
//    }

    public double getPriceByItemName (String itemName) {
        List<WebElement> items = driver.findElements(itemContainerForCartAndCheckout);
        for (WebElement item : items) {
            String name = item.findElement(productNameLocator).getText();
            if (name.equals(itemName)) {
                String priceText = item.findElement(productPriceLocator).getText();
                return Double.parseDouble((priceText.replace("$", "")));
            }
        }
        throw new RuntimeException("item not fund on the page: " + itemName);
    }
}