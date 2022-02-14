package com.salesforce.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.salesforce.basetest.BaseTest;
import com.salesforce.pages.opportunity.NewOpportPage;
import com.salesforce.pages.opportunity.OppHomePage;
import com.salesforce.utility.Constants;

public class OpportunityTest extends BaseTest {
	static ITestResult result;

	@Test
	public void checkOpportunityMenu_TC15() {
		try {
			OppHomePage op = page.getInsatnce(OppHomePage.class);
			goToOpportunityTab(op);
			List<WebElement> options = op.getDdView().getOptions();
			// assertTrue(options.containsAll(Arrays.asList(choices)));
			for (String s : Constants.choices) {
				Assert.assertTrue(op.checkOptions(s, options));
			}
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}

	}

	@Test
	public void createOpty_TC16() {
		try {
			OppHomePage op = page.getInsatnce(OppHomePage.class);
			NewOpportPage nop = page.getInsatnce(NewOpportPage.class);
			// SearchPage srch = page.getInsatnce(SearchPage.class);
			String accname = "abbb";
			String optName = "opt123";
			goToOpportunityTab(op);
			report.logTestInfo("Opportunity Tab selected");
			op.clickElement(op.getBtnNew());

			Thread.sleep(3000);
			Assert.assertTrue(op.waitUntilTitleContains("New Opportunity"));
			nop.enterOptName(optName);
			nop.enterAccount(accname);
			report.logTestInfo("account selected");
			nop.selectCloseDate();
			report.logTestInfo("Close date selected");
			nop.getDdStage().selectByIndex(2);
			report.logTestInfo("Stage selected");
			nop.getDsLead().selectByIndex(2);
			report.logTestInfo("Leads selected");
			// nop.selectCampaign("u", srch);// campaign
			nop.clickElement(nop.getBtnSave());
			Assert.assertTrue(nop.waitUntilTitleContains(optName));

		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void optyPipelineReport_TC17() {
		try {
			OppHomePage op = page.getInsatnce(OppHomePage.class);
			goToOpportunityTab(op);
			report.logTestInfo("Opportunity Tab clicked");
			op.clickElement(op.getLnkOptyPipeline());
			report.logTestInfo("Opportunity pipeline link clicked");
			Assert.assertTrue(op.waitUntilTitleContains("Opportunity Pipeline"));
			report.logTestInfo("in opportunity pipeline report ");
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}

	}

	@Test
	public void stuckOptyReport_TC18() {
		try {
			OppHomePage op = page.getInsatnce(OppHomePage.class);
			goToOpportunityTab(op);
			report.logTestInfo("Opportunity Tab clicked");
			op.clickElement(op.getLnkStuckOpp());
			report.logTestInfo("Stuck Opportunity link clicked");
			Assert.assertTrue(op.waitUntilTitleContains("Stuck Opportunities"));
			report.logTestInfo("in stuck opportunities report ");

		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void qrtSummaryReport_TC19() {
		try {
			OppHomePage op = page.getInsatnce(OppHomePage.class);
			goToOpportunityTab(op);
			report.logTestInfo("Opportunity Tab clicked");
			op.getDdInterval().selectByValue("current");
			report.logTestInfo("interval set to currentFQ");
			String selectedText = op.getDdInterval().getFirstSelectedOption().getText();
			op.getDdInclude().selectByValue("all");
			report.logTestInfo("include all set");
			op.clickElement(op.getBtnRunReport());
			report.logTestInfo("run report clicked");
			Thread.sleep(3000);
			Assert.assertTrue(op.waitUntilTitleContains("Opportunity Report"));
			Assert.assertEquals(op.getDdInterval().getFirstSelectedOption().getText(), selectedText);

		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	private void goToOpportunityTab(OppHomePage obj) {
		login.login(username, password);
		Assert.assertTrue(home.waitUntilTitleContains("Home Page ~ "));
		home.gotoOpportunityTab();
		obj.clickElement(obj.getPopUp());
		Assert.assertTrue(obj.waitUntilTitleContains("Opportunities: Home"));
	}
}
