package com.salesforce.pages.opportunity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class OppHomePage extends BasePage {

	public OppHomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}

	@FindBy(id = "tryLexDialogX")
	private WebElement popUp;
	@FindBy(id = "fcf")
	private WebElement ddView;
	@FindBy(name = "new")
	private WebElement btnNew;
	@FindBy(xpath = "//a[text()='Opportunity Pipeline']")
	private WebElement lnkOptyPipeline;
	@FindBy(xpath = "//a[text()='Stuck Opportunities']")
	private WebElement lnkStuckOpp;
	@FindBy(id = "quarter_q")
	private WebElement ddInterval;
	@FindBy(id = "open")
	private WebElement ddInclude;
	@FindBy(xpath = "//input[@value='Run Report']")
	private WebElement btnRunReport;

	public WebElement getPopUp() {
		return popUp;
	}

	public Select getDdView() {
		return getDropdown(ddView);
	}

	public WebElement getBtnNew() {
		return btnNew;
	}

	public WebElement getLnkOptyPipeline() {
		return lnkOptyPipeline;
	}

	public WebElement getLnkStuckOpp() {
		return lnkStuckOpp;
	}

	public Select getDdInterval() {
		return getDropdown(ddInterval);
	}

	public Select getDdInclude() {
		return getDropdown(ddInclude);
	}
	
	public WebElement getBtnRunReport() {
		return btnRunReport;
	}
	

}
