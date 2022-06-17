package com.magento.pages;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import com.magento.testBase.DriverFactory;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class DirectMailingServicePage extends TestBase{
	By frameInside=By.xpath("//iframe[contains(@data-bind,'iframeLoaded')]");
	By csvFilesHere=By.xpath("//div[@label='fileUpload']");
	By csvFileUpload=By.xpath("//div[@label='fileUpload']/input");
	By spinner=By.xpath("//img[@title='Loading...']");
	By chooseLevelofAddress=By.xpath("//div[@class='form-group form-group-validation-level']//div/p");
	By reviewValidationButton=By.xpath("//button[@class='nav-next-upload']");
	By CalculatePostageButton=By.xpath("//button[@id='nav-next']");
	By closeButton=By.xpath("//h1[contains(text(),' Direct Mailing Services')]/..//div");
	By ClickonLevel=By.xpath("//div//input[@name='validation_type']/..//p");
	By ClickLevel=By.xpath("//div//input[@name='validation_type']/..");
	By approvalCheckbox=By.id("approval");
	By waitForLoading=By.xpath("//button[contains(@class,'loading')]");
	By acceptMailingDetailsButton=By.xpath("//button[@class='nav-next-confirm']");
	
	
	//for come inside the frame 
	public void enterTheInsideFrame() {
		waitForElementToBeInVisible(custom_findElements(spinner));
		switchToFrame_custom(custom_findElement(frameInside));
	}
	
	//for click files here link
	public void clickFilesHere() throws InterruptedException{
		WebElement cSVFileUpload = custom_findElement(csvFilesHere);
		waitForElementToBeClickable(cSVFileUpload);
		click_custom(cSVFileUpload, "Click here");
		
	}
	
	//for upload the files from local machine.
	public void uploadTheFiles(String fileName) throws InterruptedException, AWTException{
		waitFor(1000);
		uploadTheFiles_custom(fileName);
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "File ==> Uploaded Successfully! ");
		Assert.assertTrue(true);
	}
	
	//for upload the files from local machine.
		public void uploadCSVFiles(String fileName) throws InterruptedException, AWTException{
			waitFor(2000);
			sendKeys_custom(custom_findElement(csvFileUpload), fileName, "Click here");
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "File ==> Uploaded Successfully! ");
			Assert.assertTrue(true);
		}
	
	//for Choose level of address validation for mailing list
	public void clickLevelofAddress(String level) throws InterruptedException {
		List<WebElement> levelAddress = custom_findElements(ClickonLevel);
		for(WebElement address:levelAddress) {
			if(address.getText().equalsIgnoreCase(level)) {
				click_custom(custom_findElement(ClickLevel), level);
				break;
			}
			else
			{
				Assert.fail();
			}
		}
	}
	
	//for Click Next >> Review Validation Results
	public void clickReviewValidation() {
		WebElement reviewButton = custom_findElement(reviewValidationButton);
		waitForElementToBeClickable(reviewButton);
			Assert.assertTrue(reviewButton.isEnabled());
			click_custom(custom_findElement(reviewValidationButton), "NEXT >> Review Validation Results");
			waitForElementToBeInVisible(custom_findElements(waitForLoading));
	}
		
	//for Click Next >> Calculate Postage
	public void clickCalculatePostage() throws InterruptedException {
		WebElement calculateButton = custom_findElement(CalculatePostageButton);
		waitForElementToBeClickable(calculateButton);
			Assert.assertTrue(calculateButton.isEnabled());
			click_custom(calculateButton, "NEXT >> calculate Postage");
			waitForElementToBeInVisible(custom_findElements(waitForLoading));
	}

	//Click Chekbox for Approve Mailing List
	public void clickCheckBoxForApproval() throws InterruptedException {
		WebElement approvalCheckBox = custom_findElement(approvalCheckbox);
		waitForElementToBeClickable(approvalCheckBox);
		Assert.assertTrue(approvalCheckBox.isDisplayed());
		click_custom(approvalCheckBox, "Checkbox For Approval");
	}
	
	//Click Accept Mailing Details
	public void clickAcceptMailingDetails() throws InterruptedException {
		WebElement acceptMailingDetails = custom_findElement(acceptMailingDetailsButton);
		waitForElementToBeClickable(acceptMailingDetails);
		Assert.assertTrue(acceptMailingDetails.isEnabled());
		click_custom(acceptMailingDetails, "Accept Mailing Details");
	}
	
	public void closeDirectMailingServicePage() {
		click_custom(custom_findElement(closeButton), "Close");
	}	
}
