package com.salesforce.pages.leads;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class LeadsHomePage extends BasePage {

	@FindBy(id = "tryLexDialogX")
	private WebElement popUp;
	@FindBy(name = "fcf")
	private WebElement ddLeads;
	@FindBy(name = "go")
	private WebElement btnGo;
	@FindBy(name="new")
	private WebElement btnNew;
	@FindBy(id="name_lastlea2")
	private WebElement leadName;
	@FindBy(id="lea3")
	private WebElement compName;
	@FindBy(name="save")
	private WebElement btnSave;

	public WebElement getPopUp() {
		return popUp;
	}

	public Select getDdLeads() {
		return getDropdown(ddLeads);
	}

	public WebElement getBtnGo() {
		return btnGo;
	}
	
	public WebElement getBtnNew() {
		return btnNew;
	}
	
	public WebElement getBtnSave() {
		return btnSave;
	}

	public LeadsHomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	public void enterLeadName_companyName(String name,String compname) {
		leadName.clear();
		leadName.sendKeys(name);
		compName.clear();
		compName.sendKeys(compname);
		
	}

}
