package com.salesforce.pages.accounts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class CreateAccPage extends BasePage{
	
	@FindBy(id = "acc2")
	private WebElement accName;
	@FindBy(name = "save")
	private WebElement btnSave;
	
	public CreateAccPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}
	public WebElement getbtnSave() {
		return btnSave;
	}
	public void enterAccName(String name) {
		accName.sendKeys(name);
	}
	
}
