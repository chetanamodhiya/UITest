package com.magento.customerImport;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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

public class ValidateDefaultAddress {

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
	SoftAssert sa=new SoftAssert();
	CustomerAddressBookPage address=new CustomerAddressBookPage();
	BrowserFactory bf = new BrowserFactory();
		@Test()
		public void userList() throws Throwable{
			Map<String, ArrayList<String>> m=excelUtility.storeExcelAsMap(".\\src\\test\\resources\\excel\\LoginData.xlsx","userList1",0,1);
			for(Map.Entry<String, ArrayList<String>> entry : m.entrySet()){
				ArrayList<String> al = entry.getValue();
				System.out.println(entry.getKey()+ " "+al);
				String browser = PropertiesUtility.getPropertyValueByKey("browser");
				String url = 	PropertiesUtility.getPropertyValueByKey("appUrl");

				DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));
				DriverFactory.getInstance().getDriver().manage().window().maximize();
				DriverFactory.getInstance().getDriver().navigate().to(url);
				DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				bp.waitForPageLoad();
				bp.loginFromHeader(entry.getKey(), entry.getValue().get(0));
				accountPage.clickAddressBook();
				String city=entry.getValue().get(1);
				String company=entry.getValue().get(2);
				String postcode=entry.getValue().get(3);
				String region=entry.getValue().get(4);
				String street=entry.getValue().get(5);
				String telephone=entry.getValue().get(6);

				address.validateDefaultBillingAddress(company,street,city,region,postcode,telephone,sa);
				sa.assertAll();
				DriverFactory.getInstance().closeBrowser();
			}
		}

}
