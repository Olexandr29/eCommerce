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
    By errorMessageLocator = By.xpath("//h3[@data-test='error']");

public LoginPage(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
}

public void enterCredentials(String username, String password) {
    wait.until(ExpectedConditions.elementToBeClickable(usernameField)).sendKeys(username);
    wait.until(ExpectedConditions.elementToBeClickable(passwordField)).sendKeys(password);
}

public InventoryPage loginWitValidCredentials() {
    wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
return new InventoryPage(driver, wait);
}

public String loginWithInvalidCredentials() {
    wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    System.out.println("errorMessageLocator.getText() = " + wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator)).getText());
    return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator)).getText();
}


}