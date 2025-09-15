package com.ninza.crm.campaignstest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CampaignTest {

	@Test
	public  void  createCampaignWithMandatoryFieldsTest() throws InterruptedException, IOException {

		// Reading data from properties file
		FileInputStream fis = new FileInputStream("C:\\Users\\QSP\\Documents\\http://LoginData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String browser = prop.getProperty("Browser");
		String url = prop.getProperty("Url");
		String username = prop.getProperty("Username");
		String password = prop.getProperty("Password");

		// Reading the data from Excel file
		FileInputStream fis1 = new FileInputStream("C:\\Users\\QSP\\Documents\\NinzaCRM_M28.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Row r = wb.getSheet("Campaign").getRow(1);
		String campaignName = r.getCell(2).getStringCellValue();
		String targetSize = r.getCell(3).getStringCellValue();
		wb.close();

		WebDriver driver = null;
		if (browser.equalsIgnoreCase("CHROME"))
			driver = new ChromeDriver();
		else if (browser.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
		else if (browser.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("Safari"))
			driver = new SafariDriver();

		// Login to NINZA CRM
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(url);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();

		// Generating random number
		Random random = new Random();
		int ranInt = random.nextInt(1000);

		// Create Campaign
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(campaignName + ranInt);
		WebElement targetSizeTF = driver.findElement(By.name("targetSize"));
		targetSizeTF.clear();
		targetSizeTF.sendKeys(targetSize);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

		// Validation
		WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));
		String msg = toastMsg.getText();
		if (msg.contains(campaignName + ranInt))
			System.out.println("Campaign Created");
		else
			System.out.println("Campaign Not Created");
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();

		// Logout
		WebElement userIcon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions action = new Actions(driver);
		action.moveToElement(userIcon).perform();
		WebElement logoutBtn = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		action.moveToElement(logoutBtn).click().perform();

		driver.quit();
	}
	
	@Test
	public void createCampaignWithStatusTest() throws IOException {
		// Create java representation object of physical file
				FileInputStream fis = new FileInputStream("C:\\Users\\QSP\\Documents\\http://LoginData.properties");
				// Create an object of Properties
				Properties prop = new Properties();
				// Load all the keys
				prop.load(fis);
				String browser = prop.getProperty("Browser");
				String url = prop.getProperty("Url");
				String username = prop.getProperty("Username");
				String password = prop.getProperty("Password");

				// Reading the data from Excel file
				FileInputStream fis1 = new FileInputStream("C:\\Users\\QSP\\Documents\\NinzaCRM_M28.xlsx");
				Workbook wb = WorkbookFactory.create(fis1);
				Row r = wb.getSheet("Campaign").getRow(4);
				String campaignName = r.getCell(2).getStringCellValue();
				String targetSize = r.getCell(3).getStringCellValue();
				String status = r.getCell(4).getStringCellValue();
				wb.close();

				WebDriver driver = null;
				if (browser.equalsIgnoreCase("CHROME"))
					driver = new ChromeDriver();
				else if (browser.equalsIgnoreCase("Edge"))
					driver = new EdgeDriver();
				else if (browser.equalsIgnoreCase("Firefox"))
					driver = new FirefoxDriver();
				else if (browser.equalsIgnoreCase("Safari"))
					driver = new SafariDriver();

				// Login to NINZA CRM
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
				driver.get(url);
				driver.findElement(By.id("username")).sendKeys(username);
				driver.findElement(By.id("inputPassword")).sendKeys(password);
				driver.findElement(By.xpath("//button[text()='Sign In']")).click();

				// Generating random number
				Random random = new Random();
				int ranInt = random.nextInt(1000);

				// Create Campaign
				driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
				driver.findElement(By.name("campaignName")).sendKeys(campaignName + ranInt);
				driver.findElement(By.name("campaignStatus")).sendKeys(status);
				WebElement targetSizeTF = driver.findElement(By.name("targetSize"));
				targetSizeTF.clear();
				targetSizeTF.sendKeys(targetSize);
				driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

				// Validation
				WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOf(toastMsg));
				String msg = toastMsg.getText();
				if (msg.contains(campaignName + ranInt))
					System.out.println("Campaign Created");
				else
					System.out.println("Campaign Not Created");
				driver.findElement(By.xpath("//button[@aria-label='close']")).click();

				// Logout
				WebElement userIcon = driver.findElement(By.xpath("//div[@class='user-icon']"));
				Actions action = new Actions(driver);
				action.moveToElement(userIcon).perform();
				WebElement logoutBtn = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
				action.moveToElement(logoutBtn).click().perform();

				driver.quit();	
	}
	
	@Test
	public void createCampaignWithExpectedCloseDate() throws IOException {
		// Reading data from properties file
				FileInputStream fis = new FileInputStream("C:\\Users\\QSP\\Documents\\http://LoginData.properties");
				Properties prop = new Properties();
				prop.load(fis);
				String browser = prop.getProperty("Browser");
				String url = prop.getProperty("Url");
				String username = prop.getProperty("Username");
				String password = prop.getProperty("Password");

				// Reading the data from Excel file
				FileInputStream fis1 = new FileInputStream("C:\\Users\\QSP\\Documents\\NinzaCRM_M28.xlsx");
				Workbook wb = WorkbookFactory.create(fis1);
				Row r = wb.getSheet("Campaign").getRow(7);
				String campaignName = r.getCell(2).getStringCellValue();
				String targetSize = r.getCell(3).getStringCellValue();
				wb.close();

				// Generating random number
				Random random = new Random();
				int ranInt = random.nextInt(1000);

				// Get date after 30 days
				Date date = new Date();
				SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
				sim.format(date);
				Calendar cal = sim.getCalendar();
				cal.add(Calendar.DAY_OF_MONTH, 30);
				String dateRequired = sim.format(cal.getTime());

				WebDriver driver = null;
				if (browser.equalsIgnoreCase("CHROME"))
					driver = new ChromeDriver();
				else if (browser.equalsIgnoreCase("Edge"))
					driver = new EdgeDriver();
				else if (browser.equalsIgnoreCase("Firefox"))
					driver = new FirefoxDriver();
				else if (browser.equalsIgnoreCase("Safari"))
					driver = new SafariDriver();

				// Login to NINZA CRM
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
				driver.get(url);
				driver.findElement(By.id("username")).sendKeys(username);
				driver.findElement(By.id("inputPassword")).sendKeys(password);
				driver.findElement(By.xpath("//button[text()='Sign In']")).click();

				// Create Campaign
				driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
				driver.findElement(By.name("campaignName")).sendKeys(campaignName);
				WebElement targetSizeTF = driver.findElement(By.name("targetSize"));
				targetSizeTF.clear();
				targetSizeTF.sendKeys(targetSize);
				WebElement expCloseDate = driver.findElement(By.xpath("//input[@type='date']"));
				Actions action = new Actions(driver);
				http://action.click(expCloseDate).sendKeys(dateRequired).perform();
				driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

				// Validation
				WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOf(toastMsg));
				String msg = toastMsg.getText();
//			    Assert.assertTrue(msg.contains(campaignName),"Campaign Not Created");
				SoftAssert soft=new SoftAssert();
				soft.assertTrue(msg.contains(campaignName),"Campaign Not Created");
			
				driver.findElement(By.xpath("//button[@aria-label='close']")).click();

				// Logout
				WebElement userIcon = driver.findElement(By.xpath("//div[@class='user-icon']"));
				action.moveToElement(userIcon).perform();
				WebElement logoutBtn = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
				action.moveToElement(logoutBtn).click().perform();

				driver.quit();
	}
}
