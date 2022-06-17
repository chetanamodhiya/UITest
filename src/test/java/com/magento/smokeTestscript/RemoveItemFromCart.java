package com.magento.smokeTestscript;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.magento.pages.BasePage;
import com.magento.pages.PDPProductOptionsPage;
import com.magento.pages.PDPSetsAndShippingPage;
import com.magento.pages.ShoppingCartPage;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class RemoveItemFromCart extends TestBase{
	ExcelUtility excelUtility = new ExcelUtility();
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
	ShoppingCartPage cart=new ShoppingCartPage();
	BasePage bp=new BasePage();

@Test
public void scrimVinylBanners() throws Throwable{
	Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
	Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
	Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

	ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Scrim Vinyl Banners Product"));
	if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
		navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("envi", run.get("DevKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
		navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("envi", run.get("StagingKeyword")));
	else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
		navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("envi", run.get("BetaKeyword")));
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
	set.clickAddToCartButton();
	cart.waitForTotalPriceToBeVisible();
	bp.clearCart();
	
}
}