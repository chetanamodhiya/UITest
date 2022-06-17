/*
 * Created By : Chetana
 */
package com.magento.artworkTestScripts;

import java.util.Map;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.magento.pages.ArtworkPage;
import com.magento.pages.BasePage;
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
 * Default Behavior of "Rotate template"
 * in the Artwork page is validated 
 * for few products like AllInclusive Postcards, Offset Envelopes, EndurACE Menus, Natural Menus, 
 * Regular HangTags, PremiumVinyl Banners, CircleBusinessCards, MeshBanners, Round2Corner BusinessCards, Foldover BusinessCards and BrownKraft Menus
 * Reference Jira ID : M2-2291
 */
public class ValidateRotateTemplateDefaultBehavior_CQA_71_M2_2291 extends TestBase{
	ExcelUtility excelUtility = new ExcelUtility();
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
	BasePage bp=new BasePage();
	ArtworkPage artwork=new ArtworkPage();
	ShoppingCartPage cart=new ShoppingCartPage();
	ReviewAndPaymentsPage review=new ReviewAndPaymentsPage();
	SoftAssert sa=new SoftAssert();
	@Test
	public void allInclusivePostcards() throws Throwable{
		
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("All Inclusive Postcards Product"));
		
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("All Inclusive Postcards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("All Inclusive Postcards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("All Inclusive Postcards ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over Link");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 5 x 7"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 14PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING Matte"));
		pdp.selectSpotUVSides(pdpOptionsAsMap.get("SPOT UV SIDES No Spot UV"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 500"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("All Inclusive Postcards Product"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.validateReviewArtworkOnTemplateButton(sa);
		artwork.rotateTemplateIconIsEnabled();
	}	
	
	@Test
	public void offsetEnvelopes() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Offset Envelopes Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Offset Envelopes ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Offset Envelopes ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Offset Envelopes ProductURL").replace("envi", run.get("BetaKeyword")));
			
		pdp.clickStartOverLink("Start Over Link");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 4.125 x 9.5"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 60LB Premium Opaque"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING"));
		pdp.selectWindowOptions(pdpOptionsAsMap.get("WINDOW OPTIONS"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 500"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Offset Envelopes Product"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.validateReviewArtworkOnTemplateButton(sa);
		artwork.rotateTemplateIconIsDisabled();
	}	
	
	
	
	@Test
	public void endurACEMenus() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("EndurACE Menus Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("EndurACE Menus ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("EndurACE Menus ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("EndurACE Menus ProductURL").replace("envi", run.get("BetaKeyword")));
		
		pdp.clickStartOverLink("Start Over Link");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 11 x 17"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 10PT EnduraACE"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING"));
		pdp.selectScoreAndFold(pdpOptionsAsMap.get("SCORE AND FOLD No Scoring and Folding"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 100"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("EndurACE Menus Product"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.validateReviewArtworkOnTemplateButton(sa);
		artwork.rotateTemplateIconIsEnabled();
	}	
	
	@Test
	public void naturalMenus() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Natural Menus Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Natural Menus ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Natural Menus ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Natural Menus ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over Link");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 8.5 x 11"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 14PT Natural"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING"));
		pdp.selectScoringOptions(pdpOptionsAsMap.get("SCORING OPTIONS Two Scores"));
		pdp.selectDrillHole(pdpOptionsAsMap.get("DRILL HOLE No Hole Punch"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 100"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Natural Menus Product"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.validateReviewArtworkOnTemplateButton(sa);
		artwork.rotateTemplateIconIsEnabled();
	}	
	
	@Test
	public void regularHangTags() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Regular Hang Tags Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Regular Hang Tags ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Regular Hang Tags ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Regular Hang Tags ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over Link");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 2 x 3.5"));
		pdp.clickShapeBox(pdpOptionsAsMap.get("SHAPE Rectangle"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 14PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING Aqueous Coating"));
		pdp.selectDrillHole(pdpOptionsAsMap.get("DRILL HOLE Standard Drill Hole 1/8"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 1000"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Regular Hang Tags Product"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.validateReviewArtworkOnTemplateButton(sa);
		artwork.rotateTemplateIconIsEnabled(pdpOptionsAsMap.get("SHAPE Rectangle"));
	}	
	
	@Test
	public void premiumVinylBanners() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Premium Vinyl Banners Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Premium Vinyl Banners ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Premium Vinyl Banners ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Premium Vinyl Banners ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over Link");
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 10mil Polypropylene - Indoor"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH"));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT"));
		pdp.selectGrommets(pdpOptionsAsMap.get("GROMMETS No Grommets"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Premium Vinyl Banners Product"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.validateReviewArtworkOnTemplateButton(sa);
		artwork.rotateTemplateIconIsDisabled();
	}	
	
	
@Test
public void circleBusinessCards() throws Throwable{
	Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
	Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
	Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

	ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Circle Business Cards Product"));
	if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
		navigateTo_custom(pdpOptionsAsMap.get("Circle Business Cards ProductURL").replace("envi", run.get("DevKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
		navigateTo_custom(pdpOptionsAsMap.get("Circle Business Cards ProductURL").replace("envi", run.get("StagingKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
		navigateTo_custom(pdpOptionsAsMap.get("Circle Business Cards ProductURL").replace("envi", run.get("BetaKeyword")));
	pdp.clickStartOverLink("Start Over Link");
	pdp.selectSize(pdpOptionsAsMap.get("SIZE 2 x 2"));
	pdp.clickShapeBox(pdpOptionsAsMap.get("SHAPE Round"));
	pdp.selectStock(pdpOptionsAsMap.get("STOCK 14PT C2S"));
	pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
	pdp.selectCoating(pdpOptionsAsMap.get("COATING Aqueous Coating"));
	pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 1000"));
	pdp.selectSecondOptionFromTurnAroundTimeDropdown();
	pdp.clickProceedToShippingButton();
	set.enterProjectOrClientName(pdpOptionsAsMap.get("Circle Business Cards Product"));
	set.selectSecondOptionFromShippingMethod();
	set.clickUploadYourArtButton();
	artwork.switchToArtworkFrameBeforePlaceOrder();
	artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
	artwork.validateReviewArtworkOnTemplateButton(sa);
	artwork.rotateTemplateIconIsDisabled();
}
	
	@Test
	public void meshBanners() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Mesh Banners Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Mesh Banners ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Mesh Banners ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Mesh Banners ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 8oz Mesh Banner"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH"));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT"));
		waitFor(1000);
		pdp.selectFinishOptions(pdpOptionsAsMap.get("FINISH OPTION Pole Pockets"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.selectHems(pdpOptionsAsMap.get("HEMS No Hems"));
		pdp.selectGrommets(pdpOptionsAsMap.get("GROMMETS No Grommets"));
		pdp.selectPolePockets(pdpOptionsAsMap.get("POLE POCKETS None"));
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Mesh Banners Product"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.validateReviewArtworkOnTemplateButton(sa);
		artwork.rotateTemplateIconIsDisabled();
	}
	
	@Test
	public void round2CornerBusinessCards() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Round 2 Corner Business Cards Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Round 2 Corner Business Cards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Round 2 Corner Business Cards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Round 2 Corner Business Cards ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 2 x 3.5"));
		pdp.clickShapeBox(pdpOptionsAsMap.get("SHAPE Rounded 2 Corners"));
		pdp.clickRadiusOfCorners(pdpOptionsAsMap.get("RADIUS OF CORNERS Rounded 1/4"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 16PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING"));
		pdp.selectLamination(pdpOptionsAsMap.get("LAMINATION Silk"));
		pdp.selectFoilColor(pdpOptionsAsMap.get("FOIL COLOR Copper Foil"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 100"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Round 2 Corner Business Cards Product"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.validateReviewArtworkOnTemplateButton(sa);
		artwork.rotateTemplateIconIsEnabled(pdpOptionsAsMap.get("SHAPE Rounded 2 Corners"));
	}
	
	@Test
	public void foldoverBusinessCards() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Fold-over Business Cards Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Fold-over Business Cards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Fold-over Business Cards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Fold-over Business Cards ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 3.5 x 4"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 16PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING Aqueous Coating"));
		pdp.selectSpotUVSides(pdpOptionsAsMap.get("SPOT UV SIDES"));
		pdp.selectScoringOptions(pdpOptionsAsMap.get("SCORING OPTIONS Score in Half"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 500"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Fold-over Business Cards Product"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.validateReviewArtworkOnTemplateButton(sa);
		artwork.rotateTemplateIconIsDisabled();
	}
	
	@Test
	public void brownKraftMenus() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		Map<String,String> fileUploading=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_71_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Brown Kraft Menus Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Brown Kraft Menus ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Brown Kraft Menus ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Brown Kraft Menus ProductURL").replace("envi", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 9x 12"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 18PT Uncoated Kraft"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 100"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Brown Kraft Menus Product"));
		set.selectSecondOptionFromShippingMethod();
		set.clickUploadYourArtButton();
		artwork.switchToArtworkFrameBeforePlaceOrder();
		artwork.dragAndDropFiles(System.getProperty("user.dir")+fileUploading.get("Artwork File"),fileUploading.get("Artwork File"));
		artwork.validateReviewArtworkOnTemplateButton(sa);
		artwork.rotateTemplateIconIsDisabled();
	}
}