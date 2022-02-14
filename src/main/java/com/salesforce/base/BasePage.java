package com.salesforce.base;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends Page {

	public BasePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public void waitUntiVisibility(WebElement e) {
		try {
			wait.until(ExpectedConditions.visibilityOf(e));
		} catch (Exception ex) {
			System.out.println("timeout- waiting for element: " + e);
		}
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getText(WebElement e) {
		try {
			waitUntiVisibility(e);
			return e.getText();
		} catch (Exception ex) {
			System.out.println("timeout- waiting for element: " + e);
			return null;
		}
	}

	public void clickElement(WebElement e) {
		try {
			waitUntiVisibility(e);
			e.click();
		} catch (Exception ex) {
			System.out.println("click not possible: " + e);
		}
	}

	public void switchToFrameByName(WebElement e) {
		waitUntiVisibility(e);
		driver.switchTo().frame(e);
	}

	public void switchBackToDefault() {
		driver.switchTo().defaultContent();
	}

	public boolean waitUntilTitleContains(String title) {
		try {
			return wait.until(ExpectedConditions.titleContains(title));
		} catch (Exception e) {
			System.out.println("timeout- waiting for page to load: " + title);
			return false;
		}

	}

	public void selectByVisibleText(WebElement e, String txt) {
		Select select = new Select(e);
		select.selectByVisibleText(txt);
	}

	public boolean checkOptionPresentSelect(WebElement e, String option) {
		Select savedOp = new Select(e);
		List<WebElement> opts = savedOp.getOptions();
		boolean flag = false;

		for (WebElement el : opts) {
			if (el.getText().equalsIgnoreCase(option)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public String switchWindow() {
		String currentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			if (!window.equals(currentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}

		return currentWindow;
	}

	public void closeWindow() {
		driver.close();
	}

	public void acceptAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public Select getDropdown(WebElement e) {
		Select select = new Select(e);
		return select;
	}

	public boolean checkOptions(String s, List<WebElement> op) {

		boolean flag = false;
		for (WebElement e : op) {
			if (e.getText().trim().equalsIgnoreCase(s))
				flag = true;
		}
		return flag;

	}

	public boolean findAccount(String accname, WebElement accTable) throws Exception {

		String currentWindow = switchWindow();
		Thread.sleep(3000);
		driver.switchTo().frame("resultsFrame");

		boolean success = false;
		List<WebElement> list_tr = accTable.findElements(By.xpath("//tr"));
		for (WebElement row : list_tr) {
			WebElement th = row.findElement(By.xpath("th"));
			if (th.getText().toLowerCase().contains(accname)) {
				th.findElement(By.tagName("a")).click();
				success = true;
				break;
			}
		}
		driver.switchTo().window(currentWindow);
		return success;
	}

	public boolean checkElementPresent(WebElement e) {
		try {
			wait.until(ExpectedConditions.visibilityOf(e));
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}
