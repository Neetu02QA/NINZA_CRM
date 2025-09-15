package com.ninza.crm.baseclass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;

import com.ninza.crm.generic.fileutility.ExcelFileUtility;
import com.ninza.crm.generic.fileutility.PropertyFileUtility;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepository.LoginPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.HomePage;
//import com.mysql.jdbc.Driver;

//import fileutility.ExcelUtility;
//import fileutility.PropertyFileUtility;
//import javautility.JavaUtility;
//import objectrepository.HomePage;
//import objectrepository.LoginPage;
//import webdriverutility.WebDriverUtility;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseClass1 {
	public PropertyFileUtility pLib = new PropertyFileUtility();
	public ExcelFileUtility eLib = new ExcelFileUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public WebDriver driver = null;

	@BeforeSuite
	public void beforeSuite() throws SQLException {

		System.out.println("Database connection");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("precondition");
	}

	@BeforeClass
	public void beforeClass() throws IOException {
		String browser = pLib.toGetDataFromProperitiesFile("Browser");
		if (browser.equalsIgnoreCase("CHROME"))
			driver = new ChromeDriver();
		else if (browser.equalsIgnoreCase("Edge"))
			driver = new EdgeDriver();
		else if (browser.equalsIgnoreCase("Firefox"))
			driver = new FirefoxDriver();
		else if (browser.equalsIgnoreCase("Safari"))
			driver = new SafariDriver();
		System.out.println("Launch the browser");
	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		String url = pLib.toGetDataFromProperitiesFile("Url");
		String username = pLib.toGetDataFromProperitiesFile("Username");
		String password = pLib.toGetDataFromProperitiesFile("Password");
		LoginPage lp = new LoginPage(driver);
		lp.loginIntoApp(url, username, password);
		System.out.println("Login");
	}

	@AfterMethod
	public void afterMethod() {
		HomePage hp = new HomePage(driver);
		hp.logout();
		System.out.println("Logout");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
		System.out.println("Close the browser");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("Post condition");
	}

	@AfterSuite
	public void afterSuite() throws SQLException {
		
		System.out.println("Close database connection");
	}

}