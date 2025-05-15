package com.automation.tests.smoke;

import com.automation.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import static com.automation.pages.TestData.*;

public class LoginTests extends BaseTest {

    @Test
    public void successfulLoginTest() {
    loginPage.enterCredentials(standardUsername, password);
    inventoryPage = loginPage.loginWithValidCredentials();
        Assert.assertEquals("the URL is wrong", expectedInventoryUrl, inventoryPage.getUrl());
        Assert.assertTrue("the title is wrong", inventoryPage.getHeading().equalsIgnoreCase(expectedInventoryTitle));
    }

    @Test
    public void unsuccessfulLoginWithLockedUser() {
    loginPage.enterCredentials(lockedUser, password);
    Assert.assertTrue("the error message for locked user is wrong",
            loginPage.loginWithInvalidCredentials().contains(expectedErrMsgForLockedUser));
        }

}