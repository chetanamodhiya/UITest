package com.magento.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class OrderConfirmationPage extends TestBase{
	
	By orderPlaced=By.xpath("//div[@class='orders-succeed']");
	By orderLink=By.xpath("//div[@class='order-id']//a");
	By myOrdersLink=By.linkText("My Orders");
	By jobId=By.xpath("(//h4[text()='Job ID']/../../..//span)[2]");
	By customerName=By.xpath("//div[@class='welcome-customer-title top-banner-block']");
	By orderId=By.xpath("//div[@class='order-id']/a");
	By thankYouMessage=By.xpath("//span[@class='base']");
	By spinner=By.xpath("//img[@title='Loading...']");
	
	public String viewOrderId() throws InterruptedException {
		waitForElementToBeVisible(custom_findElement(orderId));
		List<WebElement> orders = custom_findElements(orderId);
		if(orders.size()>0) {
			Assert.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Customer Order Id : "+orders.get(0).getText());
		}
		return orders.get(0).getText();
	}
	
	public String getThankYouMessageText() throws InterruptedException {
		waitForElementToBeInVisible(custom_findElements(spinner));
		waitForElementToBeVisible(custom_findElement(thankYouMessage));
		String thankYouMessageText = custom_findElement(thankYouMessage).getText();
		return thankYouMessageText;
	}
	
	public void validateThankYouMessage(String expectedMessage, SoftAssert sa) throws InterruptedException {
		sa.assertEquals(custom_findElement(thankYouMessage).getText(), expectedMessage);
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "After placed the order:  "+expectedMessage+" message is displayed");
	}
}
