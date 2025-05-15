package com.automation.tests.sanity;

import com.automation.tests.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import static com.automation.pages.TestData.*;

public class SanityLoginTests extends BaseTest {

    @Test
    public void successfulLoginAsPerformanceGlitchUser() {
    loginPage.enterCredentials(performanceUser, password);
        inventoryPage = loginPage.loginWithValidCredentials();
        Assert.assertEquals("the URL is wrong", expectedInventoryUrl, inventoryPage.getUrl());
        Assert.assertTrue("the title is wrong", inventoryPage.getHeading().equalsIgnoreCase(expectedInventoryTitle));
    }

    @Test
    public void unsuccessfulLoginWithEmptyFields() {
    Assert.assertTrue("the error message for Login with empty fields is wrong",
            loginPage.loginWithoutCredentials().contains(expectedErrMsgForLoginWithEmptyFields));
    }

    @Test
    public void unsuccessfulLoginWithNonExistentUser() {
    Assert.assertTrue("the error message for non-existent user is wrong",
            loginPage.loginWithNonExistingCredentials().contains(expectedErrMsgForNonExistentUser));
    }

}