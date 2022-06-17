package com.magento.testScripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.magento.pages.BasePage;
import com.magento.pages.PDPProductOptionsPage;
import com.magento.pages.PDPSetsAndShippingPage;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.DriverFactory;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class PresenceOfRunsizeTableQuantityTableTATDropdownValidation extends TestBase{
	
ExcelUtility excelUtility = new ExcelUtility();
BasePage bp = new BasePage();
PDPProductOptionsPage pdp=new PDPProductOptionsPage();
PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();

public static FileInputStream fis;

	@Test(dataProvider = "getData")
	public void runsizeOrQuantity_TATValidation(Hashtable<String,String> data) throws Throwable{
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("RunsizeOrQuantity_TATExcel"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> files=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("RunsizeOrQuantity_TATExcel"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
		
		if(run.get("Env").equalsIgnoreCase("Dev"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
		else if(run.get("Env").equalsIgnoreCase("Stage"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(run.get("Env").equalsIgnoreCase("Printing"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLinkORLoginFromPDP(PropertiesUtility.getPropertyValueByKey("defaultUserName"),PropertiesUtility.getPropertyValueByKey("defaultPassword"));
		pdp.selectAnOptionInAllTheDropdownsExceptTAT();
		pdp.selectRunsizeOrQuantity(files.get("Quantity"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
	}
	
	@DataProvider
	public Object[][] getData(Method m) throws EncryptedDocumentException, IOException{
		String sheetName = m.getName();
		int rows = excelUtility.getRowCount(PropertiesUtility.getPropertyValueByKey("RunsizeOrQuantity_TATExcel"), sheetName);
		int columns = excelUtility.getColumnCount(PropertiesUtility.getPropertyValueByKey("RunsizeOrQuantity_TATExcel"), sheetName);
		rows = rows+1;
		
		Object[][] data = new Object[rows-1][1];
		
		Hashtable<String, String> table = null;
		
		for(int rowNum = 2; rowNum <= rows; rowNum++) {
			table = new Hashtable<String, String>();
			for(int colNum = 0; colNum < columns; colNum++) {
				table.put(excelUtility.getCellData(PropertiesUtility.getPropertyValueByKey("RunsizeOrQuantity_TATExcel"), sheetName, 1, colNum), excelUtility.getCellData(PropertiesUtility.getPropertyValueByKey("RunsizeOrQuantity_TATExcel"), sheetName, rowNum, colNum));
				data[rowNum-2][0] = table;
			}
		}
		return data;
	}
}