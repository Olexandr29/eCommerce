package com.automation.tests.negativeTests;

import com.automation.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import static com.automation.pages.TestData.*;

public class NegativeTests extends BaseTest {

    @Test
    public void enteringSQLinjectionInUsername() {
        loginPage.enterCredentials("admin' OR '1'='1", password);
        loginPage.clickLoginBtn();
        Assert.assertEquals("Login should be rejected and user shouldn't be redirected to other page",
                loginPage.getUrl(), driver.getCurrentUrl());
        Assert.assertFalse("the error message is not displayed",
                loginPage.getErrorMessage().isEmpty());
    }

    @Test
    public void longValueInUsernameField() {
        loginPage.enterCredentials(fiveHundredSymbols,password);
        loginPage.clickLoginBtn();
        Assert.assertEquals("Login should be rejected and user shouldn't be redirected to other page",
                loginPage.getUrl(), driver.getCurrentUrl());
        Assert.assertFalse("the error message is not displayed",
                loginPage.getErrorMessage().isEmpty());
    }

    @Test
    public void loginWithSpacesInUsernameBeforeAndAfter() {
        loginPage.enterCredentials(standardUsernameButWithSpacesBeforeAndAfter, password);
        loginPage.clickLoginBtn();
        Assert.assertEquals("Login should be rejected and user shouldn't be redirected to other page",
                loginPage.getUrl(), driver.getCurrentUrl());
        Assert.assertFalse("the error message is not displayed",
                loginPage.getErrorMessage().isEmpty());
    }

}