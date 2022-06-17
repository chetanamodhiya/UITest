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
 * Place the order and store job ID 
 * for product High Tack Adhesive Vinyl
 * and check type in windsurfer
 * Reference Jira ID : CQA-280
 */
public class MIL4_AVPERProductSizeRestrictionsForWorkflowCQA_280 extends TestBase{
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
	
	@Test
	public void highTackAdhesiveVinylForSize12X12() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> paymentDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPaymentDetails"));
		Map<String,String> successMessageDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForCheckoutSuccessDetails"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("High Tack Adhesive Vinyl Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("High Tack Adhesive Vinyl ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("High Tack Adhesive Vinyl ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("High Tack Adhesive Vinyl ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over Link");
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 4mil High Tack Adhesive Vinyl"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH 12"));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT 12"));
		pdp.addQuantity(pdpOptionsAsMap.get("QUANTITY"));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day"));
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(setsAndShippingOptionsAsMap.get("PROJECT/CLIENT NAME For SIZE 12X12"));
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Ground Shipping"));
		set.clickAddToCartButton();
		cart.clickProceedToCheckOutButton("Proceed to Checkout");
		review.selectPaymentMethods(paymentDetails.get("Payment Method"));
		review.enterCardDetails(paymentDetails.get("Card Number"), paymentDetails.get("Card Exp Month"),paymentDetails.get("Card Exp Year"), paymentDetails.get("Card Verification No"));
		if(!(PropertiesUtility.getPropertyValueByKey("env").equals("printing"))) {
			review.clickPlaceOrderButton();
			ocp.validateThankYouMessage(successMessageDetails.get("After Place Order Expected Message"),sa);
			String orderId=ocp.viewOrderId();
			waitFor(5000); 
			bp.clickMyAccountLinkFromTheHeader();
			bp.clickMyOrders();	
			order.viewJobId(orderId);
		} 
		else {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Environment is "+PropertiesUtility.getPropertyValueByKey("env")+". Hence place order flow is validated until Reviews & Payments page");
		}
	}
	
	@Test
	public void highTackAdhesiveVinylForSize12X13() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> paymentDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPaymentDetails"));
		Map<String,String> successMessageDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForCheckoutSuccessDetails"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("High Tack Adhesive Vinyl Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("High Tack Adhesive Vinyl ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("High Tack Adhesive Vinyl ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("High Tack Adhesive Vinyl ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over Link");
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 4mil High Tack Adhesive Vinyl"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH 12"));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT 13"));
		pdp.addQuantity(pdpOptionsAsMap.get("QUANTITY"));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day"));
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(setsAndShippingOptionsAsMap.get("PROJECT/CLIENT NAME For SIZE 12X13"));
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Ground Shipping"));
		set.clickAddToCartButton();
		cart.clickProceedToCheckOutButton("Proceed to Checkout");
		review.selectPaymentMethods(paymentDetails.get("Payment Method"));
		review.enterCardDetails(paymentDetails.get("Card Number"), paymentDetails.get("Card Exp Month"),paymentDetails.get("Card Exp Year"), paymentDetails.get("Card Verification No"));
		if(!(PropertiesUtility.getPropertyValueByKey("env").equals("printing"))) {
			review.clickPlaceOrderButton();
			ocp.validateThankYouMessage(successMessageDetails.get("After Place Order Expected Message"),sa);
			String orderId=ocp.viewOrderId();
			waitFor(5000); 
			bp.clickMyAccountLinkFromTheHeader();
			bp.clickMyOrders();	
			order.viewJobId(orderId);
		} 
		else {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Environment is "+PropertiesUtility.getPropertyValueByKey("env")+". Hence place order flow is validated until Reviews & Payments page");
		}
	}
}