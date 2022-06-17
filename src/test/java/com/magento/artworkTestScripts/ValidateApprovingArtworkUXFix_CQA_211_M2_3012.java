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
 * Reference Jira ID : M2-3012
 */
public class ValidateApprovingArtworkUXFix_CQA_211_M2_3012 extends TestBase{
	ExcelUtility excelUtility = new ExcelUtility();
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
	BasePage bp=new BasePage();
	ArtworkPage artwork=new ArtworkPage();
	ShoppingCartPage cart=new ShoppingCartPage();
	ReviewAndPaymentsPage review=new ReviewAndPaymentsPage();
	CustomerAccountPage accountPage=new CustomerAccountPage();
	OrderConfirmationPage ocp=new OrderConfirmationPage();
	OrderHistoryPage order=new OrderHistoryPage();
	SoftAssert sa=new SoftAssert();
	
	@Test(groups= {"smoke"})
	public void validateConfirmationPopup() throws Throwable{
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
		sa.assertAll();
	}
	
	@Test
	public void validateConfirmationPopupForAllSetsShipments() throws Throwable{
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
		set.clickForAddAnotherShipment(fileUploading.get("Shipments"));
		set.clickForAddAnotherSet(fileUploading.get("Sets"),fileUploading.get("ShipmentForAddSets"));
		set.validateAvailabilityOfShippingMethod();
		int totalSets = set.totalSets();
		int totalShipments = set.totalShipments();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Leaf Business Cards Product"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.validateSetsAndShipmentsCount(totalSets, totalShipments,sa);
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.validateConfirmationPopup(sa);
		sa.assertAll();
	}
	
	/*
	 * Below script validates the availability of the confirmation popup after drag and drop -
	 * i) Image files(jpg, png etc) are uploaded first followed by csv file upload
	 */
	
	@Test
	public void validatePrintProductFunctionalityCSVFileIsUploadedLast() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Variable Numbering Event Tickets Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Variable Numbering Event Tickets ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Variable Numbering Event Tickets ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Variable Numbering Event Tickets ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over link");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 2 x 7"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 16PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/4"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING UV Coating Front Only"));
		pdp.selectPerforation(pdpOptionsAsMap.get("Perforation"));
		pdp.selectNumbering(pdpOptionsAsMap.get("Numbering"));
		pdp.selectVariableData(pdpOptionsAsMap.get("Variable Data"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 250"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Variable Numbering Event Tickets Product"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.waitForFileAnalyzeToDisappear();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork CSV File"),fileUploading.get("Artwork CSV File"));
		artwork.validateConfirmationPopup(sa);
		sa.assertAll();
	}	
	
	/*
	 * Below script validates the availability of the confirmation popup after drag and drop -
	 * i) csv file is uploaded first followed by image files(jpg, png etc) upload
	 */
	
	@Test
	public void validatePrintProductFunctionalityImageFileIsUploadedLast() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Variable Numbering Event Tickets Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Variable Numbering Event Tickets ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Variable Numbering Event Tickets ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Variable Numbering Event Tickets ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over Link");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 2 x 7"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 16PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/4"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING UV Coating Front Only"));
		pdp.selectPerforation(pdpOptionsAsMap.get("Perforation"));
		pdp.selectNumbering(pdpOptionsAsMap.get("Numbering"));
		pdp.selectVariableData(pdpOptionsAsMap.get("Variable Data"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 250"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Variable Numbering Event Tickets Product"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork CSV File"),fileUploading.get("Artwork CSV File"));
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.validateConfirmationPopup(sa);
		sa.assertAll();
	}
	
	@Test
	public void validateConfirmationPopupWithShipmentLockUnlock() throws Throwable{
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
		artwork.clickOkButtonOnTheArtworkModalPopup();
		artwork.clickShipmentApprovedExpandIcon();
		artwork.clickApprovedLockButton();
		artwork.clickUnlockYesCheckbox(sa);
		artwork.clickYesButtonForUnlock(sa);
		artwork.deleteArtworkFileFromSidebar();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.validateConfirmationPopup(sa);
		sa.assertAll();
	}	
	
	@Test
	public void validateConfirmationPopupAfterPlacedOrder() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_211_212_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> paymentDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPaymentDetails"));
		Map<String,String> successMessageDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForCheckoutSuccessDetails"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		
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
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Ground Shipping $6.00"));
		set.clickAddToCartButton();
		cart.clickProceedToCheckOutButton("Proceed to Checkout");
		review.selectPaymentMethods(paymentDetails.get("Payment Method"));
		review.enterCardDetails(paymentDetails.get("Card Number"), paymentDetails.get("Card Exp Month"),paymentDetails.get("Card Exp Year"), paymentDetails.get("Card Verification No"));
		if(!(PropertiesUtility.getPropertyValueByKey("env").equals("printing"))) {
			review.clickPlaceOrderButton();
//			ocp.validateThankYouMessage(successMessageDetails.get("OrderSuccess"),sa);
			String orderId=ocp.viewOrderId();
			bp.clickMyAccountLinkFromTheHeader();
			bp.clickMyOrders();
			order.clickUploadArtwork("000106145");
			artwork.switchToArtworkFrameAfterPlaceOrder();
			artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
			artwork.validateConfirmationPopup(sa);
		} 
		else {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Environment is "+PropertiesUtility.getPropertyValueByKey("env")+". Hence place order flow is validated until Reviews & Payments page");
		}
		sa.assertAll();
	}	
}