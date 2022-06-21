package com.magento.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class CustomerAddressBookPage extends TestBase{
	
	By city=By.xpath("//td[@data-th='City']");
	By firstName=By.xpath("//td[@data-th='First Name']");
	By lastName=By.xpath("//td[@data-th='Last Name']");
	By streetAddress=By.xpath("//td[@data-th='Street Address']");
	By state=By.xpath("//td[@data-th='State']");
	By zipCode=By.xpath("//td[@data-th='Zip/Postal Code']");
	By company=By.xpath("//td[@data-th='Company']");
	By phone=By.xpath("//td[@data-th='Phone']");
	By defaultBillingAddress=By.xpath("//span[text()='Default Billing Address']/../..//address");
	By defaultShippingAddress=By.xpath("//span[text()='Default Shipping Address']/../..//address");
	By telephoneForBillingAddress=By.xpath("//span[text()='Default Billing Address']/../..//address/a");
	By telephoneForShippingAddress=By.xpath("//span[text()='Default Shipping Address']/../..//address/a");
	By searchAddresses=By.name("search_addresses");
	By addresses=By.xpath("//table[@id='additional-addresses-table']//tbody//td");
	By countAddress=By.xpath("//div[contains(text(),'Total Addresses:')]");
	By NoOtherAddress=By.xpath("//p[text()='You have no other address entries in your address book.']");
	By addNewAddress=By.xpath("//button[@class='action primary add']");
	By addFirstName=By.id("firstname");
	By addLastName=By.id("lastname");
	By addStreetAddress=By.id("street_1");
	By addCity=By.id("city");
	By addState=By.id("region_id");
	By addZip=By.id("zip");
	By country=By.id("country");
	By saveAddress=By.xpath("//button[@class='action submit primary']");
	By addPhoneNumber=By.id("telephone");
	By confirmationForSaveAddress=By.xpath("//div[text()='You saved the address.']");
	By oKForDeleteAddress=By.xpath("//button[@class='action-primary action-accept']//span");
	By messageForDefaultAddress=By.xpath("//div[@class='message info']//span");
	By checkBoxForSelectDefaultAddress=By.xpath("//input[@type='checkbox']");
	By noDefaultAddress=By.xpath("//div[@class='box-content']//p");
	By checkBoxForSelectDefaultAddressText=By.xpath("//span[contains(text(),'address')]");
	By addressDeletedMessage=By.xpath("//div[text()='You deleted the address.']");
	
	
	public void editAddressAsperName(String firstNameText, String editFirstName,String editLastName,String editStreetName,String editCity, String editState,String editZip, String editCountry, String editPhoneNumber, String setDefaultAddress) throws Throwable {
		By editAddress=By.xpath("//td[text()='"+firstNameText+"']/..//span[text()='Edit']");
		click_custom(custom_findElement(editAddress), "Edit Link");
		String url=getURL_custom();
		if(url.contains("address/edit")) {
			Assert.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Edit Address Page is displayed: URL is "+url);
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Edit Address Page is displayed: URL is "+url);
			Assert.fail();
		}
		addAddressDetails(editFirstName,editLastName,editStreetName,editCity,editState,editZip,editCountry,editPhoneNumber,setDefaultAddress);
		waitFor(2000);
		for(WebElement nameElement:custom_findElements(firstName)) {
			if(getText_custom(nameElement).contains(editFirstName)) {
				Assert.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, editFirstName+" updated successfully");
				break;
			}
		}
	}
	
	public void deleteAddressAsperName(String firstName) throws InterruptedException {
		By deleteAddress=By.xpath("//td[text()='"+firstName+"']/..//span[text()='Delete']");
		for(int address=custom_findElements(deleteAddress).size();address>0;address--) {
			deleteAddress=By.xpath("//td[text()='"+firstName+"']/..//span[text()='Delete']");
			scrollDownToWebElement(custom_findElement(deleteAddress));
			click_custom(custom_findElement(deleteAddress), "Delete Link");
			click_custom(custom_findElement(oKForDeleteAddress), "Ok Button");
			if(isElementPresent_custom(custom_findElement(addressDeletedMessage))) {
				ExtentFactory.getInstance().getExtent().log(Status.PASS, getText_custom(custom_findElement(addressDeletedMessage))+ " is displayed");	
				Assert.assertTrue(true);
			}
			else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Address is not deleted");	
				Assert.fail();
			}
		}
	}
	
	public void addFirstName(String name) {
		sendKeys_custom(custom_findElement(addFirstName), name, name);
	}
	
	public void addLastName(String name) {
		sendKeys_custom(custom_findElement(addLastName), name, name);
	}
	
	public void addStreetAddress(String name) {
		sendKeys_custom(custom_findElement(addStreetAddress), name, name);
	}
	
	public void addCity(String name) {
		sendKeys_custom(custom_findElement(addCity), name, name);
	}
	
	public void addState(String name) throws Throwable {
		selectDropDownByVisibleText_custom(custom_findElement(addState), name, name);
	}
	
	public void addZip(String name) {
		sendKeys_custom(custom_findElement(addZip), name, name);
	}
	
	public void addCountry(String name) throws Throwable {
		selectDropDownByVisibleText_custom(custom_findElement(addState), name, name);
	}
	public void addPhoneNumer(String name) throws Throwable {
		sendKeys_custom(custom_findElement(addPhoneNumber), name, name);
	}
	
	public void addAddressDetailsAndSaveADefault(String firstName,String lastName,String streetName,String city, String state,String zip, String country, String phoneNumber,String setDefaultAddress) throws Throwable {
		addFirstName(firstName);
		addLastName(lastName);
		addStreetAddress(streetName);
		addCity(city);
		addState(state);
		addZip(zip);
		addPhoneNumer(phoneNumber);
//		addCountry(country);
		if(custom_findElements(checkBoxForSelectDefaultAddressText).size()>0){
			for(WebElement info:custom_findElements(checkBoxForSelectDefaultAddressText)) {
				ExtentFactory.getInstance().getExtent().log(Status.INFO, getText_custom(info)+ " is displayed");
			}
			if(setDefaultAddress.contains("Yes")) {
				for(WebElement checkBoxElement:custom_findElements(checkBoxForSelectDefaultAddressText)) {
					if(!(checkBoxElement.isSelected()))
						click_custom(checkBoxElement, "Checkbox");
				}
			}
		}
		else if(custom_findElements(messageForDefaultAddress).size()>0) {
			for(WebElement info:custom_findElements(messageForDefaultAddress)) {
				ExtentFactory.getInstance().getExtent().log(Status.INFO, getText_custom(info)+ " is displayed");
			}
		}
		clickSaveAddressButton();
	}
	
	
	public void addAddressDetails(String firstName,String lastName,String streetName,String city, String state,String zip, String country, String phoneNumber,String setDefaultAddress) throws Throwable {
		addFirstName(firstName);
		addLastName(lastName);
		addStreetAddress(streetName);
		addCity(city);
		addState(state);
		addZip(zip);
		addPhoneNumer(phoneNumber);
//		addCountry(country);
		clickSaveAddressButton();
	}
	
	public void clickAddNewAddressButton() throws InterruptedException {
		waitFor(1000);
		if(isElementPresent_custom(custom_findElement(addNewAddress))) {
			Assert.assertTrue(true);
			click_custom(custom_findElement(addNewAddress), "Add New Address Button");
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Add New Address Button is not displayed");	
			Assert.fail();
		}
	}
	
	public void clickSaveAddressButton() {
		click_custom(custom_findElement(saveAddress), "Save Address Button");
		if(isElementPresent_custom(custom_findElement(confirmationForSaveAddress))) {
			ExtentFactory.getInstance().getExtent().log(Status.PASS, getText_custom(custom_findElement(confirmationForSaveAddress))+ " is displayed");	
			Assert.assertTrue(true);
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,  "Address did not save");	
			Assert.fail();
		}
	}
	
	
	public void validateSearchAddress(String name, String firstName,String lastName,String streetName,String city, String state,String zip, String country,String phoneNumber,String setDefaultAddress) throws Throwable {
		if(custom_findElements(searchAddresses).size()>0) {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, custom_findElement(countAddress).getText());
			sendKeys_custom(custom_findElement(searchAddresses), name, name);
		}
		else 
			addAddressDetails(firstName,lastName,streetName,city,state,zip,country,phoneNumber,setDefaultAddress);
		
		if(custom_findElements(countAddress).size()>0) {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Matched addresses "+custom_findElement(countAddress).getText());
			for(WebElement address:custom_findElements(addresses)) {
				if(address.getText().contains(name)) {
					ExtentFactory.getInstance().getExtent().log(Status.INFO, "Address is displayed as per your search "+name+ " matched with "+address.getText());
					Assert.assertTrue(true);
				}
			}
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Address is not available as per "+name+" your search");	
		}
	}
	
	// Validate Address Book Page 
	public void validateAddressBookPage() {
		String url=getURL_custom();
		if(url.contains("address")) {
			Assert.assertTrue(false);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Address Book Page is displayed: URL is "+url);
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Address Book Page is displayed: URL is "+url);
			Assert.fail();
		}
	}
	
	public void viewDefaultBillingAddress() {
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Default Billing Address : "+custom_findElement(defaultBillingAddress).getText());
	}
	
	public void viewDefaultShippingAddress() {
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Default Shipping Address : "+custom_findElement(defaultShippingAddress).getText());
	}
	
	public void validateDefaultBillingAddress(String company1,String street1,String city1,String region1,String postcode1, String telephone1,SoftAssert sa) {
		
		String billingAddress[]=custom_findElement(defaultBillingAddress).getText().split("\\n");
		String company=billingAddress[1];
		if(company.equalsIgnoreCase(company1)) {
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Billing address Company "+company+" is matched with expected "+company1);
		}
		else
		{
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Billing address Company "+company+" is did not match with expected "+company1);
			sa.fail();
		}

		String street=billingAddress[2];
		if(street.equalsIgnoreCase(street1)) {
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Billing address Street : "+street+" is matched with expected "+street1);
		}
		else
		{
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Billing address Street : "+street+" did not match with expected "+street1);
			sa.fail();
		}
		String[] addressDetails = billingAddress[3].split(", ");
		String cityDetail = addressDetails[0];
		String region = addressDetails[1];
		String postCode = addressDetails[2];
		if(cityDetail.equalsIgnoreCase(city1)) {
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Billing address City : "+cityDetail+" is matched with expected "+city1);
		}
		else
		{
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Billing address City : "+cityDetail+" did not match with expected "+city1);
			sa.fail();
		}
		if(region.equalsIgnoreCase(region1)) {
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Billing address Region : "+region+" is matched with expected "+region1);
		}
		else
		{
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Billing address Region : "+region+" did not match with expected "+region1);
			sa.fail();
		}
		if(postCode.startsWith("0")) {
			int code = Integer.parseInt(postCode);
			postCode = String.valueOf(code);
		}
		if(postCode.equalsIgnoreCase(postcode1)) {
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Billing address PostCode : "+postCode+" is matched with expected "+postcode1);
		}
		else
		{
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Billing address PostCode : "+postCode+" did not match with expected "+postcode1);
			sa.fail();
		}
//		String country=billingAddress[4];
//		if(country.contains(postCode)) {
//			sa.assertTrue(true);
//			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Billing address Country : "+country+" is matched with expected Country "+countryId);
//		}
//		else
//		{
//			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Billing address Street : "+country+" did not matchwith expected Country "+countryId);
//			sa.fail();
//		}
		
		String telephoneNo = custom_findElement(telephoneForBillingAddress).getText();
		if(telephoneNo.equalsIgnoreCase(telephone1)){
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Billing address Telephone : "+telephoneNo+" is matched with expected "+telephone1);
		}
		else
		{
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Billing address Telephone : "+telephoneNo+" did not match with expected "+telephone1);
			sa.fail();
		}
	}
	
	
	public void validateDefaultShippingAddress(String company1,String street1,String city1,String region1,String postcode1, String telephone1,SoftAssert sa) {
		String shippingAddress[]=custom_findElement(defaultShippingAddress).getText().split("\\n");
		String company=shippingAddress[1];
		if(company.equalsIgnoreCase(company1)) {
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Shipping address Company : "+company+" is matched with expected "+company1);
		}
		else
		{
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Shipping address Company : "+company+" did not match with expected "+company1);
			sa.fail();
		}

		String street=shippingAddress[2];
		if(street.equalsIgnoreCase(street1)) {
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Shipping address Street : "+street+" is matched with expected "+street1);
		}
		else
		{
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Shipping address Street : "+street+" did not match with expected "+street1);
			sa.fail();
		}
		String[] addressDetails = shippingAddress[3].split(", ");
		String cityDetail = addressDetails[0];
		String region = addressDetails[1];
		String postCode = addressDetails[2];
		if(cityDetail.equalsIgnoreCase(city1)) {
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Shipping address City : "+cityDetail+" is matched with expected "+city1);
		}
		else
		{
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Shipping address City : "+cityDetail+" did not match with expected "+city1);
			sa.fail();
		}
		if(region.equalsIgnoreCase(region1)) {
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Shipping address Region : "+region+" is matched with expected "+region1);
		}
		else
		{
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Shipping address Region : "+region+" did not match with expected "+region1);
			sa.fail();
		}
		if(postCode.startsWith("0")) {
			int code = Integer.parseInt(postCode);
			postCode = String.valueOf(code);
		}
		if(postCode.equalsIgnoreCase(postcode1)) {
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Shipping address PostCode : "+postCode+" is matched with expected "+postcode1);
		}
		else
		{
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Shipping address PostCode : "+postCode+" did not match with expected "+postcode1);
			sa.fail();
		}
		String telephoneNo = custom_findElement(telephoneForShippingAddress).getText();
		if(telephoneNo.equalsIgnoreCase(telephone1)){
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Shipping address Telephone : "+telephoneNo+" is matched with expected "+telephone1);
		}
		else
		{
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Shipping address Telephone : "+telephoneNo+" did not match with expected "+telephone1);
			sa.fail();
		}
	}
}


