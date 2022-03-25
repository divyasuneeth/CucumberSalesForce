package com.salesforce.steps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.salesforce.base.Page;
import com.salesforce.pages.home.HomePage;
import com.salesforce.pages.sfdclogin.ForgotPasswordPage;
import com.salesforce.pages.sfdclogin.SFLoginPage;
import com.salesforce.utility.ReadProperty;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SFLoginStepDef {
	private WebDriver driver;
	private WebDriverWait wait;
	private static SFLoginPage login;
	private static HomePage home;
	private static ForgotPasswordPage fpage;
	private static Page page;

	
	@Before
	//@Parameters({"browser"})
	public void setup() {
		String browser="chrome";
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			wait = new WebDriverWait(driver, 10);
			page = new Page(driver, wait);
		}

	}

	@Given("user navigates to salesforce login page")
	public void while_user_on_saleforce_login_page() {
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
	}

	@When("user on {string}")
	public void user_on(String screen) {
		if (screen.equalsIgnoreCase("SFLoginPage")) {
			login = page.getInsatnce(SFLoginPage.class);
		} else if (screen.equalsIgnoreCase("HomePage")) {
			home = page.getInsatnce(HomePage.class);
		} else if (screen.equalsIgnoreCase("ForgotPasswordPage")) {
			fpage = page.getInsatnce(ForgotPasswordPage.class);
		}
	}

	@When("{string} logs in")
	public void logs_in(String username) {
		String password = ReadProperty.readProperty("password");
		login.login(username, password);
	}

	@Then("the page title is {string}")
	public void the_page_title_is(String title) {
		assertTrue(wait.until(ExpectedConditions.titleContains(title)));
		driver.getTitle();
	}

	@When("{string} logs in with wrong password")
	public void logs_in_with_wrong_password(String username) {
		login.enterusername(username);
		login.clickLogin();
	}

	@Then("error message {string} is displayed")
	public void error_message_is_displayed(String errTxt) {
		wait.until(ExpectedConditions.visibilityOf(login.getLblError()));
		Assert.assertEquals(login.getErrTxt(), errTxt);
	}

	@When("{string} logs in with remember Me checked")
	public void logs_in_with_remember_me_checked(String username) {
		login.click_rememberMe();
		login.login(username, ReadProperty.readProperty("password"));

	}

	@When("logs out")
	public void logs_out() {
		home.logout();
	}

	@Then("{string} should be saved in the username field")
	public void should_be_saved_in_the_username_field(String username) {
		wait.until(ExpectedConditions.visibilityOf(login.getIdCard()));
		assertEquals(login.getIdCardText(), username);
	}

	@When("clicks forgot password link")
	public void clicks_forgot_password_link() {
		login.click_forgotPass();
	}

	@When("enters {string} and clicks continue")
	public void on_forget_password_page_enters_and_clicks_continue(String username) {
		ForgotPasswordPage fpage = new ForgotPasswordPage(driver, wait);
		fpage.entertxtUser(username);
		fpage.clickContinue();

	}

	@Then("reset password email is sent and title of the page is {string}")
	public void reset_password_email_is_sent_and_title_of_the_page_is(String title) {
		Assert.assertTrue(fpage.chkTitleTxt(title));
	}

	@After
	public void tearDown() {
		driver.close();
	}
}
