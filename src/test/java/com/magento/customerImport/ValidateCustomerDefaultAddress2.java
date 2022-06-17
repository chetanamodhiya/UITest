package com.magento.customerImport;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.magento.pages.ArtworkPage;
import com.magento.pages.BasePage;
import com.magento.pages.OrderConfirmationPage;
import com.magento.pages.CustomerAccountPage;
import com.magento.pages.CustomerAddressBookPage;
import com.magento.pages.DirectMailingServicePage;
import com.magento.pages.EDDMAddressPage;
import com.magento.pages.OrderHistoryPage;
import com.magento.pages.PDPProductOptionsPage;
import com.magento.pages.PDPSetsAndShippingPage;
import com.magento.pages.ReviewAndPaymentsPage;
import com.magento.pages.ShoppingCartPage;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.BrowserFactory;
import com.magento.testBase.DriverFactory;
import com.magento.testBase.ExtentFactory;

public class ValidateCustomerDefaultAddress2 {

	ExcelUtility excelUtility = new ExcelUtility();
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
	BasePage bp=new BasePage();
	ArtworkPage artwork=new ArtworkPage();
	ShoppingCartPage cart=new ShoppingCartPage();
	ReviewAndPaymentsPage review=new ReviewAndPaymentsPage();
	EDDMAddressPage eDDM=new EDDMAddressPage();
	DirectMailingServicePage dm=new DirectMailingServicePage();
	CustomerAccountPage accountPage=new CustomerAccountPage();
	OrderConfirmationPage checkOutSuccess=new OrderConfirmationPage();
	OrderHistoryPage order=new OrderHistoryPage();
	CustomerAddressBookPage address=new CustomerAddressBookPage();
	BrowserFactory bf = new BrowserFactory();
	SoftAssert sa=new SoftAssert();
	
		@Test(dataProvider = "getData")
		public void userList(Hashtable<String,String> data) throws Throwable{
			
			if(data.get("_address_default_billing_").equals("1")) {
				String browser = PropertiesUtility.getPropertyValueByKey("browser");
				String url = 	PropertiesUtility.getPropertyValueByKey("appUrl");

				DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));
				DriverFactory.getInstance().getDriver().manage().window().maximize();
				DriverFactory.getInstance().getDriver().navigate().to(url);
				DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				bp.waitForPageLoad();
				bp.loginFromHeader(data.get("email"), data.get("password"));
				accountPage.clickAddressBook();
				address.validateDefaultBillingAddress(data.get("company"),data.get("street"),data.get("city"),data.get("region"),data.get("postcode"),data.get("telephone"),sa);
				DriverFactory.getInstance().closeBrowser();
			}
			else if(data.get("_address_default_shipping_").equals("1")) {
				String browser = PropertiesUtility.getPropertyValueByKey("browser");
				String url = 	PropertiesUtility.getPropertyValueByKey("appUrl");

				DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));
				DriverFactory.getInstance().getDriver().manage().window().maximize();
				DriverFactory.getInstance().getDriver().navigate().to(url);
				DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				bp.waitForPageLoad();
				bp.loginFromHeader(data.get("email"), data.get("password"));
				accountPage.clickAddressBook();
				address.validateDefaultShippingAddress(data.get("company"),data.get("street"),data.get("city"),data.get("region"),data.get("postcode"),data.get("telephone"),sa);
				DriverFactory.getInstance().closeBrowser();
			}
			else {
				ExtentFactory.getInstance().getExtent().log(Status.INFO, "This address is not a billing or shipping address");
			}
		}
		@DataProvider
		public Object[][] getData(Method m) throws EncryptedDocumentException, IOException{
			String sheetName = m.getName();
			int rows = excelUtility.getRowCount(PropertiesUtility.getPropertyValueByKey("UserList_excelPath"), sheetName);
			int columns = excelUtility.getColumnCount(PropertiesUtility.getPropertyValueByKey("UserList_excelPath"), sheetName);
			rows = rows+1;
			
			Object[][] data = new Object[rows-1][1];
			
			Hashtable<String, String> table = null;
			
			for(int rowNum = 2; rowNum <= rows; rowNum++) {
				table = new Hashtable<String, String>();
				for(int colNum = 0; colNum < columns; colNum++) {
					table.put(excelUtility.getCellData(PropertiesUtility.getPropertyValueByKey("UserList_excelPath"), sheetName, 1, colNum), excelUtility.getCellData(PropertiesUtility.getPropertyValueByKey("UserList_excelPath"), sheetName, rowNum, colNum));
					data[rowNum-2][0] = table;
				}
			}
			return data;
		}
}
