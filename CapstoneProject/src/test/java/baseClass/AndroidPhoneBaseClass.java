package baseClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import utils.CommonFunctions;
import utils.ConfigFileReader;
import utils.ReadJSONFile;

public class AndroidPhoneBaseClass {
	
	public ExtentTest test;
	public static ExtentReports report;
	public static AndroidDriver driver;
	ConfigFileReader cfr = new ConfigFileReader();
	String jsonFilePath = cfr.getSpecificUrlProperties("androidPhoneJSONPath");
	String androidAppPath = cfr.getSpecificUrlProperties("androidAppPath");
	String url = cfr.getSpecificUrlProperties("url");
	ReadJSONFile rj = new ReadJSONFile();
	
	@BeforeSuite
	public void beforeSuite() {
		test = CommonFunctions.generateExtentReports("Android Phone");
	}
	
	@AfterSuite
	public void afterClass() {
		CommonFunctions.closeExtentReports();
	}

	@BeforeClass
	public void configureAppLaunch() throws FileNotFoundException, IOException, ParseException {
		String deviceName = rj.getDeviceName(jsonFilePath);
		String platformVersion = rj.getPlatformVersion(jsonFilePath);
		String automationName = rj.getAutomationName(jsonFilePath);
		String platformName = rj.getPlatformName(jsonFilePath);
		File fs = new File(androidAppPath);
		UiAutomator2Options cap = new UiAutomator2Options();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3600);

		try {
			driver = new AndroidDriver(new URL(url), cap);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(cfr.getImplicitWait("implicitWait")));
			test.log(LogStatus.INFO, "Launch MyBank Application on Android Phone.");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Launch MyBank Application failed.");
		}
	}

	@AfterClass
	public void closeTest() {
		driver.close();
		driver.quit();
	}
}
