
package com.ninza.crm.campaignstest;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;

public class CreateCampaignWithExpectedDateTest {

	public static void main(String[] args) throws Throwable {

		// Properties file
		// 1.Get the java representation object of physical file
		FileReader fr = new FileReader("./src/test/resources/comData.json");
		// Step 2. create the object of JSONparser and use parse method to pass the
		// object of physical file
		JSONParser jp = new JSONParser();
		Object javaObject = jp.parse(fr);

		// Step 3. Convert java object to Json by Downcasting
		JSONObject obj = (JSONObject) javaObject;

		// Step http://4.Read data using get()
		String BROWSER = obj.get("browser").toString();
		String URL = obj.get("url").toString();
		String USERNAME = obj.get("username").toString();
		String PASSWORD = obj.get("password").toString();

		// launch the browser

		ChromeOptions settings = new ChromeOptions();// when ever notifications popups
//						EdgeOptions settings1 = new EdgeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs); // preferences- key - value
														// pair//org.openqa.selenium.NoSuchElementException
		// Launch the browser
//						WebDriver driver = new ChromeDriver(settings);

		WebDriver driver = null; // I don knw which browser to open so I gave WebDriver
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver(settings);
		} else if (BROWSER.equals("safari")) {
			driver = new SafariDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		// Navigate and Login into the application
		LoginPage lp = new LoginPage(driver);
		lp.loginIntoApp(URL, USERNAME, PASSWORD);

		
		//Click on campaign link in home page
		HomePage hp = new HomePage(driver);
		hp.getCampaignLink();
		
		// Create campaign with Mandatory fields
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys("Avengers1a");
		WebElement targSize = driver.findElement(By.name("targetSize"));
		targSize.clear();
		targSize.sendKeys("959466");

		// Get the date after 30 days
		// Create the object of date to get the todays date
		Date date = new Date();// import from java.util package

		// Format Date
		SimpleDateFormat sim = new SimpleDateFormat("MM-dd-yyyy");// after 30 days i cannt select date
		sim.format(date);
		Calendar cal = sim.getCalendar();

		cal.add(Calendar.DAY_OF_MONTH, 30);
		String expectedDate = sim.format(cal.getTime());

		driver.findElement(By.name("expectedCloseDate")).sendKeys(expectedDate);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Verify the succesfull message
		WebElement toastMsg = driver.findElement(By.xpath("//div[@role='alert']"));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));

		String msg = toastMsg.getText();
		System.out.println(msg);
		
		hp.getClosetoastMsgBtn().click();
	
		hp.logout();

	}

}
