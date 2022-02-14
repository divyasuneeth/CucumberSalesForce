package com.salesforce.pages.home;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class AllTabsPage extends BasePage {
	@FindBy(name = "customize")
	private WebElement custom;
	@FindBy(xpath = "//li/a")
	private List<WebElement> taboptions;

	public AllTabsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public WebElement getCustom() {
		return custom;
	}

	public boolean findTab(String option) {
		for (WebElement a : taboptions) {
			if (a.getText().equalsIgnoreCase(option))
				return true;
		}
		return false;
	}

}
