package listeners;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

import utils.ExtentManager;
import utils.Log;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {
	
	private static final Logger log = Log.getLogger(TestListener.class);
	
	@Override
	public void onStart(ITestContext context) {
		ExtentManager.getExtent();
	}

    @Override
    public void onTestStart(ITestResult result) {
    	log.info("Executing Testcase: " + result.getMethod().getMethodName());
        ExtentTest test = ExtentManager.getExtent().createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	if (ExtentManager.getTest() == null) {
            return; // DataProvider failure safety
        }
        String path = ScreenshotUtil.capture(result.getMethod().getMethodName());
        ExtentManager.getTest().fail(result.getThrowable());
        if (path != null) {
            try {
                ExtentManager.getTest().addScreenCaptureFromPath(path);
            } catch (Exception ignored) {}
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.flush();
    }
}
