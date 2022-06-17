package com.magento.customerAccountTestScripts;
import org.testng.annotations.Test;
import com.magento.pages.BasePage;
import com.magento.pages.CustomerAccountPage;
import com.magento.testBase.TestBase;
import com.magento.reusableComponents.PropertiesUtility;

public class PreventCustomersFromBeingAbleToChangeEmailAddressCQA_268 extends TestBase{
	BasePage bp=new BasePage();
	CustomerAccountPage cap=new CustomerAccountPage();

	@Test()
	public void changeEmailAddressFunctionality() throws Throwable{
		navigateTo_custom(PropertiesUtility.getPropertyValueByKey("appUrl"));
		bp.clickAccountProfile();
		cap.validateEmailAddress();
	}
}
