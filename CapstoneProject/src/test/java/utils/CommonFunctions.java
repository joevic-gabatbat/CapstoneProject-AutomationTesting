package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;

public class CommonFunctions {
	
	static AndroidDriver driver;
	static ExtentTest test;
	static ExtentReports report;
	
	// This will get the date and time in yyyyMMddhhmmss format
	static String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	
	// This will generate the html for reports
	public static ExtentTest generateExtentReports(String executionDevice) {
		File file = new File(executionDevice + " - " + dateName);
		file.mkdir();
		report = new ExtentReports(executionDevice + " - " + dateName + "/ExecutionReport.html");
		test = report.startTest("Capstone MyBank Application : " + executionDevice);
		return test;
	}
	
	// This will close Extent and flush reports
	public static void closeExtentReports() {
		report.endTest(test);
		report.flush();
	}
	
	// This will capture a screenshot of the defect and log a status fail and attach the screenshot as proof
	// Still under configuration
	public static String captureFailTestScreenShots() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/MidTermFailedTestsScreenshots/" + "ScreenShots" + dateName
				+ ".png";
		test.log(LogStatus.FAIL, "Test Failed" + test.addScreenCapture(destination));
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
}
