/*
 * Created By : Chetana
 */
package com.magento.shoppingCartPageTestScripts;

import java.util.Map;
import org.testng.annotations.Test;
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
 * Product Add to Cart to Shopping Page
 * for few products like StandardBusinessCards, 16PT Postcards, FlatFlyersandBrochures,
 * EDDMFullService Postcards, Direct Mail Postcards and ScrimVinyl Banners
 * Reference Jira ID : CQA-196
 */
public class ValidateAddToCartTakesDirectlyToShoppingCart extends TestBase{
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
	
	@Test
	public void standardBusinessCards() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Business Cards Product"));
		if(run.get("Env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(run.get("Env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Standard Business Cards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(run.get("Env").equalsIgnoreCase("Printing"))
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
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Ground Shipping"));
		set.clickAddToCartButton();
		cart.viewShppingCartLabel();
	}
	
	@Test
	public void pT16Postcards() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
	
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Postcards Product"));
		if(run.get("Env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("16PT Postcards ProductURL").replace("envi", run.get("DevKeyword")));
		else if(run.get("Env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("16PT Postcards ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(run.get("Env").equalsIgnoreCase("Printing"))
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
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Ground Shipping"));
		set.clickAddToCartButton();
		cart.viewShppingCartLabel();
	}
	
	@Test
	public void flatFlyersandBrochures() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Flyers Product"));
		if(run.get("Env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Flat Flyers and Brochures ProductURL").replace("envi", run.get("DevKeyword")));
		else if(run.get("Env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Flat Flyers and Brochures ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(run.get("Env").equalsIgnoreCase("Printing"))
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
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Ground Shipping"));
		set.clickAddToCartButton();
		cart.viewShppingCartLabel();
	}
	
	@Test
	public void scrimVinylBanners() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));

		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Scrim Vinyl Banners Product"));
		if(run.get("Env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("envi", run.get("DevKeyword")));
		else if(run.get("Env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Scrim Vinyl Banners ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(run.get("Env").equalsIgnoreCase("Printing"))
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
		set.selectShippingMethod(setsAndShippingOptionsAsMap.get("SHIPPING METHOD Next Day"));
		set.clickAddToCartButton();
		cart.viewShppingCartLabel();
	}
}