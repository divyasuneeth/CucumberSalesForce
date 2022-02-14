package com.salesforce.pages.usermenu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.base.BasePage;
import com.salesforce.utility.ReadProperty;

public class UserProfilePage extends BasePage {
	@FindBy(id = "tailBreadcrumbNode")
	private WebElement usernametag;
	@FindBy(id = "moderatorMutton")
	private WebElement btnEditdrop;
	@FindBy(css = "a[title='Edit Profile']")
	private WebElement lnkEditProfile;
	@FindBy(id = "aboutMeContentId")
	private WebElement editFrame;
	@FindBy(id = "aboutTab")
	private WebElement aboutTab;
	@FindBy(id = "lastName")
	private WebElement lastName;
	@FindBy(xpath = "//input[@value='Save All']")
	private WebElement saveBtn;
	@FindBy(xpath = "//span[text()='Post']")
	private WebElement lnkPost;
	@FindBy(xpath = "//iframe[@title='Rich Text Editor, publisherRichTextEditor']")
	private WebElement postFrame;
	@FindBy(tagName = "body")
	private WebElement postbody;
	@FindBy(id = "publishersharebutton")
	private WebElement publishBtn;
	@FindBy(xpath = "(//div[@class='feeditemcontent cxfeeditemcontent'])[1]")
	private WebElement post;
	@FindBy(id = "publisherAttachContentPost")
	private WebElement lnkFileUpload;
	@FindBy(id = "chatterUploadFileAction")
	private WebElement btnChatterUpload;
	@FindBy(id = "chatterFile")
	private WebElement chatterFile;
	@FindBy(xpath = "(//div[@class='preamblecontainer displayblock'])[1]")
	private WebElement filepost;
	@FindBy(id = "photoSection")
	private WebElement picblock;
	@FindBy(id = "uploadLink")
	private WebElement lnkPicUpload;
	@FindBy(id = "uploadPhotoContentId")
	private WebElement framePicUpload;
	@FindBy(id = "j_id0:uploadFileForm:uploadInputFile")
	private WebElement btnPicUpload;
	@FindBy(id = "j_id0:uploadFileForm:uploadBtn") // j_id0:uploadFileForm:save
	private WebElement btnPicSave;
	@FindBy(id = "j_id0:j_id7:save")
	private WebElement btnScrTwoSave;
	@FindBy(xpath = "(//img[@title='Edit Profile'])[1]")
	private WebElement imgEdit;
	@FindBy(id = "contactInfo")
	private WebElement contactTab;
	@FindBy(id = "contactInfoContentId")
	private WebElement iframeContactInfo;
	@FindBy(id = "email")
	private WebElement txtemail;

	public WebElement getTxtEmail() {
		return txtemail;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getAboutTab() {
		return aboutTab;
	}

	public WebElement getContactTab() {
		return contactTab;
	}

	public WebElement getBtnEditdrop() {
		return btnEditdrop;
	}

	public WebElement getUsernametag() {
		return usernametag;
	}

	public WebElement getLnkEditProfile() {
		return lnkEditProfile;
	}

	public WebElement getEditFrame() {
		return editFrame;
	}

	public WebElement getLnkPost() {
		return lnkPost;
	}

	public WebElement getPostFrame() {
		return postFrame;
	}

	public WebElement getPublishBtn() {
		return publishBtn;
	}

	public WebElement getLnkFileUpload() {
		return lnkFileUpload;
	}

	public WebElement getBtnChatterUpload() {
		return btnChatterUpload;
	}

	public WebElement getChatterFile() {
		return chatterFile;
	}

	public WebElement getLnkPicUpload() {
		return lnkPicUpload;
	}

	public WebElement getFramePicUpload() {
		return framePicUpload;
	}

	public void setFramePicUpload(WebElement framePicUpload) {
		this.framePicUpload = framePicUpload;
	}

	public WebElement getIframeContactInfo() {
		return iframeContactInfo;
	}

	public UserProfilePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public void enterLastName(String lastN) {
		waitUntiVisibility(lastName);
		lastName.clear();
		lastName.sendKeys(lastN);
	}

	public void enterPost(String txt) {
		waitUntiVisibility(postbody);
		postbody.clear();
		postbody.sendKeys(txt);
	}

	public boolean varifyPost(String postedTxt) {

		return getText(post).contains(postedTxt);
	}

	public void uploadFile() throws InterruptedException {
		waitUntiVisibility(chatterFile);
		chatterFile.sendKeys(ReadProperty.readProperty("uploadFile"));
		Thread.sleep(5000);
		waitUntiVisibility(publishBtn);
		clickElement(publishBtn);
	}

	public boolean verifyFileUpload() {
		waitUntiVisibility(filepost);
		return getText(filepost).contains("posted a file");
	}

	public void uploadPic() throws InterruptedException {
		waitUntiVisibility(picblock);
		Actions action = new Actions(driver);
		action.moveToElement(picblock).perform();
		Thread.sleep(3000);
		waitUntiVisibility(lnkPicUpload);
		clickElement(lnkPicUpload);
		waitUntiVisibility(framePicUpload);
		switchToFrameByName(framePicUpload);
		btnPicUpload.sendKeys(ReadProperty.readProperty("profilePic"));
		waitUntiVisibility(btnPicSave);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", btnPicSave);
		clickElement(btnPicSave);
		waitUntiVisibility(btnScrTwoSave);
		clickElement(btnScrTwoSave);

		Thread.sleep(3000);
		switchBackToDefault();
	}

	public void selectEditImg() {
		clickElement(imgEdit);
		Actions action = new Actions(driver);
		action.moveToElement(imgEdit).build().perform();
		action.click().build().perform();
	}

}
