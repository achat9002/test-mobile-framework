package tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import utils.DriverUtils;

public class BaseTest implements ITestListener {
	protected AppiumDriver driver;
	private DriverUtils driverUtil;
	private static ExtentReports extent = ExtentManager.getInstance();

	@BeforeSuite
	public void beforeSuite() {
		ExtentManager.createReport();
	}

	@BeforeClass
	public void setUp() {
		driverUtil = new DriverUtils();
		driver = driverUtil.initializeDriver().getDriver();
	}

	@BeforeMethod
	public void initReports(Method m) {
		ExtentTest test = extent.createTest(m.getName());
		ExtentTestManager.setTest(test);
	}

	@AfterClass
	public void closeTestInstance(ITestResult result) {
		ExtentTest test = ExtentTestManager.getTest();

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Failed: " + result.getThrowable());

			// Capture screenshot
			String screenshotPath = captureScreenshot(result.getName());
			test.addScreenCaptureFromPath(screenshotPath);

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Passed");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Skipped");
		}
	}

	@AfterClass
	public void tearDown(ITestResult result) {
		driverUtil.quitDriver();
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
	}

	// Helper method to capture screenshot
	private String captureScreenshot(String testName) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = "test-output/screenshots/" + testName + ".png";
		File destFile = new File(path);
		try {
			Files.createDirectories(destFile.getParentFile().toPath());
			Files.copy(srcFile.toPath(), destFile.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
