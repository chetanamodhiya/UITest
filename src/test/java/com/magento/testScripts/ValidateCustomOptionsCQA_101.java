/*
 * Created By : Chetana
 */
package com.magento.testScripts;

import java.util.Map;
import org.testng.Reporter;
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

//Scripts are to be executed using Admiral Account only

public class ValidateCustomOptionsCQA_101 extends TestBase{
	ExcelUtility excelUtility = new ExcelUtility();
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
	BasePage bp=new BasePage();
	ArtworkPage artwork=new ArtworkPage();
	ShoppingCartPage cart=new ShoppingCartPage();
	ReviewAndPaymentsPage review=new ReviewAndPaymentsPage();
	
	@Test
	public void scrimVinylBannersPolePockets() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		SoftAssert sa=new SoftAssert();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Scrim Vinyl Banners Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("env", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("env", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("env", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		sa.assertTrue(pdp.getStockOptions().contains(pdpOptionsAsMap.get("STOCK 13oz Scrim Vinyl - Outdoor")));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 13oz Scrim Vinyl - Outdoor"));
		sa.assertTrue(pdp.getColorspecOptions().contains(pdpOptionsAsMap.get("COLORSPEC 4/0")));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		sa.assertTrue(pdp.getWidthOptions().contains(pdpOptionsAsMap.get("WIDTH 12")));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH 12"));
		sa.assertTrue(pdp.getHeightOptions().contains(pdpOptionsAsMap.get("HEIGHT 12")));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT 12"));
		pdp.validateQuantityOptions(pdpOptionsAsMap.get("QUANTITY 1"),sa);
		sa.assertTrue(pdp.getFinishOptionsOptions().contains(pdpOptionsAsMap.get("FINISH OPTIONS Pole Pockets")));
		pdp.selectFinishOptions(pdpOptionsAsMap.get("FINISH OPTIONS Pole Pockets"));
		sa.assertTrue(pdp.getTurnAroundTimeOptions().contains(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day")));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day"));
		sa.assertTrue(pdp.getPolePocketsOptions().contains(pdpOptionsAsMap.get("POLE POCKETS 2 in. Left and Right +$0.0989")));
		pdp.selectPolePockets(pdpOptionsAsMap.get("POLE POCKETS 2 in. Left and Right +$0.0989"));
		pdp.validateSubTotalPriceWithGivenPrice(pdpOptionsAsMap.get("POLE POCKETS SUBTOTAL Price $1.31"),sa);
		sa.assertAll();
	}
//	@Test
	public void scrimVinylBannersPolePockets1() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		SoftAssert sa=new SoftAssert();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Scrim Vinyl Banners Product"));
		Reporter.log("Product : "+pdpOptionsAsMap.get("Scrim Vinyl Banners Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("env", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("env", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("env", run.get("BetaKeyword")));

		pdp.clickStartOverLink("Start Over");
		sa.assertTrue(pdp.getStockOptions().contains(pdpOptionsAsMap.get("STOCK 13oz Scrim Vinyl - Outdoor")));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 13oz Scrim Vinyl - Outdoor"));
		sa.assertTrue(pdp.getColorspecOptions().contains(pdpOptionsAsMap.get("COLORSPEC 4/1")));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/1"));
		sa.assertTrue(pdp.getWidthOptions().contains(pdpOptionsAsMap.get("WIDTH 12")));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH 12"));
		sa.assertTrue(pdp.getHeightOptions().contains(pdpOptionsAsMap.get("HEIGHT 12")));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT 12"));
		pdp.validateQuantityOptions(pdpOptionsAsMap.get("QUANTITY 1"),sa);
		
		sa.assertTrue(pdp.getFinishOptionsOptions().contains(pdpOptionsAsMap.get("FINISH OPTIONS Pole Pockets")));
		pdp.selectFinishOptions(pdpOptionsAsMap.get("FINISH OPTIONS Pole Pockets"));
		sa.assertTrue(pdp.getTurnAroundTimeOptions().contains(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day")));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day"));
		sa.assertTrue(pdp.getPolePocketsOptions().contains(pdpOptionsAsMap.get("POLE POCKETS 2 in. Left and Right +$0.0989")));
		pdp.selectPolePockets(pdpOptionsAsMap.get("POLE POCKETS 2 in. Left and Right +$0.0989"));
		pdp.validateSubTotalPriceWithGivenPrice(pdpOptionsAsMap.get("POLE POCKETS SUBTOTAL Price $2.85"),sa);
		sa.assertAll();
	}
	
	@Test
	public void scrimVinylBannersGrommetsAndHems() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		SoftAssert sa=new SoftAssert();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Scrim Vinyl Banners Product"));
		Reporter.log("Product : "+pdpOptionsAsMap.get("Scrim Vinyl Banners Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("env", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("env", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("env", run.get("BetaKeyword")));

		pdp.clickStartOverLink("Start Over");
		sa.assertTrue(pdp.getStockOptions().contains(pdpOptionsAsMap.get("STOCK 13oz Scrim Vinyl - Outdoor")));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 13oz Scrim Vinyl - Outdoor"));
		sa.assertTrue(pdp.getColorspecOptions().contains(pdpOptionsAsMap.get("COLORSPEC 4/0")));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		sa.assertTrue(pdp.getWidthOptions().contains(pdpOptionsAsMap.get("WIDTH 12")));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH 12"));
		sa.assertTrue(pdp.getHeightOptions().contains(pdpOptionsAsMap.get("HEIGHT 12")));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT 12"));
		pdp.validateQuantityOptions(pdpOptionsAsMap.get("QUANTITY 1"),sa);
		
		sa.assertTrue(pdp.getFinishOptionsOptions().contains(pdpOptionsAsMap.get("FINISH OPTIONS Grommets and Hems")));
		pdp.selectFinishOptions(pdpOptionsAsMap.get("FINISH OPTIONS Grommets and Hems"));
		sa.assertTrue(pdp.getTurnAroundTimeOptions().contains(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day")));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day"));
		sa.assertTrue(pdp.getHemsOptions().contains(pdpOptionsAsMap.get("HEMS All 4 Sides +$0.0989")));
		pdp.selectHems(pdpOptionsAsMap.get("HEMS All 4 Sides +$0.0989"));
		sa.assertTrue(pdp.getGrommetsOptions().contains(pdpOptionsAsMap.get("GROMMETS Every 1 ft. +$0.25")));
		pdp.selectGrommets(pdpOptionsAsMap.get("GROMMETS Every 1 ft. +$0.25"));
		pdp.validateSubTotalPriceWithGivenPrice(pdpOptionsAsMap.get("GROMMETS AND HEMS SUBTOTAL Price $1.56"),sa);
		sa.assertAll();
	}
	
	@Test
	public void premiumPolyesterBannersPolePockets() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		SoftAssert sa=new SoftAssert();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Premium Polyester Banners Product"));
		Reporter.log("Product : "+pdpOptionsAsMap.get("Premium Polyester Banners Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Premium Polyester Banners ProductURL").replace("env", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Premium Polyester Banners ProductURL").replace("env", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Premium Polyester Banners ProductURL").replace("env", run.get("BetaKeyword")));

		pdp.clickStartOverLink("Start Over");
		sa.assertTrue(pdp.getStockOptions().contains(pdpOptionsAsMap.get("STOCK 9oz Premium Polyester")));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 9oz Premium Polyester"));
		sa.assertTrue(pdp.getColorspecOptions().contains(pdpOptionsAsMap.get("COLORSPEC 4/0")));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		sa.assertTrue(pdp.getWidthOptions().contains(pdpOptionsAsMap.get("WIDTH 12")));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH 12"));
		sa.assertTrue(pdp.getHeightOptions().contains(pdpOptionsAsMap.get("HEIGHT 12")));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT 12"));
		pdp.validateQuantityOptions(pdpOptionsAsMap.get("QUANTITY 1"),sa);
		
		sa.assertTrue(pdp.getFinishOptionsOptions().contains(pdpOptionsAsMap.get("FINISH OPTIONS Pole Pockets")));
		pdp.selectFinishOptions(pdpOptionsAsMap.get("FINISH OPTIONS Pole Pockets"));
		sa.assertTrue(pdp.getTurnAroundTimeOptions().contains(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day")));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day"));
		sa.assertTrue(pdp.getThreadColorOptions().contains(pdpOptionsAsMap.get("THREAD COLOR None")));
		pdp.selectThreadColor(pdpOptionsAsMap.get("THREAD COLOR None"));
		sa.assertTrue(pdp.getPolePocketsOptions().contains(pdpOptionsAsMap.get("POLE POCKETS 2 in. Left and Right +$0.2601")));
		pdp.selectPolePockets(pdpOptionsAsMap.get("POLE POCKETS 2 in. Left and Right +$0.2601"));
		pdp.validateSubTotalPriceWithGivenPrice(pdpOptionsAsMap.get("POLE POCKETS SUBTOTAL Price $3.28"),sa);
		sa.assertAll();
	}
	
	@Test
	public void premiumPolyesterBannersGrommetsAndHems() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		SoftAssert sa=new SoftAssert();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Premium Polyester Banners Product"));
		Reporter.log("Product : "+pdpOptionsAsMap.get("Premium Polyester Banners Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Premium Polyester Banners ProductURL").replace("env", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Premium Polyester Banners ProductURL").replace("env", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Premium Polyester Banners ProductURL").replace("env", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		sa.assertTrue(pdp.getStockOptions().contains(pdpOptionsAsMap.get("STOCK 9oz Premium Polyester")));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 9oz Premium Polyester"));
		sa.assertTrue(pdp.getColorspecOptions().contains(pdpOptionsAsMap.get("COLORSPEC 4/0")));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		sa.assertTrue(pdp.getWidthOptions().contains(pdpOptionsAsMap.get("WIDTH 12")));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH 12"));
		sa.assertTrue(pdp.getHeightOptions().contains(pdpOptionsAsMap.get("HEIGHT 12")));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT 12"));
		pdp.validateQuantityOptions(pdpOptionsAsMap.get("QUANTITY 1"),sa);
		sa.assertTrue(pdp.getFinishOptionsOptions().contains(pdpOptionsAsMap.get("FINISH OPTIONS Grommets and Hems")));
		pdp.selectFinishOptions(pdpOptionsAsMap.get("FINISH OPTIONS Grommets and Hems"));
		sa.assertTrue(pdp.getTurnAroundTimeOptions().contains(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day")));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day"));
		sa.assertTrue(pdp.getHemsOptions().contains(pdpOptionsAsMap.get("HEMS All 4 Sides +$0.2601")));
		pdp.selectHems(pdpOptionsAsMap.get("HEMS All 4 Sides +$0.2601"));
		sa.assertTrue(pdp.getGrommetsOptions().contains(pdpOptionsAsMap.get("GROMMETS Every 2 ft.")));
		pdp.selectGrommets(pdpOptionsAsMap.get("GROMMETS Every 2 ft."));
		sa.assertTrue(pdp.getThreadColorOptions().contains(pdpOptionsAsMap.get("THREAD COLOR Black Thread")));
		pdp.selectThreadColor(pdpOptionsAsMap.get("THREAD COLOR Black Thread"));
		pdp.validateSubTotalPriceWithGivenPrice(pdpOptionsAsMap.get("GROMMETS AND HEMS SUBTOTAL Price $3.28"),sa);
		sa.assertAll();
	}
	
	@Test
	public void artistCanvasPolePockets() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		SoftAssert sa=new SoftAssert();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Artist Canvas Product"));
		Reporter.log("Product : "+pdpOptionsAsMap.get("Artist Canvas Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Artist Canvas ProductURL").replace("env", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Artist Canvas ProductURL").replace("env", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Artist Canvas ProductURL").replace("env", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		sa.assertTrue(pdp.getStockOptions().contains(pdpOptionsAsMap.get("STOCK 17mil Artist Canvas")));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 17mil Artist Canvas"));
		sa.assertTrue(pdp.getColorspecOptions().contains(pdpOptionsAsMap.get("COLORSPEC 4/0")));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		sa.assertTrue(pdp.getWidthOptions().contains(pdpOptionsAsMap.get("WIDTH 12")));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH 12"));
		sa.assertTrue(pdp.getHeightOptions().contains(pdpOptionsAsMap.get("HEIGHT 12")));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT 12"));
		pdp.validateQuantityOptions(pdpOptionsAsMap.get("QUANTITY 1"),sa);
		sa.assertTrue(pdp.getFinishOptionsOptions().contains(pdpOptionsAsMap.get("FINISH OPTIONS Pole Pockets")));
		pdp.selectFinishOptions(pdpOptionsAsMap.get("FINISH OPTIONS Pole Pockets"));
		sa.assertTrue(pdp.getTurnAroundTimeOptions().contains(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day")));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day"));
		sa.assertTrue(pdp.getThreadColorOptions().contains(pdpOptionsAsMap.get("THREAD COLOR Black Thread")));
		pdp.selectThreadColor(pdpOptionsAsMap.get("THREAD COLOR Black Thread"));
		sa.assertTrue(pdp.getPolePocketsOptions().contains(pdpOptionsAsMap.get("POLE POCKETS 2 in. Left and Right +$0.31")));
		pdp.selectPolePockets(pdpOptionsAsMap.get("POLE POCKETS 2 in. Left and Right +$0.31"));
		pdp.validateSubTotalPriceWithGivenPrice(pdpOptionsAsMap.get("POLE POCKETS SUBTOTAL Price $3.97"),sa);
		sa.assertAll();
	}
	
	@Test
	public void artistCanvasGrommetsAndHems() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		SoftAssert sa=new SoftAssert();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Artist Canvas Product"));
		Reporter.log("Product : "+pdpOptionsAsMap.get("Artist Canvas Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Artist Canvas ProductURL").replace("env", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Artist Canvas ProductURL").replace("env", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Artist Canvas ProductURL").replace("env", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		sa.assertTrue(pdp.getStockOptions().contains(pdpOptionsAsMap.get("STOCK 17mil Artist Canvas")));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 17mil Artist Canvas"));
		sa.assertTrue(pdp.getColorspecOptions().contains(pdpOptionsAsMap.get("COLORSPEC 4/0")));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		sa.assertTrue(pdp.getWidthOptions().contains(pdpOptionsAsMap.get("WIDTH 12")));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH 12"));
		sa.assertTrue(pdp.getHeightOptions().contains(pdpOptionsAsMap.get("HEIGHT 12")));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT 12"));
		pdp.validateQuantityOptions(pdpOptionsAsMap.get("QUANTITY 1"),sa);
		
		sa.assertTrue(pdp.getFinishOptionsOptions().contains(pdpOptionsAsMap.get("FINISH OPTIONS Grommets and Hems")));
		pdp.selectFinishOptions(pdpOptionsAsMap.get("FINISH OPTIONS Grommets and Hems"));
		sa.assertTrue(pdp.getTurnAroundTimeOptions().contains(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day")));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day"));
		sa.assertTrue(pdp.getHemsOptions().contains(pdpOptionsAsMap.get("HEMS All 4 Sides +$0.31")));
		pdp.selectHems(pdpOptionsAsMap.get("HEMS All 4 Sides +$0.31"));
		sa.assertTrue(pdp.getGrommetsOptions().contains(pdpOptionsAsMap.get("GROMMETS Corners Only")));
		pdp.selectGrommets(pdpOptionsAsMap.get("GROMMETS Corners Only"));
		sa.assertTrue(pdp.getThreadColorOptions().contains(pdpOptionsAsMap.get("THREAD COLOR White Thread")));
		pdp.selectThreadColor(pdpOptionsAsMap.get("THREAD COLOR White Thread"));
		pdp.validateSubTotalPriceWithGivenPrice(pdpOptionsAsMap.get("GROMMETS AND HEMS SUBTOTAL Price $3.97"),sa);
		sa.assertAll();
	}
	
	@Test
	public void blockoutVinylSingleSidedBannerPolePockets() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		SoftAssert sa=new SoftAssert();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Blockout Vinyl Single Sided Banner Product"));
		Reporter.log("Product : "+pdpOptionsAsMap.get("Blockout Vinyl Single Sided Banner Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Blockout Vinyl Single Sided Banner ProductURL").replace("env", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Blockout Vinyl Single Sided Banner ProductURL").replace("env", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Blockout Vinyl Single Sided Banner ProductURL").replace("env", run.get("BetaKeyword")));
		
		pdp.clickStartOverLink("Start Over");
		sa.assertTrue(pdp.getStockOptions().contains(pdpOptionsAsMap.get("STOCK 15oz Blockout Vinyl - Indoor")));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 15oz Blockout Vinyl - Indoor"));
		sa.assertTrue(pdp.getColorspecOptions().contains(pdpOptionsAsMap.get("COLORSPEC 4/0")));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		sa.assertTrue(pdp.getWidthOptions().contains(pdpOptionsAsMap.get("WIDTH 12")));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH 12"));
		sa.assertTrue(pdp.getHeightOptions().contains(pdpOptionsAsMap.get("HEIGHT 12")));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT 12"));
		pdp.validateQuantityOptions(pdpOptionsAsMap.get("QUANTITY 1"),sa);
		
		sa.assertTrue(pdp.getFinishOptionsOptions().contains(pdpOptionsAsMap.get("FINISH OPTIONS Pole Pockets")));
		pdp.selectFinishOptions(pdpOptionsAsMap.get("FINISH OPTIONS Pole Pockets"));
		sa.assertTrue(pdp.getTurnAroundTimeOptions().contains(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day")));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day"));
		sa.assertTrue(pdp.getPolePocketsOptions().contains(pdpOptionsAsMap.get("POLE POCKETS 2 in. Left and Right +$0.13")));
		pdp.selectPolePockets(pdpOptionsAsMap.get("POLE POCKETS 2 in. Left and Right +$0.13"));
		pdp.validateSubTotalPriceWithGivenPrice(pdpOptionsAsMap.get("POLE POCKETS SUBTOTAL Price $1.84"),sa);
		sa.assertAll();
	}
	
	@Test
	public void blockoutVinylSingleSidedBannerGrommetsAndHems() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		SoftAssert sa=new SoftAssert();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Blockout Vinyl Single Sided Banner Product"));
		Reporter.log("Product : "+pdpOptionsAsMap.get("Blockout Vinyl Single Sided Banner Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Blockout Vinyl Single Sided Banner ProductURL").replace("env", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Blockout Vinyl Single Sided Banner ProductURL").replace("env", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Blockout Vinyl Single Sided Banner ProductURL").replace("env", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		sa.assertTrue(pdp.getStockOptions().contains(pdpOptionsAsMap.get("STOCK 15oz Blockout Vinyl - Indoor")));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 15oz Blockout Vinyl - Indoor"));
		sa.assertTrue(pdp.getColorspecOptions().contains(pdpOptionsAsMap.get("COLORSPEC 4/0")));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		sa.assertTrue(pdp.getWidthOptions().contains(pdpOptionsAsMap.get("WIDTH 12")));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH 12"));
		sa.assertTrue(pdp.getHeightOptions().contains(pdpOptionsAsMap.get("HEIGHT 12")));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT 12"));
		pdp.validateQuantityOptions(pdpOptionsAsMap.get("QUANTITY 1"),sa);
		
		sa.assertTrue(pdp.getFinishOptionsOptions().contains(pdpOptionsAsMap.get("FINISH OPTIONS Grommets and Hems")));
		pdp.selectFinishOptions(pdpOptionsAsMap.get("FINISH OPTIONS Grommets and Hems"));
		sa.assertTrue(pdp.getTurnAroundTimeOptions().contains(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day")));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day"));
		sa.assertTrue(pdp.getHemsOptions().contains(pdpOptionsAsMap.get("HEMS All 4 Sides +$0.13")));
		pdp.selectHems(pdpOptionsAsMap.get("HEMS All 4 Sides +$0.13"));
		sa.assertTrue(pdp.getGrommetsOptions().contains(pdpOptionsAsMap.get("GROMMETS Corners Only")));
		pdp.selectGrommets(pdpOptionsAsMap.get("GROMMETS Corners Only"));
		pdp.validateSubTotalPriceWithGivenPrice(pdpOptionsAsMap.get("GROMMETS AND HEMS SUBTOTAL Price $1.84"),sa);
		sa.assertAll();
	}
	
	@Test
	public void aluminumDyeSubContourCut() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		SoftAssert sa=new SoftAssert();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Aluminum Dye Sub Product"));
		Reporter.log("Product : "+pdpOptionsAsMap.get("Aluminum Dye Sub Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Aluminum Dye Sub ProductURL").replace("env", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Aluminum Dye Sub ProductURL").replace("env", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Aluminum Dye Sub ProductURL").replace("env", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		sa.assertTrue(pdp.getStockOptions().contains(pdpOptionsAsMap.get("STOCK 0.045 Aluminum - Gloss")));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 0.045 Aluminum - Gloss"));
		sa.assertTrue(pdp.getColorspecOptions().contains(pdpOptionsAsMap.get("COLORSPEC 4/0")));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		sa.assertTrue(pdp.getWidthOptions().contains(pdpOptionsAsMap.get("WIDTH 6")));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH 6"));
		sa.assertTrue(pdp.getHeightOptions().contains(pdpOptionsAsMap.get("HEIGHT 6")));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT 6"));
		pdp.validateQuantityOptions(pdpOptionsAsMap.get("QUANTITY 1"),sa);
		
		sa.assertTrue(pdp.getFinishOptionsOptions().contains(pdpOptionsAsMap.get("FINISH OPTIONS Contour Cut")));
		pdp.selectFinishOptions(pdpOptionsAsMap.get("FINISH OPTIONS Contour Cut"));
		sa.assertTrue(pdp.getTurnAroundTimeOptions().contains(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day")));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day"));
		sa.assertTrue(pdp.getContourCutOptions().contains(pdpOptionsAsMap.get("CONTOUR CUT Yes")));
		pdp.selectContourCut(pdpOptionsAsMap.get("CONTOUR CUT Yes"));
		pdp.validateSubTotalPriceWithGivenPrice(pdpOptionsAsMap.get("COUNTOUR CUT SUBTOTAL Price"), sa);
		sa.assertAll();
	}
	
	@Test
	public void aluminumDyeSubDrillholeandHardware() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		SoftAssert sa=new SoftAssert();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Aluminum Dye Sub Product"));
		Reporter.log("Product : "+pdpOptionsAsMap.get("Aluminum Dye Sub Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Aluminum Dye Sub ProductURL").replace("env", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Aluminum Dye Sub ProductURL").replace("env", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Aluminum Dye Sub ProductURL").replace("env", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		sa.assertTrue(pdp.getStockOptions().contains(pdpOptionsAsMap.get("STOCK 0.045 Aluminum - Gloss")));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 0.045 Aluminum - Gloss"));
		sa.assertTrue(pdp.getColorspecOptions().contains(pdpOptionsAsMap.get("COLORSPEC 4/0")));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/0"));
		sa.assertTrue(pdp.getWidthOptions().contains(pdpOptionsAsMap.get("WIDTH 6")));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH 6"));
		sa.assertTrue(pdp.getHeightOptions().contains(pdpOptionsAsMap.get("HEIGHT 6")));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT 6"));
		pdp.validateQuantityOptions(pdpOptionsAsMap.get("QUANTITY 1"),sa);
		
		sa.assertTrue(pdp.getFinishOptionsOptions().contains(pdpOptionsAsMap.get("FINISH OPTIONS Drill Hole and Hardware")));
		pdp.selectFinishOptions(pdpOptionsAsMap.get("FINISH OPTIONS Drill Hole and Hardware"));
		sa.assertTrue(pdp.getTurnAroundTimeOptions().contains(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day")));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day"));
		sa.assertTrue(pdp.getDrillholeandHardwareOptions().contains(pdpOptionsAsMap.get("DRILL HOLE AND HARDWARE")));
		pdp.selectDrillholeandHardware(pdpOptionsAsMap.get("DRILL HOLE AND HARDWARE"));
		sa.assertTrue(pdp.getRoundCornersOptions().contains(pdpOptionsAsMap.get("ROUND CORNERS")));
		pdp.selectRoundCorners(pdpOptionsAsMap.get("ROUND CORNERS"));
		pdp.validateSubTotalPriceWithGivenPrice(pdpOptionsAsMap.get("DRILL HOLE ANS HARDWARE SUBTOTAL Price"),sa);
		sa.assertAll();
	}
	
//	@Test
	public void aluminumDyeSubDrillholeandHardware1() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("CQA_101_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		SoftAssert sa=new SoftAssert();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Aluminum Dye Sub Product"));
		Reporter.log("Product : "+pdpOptionsAsMap.get("Aluminum Dye Sub Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Aluminum Dye Sub ProductURL").replace("env", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Aluminum Dye Sub ProductURL").replace("env", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Aluminum Dye Sub ProductURL").replace("env", run.get("BetaKeyword")));
		pdp.clickStartOverLink("Start Over");
		sa.assertTrue(pdp.getStockOptions().contains(pdpOptionsAsMap.get("STOCK 0.045 Aluminum - Gloss")));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 0.045 Aluminum - Gloss"));
		sa.assertTrue(pdp.getColorspecOptions().contains(pdpOptionsAsMap.get("COLORSPEC 4/1")));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/1"));
		sa.assertTrue(pdp.getWidthOptions().contains(pdpOptionsAsMap.get("WIDTH 6")));
		pdp.selectWidth(pdpOptionsAsMap.get("WIDTH 6"));
		sa.assertTrue(pdp.getHeightOptions().contains(pdpOptionsAsMap.get("HEIGHT 6")));
		pdp.selectHeight(pdpOptionsAsMap.get("HEIGHT 6"));
		pdp.validateQuantityOptions(pdpOptionsAsMap.get("QUANTITY 1"),sa);
		sa.assertTrue(pdp.getTurnAroundTimeOptions().contains(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day")));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME Next Business Day"));
		sa.assertTrue(pdp.getFinishOptionsOptions().contains(pdpOptionsAsMap.get("FINISH OPTIONS Drill Hole and Hardware")));
		pdp.selectFinishOptions(pdpOptionsAsMap.get("FINISH OPTIONS Drill Hole and Hardware"));
		sa.assertTrue(pdp.getDrillholeandHardwareOptions().contains(pdpOptionsAsMap.get("DRILL HOLE AND HARDWARE")));
		pdp.selectDrillholeandHardware(pdpOptionsAsMap.get("DRILL HOLE AND HARDWARE"));
		sa.assertTrue(pdp.getRoundCornersOptions().contains(pdpOptionsAsMap.get("ROUND CORNERS")));
		pdp.selectRoundCorners(pdpOptionsAsMap.get("ROUND CORNERS"));
		pdp.validateSubTotalPriceWithGivenPrice(pdpOptionsAsMap.get("DRILL HOLE ANS HARDWARE SUBTOTAL Price"),sa);
		sa.assertAll();
	}
	
}