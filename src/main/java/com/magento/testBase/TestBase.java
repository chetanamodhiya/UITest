package com.magento.testBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.magento.pages.BasePage;
import com.magento.reusableComponents.CommonUtility;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.PropertiesUtility;

/**
 * @author: Mohan N
 */
public class TestBase extends CommonUtility {
	BrowserFactory bf = new BrowserFactory();
	ExcelUtility excelUtility = new ExcelUtility();

	@BeforeTest
	public void launchApplication() throws Exception {
		String browser = PropertiesUtility.getPropertyValueByKey("browser");
		String url = 	PropertiesUtility.getPropertyValueByKey("appUrl");

		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));
		DriverFactory.getInstance().getDriver().manage().window().maximize();
		DriverFactory.getInstance().getDriver().navigate().to(url);
//		DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		waitFor(Integer.parseInt(PropertiesUtility.getPropertyValueByKey("waitForPageLoad")));
		custom_findElement(By.xpath("(//span[text()='Sign In'])[1]/..")).click();
		custom_findElement(By.id("login-dropdown-customer-email")).clear();
		custom_findElement(By.id("login-dropdown-customer-email")).sendKeys(PropertiesUtility.getPropertyValueByKey("defaultUserName"));
		custom_findElement(By.id("login-dropdown-pass")).clear();
		custom_findElement(By.id("login-dropdown-pass")).sendKeys(PropertiesUtility.getPropertyValueByKey("defaultPassword"));
		if(PropertiesUtility.getPropertyValueByKey("env").equals("printing")) {
			DriverFactory.getInstance().getDriver().switchTo().frame(DriverFactory.getInstance().getDriver().findElement(By.xpath("//iframe[@title='chat widget']")));
			if(custom_findElement(By.xpath("//button[@aria-label='Dismiss']")).isDisplayed()) {
				custom_findElement(By.xpath("//button[@aria-label='Dismiss']")).click();
			}
			DriverFactory.getInstance().getDriver().switchTo().defaultContent();
		}
		custom_findElement(By.xpath("(//span[text()='Sign In'])[2]/..")).click();
		Assert.assertTrue(custom_findElement(By.xpath("(//li[@class='customer-welcome']//button)[1]")).isDisplayed());

	}

	@AfterTest
	public void tearDown() {
		DriverFactory.getInstance().closeBrowser();
	}
}
