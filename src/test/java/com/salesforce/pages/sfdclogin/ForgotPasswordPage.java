package com.salesforce.pages.sfdclogin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class ForgotPasswordPage extends BasePage {

	public ForgotPasswordPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	@FindBy(id = "un")
	WebElement usertxt;
	@FindBy(id = "continue")
	WebElement btncontinue;

	public boolean chkTitleTxt(String titlContains) {
		return waitUntilTitleContains(titlContains);
	}

	public void entertxtUser(String usern) {
		waitUntiVisibility(usertxt);
		usertxt.clear();
		usertxt.sendKeys(usern);
	}

	public void clickContinue() {
		btncontinue.click();
	}
}
