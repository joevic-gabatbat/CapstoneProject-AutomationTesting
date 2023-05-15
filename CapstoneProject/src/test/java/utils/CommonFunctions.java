package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

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
	
}
