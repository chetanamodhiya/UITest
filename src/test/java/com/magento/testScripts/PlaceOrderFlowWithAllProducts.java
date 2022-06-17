package com.magento.testScripts;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
import com.magento.testBase.TestBase;

public class PlaceOrderFlowWithAllProducts extends TestBase{
	
ExcelUtility excelUtility = new ExcelUtility();
BasePage bp = new BasePage();
PDPProductOptionsPage pdp=new PDPProductOptionsPage();
DirectMailingServicePage dm=new DirectMailingServicePage();
EDDMAddressPage eDDM=new EDDMAddressPage();
PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
ArtworkPage artwork=new ArtworkPage();
ShoppingCartPage cart=new ShoppingCartPage();
ReviewAndPaymentsPage review=new ReviewAndPaymentsPage();
CustomerAccountPage accountPage=new CustomerAccountPage();
OrderConfirmationPage ocp=new OrderConfirmationPage();
OrderHistoryPage order=new OrderHistoryPage();
ArrayList<String> list=new ArrayList<>();
SoftAssert sa=new SoftAssert();

	@Test(dataProvider = "getData")
	public void validateShippingMethod(Hashtable<String,String> data) throws Throwable{
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("ShippingMethodvalidationExcel"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> file=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("ShippingMethodvalidationExcel"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> paymentDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("ShippingMethodvalidationExcel"), PropertiesUtility.getPropertyValueByKey("sheetForPaymentDetails"));
		Map<String,String> successMessageDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("ShippingMethodvalidationExcel"), PropertiesUtility.getPropertyValueByKey("sheetForCheckoutSuccessDetails"));
		Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("ShippingMethodvalidationExcel"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));

//		bp.clearCart();
		if(run.get("Env").equalsIgnoreCase("Dev"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
		else if(run.get("Env").equalsIgnoreCase("Stage"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(run.get("Env").equalsIgnoreCase("Printing"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over Link");

		list= excelUtility.storeDataToList(PropertiesUtility.getPropertyValueByKey("ShippingMethodvalidationExcel"), PropertiesUtility.getPropertyValueByKey("sheetForShapeProducts"));
		if(list.contains(data.get("Products"))) {
			pdp.clickShapeBox();
			pdp.clickRadiusOfCorners();
		}
		
		if(data.get("Products").contains("EDDM Full Service")) {
			pdp.clickCalendarDateField();
			pdp.selectDateFromCalendar();
		}
		pdp.selectAnOptionInAllTheDropdowns();
		if(!(data.get("Products").contains("EDDM Full Service")))
				pdp.selectRunsizeOrQuantity(file.get("Quantity"));
		pdp.clickProceedToShippingButton();

		//for Direct Mail Products
		if(data.get("Products").contains("Direct Mail")) {
			dm.enterTheInsideFrame();
			dm.clickFilesHere();
			dm.uploadTheFiles(System.getProperty("user.dir")+file.get("CSV File"));
			dm.clickFilesHere();
			dm.uploadTheFiles(System.getProperty("user.dir")+file.get("CSV File"));
			dm.clickLevelofAddress("Low Validation");
			dm.clickReviewValidation();
			dm.clickCalculatePostage();
			dm.clickCheckBoxForApproval();
			dm.clickAcceptMailingDetails();
			switchToDefaultContent_custom();
		}
		//for EDDM Full Service Products
		else if(data.get("Products").contains("EDDM Full Service")) {
			eDDM.switchToFrameForDMOrEDDM();
			eDDM.enterZipcode(file.get("ZIPCODE"));
			eDDM.clickSearchButton();
			eDDM.validateSaveOrderButtonIsDisabledBeforeSelectingRoutes();
			eDDM.clickListViewAndSelectTheFirstRoute();
			eDDM.validateEstimatedTotalPrice();
			eDDM.clickSaveOrderButton();
			switchToDefaultContent_custom();
		}
		
		set.clickUploadYourArtButton();
		if(data.get("Products").contains("Direct Mail") || data.get("Products").contains("EDDM Full Service")) {
			artwork.switchToArtworkFrameForDM_EDDM();
		}
		else {
			artwork.switchToArtworkFrameBeforePlaceOrder();
		}
		if(data.get("Products").contains("EDDM Full Service"))
				artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessageForEDDM"),sa);
		
		else if(data.get("Products").contains("Boxes")) {
			artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessageForBoxes"),sa);
			artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+file.get("Artwork File"),file.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
		}
		
		artwork.dragAndDropFiles(System.getProperty("user.dir")+file.get("Artwork File"),file.get("Artwork File"));
		if(!(data.get("Products").contains("Boxes"))) {
			artwork.selectAllTheCheckboxes();
			artwork.clickApproveArtwork();
		}
		artwork.clickOkButtonOnTheArtworkModalPopup();
		switchToDefaultContent_custom();
		artwork.closeArtworkPage();

		if(data.get("Products").contains("Direct Mail")) {
			set.clickSendmetheremainders();
		}
		else if(data.get("Products").contains("EDDM Full Service")) {
			set.clickJobSamples();
		}

		set.selectSecondOptionFromShippingMethod();
		set.clickAddToCartButton();
		cart.clickProceedToCheckOutButton("Proceed to Checkout");
		
		review.selectPaymentMethods(paymentDetails.get("Payment Method"));
		review.enterCardDetails(paymentDetails.get("Card Number"), paymentDetails.get("Card Exp Month"),paymentDetails.get("Card Exp Year"), paymentDetails.get("Card Verification No"));
		if(!(PropertiesUtility.getPropertyValueByKey("env").equals("printing"))) {
			review.clickPlaceOrderButton();
			ocp.validateThankYouMessage(successMessageDetails.get("OrderSuccess"),sa);
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

	@DataProvider
	public Object[][] getData(Method m) throws EncryptedDocumentException, IOException{
		String sheetName = m.getName();
		int rows = excelUtility.getRowCount(PropertiesUtility.getPropertyValueByKey("ShippingMethodvalidationExcel"), sheetName);
		int columns = excelUtility.getColumnCount(PropertiesUtility.getPropertyValueByKey("ShippingMethodvalidationExcel"), sheetName);
		rows = rows+1;
		
		Object[][] data = new Object[rows-1][1];
		
		Hashtable<String, String> table = null;
		
		for(int rowNum = 2; rowNum <= rows; rowNum++) {
			table = new Hashtable<String, String>();
			for(int colNum = 0; colNum < columns; colNum++) {
				table.put(excelUtility.getCellData(PropertiesUtility.getPropertyValueByKey("ShippingMethodvalidationExcel"), sheetName, 1, colNum), excelUtility.getCellData(PropertiesUtility.getPropertyValueByKey("ShippingMethodvalidationExcel"), sheetName, rowNum, colNum));
				data[rowNum-2][0] = table;
			}
		}
		return data;
	}
}

