package com.salesforce.pages.accounts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class UnsavedReport extends BasePage {
	@FindBy(id = "ext-gen20")
	private WebElement ddDateField;
	@FindBy(xpath = "//div[contains(text(),'Created Date')]")
	private WebElement option;
	@FindBy(id = "ext-gen152")
	private WebElement imgFromCal;
	@FindBy(id = "ext-comp-1042")
	private WebElement txtFromdate;
	@FindBy(id = "ext-comp-1045")
	private WebElement txtTodate;
	@FindBy(id = "ext-gen49")
	private WebElement btnSave;
	@FindBy(id = "saveReportDlg_reportNameField")
	private WebElement repName;
	@FindBy(id = "saveReportDlg_DeveloperName")
	private WebElement repUniName;
	@FindBy(id = "dlgSaveReport")
	private WebElement btnRepSave;

	public UnsavedReport(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public void selectDateField() {
		clickElement(ddDateField);
		clickElement(option);
		txtFromdate.sendKeys(txtTodate.getText());
	}

	public void saveReport(String repname) throws InterruptedException {
		clickElement(btnSave);
		repName.clear();
		repUniName.clear();
		repName.sendKeys(repname);
		repUniName.click();
		waitUntiVisibility(btnRepSave);
		if (btnRepSave.isEnabled()) {
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript("arguments[0].click();", btnRepSave);
//			clickElement(btnRepSave);
			btnRepSave.click();
		} else {	
			clickElement(btnRepSave);
		}

	}

}
