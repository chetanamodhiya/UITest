package com.magento.testScripts;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

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
import com.magento.testBase.TestBase;

public class CaptureArtworkpageLoadingTime extends TestBase{

	ExcelUtility excelUtility = new ExcelUtility();
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
	BasePage bp=new BasePage();
	ArtworkPage artwork=new ArtworkPage();
	
	@Test
	public void standardBusinessCards() throws Throwable{
		
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Business Cards Product"));
		if(run.get("Env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(run.get("Env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(run.get("Env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 2 x 3.5"));
		pdp.clickShapeBox(pdpOptionsAsMap.get("SHAPE Rectangle"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 14PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/1"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING No Coating"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 500"));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME 2-4 Business Days"));
		pdp.validateSubTotalPrice();
		pdp.clickProceedToShippingButton();
		set.clickUploadYourArtButton();
		long startTime = System.currentTimeMillis();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.waitForFirstImageLoad();
		long endTime = System.currentTimeMillis();
		//log the time in the report
		if((endTime-startTime)<=10000 || (endTime-startTime)<=12000) {
			Assert.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "WaitTillPageReadyState for Artwork : "+ (endTime-startTime)+" ms");
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "WaitTillPageReadyState for Artwork : "+ (endTime-startTime)+" ms");
			Assert.fail();
		}
	}
}
