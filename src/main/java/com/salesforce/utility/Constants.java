package com.salesforce.utility;

public class Constants {
	public static final String PROP_PATH = System.getProperty("user.dir") + "/src/main/resources/data.properties";
	public static final String passERR01 = "Please enter your password.";
	public static final String passERR02 = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
	public static final String errorContactView = "Error: You must enter a value";
	public static final String GENERATE_REPORT_PATH = System.getProperty("user.dir") + "/extentreport/report.html";
	public static final String SCREENSHOT_PATH = System.getProperty("user.dir") + "/screenshots/";
	public static final String[] choices = { "All Opportunities", "Closing Next Month", "Closing This Month",
			"My Opportunities", "New This Week", "Recently Viewed Opportunities", "Won" };
	public static final String[] choicesLeads = { "All Open Leads", "My Unread Leads", "Recently Viewed Leads",
			"Today's Leads" };
}
