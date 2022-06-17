/*
 * Created By : Chetana
 */

package com.magento.artworkTestScripts;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.magento.pages.ArtworkPage;
import com.magento.pages.BasePage;
import com.magento.pages.DirectMailingServicePage;
import com.magento.pages.PDPProductOptionsPage;
import com.magento.pages.PDPSetsAndShippingPage;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

/*
 * Artwork behavior is validated for all the boxes products
 * Reference Jira ID : M2-2236
 */
public class ValidateArtworkForBoxesProducts_CQA_70_M2_2236 extends TestBase{
	ExcelUtility excelUtility = new ExcelUtility();
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
	ArtworkPage artwork=new ArtworkPage();
	DirectMailingServicePage dm=new DirectMailingServicePage();
	BasePage bp=new BasePage();
	SoftAssert sa=new SoftAssert();
	
	
	@Test(dataProvider = "getData")
	public void akuafoilPrintAndTrimBoxes(Hashtable<String,String> data) throws Throwable{
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));

		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(data.get("SIZE"));
		pdp.selectStock(data.get("STOCK"));
		pdp.selectColorspec(data.get("COLORSPEC"));
		pdp.selectCoating(data.get("COATING"));
		pdp.selectSpotUVSides(data.get("SPOT UV SIDES"));
		pdp.clickRunSize(data.get("RUNSIZE"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(data.get("Products"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
		artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
//		sa.assertAll();
	}
	
	
	@Test(dataProvider = "getData")
	public void akuafoilCubeBoxes(Hashtable<String,String> data) throws Throwable{
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(data.get("SIZE"));
		pdp.selectStock(data.get("STOCK"));
		pdp.selectColorspec(data.get("COLORSPEC"));
		pdp.selectCoating(data.get("COATING"));
		pdp.clickRunSize(data.get("RUNSIZE"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(data.get("Products"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
		artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
		sa.assertAll();
	}
	
	@Test(dataProvider = "getData")
	public void akuafoilWineBoxes(Hashtable<String,String> data) throws Throwable{
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(data.get("SIZE"));
		pdp.selectStock(data.get("STOCK"));
		pdp.selectColorspec(data.get("COLORSPEC"));
		pdp.selectCoating(data.get("COATING"));
		pdp.selectHandleOptions(data.get("HANDLE OPTIONS"));
		pdp.clickRunSize(data.get("RUNSIZE"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(data.get("Products"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
		artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
		sa.assertAll();
	}
	
	
	@Test(dataProvider = "getData")
	public void akuafoiPillowBoxes(Hashtable<String,String> data) throws Throwable{
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(data.get("SIZE"));
		pdp.selectStock(data.get("STOCK"));
		pdp.selectColorspec(data.get("COLORSPEC"));
		pdp.selectCoating(data.get("COATING"));
		pdp.selectEdgeJoinOptions(data.get("EDGE JOIN OPTIONS"));
		pdp.clickRunSize(data.get("RUNSIZE"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(data.get("Products"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
		artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
		sa.assertAll();
	}
	
	
	
	@Test(dataProvider = "getData")
	public void akuafoilRollEndTuckBoxes(Hashtable<String,String> data) throws Throwable{
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(data.get("SIZE"));
		pdp.selectStock(data.get("STOCK"));
		pdp.selectColorspec(data.get("COLORSPEC"));
		pdp.selectCoating(data.get("COATING"));
		pdp.clickRunSize(data.get("RUNSIZE"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(data.get("Products"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
		artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
		sa.assertAll();
	}
	
	
	
	
	
	@Test(dataProvider = "getData")
	public void akuafoilSalesPresentationBoxes(Hashtable<String,String> data) throws Throwable{
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(data.get("SIZE"));
		pdp.selectStock(data.get("STOCK"));
		pdp.selectColorspec(data.get("COLORSPEC"));
		pdp.selectCoating(data.get("COATING"));
		pdp.selectFastenerOptions(data.get("FASTENER OPTIONS"));
		pdp.clickRunSize(data.get("RUNSIZE"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(data.get("Products"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
		artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
		sa.assertAll();
	}
	
	
	
	
	@Test(dataProvider = "getData")
	public void akuafoilBusinessCardBoxes(Hashtable<String,String> data) throws Throwable{
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(data.get("SIZE"));
		pdp.selectStock(data.get("STOCK"));
		pdp.selectColorspec(data.get("COLORSPEC"));
		pdp.selectCoating(data.get("COATING"));
		pdp.selectBusinessCardBoxSize(data.get("BUSINESS CARD BOX SIZE"));
		pdp.clickRunSize(data.get("RUNSIZE"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(data.get("Products"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
		artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
		sa.assertAll();
	}
	
	
	@Test(dataProvider = "getData")
	public void akuafoilGolfBallBoxes(Hashtable<String,String> data) throws Throwable{
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(data.get("SIZE"));
		pdp.selectStock(data.get("STOCK"));
		pdp.selectColorspec(data.get("COLORSPEC"));
		pdp.selectCoating(data.get("COATING"));
		pdp.clickRunSize(data.get("RUNSIZE"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(data.get("Products"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
		artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
		sa.assertAll();
	}
	
	
	@Test(dataProvider = "getData")
	public void cubeBoxes(Hashtable<String,String> data) throws Throwable{
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(data.get("SIZE"));
		pdp.selectStock(data.get("STOCK"));
		pdp.selectColorspec(data.get("COLORSPEC"));
		pdp.selectCoating(data.get("COATING"));
		pdp.selectSpotUVSides(data.get("SPOT UV SIDES"));
		pdp.clickRunSize(data.get("RUNSIZE"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(data.get("Products"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
		artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
		sa.assertAll();
	}
	

@Test(dataProvider = "getData")
public void printAndTrimBoxes(Hashtable<String,String> data) throws Throwable{
	Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
	Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
	Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
	ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
	if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
	pdp.clickStartOverLink("Start Over Link");
	pdp.selectSize(data.get("SIZE"));
	pdp.selectStock(data.get("STOCK"));
	pdp.selectColorspec(data.get("COLORSPEC"));
	pdp.selectCoating(data.get("COATING"));
	pdp.selectSpotUVSides(data.get("SPOT UV SIDES"));
	pdp.clickRunSize(data.get("RUNSIZE"));
	pdp.selectSecondOptionFromTurnAroundTimeDropdown();
	pdp.clickProceedToShippingButton();
	set.enterProjectOrClientName(data.get("Products"));
	set.selectSecondOptionFromShippingMethod();
	set.clickUploadYourArtButton();
	artwork.switchToArtworkFrameBeforePlaceOrder();
	artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
	artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
	sa.assertAll();
}

@Test(dataProvider = "getData")
public void golfBallBoxes(Hashtable<String,String> data) throws Throwable{
	Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
	Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
	Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
	ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
	if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
	pdp.clickStartOverLink("Start Over Link");
	pdp.selectSize(data.get("SIZE"));
	pdp.selectStock(data.get("STOCK"));
	pdp.selectColorspec(data.get("COLORSPEC"));
	pdp.selectCoating(data.get("COATING"));
	pdp.selectSpotUVSides(data.get("SPOT UV SIDES"));
	pdp.clickRunSize(data.get("RUNSIZE"));
	pdp.selectSecondOptionFromTurnAroundTimeDropdown();
	pdp.clickProceedToShippingButton();
	set.enterProjectOrClientName(data.get("Products"));
	set.selectSecondOptionFromShippingMethod();
	set.clickUploadYourArtButton();
	artwork.switchToArtworkFrameBeforePlaceOrder();
	artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
	artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
	sa.assertAll();
}	
	

@Test(dataProvider = "getData")
public void wineBoxes(Hashtable<String,String> data) throws Throwable{
	Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
	Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
	Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
	ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
	if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
	pdp.clickStartOverLink("Start Over Link");
	pdp.selectSize(data.get("SIZE"));
	pdp.selectStock(data.get("STOCK"));
	pdp.selectColorspec(data.get("COLORSPEC"));
	pdp.selectCoating(data.get("COATING"));
	pdp.selectSpotUVSides(data.get("SPOT UV SIDES"));
	pdp.selectHandleOptions(data.get("HANDLE OPTIONS"));
	pdp.clickRunSize(data.get("RUNSIZE"));
	pdp.selectSecondOptionFromTurnAroundTimeDropdown();
	pdp.clickProceedToShippingButton();
	set.enterProjectOrClientName(data.get("Products"));
	set.selectSecondOptionFromShippingMethod();
	set.clickUploadYourArtButton();
	artwork.switchToArtworkFrameBeforePlaceOrder();
	artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
	artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
	sa.assertAll();
}

@Test(dataProvider = "getData")
public void salesPresentationBoxes(Hashtable<String,String> data) throws Throwable{
	Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
	Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
	Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
	ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
	if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
	pdp.clickStartOverLink("Start Over Link");
	pdp.selectSize(data.get("SIZE"));
	pdp.selectStock(data.get("STOCK"));
	pdp.selectColorspec(data.get("COLORSPEC"));
	pdp.selectCoating(data.get("COATING"));
	pdp.selectSpotUVSides(data.get("SPOT UV SIDES"));
	pdp.selectFastenerOptions(data.get("FASTENER OPTIONS"));
	pdp.clickRunSize(data.get("RUNSIZE"));
	pdp.selectSecondOptionFromTurnAroundTimeDropdown();
	pdp.clickProceedToShippingButton();
	set.enterProjectOrClientName(data.get("Products"));
	set.selectSecondOptionFromShippingMethod();
	set.clickUploadYourArtButton();
	artwork.switchToArtworkFrameBeforePlaceOrder();
	artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
	artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
	sa.assertAll();
}

@Test(dataProvider = "getData")
public void rollEndTuckBoxes(Hashtable<String,String> data) throws Throwable{
	Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
	Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
	Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
	ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
	if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
	pdp.clickStartOverLink("Start Over Link");
	pdp.selectSize(data.get("SIZE"));
	pdp.selectStock(data.get("STOCK"));
	pdp.selectColorspec(data.get("COLORSPEC"));
	pdp.selectCoating(data.get("COATING"));
	pdp.selectSpotUVSides(data.get("SPOT UV SIDES"));
	pdp.clickRunSize(data.get("RUNSIZE"));
	pdp.selectSecondOptionFromTurnAroundTimeDropdown();
	pdp.clickProceedToShippingButton();
	set.enterProjectOrClientName(data.get("Products"));
	set.selectSecondOptionFromShippingMethod();
	set.clickUploadYourArtButton();
	artwork.switchToArtworkFrameBeforePlaceOrder();
	artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
	artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
	sa.assertAll();
}

@Test(dataProvider = "getData")
public void pillowBoxes(Hashtable<String,String> data) throws Throwable{
	Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
	Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
	Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
	ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
	if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
	pdp.clickStartOverLink("Start Over Link");
	pdp.selectSize(data.get("SIZE"));
	pdp.selectStock(data.get("STOCK"));
	pdp.selectColorspec(data.get("COLORSPEC"));
	pdp.selectCoating(data.get("COATING"));
	pdp.selectSpotUVSides(data.get("SPOT UV SIDES"));
	pdp.selectEdgeJoinOptions(data.get("EDGE JOIN OPTIONS"));
	pdp.clickRunSize(data.get("RUNSIZE"));
	pdp.selectSecondOptionFromTurnAroundTimeDropdown();
	pdp.clickProceedToShippingButton();
	set.enterProjectOrClientName(data.get("Products"));
	set.selectSecondOptionFromShippingMethod();
	set.clickUploadYourArtButton();
	artwork.switchToArtworkFrameBeforePlaceOrder();
	artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
	artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
	sa.assertAll();
}
@Test(dataProvider = "getData")
public void businessCardBoxes(Hashtable<String,String> data) throws Throwable{
	Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
	Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
	Map<String,String> confirmMessage=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForConfirmMessage"));
	ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+data.get("Products"));
	if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("DevKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("StagingKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
		navigateTo_custom(data.get("ProductURL").replace("envi", run.get("BetaKeyword")));
	pdp.clickStartOverLink("Start Over Link");
	pdp.selectSize(data.get("SIZE"));
	pdp.selectStock(data.get("STOCK"));
	pdp.selectColorspec(data.get("COLORSPEC"));
	pdp.selectCoating(data.get("COATING"));
	pdp.selectSpotUVSides(data.get("SPOT UV SIDES"));
	pdp.selectBusinessCardBoxSize(data.get("BUSINESS CARD BOX SIZE"));
	pdp.clickRunSize(data.get("RUNSIZE"));
	pdp.selectSecondOptionFromTurnAroundTimeDropdown();
	pdp.clickProceedToShippingButton();
	set.enterProjectOrClientName(data.get("Products"));
	set.selectSecondOptionFromShippingMethod();
	set.clickUploadYourArtButton();
	artwork.switchToArtworkFrameBeforePlaceOrder();
	artwork.clickUnderstoodButton(confirmMessage.get("UnderstoodMessage"),sa);
	artwork.validateBoxesProductsInArtwork(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"),confirmMessage.get("confirmPopupMessage"),sa);
	sa.assertAll();
}

@Test()
	public void deleteArtworkFilesFromMyMedia() throws InterruptedException {
		navigateTo_custom(PropertiesUtility.getPropertyValueByKey("appUrl"));
		bp.clickMyMedia();
		artwork.switchToArtworkFrameFromMyMedia();
		artwork.deleteArtworkFiles();
	}

	@DataProvider
	public Object[][] getData(Method m) throws EncryptedDocumentException, IOException{
		String sheetName = m.getName();
		int rows = excelUtility.getRowCount(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), sheetName);
		int columns = excelUtility.getColumnCount(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), sheetName);
		rows = rows+1;
		
		Object[][] data = new Object[rows-1][1];
		
		Hashtable<String, String> table = null;
		
		for(int rowNum = 2; rowNum <= rows; rowNum++) {
			table = new Hashtable<String, String>();
			for(int colNum = 0; colNum < columns; colNum++) {
				table.put(excelUtility.getCellData(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), sheetName, 1, colNum), excelUtility.getCellData(PropertiesUtility.getPropertyValueByKey("CQA_70_excelPath"), sheetName, rowNum, colNum));
				data[rowNum-2][0] = table;
			}
		}
		return data;
	}
}