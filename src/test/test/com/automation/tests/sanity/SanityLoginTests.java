package com.automation.tests.sanity;

import com.automation.tests.BaseTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import static com.automation.pages.TestData.*;

public class SanityLoginTests extends BaseTest {

    @Test
    public void successfulLoginAsPerformanceGlitchUser() {
        loginPage.enterCredentials(performanceUser, password);
        inventoryPage = loginPage.loginWithValidCredentials();
        Assertions.assertEquals(expectedInventoryUrl, inventoryPage.getUrl(), "the URL is wrong");
        Assertions.assertTrue(inventoryPage.getHeading().equalsIgnoreCase(expectedInventoryTitle), "the title is wrong");
    }

    @Test
    public void unsuccessfulLoginWithEmptyFields() {
        Assertions.assertTrue(loginPage.loginWithoutCredentials().contains(expectedErrMsgForLoginWithEmptyFields), "the error message for Login with empty fields is wrong");
    }

    @Test
    public void unsuccessfulLoginWithNonExistentUser() {
        Assertions.assertTrue(loginPage.loginWithNonExistingCredentials().contains(expectedErrMsgForNonExistentUser), "the error message for non-existent user is wrong");
    }

}