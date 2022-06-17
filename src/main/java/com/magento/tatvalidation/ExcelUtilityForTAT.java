package com.magento.tatvalidation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.magento.pages.PDPProductOptionsPage;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.DriverFactory;
import com.magento.testBase.ExtentFactory;

public class ExcelUtilityForTAT {
	
	PDPProductOptionsPage pdp=new PDPProductOptionsPage();
	
	public static FileInputStream fis;
	
	//store TAT rules into map
	public Map<String, Map<String,String>> storeRunSizeAsMap(String filePath,String sheetName) throws EncryptedDocumentException, IOException {
		Map<String, Map<String,String>> runSizeMapWithTAT= new LinkedHashMap<String, Map<String,String>>();
		fis = new FileInputStream(System.getProperty("user.dir")+filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(sheetName);

		Map<String,String> tATMap=null;
		for(int i=1; i<=8; i++) {
			tATMap=new  LinkedHashMap<String,String>();
			for(int j=1;j<sheet.getRow(i).getLastCellNum();) {
				tATMap.put(getCellValueAsString(sheet.getRow(i).getCell(j)), getCellValueAsString(sheet.getRow(i).getCell(j+1)));
				j=j+2;
			}
			runSizeMapWithTAT.put(getCellValueAsString(sheet.getRow(i).getCell(0)), tATMap);
		}
		return runSizeMapWithTAT;
	}
	//store TAT values into map
	public Map<String,String> storeTATValuesAsMap(String filePath,String sheetName) throws EncryptedDocumentException, IOException {
		fis = new FileInputStream(System.getProperty("user.dir")+filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(sheetName);
		Map<String,String> tATMap= new LinkedHashMap<String,String>();
		for(int i=1;i<=8;i++) {
			for(int j=1;j<sheet.getRow(i).getLastCellNum();) {
				tATMap= new LinkedHashMap<String,String>();
				tATMap.put(getCellValueAsString(sheet.getRow(i).getCell(j)), getCellValueAsString(sheet.getRow(i).getCell(j+1)));
				j=j+2;
			}
		}
		return tATMap;
	}
	
	//store loyalty into the map
	public Map<String,String> storeLoyaltyAsMap(String filePath,String sheetName,String loyaltyLevel) throws EncryptedDocumentException, IOException {
		Map<String,String> loyaltyMap=new LinkedHashMap<String,String>();
		fis = new FileInputStream(System.getProperty("user.dir")+filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(sheetName);
		if(loyaltyLevel.equalsIgnoreCase("Associate Level")) {
			for(int i=14;i<=15;i++) {
				loyaltyMap.put(getCellValueAsString(sheet.getRow(i).getCell(0)), getCellValueAsString(sheet.getRow(i).getCell(1)));
			}
		}
		else if(loyaltyLevel.equalsIgnoreCase("Admiral Level")) {
			for(int i=18;i<=19;i++) {
				loyaltyMap.put(getCellValueAsString(sheet.getRow(i).getCell(0)), getCellValueAsString(sheet.getRow(i).getCell(1)));
			}
		}

		else if(loyaltyLevel.equalsIgnoreCase("President Level")) {
			for(int i=22;i<=23;i++) {
				loyaltyMap.put(getCellValueAsString(sheet.getRow(i).getCell(0)), getCellValueAsString(sheet.getRow(i).getCell(1)));
			}
		}
		return loyaltyMap;
	}
	
	//store runsizes into an ArrayList from the application
	public ArrayList<String> storeApplicationRunsizeIntoArray(List<WebElement> elements) {
		ArrayList<String> runSizeArrayList=new ArrayList<>();
		for(WebElement runsizeElement:elements) {
			runSizeArrayList.add(runsizeElement.getText());
		}
		return runSizeArrayList;
	}
	
	//store quantity into an ArrayList from the application
		public ArrayList<String> storeApplicationQuantityIntoArray(List<WebElement> elements) {
			ArrayList<String> runSizeArrayList=new ArrayList<>();
			for(WebElement runsizeElement:elements) {
				runSizeArrayList.add(runsizeElement.getAttribute("value"));
			}
			return runSizeArrayList;
		}
	
	public void validateTATPercentageForRunsize(String filePath,String sheetName,List<WebElement> elements,String loyaltyLevel) throws EncryptedDocumentException, NumberFormatException, IOException {
		for(Map.Entry m : storeRunSizeAsMap(filePath,sheetName).entrySet()){
			String s[]=m.getKey().toString().split("-");
			for(int i=0;i<s.length;i++) {
				if(storeApplicationRunsizeIntoArray(elements).contains(s[i])) {
					WebElement runsize = DriverFactory.getInstance().getDriver().findElement(By.xpath("//span[@class='runsize' and text()='"+s[i]+"']"));
					pdp.clickRunSize(s[i]); 
					//store TAT options into an ArrayList from the application
					ArrayList<String> tATList=new ArrayList<>();
					WebElement tAT = DriverFactory.getInstance().getDriver().findElement(By.xpath("//span[text()='Turnaround Time']/../../..//select"));
					Select s1=new Select(tAT);
					for(WebElement tATElement:s1.getOptions()) {
						tATList.add(tATElement.getText());
					}
					tATList.remove(0);

					Map<String, String> mapTATValue = (Map<String, String>) m.getValue();
					if(tATList.size()==mapTATValue.size()) {
							
							//capture the subtotal price from the application
							WebElement subTotal = DriverFactory.getInstance().getDriver().findElement(By.xpath("//span[text()='Subtotal:']/../..//span[@class='value']"));
							ExtentFactory.getInstance().getExtent().log(Status.INFO, "Subtotal Price is : "+subTotal.getText().trim());
							String subTotalPrice= subTotal.getText().trim().replace("$", "");

							if(subTotalPrice.contains(",")) {
								subTotalPrice=subTotalPrice.replace(",", "");
							}
							for(String str:tATList) {
								s1.selectByVisibleText(str);
								ExtentFactory.getInstance().getExtent().log(Status.INFO, "Selected TAT Option is : "+str);

								//capture the updated sub total price
								String subTotalPriceUpdated= subTotal.getText().trim().replace("$", "");
								if(subTotalPriceUpdated.contains(",")) {
									subTotalPriceUpdated=subTotalPriceUpdated.replace(",", "");
								}
								//Find out the % increase
								double perc = ((Double.parseDouble(subTotalPriceUpdated)-Double.parseDouble(subTotalPrice))/Double.parseDouble(subTotalPrice))*100;
								int percentage= (int) Math.round(perc);

								int value1=Integer.parseInt(storeLoyaltyAsMap(filePath,sheetName,loyaltyLevel).values().toArray()[0].toString());
								int value2=Integer.parseInt(storeLoyaltyAsMap(filePath,sheetName,loyaltyLevel).values().toArray()[1].toString());
								if(percentage==value1 || percentage==value2)
									ExtentFactory.getInstance().getExtent().log(Status.PASS, "TAT Rules passed for TurnAround Time and Percentage is : "+percentage);
								else {
									ExtentFactory.getInstance().getExtent().log(Status.FAIL, "TAT Rules passed for TurnAround Time and Percentage is : "+percentage);
									Assert.fail();
								}
								s1.selectByIndex(0);
							}
						}
					}
				}
			}
		}
	
	public void validateTATPercentageForQuantity(String filePath,String sheetName,List<WebElement> elements,String loyaltyLevel) throws EncryptedDocumentException, NumberFormatException, IOException {
		for(Map.Entry m : storeRunSizeAsMap(filePath,sheetName).entrySet()){
			String s[]=m.getKey().toString().split("-");
			for(int i=0;i<s.length;i++) {
				if(storeApplicationQuantityIntoArray(elements).contains(s[i])) {
					//store TAT options into an ArrayList from the application
					ArrayList<String> tATList=new ArrayList<>();
					WebElement tAT = DriverFactory.getInstance().getDriver().findElement(By.xpath("//span[text()='Turnaround Time']/../../..//select"));
					Select s1=new Select(tAT);
					for(WebElement tATElement:s1.getOptions()) {
						tATList.add(tATElement.getText());
					}
					tATList.remove(0);

					//validate the TAT % increase
					Map<String, String> mapTATValue = (Map<String, String>) m.getValue();
					if(tATList.size()==mapTATValue.size()) {
						//capture the subtotal price from the application
						WebElement subTotal = DriverFactory.getInstance().getDriver().findElement(By.xpath("//span[text()='Subtotal:']/../..//span[@class='value']"));
						ExtentFactory.getInstance().getExtent().log(Status.INFO, "Subtotal Price is : "+subTotal.getText().trim());
						String subTotalPrice= subTotal.getText().trim().replace("$", "");

						if(subTotalPrice.contains(",")) {
							subTotalPrice=subTotalPrice.replace(",", "");
						}
						for(String str:tATList) {
							s1.selectByVisibleText(str);
							ExtentFactory.getInstance().getExtent().log(Status.INFO, "Selected TAT Option is : "+str);

							//capture the updated sub total price
							String subTotalPriceUpdated= subTotal.getText().trim().replace("$", "");
							if(subTotalPriceUpdated.contains(",")) {
								subTotalPriceUpdated=subTotalPriceUpdated.replace(",", "");
							}
							//Find out the % increase
							double perc = ((Double.parseDouble(subTotalPriceUpdated)-Double.parseDouble(subTotalPrice))/Double.parseDouble(subTotalPrice))*100;
							int percentage= (int) Math.round(perc);

							int value1=Integer.parseInt(storeLoyaltyAsMap(filePath,sheetName,loyaltyLevel).values().toArray()[0].toString());
							int value2=Integer.parseInt(storeLoyaltyAsMap(filePath,sheetName,loyaltyLevel).values().toArray()[1].toString());
							if(percentage==value1 || percentage==value2)
								ExtentFactory.getInstance().getExtent().log(Status.PASS, "TAT Rules passed for TurnAround Time and Percentage is : "+percentage);
							else {
								ExtentFactory.getInstance().getExtent().log(Status.FAIL, "TAT Rules passed for TurnAround Time and Percentage is : "+percentage);
								Assert.fail();
							}
							s1.selectByIndex(0);
						}
					}
				}
			}
		}
	}

		public String getCellValueAsString(Cell cell) {
			String cellValue = null;
			switch (cell.getCellType()) {
			case NUMERIC:
				cellValue = String.valueOf(cell.getNumericCellValue());
				break;
			case STRING:
				cellValue = cell.getStringCellValue();
				break;
			case BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case FORMULA:
				cellValue= cell.getCellFormula();
			case BLANK:
				cellValue="BLANK";
			default:
				cellValue ="DEFAULT";
			}
			return cellValue;
		}

		public int getRowCount(String filePath, String sheetName) throws EncryptedDocumentException, IOException {
			fis = new FileInputStream(System.getProperty("user.dir")+filePath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);
			return s.getLastRowNum();
		}

		public Map<String, String> readExcelCellValueGivenTheKey(String filePath, String sheetName) throws EncryptedDocumentException, IOException {
			Map<String, String> sheetDataAsMap = new HashMap<String, String>();
			fis = new FileInputStream(System.getProperty("user.dir")+filePath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(sheetName);

			int rows = getRowCount(filePath, sheetName);
			for(int i=1; i<rows; i++) {
				sheetDataAsMap.put(getCellValueAsString(s.getRow(i).getCell(0)), getCellValueAsString(s.getRow(i).getCell(1)));
			}
			return sheetDataAsMap;
		}

		//	find whether sheet exists
		public boolean isSheetExists(String filePath, String sheetName) throws EncryptedDocumentException, IOException {

			fis = new FileInputStream(System.getProperty("user.dir")+filePath);
			Workbook wb = WorkbookFactory.create(fis);
			int index = wb.getSheetIndex(sheetName);

			if(index==-1) {
				index = wb.getSheetIndex(sheetName.toUpperCase());
				if(index==-1)
					return false;
				else
					return true;
			}
			else
				return true;

		}

		//	returns number of columns in a sheet
		public int getColumnCount(String filePath, String sheetName) throws EncryptedDocumentException, IOException {
			fis = new FileInputStream(System.getProperty("user.dir")+filePath);
			Workbook wb = WorkbookFactory.create(fis);

			//		check if sheet exists
			if(!isSheetExists(filePath, sheetName)) 
				return -1;

			Sheet s = wb.getSheet(sheetName);
			Row r = s.getRow(0);

			if(r == null)
				return 1;

			return r.getLastCellNum();
		}

		public String getCellData(String filePath, String sheetName,int rowNum, int colNum){
			try{
				if(rowNum <=0)
					return "";
				fis = new FileInputStream(System.getProperty("user.dir")+filePath);
				Workbook wb = WorkbookFactory.create(fis);
				Sheet sheet = wb.getSheet(sheetName);
				Row row = sheet.getRow(rowNum-1);
				if(row==null)
					return "";

				return getCellValueAsString(row.getCell(colNum));
			}
			catch(Exception e){

				e.printStackTrace();
				return "Row "+rowNum+" or Column "+colNum +" does not exist in the excel";
			}
		}
		
		
		public void writeToExcel(String fileName,String sheetName,ArrayList<String> arraylist) throws IOException {
			fis = new FileInputStream(System.getProperty("user.dir")+fileName);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sheet = wb.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();

			Row row = sheet.createRow(rowCount+1);
			short cellNum = row.getLastCellNum();
			for (String list : arraylist)
			{
				Cell cell = row.createCell(++cellNum);
				cell.setCellValue(list);
			}
			try
			{
				//Write the workbook in file system
				FileOutputStream out = new FileOutputStream(System.getProperty("user.dir")+fileName);
				wb.write(out);
				out.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			finally {
				wb.close();
			}
		}
}
