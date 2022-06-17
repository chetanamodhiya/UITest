package com.magento.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class MyOrderPage extends TestBase{
	By nextPageArrow=By.xpath("//a[@class='action  next']");
	By orderNumber=By.xpath("//h4[text()='Order Number']/..//span");
	By projectText=By.xpath("//h3[@class='project_name']");


	public void validateOrderNumber(String expectedOrder) {
		if(custom_findElements(nextPageArrow).size()==0) {
			for(WebElement orderId:custom_findElements(orderNumber)) {
				if(getText_custom(orderId).equalsIgnoreCase(expectedOrder)){
					Assert.assertTrue(true);
					ExtentFactory.getInstance().getExtent().log(Status.PASS, "Order Id is matched, Order Id is: "+expectedOrder);
					break;
				}
			}
		}
		else if(custom_findElements(nextPageArrow).size()>0) {
			for(WebElement orderId:custom_findElements(orderNumber)) {
				if(getText_custom(orderId).equalsIgnoreCase(expectedOrder)){
					Assert.assertTrue(true);
					ExtentFactory.getInstance().getExtent().log(Status.PASS, "Order Id is matched, Order Id is: "+expectedOrder);
					break;
				}
			}
			click_javascript(custom_findElement(nextPageArrow), "Next");
			validateOrderNumber(expectedOrder);
		}
	}
}
