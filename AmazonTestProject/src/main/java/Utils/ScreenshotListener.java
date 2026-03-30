
package Utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import base.BasePage;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object instance = result.getInstance();
        String name = result.getMethod().getMethodName();
        if (instance instanceof BasePage) {
            BasePage base = (BasePage) instance;
            String path = base.takeScreenshot(name, "FAILED");
            if (path != null) {
                Reporter.log("Screenshot for failure: file://" + path);
                System.out.println("Screenshot captured for failure at: " + path);
            }
        }
    }

    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestSkipped(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}