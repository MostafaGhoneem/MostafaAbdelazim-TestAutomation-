package base;

import helpers.PropertiesReader;
import helpers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTests {
    private WebDriver driver;

    @BeforeClass
    public void majorSetUp() {
        driver = WebDriverFactory.getDriver();
    }

    @BeforeMethod
    public void minorSetUp() {
        var url = new PropertiesReader("test-configurations")
                .getProperty("base-url");
        driver.get(url);
    }

    @AfterMethod
    public void minorTearDown() {}

    @AfterClass
    public void majorTearDown() {
        WebDriverFactory.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
