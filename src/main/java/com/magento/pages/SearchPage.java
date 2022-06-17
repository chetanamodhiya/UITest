package com.magento.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class SearchPage extends TestBase{
	By activeSearch=By.xpath("//label[contains(@class,'active')]");
	By searchTextbox=By.id("search");
	By searchButton=By.xpath("//button[@type='submit' and @title='Search']");
	By searchMatch=By.xpath("//div[contains(@class,'product details')]//a");
	By searchNotMatch=By.xpath("//div[@class='message notice']");
	By sortBy=By.xpath("(//label[text()='Sort By'])[1]/..//select");
	By setDirection=By.xpath("(//a[contains(@class,'action sorter-action sort')])[1]/span");
	By viewAs=By.xpath("(//strong[contains(@class,'modes-mode active')])[1]/span");
	By viewAsLink=By.xpath("(//a[contains(@class,'modes-mode')])[1]/span");
	By results=By.xpath("(//p[text()=' Results'])[1]/span");
	By productItems=By.className("products list items product-items");
	
	// Validate Search option
	public void validateSearchOption(String search) throws InterruptedException {
		if(custom_findElements(activeSearch).size()>0) {
			Assert.assertTrue(true);
			sendKeys_custom(custom_findElement(searchTextbox), search, "Search textbox");
			waitForElementToBeClickable(custom_findElement(searchButton));
			click_javascript(custom_findElement(searchButton), "Search");
			// Validate the text searched
			By searchText=By.xpath("//span[text()=\"Search results for: '"+search+"'\"]");
			if(custom_findElement(searchText).isDisplayed()) {
				Assert.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.INFO, custom_findElement(searchText).getText());
			}
//			validate search results section
//			if(custom_findElement(productItems).isDisplayed()) {
//				Assert.assertTrue(true);
//				ExtentFactory.getInstance().getExtent().log(Status.INFO, "Products available as per your search");
//			}
//			Search operation yields results
			if(custom_findElements(searchMatch).size()>0) {
				List<WebElement> searchResults=custom_findElements(searchMatch);
				for(WebElement result:searchResults) {
					ExtentFactory.getInstance().getExtent().log(Status.INFO, "Search result : "+result.getText());
				}
			}
//			Search operation does not yield any results
			else if(custom_findElements(searchNotMatch).size()>0) {
				WebElement noResults=custom_findElement(searchNotMatch);
				ExtentFactory.getInstance().getExtent().log(Status.INFO, "Option is : "+noResults.getText());
			}
		}
	}
	
	//sort the value as per requirement
	public void sortResults(String value) throws Throwable {
		WebElement sortByOption = custom_findElement(sortBy);
		List<String> options = getOptionsAsArrayList(sortByOption);
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "We can sort results by: "+options);
		selectDropDownByVisibleText_custom(sortByOption,value,"Sort By");
	}
	
	//set direction ascending or descending 
	public void setDirections() throws InterruptedException  {
		click_javascript(custom_findElement(setDirection), custom_findElement(setDirection).getText());
		waitFor(2000);
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Now we can "+custom_findElement(setDirection).getText());
	}
	
	//view as Grid or List
		public void viewAsGridOrList()  {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "By Default View As "+custom_findElement(viewAs).getText());
			click_javascript(custom_findElement(viewAsLink), "View as "+custom_findElement(viewAsLink).getText());
			waitForElementToBeClickable(custom_findElement(viewAs));
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Now showing view as "+custom_findElement(viewAs).getText());
		}
		
	//view results on page
		public void viewresults()  {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, "Results  "+custom_findElement(results).getText());
		}
}
