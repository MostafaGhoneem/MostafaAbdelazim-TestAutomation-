package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GUIActions {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public GUIActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    public void clickOn(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        WebElement element = driver.findElement(by);
//        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void sendTextTo(By by, String text) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        WebElement element = driver.findElement(by);
//        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    public String getTextFrom(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        WebElement element = driver.findElement(by);
//        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public boolean ensureElementDoesNotExist(By by) {
        List<WebElement> elements = driver.findElements(by);
        return elements.isEmpty();
    }
}
