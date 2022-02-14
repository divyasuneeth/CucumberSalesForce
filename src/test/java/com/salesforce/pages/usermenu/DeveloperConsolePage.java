package com.salesforce.pages.usermenu;

import static org.testng.Assert.assertEquals;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class DeveloperConsolePage extends BasePage {

	public DeveloperConsolePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	public void switchToDevConsole() throws InterruptedException {
		String currentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		
			for (String window : windows) {
				if (!window.equals(currentWindow)) {
					driver.switchTo().window(window);
					Thread.sleep(5000);
					assertEquals(driver.getTitle(), "Developer Console");
					closeWindow();
					driver.switchTo().window(currentWindow);
				}
			}
		

	}
	

}
