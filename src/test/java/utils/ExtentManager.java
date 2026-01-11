package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static synchronized ExtentReports getExtent() {
        if (extent == null) {
        	
        	String reportDir = System.getProperty("user.dir") + "/reports";
        	
        	new java.io.File(reportDir).mkdirs();

            String reportPath = reportDir + "/ExtentReport.html";

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

            reporter.config().setReportName("Automation Test Report");
            reporter.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static synchronized void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
