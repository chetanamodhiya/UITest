package com.magento.customerAccountTestScripts;

import org.testng.annotations.Test;

import com.magento.pages.BasePage;
import com.magento.pages.MyOrderPage;
import com.magento.testBase.TestBase;

public class ValidateOrderDetails extends TestBase{
	BasePage bp=new BasePage();
	MyOrderPage order=new MyOrderPage();
	
	@Test()
	public void validateOrder() {
		bp.clickMyOrders();
		order.validateOrderNumber("000020218");
	}
}
