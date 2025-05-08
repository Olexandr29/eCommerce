package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
public WebDriver driver;
public WebDriverWait wait;

    By usernameField = By.id("user-name");
    By passwordField = By.id("password");
    By loginButton = By.name("login-button");


public LoginPage(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
}

public void enterCredentials(String standardUsername, String password) {
    wait.until(ExpectedConditions.elementToBeClickable(usernameField)).sendKeys(standardUsername);
    wait.until(ExpectedConditions.elementToBeClickable(passwordField)).sendKeys(password);
}

public InventoryPage clickLoginBtn() {
    wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
return new InventoryPage(driver, wait);
}

}