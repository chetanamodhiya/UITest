package com.magento.testScriptsForSearch;

import java.util.Map;

import org.testng.annotations.Test;
import com.magento.pages.BasePage;
import com.magento.pages.DirectMailingServicePage;
import com.magento.pages.EDDMAddressPage;
import com.magento.pages.PDPProductOptionsPage;
import com.magento.pages.PDPSetsAndShippingPage;
import com.magento.pages.SearchPage;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.TestBase;

public class ValidateSearchFunctionallity extends TestBase {
	ExcelUtility excelUtility = new ExcelUtility();
	BasePage bp = new BasePage();
	PDPProductOptionsPage pdp = new PDPProductOptionsPage();
	DirectMailingServicePage dm = new DirectMailingServicePage();
	EDDMAddressPage eDDM = new EDDMAddressPage();
	PDPSetsAndShippingPage set = new PDPSetsAndShippingPage();
	SearchPage search = new SearchPage();

	@Test()
	public void validateSearchResultPage() throws Throwable {
		Map<String,String> file=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		bp.clickSearchOption();
		search.validateSearchOption(file.get("Search"));
	}
	
	@Test(dependsOnMethods= {"validateSearchResultPage"})
	public void sortSearchResults() throws Throwable {
		Map<String,String> file=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));
		search.sortResults(file.get("SortResult By"));
		search.setDirections();
		search.viewAsGridOrList();
		search.viewresults();
	}
}
