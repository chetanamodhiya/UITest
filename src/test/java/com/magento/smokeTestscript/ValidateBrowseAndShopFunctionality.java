package com.magento.smokeTestscript;

import java.io.IOException;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;
import com.magento.pages.BasePage;
import com.magento.pages.ProductsLandingPage;
import com.magento.reusableComponents.ExcelUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.TestBase;

public class ValidateBrowseAndShopFunctionality extends TestBase{
BasePage bp = new BasePage();
ExcelUtility excelUtility = new ExcelUtility();


	@Test()
	public void browseGivenProduct() throws InterruptedException, EncryptedDocumentException, IOException {
		Map<String,String> file=excelUtility.readExcelCellValueGivenTheKey(PropertiesUtility.getPropertyValueByKey("PlaceOrder_excelPath"), PropertiesUtility.getPropertyValueByKey("sheetForFiles"));

//		Browse Marketing Products > Postcards sub-category
		bp.validateBrowseFuntionality(file.get("Category Marketing Products"), file.get("Sub Category Postcards"));
	}
}