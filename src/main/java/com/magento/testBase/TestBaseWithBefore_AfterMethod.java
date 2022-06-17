package com.magento.testBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.magento.reusableComponents.CommonUtility;
import com.magento.reusableComponents.PropertiesUtility;

/**
 * @author: Mohan N
 */
public class TestBaseWithBefore_AfterMethod extends CommonUtility {
	BrowserFactory bf = new BrowserFactory();
	
	@BeforeMethod
	public void launchApplication() throws Exception {
		String browser = PropertiesUtility.getPropertyValueByKey("browser");
		String url = 	PropertiesUtility.getPropertyValueByKey("appUrl");

		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));
		DriverFactory.getInstance().getDriver().manage().window().maximize();
		DriverFactory.getInstance().getDriver().navigate().to(url);
		DriverFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//		waitForElementToBeVisible(custom_findElement(By.xpath("//a[text()='Click Here']")));
	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.getInstance().closeBrowser();
	}
}
