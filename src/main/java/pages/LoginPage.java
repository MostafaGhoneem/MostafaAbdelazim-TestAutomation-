package pages;

import helpers.GUIActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final GUIActions guiActions;
    private final By signIn = By.className("login");
    private final By textFieldEmail = By.id("email");
    private final By textFieldPassword = By.id("passwd");
    private final By submitLogin = By.id("SubmitLogin");
    private final By errorLabel = By.cssSelector(".alert.alert-danger");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        guiActions = new GUIActions(driver);
    }

    public LoginPage enterUserCredentials(String email, String password) {
        guiActions.clickOn(signIn);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        guiActions.sendTextTo(textFieldEmail, email);
        guiActions.sendTextTo(textFieldPassword, password);
        return new LoginPage(driver);
    }

    public String getErrorMessage() {
        return guiActions.getTextFrom(errorLabel);
    }

    public LoginPage login() {
        guiActions.clickOn(submitLogin);
        return new LoginPage(driver);
    }
}
