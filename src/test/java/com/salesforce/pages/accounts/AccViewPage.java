package com.salesforce.pages.accounts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class AccViewPage extends BasePage {

	@FindBy(xpath = "//a[text()='Create New View']")
	private WebElement lnkCreateView;
	@FindBy(id = "fname")
	private WebElement txtViewName;
	@FindBy(id = "devname")
	private WebElement txtUniViewName;
	@FindBy(name = "save")
	private WebElement btnSave;
	@FindBy(id = "fcol1")
	private WebElement ddField;
	@FindBy(id = "fop1")
	private WebElement ddOperator;
	@FindBy(id = "fval1")
	private WebElement txtValue;
	@FindBy(id = "colselector_select_0")
	private WebElement ddAvailableFields;
	@FindBy(id = "colselector_select_0_right")
	private WebElement rightArrow;
	@FindBy(xpath = "//input[contains(@value,' Save')]")
	private WebElement saveView;

	public AccViewPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		// TODO Auto-generated constructor stub
	}

	public WebElement getSaveView() {
		return saveView;
	}

	public void createNewView(String viewName) {
		clickElement(lnkCreateView);
		txtViewName.clear();
		txtViewName.sendKeys(viewName);
		txtUniViewName.clear();
		txtUniViewName.sendKeys(viewName);
		clickElement(btnSave);
	}

	public void editView(String newViewName) {
		txtViewName.clear();
		txtUniViewName.clear();
		txtViewName.sendKeys(newViewName);
		selectByVisibleText(ddField, "Account Name");
		selectByVisibleText(ddOperator, "contains");
		txtValue.sendKeys("a");
		if (checkOptionPresentSelect(ddAvailableFields, "Last Activity")) {
			selectByVisibleText(ddAvailableFields, "Last Activity");
			clickElement(rightArrow);
		}
	}

}
