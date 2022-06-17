/*
 * Created By : Chetana
 */

package com.magento.artworkTestScripts;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.magento.pages.ArtworkPage;
import com.magento.pages.BasePage;
import com.magento.pages.OrderConfirmationPage;
import com.magento.pages.CustomerAccountPage;
import com.magento.pages.OrderHistoryPage;
import com.magento.pages.PDPProductOptionsPage;
import com.magento.pages.PDPSetsAndShippingPage;
import com.magento.pages.ReviewAndPaymentsPage;
import com.magento.pages.ShoppingCartPage;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

/*
 * Artwork behavior is validated for all the boxes products
 * Reference Jira ID : M2-3013
 */
public class ApprovingArtworkUXFixConfirmationmessageupdates_CQA_212_M2_3013 extends TestBase{
	ExcelUtility excelUtility = new ExcelUtility();
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
	BasePage bp=new BasePage();
	ArtworkPage artwork=new ArtworkPage();
	ShoppingCartPage cart=new ShoppingCartPage();
	ReviewAndPaymentsPage review=new ReviewAndPaymentsPage();
	CustomerAccountPage accountPage=new CustomerAccountPage();
	OrderConfirmationPage checkOutSuccess=new OrderConfirmationPage();
	OrderHistoryPage order=new OrderHistoryPage();
	SoftAssert sa=new SoftAssert();
	
	@Test
	public void validateReviewArtworkonTemplateButton() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Leaf Business Cards Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Leaf Business Cards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Leaf Business Cards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Leaf Business Cards ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over Link");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 2 x 3.5"));
		pdp.clickShapeBox(pdpOptionsAsMap.get("SHAPE Leaf"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 16PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/4"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING UV Coating Front Only"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 250"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Leaf Business Cards Product"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.validateConfirmationPopup(sa);
		artwork.validateReviewArtworkOnTemplateButton(sa);
		sa.assertAll();
	}	
	@Test
	public void validateReturntomedialibraryButton() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Leaf Business Cards Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Leaf Business Cards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Leaf Business Cards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Leaf Business Cards ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over Link");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 2 x 3.5"));
		pdp.clickShapeBox(pdpOptionsAsMap.get("SHAPE Leaf"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 16PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/4"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING UV Coating Front Only"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 250"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Leaf Business Cards Product"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.validateConfirmationPopup(sa);
		artwork.validateReturnToMediaLibraryButton(sa);
		sa.assertAll();
	}
	@Test
	public void validateApproveallartworkforproductionButton() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Leaf Business Cards Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Leaf Business Cards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Leaf Business Cards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Leaf Business Cards ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over Link");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 2 x 3.5"));
		pdp.clickShapeBox(pdpOptionsAsMap.get("SHAPE Leaf"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 16PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/4"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING UV Coating Front Only"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 250"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Leaf Business Cards Product"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.validateConfirmationPopup(sa);
		artwork.validateApproveAllArtworkForProductionButtonFunctionality(sa);
		sa.assertAll();
	}
}