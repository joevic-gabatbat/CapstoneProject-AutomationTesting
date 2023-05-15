package iOSPhoneTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import baseClass.PhoneiOSBaseClass;
import io.appium.java_client.AppiumBy;
import utils.ConfigFileReader;
import utils.ReadDataFromExcel;

public class PhoneiOS_UI_Test extends PhoneiOSBaseClass {

	ConfigFileReader cfr = new ConfigFileReader();
	String excelPath = cfr.getSpecificUrlProperties("excelFilePath");
	String sheetName = "TestData - iOS";
	ReadDataFromExcel excel = new ReadDataFromExcel();

	@Test(priority = 0, enabled = true)
	public void test_UI_01() throws IOException {
		try {
			WebElement ele = driver.findElement(AppiumBy.accessibilityId("Web View"));
			Map<String, Object> params = new HashMap<>();
			params.put("element", ((RemoteWebElement) ele).getId());
			params.put("direction", "down");
			driver.executeScript("mobile:scroll", params);
			List<WebElement> getOptions = driver.findElements(By.xpath("//XCUIElementTypeButton[@name='chevron']"));
			Reporter.log("Total number of options: " + getOptions.size());
			Assert.assertTrue(true);
			generateScreenShots("Total number of options: " + getOptions.size(), "PASS");
		} catch (Exception e) {
			e.printStackTrace();
			generateScreenShots("Test fail.", "FAIL");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 1, enabled = true)
	public void test_UI_02() throws MalformedURLException {
		try {
			driver.findElement(AppiumBy.accessibilityId("Activity Indicators")).click();
			WebElement activityIndicatorTitle = driver
					.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Activity Indicators']"));
			if (activityIndicatorTitle.isDisplayed()) {
				Assert.assertTrue(true);
				test.log(LogStatus.PASS, "Test successful with title Activity Indicators");
			} else {
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Test fail. Title is not present.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 2, enabled = true)
	public void test_UI_03() throws MalformedURLException {
		try {
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			driver.findElement(AppiumBy.accessibilityId("Date Picker")).click();
			driver.findElements(By.xpath("//XCUIElementTypeButton")).get(1).click();
			driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='18']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='PopoverDismissRegion']")).click();
			driver.findElements(By.xpath("//XCUIElementTypeButton")).get(3).click();
			String hour = excel.getDataFromExcel(excelPath, sheetName, 1, "UI_02_Hour");
			String minutes = excel.getDataFromExcel(excelPath, sheetName, 1, "UI_02_Minutes");
			String ampm = excel.getDataFromExcel(excelPath, sheetName, 1, "UI_02_AMorPM");
			driver.findElements(By.xpath(("//XCUIElementTypePickerWheel"))).get(2).sendKeys(ampm);
			driver.findElements(By.xpath(("//XCUIElementTypePickerWheel"))).get(0).sendKeys(hour);
			driver.findElements(By.xpath(("//XCUIElementTypePickerWheel"))).get(1).sendKeys(minutes);
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='PopoverDismissRegion']")).click();
			String dateAndTime = driver.findElements(By.xpath("//XCUIElementTypeStaticText")).get(1)
					.getAttribute("value");
			if (dateAndTime == "June 18, 2023 at 7:46 PM") {
				test.log(LogStatus.PASS, "Test successful for Date Picker.");
				Assert.assertTrue(true);
			} else {
				generateScreenShots("Test fail for Date Picker. Actual date and time: " + dateAndTime, "FAIL");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 3, enabled = true)
	public void test_UI_04() throws MalformedURLException {
		try {
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			driver.findElement(AppiumBy.accessibilityId("Image View")).click();
			Thread.sleep(3000);
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "Test successful for Image View.");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 4, enabled = true)
	public void test_UI_05() throws MalformedURLException {
		try {
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			driver.findElement(AppiumBy.accessibilityId("Page Control")).click();
			WebElement indicatorDots = driver.findElement(By.xpath("//XCUIElementTypePageIndicator"));
			indicatorDots.click();
			indicatorDots.click();
			Assert.assertTrue(true);
			generateScreenShots("Test successful for Page Control.", "PASS");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 5, enabled = true)
	public void test_UI_06() throws MalformedURLException {
		try {
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			driver.findElement(AppiumBy.accessibilityId("Picker View")).click();
			driver.findElement(AppiumBy.accessibilityId("Red color component value")).sendKeys("255");
			driver.findElement(AppiumBy.accessibilityId("Green color component value")).sendKeys("0");
			driver.findElement(AppiumBy.accessibilityId("Blue color component value")).sendKeys("0");
			Assert.assertTrue(true);
			generateScreenShots("Test successful for Picker View, changed the color to red.", "PASS");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 6, enabled = true)
	public void test_UI_07() throws MalformedURLException {
		try {
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			driver.findElement(AppiumBy.accessibilityId("Progress Views")).click();
			String initialProgressIndicator = driver
					.findElement(By.xpath("(//XCUIElementTypeProgressIndicator[@name='Progress'])[1]"))
					.getAttribute("value");
			if (initialProgressIndicator != "100%") {
				Thread.sleep(10000);
			}
			String currentProgressIndicator = driver
					.findElement(By.xpath("(//XCUIElementTypeProgressIndicator[@name='Progress'])[1]"))
					.getAttribute("value");
			Assert.assertEquals(currentProgressIndicator, "100%");
			generateScreenShots("Test successful for Progress Views, wait 10 seconds for bar to reach 100%", "PASS");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 7, enabled = true)
	public void test_UI_08() throws MalformedURLException {
		try {
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			driver.findElement(AppiumBy.accessibilityId("Search")).click();
			driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Default']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Scope Two']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Search']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Custom']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeSearchField")).click();
			driver.findElement(By.xpath("//XCUIElementTypeKey[@name='T']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeKey[@name='e']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeKey[@name='s']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeKey[@name='t']")).click();
			String searchFieldValue = driver.findElement(By.xpath("//XCUIElementTypeSearchField"))
					.getAttribute("value");
			Assert.assertEquals(searchFieldValue, "Test");
			generateScreenShots("Test successful for Search, current value for search field: " + searchFieldValue,
					"PASS");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 8, enabled = true)
	public void test_UI_09() throws MalformedURLException {
		try {
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Search']")).click();
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			driver.findElement(AppiumBy.accessibilityId("Segmented Controls")).click();
			driver.findElement(By.xpath("(//XCUIElementTypeButton[@name='Tools'])[1]")).click();
			driver.findElement(By.xpath("(//XCUIElementTypeButton[@name='Check'])[2]")).click();
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Gift']")).click();
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "Test successful for Segmented Controls.");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 9, enabled = true)
	public void test_UI_10() throws MalformedURLException {
		try {
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			driver.findElement(AppiumBy.accessibilityId("Sliders")).click();
			driver.findElement(By.xpath("(//XCUIElementTypeCell)[1]/XCUIElementTypeSlider")).sendKeys("0%");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//XCUIElementTypeCell)[2]/XCUIElementTypeSlider")).sendKeys("1");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//XCUIElementTypeCell)[3]/XCUIElementTypeSlider")).sendKeys("0.51");
			String defaultSlider = driver.findElement(By.xpath("(//XCUIElementTypeCell)[1]/XCUIElementTypeSlider"))
					.getAttribute("value");
			String tintedSlider = driver.findElement(By.xpath("(//XCUIElementTypeCell)[2]/XCUIElementTypeSlider"))
					.getAttribute("value");
			String customSlider = driver.findElement(By.xpath("(//XCUIElementTypeCell)[3]/XCUIElementTypeSlider"))
					.getAttribute("value");
			if (defaultSlider.equals("0%") && tintedSlider.equals("100%") && customSlider.equals("50%")) {
				Assert.assertTrue(true);
				generateScreenShots("Test successful for Sliders, sliders reached the expected percentage.", "PASS");

			} else {
				generateScreenShots("Test fail. sliders didn't reached the expected percentage.", "FAIL");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 10, enabled = true)
	public void test_UI_11() throws MalformedURLException {
		try {
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			driver.findElement(AppiumBy.accessibilityId("Stack Views")).click();
			driver.findElement(By.xpath("(//XCUIElementTypeButton[@name='stepper increment'])[1]")).click();
			WebElement furtherDetails = driver
					.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Further Detail']"));
			if (furtherDetails.isDisplayed()) {
				driver.findElement(By.xpath("//XCUIElementTypeButton[@name='stepper increment']")).click();
				Assert.assertTrue(true);
				generateScreenShots("Test successful for Stack Views.", "PASS");
			} else {
				generateScreenShots("Test fail, Further Details is not displayed.", "FAIL");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 11, enabled = true)
	public void test_UI_12() throws MalformedURLException {
		try {
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			driver.findElement(AppiumBy.accessibilityId("Switches")).click();
			driver.findElements(By.xpath("//XCUIElementTypeSwitch")).get(0).click();
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "Test successful for Switches.");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 12, enabled = true)
	public void test_UI_13() throws MalformedURLException {
		try {
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			driver.findElement(AppiumBy.accessibilityId("Text Fields")).click();
			String testValue = excel.getDataFromExcel(excelPath, sheetName, 1, "TestValue");
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == 'Placeholder text'`][2]"))
					.sendKeys(testValue);
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == 'Placeholder text'`][1]"))
					.sendKeys(testValue);
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeTextField[`value == 'Placeholder text'`][2]"))
					.sendKeys(testValue);
			driver.findElement(
					AppiumBy.iOSClassChain("**/XCUIElementTypeSecureTextField[`value == 'Placeholder text'`][1]"))
					.sendKeys(testValue);
			driver.findElement(By.xpath("//XCUIElementTypeCell[4]/XCUIElementTypeTextField[@value='Placeholder text']"))
					.sendKeys(testValue);
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "Test successful for Text Fields, successfully input value :" + testValue);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 13, enabled = true)
	public void test_UI_14() throws MalformedURLException {
		try {
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			driver.findElement(AppiumBy.accessibilityId("Toolbars")).click();
			driver.findElement(AppiumBy.accessibilityId("Default")).click();
			driver.findElement(AppiumBy.accessibilityId("Delete")).click();
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "Test successful for Toolbars.");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 14, enabled = true)
	public void test_UI_15() throws MalformedURLException {
		try {
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label == 'Toolbars'`]")).click();
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			WebElement ele = driver.findElement(AppiumBy.accessibilityId("Web View"));
			Map<String, Object> params = new HashMap<>();
			params.put("element", ((RemoteWebElement) ele).getId());
			params.put("direction", "down");
			driver.executeScript("mobile:scroll", params);
			ele.click();
			WebElement textHTML = driver.findElement(AppiumBy.accessibilityId("This is HTML content inside a "));
			if (textHTML.isDisplayed()) {
				Assert.assertTrue(true);
				generateScreenShots("Test successful for Web View, HTML content is displayed.", "PASS");
			} else {
				generateScreenShots("Test fail, HTML content is not displayed.", "FAIL");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 15, enabled = true)
	public void test_UI_18() throws MalformedURLException {
		try {
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
			driver.findElement(AppiumBy.accessibilityId("Text Entry")).click();
			WebElement getAlertBoxTitle = driver
					.findElement(By.xpath("//XCUIElementTypeStaticText[@name='A Short Title Is Best']"));
			if (getAlertBoxTitle.isDisplayed()) {
				driver.findElement(By.xpath("(//XCUIElementTypeOther)[1]")).sendKeys("Test");
				generateScreenShots("Test successful for Alert Views.", "PASS");
				driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Cancel']")).click();
				Assert.assertTrue(true);
			} else {
				generateScreenShots("Test fail.", "FAIL");
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}
}
