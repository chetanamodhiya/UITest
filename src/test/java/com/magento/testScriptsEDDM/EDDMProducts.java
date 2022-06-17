package com.magento.testScriptsEDDM;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.magento.pages.ArtworkPage;
import com.magento.pages.BasePage;
import com.magento.pages.EDDMAddressPage;
import com.magento.pages.PDPProductOptionsPage;
import com.magento.pages.PDPSetsAndShippingPage;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class EDDMProducts extends TestBase{
	ExcelUtility excelUtility = new ExcelUtility();
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
	BasePage bp=new BasePage();
	ArtworkPage artwork=new ArtworkPage();
	EDDMAddressPage eDDM=new EDDMAddressPage();
	SoftAssert sa=new SoftAssert();
	
	@Test()
	public void tAT_QuantityShowingInProjectSummarylinkInArtworkPageCQA_279() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
	
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("EDDM Full Service - Flyers Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("EDDM Full Service - Flyers ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("EDDM Full Service - Flyers ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("EDDM Full Service - Flyers ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over Link");
		pdp.clickCalendarDateField();
		pdp.selectDateFromCalendar();
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 4.25 x 11"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 100LB Gloss Cover"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING Aqueous Coating"));
		pdp.selectBundlingService(pdpOptionsAsMap.get("BUNDLING SERVICE Yes"));
		pdp.selectEDDMService(pdpOptionsAsMap.get("EDDM SERVICE OPTION"));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME 5-7 Business Days"));
		pdp.enterProjectName(pdpOptionsAsMap.get("EDDM Full Service - Flyers Product"));
		pdp.clickProceedToShippingButton();
		eDDM.switchToFrameForDMOrEDDM();
		eDDM.enterZipcode(fileUploading.get("Zipcode 91340"));
		eDDM.clickSearchButton();
		eDDM.validateSaveOrderButtonIsDisabledBeforeSelectingRoutes();
		eDDM.clickListViewAndSelectTheFirstRoute();
		eDDM.validateEstimatedTotalPrice();
		eDDM.clickSaveOrderButton();
		switchToDefaultContent_custom();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameForDM_EDDM();
		artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
		artwork.clickProjectSummary();
		artwork.validateTATAndQuantityInArtworkPage(sa);
		sa.assertAll();
	}
	
//	@Test()
	public void moveLocationOfJobSamplesCQA_281() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
	
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
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 6 x 12"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 16PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/1"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING Matte"));
		pdp.selectSpotUVSides(pdpOptionsAsMap.get("SPOT UV SIDES No Spot UV"));
		pdp.selectBundlingService(pdpOptionsAsMap.get("BUNDLING SERVICE Yes"));
		pdp.selectEDDMService(pdpOptionsAsMap.get("EDDM SERVICE OPTION"));
		pdp.iSSampleOfCompletedJobDisplayed();
		pdp.enterProjectName(pdpOptionsAsMap.get("EDDM Full Service - Flyers Product"));
		pdp.clickProceedToShippingButton();
		eDDM.switchToFrameForDMOrEDDM();
		eDDM.enterZipcode(fileUploading.get("Zipcode 91340"));
		eDDM.clickSearchButton();
		eDDM.validateSaveOrderButtonIsDisabledBeforeSelectingRoutes();
		eDDM.clickListViewAndSelectTheFirstRoute();
		eDDM.validateEstimatedTotalPrice();
		eDDM.clickSaveOrderButton();
		switchToDefaultContent_custom();
		set.iSSampleOfCompletedJobDisplayed();
		sa.assertAll();
	}
}
