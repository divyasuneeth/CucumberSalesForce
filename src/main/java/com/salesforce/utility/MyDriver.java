package com.salesforce.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyDriver {
	private WebDriver driver;
	private static MyDriver obdriver;

	private MyDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
	}

	public static MyDriver getInstanceDriver(String browser) {
		if (obdriver == null)
			obdriver = new MyDriver(browser);
		return obdriver;
	}

	public WebDriver getDriver() {
		return driver;
	}
}
