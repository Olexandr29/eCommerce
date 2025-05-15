package com.automation.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.main.main.java.com.automation.pages.CheckoutStep2Page;

public class CheckoutStep1Page {
    WebDriver driver;
    WebDriverWait wait;

    By firstNameField = By.id("first-name");
    By lastNameField = By.id("last-name");
    By zipOrPostCode = By.id("postal-code");
    By continueBtn = By.id("continue");

    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String zipCode = faker.address().zipCode();

    public CheckoutStep1Page(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void enterCheckoutInfo() {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameField)).sendKeys(firstName);
        wait.until(ExpectedConditions.elementToBeClickable(lastNameField)).sendKeys(lastName);
        wait.until(ExpectedConditions.elementToBeClickable(zipOrPostCode)).sendKeys(zipCode);
    }

    public CheckoutStep2Page clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
        return new CheckoutStep2Page(driver, wait);
    }

}