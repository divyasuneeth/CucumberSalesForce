package com.salesforce.basetest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.salesforce.base.Page;
import com.salesforce.pages.home.HomePage;
import com.salesforce.pages.sfdclogin.SFLoginPage;
import com.salesforce.utility.Constants;
import com.salesforce.utility.GenerateReport;
import com.salesforce.utility.ReadProperty;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected static WebDriver driver;
	protected WebDriverWait wait;
	protected Page page;
	protected HomePage home;
	protected SFLoginPage login;
	protected String username = ReadProperty.readProperty("username");
	protected String password = ReadProperty.readProperty("password");
	protected GenerateReport report = GenerateReport.getInstanceOfGenerateReport();
	private static int filecount=0;

	@BeforeTest
	public void initialSetUp() {
		report.startExtentReport();
	}

	@Parameters({ "browser" })
	@BeforeMethod
	public void launchApp(String browser, Method method) {
		// ob = MyDriver.getInstanceDriver(browser);
		// driver = ob.getDriver();
		report.startSingleTestReport(method.getName());
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 10);
		page = new Page(driver, wait);
		home = page.getInsatnce(HomePage.class);
		login = page.getInsatnce(SFLoginPage.class);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			report.logTestpassed();
		} else if (result.getStatus() == ITestResult.FAILURE) {
			report.logTestFailed();
			String path = takescreenshot();
			try {
				report.logger.addScreenCaptureFromPath(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		driver.quit();
		report.logTestInfo("Test window closed");
	}

	@AfterTest
	public void endTest() {
		report.endTestReport();
	}

	public static String takescreenshot() {
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		String filePath = Constants.SCREENSHOT_PATH + filecount+"_SalesForce.jpg";
		filecount++;
		File DestFile = new File(filePath);
		// Copy file at destination
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return filePath;
	}
}
