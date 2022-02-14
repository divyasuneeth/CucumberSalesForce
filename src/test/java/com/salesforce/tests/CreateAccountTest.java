package com.salesforce.tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.salesforce.basetest.BaseTest;
import com.salesforce.pages.accounts.AccViewPage;
import com.salesforce.pages.accounts.AccountsPage;
import com.salesforce.pages.accounts.CreateAccPage;
import com.salesforce.pages.accounts.MergeAcc;
import com.salesforce.pages.accounts.UnsavedReport;

public class CreateAccountTest extends BaseTest {
	static ITestResult result;

	@Test
	public void createAccount_TC10() {
		// Account_Tab
		try {
			String accName = "testSales";
			AccountsPage acc = page.getInsatnce(AccountsPage.class);
			CreateAccPage c_acc = page.getInsatnce(CreateAccPage.class);
			goToAccounts(acc);
			Thread.sleep(3000);
			acc.clickElement(acc.getLnkNewAcc());
			report.logTestInfo("new clicked");
			Assert.assertTrue(acc.waitUntilTitleContains("New Account"));
			c_acc.enterAccName(accName);
			acc.clickElement(c_acc.getbtnSave());
			report.logTestInfo("account name entered and saved");
			Assert.assertTrue(acc.waitUntilTitleContains(accName));
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void createNewView_TC11() {
		String viewName = "SalesTestAccView";

		try {
			AccountsPage acc = page.getInsatnce(AccountsPage.class);
			AccViewPage aview = page.getInsatnce(AccViewPage.class);
			goToAccounts(acc);
			Thread.sleep(3000);
			if (acc.checkOptionPresentSelect(acc.getDdView(), "SalesTestAccView")) {
				acc.clickElement(acc.getViewEdit());
				acc.deleteView();
			}

			aview.createNewView(viewName);
			report.logTestInfo("new view created");
			Assert.assertTrue(acc.chkViewNameInDD(viewName), "new view not selected in the list");
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	@Test
	public void editView_TC12() {
		try {
			String viewName = "SalesTestAccView";
			String new_viewName = "SalesTestAccView";
			AccountsPage acc = page.getInsatnce(AccountsPage.class);
			AccViewPage aview = page.getInsatnce(AccViewPage.class);
			goToAccounts(acc);
			Thread.sleep(3000);
			acc.selectByVisibleText(acc.getDdView(), viewName);
			report.logTestInfo("view selected in dropdown view");
			acc.clickElement(acc.getViewEdit());
			Assert.assertTrue(acc.waitUntilTitleContains("Edit View"), "not in Edit View page");
			aview.editView(new_viewName);
			acc.clickElement(aview.getSaveView());
			report.logTestInfo("edited view name and save");
			Assert.assertEquals(acc.getText(acc.getViewTableCol()), "Last Activity");
		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}

	}

	@Test
	public void mergeAccounts_TC13() {
		try {
			String acc_name = "test";
			AccountsPage acc = page.getInsatnce(AccountsPage.class);
			MergeAcc merge = page.getInsatnce(MergeAcc.class);
			goToAccounts(acc);
			acc.clickElement(acc.getMergeAcc());
			report.logTestInfo("merge account clicked");
			merge.eneterAcc(acc_name);
			merge.clickElement(merge.getBtnSearch());
			report.logTestInfo("account search clicked");
			merge.accountsMerge();
			report.logTestInfo("merge option clicked ");
			Thread.sleep(3000);
			merge.acceptAlert();
			report.logTestInfo("accounts merged");
		} catch (InterruptedException e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}

	}

	@Test
	public void report_TC14() {
		try {
			String report_name = "r01";
			AccountsPage acc = page.getInsatnce(AccountsPage.class);
			UnsavedReport unreport = page.getInsatnce(UnsavedReport.class);
			goToAccounts(acc);
			acc.clickElement(acc.getReport_activity());
			report.logTestInfo("last activity report link clicked");
			acc.waitUntilTitleContains("Unsaved Report");
			unreport.selectDateField();
			report.logTestInfo("date entered");
			unreport.saveReport(report_name);
			report.logTestInfo("report saved");
			Thread.sleep(3000);
			Assert.assertTrue(acc.waitUntilTitleContains(report_name), "Report page not displayed after save");

		} catch (Exception e) {
			report.logTestInfo(e.getMessage());
			result.setStatus(ITestResult.FAILURE);
		}
	}

	private void goToAccounts(AccountsPage acc) {
		login.login(username, password);
		report.logTestInfo("login success");
		Assert.assertTrue(home.waitUntilTitleContains("Home Page ~ "));
		home.clickElement(home.getAccTab());
		acc.clickElement(acc.getPopUp());
	}

}
