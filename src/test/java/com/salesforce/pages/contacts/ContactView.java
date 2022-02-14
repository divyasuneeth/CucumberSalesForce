package com.salesforce.pages.contacts;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class ContactView extends BasePage {
	@FindBy(id = "fname")
	private WebElement txtViewName;
	@FindBy(id = "devname")
	private WebElement txtUniViewName;
	@FindBy(name = "save")
	private WebElement btnSave;
	@FindBy(xpath = "//div[@class='errorMsg']")
	private WebElement error;
	@FindBy(name = "cancel")
	private WebElement btnCancel;

	public ContactView(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}

	public WebElement getTxtViewName() {
		return txtViewName;
	}
	
	public WebElement getTxtUniViewName() {
		return txtUniViewName;
	}

	public WebElement getBtnSave() {
		return btnSave;
	}

	public WebElement getError() {
		return error;
	}

	public WebElement getBtnCancel() {
		return btnCancel;
	}

	public void enterViewname(String name) {
		waitUntiVisibility(txtViewName);
		txtViewName.clear();
		txtUniViewName.clear();
		txtViewName.sendKeys(name);
		txtUniViewName.click();
	}

	public void enterUViewName(String name) {
		waitUntiVisibility(txtViewName);
		txtUniViewName.clear();
		txtUniViewName.sendKeys(name);
	}

	public void assertViewNames(String viewName, String uniViewName) {
		waitUntiVisibility(txtViewName);
		assertEquals(txtViewName.getAttribute("value"), viewName);
		assertEquals(txtUniViewName.getAttribute("value"), uniViewName);
	}
	
	

}
