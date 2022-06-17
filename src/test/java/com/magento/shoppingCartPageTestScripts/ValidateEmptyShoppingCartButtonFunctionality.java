package com.magento.shoppingCartPageTestScripts;

import org.testng.annotations.Test;

import com.magento.pages.BasePage;
import com.magento.pages.ShoppingCartPage;
import com.magento.testBase.TestBase;

public class ValidateEmptyShoppingCartButtonFunctionality extends TestBase{
	BasePage bp = new BasePage();
	ShoppingCartPage cart=new ShoppingCartPage();
	@Test()
	public void validateEmptyShoppingCartButton() throws Throwable{
		bp.clickMiniCart();
		bp.clickViewCartButton("View Cart");
		cart.clickEmptyShoppingCartButton();
}
}
