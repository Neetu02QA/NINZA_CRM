package com.ninza.crm.baseclass;



import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;

import org.testng.annotations.AfterSuite;

public class BaseClass {

	public WebDriver driver = null;
	public PropertyFileUtility pf = new PropertyFileUtility();
	public ExcelFileUtility ex=new ExcelFileUtility();
	public JavaUtility ju=new JavaUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	public static WebDriver sdriver = null;
	
	@BeforeMethod(groups = {"Smoke","Regression"})
	public void beforeMethod() throws IOException {
		System.out.println("Login");
		String URL = pf.toGetDataFromProperitiesFile("url");
		String USERNAME = pf.toGetDataFromProperitiesFile("username");
		String PASSWORD = pf.toGetDataFromProperitiesFile("password");
//		String URL = System.getProperty("URL");
//		String USERNAME = System.getProperty("username");
//		String PASSWORD = System.getProperty("password");
//		
//		
		LoginPage lp = new LoginPage(driver);
		lp.loginIntoApp(URL, USERNAME, PASSWORD);
	}

	@AfterMethod(groups = {"Smoke","Regression"})
	public void afterMethod() {
		System.out.println("Logout");
		HomePage hp=new HomePage(driver);
		hp.logout();
	}
//	@Parameters("Browser")
	@BeforeClass(groups = {"Smoke","Regression"})
//	public void beforeClass(String BROWSER) throws IOException {
		public void beforeClass() throws IOException {
		System.out.println("Launch the browser");
		String BROWSER = pf.toGetDataFromProperitiesFile("browser");

		//command line
//		mvn -DBrowser=EDGE -DURL=http://49.249.28.218:8098/ -Dusername=rmgyantra -Dpassword=rmgy@9999 test 
//		mvn -DBrowser=CHROME -DURL=http://49.249.28.218:8098/ -Dusername=rmgyantra -Dpassword=rmgy@9999 -Dtest=CreateCampaignTest test
//		mvn test -DBrowser=CHROME -DURL=http://49.249.28.218:8098/ -Dusername=rmgyantra -Dpassword=rmgy@9999 -Dtest=CreateCampaignTest#createCamapignWithMandataoryFieldsTest
//		String BROWSER = System.getProperty("Browser");commmented

		
//		WebDriverManager.chromedriver().setup(); 
		
		ChromeOptions settings = new ChromeOptions();// when ever notifications popups
//		EdgeOptions settings1 = new EdgeOptions();
//		FirefoxOptions settings2 = new FirefoxOptions();
		
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.password_manager_leak_detection", false);
		settings.setExperimentalOption("prefs", prefs); 
		if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(settings);
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		sdriver=driver;
	}

	
	@AfterClass(groups = {"Smoke","Regression"})
	public void afterClass() {
		System.out.println("Close the browser");
		driver.quit();
	}

	@BeforeTest(groups = {"Smoke","Regression"})
	public void BeforeTest() {
		System.out.println("Pre-codiotions for parallel executions");
	}

	@AfterTest(groups ={"Smoke","Regression"})
	public void afterTest() {
		System.out.println("Post-codiotions for parallel executions");
	}

	@BeforeSuite(groups ={"Smoke","Regression"})
	public void beforeSuite() {
		System.out.println("Connect to Database");
	}

	@AfterSuite(groups ={"Smoke","Regression"})
	public void afterSuite() {
		System.out.println("Disconnect to Database");
	}

}