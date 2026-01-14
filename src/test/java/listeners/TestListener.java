package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.ExtentManager;

public class TestListener implements ITestListener {
	
	
	@Override
	public void onStart(ITestContext context) {
		ExtentManager.getExtent();
	}

    @Override
    public void onTestStart(ITestResult result) {
    	String testName = result.getMethod().getMethodName();
        ExtentManager.startTest(testName);
        ExtentManager.logStep("Executing Testcase: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	ExtentManager.logStep("Test Passed Successfully");
        ExtentManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	String testName = result.getMethod().getMethodName();
    	String failureMessage = result.getThrowable() != null
                ? result.getThrowable().getMessage()
                : "Test failed with unknown error";
        
    	ExtentManager.logFailure(failureMessage,  "Test End: " + testName + " - ❌ Test Failed");
        ExtentManager.removeTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.logSkip("Test Skipped");
        ExtentManager.removeTest();
    }
    
    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getExtent().flush();
    }
}
