package com.salesforce.pages.contacts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class ContactsHomePage extends BasePage {
	@FindBy(id = "tryLexDialogX")
	private WebElement popUp;
	@FindBy(name = "new")
	private WebElement btnNew;
	@FindBy(xpath = "//a[text()='Create New View']")
	private WebElement lnkNewView;
	@FindBy(name = "fcf")
	private WebElement ddView;
	@FindBy(xpath = "//a[text()='Delete']")
	private WebElement deleteView;
	@FindBy(id = "hotlist_mode")
	private WebElement ddRecent;
	@FindBy(xpath = "(//table//tbody//tr//th)[4]")
	private WebElement tableEle;
	@FindBy(xpath = "//div[@class='pbBody']//table")
	private WebElement contactsTable;
	@FindBy(xpath = "//table[@class='x-grid3-row-table']")
	private List<WebElement> listContacts;

	public ContactsHomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public WebElement getPopUp() {
		return popUp;
	}

	public WebElement getBtnNew() {
		return btnNew;
	}

	public WebElement getLnkNewView() {
		return lnkNewView;
	}

	public Select getDdView() {
		return getDropdown(ddView);
	}

	public Select getDdRecent() {
		return getDropdown(ddRecent);
	}

	public void clearViewContact(String name) {

		Select select = new Select(ddView);
		select.selectByVisibleText(name);
		clickElement(deleteView);
		acceptAlert();
	}

	public String getTableHeading() {
		return tableEle.getText();

	}

	public String clickOnContact() {

		List<WebElement> rows = contactsTable.findElements(By.xpath("//tr"));
		String name = "";
		if (rows.size() > 1) {

			WebElement link = rows.get(2).findElement(By.xpath("//th/a"));
			name = link.getText();
			link.click();
		}
		return name;
	}
	
//	public int countRows() {
//		List<WebElement> list= driver.findElements(By.xpath("//table[@class='x-grid3-row-table']"));
//		System.out.println("Size of List"+list.size() );//listContacts.size()
//		return listContacts.size();
//	}

}
