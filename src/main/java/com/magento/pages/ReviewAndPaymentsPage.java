package com.magento.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.magento.reusableComponents.MathUtility;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class ReviewAndPaymentsPage extends TestBase{
	By paymentMethods=By.xpath("//div[contains(@class,'payment-method-title field choice')]/label/span");
	By spinner=By.xpath("//img[@title='Loading...']");
	By creditCardTextBox=By.id("anet_creditcard_cc_number");
	By selectCreditCardExpMonth=By.name("payment[cc_exp_month]");
	By selectCreditCardExpYr=By.name("payment[cc_exp_year]");
	By creditCardVerificationNo=By.name("payment[cc_cid]");
	By placeOrderButton=By.xpath("//div[@class='payment-method _active']/div[2]/div//button[@class='action primary checkout']");
	By saveForLaterUseCheckBox=By.xpath("//div[@class='payment-method _active']//div[@class='field choice']/input");
	By routingNumberTextBox=By.id("routing_number");
	By accountNumberTextBox=By.id("account_number");
	By nameOnAccountTextBox=By.id("account_name");
	By accountTypeDropdown=By.id("account_type");
	By grandTotalPrice=By.xpath("//tr[@class='grand totals']//span[@class='price']"); 
	
	
	//view grand total price  
	public double viewGrandTotalPrice() {
		String totalCurrency = getText_custom(custom_findElement(grandTotalPrice));
		totalCurrency=MathUtility.regularExpressionRetainDot(totalCurrency).trim();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Total Price: "+totalCurrency);
		return MathUtility.parseStringToDouble(totalCurrency);
	}
	
	//for select which Payment Method to choose for Payment 
	public void selectPaymentMethods(String paymentOption) throws InterruptedException {
		waitFor(4000);
		waitForElementToBeVisible(custom_findElement(paymentMethods));
		for(WebElement paymentOptionsList:custom_findElements(paymentMethods)) {
			if(paymentOptionsList.getText().contains(paymentOption)) {
				click_javascript(paymentOptionsList, paymentOption+" radiobutton");
				break;
			}
		}
	}
	
	// for credit card fill the card details 
	public void enterCardDetails(String cardNumber,String cardExpDateWithMonth, String cardExpYr,String cardVerificationNo) throws Throwable {
		waitFor(500);
		sendKeys_custom(custom_findElement(creditCardTextBox),cardNumber,"CreditCardDetails");
		waitFor(1000);
		selectDropDownByVisibleText_custom(custom_findElement(selectCreditCardExpMonth), cardExpDateWithMonth, "cardExpDateWithMonth");
		waitFor(1000);
		selectDropDownByVisibleText_custom(custom_findElement(selectCreditCardExpYr), cardExpYr, "cardExpDateWithMonth");
		waitFor(500);
		sendKeys_custom(custom_findElement(creditCardVerificationNo),cardVerificationNo,"CreditCardDetails");
	}	
	
	// for echeck fill the details 
		public void enter_eCheckDetails(String routingNo,String accountNo, String nameOnAccount,String accountType) throws Throwable {
			sendKeys_custom(custom_findElement(routingNumberTextBox),routingNo,"Routing Number");
			sendKeys_custom(custom_findElement(accountNumberTextBox),accountNo,"Account Number");
			sendKeys_custom(custom_findElement(nameOnAccountTextBox),nameOnAccount,"Name on Account");
			selectDropDownByVisibleText_custom(custom_findElement(accountTypeDropdown), accountType, "Account Type");
		}	
		
	// click Save For Later Use Checkbox for next time used same Payment Options.
	public void clickSaveForLaterUseCheckBox() throws InterruptedException {
		if(custom_findElements(saveForLaterUseCheckBox).size()>0) {
			WebElement saveCheckBox = custom_findElement(saveForLaterUseCheckBox);
			if(saveCheckBox.isSelected()) {
				click_javascript(saveCheckBox, "Save For Later Use");
			}
		}
	}
	
	//for Click Place the Order Button for Placing the order.
	public void clickPlaceOrderButton() throws InterruptedException {
		waitFor(2000);
		if(custom_findElement(placeOrderButton).isEnabled()) {
			click_javascript(custom_findElement(placeOrderButton), "Place Order button");
			waitForElementToBeInVisible(custom_findElements(spinner));
		}
	}
}
