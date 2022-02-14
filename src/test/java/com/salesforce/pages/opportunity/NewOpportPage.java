package com.salesforce.pages.opportunity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class NewOpportPage extends BasePage {

	public NewOpportPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "opp3")
	private WebElement txtOptName;
	@FindBy(id = "opp4_lkwgt")
	private WebElement imgAcc;
	@FindBy(xpath = "//table")
	private WebElement accTable;
	@FindBy(id = "opp9")
	private WebElement txtCloseDt;
	@FindBy(xpath = "//a[text()='Today']")
	private WebElement lnkToday;
	@FindBy(id = "opp11")
	private WebElement ddStage;
	@FindBy(id = "opp6")
	private WebElement ddLead;
	@FindBy(name = "save")
	private WebElement btnSave;
	@FindBy(id = "opp17_lkwgt")
	private WebElement imgCamp;
	

	public Select getDdStage() {
		return getDropdown(ddStage);
	}

	public Select getDsLead() {
		return getDropdown(ddLead);
	}

	public WebElement getBtnSave() {
		return btnSave;
	}

	public void enterOptName(String name) {
		txtOptName.clear();
		txtOptName.sendKeys(name);

	}

	public void enterAccount(String accName) throws Exception {
		// Thread.sleep(3000);
		clickElement(imgAcc);
		if (!findAccount(accName, accTable)) {
			throw new Exception("account not found");

		}

	}

	public void selectCloseDate() throws InterruptedException {
		Thread.sleep(3000);
		clickElement(txtCloseDt);
		clickElement(lnkToday);
	}

	public void selectCampaign(String campname) throws Exception {
		clickElement(imgCamp);
//		spage.findAccount(campname);
		if (!findAccount(campname, accTable)) {
			throw new Exception("campname not found");

		}
	}

}
