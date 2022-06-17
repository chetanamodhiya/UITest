package com.magento.testScriptsEDDM;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.magento.pages.BasePage;
import com.magento.pages.EDDMAddressPage;
import com.magento.pages.PDPProductOptionsPage;
import com.magento.pages.PDPSetsAndShippingPage;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class ValidateZeroRoutesDisplayedForFirstTimeCQA_241 extends TestBase{
	ExcelUtility excelUtility = new ExcelUtility();
	BasePage bp = new BasePage();
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	EDDMAddressPage eDDM=new EDDMAddressPage();
	PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
	SoftAssert sa=new SoftAssert();
	
	@Test(groups= {"smoke"})
	public void eDDMFullServicePostcards() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("EDDM Full Service Postcards Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("EDDM Full Service Postcards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("EDDM Full Service Postcards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("EDDM Full Service Postcards ProductURL").replace("envi", run.get("BetaKeyword")));
		
		pdp.clickStartOverLink("Start Over Link");
		pdp.clickCalendarDateField();
		pdp.selectDateFromCalendar();
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 6.5 x 8"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 16PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING Matte"));
		pdp.selectSpotUVSides(pdpOptionsAsMap.get("SPOT UV SIDES No Spot UV"));
		pdp.selectBundlingService(pdpOptionsAsMap.get("BUNDLING SERVICE Yes"));
		pdp.selectEDDMService(pdpOptionsAsMap.get("EDDM SERVICE OPTION"));
		pdp.clickProceedToShippingButton();
		eDDM.switchToFrameForDMOrEDDM();
		eDDM.enterZipcode(fileUploading.get("ZIPCODE"));
		eDDM.clickSearchButton();
		eDDM.viewZeroRoutesIsDisplayed(sa);
		sa.assertAll();
	}
}
