package com.salesforce.pages.accounts;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.salesforce.base.BasePage;

public class MergeAcc extends BasePage {
	@FindBy(id = "srch")
	private WebElement txtFindAcc;
	@FindBy(name = "srchbutton")
	private WebElement btnSearch;
	@FindBy(tagName = "table")
	private WebElement table;
	@FindBy(name = "goNext")
	private WebElement btnNext;
	@FindBy(xpath = "//table//tr[1]/th[1]")
	private WebElement col1;
	@FindBy(xpath = "//table//tr[1]/th[2]")
	private WebElement col2;
	@FindBy(xpath = "//input[@value=' Merge ']")
	private WebElement btnMerge;

	public MergeAcc(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public WebElement getBtnSearch() {
		return btnSearch;
	}

	public void eneterAcc(String accName) {
		txtFindAcc.sendKeys(accName);
	}

	public void accountsMerge() {
		List<WebElement> trList = table.findElements(By.xpath("//tr"));
		assertTrue(trList.size() > 1, "less than 2 accounts found.");
		int j=0;
		int limit=5;
		try {
			if (trList.size() > 3) {
				for (int i = 3; i < limit; i++) {//trList.size()
					WebElement row = trList.get(i);

					//Thread.sleep(3000);

					row.findElement(By.id("cid"+j)).click();
					j++;
				}
			}
			String acc1 = trList.get(1).findElement(By.xpath("//table//tr[2]/td")).getText();
			String acc2 = trList.get(2).findElement(By.xpath("//table//tr[3]/td")).getText();

//			System.out.println(acc1 + " " + acc2);

			clickElement(btnNext);

			Assert.assertTrue(waitUntilTitleContains("Merge My Accounts"), "Merge My Accounts page not displayed");
			Thread.sleep(3000);
			String a1 = col1.getText().substring(0, acc1.length() + 1);
			String a2 = col2.getText().substring(0, acc2.length() + 1);

			Assert.assertEquals(a1.trim(), acc1.trim(), "Not the selected account");
			Assert.assertEquals(a2.trim(), acc2.trim(), "Not the selected account2");
			clickElement(btnMerge);

		} catch (Exception e) {
			System.out.println("Merge exception");
		}
	}

}
