package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CreateCampaignsPage {

	WebDriver driver;

	public CreateCampaignsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="campaignName")
	private WebElement campaignName;
	
	@FindBy(name="targetSize")
	private WebElement targetSize;
	
	@FindBy(name="expectedCloseDate")
	private WebElement expectedCloseDate;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement createButton;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(WebElement campaignName) {
		this.campaignName = campaignName;
	}

	public WebElement getTargetSize() {
		return targetSize;
	}

	public void setTargetSize(WebElement targetSize) {
		this.targetSize = targetSize;
	}

	public WebElement getExpectedCloseDate() {
		return expectedCloseDate;
	}

	public void setExpectedCloseDate(WebElement expectedCloseDate) {
		this.expectedCloseDate = expectedCloseDate;
	}

	public WebElement getCreateButton() {
		return createButton;
	}

	public void setCreateButton(WebElement createButton) {
		this.createButton = createButton;
	}
	
}
