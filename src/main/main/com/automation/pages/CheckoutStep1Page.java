package com.automation.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutStep1Page extends BasePage {

    By firstNameField = By.id("first-name");
    By lastNameField = By.id("last-name");
    By zipOrPostCode = By.id("postal-code");
    By continueBtn = By.id("continue");

    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String zipCode = faker.address().zipCode();

    public CheckoutStep1Page(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
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