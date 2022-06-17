package com.magento.smokeTestscript;

import org.testng.annotations.Test;
import com.magento.pages.BasePage;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.TestBase;

public class ValidateLogoutFunctionality extends TestBase{
BasePage bp = new BasePage();

	@Test(groups= {"smoke"})
	public void validateLogoutFunctionality() throws Throwable{
		navigateTo_custom(PropertiesUtility.getPropertyValueByKey("appUrl"));
		bp.logout();
	}
}