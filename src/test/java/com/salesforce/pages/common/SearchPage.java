package com.salesforce.pages.common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class SearchPage extends BasePage {
	@FindBy(id = "lksrch")
	private WebElement txtsearch;
	@FindBy(name = "go")
	private WebElement btnGo;
	@FindBy(xpath = "//table")
	private WebElement accTable;

	public SearchPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	public boolean findAccount(String accname) throws Exception {

		String currentWindow=switchWindow();
		Thread.sleep(3000);
		driver.switchTo().frame("resultsFrame");
		txtsearch.click();
		txtsearch.clear();
		txtsearch.sendKeys(accname);
		btnGo.click();
		
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

}
