package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InventoryPage extends BasePage {

    By headingLocator = By.className("title");
    By listOfProductsContainer = By.className("inventory_list");
    By productItemContainer = By.className("inventory_item");
    By burgerMenuBtn = By.id("react-burger-menu-btn");
    By logOutBtn = By.id("logout_sidebar_link");
    By addToCartBtn = By.xpath("//button[text()='Add to cart']");
    By shoppingCartLink = By.className("shopping_cart_link");
    By sortDropdown = By.className("product_sort_container");
    By removeBtnLocator = By.xpath("//button[text()='Remove']");

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

    public CartPage openCartByClickOnCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink)).click();
        return new CartPage(driver, wait);
    }

    public void sortByPriceFromLowToHigh() {
        WebElement dropdown = driver.findElement(sortDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText("Price (low to high)");
    }

    public void sortByNameFromZtoA() {
        WebElement dropdown = driver.findElement(sortDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText("Name (Z to A)");
    }

    public InventoryProductDetails openProductDetailList() {
        WebElement productName = wait.until(ExpectedConditions.elementToBeClickable(productNameLocator));
        productName.click();
        return new InventoryProductDetails(driver, wait);
    }

    public void add3ItemsToTheCart() {
    List<WebElement> allAddToCartButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addToCartBtn));
    for (int i = 1; i < 4; i++) {
        allAddToCartButtons.get(i).click();
         }
    }

    public boolean isInventoryContainerVisible() {
        return driver.findElement(listOfProductsContainer).isDisplayed();
    }

    public boolean isBurgerMenuVisible() {
        return driver.findElement(burgerMenuBtn).isDisplayed();
    }

    public boolean isAddToCartButtonClickable() {
        WebElement addButton = driver.findElement(addToCartBtn);
        return addButton.isDisplayed() && addButton.isEnabled();
    }

    public void addSpecificItemToCart(String itemName) {
        WebElement itemContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='inventory_item' and .//div[text()='" + itemName + "']]")));
        WebElement button = itemContainer.findElement(By.tagName("button"));
        button.click();
    }

    public String getTextOfTheButton(String itemName) {
        WebElement itemContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='inventory_item' and .//div[text()='" + itemName + "']]")));
        WebElement button = itemContainer.findElement(By.tagName("button"));
        return button.getText();
    }

    public String getButtonClass(String itemName) {
        WebElement itemContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='inventory_item' and .//div[text()='" + itemName + "']]")));
        WebElement button = itemContainer.findElement(By.tagName("button"));
        return button.getCssValue("color");
    }

    public void removeAddedItemFromCartViaInventoryPage() {
        WebElement removeBtn = wait.until(ExpectedConditions.elementToBeClickable(removeBtnLocator));
        removeBtn.click();
    }
}