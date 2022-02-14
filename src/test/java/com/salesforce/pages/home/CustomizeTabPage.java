package com.salesforce.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class CustomizeTabPage extends BasePage {
	@FindBy(id = "duel_select_1")
	private WebElement ddSelectedTab;
	@FindBy(id = "duel_select_0_left")
	private WebElement btnLeft;
	@FindBy(id = "duel_select_0")
	private WebElement ddAvailabeTab;
	@FindBy(name="save")
	private WebElement btnSave;

	public CustomizeTabPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}

	public Select getDdSelectedTab() {
		return getDropdown(ddSelectedTab);
	}

	public WebElement getBtnLeft() {
		return btnLeft;
	}
	
	public WebElement getBtnSave() {
		return btnSave;
	}

	public boolean chkSelectedOPtion(String option) {
		return checkOptionPresentSelect(ddSelectedTab, option);
	}
	public boolean chkAvailabeOPtion(String option) {
		return checkOptionPresentSelect(ddAvailabeTab, option);
	}
	public void waitSelectDD() {
		wait.until(ExpectedConditions.visibilityOf(ddSelectedTab));
	}
	public void waitAvailDD() {
		wait.until(ExpectedConditions.visibilityOf(ddAvailabeTab));
	}

}
