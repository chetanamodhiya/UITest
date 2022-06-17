package com.magento.shoppingCartPageTestScripts;

import java.util.Map;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.magento.pages.BasePage;
import com.magento.pages.PDPProductOptionsPage;
import com.magento.pages.PDPSetsAndShippingPage;
import com.magento.pages.ShoppingCartPage;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.MathUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class ValidateCouponFeatureCQA_249Updated extends TestBase{
	ExcelUtility excelUtility = new ExcelUtility();
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
	ShoppingCartPage cart=new ShoppingCartPage();
	BasePage bp=new BasePage();
	SoftAssert sa = new SoftAssert();

	@Test
	public void validateCouponCodeOnShoppingCartPage() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> setsAndShippingOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForSetsandShippingPage"));
		Map<String,String> run=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForRun"));
		Map<String,String> couponDetails=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForCart"));
		
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Flyers Product"));
		if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Dev"))
			navigateTo_custom(pdpOptionsAsMap.get("Flat Flyers and Brochures ProductURL").replace("envi", run.get("DevKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Stage"))
			navigateTo_custom(pdpOptionsAsMap.get("Flat Flyers and Brochures ProductURL").replace("envi", run.get("StagingKeyword")));
		else if(PropertiesUtility.getPropertyValueByKey("env").equalsIgnoreCase("Printing"))
			navigateTo_custom(pdpOptionsAsMap.get("Flat Flyers and Brochures ProductURL").replace("envi", run.get("BetaKeyword")));
		bp.clearCart();
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
		cart.validateTotal_JobTotalPriceBeforeApplyingCoupon(sa);
		cart.clickApplyPromoOrRewardsLabel();
		cart.applyCouponCode(couponDetails.get("CouponFor$1"));
		cart.clickApplyPromoCodeButton();
		cart.validateTotalPriceAfterApplyingCoupon(sa);
		cart.clickRemovePromoCodeButton();
		cart.validateTotal_JobTotalPriceBeforeApplyingCoupon(sa);
		cart.applyCouponCode(couponDetails.get("CouponFor1%"));
		cart.clickApplyPromoCodeButton();
		cart.validateTotalPriceAfterApplyingCoupon(sa);
		cart.clickRemovePromoCodeButton();
		cart.validateTotal_JobTotalPriceBeforeApplyingCoupon(sa);
		sa.assertAll();
	}
}