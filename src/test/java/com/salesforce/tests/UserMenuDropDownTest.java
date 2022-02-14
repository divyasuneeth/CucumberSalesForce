package com.salesforce.tests;

import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.salesforce.basetest.BaseTest;
import com.salesforce.pages.usermenu.DeveloperConsolePage;
import com.salesforce.pages.usermenu.MySettingsPage;
import com.salesforce.pages.usermenu.UserProfilePage;
import com.salesforce.utility.ReadProperty;

public class UserMenuDropDownTest extends BaseTest {
	static ITestResult result;

	@Test
	public void selctDropDown_TC05() {
		try {
			login.login(username, password);
			report.logTestInfo("login successful");
			Assert.assertTrue(home.waitUntilTitleContains("Home Page ~ "));
			home.clickUserNav();
			report.logTestInfo("user drop down clicked");
			String[] nav = { "My Profile", "My Settings", "Developer Console", "Logout" };
			assertTrue(home.getUserMenuOptions().containsAll(Arrays.asList(nav)));
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void selectProfile_TC06() {

		// resetTC_06();
		try {
			String lastName = "aaa";
			String testText = " test random text";
			UserProfilePage profile = page.getInsatnce(UserProfilePage.class);
			login.login(username, password);
			report.logTestInfo("login successful");
			Assert.assertTrue(home.waitUntilTitleContains("Home Page ~ "));
			home.gotoMyProfile();
			report.logTestInfo("My profile clicked");
			Assert.assertTrue(home.waitUntilTitleContains("User:"));

			// --- Edit Profile----------
			profile.waitUntiVisibility(profile.getBtnEditdrop());
			Thread.sleep(3000);
			profile.clickElement(profile.getBtnEditdrop());
			report.logTestInfo("profile drop menu clicked");
			profile.clickElement(profile.getLnkEditProfile());
			report.logTestInfo("edit profile clicked");
			profile.waitUntiVisibility(profile.getEditFrame());
			profile.switchToFrameByName(profile.getEditFrame());
			profile.clickElement(profile.getAboutTab());
			profile.enterLastName(lastName);
			report.logTestInfo("lastName entered");
			profile.clickElement(profile.getSaveBtn());
			Assert.assertEquals(profile.getText(profile.getUsernametag()).split(" ")[1], lastName,
					"LastName not changed");

			// --- check Post option-----------
			profile.clickElement(profile.getLnkPost());
			report.logTestInfo("post link clicked");
			profile.switchToFrameByName(profile.getPostFrame());
			profile.enterPost(testText);
			report.logTestInfo("text posted");
			profile.switchBackToDefault();
			profile.waitUntiVisibility(profile.getPublishBtn());
			profile.clickElement(profile.getPublishBtn());
			Assert.assertTrue(profile.varifyPost(testText), "post not published");
			report.logTestInfo("post published");
			// ---- to check file upload----
			profile.waitUntiVisibility(profile.getLnkFileUpload());
			profile.clickElement(profile.getLnkFileUpload());
			report.logTestInfo("file upload link clicked");
			profile.waitUntiVisibility(profile.getBtnChatterUpload());
			profile.clickElement(profile.getBtnChatterUpload());
			report.logTestInfo("chatter clicked");
			Thread.sleep(3000);
			profile.uploadFile();
			Assert.assertTrue(profile.verifyFileUpload(), "file not published");
			report.logTestInfo("file uploaded");
			// -----Profile pic
			Thread.sleep(3000);
			profile.uploadPic();
			report.logTestInfo("Profile pic uploaded");

		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}

	}

	@Test
	public void testMySettings_TC07() {
		try {
			MySettingsPage settings = page.getInsatnce(MySettingsPage.class);
			login.login(username, password);
			report.logTestInfo("login successful");
			Assert.assertTrue(home.waitUntilTitleContains("Home Page ~ "));
			home.gotoMySettings();
			report.logTestInfo("MySettings clicked");
			Assert.assertTrue(home.waitUntilTitleContains("Hello,"));

			String eName = ReadProperty.readProperty("ename");
			String eMail = ReadProperty.readProperty("email");

			// --Login History
			settings.clickElement(settings.getPersonalTab());
			settings.clickElement(settings.getLoginHistoryTab());
			settings.clickElement(settings.getLnkLoginDownload());
			report.logTestInfo("login history downloaded");
			// ------ Customize tab-----
			settings.clickElement(settings.getDisplayTab());
			settings.clickElement(settings.getCutomizeTab());
			settings.selectByVisibleText(settings.getDdCustomApp(), "Salesforce Chatter");
			settings.makeTabCustomization("reports");
			assertTrue(settings.checkOptionPresentSelect(settings.getDdSelectedTabs(), "reports"));
			settings.clickElement(settings.getBtnSave());
			report.logTestInfo("Tab customization done");
			settings.clickElement(settings.getDdTabSet());
			Thread.sleep(3000);
			if (settings.getMenuTabSet().isDisplayed()) {
				if (!settings.getLblTabSet().getText().equalsIgnoreCase("Salesforce Chatter"))
					settings.clickElement(settings.getLnkSalesChatter());

				// assertNotNull(waitUntil_VisibilityLoc("xpath",
				// "//a[contains(text(),'Reports')]"));
				// waitUntil_VisibilityLoc("id", "tryLexDialogX").click();

				// id---> report_Tab
			}

			// ----- Email-------
			settings.setEmailSettings(eName, eMail);
			report.logTestInfo("email setting successful");
			// ---- Calendar
			settings.createCalTestReminder();
			report.logTestInfo("Calender test successful");
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}

	}

	@Test
	public void testDeveloperConsole_TC08() {
		try {
			login.login(username, password);
			report.logTestInfo("login successful");
			Assert.assertTrue(home.waitUntilTitleContains("Home Page ~ "));
			home.gotoDeveloperConsole();
			report.logTestInfo("Developer console clicked");
			DeveloperConsolePage dev = page.getInsatnce(DeveloperConsolePage.class);
			dev.switchToDevConsole();
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}

	}

	@Test
	public void testLogout_TC09() {
		try {
			login.login(username, password);
			report.logTestInfo("login successful");
			Assert.assertTrue(home.waitUntilTitleContains("Home Page ~ "));
			home.logout();
			report.logTestInfo("logout successful");
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}

	}

}
