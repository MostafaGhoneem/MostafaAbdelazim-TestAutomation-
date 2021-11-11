package tests;

import helpers.TestngListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.BaseTests;
import pages.HomePage;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners(TestngListener.class)
public class LoginTests extends BaseTests {

    /**
     * Check login with registered account with valid password
     **/

    @Test(dataProvider = "valid-user-credentials", dataProviderClass = data_providers.DataProviders.class)
    public void testValidLogin(String email, String password) {
        var loginPage = new LoginPage(getDriver());
        loginPage.enterUserCredentials(email, password)
                .login();
        var homePage = new HomePage(getDriver());

        assertEquals(homePage.getSignOutText(), "Sign out");
    }

    /**
     * Check login with registered account with invalid password and unregistered account
     **/

    @Test(dataProvider = "invalid-user-credentials", dataProviderClass = data_providers.DataProviders.class)
    public void testInvalidLogin(String email, String password, String message) {
        var loginPage = new LoginPage(getDriver());
        loginPage
                .enterUserCredentials(email, password)
                .login();
        var errorMessage = loginPage
                .getErrorMessage();
        assertTrue(errorMessage.contains(message));

    }
}
