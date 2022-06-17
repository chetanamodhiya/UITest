package com.magento.setsAndShippingTestScripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.magento.pages.BasePage;
import com.magento.pages.PDPProductOptionsPage;
import com.magento.pages.PDPSetsAndShippingPage;
import com.magento.pages.ProductsLandingPage;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.TestBase;

public class Project_ClientNameIsARequiredFieldCQA_267 extends TestBase{
	ExcelUtility excelUtility = new ExcelUtility();
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	PDPSetsAndShippingPage set=new PDPSetsAndShippingPage();
	BasePage bp=new BasePage();
	ProductsLandingPage plp=new ProductsLandingPage();
	
	@Test()
	public void project_ClientNameIsARequiredField() throws Throwable{
		Map<String,String> pdpOptionsAsMap=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForPDPPage"));
		Map<String,String> file=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));

//		Browse Marketing Products > Trading Cards sub-category
		bp.validateBrowseFuntionality(file.get("Category Marketing Products"), file.get("Sub Category Trading Cards"));
		plp.clickProductLink(file.get("Product 14pt Trading Cards"));
		pdp.clickStartOverLink("Start Over");
		pdp.selectSize(pdpOptionsAsMap.get("SIZE 2.5 x 3.5"));
		pdp.selectStock(pdpOptionsAsMap.get("STOCK 14PT C2S"));
		pdp.selectColorspec(pdpOptionsAsMap.get("COLORSPEC 4/1"));
		pdp.selectCoating(pdpOptionsAsMap.get("COATING UV Both Sides"));
		pdp.clickRunSize(pdpOptionsAsMap.get("RUNSIZE 1000"));
		pdp.selectTurnAroundTime(pdpOptionsAsMap.get("TURNAROUND TIME 2-4 Business Days"));
		pdp.clickProceedToShippingButton();
		set.validateUserIsNavigatedToSetsAndShippingPage();
		set.validateProjectOrClientNameIsMandatoryField();
	}
}
