package com.salesforce.tests;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.salesforce.basetest.BaseTest;
import com.salesforce.pages.home.AllTabsPage;
import com.salesforce.pages.home.CalendarPage;
import com.salesforce.pages.home.CustomizeTabPage;
import com.salesforce.pages.usermenu.UserProfilePage;

public class RandomScenariosTest extends BaseTest {
	static ITestResult result;

	@Test
	public void verifyNameHomeTabTC33() {
		String name = "Divya";

		try {
			goToHome();
			home.clickElement(home.getHomeTab());
			if (home.checkElementPresent(home.getPopUp()))
				home.clickElement(home.getPopUp());
			Assert.assertTrue(home.waitUntilTitleContains("Salesforce - Developer Edition"));
			report.logTestInfo("home tab clicked");
			Assert.assertTrue(home.getUserNameDisplay().getText().toLowerCase().contains(name.toLowerCase()));
			home.clickElement(home.getUserNameDisplay());
			report.logTestInfo("user name clicked");
			Assert.assertTrue(home.waitUntilTitleContains(name));
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void verifyLastNameTC34() {
		try {
			String name = "Divya";
			String lastname = "ABCD";
			UserProfilePage user = page.getInsatnce(UserProfilePage.class);
			goToHome();
			home.clickElement(home.getHomeTab());
			if (home.checkElementPresent(home.getPopUp()))
				home.clickElement(home.getPopUp());
			Assert.assertTrue(home.waitUntilTitleContains("Salesforce - Developer Edition"));
			report.logTestInfo("home tab clicked");
			Assert.assertTrue(home.getUserNameDisplay().getText().toLowerCase().contains(name.toLowerCase()));
			home.clickElement(home.getUserNameDisplay());
			report.logTestInfo("User Name clicked");
			Assert.assertTrue(home.waitUntilTitleContains(name));
			user.selectEditImg();
			report.logTestInfo("edit image clicked");
			user.waitUntiVisibility(user.getContactTab());
			Assert.assertTrue(user.getContactTab().isDisplayed());
			user.switchToFrameByName(user.getIframeContactInfo());
			Assert.assertTrue(driver.switchTo().activeElement().equals(user.getTxtEmail()),
					"Contact Tab not selected by default");
			user.clickElement(user.getAboutTab());
			user.enterLastName(lastname);
			report.logTestInfo("lastName entered");
			user.clickElement(user.getSaveBtn());
			user.switchBackToDefault();
			report.logTestInfo("switched to default");
			user.waitUntiVisibility(user.getUsernametag());
			Assert.assertTrue(user.getUsernametag().getText().toLowerCase().contains(lastname.toLowerCase()));
			home.waitUntiVisibility(home.getUserNavLabel());
			Assert.assertTrue(home.getUserNavLabel().getText().toLowerCase().contains(lastname.toLowerCase()));
			Assert.assertTrue(user.waitUntilTitleContains(lastname));
			report.logTestInfo("Name displayed with the lastname changed");
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void verifyTabCustomiTC35() {
		try {
			String selectValue = "CollaborationGroup";
			AllTabsPage all = page.getInsatnce(AllTabsPage.class);
			CustomizeTabPage ctab = page.getInsatnce(CustomizeTabPage.class);
			goToHome();
			home.clickElement(home.getAllTabs());
			report.logTestInfo("all tabs  selected");
			Assert.assertTrue(home.waitUntilTitleContains("All Tabs"));
			all.waitUntiVisibility(all.getCustom());
			all.clickElement(all.getCustom());
			Assert.assertTrue(all.waitUntilTitleContains("Customize"));
			ctab.waitSelectDD();
			// check if option in seleceted tab list--- need to handle
			ctab.getDdSelectedTab().selectByValue(selectValue);
			report.logTestInfo("selected tab by value: " + selectValue);
			String optionselected = ctab.getDdSelectedTab().getFirstSelectedOption().getText();
			ctab.clickElement(ctab.getBtnLeft());
			Assert.assertFalse(ctab.chkSelectedOPtion(optionselected));
			ctab.waitAvailDD();
			Assert.assertTrue(ctab.chkAvailabeOPtion(optionselected));
			ctab.clickElement(ctab.getBtnSave());
			report.logTestInfo("save clicked");
//			mysoftAssert.assertNotNull(waitUntilTitleContains("All Tabs"));
			Assert.assertFalse(all.findTab(optionselected));
			home.logout();
			login.login(username, password);
			report.logTestInfo("login again successful");
			Assert.assertFalse(all.findTab(optionselected));
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}

	}

	@Test
	public void blockingEventTC36() {
		CalendarPage cal = page.getInsatnce(CalendarPage.class);
		String startTime = "8:00 PM";
		String endTime = "9:00 PM";
		createCalenderEvent(startTime, endTime);
		cal.waitUntiVisibility(cal.getBtnSave());
		cal.clickElement(cal.getBtnSave());
		report.logTestInfo("calender event created");
		Assert.assertTrue(cal.waitUntilTitleContains("Calendar for"));

//		checkEventCreated(startTime, endTime);

	}

	@Test
	public void TC37() {
		try {
			String startTime = "4:00 PM";
			String endTime = "7:00 PM";
			createCalenderEvent(startTime, endTime);
			CalendarPage cal = page.getInsatnce(CalendarPage.class);
			cal.waitUntiVisibility(cal.getChkRecurrence());
			cal.clickElement(cal.getChkRecurrence());
			report.logTestInfo("recurrence checkbox selected");
			Assert.assertTrue(cal.checkElementPresent(cal.getDivRec()));
			cal.waitUntiVisibility(cal.getWeekly());
			cal.clickElement(cal.getWeekly());
			report.logTestInfo("weekly radio btn selected");
			Assert.assertTrue(cal.checkElementPresent(cal.getDivWeek()));
			cal.waitUntiVisibility(cal.getRecDaysCount());
			Assert.assertEquals(cal.getRecDaysCount().getAttribute("value"), "1");

			cal.selectDayOfWeek();
			report.logTestInfo("day of the week selected");
			cal.enterEndRecDate();
			report.logTestInfo("end date set to two weeks from today");
			cal.clickElement(cal.getBtnSave());
			cal.waitUntilTitleContains("Calendar for");
			report.logTestInfo("Calendar event saved");
			// checkEventCreated(startTime, endTime);
			// clickElement(waitUntil_VisibilityLoc("xpath", "//img[contains(@title,'Month
			// View')]"));
			// mysoftAssert.assertNotNull(waitUntilTitleContains("Month View"));

			/// assert event present for current date and week.
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}

	}

	public void createCalenderEvent(String start_time, String end_time) {
		try {
			CalendarPage cal = page.getInsatnce(CalendarPage.class);
			goToHome();
			home.clickElement(home.getHomeTab());
			if (home.checkElementPresent(home.getPopUp()))
				home.clickElement(home.getPopUp());
			report.logTestInfo("home tab clicked");
			Assert.assertTrue(home.waitUntilTitleContains("Salesforce - Developer Edition"));
			Date dateTime = new Date();
			SimpleDateFormat format_date = new SimpleDateFormat("EEEEE MMMMM dd', 'yyyy");
			home.waitUntiVisibility(home.getLnkDate());
			home.clickElement(home.getLnkDate());
			report.logTestInfo("date on home clicked");
			Thread.sleep(3000);
			Assert.assertTrue(cal.waitUntilTitleContains("Calendar for"));
			cal.findTimeSlot(start_time);
			report.logTestInfo("time slot clicked");
			Assert.assertTrue(cal.waitUntilTitleContains("Calendar: New Event"));
			Assert.assertEquals(cal.getTxtSubject(), driver.switchTo().activeElement());
			cal.clickElement(cal.getImgSubject());
			report.logTestInfo("subject image clicked");
			String currentwindow = cal.switchWindow();

			Assert.assertTrue(cal.waitUntilTitleContains("ComboBox"));
			cal.waitUntiVisibility(cal.getCombo_Other());
			cal.clickElement(cal.getCombo_Other());
			report.logTestInfo("other link clicked");
			cal.goBackToCurrentWindow(currentwindow);
			cal.waitUntiVisibility(cal.getDdEndTime());
			cal.clickElement(cal.getDdEndTime());
			report.logTestInfo("end date clicked");
			cal.waitUntiVisibility(cal.getTimePicker());
			Assert.assertTrue(cal.getTimePicker().isDisplayed());
			cal.selectEndTimeInNewEven(end_time);
			report.logTestInfo("end time selected");
			Assert.assertEquals(cal.getDdEndTime().getAttribute("value"), end_time);

		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

//	private static void checkEventCreated(String startTime, String endTime) {
//		WebElement cal = waitUntil_VisibilityLoc("xpath", "//span/a/span[contains(text(),'Other')]");
//		Actions action = new Actions(driver);
//		action.moveToElement(cal).build().perform();
//		staticWait(3000);
//
//		/* 'Other' event in the time slot 8:00PM to 9:00PM, as a link. */
//
//		if (waitUntil_VisibilityLoc("css", "div.pbSubsection").isDisplayed()) {
//			WebElement popEvent = waitUntil_VisibilityLoc("css", "div.pbSubsection");
//			action.moveToElement(popEvent).build().perform();
//			WebElement table = waitUntil_VisibilityLoc("xpath", "//table[@class='detailList']");
//			List<WebElement> rows = table.findElements(By.tagName("tr"));
//
//			for (WebElement row : rows) {
//				List<WebElement> tds = row.findElements(By.tagName("td"));
//				for (WebElement td : tds) {
//
//					if ((td.getText().split(",")).length > 1) {
//
//						String test_time = td.getText().split(",")[1].trim();
//
//						if (test_time.split("C").length > 1) {
//							String stTime = test_time.split("C")[0].trim();
//							mysoftAssert.assertEquals(stTime, startTime);
//
//						} else {
//							mysoftAssert.assertEquals(test_time, endTime);
//						}
//					}
//				}
//			}
//
//		}
//	}

	private void goToHome() {
		login.login(username, password);
		report.logTestInfo("login success");
		Assert.assertTrue(home.waitUntilTitleContains("Home Page ~ "));
	}
}
