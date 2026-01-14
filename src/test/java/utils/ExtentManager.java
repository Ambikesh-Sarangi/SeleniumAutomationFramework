package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import base.BaseClass;

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

	public static ExtentTest startTest(String testName) {
		ExtentTest extentTest = getExtent().createTest(testName);
		test.set(extentTest);
		return extentTest;
	}

	public static ExtentTest getTest() {
		return test.get();
	}

	public synchronized static void endTest() {
		getExtent().flush();
	}

	public static String getTestName() {
		ExtentTest currentTest = getTest();
		if (currentTest != null) {
			return currentTest.getModel().getName();
		} else {
			return "No Test is currently active for this thread";
		}
	}

	public static void logStep(String logMessage) {
		if (getTest() != null) {
			getTest().info(logMessage);
		} else {
			System.out.println("[Extent] " + logMessage);
		}
	}

	public static void logStepWithScreenshot(String logMessage, String screenshotMessage) {
		if (getTest() != null) {
			getTest().pass(logMessage);
			attachScreenshot(screenshotMessage);
		}
	}

	public static void logFailure(String logMessage, String screenshotMessage) {
		if (getTest() != null) {
			getTest().fail(logMessage);
			attachScreenshot(screenshotMessage);
		} else {
			System.out.println("[Extent] " + logMessage);
		}
	}

	public static void logSkip(String logMessage) {
		if (getTest() != null) {
			getTest().skip(logMessage);
		} else {
			System.out.println("[Extent] " + logMessage);
		}
	}

	public synchronized static String takeScreenshot(String screenshotName) {
		TakesScreenshot ts = (TakesScreenshot) BaseClass.getDriver();
		File src = ts.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyy-MM-dd__HH-mm-ss").format(new Date());
		String destPath = System.getProperty("user.dir") + "/screenshots/" + screenshotName + "_" + timeStamp+".png";
		File finalPath = new File(destPath); 
		try {
			FileUtils.copyFile(src, finalPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String base64Format = convertToBase64(src);
		return base64Format;
	}

	public static String convertToBase64(File screenshotFile) {
		String base64Format = "";
		byte[] fileContent;
		try {
			fileContent = FileUtils.readFileToByteArray(screenshotFile);
			base64Format = Base64.getEncoder().encodeToString(fileContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return base64Format;
	}

	public synchronized static void attachScreenshot(String message) {
		if (getTest() == null)
			return;
		try {
			String screenshotBase64 = takeScreenshot(getTestName());
			getTest().info(message, com.aventstack.extentreports.MediaEntityBuilder
					.createScreenCaptureFromBase64String(screenshotBase64).build());
		} catch (Exception e) {
			getTest().fail("Failed to attach screenshot: " + message);
			e.printStackTrace();
		}
	}

	public static void removeTest() {
		test.remove();
	}
}
