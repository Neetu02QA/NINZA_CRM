package com.ninza.crm.objectrepository;

import java.awt.Desktop.Action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ninza.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage {

	WebDriver driver;
	WebDriverUtility wLib = new WebDriverUtility();
	
	
	

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(linkText = "Campaigns")
	private WebElement campaignLink;

	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;

	@FindBy(linkText = "Leads")
	private WebElement leadsLink;

	@FindBy(linkText = "Opportunites")
	private WebElement opportunitesLink;

	@FindBy(linkText = "Products")
	private WebElement productsLink;

	@FindBy(linkText = "Quotes")
	private WebElement quotesLink;

	@FindBy(linkText = "Purchase Order")
	private WebElement purchaseOrderLink;
	
	@FindBy(xpath = "//div[@role='alert']")
	private WebElement toastMsg;
	
	@FindBy(xpath = "//button[@aria-label='close']")
	private WebElement closetoastMsgBtn;
	
	
	@FindBy(className = "user-icon")
	private WebElement userIcon;
	
@FindBy(xpath = "//div[text()='Logout ']")
private WebElement logoutBtn;// I don have this logout xpath - can we take linktext

//	@FindBy(className ="div.dropdown-item.logout" )
//	private WebElement logoutBtn;
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getToastMsg() {
		return toastMsg;
	}

	public void setToastMsg(WebElement toastMsg) {
		this.toastMsg = toastMsg;
	}

	public WebElement getClosetoastMsgBtn() {
		return closetoastMsgBtn;
	}

	public void setClosetoastMsgBtn(WebElement closetoastMsgBtn) {
		this.closetoastMsgBtn = closetoastMsgBtn;
	}

	public WebElement getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(WebElement userIcon) {
		this.userIcon = userIcon;
	}

	public WebElement getLogoutBtn() {
		return logoutBtn;
	}

	public void setLogoutBtn(WebElement logoutBtn) {
		this.logoutBtn = logoutBtn;
	}

	public void setCampaignLink(WebElement campaignLink) {
		this.campaignLink = campaignLink;
	}

	public void setContactsLink(WebElement contactsLink) {
		this.contactsLink = contactsLink;
	}

	public void setLeadsLink(WebElement leadsLink) {
		this.leadsLink = leadsLink;
	}

	public void setOpportunitesLink(WebElement opportunitesLink) {
		this.opportunitesLink = opportunitesLink;
	}

	public void setProductsLink(WebElement productsLink) {
		this.productsLink = productsLink;
	}

	public void setQuotesLink(WebElement quotesLink) {
		this.quotesLink = quotesLink;
	}

	public void setPurchaseOrderLink(WebElement purchaseOrderLink) {
		this.purchaseOrderLink = purchaseOrderLink;
	}

	public WebElement getCampaignLink() {
		return campaignLink;
	}

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getLeadsLink() {
		return leadsLink;
	}

	public WebElement getOpportunitesLink() {
		return opportunitesLink;
		
	}

	public WebElement getProductsLink() {
		return productsLink;
	}

	public WebElement getQuotesLink() {
		return quotesLink;
	}

	public WebElement getPurchaseOrderLink() {
		return purchaseOrderLink;
	}
	
	public void logout() {
//		Actions action = new Actions(driver);
//		action.moveToElement(userIcon).perform();
//		action.moveToElement(logoutBtn).click().perform();
//		
		
		wLib.mouseHoverOnWebelement(driver, userIcon);
		wLib.ClickOnWebElement(driver, logoutBtn);
		
	}

	
	
	
	
	
	
	//This is business library  create method for reusabilty
	

}
