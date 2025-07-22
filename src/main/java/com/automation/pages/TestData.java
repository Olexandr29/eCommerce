package com.automation.pages;

public class TestData {
public static final String url = "https://www.saucedemo.com/";
public static final String standardUsername = "standard_user";
public static final String lockedUser = "locked_out_user";
public static final String performanceUser = "performance_glitch_user";
public static final String password = "secret_sauce";
public static final String expectedInventoryTitle = "Products";
public static final String expectedInventoryUrl = url + "inventory.html";
public static final String expectedErrMsgForLoginWithEmptyFields = "Username is required";
public static final String expectedErrMsgForLockedUser = "Sorry, this user has been locked out.";
public static final String fakeUser = "fake_user";
public static final String fakePassword = "fake_password";
public static final String expectedErrMsgForNonExistentUser = "Username and password do not match any user";
public static final String expectedCartPageUrl = url + "cart.html";
public static final String expectedCheckoutStep1Url = url + "checkout-step-one.html";
public static final String expectedCheckoutStep2Url = url + "checkout-step-two.html";
public static final String expectedCheckoutStep3Confirmation = "Thank you for your order!";
public static final String expectedCheckoutStep1NameIsEmpty = "First Name is required";
public static final String specificProductName = "Sauce Labs Onesie";
public static final String fiveHundredSymbols = "a".repeat(500);
public static final String standardUsernameButWithSpacesBeforeAndAfter = " standard_user ";
public static final String lastItemName = "Test.allTheThings() T-Shirt (Red)";
}
