package com.ninza.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProductsPage {
	
	WebDriver driver;

	public CreateProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="productName")
	private WebElement productName;
	
	@FindBy(name="quantity")
	private WebElement quantity;
	
	@FindBy(name="price")
	private WebElement price;
	
	@FindBy(name="Price Per Unit")
	private WebElement pricePerUnit;
	
	
	public void setPricePerUnit(WebElement pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public WebElement getPricePerUnit() {
		return pricePerUnit;
	}

	@FindBy(name="productCategory")
	private WebElement productCategory;
	
	@FindBy(name="vendorId")
	private WebElement vendorId;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement addButton;

	public WebElement getAddProductButton() {
		return addButton;
	}

	public void setAddProductbutton(WebElement addButton) {
		this.addButton = addButton;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getProductName() {
		return productName;
	}

	public void setProductName(WebElement productName) {
		this.productName = productName;
	}

	public WebElement getQuantity() {
		return quantity;
	}

	public void setQuantity(WebElement quantity) {
		this.quantity = quantity;
	}

	public WebElement getPrice() {
		return price;
	}

	public void setPrice(WebElement price) {
		this.price = price;
	}

	public WebElement getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(WebElement productCategory) {
		this.productCategory = productCategory;
	}

	public WebElement getVendorId() {
		return vendorId;
	}

	public void setVendorId(WebElement vendorId) {
		this.vendorId = vendorId;
	}
	
	
	
	
}
