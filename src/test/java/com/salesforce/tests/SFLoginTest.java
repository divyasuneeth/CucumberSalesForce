package com.salesforce.tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.salesforce.basetest.BaseTest;
import com.salesforce.pages.sfdclogin.ForgotPasswordPage;
import com.salesforce.utility.Constants;

public class SFLoginTest extends BaseTest {
	static ITestResult result;

	@Test
	public void testloginError_TC01() {
		try {
			login.enterusername(username);
			report.logTestInfo("username enetered");
			login.clickLogin();
			report.logTestInfo("login btn clicked");
			Assert.assertEquals(login.getErrTxt(), Constants.passERR01);
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void testLoginWithCredentials_TC02() {
		try {
			login.enterusername(username);
			report.logTestInfo("username enetered");
			login.enterpassword(password);
			report.logTestInfo("password enetered");
			login.clickLogin();
			report.logTestInfo("login btn clicked");
			Assert.assertTrue(home.waitUntilTitleContains("Home Page"));
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void loginRememberMe_TC03() {
		try {
			login.click_rememberMe();
			report.logTestInfo("remember me checked");
			login.login(username, password);
			report.logTestInfo("login successful");
			home.logout();
			report.logTestInfo("logout successful");
			assertEquals(login.getIdCardText(), username);
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}

	}

	@Test
	public void forgotPassword_TC04A() {
		try {
			ForgotPasswordPage fpage = page.getInsatnce(ForgotPasswordPage.class);
			login.click_forgotPass();
			report.logTestInfo("forgot password clicked");
			Assert.assertTrue(fpage.chkTitleTxt("Forgot Your"));
			fpage.entertxtUser(username);
			report.logTestInfo("username entered for password recovery");
			fpage.clickContinue();
			report.logTestInfo("continue clicked");	
			Assert.assertTrue(fpage.chkTitleTxt("Check Your Email"));
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void forgotPassword_TC04B() {
		try {
			String user_name = "123";
			String pass_word = "22131";
			login.login(user_name, pass_word);
			report.logTestInfo("login clicked");
			Assert.assertEquals(login.getErrTxt(), Constants.passERR02);
		}catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}

	}

}
