package com.ninza.crm.campaignstest;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;


public class CreateCampaignWithMandatoryFieldsTest {
	
//	@DataProvider
//	public Object[][] login() throws IOException{
//		Object[][] obj = new Object[1][2];	//how many rows and columns are there no indexing one row n two columns
//		PropertyFileUtility pf = new PropertyFileUtility();
//		pf.toGetDataFromProperitiesFile("username");
		
		@DataProvider
		public Object[][] login() throws EncryptedDocumentException, IOException {
			
			ExcelFileUtility ex = new ExcelFileUtility();
			int lastRow = ex.toGetTheRowcount("Campaigns");
			
			Object[][] obj = new Object[lastRow][2];
			
			for (int i = 0; i < lastRow; i++) {
				obj[i][0]=ex.toReadTheDatafromExcel("Campaigns", i+1, 1);
				obj[i][1]=ex.toReadTheDatafromExcel("Campaigns", i+1, 2);
			}
			return obj;
	}
	
	
	//Excel sheet - store the multiple set of data 
		//data provider - using multiple script with multiple set of data 
		
	
	
	
	@Test(dataProvider = "login")
	public  void createCampaignWithMandatoryFieldsTest(String campaignName, String targetSize)  throws Throwable {
		
		
		//Properties file
		PropertyFileUtility pf = new PropertyFileUtility();
//		ExcelFileUtility elib = new ExcelFileUtility();
		WebDriverUtility wlib = new WebDriverUtility();
				
				
				//get the values 
			
				String BROWSER = pf.toGetDataFromProperitiesFile("browser");
				String URL = pf.toGetDataFromProperitiesFile("url");
				String USERNAME = pf.toGetDataFromProperitiesFile("username");
				String PASSWORD = pf.toGetDataFromProperitiesFile("password");
				
				//excel file
//				ExcelFileUtility ex = new ExcelFileUtility();
//				String campaignName= ex.toReadTheDatafromExcel("Camp", 1, 1);
//				String targetSize = ex.toReadTheDatafromExcel("Camp", 1, 2);
//				
				
				//launch the browser
				
						ChromeOptions settings = new ChromeOptions();//when ever notifications popups
//						EdgeOptions settings1 = new EdgeOptions();
						Map<String, Object> prefs = new HashMap<>();
					    prefs.put("profile.password_manager_leak_detection", false);
					    settings.setExperimentalOption("prefs", prefs);      //preferences- key - value pair//org.openqa.selenium.NoSuchElementException
						//Launch the browser
//						WebDriver driver = new ChromeDriver(settings);
						
					    
					    
						WebDriver driver = null;                         //I don knw which browser to open so I gave WebDriver
						if(BROWSER.equals("chrome")) {
							driver = new ChromeDriver(settings);
						}else if (BROWSER.equals("safari")) {
							driver = new SafariDriver();
						}else if (BROWSER.equals("firefox")) {
							driver = new FirefoxDriver();
						}
							
						

						// Navigate and Login into the application
						LoginPage lp = new LoginPage(driver);
						lp.loginIntoApp(URL, USERNAME, PASSWORD);

						
						//Click on campaign link in home page
						HomePage hp = new HomePage(driver);
						hp.getCampaignLink();
							
				

				//Create campaign with Mandatory fields
				driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
				driver.findElement(By.name("campaignName")).sendKeys(campaignName);
				WebElement targSize = driver.findElement(By.name(targetSize));
				targSize.clear();
				targSize.sendKeys("959466");
				driver.findElement(By.xpath("//button[@type='submit']")).click();

				//Verify the successful message
				WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));

				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.visibilityOf(toastMsg));

				String msg = toastMsg.getText();

				if (msg.contains("Avengers")) {
				    System.out.println("Campaign created successfully");
				} else {
				    System.out.println("Campaign is not created");
				}
	}
	
	
	
}
