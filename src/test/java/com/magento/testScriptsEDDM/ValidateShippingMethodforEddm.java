package com.magento.testScriptsEDDM;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.magento.pages.BasePage;
import com.magento.pages.DirectMailingServicePage;
import com.magento.pages.EDDMAddressPage;
import com.magento.pages.PDPProductOptionsPage;
import com.magento.pages.PDPSetsAndShippingPage;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class ValidateShippingMethodforEddm extends TestBase{
	ExcelUtility excelUtility = new ExcelUtility();
	BasePage bp = new BasePage();
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	DirectMailingServicePage dm=new DirectMailingServicePage();
	EDDMAddressPage eDDM=new EDDMAddressPage();
	PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
	ArrayList<String> list=new ArrayList<>();
	SoftAssert sa=new SoftAssert();
	
	@Test(dataProvider = "getData")
	public void validateShippingMethod(Hashtable<String,String> data) throws Throwable{
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("ShippingMethodvalidationForEDDMExcel"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("ShippingMethodvalidationForEDDMExcel"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));

		if(run.get("Env").contains("Stage"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(run.get("Env").contains("Printing"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over Link");

		pdp.clickCalendarDateField();
		pdp.selectDateFromCalendar();
		pdp.selectAnOptionInAllTheDropdowns();
		pdp.selectRunsizeOrQuantity(fileUploading.get("Quantity"));
		pdp.clickProceedToShippingButton();
		eDDM.switchToFrameForDMOrEDDM();
		eDDM.enterZipcode(fileUploading.get("ZIPCODE"));
		eDDM.clickSearchButton();
		eDDM.validateSaveOrderButtonIsDisabledBeforeSelectingRoutes();
		eDDM.clickListViewAndSelectTheFirstRoute();
		eDDM.validateEstimatedTotalPrice();
		eDDM.clickSaveOrderButton();
		switchToDefaultContent_custom();
		set.clickJobSamples();
		set.validateAvailabilityOfShippingMethod();
}
@DataProvider
public Object[][] getData(Method m) throws EncryptedDocumentException, IOException{
	String sheetName = m.getName();
	int rows = excelUtility.getRowCount(PropertiesUtility.getPropertyValueByKey("ShippingMethodvalidationForEDDMExcel"), sheetName);
	int columns = excelUtility.getColumnCount(PropertiesUtility.getPropertyValueByKey("ShippingMethodvalidationForEDDMExcel"), sheetName);
	rows = rows+1;
	
	Object[][] data = new Object[rows-1][1];
	
	Hashtable<String, String> table = null;
	
	for(int rowNum = 2; rowNum <= rows; rowNum++) {
		table = new Hashtable<String, String>();
		for(int colNum = 0; colNum < columns; colNum++) {
			table.put(excelUtility.getCellData(PropertiesUtility.getPropertyValueByKey("ShippingMethodvalidationForEDDMExcel"), sheetName, 1, colNum), excelUtility.getCellData(PropertiesUtility.getPropertyValueByKey("ShippingMethodvalidationForEDDMExcel"), sheetName, rowNum, colNum));
			data[rowNum-2][0] = table;
		}
	}
	return data;
}
}
