package com.automation.tests.negativeTests;

import com.automation.tests.BaseTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static com.automation.pages.TestData.*;

@Epic("Security and Validation")
@Tag("Regression") @Tag("Negative")
public class NegativeTests extends BaseTest {

    @Test
    @Feature("SQL injection protection")
    @Story("Reject login with SQL payload")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-028")
    public void enteringSQLinjectionInUsername() {
        loginPage.enterCredentials("admin' OR '1'='1", password);
        loginPage.clickLoginBtn();
        Assertions.assertEquals(loginPage.getUrl(), driver.getCurrentUrl(), "Login should be rejected and user shouldn't be redirected to other page");
        Assertions.assertFalse(loginPage.getErrorMessage().isEmpty(), "the error message is not displayed");
    }

    @Test
    @Feature("Input length limits")
    @Story("Reject too long username")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-029")
    public void longValueInUsernameField() {
        loginPage.enterCredentials(fiveHundredSymbols, password);
        loginPage.clickLoginBtn();
        Assertions.assertEquals(loginPage.getUrl(), driver.getCurrentUrl(), "Login should be rejected and user shouldn't be redirected to other page");
        Assertions.assertFalse(loginPage.getErrorMessage().isEmpty(), "the error message is not displayed");
    }

    @Test
    @Feature("Whitespace handling")
    @Story("Login fails with trimmed username")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-030")
    public void loginWithSpacesInUsernameBeforeAndAfter() {
        loginPage.enterCredentials(standardUsernameButWithSpacesBeforeAndAfter, password);
        loginPage.clickLoginBtn();
        Assertions.assertEquals(loginPage.getUrl(), driver.getCurrentUrl(), "Login should be rejected and user shouldn't be redirected to other page");
        Assertions.assertFalse(loginPage.getErrorMessage().isEmpty(), "the error message is not displayed");
    }

}