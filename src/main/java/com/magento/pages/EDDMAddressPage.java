package com.magento.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.magento.reusableComponents.MathUtility;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class EDDMAddressPage extends TestBase{
	
	By enterZipcode=By.xpath("//section[@class='default-search']//div[@class='eddm-panel eddm-panel-search']//input");
	By searchButton=By.xpath("//section[@class='search-input-wrapper']//button");
	By loading=By.xpath("//div[@class='eddm-region-loading']");
	By spinner=By.xpath("//img[@title='Loading...']");
	By eddmFrame=By.xpath("//iframe[@id='eddm-iframe']");
	By eddmFrame1=By.xpath("//div[@id='eddm-easy-wrapper']//iframe");
	By address=By.xpath("//div[@class='eddm-panel-dropdown']//div[contains(@class,'pac-container pac-dropdown')]/div");
	By estimateTotalPrice=By.xpath("//dt[text()='Estimated total']/..//dd[4]");
	By listView=By.xpath("//i[@class='list-view']");
	By selectFirstChecbox=By.xpath("//div[@class='tablebody']//td[1]/i");
	By saveOrderButton=By.xpath("//button[@id='btnNext']");
	By waitForLoading=By.xpath("//button[contains(@class,'loading')]");
	By zeroRoutesMessage=By.xpath("//div[@class='ns-box-inner' and text()='USPS has found 0 routes within the Zip Code you entered. Please try an address instead.']");

	//verify zero route is displayed or not
	public void viewZeroRoutesIsDisplayed(SoftAssert sa) {
		if(custom_findElements(zeroRoutesMessage).size()>0) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Message "+getText_custom(custom_findElement(zeroRoutesMessage))+" is displayed");
			sa.fail();
		}
		else
			sa.assertTrue(true);
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "Routes are displayed");
	}
	
	
	//for come inside the frame 
	public void switchToFrameForDMOrEDDM(){
		switchToFrame_custom(custom_findElement(eddmFrame));
		switchToFrame_custom(custom_findElement(eddmFrame1));
	}
	
	public void enterZipcode(String zipCode) throws InterruptedException {
		waitForElementToBeVisible(custom_findElement(enterZipcode));
		WebElement zipCodeWebElement = custom_findElement(enterZipcode);
		if(zipCodeWebElement.isDisplayed()) {
			Assert.assertTrue(true);
			sendKeys_custom(zipCodeWebElement, zipCode, "ZipCode");
		}
		else {
			Assert.fail("Zipcode Text box not displayed");
		}
	}
	
	//click search button 
	public void clickSearchButton1() throws InterruptedException {
		waitFor(1000);
		click_custom(custom_findElements(searchButton).get(0), "Search");
		click_custom(custom_findElement(address), "Address");
	}
	
	public void clickSearchButton() throws InterruptedException {
		waitFor(500);
		click_custom(custom_findElement(address), "Address");
//		or
//		click_custom(custom_findElements(address).get(0), "Address");
	}
	
	
	public void validateEstimatedTotalPrice()  {
		String estimatedPrice = MathUtility.regularExpressionRetainDot(custom_findElement(estimateTotalPrice).getText().trim());
		if(MathUtility.parseStringToDouble(estimatedPrice)>0) {
			Assert.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Estimated Total Price ==> "+estimatedPrice);
		}
	}
	
	//check Save Order Button is disabled before select any routes
	public void validateSaveOrderButtonIsDisabledBeforeSelectingRoutes() throws InterruptedException {
		waitFor(1000);
		WebElement saveOrder = custom_findElement(saveOrderButton);
		waitForElementToBeVisible(saveOrder);
		if(saveOrder.isEnabled()) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "save Order Button ==> is Enabled");
			Assert.fail();
		}
		else {
			Assert.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Before Select route save Order Button ==> is Disabled");
		}
	}
	
	//click list view and select routes
	public void clickListViewAndSelectTheFirstRoute(){
		waitForElementToBeClickable(custom_findElement(listView));
		click_custom(custom_findElement(listView), "List View");
		waitForElementToBeClickable(custom_findElement(selectFirstChecbox));
		click_custom(custom_findElement(selectFirstChecbox), "Checkbox");
	}
	
	//click save order button
	public void clickSaveOrderButton() throws InterruptedException {
		click_custom(custom_findElement(saveOrderButton), "Save Order");
	}
}
