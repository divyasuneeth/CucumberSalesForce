package com.salesforce.pages.accounts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class AccountsPage extends BasePage {

	@FindBy(id = "tryLexDialogX")
	private WebElement popUp;
	@FindBy(name = "new")
	private WebElement lnkNewAcc;
	@FindBy(name = "fcf")
	private WebElement ddView;
	@FindBy(xpath = "//a[text()='Edit']")
	private WebElement viewEdit;
	@FindBy(name = "delID")
	private WebElement viewDel;
	@FindBy(xpath = "//td//div[@title='Last Activity']")
	private WebElement viewTableCol;
	@FindBy(xpath = "//a[contains(text(),'Merge Accounts')]")
	private WebElement mergeAcc;
	@FindBy(xpath = "//a[contains(text(),'30 days')]")
	private WebElement report_activity;
	@FindBy(name = "delID")
	private WebElement deleteView;

	public AccountsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public WebElement getPopUp() {
		return popUp;
	}

	public WebElement getLnkNewAcc() {
		return lnkNewAcc;
	}

	public WebElement getDdView() {
		return ddView;
	}

	public WebElement getViewEdit() {
		return viewEdit;
	}

	public WebElement getViewTableCol() {
		return viewTableCol;
	}

	public WebElement getMergeAcc() {
		return mergeAcc;
	}

	public WebElement getReport_activity() {
		return report_activity;
	}

	public void deletView(String viewName) {
		try {
			Select view = new Select(ddView);
			if (view.getFirstSelectedOption().getText().equals(viewName)) {
				clickElement(viewEdit);
				clickElement(viewDel);
				Thread.sleep(3000);
				Alert alert = driver.switchTo().alert();
				alert.accept();
			}

		} catch (Exception e) {
			System.out.println("deleteView ---exception");
		}

	}

	public boolean chkViewNameInDD(String viewName) {

		Select view = new Select(ddView);
		return view.getFirstSelectedOption().getText().equalsIgnoreCase(viewName);
	}

	public void deleteView() {
		waitUntiVisibility(deleteView);
		deleteView.click();
	}
}
