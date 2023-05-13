package iOSTabletTest;

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

import baseClass.TabletiOSBaseClass;
import io.appium.java_client.AppiumBy;
import utils.ConfigFileReader;
import utils.ReadDataFromExcel;

public class TabletiOS_UI_Test extends TabletiOSBaseClass {

	ConfigFileReader cfr = new ConfigFileReader();
	String excelPath = cfr.getSpecificUrlProperties("excelFilePath");
	String sheetName = "TestData - iOS";
	ReadDataFromExcel excel = new ReadDataFromExcel();

	@Test(priority = 0, enabled = true)
	public void test_UI_01() throws MalformedURLException {
		try {
			WebElement ele = driver.findElement(AppiumBy.accessibilityId("Web View"));
			Map<String, Object> params = new HashMap<>();
			params.put("element", ((RemoteWebElement) ele).getId());
			params.put("direction", "down");
			driver.executeScript("mobile:scroll", params);
			List<WebElement> getOptions = driver.findElements(By.xpath("//XCUIElementTypeCell"));
			Reporter.log("Total number of options: " + getOptions.size());
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "Total number of options: " + getOptions.size());
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
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
			driver.findElement(AppiumBy.accessibilityId("Date Picker")).click();
			driver.findElements(By.xpath("//XCUIElementTypeButton")).get(3).click();
			driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='18']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='PopoverDismissRegion']")).click();
			driver.findElements(By.xpath("//XCUIElementTypeButton")).get(4).click();
			String hour = excel.getDataFromExcel(excelPath, sheetName, 1, "UI_02_Hour");
			String minutes = excel.getDataFromExcel(excelPath, sheetName, 1, "UI_02_Minutes");
			String ampm = excel.getDataFromExcel(excelPath, sheetName, 1, "UI_02_AMorPM");
			driver.findElements(By.xpath(("//XCUIElementTypePickerWheel"))).get(2).sendKeys(ampm);
			driver.findElements(By.xpath(("//XCUIElementTypePickerWheel"))).get(0).sendKeys(hour);
			driver.findElements(By.xpath(("//XCUIElementTypePickerWheel"))).get(1).sendKeys(minutes);
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='PopoverDismissRegion']")).click();
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "Test successful for Date Picker.");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 3, enabled = true)
	public void test_UI_04() throws MalformedURLException {
		try {
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
			driver.findElement(AppiumBy.accessibilityId("Page Control")).click();
			WebElement indicatorDots = driver.findElement(By.xpath("//XCUIElementTypePageIndicator"));
			indicatorDots.click();
			indicatorDots.click();
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "Test successful for Page Control.");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 5, enabled = true)
	public void test_UI_06() throws MalformedURLException {
		try {
			driver.findElement(AppiumBy.accessibilityId("Picker View")).click();
			driver.findElement(AppiumBy.accessibilityId("Red color component value")).sendKeys("255");
			driver.findElement(AppiumBy.accessibilityId("Green color component value")).sendKeys("0");
			driver.findElement(AppiumBy.accessibilityId("Blue color component value")).sendKeys("0");
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "Test successful for Picker View, changed the color to red.");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 6, enabled = true)
	public void test_UI_07() throws MalformedURLException {
		try {
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
			test.log(LogStatus.PASS, "Test successful for Progress Views, wait 10 seconds for bar to reach 100%");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 7, enabled = true)
	public void test_UI_08() throws MalformedURLException {
		try {
			driver.findElement(AppiumBy.accessibilityId("Search")).click();
			driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Default']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Scope Two']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Custom']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeSearchField")).click();
			driver.findElement(By.xpath("//XCUIElementTypeKey[@name='T']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeKey[@name='e']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeKey[@name='s']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeKey[@name='t']")).click();
			driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Cancel']")).click();
			String searchFieldValue = driver.findElement(By.xpath("//XCUIElementTypeSearchField"))
					.getAttribute("value");
			Assert.assertEquals(searchFieldValue, "Test");
			test.log(LogStatus.PASS, "Test successful for Search, current value for search field: " + searchFieldValue);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 8, enabled = true)
	public void test_UI_09() throws MalformedURLException {
		try {
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
			driver.findElement(AppiumBy.accessibilityId("Sliders")).click();
			driver.findElements(By.xpath("//XCUIElementTypeSlider")).get(0).sendKeys("0%");
			Thread.sleep(2000);
			driver.findElements(By.xpath("//XCUIElementTypeSlider")).get(1).sendKeys("1");
			Thread.sleep(2000);
			driver.findElements(By.xpath("//XCUIElementTypeSlider")).get(2).sendKeys("0.5");
			String defaultSlider = driver.findElements(By.xpath("//XCUIElementTypeSlider")).get(0)
					.getAttribute("value");
			String tintedSlider = driver.findElements(By.xpath("//XCUIElementTypeSlider")).get(1).getAttribute("value");
			String customSlider = driver.findElements(By.xpath("//XCUIElementTypeSlider")).get(2).getAttribute("value");
			if (defaultSlider.equals("0%") && tintedSlider.equals("100%") && customSlider.equals("50%")) {
				Assert.assertTrue(true);
				test.log(LogStatus.PASS, "Test successful for Sliders, sliders reached the expected percentage.");
			} else {
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Test fail. sliders didn't reached the expected percentage.");
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
			driver.findElement(AppiumBy.accessibilityId("Stack Views")).click();
			driver.findElement(By.xpath("(//XCUIElementTypeButton[@name='stepper increment'])[1]")).click();
			WebElement furtherDetails = driver
					.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Further Detail']"));
			if (furtherDetails.isDisplayed()) {
				driver.findElement(By.xpath("//XCUIElementTypeButton[@name='stepper increment']")).click();
				Assert.assertTrue(true);
				test.log(LogStatus.PASS, "Test successful for Stack Views.");
			}else {
				test.log(LogStatus.FAIL, "Test fail, Further Details is not displayed.");
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
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			WebElement ele = driver.findElement(AppiumBy.accessibilityId("Web View"));
			Map<String, Object> params = new HashMap<>();
			params.put("element", ((RemoteWebElement) ele).getId());
			params.put("direction", "down");
			driver.executeScript("mobile:scroll", params);
			ele.click();
			WebElement textHTML = driver.findElement(AppiumBy.accessibilityId("This is HTML content inside a"));
			if (textHTML.isDisplayed()) {
				Assert.assertTrue(true);
				test.log(LogStatus.PASS, "Test successful for Web View, HTML content is displayed.");
			} else {
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Test fail, HTML content is not displayed.");
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
			driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
			driver.findElement(AppiumBy.accessibilityId("Text Entry")).click();
			WebElement getAlertBoxTitle = driver
					.findElement(By.xpath("//XCUIElementTypeStaticText[@name='A Short Title Is Best']"));
			if (getAlertBoxTitle.isDisplayed()) {
				driver.findElement(By.xpath("(//XCUIElementTypeOther)[1]")).sendKeys("Test");
				driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Cancel']")).click();
				Assert.assertTrue(true);
				test.log(LogStatus.PASS, "Test successful for Alert Views.");
			} else {
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Test fail.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Test fail.");
			Assert.assertTrue(false);
		}
	}
}
