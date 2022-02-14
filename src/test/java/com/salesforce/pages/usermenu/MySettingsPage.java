package com.salesforce.pages.usermenu;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class MySettingsPage extends BasePage {

	@FindBy(id = "PersonalInfo")
	WebElement personalTab;
	@FindBy(id = "LoginHistory_font")
	WebElement loginHistoryTab;
	@FindBy(xpath = "//a[@href='/servlet/servlet.LoginHistory?id=0058c000007mTfi']")
	WebElement lnkLoginDownload;
	@FindBy(id = "DisplayAndLayout")
	WebElement displayTab;
	@FindBy(id = "CustomizeTabs_font")
	WebElement cutomizeTab;
	@FindBy(id = "p4")
	WebElement ddCustomApp;
	@FindBy(id = "duel_select_0")
	WebElement ddAvailableTabs;
	@FindBy(id = "duel_select_1")
	WebElement ddSelectedTabs;
	@FindBy(id = "duel_select_0_right")
	WebElement addRight;
	@FindBy(name = "save")
	WebElement btnSave;
	@FindBy(id = "tsidButton")
	WebElement ddTabSet;
	@FindBy(id = "tsid-menuItems")
	WebElement menuTabSet;
	@FindBy(id = "tsidLabel")
	WebElement lblTabSet;
	@FindBy(xpath = "//a[text()='Salesforce Chatter']")
	WebElement lnkSalesChatter;
	@FindBy(id = "EmailSetup")
	WebElement emailTab;
	@FindBy(id = "EmailSettings_font")
	WebElement emailSettings;
	@FindBy(id = "sender_name")
	WebElement senderName;
	@FindBy(id = "sender_email")
	WebElement senderEmail;
	@FindBy(id = "auto_bcc1")
	WebElement autoBcc;
	@FindBy(id = "CalendarAndReminders")
	WebElement calTab;
	@FindBy(id = "Reminders_font")
	WebElement lnkActivity;
	@FindBy(id = "testbtn")
	WebElement btnOpenTestReminder;
	@FindBy(id = "dismiss_all")
	WebElement dismissAll;

	public WebElement getBtnSave() {
		return btnSave;
	}

	public WebElement getPersonalTab() {
		return personalTab;
	}

	public WebElement getLoginHistoryTab() {
		return loginHistoryTab;
	}

	public WebElement getLnkLoginDownload() {
		return lnkLoginDownload;
	}

	public WebElement getDisplayTab() {
		return displayTab;
	}

	public WebElement getCutomizeTab() {
		return cutomizeTab;
	}

	public WebElement getDdCustomApp() {
		return ddCustomApp;
	}

	public WebElement getDdSelectedTabs() {
		return ddSelectedTabs;
	}

	public WebElement getDdTabSet() {
		return ddTabSet;
	}

	public WebElement getMenuTabSet() {
		return menuTabSet;
	}

	public WebElement getLblTabSet() {
		return lblTabSet;
	}

	public WebElement getLnkSalesChatter() {
		return lnkSalesChatter;
	}

	public MySettingsPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public void makeTabCustomization(String option) {
		Select selectOptions = new Select(ddAvailableTabs);
		if (checkOptionPresentSelect(ddAvailableTabs, option)) {
			selectOptions.selectByValue("report");
			clickElement(addRight);
		}
	}

	public void setEmailSettings(String eName, String eMail) {
		clickElement(emailTab);
		clickElement(emailSettings);
		senderName.clear();
		senderName.sendKeys(eName);
		senderEmail.clear();
		senderEmail.sendKeys(eMail);

		if (!autoBcc.isSelected())
			autoBcc.click();

		clickElement(btnSave);
	}

	public void createCalTestReminder() throws InterruptedException {
		clickElement(calTab);
		clickElement(lnkActivity);
		clickElement(btnOpenTestReminder);
		//switchWindow();
		
		String currentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			if (!window.equals(currentWindow)) {
				driver.switchTo().window(window);
				Thread.sleep(5000);
				clickElement(dismissAll);
				break;
			}
		}
		
	}
}
