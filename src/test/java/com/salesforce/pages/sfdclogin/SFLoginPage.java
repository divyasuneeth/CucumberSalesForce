package com.salesforce.pages.sfdclogin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class SFLoginPage extends BasePage {

	@FindBy(id = "username")
	private WebElement txtusername;
	@FindBy(id = "password")
	private WebElement txtpassword;
	@FindBy(id = "Login")
	private WebElement btnlogin;
	@FindBy(id = "rememberUn")
	private WebElement chkremmeber_me;
	@FindBy(id = "forgot_password_link")
	private WebElement lnkforgot_pass;
	@FindBy(id = "error")
	private WebElement lblerror;
	@FindBy(id = "idcard-identity")
	private WebElement idcard;

	public SFLoginPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);

	}

	public WebElement getLblError() {
		return lblerror;
	}
	public WebElement getIdCard() {
		return idcard;
	}

	public void enterusername(String usrname) {
		waitUntiVisibility(txtusername);
		txtusername.clear();
		txtusername.sendKeys(usrname);
	}

	public void enterpassword(String passwrd) {
		waitUntiVisibility(txtpassword);
		txtpassword.clear();
		txtpassword.sendKeys(passwrd);
	}

	public void clickLogin() {
		btnlogin.click();
	}

	public void click_rememberMe() {
		waitUntiVisibility(chkremmeber_me);
		chkremmeber_me.click();
	}

	public void click_forgotPass() {
		waitUntiVisibility(lnkforgot_pass);
		lnkforgot_pass.click();
	}

	public String getErrTxt() {
		waitUntiVisibility(lblerror);
		return lblerror.getText();
	}

	public void login(String usrname, String passwrd) {
		enterusername(usrname);
		enterpassword(passwrd);
		clickLogin();
	}

	public String getIdCardText() {
		waitUntiVisibility(idcard);
		return getText(idcard);
	}

}
