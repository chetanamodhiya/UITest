package com.magento.pdptestScripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.magento.pages.BasePage;
import com.magento.pages.CustomerAccountPage;
import com.magento.pages.DirectMailingServicePage;
import com.magento.pages.EDDMAddressPage;
import com.magento.pages.PDPProductOptionsPage;
import com.magento.pages.PDPSetsAndShippingPage;
import com.magento.pages.SearchPage;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class ValidateSaveProjectForLaterFunctionality extends TestBase{
	ExcelUtility excelUtility = new ExcelUtility();
	BasePage bp = new BasePage();
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
	CustomerAccountPage account=new CustomerAccountPage();
	
	@Test()
	public void verifySaveProjectlink() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("SaveProjectExcel"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Product"));
		navigateTo_custom(pdpOptionsAsMap.get("ProductURL"));
		pdp.clickStartOverLink("Start Over");
		pdp.selectAnOptionInAllTheDropdowns();
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE"));
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Product"));
		set.verifySaveProjectForLaterLink();
}
	
	@Test()
	public void verifyFunctionalityOfSaveProjectlink() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("SaveProjectExcel"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Product"));
		navigateTo_custom(pdpOptionsAsMap.get("ProductURL"));
		pdp.clickStartOverLink("Start Over");
		pdp.selectAnOptionInAllTheDropdowns();
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Product"));
		set.verifySaveProjectForLaterLink();
		set.clickSaveProjectForLaterLink();
		pdp.verifySaveProjectForLaterMessage();
}
	
	@Test()
	public void verifytheUpdateSavedProjectdetailsfunctionality() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("SaveProjectExcel"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Product"));
		navigateTo_custom(pdpOptionsAsMap.get("ProductURL"));
		pdp.clickStartOverLink("Start Over");
		pdp.selectAnOptionInAllTheDropdowns();
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Product"));
		set.verifySaveProjectForLaterLink();
		set.clickSaveProjectForLaterLink();
		pdp.verifySaveProjectForLaterMessage();
		bp.clickMyAccountLinkFromTheHeader();
		account.clickSavedProjects();
		account.viewSavedProjects();
		account.editSavedProjects(pdpOptionsAsMap.get("Product"));
}
	
//	@Test()
	public void deleteSavedProject() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("SaveProjectExcel"), PropertiesUtility.getPropertyValueByKey("sheetName"));
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product : "+pdpOptionsAsMap.get("Product"));
		navigateTo_custom(pdpOptionsAsMap.get("ProductURL"));
		pdp.clickStartOverLink("Start Over");
		pdp.selectAnOptionInAllTheDropdowns();
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE"));
		pdp.selectSecondOptionFromTurnAroundTimeDropdown();
		pdp.clickProceedToShippingButton();
		set.enterProjectOrClientName(pdpOptionsAsMap.get("Product"));
		set.verifySaveProjectForLaterLink();
		set.clickSaveProjectForLaterLink();
		pdp.verifySaveProjectForLaterMessage();
		bp.clickMyAccountLinkFromTheHeader();
		account.clickSavedProjects();
		account.viewSavedProjects();
		account.deleteSavedProjects(pdpOptionsAsMap.get("Product"));
}
}
