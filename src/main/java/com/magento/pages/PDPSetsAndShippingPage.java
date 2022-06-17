package com.magento.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.magento.reusableComponents.MathUtility;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class PDPSetsAndShippingPage extends TestBase{

	By quoteShippingMethod=By.xpath("(//span[text()='Shipping Method']/../../..//span)[3]");
	By shippingMethodDropdown=By.xpath("//span[text()='Shipping Method']/../../..//select");
	By uploadYourArtButton=By.xpath("//button[@class='action btn-upload']");
	By addToCartButton=By.xpath("//*[@class='action primary']");
	By subTotalPrice=By.xpath("//span[text()='Subtotal']/..//span[@class='value']");
	By shippingMethodText=By.xpath("//span[text()='Shipping Method']");
	By spinner=By.xpath("//img[@title='Loading...']");
	By requireProject_ClientNameField=By.xpath("//input[@id='project_name']/..//span[text()='This is a required field.']");
	By projectName=By.id("project_name");
	By setName=By.xpath("//div[@class='shipment-set-name_item']//input");
	By sendReminder=By.xpath("//div[@class='field choice']/input[@id='send_remainders']");
	By jobSamples=By.xpath("//div[@class='control']/input[@id='job_sample']");
	By saveProjectForLater=By.xpath("//button[@class='action btn-save']/span");
	By addSetsButton=By.xpath("//span[@class='action more']");
	By setsCount=By.xpath("//span[@class='sets-count']");
	By addAnotherShipmentButton=By.xpath("//button[@class='action btn-add']/span[text()='Add Another Shipment']");
	By shipments=By.xpath("//span[text()='Shipment:']/../following-sibling::span");
	By shippingPrice=By.xpath("//span[contains(@data-bind,'shipping') and @class='value']");
	By taxPrice=By.xpath("//span[contains(@data-bind,'tax') and @class='value']");
	By grandTotalPrice=By.xpath("//span[contains(@data-bind,'grand_total')]");
	By setsAndShippingText=By.xpath("//a[text()='Sets & Shipping']/..");
	By smallQuantityFeeText=By.xpath("//span[text()='Small Quantity Fee']");
	By smallQuantityFeeCharge=By.xpath("//span[text()='Small Quantity Fee']/../..//span[@class='value']");
	
	public void validateTextAsPerQuantity(String quantity, String expectedCharge) {
		if(MathUtility.parseStringToInteger(quantity)<=3) {
			if(custom_findElement(smallQuantityFeeText).isDisplayed() && getText_custom(custom_findElement(smallQuantityFeeCharge)).equalsIgnoreCase(expectedCharge)) {
				Assert.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "For Quantity "+quantity+ " "+getText_custom(custom_findElement(smallQuantityFeeText))+ " is displayed and Expected charge " 
						+expectedCharge+" is matched with Actual Charge " +getText_custom(custom_findElement(smallQuantityFeeCharge)));
			}
			else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "For Quantity "+quantity+ " Small Quantity Fee is not displayed and Actual charge is " +getText_custom(custom_findElement(smallQuantityFeeCharge)));
				Assert.fail();
			}
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Quantity is more than 3, Quantity is "+quantity);
		}
	}
	
	//view subtotal price  
	public double viewSubTotalPrice() {
		String subTotalCurrency = getText_custom(custom_findElement(subTotalPrice));
		subTotalCurrency=MathUtility.regularExpressionRetainDot(subTotalCurrency).trim();
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "SubTotal Price: "+subTotalCurrency);
		return MathUtility.parseStringToDouble(subTotalCurrency);
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
		double taxAmount=0.0;
		if(custom_findElements(taxPrice).size()>0) {
			String taxCurrency = getText_custom(custom_findElement(taxPrice));
			taxCurrency=MathUtility.regularExpressionRetainDot(taxCurrency).trim();
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Tax Price: "+taxCurrency);
			return MathUtility.parseStringToDouble(taxCurrency);
		}
		return taxAmount;
	}
	
	//view grand total price  
		public double viewAndValidateGrandTotalPrice(SoftAssert sa) {
			String totalCurrency = getText_custom(custom_findElement(grandTotalPrice));
			totalCurrency=MathUtility.regularExpressionRetainDot(totalCurrency).trim();
			double actualPrice = MathUtility.parseStringToDouble(totalCurrency);
			double expectedPrice=viewSubTotalPrice()+viewShippingPrice()+viewTaxPrice();
			sa.assertEquals(actualPrice, expectedPrice);
			ExtentFactory.getInstance().getExtent().log(Status.INFO, actualPrice+ " matched with " +expectedPrice);
			return actualPrice;
		}
	
	// click Checkbox for Send me the remainders
		public void clickSendmetheremainders() throws InterruptedException {
			WebElement checkBox = custom_findElement(sendReminder);
			if(!(checkBox.isSelected())) {
				click_javascript(checkBox, "Send me the remainders");
			}
		}
		
	// click Checkbox for Send me the remainders
		public void clickJobSamples() throws InterruptedException {
			WebElement checkBox = custom_findElement(jobSamples);
			if(!(checkBox.isSelected())) {
				click_javascript(checkBox, "Job Samples");
			}
		}
	
	//for select Shipping Option in Shipping Method Dropdown
	public void selectShippingMethod(String shippingMethod) throws Throwable {
		if(custom_findElements(shippingMethodDropdown).size()>0)
			selectDropDownByPartialText_custom(custom_findElement(shippingMethodDropdown),shippingMethod,"Shipping Method dropdown");
		else if(custom_findElement(quoteShippingMethod).isDisplayed())
		{
			ExtentFactory.getInstance().getExtent().log(Status.INFO, custom_findElement(quoteShippingMethod).getText()+" is displayed");
			Assert.fail();;
		}
		else 
			Assert.fail("Shipping Method Dropdown or Sorry, no quotes are available.... is not displayed");
	}
	
	//for select second option from Shipping Method Dropdown
	public void selectSecondOptionFromShippingMethod() throws Throwable {
		if(custom_findElements(shippingMethodDropdown).size()>0)
			selectSecondOptionFromDropDown(custom_findElement(shippingMethodDropdown),"Shipping Method");
		else if(custom_findElement(quoteShippingMethod).isDisplayed())
		{
			ExtentFactory.getInstance().getExtent().log(Status.INFO, custom_findElement(quoteShippingMethod).getText()+" is displayed");
			Assert.assertTrue(false);
		}
		else {
			Assert.fail("Shipping Method Dropdown or Sorry, no quotes are available.... is not displayed");
		}
	}
	
	//for Check the availability of Shipping Method Dropdown 
	public void validateAvailabilityOfShippingMethod() throws InterruptedException {
		if(custom_findElements(shippingMethodDropdown).size()>0)
		{
			for(int i=0;i<custom_findElements(shippingMethodDropdown).size();i++) {
				Assert.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.INFO, "Shipping Method dropdown is displayed for Shipment: "+custom_findElements(shipments).get(i).getText());
			}
		}
		else if(custom_findElements(quoteShippingMethod).size()>0)
		{
			ExtentFactory.getInstance().getExtent().log(Status.INFO, getText_custom(custom_findElement(quoteShippingMethod))+" is displayed");
			Assert.assertTrue(false);
		}
		else {
			Assert.fail("Shipping Method Dropdown or Sorry, no quotes are available.... is not displayed");
		}
	}
		
		//for Check the availability of Shipping Method Dropdown 
		public void validateAvailabilityOfShippingMethodAndClocktheTime() throws InterruptedException {
			long startTime = System.currentTimeMillis();
			if(custom_findElements(spinner).size()>0) {
				waitForElementToBeInVisible(custom_findElements(spinner));
			}
			long endTime = System.currentTimeMillis();
			//log the time in the report
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "WaitTillSpinnerDisappear, Timer  : "+ (endTime-startTime)+" ms");
			if(custom_findElements(shippingMethodDropdown).size()>0)
			{
				List<WebElement> shipping = custom_findElements(shippingMethodDropdown);
				for(int i=0;i<shipping.size();i++) {
					Assert.assertTrue(true);
					ExtentFactory.getInstance().getExtent().log(Status.INFO, "Shipping Method dropdown is displayed for Shipment: "+custom_findElements(shipments).get(i).getText());
				}
			}
			else if(custom_findElements(quoteShippingMethod).size()>0)
			{
				ExtentFactory.getInstance().getExtent().log(Status.INFO, getText_custom(custom_findElement(quoteShippingMethod))+" is displayed");
				Assert.assertTrue(false);
			}
			else {
				Assert.fail("Shipping Method Dropdown or Sorry, no quotes are available.... is not displayed");
			}
		}
		
		
	//Click Upload Artwork button for Uploding Artwork
		public void clickUploadYourArtButton() throws InterruptedException {
			waitFor(1000);
			WebElement uploadYourArtButtonElement=custom_findElement(uploadYourArtButton);
			waitForElementToBeClickable(uploadYourArtButtonElement);
			click_javascript(uploadYourArtButtonElement, "Upload Your Art button");
		}
	
	//Click Add To Cart Button for Product Add To Cart
		public void clickAddToCartButton() throws InterruptedException {
			waitFor(1000);
			List<WebElement> addToCartButtonElement=custom_findElements(addToCartButton);
			waitForElementToBeClickable(addToCartButtonElement.get(0));
			if(addToCartButtonElement.get(0).isEnabled()) {
				click_javascript(addToCartButtonElement.get(0), "Add To Cart button");
				if(custom_findElements(spinner).size()>0) {
					waitForElementToBeInVisible(custom_findElements(spinner));
				}
			}
			else {
				ExtentFactory.getInstance().getExtent().log(Status.INFO, "Add To Cart Button is disabled");
				Assert.fail();
			}
		}
	
	// for visibility of Sub Total Price 
	public String visiblePriceShipping() {
		List<WebElement> subTotal=custom_findElements(subTotalPrice);
		return subTotal.get(0).getText().trim();
	}
	
	//For Enter Project Name in Project/Client Name TextBox
	public void validateProjectOrClientNameIsMandatoryField() {
		if(custom_findElement(requireProject_ClientNameField).isDisplayed()) {
			Assert.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "PROJECT/CLIENT NAME Field shows "+custom_findElement(requireProject_ClientNameField).getText());
		}
	}
	
	//For Enter Project Name in Project/Client Name TextBox
		public void enterProjectOrClientName(String project) {
				sendKeys_custom(custom_findElements(projectName).get(0), project,"ProjectName textbox");
		}
	
	
	//For Enter Set1Name in Set1Name TextBox
		public void enterSet1Name(String set1Name) {
			sendKeys_custom(custom_findElements(setName).get(0), set1Name,set1Name);
		}
	
	//verify Save Project For Later link is displayed or not
		public void verifySaveProjectForLaterLink() {
			if(custom_findElement(saveProjectForLater).isDisplayed()) {
				Assert.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "User is able to see the SAVE PROJECT FOR LATER link on the sets and shipping page");
			}
			else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "User is not able to see the SAVE PROJECT FOR LATER link on the sets and shipping page");
				Assert.fail();
			}
		}
	
	//click Save Project For Later link
	public void clickSaveProjectForLaterLink() {
		if(custom_findElement(saveProjectForLater).isDisplayed());
		click_javascript(custom_findElement(saveProjectForLater), getText_custom(custom_findElement(saveProjectForLater)));
	}
	
	//add sets
	public void clickForAddAnotherSet() {
		click_javascript(custom_findElement(addSetsButton), "Plus sign for Adding Sets");
		ExtentFactory.getInstance().getExtent().log(Status.INFO, " Sets- "+getText_custom(custom_findElement(setsCount)));
	}
	
	//add another shipment
		public void clickForAddAnotherShipment(String shipmentNumber) {
			int noOfShipments=Integer.parseInt(shipmentNumber);
			for(int totalShipments=2;totalShipments<=noOfShipments;totalShipments++) {
				click_javascript(custom_findElement(addAnotherShipmentButton), getText_custom(custom_findElement(addAnotherShipmentButton)));
				ExtentFactory.getInstance().getExtent().log(Status.INFO, " Shipment: "+totalShipments+" available");
			}
		}
		
		// click Checkbox for Send me the remainders
		public void clickForAddAnotherSet(String setNumber,String shipmentNumber) throws InterruptedException {
			int noOfSets=Integer.parseInt(setNumber);
			By sets=By.xpath("//span[text()='Shipment:']/../following-sibling::span[text()='"+shipmentNumber+"']/../../..//span[@class='action more']");
			for(int totalSets=2;totalSets<=noOfSets;totalSets++) {
				click_javascript(custom_findElement(sets), "Plus sign for Adding Sets");
				ExtentFactory.getInstance().getExtent().log(Status.INFO, " Sets- "+totalSets+" available");
			}
		}
		
		public int totalSets() {
			int setCount=0;
			for(WebElement set:custom_findElements(setsCount)) {
				setCount=Integer.parseInt(set.getText())+setCount;
			}
			return setCount;
		}
		
		public int totalShipments() {
			int shipmentCount=custom_findElements(shipments).size();
			return shipmentCount;
		}
		
		public void validateUserIsNavigatedToSetsAndShippingPage() {
			if(custom_findElement(setsAndShippingText).getAttribute("class").contains("active")) {
				Assert.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "User is on Sets And Shipping page");
			} else {
				Assert.assertTrue(false);
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "User is not navigated to Sets And Shipping page");
			}
		}
		
		//check Sample of Completed job is displayed
		public void iSSampleOfCompletedJobDisplayed() {
			if(!custom_findElement(jobSamples).isDisplayed()) { 
				Assert.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "Job Samples is not displayed");
			}
			else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Job Samples is displayed");
				Assert.fail();
			}
		}
}
