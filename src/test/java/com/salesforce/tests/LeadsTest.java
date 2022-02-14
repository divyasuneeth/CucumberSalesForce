package com.salesforce.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.salesforce.basetest.BaseTest;
import com.salesforce.pages.leads.LeadsHomePage;
import com.salesforce.utility.Constants;

public class LeadsTest extends BaseTest {
	static ITestResult result;

	@Test
	public void checkLeadsTab_TC20() {
		try {
			LeadsHomePage lead = page.getInsatnce(LeadsHomePage.class);
			goToLeads(lead);
			report.logTestInfo("leads tab clicked");
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void leadsSelectView_TC21() {

		try {
			LeadsHomePage lead = page.getInsatnce(LeadsHomePage.class);
			goToLeads(lead);
			report.logTestInfo("leads tab clicked");
			List<WebElement> options = lead.getDdLeads().getOptions();
			for (String s : Constants.choicesLeads) {
				assertTrue(lead.checkOptions(s, options));
			}
			report.logTestInfo("leads dropdown option checked");
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}

	}

	@Test
	public void defaultView_TC22() {

		try {
			LeadsHomePage lead = page.getInsatnce(LeadsHomePage.class);
			goToLeads(lead);
			report.logTestInfo("leads tab clicked");
			lead.getDdLeads().selectByVisibleText("Today's Leads");
			report.logTestInfo("Today's Leads selected");
			home.logout();
			login.login(username, password);
			report.logTestInfo("logout and login done");
			home.clickElement(home.getLeadTab());
			lead.clickElement(lead.getBtnGo());
			Thread.sleep(3000);
			report.logTestInfo("Go clicked");
			assertEquals(lead.getDdLeads().getFirstSelectedOption().getText(), "Today's Leads");

		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void todayView_TC23() {

		try {
			LeadsHomePage lead = page.getInsatnce(LeadsHomePage.class);
			goToLeads(lead);
			report.logTestInfo("leads tab clicked");
			lead.getDdLeads().selectByVisibleText("Today's Leads");
			lead.clickElement(lead.getBtnGo());
			Thread.sleep(3000);
			report.logTestInfo("Go clicked");
			assertEquals(lead.getDdLeads().getFirstSelectedOption().getText(), "Today's Leads");
			report.logTestInfo("Today's Leads displayed");
			
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}
	
	@Test
	public void newLeads_TC24() {
		try {
			String lead_Last_name = "Lasso";
			String company_name = "Lasso";
			LeadsHomePage lead = page.getInsatnce(LeadsHomePage.class);
			goToLeads(lead);
			report.logTestInfo("leads tab clicked");
			lead.clickElement(lead.getBtnNew());
			report.logTestInfo("new btn clicked");
			Thread.sleep(3000);
			Assert.assertTrue(lead.waitUntilTitleContains("New Lead"));
			lead.enterLeadName_companyName(lead_Last_name,company_name);
			report.logTestInfo("lastName and Company Name entered");
			lead.clickElement(lead.getBtnSave());
			report.logTestInfo("save clicked");
			assertNotNull(lead.waitUntilTitleContains(lead_Last_name));

		}catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
		
	}
	

	private void goToLeads(LeadsHomePage obj) {
		login.login(username, password);
		report.logTestInfo("login success");
		Assert.assertTrue(home.waitUntilTitleContains("Home Page ~ "));
		home.clickElement(home.getLeadTab());
		obj.clickElement(obj.getPopUp());
		Assert.assertTrue(obj.waitUntilTitleContains("Leads: Home"));
	}
}
