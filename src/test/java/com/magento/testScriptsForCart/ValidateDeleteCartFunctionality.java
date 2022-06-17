package com.magento.testScriptsForCart;
import java.util.Map;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.magento.pages.BasePage;
import com.magento.pages.PDPProductOptionsPage;
import com.magento.pages.PDPSetsAndShippingPage;
import com.magento.pages.ShoppingCartPage;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class ValidateDeleteCartFunctionality extends TestBase{
	ExcelUtility excelUtility = new ExcelUtility();
	BasePage bp = new BasePage();
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
	ShoppingCartPage cart=new ShoppingCartPage();
	SoftAssert sa=new SoftAssert();
	
	@Test()
	public void validateDeleteCartFeatureFromRemoveProductLink() throws Throwable{
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
		set.validateProjectOrClientNameIsMandatoryField();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Scrim Vinyl Banners Product"));
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Next Day"));
		set.clickAddToCartButton();
		cart.verifySuccessMessage(sa);
		cart.clickRemoveProductLink();
		cart.verifyItemIsRemoved(sa);
		sa.assertAll();
}
	@Test()
	public void validateDeleteCartFeatureFromEmptyShoppingCartLink() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Postcards Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("16PT Postcards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("16PT Postcards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("16PT Postcards ProductURL").replace("envi", run.get("BetaKeyword")));
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
		set.clickAddToCartButton();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Business Cards Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("BetaKeyword")));
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
		set.clickAddToCartButton();
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
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Flyers Product"));
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Ground Shipping"));
		set.clickAddToCartButton();
		cart.clickEmptyShoppingCartButton();
		cart.verifyItemIsRemoved(sa);
		sa.assertAll();
	}
	
	@Test()
	public void validateDeleteCartFeatureFromMiniCart() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
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
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Flyers Product"));
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Ground Shipping"));
		set.clickAddToCartButton();
		bp.clearCart();
		cart.verifyItemIsRemoved(sa);
		sa.assertAll();
	}
}
