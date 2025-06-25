package com.automation.tests.sanity;

import com.automation.tests.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static com.automation.pages.TestData.*;

@Epic("Authentication")
@Feature("Login")
@Tag("Regression") @Tag("Sanity")
public class SanityLoginTests extends BaseTest {

    @Test
    @Story("Performance_glitch_user can login successfully")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("TC-006")
    @DisplayName("Successful login as performance glitch user")
    public void successfulLoginAsPerformanceGlitchUser() {
        loginPage.enterCredentials(performanceUser, password);
        inventoryPage = loginPage.loginWithValidCredentials();
        Assertions.assertEquals(expectedInventoryUrl, inventoryPage.getUrl(), "the URL is wrong");
        Assertions.assertTrue(inventoryPage.getHeading().equalsIgnoreCase(expectedInventoryTitle), "the title is wrong");
    }

    @Test
    @Story("Error displayed for empty login fields")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-007")
    @DisplayName("Unsuccessful login with empty fields")
    public void unsuccessfulLoginWithEmptyFields() {
        Assertions.assertTrue(loginPage.loginWithoutCredentials().contains(expectedErrMsgForLoginWithEmptyFields), "the error message for Login with empty fields is wrong");
    }

    @Test
    @Story("Error displayed for invalid login credentials")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-008")
    @DisplayName("Unsuccessful login with non-existent user")
    public void unsuccessfulLoginWithNonExistentUser() {
        Assertions.assertTrue(loginPage.loginWithNonExistingCredentials().contains(expectedErrMsgForNonExistentUser), "the error message for non-existent user is wrong");
    }

}