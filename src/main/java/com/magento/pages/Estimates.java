package com.magento.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.magento.testBase.TestBase;

public class Estimates extends TestBase{

	By createNewEstimate=By.xpath("//a//span[text()='Create New Estimate']");
	By contactNameText=By.id("contact_name");
	By contactEmailText=By.id("contact_email");
	By contactPhoneText=By.id("contact_phone");
	By zipcodeText=By.id("destination_postcode");
	By mySelfCheckbox=By.id("is_myself");
	By estimateName=By.id("estimate_name");
	By selectProductCategory=By.id("product_category");
	
	//click Requested Estimates
	public void clickMyselfCheckbox() {
		if(!(custom_findElement(mySelfCheckbox).isSelected()))
			click_custom(custom_findElement(mySelfCheckbox), "Myself Checkbox");
	}
	
	//click Requested Estimates
	public void clickCreateNewEstimates() {
		click_custom(custom_findElement(createNewEstimate), "Create New Estimate");
	}
	
	//enter contact name in contact name text
	public void enterContactName(String name) {
		sendKeys_custom(custom_findElement(contactNameText),name, name);
	}
	
	//enter contact email in contact email text
	public void enterContactEmail(String email) {
		sendKeys_custom(custom_findElement(contactEmailText),email, email);
	}
	
	//enter contact phone in contact phone text
	public void enterContactPhone(String phoneNumber) {
		sendKeys_custom(custom_findElement(contactPhoneText),phoneNumber, phoneNumber);
	}
		
	//enter zipcode in zipcode text
	public void enterZipcode(String zipcode) {
		sendKeys_custom(custom_findElement(zipcodeText),zipcode, zipcode);
	}
	
	//enter estimate name in estimate name text
	public void enterEstimateName(String estimate) {
		sendKeys_custom(custom_findElement(estimateName),estimate, estimate);
	}
	
	//Select Product Category in Product Category DropDown Menu 
	public void selectProductCategory(String productCategory) throws Throwable {
		WebElement productCategoryDropdown=custom_findElement(selectProductCategory);
		if(productCategoryDropdown.isDisplayed() && productCategoryDropdown.isEnabled()) {
			selectDropDownByVisibleText_custom(productCategoryDropdown, productCategory, "Product Category");
		}
	}
}
