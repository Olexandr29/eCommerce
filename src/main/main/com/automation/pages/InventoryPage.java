package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InventoryPage extends BasePage {

    By headingLocator = By.className("title");
    By productItemContainer = By.className("inventory_item");
    By productNameLocator = By.className("inventory_item_name");
    By productPriceLocator = By.className("inventory_item_price");
    By burgerMenuBtn = By.id("react-burger-menu-btn");
    By logOutBtn = By.id("logout_sidebar_link");
    By addToCartBtn = By.xpath("//button[text()='Add to cart']");
    By shoppingCartBadge = By.className("shopping_cart_badge");
    By shoppingCartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String getHeading() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(headingLocator));
    return driver.findElement(headingLocator).getText();
    }

    public boolean displayedMoreThanOneProduct() {
        boolean moreThan1Product = false;
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productItemContainer));
        if (products.size() > 1) {
            System.out.println("products.size = " + products.size());
            moreThan1Product = true;
        }
        return moreThan1Product;
    }

    public boolean areProductsContainNameAndPrice() {
        boolean allProductsContainNameAndPrice = true;
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productItemContainer));
        for (WebElement product : products) {
            if (!product.findElement(productNameLocator).isDisplayed()
                    || !product.findElement(productPriceLocator).isDisplayed()) {
                System.out.println("the product does not have name or/and price, it has just = " + product.getText());
                allProductsContainNameAndPrice = false;
            }
        }
        return allProductsContainNameAndPrice;
    }

    public LoginPage logOutAction() {
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenuBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logOutBtn)).click();
       return new LoginPage(driver, wait);
    }

    public void addToCartAction() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
    }

    public int getItemAmount() {
        String actualText = wait.until(ExpectedConditions.elementToBeClickable(shoppingCartBadge)).getText();
        int actualAmount = Integer.parseInt(actualText);
        return actualAmount;
    }

    public CartPage openCartByClickOnCartBadge() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartBadge)).click();
        return new CartPage(driver, wait);
    }

    public CartPage openCartByClickOnCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink)).click();
        return new CartPage(driver, wait);
    }

}