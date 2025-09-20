package com.ninza.crm.campaignstest;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.ninza.crm.baseclass.BaseClass;
import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.objectrepository.CampaignsPage;
import com.ninza.crm.objectrepository.CreateCampaignsPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;

@Listeners(com.ninza.crm.listenerutility.ListernerImplementation.class)
public class CreateCampaignTest  extends BaseClass {

	@Test(groups = "Smoke")
	public void createCamapignWithMandataoryFieldsTest() throws EncryptedDocumentException, IOException {

		String campaignName = ex.toReadTheDatafromExcel("Camp", 1, 1);
		String targetSize = ex.toReadTheDatafromExcel("Camp", 1, 2);

		// Click on campaign link in home page
		HomePage hp = new HomePage(driver);
		hp.getCampaignLink().click();

		// Click on create campaign
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCreateCampaign().click();

		// Create campaign with Mandatory fields
		CreateCampaignsPage ccp = new CreateCampaignsPage(driver);
		ccp.getCampaignName().sendKeys(campaignName);
		ccp.getTargetSize().clear();
		ccp.getTargetSize().sendKeys(targetSize);
		ccp.getCreateButton().click();

		// Verify the succesfull message
		WebElement toastMsg = hp.getToastMsg();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));
		String msg = toastMsg.getText();
		System.out.println(msg);
		Assert.assertTrue(msg.contains("Successfully Added"));
//		Campaign iphone Successfully Added
//		Campaign iphone Successfully Added
//		
//		hp.getClosetoastMsgBtn().click();
//		Assert.assertEquals(msg,"Campaign "+campaignName+" Successfully Added","Both the not equal");
//		
		
		hp.getClosetoastMsgBtn().click();
//	    Assert.assertEquals(msg, "Campaign "+campaignName +" Successfully Added","Both the not equal" );
		

		
		
		
		
//		if (msg.contains("Successfully Added")) {
//			System.out.println("Campign created successfully");
//		} else {
//			System.out.println("Campign is not created");
//		}
		
		
		
	}

	@Test(groups = "Regression")
	public void createCampaignWithExpectedCloseDateTest() throws EncryptedDocumentException, IOException {
		

		String campaignName = ex.toReadTheDatafromExcel("Camp", 1, 1);
		String targetSize = ex.toReadTheDatafromExcel("Camp", 1, 2);

		// Click on campaign link in home page
		HomePage hp = new HomePage(driver);
		hp.getCampaignLink().click();

		// Click on create campaign
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCreateCampaign().click();

		// Create campaign with Mandatory fields
		CreateCampaignsPage ccp = new CreateCampaignsPage(driver);
		ccp.getCampaignName().sendKeys(campaignName);
		ccp.getTargetSize().clear();
		ccp.getTargetSize().sendKeys(targetSize);
		ccp.getCreateButton().click();
		
	
		// Get the date after 30 days
				JavaUtility ju = new JavaUtility();
				String expectedDate = ju.getCurrentDateMac();
				ccp.getExpectedCloseDate().sendKeys(expectedDate);
				ccp.getCreateButton().click();


		// Verify the succesfull message
		WebElement toastMsg = hp.getToastMsg();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));

		String msg = toastMsg.getText();
//		System.out.println("Message to be printed "+msg);
		Assert.assertTrue(msg.contains("Successfully Added"));
		

//		if (msg.contains("Successfully Added")) {
//			System.out.println("Campign created successfully");
//		} else {
//			System.out.println("Campign is not created");
//		}
		hp.getClosetoastMsgBtn().click();

		

	}
}
