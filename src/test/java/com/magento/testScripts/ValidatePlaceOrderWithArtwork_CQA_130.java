/*
 * Created By : Chetana
 */
package com.magento.testScripts;

import java.util.Map;

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
import com.magento.testBase.TestBase;
/*
 * Using this class 
 * Place the order 
 * for few products like StandardBusinessCards, 16PT Postcards, FlatFlyersandBrochures,
 * EDDMFullService Postcards, Direct Mail Postcards and ScrimVinyl Banners
 * Reference Jira ID : CQA-130
 */
public class ValidatePlaceOrderWithArtwork_CQA_130 extends TestBase{
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
	OrderConfirmationPage ocp=new OrderConfirmationPage();
	OrderHistoryPage order=new OrderHistoryPage();
	SoftAssert sa=new SoftAssert();
	
	@Test()
	public void standardBusinessCardsPlaceOrderUsingCreditCardAlongWithArtwork() throws Throwable{
		
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> paymentDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPaymentDetails"));
		Map<String,String> successMessageDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForCheckoutSuccessDetails"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Business Cards Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("BetaKeyword")));
//		bp.clearCart();
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 2 x 3.5"));
		pdp.clickShapeBox(pdpOptionsAsMap.get("SHAPE Rectangle"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 14PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/1"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING No Coating"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 500"));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME 2-4 Business Days"));
		pdp.clickProceedToShippingButton();
		set.validateUserIsNavigatedToSetsAndShippingPage();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Business Cards Product"));
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Ground Shipping"));
		set.clickUploadYourArtButton();
		artwork.closeChatPopup(pdpOptionsAsMap.get("Business Cards Product"));
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.selectAllTheCheckboxes();
		artwork.clickApproveArtwork();
		artwork.clickOkButtonOnTheArtworkModalPopup();
		switchToDefaultContent_custom();
		artwork.closeArtworkPage();
		set.clickAddToCartButton();
		cart.clickProceedToCheckOutButton("Proceed to Checkout");
		review.selectPaymentMethods(paymentDetails.get("Payment Method"));
		review.enterCardDetails(paymentDetails.get("Card Number"), paymentDetails.get("Card Exp Month"),paymentDetails.get("Card Exp Year"), paymentDetails.get("Card Verification No"));
		if(!(PropertiesUtility.getPropertyValueByKey("env").equals("printing"))) {
			review.clickPlaceOrderButton();
			ocp.validateThankYouMessage(successMessageDetails.get("After Place Order Expected Message"),sa);
			String orderId=ocp.viewOrderId();
//			waitFor(8000); 
//			bp.clickMyAccountLinkFromTheHeader();
//			bp.clickMyOrders();	
//			order.viewJobId(orderId);
		} 
		else {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Environment is "+PropertiesUtility.getPropertyValueByKey("env")+". Hence place order flow is validated until Reviews & Payments page");
		}
	}
	
	@Test
	public void pT16PostcardsPlaceOrderUsingCreditCardAlongWithArtwork() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> paymentDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPaymentDetails"));
		Map<String,String> successMessageDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForCheckoutSuccessDetails"));
	
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Postcards Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("16PT Postcards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("16PT Postcards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("16PT Postcards ProductURL").replace("envi", run.get("BetaKeyword")));
		bp.clearCart();
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 4 x 6"));
		pdp.clickShapeBox(pdpOptionsAsMap.get("SHAPE Rectangle"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 16PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/1"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING UV Both Sides"));
		pdp.selectBundlingService(pdpOptionsAsMap.get("BUNDLING SERVICE Yes"));
		pdp.selectShrinkWrapping(pdpOptionsAsMap.get("SHRINK WRAPPING"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 500"));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME 2-4 Business Days"));
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Postcards Product"));
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Ground Shipping"));
		set.clickUploadYourArtButton();
		artwork.closeChatPopup(pdpOptionsAsMap.get("Postcards Product"));
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.selectAllTheCheckboxes();
		artwork.clickApproveArtwork();
		artwork.clickOkButtonOnTheArtworkModalPopup();
		switchToDefaultContent_custom();
		artwork.closeArtworkPage();
		set.clickAddToCartButton();
		cart.clickProceedToCheckOutButton("Proceed to Checkout");
		review.selectPaymentMethods(paymentDetails.get("Payment Method"));
		review.enterCardDetails(paymentDetails.get("Card Number"), paymentDetails.get("Card Exp Month"),paymentDetails.get("Card Exp Year"), paymentDetails.get("Card Verification No"));
		if(!(PropertiesUtility.getPropertyValueByKey("env").equals("printing"))) {
			review.clickPlaceOrderButton();
			ocp.validateThankYouMessage(successMessageDetails.get("After Place Order Expected Message"),sa);
			String orderId=ocp.viewOrderId();
//			waitFor(8000); 
//			bp.clickMyAccountLinkFromTheHeader();
//			bp.clickMyOrders();	
//			order.viewJobId(orderId);
		} 
		else {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Environment is "+PropertiesUtility.getPropertyValueByKey("env")+". Hence place order flow is validated until Reviews & Payments page");
		}
	}
	
	@Test
	public void flatFlyersandBrochuresPlaceOrderUsingCreditCardAlongWithArtwork() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> paymentDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPaymentDetails"));
		Map<String,String> successMessageDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForCheckoutSuccessDetails"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Flyers Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Flat Flyers and Brochures ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Flat Flyers and Brochures ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Flat Flyers and Brochures ProductURL").replace("envi", run.get("BetaKeyword")));
		bp.clearCart();
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 8.5 x 11"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 100LB Matte Book"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/1"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING No Coating"));
		pdp.selectFoldingOptions(pdpOptionsAsMap.get("FOLDING OPTIONS FLAT - No Folding"));
		pdp.selectDrillHole(pdpOptionsAsMap.get("DRILL HOLE No Hole Punch"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 500"));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME 2-4 Business Days"));
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Flyers Product"));
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Ground Shipping"));
		set.clickUploadYourArtButton();
		artwork.closeChatPopup(pdpOptionsAsMap.get("Flyers Product"));
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.selectAllTheCheckboxes();
		artwork.clickApproveArtwork();
		artwork.clickOkButtonOnTheArtworkModalPopup();
		switchToDefaultContent_custom();
		artwork.closeArtworkPage();
		set.clickAddToCartButton();
		cart.clickProceedToCheckOutButton("Proceed to Checkout");
		review.selectPaymentMethods(paymentDetails.get("Payment Method"));
		review.enterCardDetails(paymentDetails.get("Card Number"), paymentDetails.get("Card Exp Month"),paymentDetails.get("Card Exp Year"), paymentDetails.get("Card Verification No"));
		if(!(PropertiesUtility.getPropertyValueByKey("env").equals("printing"))) {
			review.clickPlaceOrderButton();
			ocp.validateThankYouMessage(successMessageDetails.get("After Place Order Expected Message"),sa);
			String orderId=ocp.viewOrderId();
//			waitFor(8000); 
//			bp.clickMyAccountLinkFromTheHeader();
//			bp.clickMyOrders();	
//			order.viewJobId(orderId);
		} 
		else {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Environment is "+PropertiesUtility.getPropertyValueByKey("env")+". Hence place order flow is validated until Reviews & Payments page");
		}
	}
	@Test()
	public void eDDMFullServicePostcardsPlaceOrderUsingCreditCardAlongWithArtwork() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> paymentDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPaymentDetails"));
		Map<String,String> successMessageDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForCheckoutSuccessDetails"));
		Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("EDDM Full Service Postcards Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("EDDM Full Service Postcards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("EDDM Full Service Postcards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("EDDM Full Service Postcards ProductURL").replace("envi", run.get("BetaKeyword")));
//		bp.clearCart();
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
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 500"));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME 2-4 Business Days"));
		pdp.enterProjectName(pdpOptionsAsMap.get("PROJECT/CLIENT NAME EDDM"));
		pdp.clickProceedToShippingButton();
		eDDM.switchToFrameForDMOrEDDM();
		eDDM.enterZipcode(fileUploading.get("Zipcode"));
		eDDM.clickSearchButton();
		eDDM.validateSaveOrderButtonIsDisabledBeforeSelectingRoutes();
		eDDM.clickListViewAndSelectTheFirstRoute();
		eDDM.validateEstimatedTotalPrice();
		eDDM.clickSaveOrderButton();
		switchToDefaultContent_custom();
		set.clickUploadYourArtButton();
		artwork.closeChatPopup(pdpOptionsAsMap.get("EDDM Full Service Postcards Product"));
		artwork.switchToArtworkFrameForDM_EDDM();
		artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.selectAllTheCheckboxes();
		artwork.clickApproveArtwork();
		artwork.clickOkButtonOnTheArtworkModalPopup();
		switchToDefaultContent_custom();
		artwork.closeArtworkPage();
		set.clickAddToCartButton();
		cart.clickProceedToCheckOutButton("Proceed to Checkout");
		review.selectPaymentMethods(paymentDetails.get("Payment Method"));
		review.enterCardDetails(paymentDetails.get("Card Number"), paymentDetails.get("Card Exp Month"),paymentDetails.get("Card Exp Year"), paymentDetails.get("Card Verification No"));
		if(!(PropertiesUtility.getPropertyValueByKey("env").equals("printing"))) {
			review.clickPlaceOrderButton();
			ocp.validateThankYouMessage(successMessageDetails.get("After Place Order Expected Message"),sa);
			String orderId=ocp.viewOrderId();
//			waitFor(8000); 
//			bp.clickMyAccountLinkFromTheHeader();
//			bp.clickMyOrders();	
//			order.viewJobId(orderId);
		} 
		else {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Environment is "+PropertiesUtility.getPropertyValueByKey("env")+". Hence place order flow is validated until Reviews & Payments page");
		}
	}
	
	@Test()
	public void directMailPostcardsPlaceOrderUsingCreditCardAlongWithArtwork() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> paymentDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPaymentDetails"));
		Map<String,String> successMessageDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForCheckoutSuccessDetails"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Direct Mail Postcards Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Direct Mail Postcards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Direct Mail Postcards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Direct Mail Postcards ProductURL").replace("envi", run.get("BetaKeyword")));
//		bp.clearCart();
		pdp.clickStartOverLink("Start Over Link");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 5 x 7"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 16PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING Matte"));
		pdp.selectSpotUVSides(pdpOptionsAsMap.get("SPOT UV SIDES No Spot UV"));
		pdp.selectMailingService(pdpOptionsAsMap.get("MAILING SERVICE Direct Mailing from FL"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 500"));
		pdp.selectPostageClass(pdpOptionsAsMap.get("POSTAGE CLASS Standard Class"));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME 2-4 Business Days"));
		pdp.enterProjectName(pdpOptionsAsMap.get("Direct Mail Postcards Product"));
		pdp.clickProceedToShippingButton();
		dm.enterTheInsideFrame();
		dm.uploadCSVFiles(System.getProperty("user.dir")+fileUploading.get("CSV File"));
		dm.clickLevelofAddress("Low Validation");
		dm.clickReviewValidation();
		dm.clickCalculatePostage();
		dm.clickCheckBoxForApproval();
		dm.clickAcceptMailingDetails();
		switchToDefaultContent_custom();
		set.clickUploadYourArtButton();
		artwork.closeChatPopup(pdpOptionsAsMap.get("Direct Mail Postcards Product"));
		artwork.switchToArtworkFrameForDM_EDDM();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.selectAllTheCheckboxes();
		artwork.clickApproveArtwork();
		artwork.clickOkButtonOnTheArtworkModalPopup();
		switchToDefaultContent_custom();
		artwork.closeArtworkPage();
		set.clickAddToCartButton();
		cart.clickProceedToCheckOutButton("Proceed to Checkout");
		review.selectPaymentMethods(paymentDetails.get("Payment Method"));
		review.enterCardDetails(paymentDetails.get("Card Number"), paymentDetails.get("Card Exp Month"),paymentDetails.get("Card Exp Year"), paymentDetails.get("Card Verification No"));
		if(!(PropertiesUtility.getPropertyValueByKey("env").equals("printing"))) {
			review.clickPlaceOrderButton();
			ocp.validateThankYouMessage(successMessageDetails.get("After Place Order Expected Message"),sa);
			String orderId=ocp.viewOrderId();
			waitFor(8000); 
			bp.clickMyAccountLinkFromTheHeader();
			bp.clickMyOrders();	
			order.viewJobId(orderId);
		} 
		else {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Environment is "+PropertiesUtility.getPropertyValueByKey("env")+". Hence place order flow is validated until Reviews & Payments page");
		}
	}
	
	@Test
	public void scrimVinylBannersPlaceOrderUsingCreditCardAlongWithArtwork() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> paymentDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPaymentDetails"));
		Map<String,String> successMessageDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForCheckoutSuccessDetails"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Scrim Vinyl Banners Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("envi", run.get("BetaKeyword")));
		bp.clearCart();
		pdp.clickStartOverLink("Start Over Link");
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 13oz Scrim Vinyl - Outdoor"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH"));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT"));
		pdp.addQuantity(pdpOptionsAsMap.get("QUANTITY"));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day"));
		pdp.selectFinishOptions(pdpOptionsAsMap.get("FINISH OPTIONS"));
		pdp.selectPolePockets(pdpOptionsAsMap.get("POLE POCKETS"));
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Scrim Vinyl Banners Product"));
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Next Day"));
		set.clickUploadYourArtButton();
		artwork.closeChatPopup(pdpOptionsAsMap.get("Scrim Vinyl Banners Product"));
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.selectAllTheCheckboxes();
		artwork.clickApproveArtwork();
		artwork.clickOkButtonOnTheArtworkModalPopup();
		switchToDefaultContent_custom();
		artwork.closeArtworkPage();
		set.clickAddToCartButton();
		cart.clickProceedToCheckOutButton("Proceed to Checkout");
		review.selectPaymentMethods(paymentDetails.get("Payment Method"));
		review.enterCardDetails(paymentDetails.get("Card Number"), paymentDetails.get("Card Exp Month"),paymentDetails.get("Card Exp Year"), paymentDetails.get("Card Verification No"));
		if(!(PropertiesUtility.getPropertyValueByKey("env").equals("printing"))) {
			review.clickPlaceOrderButton();
			ocp.validateThankYouMessage(successMessageDetails.get("After Place Order Expected Message"),sa);
			String orderId=ocp.viewOrderId();
//			waitFor(8000); 
//			bp.clickMyAccountLinkFromTheHeader();
//			bp.clickMyOrders();	
//			order.viewJobId(orderId);
		} 
		else {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Environment is "+PropertiesUtility.getPropertyValueByKey("env")+". Hence place order flow is validated until Reviews & Payments page");
		}
	}
}