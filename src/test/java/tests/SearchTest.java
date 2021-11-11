package tests;

import base.BaseTests;
import helpers.ExcelReader;
import helpers.TestngListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import static org.testng.Assert.assertTrue;

/**
 * Checks all Results contains dress text >> it will fail because there is 1 product Blouse
 **/

@Listeners(TestngListener.class)
public class SearchTest extends BaseTests {
    @Test(dataProvider = "search-data", dataProviderClass = data_providers.DataProviders.class)
    public void ValidateSearchResults(String SearchQuery) {
        var credentials = new ExcelReader("user-credentials", "valid credentials").getFirstRow();
        var loginPage = new LoginPage(getDriver());
        loginPage.enterUserCredentials(credentials.get("email").toString(), credentials.get("password").toString()).login();
        var homePage = new HomePage(getDriver());
        var results = homePage.SearchForProduct(SearchQuery).getSearchResults();

        results.forEach(r ->
                assertTrue(r.toLowerCase().contains(SearchQuery))
        );


    }
}
