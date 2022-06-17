package com.magento.customerImport;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
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
import com.magento.testBase.TestBaseWithBefore_AfterMethod;

public class TestUsersMigrationShippingquoteAndPlaceOrder extends TestBaseWithBefore_AfterMethod{

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
	OrderConfirmationPage checkOutSuccess=new OrderConfirmationPage();
	OrderHistoryPage order=new OrderHistoryPage();
	SoftAssert sa=new SoftAssert();
	
		@Test(dataProvider = "getData")
		public void userList(Hashtable<String,String> data) throws Throwable{
			Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
			Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
			Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
			Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
			Map<String,String> paymentDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPaymentDetails"));
			Map<String,String> successMessageDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForCheckoutSuccessDetails"));
			bp.waitForPageLoad();
			bp.loginFromHeader(data.get("email"), data.get("password"));
			bp.validateRewardsPoints(data.get("rewards"),sa);
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Flyers Product"));
			if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
				navigateTo_custom(pdpOptionsAsMap.get("Flat Flyers and Brochures ProductURL").replace("envi", run.get("DevKeyword")));
			else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
				navigateTo_custom(pdpOptionsAsMap.get("Flat Flyers and Brochures ProductURL").replace("envi", run.get("StagingKeyword")));
			else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
				navigateTo_custom(pdpOptionsAsMap.get("Flat Flyers and Brochures ProductURL").replace("envi", run.get("BetaKeyword")));
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
			set.validateAvailabilityOfShippingMethodAndClocktheTime();
//			set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Ground Shipping"));
//			set.clickUploadYourArtButton();
//			artwork.switchToArtworkFrameBeforePlaceOrder();
//			artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
//			artwork.selectAllTheCheckboxes();
//			artwork.clickApproveArtwork();
//			artwork.clickOkButtonOnTheArtworkModalPopup();
//			switchToDefaultContent_custom();
//			artwork.closeArtworkPage();
//			set.clickAddToCartButton();
//			cart.clickProceedToCheckOutButton("Proceed to Checkout");
//			review.selectPaymentMethods("Credit Card");
//			review.enterCardDetails(paymentDetails.get("Card Number"), paymentDetails.get("Card Exp Month"),paymentDetails.get("Card Exp Year"), paymentDetails.get("Card Verification No"));
//			review.clickPlaceOrderButton();
//			String successMessage=checkOutSuccess.validateThankyouMessage();
//			if(successMessageDetails.get("CheckoutSuccess Message").equalsIgnoreCase(successMessage)) {
//				Assert.assertEquals(successMessageDetails.get("CheckoutSuccess Message"),successMessage);
//				ExtentFactory.getInstance().getExtent().log(Status.INFO, "After placed the order:  "+successMessage+" message is displayed");
//			}
//			String orderId=checkOutSuccess.viewOrderId();
	//		waitFor(8000);
	//		bp.clickMyAccountLinkFromTheHeader();
	//		bp.clickMyOrders();
	//		order.viewJobId(orderId);
	//		}
//			accountPage.clickUploadArtwork();
//			artwork.enterTheInsideFrameAfterPlaceOrder();
//			artwork.dragandDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"));
//			artwork.clickEditArtworkButton();
//			artwork.clickOkToPrintButton();
//			artwork.clickCheckbox();
//			artwork.clickReadyForProduction();
//			artwork.oKForAddArtwork();
//			moveOutTheFrame_custom();
			sa.assertAll();
		}

		@DataProvider
		public Object[][] getData(Method m) throws EncryptedDocumentException, IOException{
			String sheetName = m.getName();
			int rows = excelUtility.getRowCount(PropertiesUtility.getPropertyValueByKey("UserList_excelPath"), sheetName);
			int columns = excelUtility.getColumnCount(PropertiesUtility.getPropertyValueByKey("UserList_excelPath"), sheetName);
			rows = rows+1;
			
			Object[][] data = new Object[rows-1][1];
			
			Hashtable<String, String> table = null;
			
			for(int rowNum = 2; rowNum <= rows; rowNum++) {
				table = new Hashtable<String, String>();
				for(int colNum = 0; colNum < columns; colNum++) {
					table.put(excelUtility.getCellData(PropertiesUtility.getPropertyValueByKey("UserList_excelPath"), sheetName, 1, colNum), excelUtility.getCellData(PropertiesUtility.getPropertyValueByKey("UserList_excelPath"), sheetName, rowNum, colNum));
					data[rowNum-2][0] = table;
				}
			}
			return data;
		}
}
