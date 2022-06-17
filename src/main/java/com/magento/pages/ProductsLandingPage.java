package com.magento.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.magento.testBase.TestBase;

public class ProductsLandingPage extends TestBase{
	By productsLink=By.xpath("//a[@class='product-item-link']");
	By stockDropdown = By.xpath("(//span[text()='Sign In'])[1]/..");
	
	//below method clicks on a specific Product link mentioned as 'expectedProduct'
	public void clickProductLink(String expectedProduct) throws InterruptedException {
		waitFor(500);
		for(WebElement productElement:custom_findElements(productsLink)) {
			String actualProduct=productElement.getText().trim();
			if(actualProduct.equalsIgnoreCase(expectedProduct)) {
				click_custom(productElement, expectedProduct);
				break;
			}
		}
	}
	
	public void selectStockOption(String dropdownOption) throws Throwable {
		selectDropDownByVisibleText_custom(custom_findElement(stockDropdown), dropdownOption, "stock");
	}
}
