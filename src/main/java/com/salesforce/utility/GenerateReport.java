package com.salesforce.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class GenerateReport {
	ExtentHtmlReporter htmlreport;
	public static ExtentReports extent;
	public static ExtentTest logger;
	private static GenerateReport ob;
	static String testcaseName;

	private GenerateReport() {

	}

	public static GenerateReport getInstanceOfGenerateReport() {
		if (ob == null) {
			ob = new GenerateReport();
		}
		return ob;
	}

	public void startExtentReport() {
		htmlreport = new ExtentHtmlReporter(Constants.GENERATE_REPORT_PATH);
		extent = new ExtentReports();
		extent.attachReporter(htmlreport);
		extent.setSystemInfo("Host Name", "Salesforce");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Divya");

		htmlreport.config().setDocumentTitle("Test Execution Report");
		htmlreport.config().setReportName("SalesForce regression tests");
		htmlreport.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlreport.config().setTheme(Theme.STANDARD);
	}
	
	public void startSingleTestReport(String testName) {
		testcaseName = testName;
		logger = extent.createTest(testName);
	}

	public void logTestInfo(String message) {

		logger.log(Status.INFO, message);
	}

	public void logTestpassed() {
		logger.log(Status.PASS, MarkupHelper.createLabel(testcaseName + "passed the Test", ExtentColor.GREEN));
	}

	public void logTestFailed() {
		logger.log(Status.FAIL, MarkupHelper.createLabel(testcaseName + " failed the Test", ExtentColor.RED));
	}

	public void endTestReport() {
		extent.flush();
	}


}
