package helpers;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;
import utilities.MyLogger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestngListener implements ISuiteListener, ITestListener {

    // ------------------> ISuiteListener

    @Override
    public void onStart(ISuite suite) {
        MyLogger.info(suite.getName(), " ==============================================> " +
                "Started <==============================================");
    }

    @Override
    public void onFinish(ISuite suite) {
        MyLogger.info(suite.getName(), " ==============================================> " +
                "Finished <==============================================");
    }


    // ------------------> ITestListener

    @Override
    public void onTestStart(ITestResult result) {
        MyLogger.info(result.getName(), " ******************************** " +
                "Started ********************************");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        MyLogger.info(result.getName(), " ******************************** " +
                "Succeeded ********************************");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        MyLogger.warning(result.getName(), " ******************************** " +
                "Skipped ********************************");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        MyLogger.warning(result.getName(), " ******************************** " +
                "Failed ********************************");

        var date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        var srcFile = ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
        MyLogger.info("TakeScreenshot", "Taking screenshot for " + result.getName());
        try {
            FileUtils.copyFile(srcFile, new File("output/screenshots/"+ result.getName() + date +".png"));
            Allure.addAttachment("Screenshot", FileUtils.openInputStream(srcFile));
        } catch (IOException e) {
            MyLogger.warning("TakeScreenshot", "Can't take screenshot for "+ result.getName()
                    +" --------> "+ e);
            e.printStackTrace();
        }
    }
}
