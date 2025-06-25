package com.automation.tests.smoke;

import com.automation.tests.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static com.automation.pages.TestData.*;

@Epic("Authentication")
@Tag("Regression") @Tag("Smoke")
public class SmokeLoginTests extends BaseTest {

    @Feature("Login")
    @Story("Login with Valid Credentials")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("TC-001")
    @DisplayName("Successful login with valid credentials")
    @Test
    public void successfulLoginTest() {
        loginPage.enterCredentials(standardUsername, password);
        inventoryPage = loginPage.loginWithValidCredentials();
        Assertions.assertEquals(inventoryPage.getUrl(), expectedInventoryUrl, "the URL is wrong");
        Assertions.assertTrue(inventoryPage.getHeading().equalsIgnoreCase(expectedInventoryTitle), "the title is wrong");
    }

    @Feature("Login")
    @Story("Login with Locked user")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-002")
    @DisplayName("Unsuccessful login with locked user")
    @Test
    public void unsuccessfulLoginWithLockedUser() {
     loginPage.enterCredentials(lockedUser, password);
     Assertions.assertTrue(loginPage.loginWithInvalidCredentials().contains(expectedErrMsgForLockedUser), "the error message for locked user is wrong");
    }

}
