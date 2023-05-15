package androidTabletTest;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import baseClass.AndroidTabletBaseClass;
import io.appium.java_client.AppiumBy;
import utils.ConfigFileReader;
import utils.ReadDataFromExcel;

public class AndroidTabletMyBankApp extends AndroidTabletBaseClass {

	ConfigFileReader cfr = new ConfigFileReader();
	String excelPath = cfr.getSpecificUrlProperties("excelFilePath");
	String sheetName = "TestData - Android";
	ReadDataFromExcel excel = new ReadDataFromExcel();

	@Test(priority = 0)
	public void verifyLaunchMyBankApp_MyBank_01() throws IOException {
		try {
			String expectedTextView = excel.getDataFromExcel(excelPath, sheetName, 1, "HomeTextView");
			String getTextView = driver.findElement(AppiumBy.className("android.widget.TextView")).getText();
			Assert.assertEquals(expectedTextView, getTextView);
			generateScreenShots("Application title: " + getTextView, "PASS");
		} catch (Exception e) {
			generateScreenShots("Application title didn't match the expected title.", "FAIL");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 1)
	public void viewAccounts_MyBank_01() {
		try {
			driver.findElement(AppiumBy.className("android.widget.Button")).click();
			Assert.assertTrue(true);
			test.log(LogStatus.INFO, "Moving to accounts page.");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Move to accounts page fail.");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 2)
	public void validateBankingSystem_MyBank_01() throws IOException {
		try {
			String expectedTextView = excel.getDataFromExcel(excelPath, sheetName, 1, "AccountsTextView");
			WebElement accountsTitle = driver.findElement(By.xpath(("//*[@text='" + expectedTextView + "']")));
			if (accountsTitle.isDisplayed()) {
				Assert.assertTrue(true);
				generateScreenShots("Account page title: " + expectedTextView, "PASS");
			} else {
				Assert.assertTrue(false);
				generateScreenShots("Expected title is not displayed", "FAIL");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			generateScreenShots("Expected title is not displayed", "FAIL");
		}
	}

	@Test(priority = 3)
	public void countAccounts_MyBank_01() throws IOException {
		try {
			List<WebElement> getAccounts = driver.findElements(AppiumBy.className("android.widget.TextView"));
			int totalCount = 0;
			for (WebElement account : getAccounts) {
				if (account.getText().contains("Customer")) {
					totalCount++;
				}
			}
			Reporter.log("Total number of visible accounts: " + totalCount);
			Assert.assertTrue(true);
			generateScreenShots("Total number of visible accounts: " + totalCount, "PASS");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "Test failed.");
			generateScreenShots("Test failed.", "FAIL");
		}
	}

	@Test(priority = 4)
	public void getUserAccount_MyBank_02() {
		try {
			String customerName = excel.getDataFromExcel(excelPath, sheetName, 1, "Customer Name");
			WebElement customerAccount = driver
					.findElement(By.xpath(("//*[@text='Customer Name: " + customerName + "']")));
			if (customerAccount.isDisplayed()) {
				customerAccount.click();
				Assert.assertTrue(true);
				test.log(LogStatus.INFO, "Moving to accounts page with customer name: " + customerName);
			} else {
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Account for customer named " + customerName + ", is not present.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "Test failed.");
		}
	}

	@Test(priority = 5)
	public void validateCustomerName_MyBank_02() {
		try {
			String customerName = excel.getDataFromExcel(excelPath, sheetName, 1, "Customer Name");
			WebElement customerAccountName = driver.findElement(By.xpath(("//*[@text='" + customerName + "']")));
			if (customerAccountName.isDisplayed()) {
				Assert.assertTrue(true);
				test.log(LogStatus.PASS, "Customer name: " + customerName);
			} else {
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Customer name is not present.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "Test failed.");
		}
	}

	@Test(priority = 6)
	public void validateAccountNumber_MyBank_02() {
		try {
			String accountNumber = excel.getDataFromExcel(excelPath, sheetName, 1, "Account Number");
			WebElement customerAccountNumber = driver.findElement(By.xpath(("//*[@text='" + accountNumber + "']")));
			if (customerAccountNumber.isDisplayed()) {
				Assert.assertTrue(true);
				test.log(LogStatus.PASS, "Customer account number: " + accountNumber);
			} else {
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Customer account number is not present.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "Test failed.");
		}
	}

	@Test(priority = 7)
	public void validateAccountEmail_MyBank_02() {
		try {
			String accountEmail = excel.getDataFromExcel(excelPath, sheetName, 1, "Email Id");
			WebElement customerAccountEmail = driver.findElement(By.xpath(("//*[@text='" + accountEmail + "']")));
			if (customerAccountEmail.isDisplayed()) {
				Assert.assertTrue(true);
				test.log(LogStatus.PASS, "Customer email: " + accountEmail);
			} else {
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Customer email is not present.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "Test failed.");
		}
	}

	@Test(priority = 8)
	public void validateAccountMobileNumber_MyBank_02() {
		try {
			String accountMobileNumber = excel.getDataFromExcel(excelPath, sheetName, 1, "Mobile Number");
			WebElement customerAccountMobileNumber = driver
					.findElement(By.xpath(("//*[@text='" + accountMobileNumber + "']")));
			if (customerAccountMobileNumber.isDisplayed()) {
				Assert.assertTrue(true);
				test.log(LogStatus.PASS, "Customer mobile number: " + accountMobileNumber);
			} else {
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Customer mobile number is not present.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "Test failed.");
		}
	}

	@Test(priority = 9)
	public void validateAccountIFSCCode_MyBank_02() {
		try {
			String accountIFSCCode = excel.getDataFromExcel(excelPath, sheetName, 1, "IFSC Code");
			WebElement customerIFSCCode = driver.findElement(By.xpath(("//*[@text='" + accountIFSCCode + "']")));
			if (customerIFSCCode.isDisplayed()) {
				Assert.assertTrue(true);
				test.log(LogStatus.PASS, "Customer IFSC Code: " + accountIFSCCode);
			} else {
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Customer IFSC Code is not present.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "Test failed.");
		}
	}

	@Test(priority = 10)
	public void validateAccountCurrentBalance_MyBank_02() throws IOException {
		try {
			String accountCurrentBalance = excel.getDataFromExcel(excelPath, sheetName, 1, "Current balance");
			WebElement customerCurrentBalance = driver
					.findElement(By.xpath(("//*[@text='" + accountCurrentBalance + "']")));
			if (customerCurrentBalance.isDisplayed()) {
				Assert.assertTrue(true);
				generateScreenShots("Customer current balance: " + accountCurrentBalance, "PASS");
			} else {
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Customer current balance is not present.");
				generateScreenShots("Customer current balance is not present.", "FAIL");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "Test failed.");
			generateScreenShots("Test failed.", "FAIL");
		}
	}

	@Test(priority = 11)
	public void getUserAccount_MyBank_03() {
		try {
			driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']")).click();
			String senderName = excel.getDataFromExcel(excelPath, sheetName, 1, "Sender");
			WebElement customerAccount = driver
					.findElement(By.xpath(("//*[@text='Customer Name: " + senderName + "']")));
			if (customerAccount.isDisplayed()) {
				customerAccount.click();
				Assert.assertTrue(true);
				test.log(LogStatus.PASS, "Moving to accounts page with customer name: " + senderName);
			} else {
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Account for customer named " + senderName + ", is not present.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "Test failed.");
		}
	}

	@Test(priority = 12)
	public void makeTransaction_MyBank_03() {
		try {
			driver.findElement(By.xpath(("//android.widget.Button[@text='Transfer money']"))).click();
			driver.findElement(AppiumBy.className("android.widget.EditText")).sendKeys("123");
			driver.findElement(By.xpath(("(//android.widget.Button)[2]"))).click();
			Assert.assertTrue(true);
			test.log(LogStatus.INFO, "Making a transaction.");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "Test failed.");
		}
	}

	@Test(priority = 13)
	public void selectReceiverAndConfirm_MyBank_03() {
		try {
			String receiverName = excel.getDataFromExcel(excelPath, sheetName, 1, "Receiver");
			driver.findElement(By.xpath(("//*[@text='" + receiverName + "']"))).click();
			Reporter.log("Receiver is " + receiverName);
			Assert.assertTrue(true);
			test.log(LogStatus.PASS, "Select receiver named " + receiverName + " and confirm.");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "Test failed.");
		}
	}

	@Test(priority = 14)
	public void verifyTransaction_MyBank_03() throws IOException {
		try {
			String expectedTextView = excel.getDataFromExcel(excelPath, sheetName, 1, "AccountsTextView");
			WebElement getTextView = driver
					.findElement(By.xpath("(//android.widget.TextView)[@text='" + expectedTextView + "']"));
			if (getTextView.isDisplayed()) {
				Assert.assertTrue(true);
				generateScreenShots("Transaction complete.", "PASS");
			} else {
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Transaction fail.");
				generateScreenShots("Transaction fail.", "FAIL");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			generateScreenShots("Transaction fail.", "FAIL");
		}
	}

	@Test(priority = 15)
	public void clickSearchButton_MyBank_04() {
		try {
			WebElement searchButton = driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Search']"));
			if (searchButton.isDisplayed()) {
				searchButton.click();
				Assert.assertTrue(true);
				test.log(LogStatus.INFO, "Moving to transaction page.");
			} else {
				Assert.assertTrue(false);
				test.log(LogStatus.FAIL, "Transaction page is not present.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
			test.log(LogStatus.FAIL, "Test fail.");
		}
	}

	@Test(priority = 16)
	public void validateTransaction_MyBank_04() throws IOException {
		try {
			String getSenderName = excel.getDataFromExcel(excelPath, sheetName, 1, "Sender");
			String getReceiverName = excel.getDataFromExcel(excelPath, sheetName, 1, "Receiver");
			WebElement getSender = driver
					.findElement(By.xpath("(//android.widget.TextView)[@text='" + getSenderName + "']"));
			WebElement getReceiver = driver
					.findElement(By.xpath("(//android.widget.TextView)[@text='" + getReceiverName + "']"));
			if (getSender.isDisplayed() && getReceiver.isDisplayed()) {
				Assert.assertTrue(true);
				generateScreenShots("Transaction successfully confirmed. Sender is " + getSenderName
						+ " and Receiver is " + getReceiverName, "PASS");
			} else {
				Assert.assertTrue(false);
				generateScreenShots("Transaction is not present.", "FAIL");
			}
		} catch (Exception e) {
			e.printStackTrace();
			generateScreenShots("Transaction is not present.", "FAIL");
		}
	}
}
