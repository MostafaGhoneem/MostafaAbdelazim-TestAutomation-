package data_providers;

import helpers.ExcelReader;
import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "valid-user-credentials")
    public static Object[][] gerValidUserCredentials() {
        return new ExcelReader("user-credentials", "valid credentials").getData();
    }

    @DataProvider(name = "invalid-user-credentials")
    public static Object[][] geInValidUserCredentials() {
        return new ExcelReader("user-credentials", "invalid credentials").getData();
    }
}
