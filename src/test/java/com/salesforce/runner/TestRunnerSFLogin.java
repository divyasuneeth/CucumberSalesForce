package com.salesforce.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = { "src/test/resources/SalesForceLogin.feature" }, 
		glue = { "com/salesforce/steps" },
		plugin = { "pretty","html:target/cucumber-reports/cucumber.html" })
public class TestRunnerSFLogin extends AbstractTestNGCucumberTests {

}
