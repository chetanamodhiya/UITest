package com.magento.pdptestScripts;

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

public class PDFProofs_SampleOfCompletedJob extends TestBase{
	ExcelUtility excelUtility = new ExcelUtility();
	BasePage bp = new BasePage();
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
	CustomerAccountPage account=new CustomerAccountPage();
	ShoppingCartPage cart=new ShoppingCartPage();
	ReviewAndPaymentsPage review=new ReviewAndPaymentsPage();
	OrderConfirmationPage ocp=new OrderConfirmationPage();
	OrderHistoryPage order=new OrderHistoryPage();
	ArtworkPage artwork=new ArtworkPage();
	SoftAssert sa=new SoftAssert();
	@Test
	public void validatePDFProofsAmountIsReflectedOnOrders() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> file=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> paymentDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPaymentDetails"));
		Map<String,String> successMessageDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForCheckoutSuccessDetails"));
		Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Business Cards Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("BetaKeyword")));
		bp.clearCart();
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 2 x 3.5"));
		pdp.clickShapeBox(pdpOptionsAsMap.get("SHAPE Rectangle"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 14PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/1"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING No Coating"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 500"));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME 2-4 Business Days"));
		double pdpSubTotal = pdp.viewSubTotalPriceWithPDFProofs(sa);
		pdp.clickProceedToShippingButton();
		double setSubTotal =set.viewSubTotalPrice();
		sa.assertEquals(setSubTotal, pdpSubTotal);
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Business Cards Product"));
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Ground Shipping"));
		double setShippingPrice=set.viewShippingPrice();
		double setTaxPrice=set.viewTaxPrice();
		double setGrandTotal=set.viewAndValidateGrandTotalPrice(sa);
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.validatePDFProofsInArtworkPage1(System.getProperty("user.dir")+file.get("Artwork File"),file.get("Artwork File"),confirmMessage.get("confirmPopupMessageForPDFProofs"), sa);
//		artwork.clickOkButtonOnTheArtworkModalPopup();
		switchToDefaultContent_custom();
		artwork.closeArtworkPage();
		set.clickAddToCartButton();
		sa.assertEquals(setSubTotal, cart.viewSubTotalPrice());
		sa.assertEquals(setShippingPrice, cart.viewShippingPrice());
		sa.assertEquals(setTaxPrice, cart.viewTaxPrice());
		sa.assertEquals(setGrandTotal, cart.viewTotalPrice());
		cart.validateTotal_JobTotalPriceBeforeApplyingCoupon(sa);
		cart.clickProceedToCheckOutButton("Proceed to Checkout");
		sa.assertEquals(review.viewGrandTotalPrice(),cart.viewTotalPrice());
		ExtentFactory.getInstance().getExtent().log(Status.INFO, review.viewGrandTotalPrice()+ " matched with " +cart.viewTotalPrice());
		review.selectPaymentMethods(paymentDetails.get("Payment Method"));
		review.enterCardDetails(paymentDetails.get("Card Number"), paymentDetails.get("Card Exp Month"),paymentDetails.get("Card Exp Year"), paymentDetails.get("Card Verification No"));
		if(!(PropertiesUtility.getPropertyValueByKey("env").equals("printing"))) {
			review.clickPlaceOrderButton();
			String orderId=ocp.viewOrderId();
			bp.clickMyAccountLinkFromTheHeader();
			bp.clickMyOrders();	
			order.clickViewOrderButton(orderId);
			sa.assertEquals(setSubTotal, order.viewSubTotalPrice());
			sa.assertEquals(setShippingPrice, order.viewShippingPrice());
			sa.assertEquals(setTaxPrice, order.viewTaxPrice());
			sa.assertEquals(setGrandTotal,order.viewAndValidateGrandTotalPrice(sa));
			order.validatePDFProofsOrSampleOfCompletedJobText(sa);
		} 
		else 
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Environment is "+PropertiesUtility.getPropertyValueByKey("env")+". Hence place order flow is validated until Reviews & Payments page");
		sa.assertAll();
	}
	
	@Test
	public void validateSampleofCompletedJobAmountIsReflectedOnOrders() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
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
		bp.clearCart();
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 2 x 3.5"));
		pdp.clickShapeBox(pdpOptionsAsMap.get("SHAPE Rectangle"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 14PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/1"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING No Coating"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 500"));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME 2-4 Business Days"));
		double pdpSubTotal = pdp.viewSubTotalPriceWithSampleOfCompletedJob(sa);
		pdp.clickProceedToShippingButton();
		double setSubTotal = set.viewSubTotalPrice();
		sa.assertEquals(setSubTotal, pdpSubTotal);
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Business Cards Product"));
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Ground Shipping"));
		double setShippingPrice=set.viewShippingPrice();
		double setTaxPrice=set.viewTaxPrice();
		double setGrandTotal=set.viewAndValidateGrandTotalPrice(sa);
		set.clickAddToCartButton();
		sa.assertEquals(setSubTotal, cart.viewSubTotalPrice());
		sa.assertEquals(setShippingPrice, cart.viewShippingPrice());
		sa.assertEquals(setTaxPrice, cart.viewTaxPrice());
		sa.assertEquals(setGrandTotal, cart.viewTotalPrice());
		cart.validateTotal_JobTotalPriceBeforeApplyingCoupon(sa);
		cart.clickProceedToCheckOutButton("Proceed to Checkout");
		sa.assertEquals(review.viewGrandTotalPrice(),cart.viewTotalPrice());
		ExtentFactory.getInstance().getExtent().log(Status.INFO, review.viewGrandTotalPrice()+ " matched with " +cart.viewTotalPrice());
		review.selectPaymentMethods(paymentDetails.get("Payment Method"));
		review.enterCardDetails(paymentDetails.get("Card Number"), paymentDetails.get("Card Exp Month"),paymentDetails.get("Card Exp Year"), paymentDetails.get("Card Verification No"));
		if(!(PropertiesUtility.getPropertyValueByKey("env").equals("printing"))) {
			review.clickPlaceOrderButton();
			String orderId=ocp.viewOrderId();
			bp.clickMyAccountLinkFromTheHeader();
			bp.clickMyOrders();	
			order.clickViewOrderButton(orderId);
			sa.assertEquals(setSubTotal, order.viewSubTotalPrice());
			sa.assertEquals(setShippingPrice, order.viewShippingPrice());
			sa.assertEquals(setTaxPrice, order.viewTaxPrice());
			sa.assertEquals(setGrandTotal,order.viewAndValidateGrandTotalPrice(sa));
			order.validatePDFProofsOrSampleOfCompletedJobText(sa);
		} 
		else 
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Environment is "+PropertiesUtility.getPropertyValueByKey("env")+". Hence place order flow is validated until Reviews & Payments page");
		sa.assertAll();
	}
	
	@Test
	public void validatePDFProofs_SampleofCompletedJobAmountIsReflectedOnOrders() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		Map<String,String> file=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> paymentDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPaymentDetails"));
		Map<String,String> successMessageDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForCheckoutSuccessDetails"));
		Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Business Cards Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("BetaKeyword")));
		bp.clearCart();
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 2 x 3.5"));
		pdp.clickShapeBox(pdpOptionsAsMap.get("SHAPE Rectangle"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 14PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/1"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING No Coating"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 500"));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME 2-4 Business Days"));
		double pdpSubTotal = pdp.viewSubTotalPriceWithPDFProofs_SampleOfCompletedJob(sa);
		pdp.clickProceedToShippingButton();
		double setSubTotal =set.viewSubTotalPrice();
		sa.assertEquals(setSubTotal, pdpSubTotal);
		ExtentFactory.getInstance().getExtent().log(Status.INFO, setSubTotal+ " matched with " +pdpSubTotal);
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Business Cards Product"));
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Ground Shipping"));
		double setShippingPrice=set.viewShippingPrice();
		double setTaxPrice=set.viewTaxPrice();
		double setGrandTotal=set.viewAndValidateGrandTotalPrice(sa);
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.validatePDFProofsInArtworkPage1(System.getProperty("user.dir")+file.get("Artwork File"),file.get("Artwork File"),confirmMessage.get("confirmPopupMessageForPDFProofs"), sa);
//		artwork.clickOkButtonOnTheArtworkModalPopup();
		switchToDefaultContent_custom();
		artwork.closeArtworkPage();
		set.clickAddToCartButton();
		sa.assertEquals(setSubTotal, cart.viewSubTotalPrice());
		ExtentFactory.getInstance().getExtent().log(Status.INFO, setSubTotal+ " matched with " +cart.viewSubTotalPrice());
		sa.assertEquals(setShippingPrice, cart.viewShippingPrice());
		ExtentFactory.getInstance().getExtent().log(Status.INFO, setShippingPrice+ " matched with " +cart.viewShippingPrice());
		sa.assertEquals(setTaxPrice, cart.viewTaxPrice());
		ExtentFactory.getInstance().getExtent().log(Status.INFO, setTaxPrice+ " matched with " +cart.viewTaxPrice());
		sa.assertEquals(setGrandTotal, cart.viewTotalPrice());
		ExtentFactory.getInstance().getExtent().log(Status.INFO, setGrandTotal+ " matched with " +cart.viewTotalPrice());
		cart.validateTotal_JobTotalPriceBeforeApplyingCoupon(sa);
		cart.clickProceedToCheckOutButton("Proceed to Checkout");
		sa.assertEquals(review.viewGrandTotalPrice(),cart.viewTotalPrice());
		ExtentFactory.getInstance().getExtent().log(Status.INFO, review.viewGrandTotalPrice()+ " matched with " +cart.viewTotalPrice());
		review.selectPaymentMethods(paymentDetails.get("Payment Method"));
		review.enterCardDetails(paymentDetails.get("Card Number"), paymentDetails.get("Card Exp Month"),paymentDetails.get("Card Exp Year"), paymentDetails.get("Card Verification No"));
		if(!(PropertiesUtility.getPropertyValueByKey("env").equals("printing"))) {
			review.clickPlaceOrderButton();
			String orderId=ocp.viewOrderId();
			bp.clickMyAccountLinkFromTheHeader();
			bp.clickMyOrders();	
			order.clickViewOrderButton(orderId);
			sa.assertEquals(setSubTotal, order.viewSubTotalPrice());
			ExtentFactory.getInstance().getExtent().log(Status.INFO, setSubTotal+ " matched with " +order.viewSubTotalPrice());
			sa.assertEquals(setShippingPrice, order.viewShippingPrice());
			ExtentFactory.getInstance().getExtent().log(Status.INFO, setShippingPrice+ " matched with " +order.viewShippingPrice());
			sa.assertEquals(setTaxPrice, order.viewTaxPrice());
			ExtentFactory.getInstance().getExtent().log(Status.INFO, setTaxPrice+ " matched with " +order.viewTaxPrice());
			sa.assertEquals(setGrandTotal,order.viewAndValidateGrandTotalPrice(sa));
			ExtentFactory.getInstance().getExtent().log(Status.INFO, setGrandTotal+ " matched with " +order.viewAndValidateGrandTotalPrice(sa));
			order.validatePDFProofsOrSampleOfCompletedJobText(sa);
		} 
		else 
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Environment is "+PropertiesUtility.getPropertyValueByKey("env")+". Hence place order flow is validated until Reviews & Payments page");
		sa.assertAll();
	}
}
