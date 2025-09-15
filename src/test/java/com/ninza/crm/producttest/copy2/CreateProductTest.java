package com.ninza.crm.producttest.copy2;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.ninza.crm.baseclass.BaseClass;
import com.ninza.crm.generic.webdriverutility.JavaUtility;
import com.ninza.crm.generic.webdriverutility.WebDriverUtility;
import com.ninza.crm.objectrepository.CreateCampaignsPage;
import com.ninza.crm.objectrepository.CreateProductsPage;
import com.ninza.crm.objectrepository.HomePage;
import com.ninza.crm.objectrepository.LoginPage;
import com.ninza.crm.objectrepository.ProductsPage;

public class CreateProductTest extends BaseClass {

	@Test(groups = {"Smoke","Regression"})
	public void reateProductWithMandatoryFieldTest() throws IOException, InterruptedException {
	
		int ranNum = ju.getRandomNumber();

		String productName = ex.toReadTheDatafromExcel("Product", 1, 1) + ranNum;
		String qty = ex.toReadTheDatafromExcel("Product", 1, 2);
		String price = ex.toReadTheDatafromExcel("Product", 1, 3);

		// Click on product link
		HomePage hp = new HomePage(driver);
		hp.getProductsLink().click();
		// click add product button
		ProductsPage pp = new ProductsPage(driver);
		pp.getCreateCampaign().click();

		// Create product
		CreateProductsPage cpp = new CreateProductsPage(driver);
		cpp.getProductName().sendKeys(productName);
		WebElement quantity = cpp.getQuantity();
		quantity.clear();
		quantity.sendKeys(qty);
		WebElement pricepp = cpp.getPrice();
		pricepp.clear();
		pricepp.sendKeys(price);

		wLib.dropDownByIndex(cpp.getProductCategory(), 3);
		Thread.sleep(5000);
		wLib.dropDownByIndex(cpp.getVendorId(), 2);
		cpp.getAddProductButton().click();
		

		// Verify the succesfull message
		WebElement toastMsg = hp.getToastMsg();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));

		String msg = toastMsg.getText();
		Assert.assertTrue(msg.contains("Successfully Added"));
		
//		if (msg.contains("Successfully Added")) {
//			System.out.println("Product created successfully");
//		} else {
//			System.out.println("Product is not created");
//		}
		hp.getClosetoastMsgBtn().click();

//		hp.logout();
//		driver.quit();
	}

}
