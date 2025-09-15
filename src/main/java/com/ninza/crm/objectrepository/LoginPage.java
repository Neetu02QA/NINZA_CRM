package com.ninza.crm.objectrepository;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;//this will represent login class
		PageFactory.initElements(driver, this);//pass driver reference n pass class object /constructor
	}

	@FindBy(id = "username")
	private WebElement username;// reference variable

	@FindBy(id = "inputPassword")
	private WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginButton;

	public WebElement getUsername() {
		return username;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public void loginIntoApp(String url, String un, String pwd) {

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(url);

		getUsername().sendKeys(un);
		getPassword().sendKeys(pwd);
		getLoginButton().click();
	}

}






//give conditions
// launch the browser
// WebDriver driver = new ChromeDriver();//constructor call

// Maximize the window
//implicit wait
//Navigate to ninja CRM
