package baseClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.MobileCapabilityType;
import utils.CommonFunctions;
import utils.ConfigFileReader;
import utils.ReadJSONFile;

public class PhoneiOSBaseClass {
	
	public ExtentTest test;
	public static ExtentReports report;
	public static IOSDriver driver;
	ConfigFileReader cfr = new ConfigFileReader();
	String jsonFilePath = cfr.getSpecificUrlProperties("iOSPhoneJSONPath");
	String iOSAppPath = cfr.getSpecificUrlProperties("iOSAppPath");
	String url = cfr.getSpecificUrlProperties("url");
	ReadJSONFile rj = new ReadJSONFile();
	
	@BeforeSuite
	public void beforeSuite() {
		test = CommonFunctions.generateExtentReports("iOS iPhone");
	}
	
	@AfterSuite
	public void afterSuite() {
		CommonFunctions.closeExtentReports();
	}
	
	@BeforeClass
	public void configureAppLaunch() throws FileNotFoundException, IOException, ParseException {
		String deviceName = rj.getDeviceName(jsonFilePath);
		String platformVersion = rj.getPlatformVersion(jsonFilePath);
		String automationName = rj.getAutomationName(jsonFilePath);
		String udID = rj.getUDID(jsonFilePath);
		File fs = new File(iOSAppPath);
		XCUITestOptions cap = new XCUITestOptions();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
		cap.setUdid(udID);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3600);

		try {
			driver = new IOSDriver(new URL(url), cap);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(cfr.getImplicitWait("implicitWait")));
			test.log(LogStatus.INFO, "Launch MyBank Application on iOS Phone.");
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
	
	public void generateScreenShots(String info, String status) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/iOSTestScreenshots/" + "ScreenShots" + dateName + ".png";
		if(status == "PASS") {
			test.log(LogStatus.PASS, info + test.addScreenCapture(destination));
		}else {
			test.log(LogStatus.FAIL, info + test.addScreenCapture(destination));
		}
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
	}
}
