package helpers;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import utilities.MyLogger;

public class SeleniumEventListener implements WebDriverEventListener {

    @Override
    public void beforeAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {
        var msg = "Navigating to " + s;
        MyLogger.info(this.getClass().getSimpleName(), msg);
        Allure.getLifecycle().startStep(s, new StepResult().setName(msg).setStatus(Status.FAILED));
    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {
        MyLogger.info(this.getClass().getSimpleName(), "Navigated to "+ s +" successfully");
        Allure.getLifecycle().updateStep(s, step -> step.setStatus(Status.PASSED));
        Allure.getLifecycle().stopStep(s);
    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        var msg = "Finding element [" + by + "]";
        MyLogger.info(this.getClass().getSimpleName(), msg);
        Allure.getLifecycle().startStep(by.toString(), new StepResult().setName(msg).setStatus(Status.FAILED));
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        MyLogger.info(this.getClass().getSimpleName(), "Successfully found element [" + by + "]");
        Allure.getLifecycle().updateStep(by.toString(), step -> step.setStatus(Status.PASSED));
        Allure.getLifecycle().stopStep(by.toString());
    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        var msg = "Clicking on element [" + webElement + "]";
        MyLogger.info(this.getClass().getSimpleName(), msg);
        Allure.getLifecycle().startStep(webElement.toString(), new StepResult().setName(msg).setStatus(Status.FAILED));
    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        MyLogger.info(this.getClass().getSimpleName(), "Successfully clicked on element [" + webElement + "]");
        Allure.getLifecycle().updateStep(webElement.toString(), step -> step.setStatus(Status.PASSED));
        Allure.getLifecycle().stopStep(webElement.toString());
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        var msg = "Changing value of element [" + webElement + "]";
        MyLogger.info(this.getClass().getSimpleName(), msg);
        Allure.getLifecycle().startStep(webElement.toString(), new StepResult().setName(msg).setStatus(Status.FAILED));
    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        MyLogger.info(this.getClass().getSimpleName(),
                "Successfully changed the value of element [" + webElement + "]");
        Allure.getLifecycle().updateStep(webElement.toString(), step -> step.setStatus(Status.PASSED));
        Allure.getLifecycle().stopStep(webElement.toString());
    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {

    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {

    }

    @Override
    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    @Override
    public void beforeGetText(WebElement webElement, WebDriver webDriver) {
        var msg = "Getting text of element [" + webElement + "]";
        MyLogger.info(this.getClass().getSimpleName(), msg);
        Allure.getLifecycle().startStep(webElement.toString(), new StepResult().setName(msg).setStatus(Status.FAILED));
    }

    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {
        MyLogger.info(this.getClass().getSimpleName(),
                "Successfully got the text of the element [" + webElement + "]");
        Allure.getLifecycle().updateStep(webElement.toString(), step -> step.setStatus(Status.PASSED));
        Allure.getLifecycle().stopStep(webElement.toString());
    }
}
