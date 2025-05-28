package com.automation.tests.negativeTests;

import com.automation.tests.BaseTest;
import org.junit.jupiter.api.*;

import static com.automation.pages.TestData.*;

public class NegativeTests extends BaseTest {

    @Test
    public void enteringSQLinjectionInUsername() {
        loginPage.enterCredentials("admin' OR '1'='1", password);
        loginPage.clickLoginBtn();
        Assertions.assertEquals(loginPage.getUrl(), driver.getCurrentUrl(), "Login should be rejected and user shouldn't be redirected to other page");
        Assertions.assertFalse(loginPage.getErrorMessage().isEmpty(), "the error message is not displayed");
    }

    @Test
    public void longValueInUsernameField() {
        loginPage.enterCredentials(fiveHundredSymbols, password);
        loginPage.clickLoginBtn();
        Assertions.assertEquals(loginPage.getUrl(), driver.getCurrentUrl(), "Login should be rejected and user shouldn't be redirected to other page");
        Assertions.assertFalse(loginPage.getErrorMessage().isEmpty(), "the error message is not displayed");
    }

    @Test
    public void loginWithSpacesInUsernameBeforeAndAfter() {
        loginPage.enterCredentials(standardUsernameButWithSpacesBeforeAndAfter, password);
        loginPage.clickLoginBtn();
        Assertions.assertEquals(loginPage.getUrl(), driver.getCurrentUrl(), "Login should be rejected and user shouldn't be redirected to other page");
        Assertions.assertFalse(loginPage.getErrorMessage().isEmpty(), "the error message is not displayed");
    }

}