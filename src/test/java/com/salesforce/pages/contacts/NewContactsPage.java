package com.salesforce.pages.contacts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class NewContactsPage extends BasePage {

	public NewContactsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "name_lastcon2")
	private WebElement txtlastName;
	@FindBy(id = "con4_lkwgt")
	private WebElement imgAcc;
	@FindBy(id = "resultsFrame")
	private WebElement iframe;
	@FindBy(xpath = "//table")
	private WebElement accTable;
	@FindBy(xpath = "//input[@name='save']")
	private WebElement btnSave;
	@FindBy(name = "save_new")
	private WebElement btnSaveNew;

	public WebElement getBtnSave() {
		return btnSave;
	}

	public WebElement getBtnSaveNew() {
		return btnSaveNew;
	}
	
	public WebElement getIFrame() {
		return iframe;
	}
	
	public WebElement getAccTable() {
		return accTable;
	}

	public void createNewContact(String lastName, String accname) throws Exception {
		txtlastName.sendKeys(lastName);
		clickElement(imgAcc);
		if (!findAccount(accname, accTable)) {
			throw new Exception("account not found");

		}
	}

}
