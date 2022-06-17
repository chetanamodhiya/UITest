package com.magento.customerImport;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.magento.pages.ArtworkPage;
import com.magento.pages.BasePage;
import com.magento.pages.OrderConfirmationPage;
import com.magento.pages.CustomerAccountPage;
import com.magento.pages.DirectMailingServicePage;
import com.magento.pages.EDDMAddressPage;
import com.magento.pages.OrderHistoryPage;
import com.magento.pages.PDPProductOptionsPage;
import com.magento.pages.PDPSetsAndShippingPage;
import com.magento.pages.ReviewAndPaymentsPage;
import com.magento.pages.ShoppingCartPage;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBaseWithBefore_AfterMethod;

public class ValidateRewardsPoint extends TestBaseWithBefore_AfterMethod{

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
	
		@Test(dataProvider = "getData")
		public void userList(Hashtable<String,String> data) throws Throwable{
			bp.waitForPageLoad();
			bp.loginFromHeader(data.get("email"), data.get("password"));
			bp.validateRewardsPoints(data.get("rewards"),sa);
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
