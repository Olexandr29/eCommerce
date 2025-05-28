
package com.automation.tests.smoke;

import com.automation.tests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;



import static com.automation.pages.TestData.*;

public class LoginTests extends BaseTest {
//    @Epic("Login Tests")
//    @Feature("Successful Login")
//    @Story("Login with Valid Credentials")
//    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void successfulLoginTest() {
        loginPage.enterCredentials(standardUsername, password);
        inventoryPage = loginPage.loginWithValidCredentials();
        Assertions.assertEquals(inventoryPage.getUrl(), expectedInventoryUrl, "the URL is wrong");
        Assertions.assertTrue(inventoryPage.getHeading().equalsIgnoreCase(expectedInventoryTitle), "the title is wrong");
    }

//    @Epic("Login Tests")
//    @Feature("Negative Login")
//    @Story("Login with Locked out user")
//    @Severity(SeverityLevel.CRITICAL)
 @Test
    public void unsuccessfulLoginWithLockedUser() {
     loginPage.enterCredentials(lockedUser, password);
     Assertions.assertTrue(loginPage.loginWithInvalidCredentials().contains(expectedErrMsgForLockedUser), "the error message for locked user is wrong");
 }

}
