package com.salesforce.pages.home;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;

public class CalendarPage extends BasePage {

	@FindBy(xpath = "//div[@class='hourRowLabel timeCellDnD evenHour']/a")
	private List<WebElement> listTimeSlot;
	@FindBy(id = "evt5")
	private WebElement txtSubject;
	@FindBy(xpath = "//img[contains(@title,'Subject')]")
	private WebElement imgSubject;
	@FindBy(xpath = "//a[text()='Other']")
	private WebElement combo_other;
	@FindBy(id = "EndDateTime_time")
	private WebElement ddEndTime;
	@FindBy(id = "simpleTimePicker")
	private WebElement timePicker;
	@FindBy(xpath = "//div[@class='simpleHour']")
	private List<WebElement> listTimeCal;
	@FindBy(name = "save")
	private WebElement btnSave;
	@FindBy(id = "IsRecurrence")
	private WebElement chkRecurrence;
	@FindBy(id = "recpat")
	private WebElement divRec;
	@FindBy(id = "rectypeftw")
	private WebElement rdweekly;
	@FindBy(id = "w")
	private WebElement divWeek;
	@FindBy(id = "wi")
	private WebElement recDaysCount;
	@FindBy(xpath = "//div[@class='periodElementGroup']/div[2]/input")
	private List<WebElement> list_day_of_week;
	@FindBy(id = "RecurrenceEndDateOnly")
	private WebElement recEndDt;

	public CalendarPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public WebElement getTxtSubject() {
		return txtSubject;
	}

	public WebElement getImgSubject() {
		return imgSubject;
	}

	public WebElement getCombo_Other() {
		return combo_other;
	}

	public WebElement getDdEndTime() {
		return ddEndTime;
	}

	public WebElement getTimePicker() {
		return timePicker;
	}

	public WebElement getBtnSave() {
		return btnSave;
	}

	public WebElement getChkRecurrence() {
		return chkRecurrence;
	}

	public WebElement getDivRec() {
		return divRec;
	}

	public WebElement getWeekly() {
		return rdweekly;
	}

	public WebElement getDivWeek() {
		return divWeek;
	}

	public WebElement getRecDaysCount() {
		return recDaysCount;
	}

	public void findTimeSlot(String time) {
		wait.until(ExpectedConditions.visibilityOfAllElements(listTimeSlot));

		for (WebElement e : listTimeSlot) {
			if (e.getText().contains(time)) {
				e.click();
				break;
			}
		}
	}

	public int countWindows() {
		Set<String> windows = driver.getWindowHandles();
		return windows.size();
	}

	public void goBackToCurrentWindow(String curWindow) {
		int windows = countWindows();
		if ((windows < countWindows()))
			;
		driver.switchTo().window(curWindow);
	}

	public void selectEndTimeInNewEven(String time) {
		wait.until(ExpectedConditions.visibilityOfAllElements(listTimeCal));

		for (WebElement e : listTimeCal) {
			if (e.getText().contains(time)) {
				e.click();
				break;
			}
		}
	}

	public void selectDayOfWeek() {
		//wait.until(ExpectedConditions.visibilityOfAllElements(list_day_of_week));
		String dayOfweek = weekSelction();
		for (WebElement e : list_day_of_week) {
			if (e.getText().toLowerCase().contains(dayOfweek.toLowerCase())) {
				e.click();
				break;
			}
		}
	}

	private String weekSelction() {
		LocalDateTime date = LocalDateTime.now();
//		int d = date.getDayOfWeek().getValue();
//		if (d > 5)
//			d = 7 - d;
//		(int) Math.pow(2, d);
		return date.getDayOfWeek().toString();
	}

	public void enterEndRecDate() {
		recEndDt.clear();
		recEndDt.sendKeys(date_two_week());
	}

	private String date_two_week() {
		Date dateNow = new Date();
		int noOfDays = 14; // i.e two weeks
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateNow);
		calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		return format.format(date);
	}

}
