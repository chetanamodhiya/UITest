package com.magento.pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.magento.reusableComponents.MathUtility;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class OrderHistoryPage extends TestBase {
	
	By uploadArtwork=By.linkText("Upload");
	By pdfProofs_jobSamples=By.xpath("//td[@data-th='Product Name']//dd[contains(text(),'per set')]");
	By requestedEstimates=By.xpath("//a[text()='Requested Estimates']");
	By subTotalPrice=By.xpath("//td[@data-th='Subtotal']//span[@class='price']");
	By shippingPrice=By.xpath("//td[@data-th='Shipping & Handling']//span[@class='price']");
	By taxPrice=By.xpath("//td[@data-th='Tax']//span[@class='price']");
	By grandTotalPrice=By.xpath("//td[@data-th='Grand Total']//span[@class='price']");
	
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
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Total Price: "+totalCurrency);
		return actualPrice;
	}
	
	//click Requested Estimates
	public void clickRequestedEstimates() {
		click_custom(custom_findElement(requestedEstimates), "Requested Estimates");
	}
	
	//click upload artwork after place the order
	public void clickUploadArtwork(String orderId) {
		By artworkUpload=By.xpath("//span[text()='"+orderId+"']/../../..//div/div[contains(@class,'artwork')]//a");
		click_custom(custom_findElement(artworkUpload), "Artwork Upload Button");
	}
	
	//view job Id 
	public void viewJobId(String orderId) throws InterruptedException {
		By jobID=By.xpath("//span[text()='"+orderId+"']/../../..//div[contains(@class,'job-id')]/span");
		waitForElementToBeVisible(custom_findElement(jobID));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Job Id is: "+custom_findElement(jobID).getText());
		Assert.assertTrue(true);
	}
	
	//view job Id and store in string 
		public String viewAndStoreJobId(String orderId) throws InterruptedException {
			By jobID=By.xpath("//span[text()='"+orderId+"']/../../..//div[contains(@class,'job-id')]/span");
			waitForElementToBeVisible(custom_findElement(jobID));
			String jobIDText=custom_findElement(jobID).getText();
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Job Id is: "+jobIDText);
			Assert.assertTrue(true);
			return jobIDText;
		}
	
	//click View Order Button
	public void clickViewOrderButton(String orderId) {
		By viewOrderButton=By.xpath("//span[text()='"+orderId+"']/../..//a[text()='View Order']");
		click_custom(custom_findElement(viewOrderButton), "View Order");
	}
	
	//validate pdf proofs or sample of job completed text 
	public void validatePDFProofsOrSampleOfCompletedJobText(SoftAssert sa) {
		if(custom_findElements(pdfProofs_jobSamples).size()>0) {
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, getText_custom(custom_findElement(pdfProofs_jobSamples))+" is displayed");
		}
	}
}
