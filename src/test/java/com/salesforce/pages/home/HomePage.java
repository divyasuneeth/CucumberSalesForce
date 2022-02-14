package com.salesforce.pages.home;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class HomePage extends BasePage {

	@FindBy(id = "userNavButton")
	WebElement dd_user;
	@FindBy(xpath = "//a[contains(@title,'Logout')]")
	WebElement lnk_logout;
	@FindBy(id = "userNavButton")
	WebElement usernav_btn;
	@FindBy(id = "userNavMenu")
	WebElement userMenu;
	@FindBy(xpath = "//a[@title='My Profile']")
	WebElement lnkmyProfile;
	@FindBy(xpath = "//a[@title='My Settings']")
	WebElement lnkMySettings;
	@FindBy(xpath = "//a[@title='Developer Console (New Window)']")
	WebElement lnkDevConsole;
	@FindBy(xpath = "//a[contains(@title,'Logout')]")
	WebElement lnklogout;
	@FindBy(id = "Account_Tab")
	private WebElement accTab;
	@FindBy(id = "Contact_Tab")
	private WebElement contactTab;
	@FindBy(id = "Opportunity_Tab")
	private WebElement optyTab;
	@FindBy(id = "Lead_Tab")
	private WebElement leadTab;
	@FindBy(id = "home_Tab")
	private WebElement homeTab;
	@FindBy(id = "tryLexDialogX")
	private WebElement popUp;
	@FindBy(xpath = "//h1[contains(@class,'currentStatusUserName')]")
	private WebElement userNameDisplay;
	@FindBy(id = "userNavLabel")
	private WebElement userNavlabel;
	@FindBy(id = "AllTab_Tab")
	private WebElement allTabs;
	@FindBy(xpath = "//span[@class='pageDescription']/a")
	private WebElement lnkDate;

	
	
	public HomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public WebElement getContactTab() {
		return contactTab;
	}

	public WebElement getAccTab() {
		return accTab;
	}

	public WebElement getOptyTab() {
		return optyTab;
	}

	public WebElement getLeadTab() {
		return leadTab;
	}

	public WebElement getHomeTab() {
		return homeTab;
	}

	public WebElement getPopUp() {
		return popUp;
	}

	public WebElement getUserNavLabel() {
		return userNavlabel;
	}

	public void logout() {
		waitUntiVisibility(dd_user);
		dd_user.click();
		waitUntiVisibility(lnk_logout);
		lnk_logout.click();
	}

	public WebElement getAllTabs() {
		return allTabs;
	}

	public WebElement getUserNameDisplay() {
		return userNameDisplay;
	}

	public void clickUserNav() {
		waitUntiVisibility(usernav_btn);
		usernav_btn.click();
	}
	
	
	public WebElement getLnkDate() {
		return lnkDate;
	}

	public List<String> getUserMenuOptions() {
		List<WebElement> navOptions = userMenu.findElements(By.tagName("a"));
		List<String> a_options = new ArrayList<String>();
		for (WebElement a : navOptions) {
			a_options.add(a.getText());
		}
		return a_options;
	}

	public void gotoMyProfile() {
		waitUntiVisibility(usernav_btn);
		usernav_btn.click();
		waitUntiVisibility(lnkmyProfile);
		lnkmyProfile.click();
	}

	public void gotoMySettings() {
		waitUntiVisibility(usernav_btn);
		usernav_btn.click();
		waitUntiVisibility(lnkMySettings);
		lnkMySettings.click();
	}

	public void gotoDeveloperConsole() {
		waitUntiVisibility(usernav_btn);
		usernav_btn.click();
		waitUntiVisibility(lnkDevConsole);
		lnkDevConsole.click();
	}

	public void gotoContactTab() {

	}

	public void gotoOpportunityTab() {
		clickElement(optyTab);
	}

}
