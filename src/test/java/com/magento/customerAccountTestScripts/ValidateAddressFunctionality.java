package com.magento.customerAccountTestScripts;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import com.magento.pages.BasePage;
import com.magento.pages.CustomerAccountPage;
import com.magento.pages.CustomerAddressBookPage;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.TestBase;

public class ValidateAddressFunctionality extends TestBase{
	CustomerAccountPage account=new CustomerAccountPage();
	CustomerAddressBookPage address=new CustomerAddressBookPage();
	BasePage bp=new BasePage();
	ExcelUtility excelUtility = new ExcelUtility();
	Map<String,String> addressFile=new HashMap<String, String>();
	@Test()
	public void validateAddressBookPage() throws Throwable{
		account.clickAddressBook();
		address.validateAddressBookPage();
	}
	
	@Test(dependsOnMethods= {"validateAddressBookPage"})
	public void addAddress() throws Throwable{
		addressFile=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("addressDetailExcel"), PropertiesUtility.getPropertyValueByKey("sheetForAddress"));
		address.clickAddNewAddressButton();
		address.addAddressDetails(addressFile.get("FirstName"),addressFile.get("LastName"), addressFile.get("StreetAddress"),addressFile.get("City"),addressFile.get("State"), addressFile.get("Zip"),addressFile.get("Country"),addressFile.get("PhoneNumber"),addressFile.get("SetAddressAsDefaultAddress"));
	}
	
	@Test(dependsOnMethods= {"addAddress"})
	public void editAddress() throws Throwable{
		addressFile=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("addressDetailExcel"), PropertiesUtility.getPropertyValueByKey("sheetForAddress"));
		address.editAddressAsperName(addressFile.get("FirstName"),addressFile.get("FirstNameForEdit"),addressFile.get("LastNameForEdit"), addressFile.get("StreetAddressForEdit"),addressFile.get("CityForEdit"),addressFile.get("StateForEdit"), addressFile.get("ZipForEdit"),addressFile.get("CountryForEdit"),addressFile.get("PhoneNumberForEdit"),addressFile.get("SetAddressAsDefaultAddress"));
	}
	
	@Test(dependsOnMethods= {"addAddress","editAddress"})
	public void deleteAddress() throws Throwable{
		addressFile=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("addressDetailExcel"), PropertiesUtility.getPropertyValueByKey("sheetForAddress"));
		address.deleteAddressAsperName(addressFile.get("FirstNameForDelete"));
	}
	
	@Test(dependsOnMethods= {"validateAddressBookPage"})
	public void searchAddressFromAddressBook() throws Throwable{
		addressFile=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("addressDetailExcel"), PropertiesUtility.getPropertyValueByKey("sheetForAddress"));
		address.validateSearchAddress(addressFile.get("SearchAddressAsPerOption"),addressFile.get("FirstName"),addressFile.get("LastName"), addressFile.get("StreetAddress"),addressFile.get("City"),addressFile.get("State"), addressFile.get("Zip"),addressFile.get("Country"),addressFile.get("PhoneNumber"),addressFile.get("SetAddressAsDefaultAddress"));
	}
}
