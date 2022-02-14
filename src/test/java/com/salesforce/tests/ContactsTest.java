package com.salesforce.tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.salesforce.basetest.BaseTest;
import com.salesforce.pages.contacts.ContactView;
import com.salesforce.pages.contacts.ContactsHomePage;
import com.salesforce.pages.contacts.NewContactsPage;
import com.salesforce.utility.Constants;

public class ContactsTest extends BaseTest {

	public static String contact_last_Name = "Potter";
	static ITestResult result;

	@Test
	public void createNewContact_TC25() {
		try {
			String accname = "12test";
			ContactsHomePage cobj = page.getInsatnce(ContactsHomePage.class);
			goToContacts(cobj);
			report.logTestInfo("ContactsTab clicked");
			NewContactsPage ncp = page.getInsatnce(NewContactsPage.class);
			cobj.clickElement(cobj.getBtnNew());
			report.logTestInfo("New button clicked");
			Thread.sleep(3000);
			Assert.assertTrue(cobj.waitUntilTitleContains("New Contact"));
			ncp.createNewContact(contact_last_Name, accname);
			report.logTestInfo("account name entered");
			ncp.clickElement(ncp.getBtnSave());
			report.logTestInfo("save clicked");
			Assert.assertTrue(cobj.waitUntilTitleContains(contact_last_Name));
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void newContactView_TC26() { // check with autoComplete
		try {
			String contact_view_Name = "XYZ";
			ContactsHomePage cobj = page.getInsatnce(ContactsHomePage.class);
			goToContacts(cobj);
			report.logTestInfo("ContactsTab clicked");
			ContactView cvo = page.getInsatnce(ContactView.class);
			cobj.waitUntiVisibility(cobj.getLnkNewView());
			cobj.clickElement(cobj.getLnkNewView());
			report.logTestInfo("new view link clicked");
			Thread.sleep(3000);
			Assert.assertTrue(cobj.waitUntilTitleContains("Create New View"));
			cvo.enterViewname(contact_view_Name);
			report.logTestInfo("view name entered");
			Assert.assertEquals(cvo.getTxtUniViewName().getAttribute("value"), contact_view_Name);
			report.logTestInfo("unique view name checked");
			cvo.clickElement(cvo.getBtnSave());
			report.logTestInfo("save clicked");
			Thread.sleep(3000);
			Assert.assertEquals(cobj.getDdView().getFirstSelectedOption().getText(), contact_view_Name);
			cobj.clearViewContact(contact_view_Name);
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void recentContact_TC27() {
		try {
			ContactsHomePage cobj = page.getInsatnce(ContactsHomePage.class);
			goToContacts(cobj);
			report.logTestInfo("ContactsTab clicked");
			cobj.getDdRecent().selectByValue("2");
			report.logTestInfo("recent dropdown clicked");
			Assert.assertEquals(cobj.getTableHeading(), contact_last_Name);
			report.logTestInfo("check for recent contact done");
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void myContactView_TC28() { /// check the table content after My Contact
		try {
			ContactsHomePage cobj = page.getInsatnce(ContactsHomePage.class);
			goToContacts(cobj);
			report.logTestInfo("ContactsTab clicked");
			cobj.getDdView().selectByVisibleText("My Contacts");
			report.logTestInfo("My Contacts selected");
//			Thread.sleep(3000);
//			cobj.countRows();

		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void contactPage_TC29() {
		try {
			ContactsHomePage cobj = page.getInsatnce(ContactsHomePage.class);
			goToContacts(cobj);
			report.logTestInfo("ContactsTab clicked");
			String name = cobj.clickOnContact();
			report.logTestInfo("contact clicked");
			Assert.assertTrue(cobj.waitUntilTitleContains(name));
		}catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void checkError_TC30() {
		try {
			SoftAssert softassert = new SoftAssert();
			ContactsHomePage cobj = page.getInsatnce(ContactsHomePage.class);
			goToContacts(cobj);
			report.logTestInfo("ContactsTab clicked");
			ContactView cvo = page.getInsatnce(ContactView.class);
			cobj.clickElement(cobj.getLnkNewView());
			report.logTestInfo("new view clicked");
			Thread.sleep(3000);
			Assert.assertTrue(cobj.waitUntilTitleContains("Create New View"));
			cvo.enterUViewName("EFGH");
			report.logTestInfo("viewUniname entered");
			cvo.clickElement(cvo.getBtnSave());
			report.logTestInfo("save clicked");
			softassert.assertTrue(cvo.getError().isDisplayed(), "error message not displayed");
			report.logTestInfo("error checked");
			assertEquals(cvo.getError().getText(), Constants.errorContactView);
	
		}catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void cancelView_TC31() {
		try {
			String viewName = "ABCD";
			String uniViewName = "EFGH";
			ContactsHomePage cobj = page.getInsatnce(ContactsHomePage.class);
			goToContacts(cobj);
			report.logTestInfo("ContactsTab clicked");
			ContactView cvo = page.getInsatnce(ContactView.class);
			cobj.clickElement(cobj.getLnkNewView());
			report.logTestInfo("new view clicked");
			Thread.sleep(3000);
			Assert.assertTrue(cobj.waitUntilTitleContains("Create New View"));
			cvo.enterViewname(viewName);
			cvo.enterUViewName(uniViewName);
			report.logTestInfo("view name and unique name clicked");
			cvo.assertViewNames(viewName, uniViewName);
			cvo.clickElement(cvo.getBtnCancel());
			report.logTestInfo("cancel clicked");
			Thread.sleep(3000);
			Assert.assertTrue(cvo.waitUntilTitleContains("Contacts: Home"));
			Assert.assertNotEquals(cobj.getDdView().getFirstSelectedOption().getText(), viewName);

		}catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void saveNew_View_TC32() {
		try {
			String accname = "abbb";
			contact_last_Name = "SalesTest";
			ContactsHomePage cobj = page.getInsatnce(ContactsHomePage.class);
			goToContacts(cobj);
			report.logTestInfo("ContactsTab clicked");
			NewContactsPage ncp = page.getInsatnce(NewContactsPage.class);
			cobj.clickElement(cobj.getBtnNew());
			report.logTestInfo("new contact clicked");
			Thread.sleep(3000);
			Assert.assertTrue(cobj.waitUntilTitleContains("New Contact"));
			ncp.createNewContact(contact_last_Name, accname);
			report.logTestInfo("account selected");
			ncp.clickElement(ncp.getBtnSaveNew());
			report.logTestInfo("Save & New clicked");
			Assert.assertTrue(ncp.waitUntilTitleContains("Edit: New Contact"));

		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	private void goToContacts(ContactsHomePage cobj) {
		login.login(username, password);
		report.logTestInfo("login success");
		Assert.assertTrue(home.waitUntilTitleContains("Home Page ~ "));
		home.clickElement(home.getContactTab());
		cobj.clickElement(cobj.getPopUp());
		Assert.assertTrue(cobj.waitUntilTitleContains("Contacts: Home"));
	}

}
