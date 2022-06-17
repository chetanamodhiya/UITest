package com.magento.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.magento.reusableComponents.MathUtility;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class ShoppingCartPage extends TestBase{
	
	By shoppingCartLabel=By.xpath("//span[text()='Shopping Cart']");
	By editLink=By.xpath("//a[@class='action action-edit']");
	By removeProductLink=By.xpath("//a[@class='action action-delete']//span[contains(text(),'Remove product')]");
	By EnterpromoCode=By.id("discount-code");
	By applyButton=By.xpath("//button[@class='action apply primary']");
	By proceedToCheckOutButton=By.xpath("//button[@class='action primary checkout']");
	By shipmentHead=By.xpath("//div[@class='shipment-item_head']");
	By editArtworkLink=By.xpath("//a[@class='action edit artwork-upload']");
	By emptyShoppingCartButton=By.xpath("//button[@name='update_cart_action']//span[text()='Empty Shopping Cart']");
	By liveChatButton=By.xpath("//button[@class='action primary']");
	By rewardPoints=By.xpath("//div[@class='cart-item_rewards']/span");
	By costPrice=By.xpath("//div[contains(text(),'Price')]/..//span[@class='price']");
	By spinner=By.xpath("//img[@title='Loading...']");
	By subTotalPrice=By.xpath("//li[@class='subtotal']//span[@class='price']");
	By shippingPrice=By.xpath("//div[contains(text(),'Shipping')]/..//span[@class='price']");
	By taxPrice=By.xpath("//li[@class='tax']//span[@class='price']");
	By jobTotalPrice=By.xpath("//li[@class='job-total']//span[@class='price']");
	By totalPrice=By.xpath("//td[@data-th=\"Total\"]//span[@class='price']");
	By cancelCoupon=By.xpath("//button[@class='action cancel primary']//span[text()='Cancel Coupon']");
	By clickRemovePromoCodeButton=By.xpath("//button//span/span[text()='Remove promo code']");
	By discountPrice=By.xpath("//li[@class='discount']//span[@class='price']");
	By discountCoupon=By.xpath("//li[@class='discount']//div[contains(text(),'Discount')]");
	By successMessageAfterAddingProductIntoCart=By.xpath("//div[contains(text(),'successfully added to the shopping cart.')]");		
	By messageAfterRemoveProduct=By.xpath("//div[@class='cart-empty']//p[text()='You have no items in your shopping cart.']");
	By okForRemoveButton=By.xpath("//button[@class='action-primary action-accept']");
	By clickApplyPromoorRewards=By.xpath("//span[text()='Apply Promo or Rewards']");
	By clickApplyPromoCodeButton=By.xpath("//button//span[text()='Apply promo code']");
	
	//click Apply Promo or Rewards label
	public void clickApplyPromoOrRewardsLabel() {
		waitForElementToBeInVisible(custom_findElements(spinner));
		click_custom(custom_findElement(clickApplyPromoorRewards), "Apply Promo or Rewards");
	}
	
	//click Apply promo code button
	public void clickApplyPromoCodeButton() throws InterruptedException {
		click_custom(custom_findElement(clickApplyPromoCodeButton), "Apply promo code button");
	}
		
	//click Remove Promo Code button
		public void clickRemovePromoCodeButton() throws InterruptedException {
			waitFor(1000);
			click_custom(custom_findElement(clickRemovePromoCodeButton), "Remove Promo Code button");
		}
	
	
	//verify item is removed 
		public void verifyItemIsRemoved(SoftAssert sa) {
			if(isElementPresent_custom(custom_findElement(messageAfterRemoveProduct))) {
				sa.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, getText_custom(custom_findElement(messageAfterRemoveProduct))+" Message is displayed");
			}
			else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Item did not remove Successfully");
				sa.fail();
			}
		}
	
	
	//verify success message is displayed after added product
	public void verifySuccessMessage(SoftAssert sa) {
		if(isElementPresent_custom(custom_findElement(successMessageAfterAddingProductIntoCart))) {
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, getText_custom(custom_findElement(successMessageAfterAddingProductIntoCart))+" Message is displayed");
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Success Message is not displayed");
			sa.fail();
		}
	}
	
	//view subtotal price  
	public double viewSubTotalPrice() {
		String subTotalCurrency = getText_custom(custom_findElement(subTotalPrice));
		subTotalCurrency=MathUtility.regularExpressionRetainDot(subTotalCurrency).trim();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "SubTotal Price: "+subTotalCurrency);
		return MathUtility.parseStringToDouble(subTotalCurrency);
	}
	
	//view discount price
	public double viewDiscountPrice() {
		Double discount=0.0;
		if(custom_findElements(discountPrice).size()>0) {
			String discountAmount=getText_custom(custom_findElement(discountPrice));
			discountAmount=MathUtility.regularExpressionRetainDot(discountAmount);
			discount= MathUtility.parseStringToDouble(discountAmount);
			discount= MathUtility.removeNegativeSign(discount);
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Discount Price: "+discountAmount);
			return discount;
		}
		else
			return discount;
	}
		
	//view shipping price  
	public double viewShippingPrice() {
		double shippingAmount=0.0;
		if(custom_findElements(shippingPrice).size()>0) {
			String shippingCurrency = getText_custom(custom_findElement(shippingPrice));
			shippingCurrency=MathUtility.regularExpressionRetainDot(shippingCurrency).trim();
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Shipping Price: "+shippingCurrency);
			return MathUtility.parseStringToDouble(shippingCurrency);
		}
		return shippingAmount;
	}
		
	//view tax price  
	public double viewTaxPrice() {
		String taxCurrency = getText_custom(custom_findElement(taxPrice));
		taxCurrency=MathUtility.regularExpressionRetainDot(taxCurrency).trim();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Tax Price: "+taxCurrency);
		return MathUtility.parseStringToDouble(taxCurrency);
	}
	
	
	//view tax price as per discount
	public void validateTaxPriceAsPerCoupon(double taxWithoutDiscount, double discount) {
		String taxCurrency = getText_custom(custom_findElement(taxPrice));
		taxCurrency=MathUtility.regularExpressionRetainDot(taxCurrency).trim();
		double actualTax = MathUtility.parseStringToDouble(taxCurrency);
		double expectedTax=MathUtility.valueInDecimalFormat(taxWithoutDiscount-(discount/10));
		if(actualTax==expectedTax) {
			Assert.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Tax Price matches as per discount : "+actualTax);
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Tax Price did not match as per discount : "+actualTax);
			Assert.fail();
		}
	}
	
	//view job total price  
	public double viewAndValidateJobTotalPrice(SoftAssert sa) {
		String jobTotalCurrency = getText_custom(custom_findElement(jobTotalPrice));
		jobTotalCurrency=MathUtility.regularExpressionRetainDot(jobTotalCurrency).trim();
		double actualPrice=MathUtility.parseStringToDouble(jobTotalCurrency);
		double expectedPrice=viewSubTotalPrice()+viewShippingPrice()+viewTaxPrice();
		sa.assertEquals(actualPrice, expectedPrice);
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Job Total Price: "+jobTotalCurrency);
		return actualPrice;
	}
	
	//view job total price with coupon code 
		public double viewAndValidateJobTotalPriceWithCouponCode(SoftAssert sa) {
			String jobTotalCurrency = getText_custom(custom_findElement(jobTotalPrice));
			jobTotalCurrency=MathUtility.regularExpressionRetainDot(jobTotalCurrency).trim();
			double actualPrice=MathUtility.parseStringToDouble(jobTotalCurrency);
			double expectedPrice=viewSubTotalPrice()+viewShippingPrice()+viewTaxPrice()-viewDiscountPrice();
			expectedPrice=MathUtility.valueInDecimalFormat(expectedPrice);
			sa.assertEquals(actualPrice, expectedPrice);
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Job Total Price: "+jobTotalCurrency);
			return actualPrice;
		}
	
		
	//view total price  
	public double viewTotalPrice() throws InterruptedException {
		waitForTotalPriceToBeVisible();
		String totalCurrency = getText_custom(custom_findElement(totalPrice));
		totalCurrency=MathUtility.regularExpressionRetainDot(totalCurrency).trim();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Total Price: "+totalCurrency);
		return MathUtility.parseStringToDouble(totalCurrency);
	}
	
	
	//view total price  
		public double viewTotalPriceAfterApplyingCoupon() throws InterruptedException {
			waitForTotalPriceToBeVisible();
			String totalCurrency = getText_custom(custom_findElement(totalPrice));
			totalCurrency=MathUtility.regularExpressionRetainDot(totalCurrency).trim();
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Total Price: "+totalCurrency);
			return MathUtility.parseStringToDouble(totalCurrency);
		}
		
	//validate total and job total price
	public void validateTotal_JobTotalPriceBeforeApplyingCoupon(SoftAssert sa) throws InterruptedException {
		waitForElementToBeInVisible(custom_findElements(spinner));
		waitForTotalPriceToBeVisible();
		sa.assertEquals(viewAndValidateJobTotalPrice(sa),viewTotalPrice());
		ExtentFactory.getInstance().getExtent().log(Status.INFO, viewAndValidateJobTotalPrice(sa)+ " matched with " +viewTotalPrice());
	}
	
	//validate total and job total price
		public void validateTotal_JobTotalPriceAfterApplyingCoupon(SoftAssert sa) throws InterruptedException {
			sa.assertEquals(viewAndValidateJobTotalPriceWithCouponCode(sa),viewTotalPrice());
			ExtentFactory.getInstance().getExtent().log(Status.INFO, viewAndValidateJobTotalPriceWithCouponCode(sa)+ " matched with " +viewTotalPrice());
		}
		
		//validate total price
		public void validateTotalPriceAfterApplyingCoupon(SoftAssert sa) throws InterruptedException {
			waitForElementToBeInVisible(custom_findElements(spinner));
			waitForTotalPriceToBeVisible();
			if(viewAndValidateJobTotalPrice(sa)>viewTotalPrice()) {
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "Coupon code applied and Total price is " +viewTotalPrice());
			}
			else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Coupon code did not apply and Total price is " +viewTotalPrice());
				sa.fail();
			}
		}
	
	//check reward points 
	public void viewShppingCartLabel() {
		WebElement shoppingCart = custom_findElement(shoppingCartLabel);
		Assert.assertTrue(shoppingCart.isDisplayed());
		ExtentFactory.getInstance().getExtent().log(Status.INFO, shoppingCart.getText()+" Page is displayed");
	}
	
	//check reward points 
	public void viewRewardPoints() {
		ExtentFactory.getInstance().getExtent().log(Status.INFO, custom_findElement(rewardPoints).getText());
	}
	
	//validate price with reward point
	public void validatePricewithRewardPoints() {
		String rewards=custom_findElement(rewardPoints).getText().replace("REWARD POINTS", "").trim();
		int point=Integer.parseInt(rewards);
		String productPrice = custom_findElement(costPrice).getText().replace("$", "");
		int price = (int) Double.parseDouble(productPrice);
		if(point==price) {
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Reward Points "+point+" and the Price is "+productPrice);
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Reward Points "+point+" and the Price is "+productPrice);
		}
	}
	
	//Click Edit Icon for Edit Product Details
	public void clickEditLink(String edit) {
		click_custom(custom_findElement(editLink), edit);
	}
	
	//Click Remove Product Icon for Remove Product 
	public void clickRemoveProductLink() {
		click_custom(custom_findElement(removeProductLink), getText_custom(custom_findElement(removeProductLink)));
	}
	
	//for Enter Promocode in Enter Promocode Textbox
	public void enterPromoCode(String code) {
		sendKeys_custom(custom_findElement(EnterpromoCode), code, "Code");
	}
	
	//Click Apply Button for Apply PromoCode 
	public void clickApplyButton(String name) {
		click_custom(custom_findElement(applyButton), name);
	}
	
	//Click Proceed To Checkout Button for Proceed To Checkout 
	public void clickProceedToCheckOutButton(String name) throws InterruptedException {
		waitForTotalPriceToBeVisible();
		waitFor(1000);
		waitForElementToBeClickable(custom_findElement(proceedToCheckOutButton));
		if(custom_findElement(proceedToCheckOutButton).isEnabled())
			click_custom(custom_findElement(proceedToCheckOutButton), name);
	}
	
	// wait for total price element visible
	public void waitForTotalPriceToBeVisible() {
		waitForElementToBeVisible(custom_findElement(totalPrice));
	}
	
	//For Click Shipment Menu for Showing Address Details and Edit Artwork
	public void clickShipment(String name) {
		click_custom(custom_findElement(shipmentHead), name);
	}
	
	//Click Edit Artwork Icon for Edit Artwork 
	public void clickEditArtworkLink(String name) {
		click_custom(custom_findElement(editArtworkLink), name);
	}
	
	//Click Empty Shopping Cart Button for Empty Shopping Cart
	public void clickEmptyShoppingCartButton() {
		if(custom_findElement(emptyShoppingCartButton).isDisplayed()) {
			click_custom(custom_findElement(emptyShoppingCartButton), "Empty Shopping Cart");
			click_custom(custom_findElement(okForRemoveButton), "OK button ");
		}
	}
	
	public double validateCouponCodeAmount(String couponCode) {
		double discount=0.0;
		if(couponCode.equalsIgnoreCase("TKT1$4ITQ@")) {
			return 1.00;
		}
		else if(couponCode.equalsIgnoreCase("TKT1%4ITQ@")) {
			discount= viewSubTotalPrice()/100;
			return MathUtility.valueInDecimalFormat(discount);
			
		}
		else
			return discount;
	}
	
	//apply coupon code
	public void applyCouponCode(String couponCode) throws InterruptedException {
		waitForTotalPriceToBeVisible();
		sendKeys_custom(custom_findElement(EnterpromoCode),couponCode,couponCode);
//		click_javascript(custom_findElement(applyButton), getText_custom(custom_findElement(applyButton)));
	}
	
	public void verifyDiscountIsRemoved1(SoftAssert sa){
		if(custom_findElements(discountCoupon).size()==0) {
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Discount Coupon is removed successfully!!!");
			
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Discount Coupon is not able to remove "+getText_custom(custom_findElement(discountCoupon)));
			sa.fail();
		}
	}
	
	public void verifyDiscountIsRemoved(SoftAssert sa){
		if(!custom_findElement(discountCoupon).isDisplayed()) {
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Discount Coupon is removed successfully!!!");
			
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Discount Coupon is not able to remove "+getText_custom(custom_findElement(discountCoupon)));
			sa.fail();
		}
	}
	
	public void verifyDiscountIsApplied(String expectedValue){
		 /* 0. Round off the expected value to 2 digits
		  * 1. Validate minus sign should be present
		  * 2. Read the discount value from the UI/App(Actual value)
		  * 3. Coupon validation : Ensure actual value == expected value
		  */
		double expectedPrice = MathUtility.parseStringToDouble(expectedValue);
		expectedPrice=MathUtility.valueInDecimalFormat(expectedPrice);
		if(getText_custom(custom_findElement(discountPrice)).contains("-") && getText_custom(custom_findElement(discountCoupon)).contains("Discount") && viewDiscountPrice()==expectedPrice) {
			Assert.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Discount Coupon used is correct "+getText_custom(custom_findElement(discountCoupon)));
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Discount Coupon "+getText_custom(custom_findElement(discountCoupon))+" did not match with "+getText_custom(custom_findElement(discountCoupon)));
			Assert.fail();
		}
	}
	
	//cancel coupon code
	public void cancelCouponCode() throws InterruptedException {
		waitFor(2000);
		click_javascript(custom_findElement(cancelCoupon), getText_custom(custom_findElement(cancelCoupon)));
	}
	
	
	//store cost summary into Map
	public Map<String, String> costSummaryStoredInMap(){
//	1. expected : map1.get(Tax) >= actual
//	2. expected : map1.get(JOB TOTAL) >= actual
		Map<String, String> costSummary = new LinkedHashMap<>();
		List<WebElement> costSummaryLabel = custom_findElements(By.xpath("//h4[text()='Cost Summary']/..//li/div[1]"));
		for(int i=3;i<=costSummaryLabel.size();i++) {
			String costLabel=getText_custom(custom_findElement(By.xpath("(//h4[text()='Cost Summary']/..//li/div[1])["+i+"]")));
			String costPrice=getText_custom(custom_findElement(By.xpath("(//h4[text()='Cost Summary']/..//li/div[2])["+i+"]")));
			costSummary.put(costLabel, costPrice);
		}
		costSummary.entrySet().forEach(entry->{
			System.out.println(entry.getKey()+" "+entry.getValue());
		});
		return costSummary;
	}
	
	public void verifyCostSummary(Map<String, String> costSummaryWithoutApplyingDiscount,Map<String, String> costSummaryWithApplyingDiscount,SoftAssert sa) {
		String taxWithoutCoupon=costSummaryWithoutApplyingDiscount.get("Tax");
		taxWithoutCoupon=MathUtility.regularExpressionRetainDot(taxWithoutCoupon);
		
		String taxWithCoupon=costSummaryWithApplyingDiscount.get("Tax");
		taxWithCoupon=MathUtility.regularExpressionRetainDot(taxWithCoupon);
		sa.assertTrue(MathUtility.parseStringToDouble(taxWithoutCoupon)>= MathUtility.parseStringToDouble(taxWithCoupon));
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "Without Coupon tax is "+taxWithoutCoupon+" and With Coupon tax is "+taxWithCoupon);
		
		String jobWithoutCoupon=costSummaryWithoutApplyingDiscount.get("JOB TOTAL");
		jobWithoutCoupon=MathUtility.regularExpressionRetainDot(jobWithoutCoupon);
		
		String jobWithCoupon=costSummaryWithApplyingDiscount.get("JOB TOTAL");
		jobWithCoupon=MathUtility.regularExpressionRetainDot(jobWithCoupon);
		sa.assertTrue(MathUtility.parseStringToDouble(jobWithoutCoupon)>= MathUtility.parseStringToDouble(jobWithCoupon));
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "Without Coupon JOB TOTAL is "+jobWithoutCoupon+" and With Coupon JOB TOTAL is "+jobWithCoupon);
		}
}
