package com.magento.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.magento.reusableComponents.MathUtility;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.tatvalidation.ExcelUtilityForTAT;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class PDPProductOptionsPage extends TestBase{
	By saveProjectMessage=By.xpath("//div[@class='message message-success success']/div");
	By targetInMailboxDate= By.id("delivery_date");
	By selectSize=By.xpath("//span[text()='Size']/..//select");
	By shape=By.xpath("//span[text()='Shape']/..//div/div[@class='swatch-option image']/span[@class='option-label']");
	By radiusOfCorners=By.xpath("//span[text()='Radius of Corners']/..//div/div");
	By productOrientation=By.xpath("//span[text()='Product Orientation']/..//div[@class='swatch-option text']");
	By selectStock=By.xpath("//span[text()='Stock']/..//select");
	By selectColorSpec=By.xpath("//span[text()='Colorspec']/..//select");
	By selectWhiteMaskFront=By.xpath("//span[text()='White Mask Front']/..//select");
	By selectCoating=By.xpath("//span[text()='Coating']/..//select");
	By selectEaselBacks=By.xpath("//span[text()='Easel Backs']/..//select");
	By selectHardware=By.xpath("//span[text()='Hardware']/..//select");
	By selectHardwareAfterTAT=By.xpath("//span[text()='Hardware']/../..//select");
	By selectSpotUVSides=By.xpath("//span[text()='SPOT UV SIDES']/..//select");
	By selectScoringOptions=By.xpath("//span[text()='Scoring Options']/..//select");
	By selectOpeningSide=By.xpath("//span[text()='Opening Side']/..//select");
	By selectWindowOptions=By.xpath("//span[text()='Window Options']/..//select");
	By selectVariableData=By.xpath("//span[text()='Variable Data']/..//select");
	By selectDrillHole=By.xpath("//span[text()='Drill Hole']/..//select");
	By selectBundlingService=By.xpath("//span[text()='Bundling Service']/..//select");
	By selectShrinkWrapping=By.xpath("//span[text()='Shrink Wrapping']/..//select");
	By selectFinishOption=By.xpath("//span[text()='Finish Option']/../..//select");
	By selectFinishOptions=By.xpath("//span[text()='Finish Options']/../..//select");
	By selectLamination=By.xpath("//span[text()='Lamination']/..//select");
	By selectUnwindOptions=By.xpath("//span[text()='Unwind Options']/..//select");
	By selectFoilColor=By.xpath("//span[text()='Foil Color']/..//select");
	By selectEdgeColor=By.xpath("//span[text()='Edge Color']/..//select");
	By selectProductStyle=By.xpath("//span[text()='Product Style']/..//select");
	By selectProductColor=By.xpath("//span[text()='Product Color']/..//select");
	By selectProductMaterial=By.xpath("//span[text()='Product Material']/..//select");
	By selectShirtSize=By.xpath("//span[text()='Shirt Size (Multi-Picker)']/..//select");
	By selectEDDMServiceOption=By.xpath("//span[text()='EDDM Service Option']/..//select");
	By selectRunSize=By.xpath("//li [contains(@class,'runsizes_row')]/span[1]");
	By markUpPrice=By.xpath("//li [contains(@class,'runsizes_row')]/span[4]");
	By quantity=By.xpath("//span[text()='Markup Price']/../../..//li[2]//span[1]/input");
	By selectWidth=By.xpath("//span[text()='Width']/../../..//select[@name='dynamic_size[width]']");
	By selectHeight=By.xpath("//span[text()='Height']/../../..//select[@name='dynamic_size[height]']");
	By addQuantity=By.xpath("//span[text()='Markup Price']/../../..//li[2]//span[1]/input");
	By selectTurnAroundTime=By.xpath("//span[text()='Turnaround Time']/../../following-sibling::div/select");
	By selectHandleOptions=By.xpath("//span[text()='Handle Options']/..//select");
	By selectFastenerOptions=By.xpath("//span[text()='Fastener Options']/..//select");
	By selectEdgeJoinOptions=By.xpath("//span[text()='Edge Join Options']/..//select");
	By selectBusinessCardBoxSize=By.xpath("//span[text()='Business Card Box Size']/..//select");
	By selectPolePockets=By.xpath("//span[text()='Pole Pockets']/../..//select");
	By selectContourCut= By.xpath("//span[text()='Contour Cut']/../..//select");
	By selectDrillholeandHardware=By.xpath("//span[text()='Drill hole and Hardware']/../..//select");
	By selectRoundCorners=By.xpath("//span[text()='Round Corners']/../..//select");
	By selectThreadColor=By.xpath("//span[text()='Thread Color']/../..//select");
	By selectHems=By.xpath("//span[text()='Hems']/../..//select");
	By selectGrommets=By.xpath("//span[text()='Grommets']/../..//select");
	By estimateShipping=By.xpath("(//div[@class='block-title'])[2]");
	By zipCode=By.id("product_shipping_postcode");
	By updateButton=By.xpath("//input[@class='action quaternary btn-small']");
	By proceedToShippingButton=By.xpath("//button[@class='proceed-to-shipping']");
	By sets_ShippingLink=By.xpath("(//a[@class='data switch'])[2]");
	By selectFoldingOptions=By.xpath("//span[text()='Folding Options']/..//select");
	By selectScoreAndFold=By.xpath("//span[text()='Score and Fold']/..//select");
	By selectMailingService=By.xpath("//span[text()='Mailing Service']/..//select");
	By selectNumberOfTabs=By.xpath("//span[text()='Number of Tabs']/..//select");
	By selectPostageClass=By.xpath("//span[text()='Postage Class']/../..//select");
    By projectNameText=By.id("project_name");
	By subTotalPrice=By.xpath("//span[text()='Subtotal:']/../..//span[2]");
	By startOverLink=By.xpath("//a[@class='action start-over']");
	By showMoreRunSize=By.xpath("//span[text()='Show More']");
	By selectPrintMethod=By.xpath("//select[@class='swatch-select print_method']");
	By selectBlankEnvelopes=By.xpath("//select[@class='swatch-select blank_envelopes']");
	By selectBlankSecondSheets=By.xpath("//select[@class='swatch-select blank_second_sheets']");
	By homeLink=By.linkText("Home");
	By allDropdowns=By.xpath("//div[@id='product-options-wrapper']//select");
	By firstDropdown=By.xpath("(//div[@id='product-options-wrapper']//select)[1]");
	By spinner=By.xpath("//div[@class='spinner']/..");
	By targetDatecalendar=By.id("ui-datepicker-div");
	By enabledDate=By.xpath("//td[@data-handler='selectDay']//a");
	By nextInCalendar=By.xpath("//a[@data-handler='next']");
	By delivery_date=By.xpath("//input[@id='delivery_date']");
	By usernameText=By.xpath("(//span[text()='Email']/../..//input[@name='username'])[2]");
	By passwordText=By.xpath("(//span[text()='Password']/../..//input[@name='password'])[2]");
	By SignInButton=By.xpath("(//button[@type='submit'])[3]");
	By pdfProofsCheckbox=By.xpath("//span[contains(text(),'PDF Proofs (per set)')]/../..//input[@type='checkbox']");
	By sampleOfCompletedJobCheckbox=By.xpath("//span[contains(text(),'Sample of Completed job')]/../..//input[@type='checkbox']");
	By shippingMethod=By.xpath("//div[@class='shipping-estimation_rates']//span"); 
	By selectPerforation=By.xpath("//span[text()='Perforation']/..//select");
	By selectNumbering=By.xpath("//span[text()='Numbering']/..//select");
	By pdfProofsPrice=By.xpath("//span[contains(text(),'PDF Proofs')]/..//span[@class='price-container tax']//span");
	By pdfProofsText=By.xpath("//span[contains(text(),'PDF Proofs (per set)')]");
	By sampleOfCompletedJobPrice=By.xpath("//span[contains(text(),'Sample of Completed job')]/..//span[@class='price-container tax']//span");
	By sampleOfCompletedJoText=By.xpath("//span[contains(text(),'Sample of Completed job (per set)')]");
	
	
	//enter Zip/Postal Code
	public void enterZip_PostalCode(String zipcode) {
		sendKeys_custom(custom_findElement(zipCode), zipcode, zipcode);
	}
	
	//validate Shipping Methods in the Estimate Shipping(for 1 set) 
		public void validateMethodsInEstimateShipping() {
			click_custom(custom_findElement(zipCode), "Zipcode");
			if(custom_findElements(shippingMethod).size()==1) {
				ExtentFactory.getInstance().getExtent().log(Status.PASS,getText_custom(custom_findElement(shippingMethod))+ " is displayed");
			}
			else {
				List<WebElement> methods = custom_findElements(shippingMethod);
				for(int i=2;i<methods.size();i++) {
					ExtentFactory.getInstance().getExtent().log(Status.PASS,getText_custom(custom_findElements(shippingMethod).get(i)));
				}
			}
		}
	
	//click Pdf Proofs checkbox 
	public void selectPDFProofsCheckbox() {
		if(!(custom_findElement(pdfProofsCheckbox).isSelected())) 
			click_javascript(custom_findElement(pdfProofsCheckbox), getText_custom(custom_findElement(pdfProofsText)));
	}
	
	//click Pdf Proofs checkbox 
	public String validatePDFProofs() {
		String text="";
		selectPDFProofsCheckbox();
		for(WebElement pdfProofs:custom_findElements(pdfProofsText)) {
			text=getText_custom(pdfProofs)+text;
		}
		return text;
	}
	
	//click Sample of Completed job checkbox
	public void selectSampleOfCompletedJobCheckbox() {
		if(!(custom_findElement(sampleOfCompletedJobCheckbox).isSelected())) 
			click_javascript(custom_findElement(sampleOfCompletedJobCheckbox), getText_custom(custom_findElement(sampleOfCompletedJoText)));
	}
	
	//check Sample of Completed job is displayed
	public void iSSampleOfCompletedJobDisplayed() {
		if(custom_findElement(sampleOfCompletedJoText).isDisplayed()) { 
			Assert.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, getText_custom(custom_findElement(sampleOfCompletedJoText))+ " is displayed");
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Job Samples is not displayed");
			Assert.fail();
		}
	}
	
	//verify save project for later message
	public void verifySaveProjectForLaterMessage() {
		if(custom_findElements(saveProjectMessage).size()>0) {
			Assert.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS,"User see the success message "+getText_custom(custom_findElement(saveProjectMessage))+ " is displayed");
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,"User do not able to see the success message ");
			Assert.fail();
		}
	}
	
	//click shape box
	public void clickShapeBox() {
		if(custom_findElements(shape).size()>0) {
			click_custom(custom_findElement(shape), getText_custom(custom_findElement(shape)));
		}
	}
	
	//click radius of corner
	public void clickRadiusOfCorners() {
		if(custom_findElements(radiusOfCorners).size()>0) {
			click_custom(custom_findElement(radiusOfCorners), getText_custom(custom_findElement(radiusOfCorners)));
		}
	}
	
	//select runsize or quantity 
	public void selectRunsizeOrQuantity(Map<String,String> user) throws EncryptedDocumentException, NumberFormatException, IOException {
		ExcelUtilityForTAT excelUtility=new ExcelUtilityForTAT();
		if(custom_findElements(selectRunSize).size()>0) {
			if(custom_findElement(showMoreRunSize).isDisplayed()) 
				click_custom(custom_findElement(showMoreRunSize), "Show More Link");	
			List<WebElement> runsizeList=custom_findElements(selectRunSize);
			excelUtility.validateTATPercentageForRunsize(PropertiesUtility.getPropertyValueByKey("pageInteractionExcel"), PropertiesUtility.getPropertyValueByKey("sheetForTAT"),runsizeList,user.get("Loyalty"));
		}
		else {
			List<WebElement> quantityText = custom_findElements(addQuantity);
			sendKeys_custom(custom_findElement(addQuantity), "2", "Quantity");
			excelUtility.validateTATPercentageForQuantity(PropertiesUtility.getPropertyValueByKey("pageInteractionExcel"), PropertiesUtility.getPropertyValueByKey("sheetForTAT"),quantityText,user.get("Loyalty"));
		}
	}
	
	//for select all dropdown except TAT
	public void selectAnOptionInAllTheDropdownsExceptTAT() throws InterruptedException, EncryptedDocumentException, IOException {
		List<WebElement> dropdownList=custom_findElements(allDropdowns);
		for(WebElement dropdown:dropdownList) {
			if(dropdown.getAttribute("name").equalsIgnoreCase("turnaround_time")) {
				continue;
			}
			if(dropdown.isDisplayed()) {
				Select dropdownMenu = new Select(dropdown);
				List<WebElement> allOptions = getOptions(dropdownMenu);
				for(int i=1;i<allOptions.size();i++) {
					if(allOptions.get(i).isEnabled())
					{
						dropdownMenu.selectByVisibleText(allOptions.get(i).getText());
						ExtentFactory.getInstance().getExtent().log(Status.INFO,getText_custom(allOptions.get(i)));
						break;
					}
				}
			}
		}
	}
	
	public void tATValidationWithSizeAndRunsize_M2_3397_1(String product,String quantityText,SoftAssert sa) throws Throwable {
		int counter=1;
		//iterate through the 'Size' dropdown
		for(String size:getOptionsAsArrayList(custom_findElement(selectSize))) {
			clickStartOverLink("Start Over");
			selectSize(size);
			List<String> runsizeOrQuantityOptions=new ArrayList<>();
			if(custom_findElements(selectRunSize).size()>0) {
				if(counter==1 &&custom_findElement(showMoreRunSize).isDisplayed()) {
					click_custom(custom_findElement(showMoreRunSize), "Show More Link");
					counter++;
				}
				for(WebElement runsize:custom_findElements(selectRunSize)) {
					runsizeOrQuantityOptions.add(runsize.getText());
				}
			}
			else if(custom_findElements(quantity).size()>0) {
				runsizeOrQuantityOptions.add(quantityText);
			}
			for(String runsizeOrQuantity:runsizeOrQuantityOptions) {
				selectRunsizeOrQuantity(runsizeOrQuantity);
				
				/*
				 *	EndurAce: Only 527
					Packaging Boxes: Only 527 for all QTY
					Offset Envelopes: 8.875” x 3.875” #9 envelope should be 5-7 (527) days for all quantities
				 * 
				 */
				if((product.contains("Boxes") || product.contains("EndurACE") || (product.contains("Offset Envelopes") && size.equalsIgnoreCase("3.875\" x 8.875\"")))
						&& getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).size()==1
						&& getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("5-7 Business Days")) {
					sa.assertTrue(true);
					ExtentFactory.getInstance().getExtent().log(Status.PASS, product+ " > "+runsizeOrQuantity+" contains 5-7 Business Days");
				}
				/*
				 * No ND for offset presentation folders over 5,000 qty
				 * No ND for door hangers over 5,000 qty
				 * No ND for Premium Products (Linen, Magnet, Pearl, Brown Kraft, Natural, and Plastic), any qty
				 * No ND for Majestic products, any qty
				 * No ND for all offset envelopes, any qty
				 * No ND for all booklets, any qty
				 * No ND for all catalogs, any qty
				 * No ND for any NCR, any qty
				 * No ND for any notepads, any qty
				 * No ND for any calendars, any qty
				 * No ND for event tickets, any qty
				 * No ND for table tents, any qty
				 * No ND for any promo item, any qty
				 */
				else if(((product.contains("Offset Envelopes") && (!(size.equalsIgnoreCase("3.875\" x 8.875\""))))  
						|| (product.contains("Door Hangers") && MathUtility.parseStringToInteger(runsizeOrQuantity)>5000) 
						|| (product.contains("Presentation Folders") && MathUtility.parseStringToInteger(runsizeOrQuantity)>5000) 
						|| product.contains("Magnets") || product.contains("Notepads") || product.contains("Booklets") || product.contains("Catalogs") || product.contains("NCR")
						|| product.contains("Brown Kraft") || product.contains("Calendars") || product.contains("Event Tickets") || product.contains("Table Tent")
						|| product.contains("Buttons") || product.contains("Linen") || product.contains("Plastic"))
						&& (!getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("Next Business Day"))) {
					sa.assertTrue(true);
					ExtentFactory.getInstance().getExtent().log(Status.PASS, product+ " > "+runsizeOrQuantity+" does not contain Next Business Day");
				}
//				 Shrink Wrap: 5-7 days for 10K+
				else if(custom_findElements(selectShrinkWrapping).size()>0 && custom_findElement(selectShrinkWrapping).isDisplayed() && MathUtility.parseStringToInteger(runsizeOrQuantity)>10000
						&& getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("5-7 Business Days")) {
					sa.assertTrue(true);
					ExtentFactory.getInstance().getExtent().log(Status.PASS, product+ " > "+runsizeOrQuantity+" contains 5-7 Business Days");
				}
//				 Shrink Wrap: 2-4 days for up to 10K
				else if(custom_findElements(selectShrinkWrapping).size()>0 && custom_findElement(selectShrinkWrapping).isDisplayed() && MathUtility.parseStringToInteger(runsizeOrQuantity)<=10000
						&& getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("2-4 Business Days")) {
					sa.assertTrue(true);
					ExtentFactory.getInstance().getExtent().log(Status.PASS, product+ " > "+runsizeOrQuantity+" contains 2-4 Business Days");
				}
				else {
					ExtentFactory.getInstance().getExtent().log(Status.FAIL, product+ " > "+runsizeOrQuantity+" TAT did not match as expected");
					sa.fail();
				}
			}
		}
	}
	
	public void tATValidationWithSizeAndRunsize_M2_3397(String product,String quantityText,SoftAssert sa) throws Throwable {
		int counter=1;
		//iterate through the 'Size' dropdown
		for(String size:getOptionsAsArrayList(custom_findElement(selectSize))) {
			clickStartOverLink("Start Over");
			selectSize(size);
			List<String> runsizeOrQuantityOptions=new ArrayList<>();
			if(custom_findElements(selectRunSize).size()>0) {
				if(counter==1 && custom_findElements(showMoreRunSize).size()>0 && custom_findElement(showMoreRunSize).isDisplayed()) {
					click_custom(custom_findElement(showMoreRunSize), "Show More Link");
					counter++;
				}
				for(WebElement runsize:custom_findElements(selectRunSize)) {
					runsizeOrQuantityOptions.add(runsize.getText());
				}
			}
			else if(custom_findElements(quantity).size()>0) {
				runsizeOrQuantityOptions.add(quantityText);
			}
			for(String runsizeOrQuantity:runsizeOrQuantityOptions) {
				selectRunsizeOrQuantity(runsizeOrQuantity);
				/*
				 *	EndurAce: Only 527
					Packaging Boxes: Only 527 for all QTY
					Offset Envelopes: 8.875” x 3.875” #9 envelope should be 5-7 (527) days for all quantities
				 * 
				 */
				if(product.contains("Boxes") || product.contains("EndurACE") || (product.contains("Offset Envelopes") && size.equalsIgnoreCase("3.875\" x 8.875\"")))
				{
					if(getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).size()==1
						&& getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("5-7 Business Days")){	
						sa.assertTrue(true);
						ExtentFactory.getInstance().getExtent().log(Status.PASS, product+ " > "+runsizeOrQuantity+" contains 5-7 Business Days");
					} else {
						sa.assertTrue(false);
						ExtentFactory.getInstance().getExtent().log(Status.FAIL, product+ " > "+runsizeOrQuantity+" does not contains 5-7 Business Days");
					}
				}
				else if((product.contains("Door Hangers") && MathUtility.parseStringToInteger(runsizeOrQuantity)<=5000) 
						|| (product.contains("Presentation Folder") && MathUtility.parseStringToInteger(runsizeOrQuantity)<=5000)
						|| (product.contains("16PT Postcards") && custom_findElements(selectShrinkWrapping).size()==0)
						|| (product.contains("Announcement Cards") && custom_findElements(selectShrinkWrapping).size()==0)
						|| (product.contains("Common Sell Sheets") && custom_findElements(selectShrinkWrapping).size()==0)){
					continue;
				}
				/*
				 * No ND for offset presentation folders over 5,000 qty
				 * No ND for door hangers over 5,000 qty
				 * No ND for Premium Products (Linen, Magnet, Pearl, Brown Kraft, Natural, and Plastic), any qty
				 * No ND for Majestic products, any qty
				 * No ND for all offset envelopes, any qty
				 * No ND for all booklets, any qty
				 * No ND for all catalogs, any qty
				 * No ND for any NCR, any qty
				 * No ND for any notepads, any qty
				 * No ND for any calendars, any qty
				 * No ND for event tickets, any qty
				 * No ND for table tents, any qty
				 * No ND for any promo item, any qty
				 */
				else if((product.contains("Offset Envelopes") && (!(size.equalsIgnoreCase("3.875\" x 8.875\""))))
						|| (product.contains("Door Hangers") && MathUtility.parseStringToInteger(runsizeOrQuantity)>5000) 
						|| (product.contains("Presentation Folder") && MathUtility.parseStringToInteger(runsizeOrQuantity)>5000) 
						|| product.contains("Magnets") || product.contains("Notepads") || product.contains("Booklets") || product.contains("Catalogs") || product.contains("NCR")
						|| product.contains("Majestic product") || product.contains("Calendars") || product.contains("Event Tickets") || product.contains("Table Tent")
						|| product.contains("Promo product") || product.contains("Linen") || product.contains("Plastic")) {
					if(!getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("Next Business Day")){
						sa.assertTrue(true);
						ExtentFactory.getInstance().getExtent().log(Status.PASS, product+ " > "+runsizeOrQuantity+" does not contain Next Business Day");
					} else if(getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("Next Business Day")){
						sa.assertTrue(false);
						ExtentFactory.getInstance().getExtent().log(Status.	FAIL, product+ " > "+runsizeOrQuantity+" contains Next Business Day");
					}
				}
				/*
				 Shrink Wrap: 5-7 days for runsize > 10K
				 Shrink Wrap: 2-4 days for runsize <= 10K
				 */
				else if(custom_findElements(selectShrinkWrapping).size()>0 && custom_findElement(selectShrinkWrapping).isDisplayed()){
					if(MathUtility.parseStringToInteger(runsizeOrQuantity)>10000
						&& getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("5-7 Business Days")) {
						sa.assertTrue(true);
						ExtentFactory.getInstance().getExtent().log(Status.PASS, product+ " > "+runsizeOrQuantity+" contains 5-7 Business Days");
					} else if(MathUtility.parseStringToInteger(runsizeOrQuantity)<=10000
						&& getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("2-4 Business Days")) {
						sa.assertTrue(true);
						ExtentFactory.getInstance().getExtent().log(Status.PASS, product+ " > "+runsizeOrQuantity+" contains 2-4 Business Days");
					}
					else {
						sa.assertTrue(false);
						ExtentFactory.getInstance().getExtent().log(Status.	FAIL, product+ " > "+runsizeOrQuantity+" contains "+custom_findElement(selectTurnAroundTime).getText());
					}
				}
				else {
					ExtentFactory.getInstance().getExtent().log(Status.FAIL, product+ " > "+runsizeOrQuantity+" TAT did not match as Expected");
					sa.fail();
				}
			}
		}
	}
	
	public void tATValidationWithAllOptionsAndRunsize_M2_3397(String product,String quantityText,SoftAssert sa) throws Throwable {
		int counter=1;
		//iterate through the 'Size' dropdown
		for(String size:getOptionsAsArrayList(custom_findElement(selectSize))) {
			clickStartOverLink("Start Over");
			selectSize(size);
			if(custom_findElements(selectStock).size()>0) {
				for(String stock:getOptionsAsArrayList(custom_findElement(selectStock))) {
					selectStock(stock);
					if(custom_findElements(selectColorSpec).size()>0) {
						for(String colorspec:getOptionsAsArrayList(custom_findElement(selectColorSpec))) {
							selectColorspec(colorspec);
							if(custom_findElements(selectCoating).size()>0) {
								for(String coating:getOptionsAsArrayList(custom_findElement(selectCoating))) {
									selectCoating(coating);
									List<String> runsizeOrQuantityOptions=new ArrayList<>();
									if(custom_findElements(selectRunSize).size()>0) {
										if(counter==1 && custom_findElements(showMoreRunSize).size()>0 && custom_findElement(showMoreRunSize).isDisplayed()) {
											click_custom(custom_findElement(showMoreRunSize), "Show More Link");
											counter++;
										}
										for(WebElement runsize:custom_findElements(selectRunSize)) {
											runsizeOrQuantityOptions.add(runsize.getText());
										}
									}
									else if(custom_findElements(quantity).size()>0) {
										runsizeOrQuantityOptions.add(quantityText);
									}
									for(String runsizeOrQuantity:runsizeOrQuantityOptions) {
										selectRunsizeOrQuantity(runsizeOrQuantity);
										/*
										 *	EndurAce: Only 527
					Packaging Boxes: Only 527 for all QTY
					Offset Envelopes: 8.875” x 3.875” #9 envelope should be 5-7 (527) days for all quantities
										 * 
										 */
										if(product.contains("Boxes") || product.contains("EndurACE") || (product.contains("Offset Envelopes") && size.equalsIgnoreCase("3.875\" x 8.875\"")))
										{
											if(getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).size()==1
													&& getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("5-7 Business Days")){	
												sa.assertTrue(true);
												ExtentFactory.getInstance().getExtent().log(Status.PASS, product+ " > "+runsizeOrQuantity+" contains 5-7 Business Days");
											} else {
												sa.assertTrue(false);
												ExtentFactory.getInstance().getExtent().log(Status.FAIL, product+ " > "+runsizeOrQuantity+" does not contains 5-7 Business Days");
											}
										}
										else if((product.contains("Door Hangers") && MathUtility.parseStringToInteger(runsizeOrQuantity)<=5000) 
												|| (product.contains("Presentation Folder") && MathUtility.parseStringToInteger(runsizeOrQuantity)<=5000)
												|| (product.contains("16PT Postcards") && custom_findElements(selectShrinkWrapping).size()==0)
												|| (product.contains("Announcement Cards") && custom_findElements(selectShrinkWrapping).size()==0)
												|| (product.contains("Common Sell Sheets") && custom_findElements(selectShrinkWrapping).size()==0)){
											continue;
										}
										/*
										 * No ND for offset presentation folders over 5,000 qty
										 * No ND for door hangers over 5,000 qty
										 * No ND for Premium Products (Linen, Magnet, Pearl, Brown Kraft, Natural, and Plastic), any qty
										 * No ND for Majestic products, any qty
										 * No ND for all offset envelopes, any qty
										 * No ND for all booklets, any qty
										 * No ND for all catalogs, any qty
										 * No ND for any NCR, any qty
										 * No ND for any notepads, any qty
										 * No ND for any calendars, any qty
										 * No ND for event tickets, any qty
										 * No ND for table tents, any qty
										 * No ND for any promo item, any qty
										 */
										else if((product.contains("Offset Envelopes") && (!(size.equalsIgnoreCase("3.875\" x 8.875\""))))
												|| (product.contains("Door Hangers") && MathUtility.parseStringToInteger(runsizeOrQuantity)>5000) 
												|| (product.contains("Presentation Folder") && MathUtility.parseStringToInteger(runsizeOrQuantity)>5000) 
												|| product.contains("Magnets") || product.contains("Notepads") || product.contains("Booklets") || product.contains("Catalogs") || product.contains("NCR")
												|| product.contains("Majestic product") || product.contains("Calendars") || product.contains("Event Tickets") || product.contains("Table Tent")
												|| product.contains("Promo product") || product.contains("Linen") || product.contains("Plastic")) {
											if(!getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("Next Business Day")){
												sa.assertTrue(true);
												ExtentFactory.getInstance().getExtent().log(Status.PASS, product+ " > "+runsizeOrQuantity+" does not contain Next Business Day");
											} else if(getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("Next Business Day")){
												sa.assertTrue(false);
												ExtentFactory.getInstance().getExtent().log(Status.	FAIL, product+ " > "+runsizeOrQuantity+" contains Next Business Day");
											}
										}
										/*
				 							Shrink Wrap: 5-7 days for runsize > 10K
				 							Shrink Wrap: 2-4 days for runsize <= 10K
										 */
										else if(custom_findElements(selectShrinkWrapping).size()>0 && custom_findElement(selectShrinkWrapping).isDisplayed()){
											if(MathUtility.parseStringToInteger(runsizeOrQuantity)>10000
													&& getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("5-7 Business Days")) {
												sa.assertTrue(true);
												ExtentFactory.getInstance().getExtent().log(Status.PASS, product+ " > "+runsizeOrQuantity+" contains 5-7 Business Days");
											} else if(MathUtility.parseStringToInteger(runsizeOrQuantity)<=10000
													&& getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("2-4 Business Days")) {
												sa.assertTrue(true);
												ExtentFactory.getInstance().getExtent().log(Status.PASS, product+ " > "+runsizeOrQuantity+" contains 2-4 Business Days");
											}
											else {
												sa.assertTrue(false);
												ExtentFactory.getInstance().getExtent().log(Status.	FAIL, product+ " > "+runsizeOrQuantity+" contains "+custom_findElement(selectTurnAroundTime).getText());
											}
										}
										else {
											ExtentFactory.getInstance().getExtent().log(Status.FAIL, product+ " > "+runsizeOrQuantity+" TAT did not match as Expected");
											sa.fail();
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

		//for select all dropdown 
		public void selectAnOptionInAllTheDropdowns() throws InterruptedException, EncryptedDocumentException, IOException {
			List<WebElement> dropdownList=custom_findElements(allDropdowns);
			for(WebElement dropdown:dropdownList) {
				if(dropdown.isDisplayed()) {
					Select dropdownMenu = new Select(dropdown);
					List<WebElement> allOptions = getOptions(dropdownMenu);
					for(int i=1;i<allOptions.size();i++) {
						if(allOptions.get(i).isEnabled())
						{
							waitFor(500);
							dropdownMenu.selectByVisibleText(allOptions.get(i).getText());
							ExtentFactory.getInstance().getExtent().log(Status.INFO,getText_custom(allOptions.get(i)));
							break;
						}
					}
				}
			}
		}
	
	
	
	//types email into the email textbox field in the header 
		public void enterEmail(String emailText) {
			sendKeys_custom(custom_findElement(usernameText), emailText, "email");
		}

		//types password into the password textbox field in the header
		public void enterPassword(String passText) {
			sendKeys_custom(custom_findElement(passwordText), passText, "password");
		}

		//click sign in button in the login form from the header 
		public void clickSignInButton() {
			click_custom(custom_findElement(SignInButton), "sign in button");
		}

	//login in PDP Page
		public void loginInFromPDPPage(String emailText, String passwordText) {
			if(custom_findElements(spinner).size()>0) {
				waitForElementToBeInVisible(custom_findElements(spinner));
			}
			if(custom_findElements(usernameText).size()>0) {
				enterEmail(emailText);
				enterPassword(passwordText);
				clickSignInButton();
			}
		}
	
	//for enter Target in Mailbox Date from the Calender 
	public void clickCalendarDateField() throws InterruptedException {
		waitForElementToBeInVisible(custom_findElements(spinner));
		waitFor(500);
		click_custom(custom_findElement(targetInMailboxDate), "Target in Mailbox Date Textbox");
	}
	
	//for check Target in Mailbox Date displayed in page
	public boolean validatenMailboxDateCalendar(SoftAssert sa) throws InterruptedException {
		if(custom_findElements(targetInMailboxDate).size()>0) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Mailbox is displayed");
			sa.assertFalse(false);
			return false;
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Mailbox is not displayed");
			sa.assertTrue(true);
			return true;
		}
	}
	
	//select date which is enabled from calender 
	public void selectDateFromCalendar() {
		if(custom_findElements(enabledDate).size()>0) {
			String dateToBeSelected = getText_custom(custom_findElement(enabledDate));
			click_custom(custom_findElement(enabledDate), dateToBeSelected);
		}
		else {
			click_custom(custom_findElement(nextInCalendar),"Next Month");
			selectDateFromCalendar();
		}
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "selected delivery_date ==>"+custom_findElement(delivery_date).getAttribute("value"));
	}
		
	// click shape in Shape 
	public void clickShapeBox(String shapeName) throws InterruptedException {
		for (WebElement elementShape : custom_findElements(shape)) {
			if (elementShape.isDisplayed() && elementShape.isEnabled() && getText_custom(elementShape).equalsIgnoreCase(shapeName)) {
				click_custom(elementShape, shapeName);
				break;
			}
		}
	}

			//for Click Radius Of Corners in Radius Of Corners Menu
			public void clickRadiusOfCorners(String radiusOfCornersnName) {
				for(WebElement elementRadius:custom_findElements(radiusOfCorners)) {
					if(elementRadius.isDisplayed()) {
					String radiusOfCornersText = elementRadius.getText();
					if(radiusOfCornersText.equalsIgnoreCase(radiusOfCornersnName))
					{
						click_custom(elementRadius, radiusOfCornersnName);
						break;
					}
				}
				}
			}
			//for Click Product Orientation in Product Orientation Menu
			public void clickProductOrientation(String productOrientationName) {
				for(WebElement elementOrientation:custom_findElements(productOrientation)) {
					if(elementOrientation.isDisplayed()) {
					String productOrientationText = getText_custom(elementOrientation);
					
					if(productOrientationText.equalsIgnoreCase(productOrientationName))
					{
						click_javascript(elementOrientation, productOrientationName);
						break;
					}
				}
			}		
		 }
	
	//Select Size in Size DropDown Menu 
	public void selectSize(String size) throws Throwable {
		List<WebElement> sizeDropdownList=custom_findElements(selectSize);
		if(sizeDropdownList.size()>0) {
			for(WebElement elementSize : sizeDropdownList) {
				waitForElementToBeClickable(elementSize);
				if(elementSize.isDisplayed() && elementSize.isEnabled()) {
					selectDropDownByVisibleText_custom(elementSize, size, "Size");
				}
			}
		}
	}
		
		//Select Perforation in Perforation DropDown Menu 
		public void selectPerforation(String perforation) throws Throwable {
			List<WebElement> perforationDropdown=custom_findElements(selectPerforation);
			if(perforationDropdown.size()>0) {
				for(WebElement elementPerforation : perforationDropdown) {
					waitForElementToBeClickable(elementPerforation);
					if(elementPerforation.isDisplayed() && elementPerforation.isEnabled()) {
						selectDropDownByVisibleText_custom(elementPerforation, perforation, "Perforation");
					}
				}
			}
		}
		
		//Select Numbering in Numbering DropDown Menu 
		public void selectNumbering(String numbering) throws Throwable {
			List<WebElement> numberingDropdown=custom_findElements(selectNumbering);
			if(numberingDropdown.size()>0) {
				for(WebElement elementNumbering : numberingDropdown) {
					waitForElementToBeClickable(elementNumbering);
					if(elementNumbering.isDisplayed() && elementNumbering.isEnabled()) {
						selectDropDownByVisibleText_custom(elementNumbering, numbering, "Numbering");
					}
				}
			}
		}
		
		//Select VariableData in VariableData DropDown Menu 
		public void selectVariableData(String variableData) throws Throwable {
			List<WebElement> variableDataDropdown=custom_findElements(selectVariableData);
			if(variableDataDropdown.size()>0) {
				for(WebElement elementVariableData : variableDataDropdown) {
					waitForElementToBeClickable(elementVariableData);
					if(elementVariableData.isDisplayed() && elementVariableData.isEnabled()) {
						selectDropDownByVisibleText_custom(elementVariableData, variableData, "VariableData");
					}
				}
			}
		}
		
		public List<String> getStockOptions() {
			return getOptionsAsArrayList(custom_findElement(selectStock));
		}
		
		public List<String> getColorspecOptions() {
			return getOptionsAsArrayList(custom_findElement(selectColorSpec));
		}
		
		public List<String> getWidthOptions() {
			return getOptionsAsArrayList(custom_findElement(selectWidth));
		}
		
		public List<String> getHeightOptions() {
			return getOptionsAsArrayList(custom_findElement(selectHeight));
		}
		
		public void validateQuantityOptions(String quantity,SoftAssert sa) {
			String ActualQuantity=custom_findElement(addQuantity).getAttribute("value");
			if(ActualQuantity.equals(quantity)) {
				sa.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "Actual Qunatity: "+ActualQuantity+" matches the expected Qunatity: "+quantity);
			}
			else {
				sa.assertFalse(true);
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Actual Qunatity: "+ActualQuantity+" does not match with the expected Qunatity: "+quantity);
			}
		}
		
		public List<String> getTurnAroundTimeOptions() {
			return getOptionsAsArrayList(custom_findElement(selectTurnAroundTime));
		}
		public List<String> getFinishOptionOptions() {
			return getOptionsAsArrayList(custom_findElement(selectFinishOption));
		}
		
		public List<String> getFinishOptionsOptions() {
			return getOptionsAsArrayList(custom_findElement(selectFinishOptions));
		}
		
		public List<String> getPolePocketsOptions() {
			return getOptionsAsArrayList(custom_findElement(selectPolePockets));
		}
		
		public List<String> getHemsOptions() {
			return getOptionsAsArrayList(custom_findElement(selectHems));
		}
		
		public List<String> getGrommetsOptions() {
			return getOptionsAsArrayList(custom_findElement(selectGrommets));
		}
		
		public List<String> getThreadColorOptions() {
			return getOptionsAsArrayList(custom_findElement(selectThreadColor));
		}
		
		public List<String> getContourCutOptions() {
			return getOptionsAsArrayList(custom_findElement(selectContourCut));
		}
		
		public List<String> getDrillholeandHardwareOptions() {
			return getOptionsAsArrayList(custom_findElement(selectDrillholeandHardware));
		}
		
		public List<String> getRoundCornersOptions() {
			return getOptionsAsArrayList(custom_findElement(selectRoundCorners));
		}
		
		
		//for Select Stock in Stock DropDown Menu 
		public void selectStock(String stock) throws Throwable {
			List<WebElement> stockDropdownList=custom_findElements(selectStock);
			if(stockDropdownList.size()>0) {
				for(WebElement elementStock : stockDropdownList) {
					if(elementStock.isDisplayed()&& elementStock.isEnabled()) {
						selectDropDownByVisibleText_custom(elementStock, stock, "Stock");
					}
				}
			}
		}

		//for Select Colorspec in Colorspec DropDown Menu 
		public void selectColorspec(String colorspec) throws Throwable {
			List<WebElement> colorSpecDropdownList=custom_findElements(selectColorSpec);
			if(colorSpecDropdownList.size()>0) {
				for(WebElement elementColorSpec : colorSpecDropdownList) {
					if(elementColorSpec.isDisplayed() && elementColorSpec.isEnabled()) {
						selectDropDownByVisibleText_custom(elementColorSpec, colorspec, "Colorspec");
					}
				}
			}
		}
		
		//for Select White Mask Front in White Mask Front DropDown Menu 
		public void selectWhiteMaskFront(String whiteMaskFront) throws Throwable {
			List<WebElement> whiteMaskFrontDropdownList=custom_findElements(selectWhiteMaskFront);
			if(whiteMaskFrontDropdownList.size()>0) {
				for(WebElement elementWhiteMaskFront : whiteMaskFrontDropdownList) {
					if(elementWhiteMaskFront.isDisplayed() && elementWhiteMaskFront.isEnabled()) {
						selectDropDownByVisibleText_custom(elementWhiteMaskFront, whiteMaskFront, "White Mask Front");
					}
				}
			}
		}

		
		//for Select Coating in Coating DropDown Menu 
		public void selectCoating(String coating) throws Throwable {
			List<WebElement> coatingDropdownList=custom_findElements(selectCoating);
			if(coatingDropdownList.size()>0) {
				for(WebElement elementCoating : custom_findElements(selectCoating)) {
					if(elementCoating.isDisplayed() && elementCoating.isEnabled()) {
						selectDropDownByVisibleText_custom(elementCoating, coating, "Coating");
					}
				}
			}
		}
		
		//for Select Easel Backs in EaselBacks DropDown Menu 
		public void selectEaselBacks(String easelBacks) throws Throwable {
			if(custom_findElements(selectEaselBacks).size()>0) {
				for(WebElement elementEaselBacks : custom_findElements(selectEaselBacks)) {
					if(elementEaselBacks.isDisplayed() && elementEaselBacks.isEnabled()) {
						selectDropDownByVisibleText_custom(elementEaselBacks, easelBacks, "Easel Backs");
					}
				}
			}
		}
		
		//for Select Hardware in Hardware DropDown Menu 
		public void selectHardware(String hardware) throws Throwable {
			if(custom_findElements(selectHardware).size()>0) {
				for(WebElement elementHardware : custom_findElements(selectHardware)) {
					if(elementHardware.isDisplayed() && elementHardware.isEnabled()) {
						selectDropDownByVisibleText_custom(elementHardware, hardware, "Hardware");
					}
				}
			}
		}
		
				
			//for Select Hardware in Hardware DropDown Menu 
				public void selectHardwareAfterTAT(String hardware) throws Throwable {
					if(custom_findElements(selectHardwareAfterTAT).size()>0) {
						for(WebElement elementHardware : custom_findElements(selectHardwareAfterTAT)) {
							if(elementHardware.isDisplayed() && elementHardware.isEnabled()) {
								selectDropDownByVisibleText_custom(elementHardware, hardware, "Hardware");
							}
						}
					}
				}	
		

		//for Select Score and Fold in Score and Fold DropDown Menu  
		public void selectScoreAndFold(String scoreAndFold) throws Throwable {
			List<WebElement> scoreAndFoldDropdown=custom_findElements(selectScoreAndFold);
			if(scoreAndFoldDropdown.size()>0) {
				for(WebElement elementScoreAndFold : scoreAndFoldDropdown) {
					if(elementScoreAndFold.isDisplayed() && elementScoreAndFold.isEnabled()) {
						selectDropDownByVisibleText_custom(elementScoreAndFold, scoreAndFold, "Score and Fold");
					}
				}
			}
		}
		
		//for Select Spot UV Sides in Spot UV Sides DropDown Menu
		public void selectSpotUVSides(String spotUVSides) throws Throwable {
			List<WebElement> spotUVSidesDropdown=custom_findElements(selectSpotUVSides);
			if(spotUVSidesDropdown.size()>0) {
				for(WebElement elementSpotUVSides : spotUVSidesDropdown) {
					if(elementSpotUVSides.isDisplayed() && elementSpotUVSides.isEnabled()) {
						selectDropDownByVisibleText_custom(elementSpotUVSides, spotUVSides, "SPOT UV SIDES");
					}
				}
			}
		}
			
			//Select Lamination in Lamination DropDown Menu 
			public void selectLamination(String lamination) throws Throwable {
				List<WebElement> laminationDropdown=custom_findElements(selectLamination);
				if(laminationDropdown.size()>0) {
					for(WebElement elementLamination : laminationDropdown) {
						if(elementLamination.isDisplayed() && elementLamination.isEnabled()) {
								selectDropDownByVisibleText_custom(elementLamination, lamination, "Lamination");
							}
						}
					}
			}
				
			//Select FoilColor in FoilColor DropDown Menu
			public void selectFoilColor(String foilColor) throws Throwable {
				List<WebElement> foilColorDropdown=custom_findElements(selectFoilColor);
				if(foilColorDropdown.size()>0) {
					for(WebElement elementFoilColor : foilColorDropdown) {
						if(elementFoilColor.isDisplayed() && elementFoilColor.isEnabled()) {
								selectDropDownByVisibleText_custom(elementFoilColor, foilColor, "FoilColor");
							}
						}
					}
			}
			
			
			//Select Edge Color in Edge Color DropDown Menu 
			public void selectEdgeColor(String edgeColor) throws Throwable {
				List<WebElement> edgeColorDropdown=custom_findElements(selectEdgeColor);
				if(edgeColorDropdown.size()>0){
					for(WebElement elementEdgeColor : edgeColorDropdown) {
						if(elementEdgeColor.isDisplayed() && elementEdgeColor.isEnabled()) {
							selectDropDownByVisibleText_custom(elementEdgeColor, edgeColor, "Edge Color");
						}
					}
				}
			}

		//Select OpeningSide in OpeningSide DropDown Menu
		public void selectOpeningSide(String openingSide) throws Throwable {
			List<WebElement> openingSideDropdown=custom_findElements(selectOpeningSide);
			if(openingSideDropdown.size()>0){
				for(WebElement elementOpeningSide : openingSideDropdown) {
					if(elementOpeningSide.isDisplayed() && elementOpeningSide.isEnabled()) {
						selectDropDownByVisibleText_custom(elementOpeningSide, openingSide, "Opening Side");
					}
				}	
			}
		}
		
		//Select Handle Options in Handle Options DropDown Menu
				public void selectHandleOptions(String handleOptions) throws Throwable {
					List<WebElement> handleOptionsDropdown=custom_findElements(selectHandleOptions);
					if(handleOptionsDropdown.size()>0){
						for(WebElement elementHandleOptions : handleOptionsDropdown) {
							if(elementHandleOptions.isDisplayed() && elementHandleOptions.isEnabled()) {
								selectDropDownByVisibleText_custom(elementHandleOptions, handleOptions, "Handle Options");
							}
						}	
					}
				}
				
			//Select Fastener Options in Fastener Options DropDown Menu
				public void selectFastenerOptions(String fastenerOptions) throws Throwable {
					List<WebElement> fastenerOptionsDropdown=custom_findElements(selectFastenerOptions);
					if(fastenerOptionsDropdown.size()>0){
						for(WebElement elementFastenerOptions : fastenerOptionsDropdown) {
							if(elementFastenerOptions.isDisplayed() && elementFastenerOptions.isEnabled()) {
								selectDropDownByVisibleText_custom(elementFastenerOptions, fastenerOptions, "Fastener Options");
							}
						}	
					}
				}
				
	//Select Edge Join Options in Edge Join Options DropDown Menu
				public void selectEdgeJoinOptions(String edgeJoinOptions) throws Throwable {
					List<WebElement> edgeJoinOptionsDropdown=custom_findElements(selectEdgeJoinOptions);
					if(edgeJoinOptionsDropdown.size()>0){
						for(WebElement elementEdgeJoinOptions : edgeJoinOptionsDropdown) {
							if(elementEdgeJoinOptions.isDisplayed() && elementEdgeJoinOptions.isEnabled()) {
								selectDropDownByVisibleText_custom(elementEdgeJoinOptions, edgeJoinOptions, "Edge Join Options");
							}
						}	
					}
				}
				
				
			//Select Business Card Box Size in Business Card Box Size DropDown Menu
				public void selectBusinessCardBoxSize(String businessCardBoxSize) throws Throwable {
					List<WebElement> businessCardBoxSizeDropdown=custom_findElements(selectBusinessCardBoxSize);
					if(businessCardBoxSizeDropdown.size()>0){
						for(WebElement elementBusinessCardBoxSize : businessCardBoxSizeDropdown) {
							if(elementBusinessCardBoxSize.isDisplayed() && elementBusinessCardBoxSize.isEnabled()) {
								selectDropDownByVisibleText_custom(elementBusinessCardBoxSize, businessCardBoxSize, "Business Card Box Size");
							}
						}	
					}
				}	
		
		//Select OpeningSide in OpeningSide DropDown Menu
		public void selectWindowOptions(String windowOptions) throws Throwable {
			List<WebElement> windowOptionsDropdown=custom_findElements(selectWindowOptions);
			if(windowOptionsDropdown.size()>0){
				for(WebElement elementWindowOptions : windowOptionsDropdown) {
					if(elementWindowOptions.isDisplayed() && elementWindowOptions.isEnabled()) {
						selectDropDownByVisibleText_custom(elementWindowOptions, windowOptions, "Window Options");
					}
				}	
			}
		}


		//for Select Scoring Options in Scoring Options DropDown Menu
		public void selectScoringOptions(String scoringOptions) throws Throwable {
			for(WebElement elementScoringOptions : custom_findElements(selectScoringOptions)) {
				if(elementScoringOptions.isDisplayed()) {
					selectDropDownByVisibleText_custom(elementScoringOptions, scoringOptions, "Scoring Options");
				}
			}
		}
		
		
		//for Select Drill Hole  in Drill Hole DropDown Menu
		public void selectDrillHole(String drillHole) throws Throwable {
			for(WebElement elementDrillHole : custom_findElements(selectDrillHole)) {
				if(elementDrillHole.isDisplayed()) {
					selectDropDownByVisibleText_custom(elementDrillHole, drillHole, "Drill Hole");
				}
			}
		}
		
		//for Select Bundling Service  in Bundling Service DropDown Menu
		public void selectBundlingService(String bundlingService) throws Throwable {
			for(WebElement elementBundlingService : custom_findElements(selectBundlingService)) {
				if(elementBundlingService.isDisplayed()) {
					selectDropDownByVisibleText_custom(elementBundlingService, bundlingService, "Bundling Service");
				}
			}
		}
		
		
		//for Select Bundling Service  in Bundling Service DropDown Menu
				public void selectEDDMService(String eDDMService) throws Throwable {
					for(WebElement elementEDDMService : custom_findElements(selectEDDMServiceOption)) {
						if(elementEDDMService.isDisplayed()) {
							selectDropDownByVisibleText_custom(elementEDDMService, eDDMService, "EDDM Service Option");
						}
					}
				}
		

		//for Select Shrink Wrapping  in Shrink Wrapping DropDown Menu
		public void selectShrinkWrapping(String shrinkWrapping) throws Throwable {
			for(WebElement elementShrinkWrapping : custom_findElements(selectShrinkWrapping)) {
				if(elementShrinkWrapping.isDisplayed()) {
					selectDropDownByVisibleText_custom(elementShrinkWrapping, shrinkWrapping, "Shrink Wrapping");
				}
			}
		}
		
		//for Select Shrink Wrapping  in Shrink Wrapping DropDown Menu
		public void selectSecondOptionFromShrinkWrapping() throws Throwable {
			for(WebElement elementShrinkWrapping : custom_findElements(selectShrinkWrapping)) {
				if(elementShrinkWrapping.isDisplayed()) {
					selectSecondOptionFromDropDown(elementShrinkWrapping, "Shrink Wrapping");
				}
			}
		}
	//for Click Runsize  in Runsize Table Menu
		public void clickRunSize(String runSizeName) {
			for(WebElement elementRunsize:custom_findElements(selectRunSize)) {
				if(elementRunsize.isDisplayed() && getText_custom(elementRunsize).equalsIgnoreCase(runSizeName)) {
						click_custom(elementRunsize, runSizeName);
						break;
				}
			}
		}
		
		//for Click Runsize  in Runsize Table Menu
		public void clickShowMore(String runSizeName) {
			if(custom_findElements(showMoreRunSize).get(0).isDisplayed()) 
				click_custom(custom_findElements(showMoreRunSize).get(0), "Show More Link");	
		}
		
		//for validate and select Runsize table or Quantity table displayed
		public void selectRunsizeOrQuantity(String runsizeOrQuantityText) throws Throwable {
			if(custom_findElements(selectRunSize).size()>0) {
				Assert.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.PASS,"Runsize table is displayed");
				clickRunSize(runsizeOrQuantityText);
			} else if (custom_findElements(quantity).size()>0) {
				Assert.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.PASS,"Quantity table is displayed");
				addQuantity(runsizeOrQuantityText);
			}else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL,"Runsize or Quantity not displayed");
				Assert.fail();
			}
		}
		//Add quantity in quantity table
		public void addQuantity(String quantity) {
			if(custom_findElements(addQuantity).get(0).isDisplayed()) {
				for(WebElement elementQuantity:custom_findElements(addQuantity)) {
					if(elementQuantity.isDisplayed()) {
						sendKeys_custom(elementQuantity, quantity, "Quantity");
						break;
					}
				}
			}
		}
		
		
		public void quantity(String quantity) {
			WebElement quantityElement=custom_findElement(addQuantity);
			if(quantityElement.isDisplayed()) {
				try {
					if(quantityElement.getAttribute("value").equals(quantity)) {
						//log success message in extent report
						ExtentFactory.getInstance().getExtent().log(Status.PASS, "Quantity ==> "+quantityElement.getAttribute("value"));
					}
				} catch (Exception e) {
					//log failure message in extent report
					ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to click on field: " +quantityElement.getAttribute("value") +" due to exception: "+e);
				}
			}
		}
		
			//for Select width in width DropDown Menu
			public void selectWidth(String width) throws Throwable {
				for(WebElement elementWidth : custom_findElements(selectWidth)) {
						if(elementWidth.isDisplayed()) {
							selectDropDownByVisibleText_custom(elementWidth, width, "Width");
					}
				}
			}

			//for Select height in height DropDown Menu
			public void selectHeight(String height) throws Throwable {
				waitFor(1000);
				for(WebElement elementHeight : custom_findElements(selectHeight)) {
						if(elementHeight.isDisplayed()) {
							selectDropDownByVisibleText_custom(elementHeight, height, "Height");
					}
				}
			}

			//for Select Turn Around Time by second option in Turn Around Time DropDown Menu
			public void selectSecondOptionFromTurnAroundTimeDropdown() throws Throwable {
				if(custom_findElements(selectTurnAroundTime).size()>0) {
					selectSecondOptionFromDropDown(custom_findElement(selectTurnAroundTime), "Turnaround Time");
				}
			}

			//for Select Turn Around Time  in Turn Around Time DropDown Menu
			public void selectTurnAroundTime(String turnAroundTime) throws Throwable {
				for(WebElement elementTurnAroundTime : custom_findElements(selectTurnAroundTime)) {
					if(elementTurnAroundTime.isDisplayed()) {
						selectDropDownByVisibleText_custom(elementTurnAroundTime, turnAroundTime, "Turnaround Time");
					}
				}
			}
			
			//for check Turn Around Time  in Turn Around Time DropDown Menu
			public void validateTurnAroundTime() throws Throwable {
				Select s=new Select(custom_findElement(selectTurnAroundTime));
				List<WebElement> options = getOptions(s);
				for(WebElement option:options) {
					ExtentFactory.getInstance().getExtent().log(Status.PASS, "TurnAround Time ==> Dropdown available by text: "+ getText_custom(option));
				}
			}
			
			//for Select Finish Option  in Finish Option DropDown Menu
			public void selectFinishOption(String finishOption) throws Throwable {
				for(WebElement elementFinishOption : custom_findElements(selectFinishOption)) {
					if(elementFinishOption.isDisplayed()) {
						selectDropDownByVisibleText_custom(elementFinishOption, finishOption, "Finish Option");
					}
				}
			}
			
			//for Select Finish Options  in Finish Options DropDown Menu
			public void selectFinishOptions(String finishOptions) throws Throwable {
				for(WebElement elementFinishOptions : custom_findElements(selectFinishOptions)) {
					if(elementFinishOptions.isDisplayed()) {
						selectDropDownByVisibleText_custom(elementFinishOptions, finishOptions, "Finish Options");
					}
				}
			}
			
			//for Select Hems  in Hems DropDown Menu
			public void selectHems(String hems) throws Throwable {
				for(WebElement elementHems : custom_findElements(selectHems)) {
					if(elementHems.isDisplayed()) {
						selectDropDownByVisibleText_custom(elementHems, hems, "Hems");
					}
				}
			}
			
			//for Select Grommets  in Grommets DropDown Menu
			public void selectGrommets(String grommets) throws Throwable {
				for(WebElement elementGrommets : custom_findElements(selectGrommets)) {
					if(elementGrommets.isDisplayed()) {
						selectDropDownByVisibleText_custom(elementGrommets, grommets, "Grommets");
					}
				}
			}
			
			//for Select Thread Color  in Thread Color DropDown Menu
			public void selectThreadColor(String threadColor) throws Throwable {
				for(WebElement elementThreadColor : custom_findElements(selectThreadColor)) {
					if(elementThreadColor.isDisplayed()) {
						selectDropDownByVisibleText_custom(elementThreadColor, threadColor, "Thread Color");
					}
				}
			}
			
			//for Select Pole Pockets  in Pole Pockets DropDown Menu
			public void selectPolePockets(String polePockets) throws Throwable {
				for(WebElement elementPolePockets : custom_findElements(selectPolePockets)) {
					if(elementPolePockets.isDisplayed()) {
						selectDropDownByVisibleText_custom(elementPolePockets, polePockets, "Pole Pockets");
					}
				}
			}
			
			//for Select Contour Cut  in Contour Cut DropDown Menu
			public void selectContourCut(String contourCut) throws Throwable {
				for(WebElement elementContourCut : custom_findElements(selectContourCut)) {
					if(elementContourCut.isDisplayed()) {
						selectDropDownByVisibleText_custom(elementContourCut, contourCut, "Contour Cut");
					}
				}
			}
			
			//for Select Drill hole and Hardware  in Drill hole and Hardware DropDown Menu
			public void selectDrillholeandHardware(String drillholeandHardware) throws Throwable {
				for(WebElement elementDrillholeandHardware : custom_findElements(selectDrillholeandHardware)) {
					if(elementDrillholeandHardware.isDisplayed()) {
						selectDropDownByVisibleText_custom(elementDrillholeandHardware, drillholeandHardware, "Drill hole and Hardware");
					}
				}
			}
			
			//for Select Round Corners in Round Corners DropDown Menu
			public void selectRoundCorners(String roundCorners) throws Throwable {
				for(WebElement elementRoundCorners : custom_findElements(selectRoundCorners)) {
					if(elementRoundCorners.isDisplayed()) {
						selectDropDownByVisibleText_custom(elementRoundCorners, roundCorners, "Round Corners");
					}
				}
			}
			
			//for Click Estimate Shippingbox 
			public void clickEstimateShippingBox(String name) {
				click_custom(custom_findElements(estimateShipping).get(0), name);
			}
			
			//for Enter Zipcode in Zipcode Textbox
			public void typeZipCode(String name) {
				sendKeys_custom(custom_findElements(zipCode).get(0), name,"ZipCode");
			}

			//for Click Update Button 
			public void clickUpdateButton(String name) {
				click_custom(custom_findElements(updateButton).get(0), name);
			}
			
			//for Click Proceed To Shipping Button
			public void clickProceedToShippingButton() throws InterruptedException {
				if(custom_findElements(proceedToShippingButton).get(0).isDisplayed()) {
					click_javascript(custom_findElements(proceedToShippingButton).get(0), "Proceed To Shipping");
				}
			}
			
			//for Select Folding Options  in Folding Options DropDown Menu
			public void selectFoldingOptions(String foldingOptionName) throws Throwable {
				for(WebElement elementFoldingOptions : custom_findElements(selectFoldingOptions)) {
					if(elementFoldingOptions.isDisplayed()) {
						selectDropDownByVisibleText_custom(elementFoldingOptions, foldingOptionName, "Folding Options");
				}
				}
			}

			//for Select Mailing Service  in Mailing Service DropDown Menu
			public void selectMailingService(String mailingServiceName) throws Throwable {
				for(WebElement elementMailingService : custom_findElements(selectMailingService)) {
					if(elementMailingService.isDisplayed()) {
				selectDropDownByVisibleText_custom(elementMailingService, mailingServiceName, "Mailing Service");
					}
			}
			}
			
			//for Select Number Of Tabs  in Number Of Tabs DropDown Menu
			public void selectNumberOfTabs(String numberOfTabsName) throws Throwable {
				for(WebElement elementNumberOfTabs : custom_findElements(selectNumberOfTabs)) {
					if(elementNumberOfTabs.isDisplayed()) {
						selectDropDownByVisibleText_custom(elementNumberOfTabs, numberOfTabsName, "NumberOfTabs");
					}
				}
			}

			//For select Postage Class in Postage Class DropDown Menu
			public void selectPostageClass(String postageClassName) throws Throwable {
				for(WebElement elementPostageClass : custom_findElements(selectPostageClass)) {
					if(elementPostageClass.isDisplayed()) {
						selectDropDownByVisibleText_custom(elementPostageClass, postageClassName, "Postage Class");
					}
				}
			}
			
			//For select Print Method in Print Method DropDown Menu
			public void selectPrintMethod(String printMethod) throws Throwable {
				for(WebElement elementPrintMethod : custom_findElements(selectPrintMethod)) {
						waitFor(1000);
						if(elementPrintMethod.isDisplayed()) {
							selectDropDownByVisibleText_custom(elementPrintMethod, printMethod, "Print Method");
					}
				}
			}
			
			//For select Blank Envelopes in Blank Envelopes DropDown Menu
			public void selectBlankEnvelopes(String blankEnvelopes) throws Throwable {
				for(WebElement elementBlankEnvelopes : custom_findElements(selectBlankEnvelopes)) {
						waitFor(1000);
						if(elementBlankEnvelopes.isDisplayed()) {
							selectDropDownByVisibleText_custom(elementBlankEnvelopes, blankEnvelopes, "Blank Envelopes");
					}
				}
			}
			
			//For select Blank Second Sheets in Blank Second Sheets DropDown Menu
			public void selectBlankSecondSheets(String blankSecondSheets) throws Throwable {
				for(WebElement elementBlankSecondSheets : custom_findElements(selectBlankSecondSheets)) {
						waitFor(1000);
						if(elementBlankSecondSheets.isDisplayed()) {
							selectDropDownByVisibleText_custom(elementBlankSecondSheets, blankSecondSheets, "Blank Second Sheets");
					}
				}
			}

			//For Enter Project Name in Project/Client Name TextBox
			public void enterProjectName(String projectName) {
				sendKeys_custom(custom_findElements(projectNameText).get(0), projectName,"ProjectName");
			}
			
			//check actual price with expected price  
			public void validateSubTotalPriceWithGivenPrice(String price, SoftAssert sa) {
				String subTotal=custom_findElement(subTotalPrice).getText().trim();
				if(subTotal.equals(price)) {
					sa.assertTrue(true);
					ExtentFactory.getInstance().getExtent().log(Status.PASS, "Actual Sub Total Price: "+subTotal+" matches the expected price: "+price);
				}
				else {
					sa.assertFalse(true);
					ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Actual Sub Total Price: "+subTotal+" does not match with the expected price: "+price);
				}
			}
			
			//validate subtotal price 
			public void validateSubTotalPrice() {
				String subTotal= getText_custom(custom_findElement(subTotalPrice));
				subTotal = MathUtility.regularExpressionRetainDot(subTotal);
				double price = MathUtility.parseStringToDouble(subTotal);
				if(price>0.0) {
					ExtentFactory.getInstance().getExtent().log(Status.PASS, "Sub Total Price==>"+subTotal);
					Assert.assertTrue(true);
				}
				else {
					Assert.fail("Price not valid");
				}
			}
			
			//check price availability 
			public void validateSubTotalPrice1() {
				String subTotalCurrency;
				String subTotal= getText_custom(custom_findElement(subTotalPrice));
				subTotalCurrency = subTotal;
				subTotal=subTotal.replace("$", "").trim();
				if((subTotal).contains(","))
				{
					subTotal=subTotal.replace(",", "").trim();	
				}
				double price = Double.parseDouble(subTotal);
				if(price>0.0) {
					ExtentFactory.getInstance().getExtent().log(Status.INFO, "Sub Total Price==>"+subTotalCurrency);
					Reporter.log("Sub Total Price==>"+subTotalCurrency);
					Assert.assertTrue(true);
				}
				else {
					Assert.fail("Price not valid");
				}
			}
			
			//view subtotal price  
			public double viewSubTotalPrice() {
				String subTotalCurrency = getText_custom(custom_findElement(subTotalPrice));
				subTotalCurrency=MathUtility.regularExpressionRetainDot(subTotalCurrency).trim();
				ExtentFactory.getInstance().getExtent().log(Status.INFO, "SubTotal Price: "+subTotalCurrency);
				return MathUtility.parseStringToDouble(subTotalCurrency);
			}
			
			//view PDF Proofs price  
			public double viewPDFProofsPrice() {
				double pdfProofsPriceAmount=0.0;
				if(custom_findElement(pdfProofsCheckbox).isSelected()) {
					String pdfProofsPriceText = getText_custom(custom_findElement(pdfProofsPrice));
					pdfProofsPriceText=MathUtility.regularExpressionRetainDot(pdfProofsPriceText).trim();
					ExtentFactory.getInstance().getExtent().log(Status.INFO, "PDF Proofs Price: "+pdfProofsPriceText);
					return MathUtility.parseStringToDouble(pdfProofsPriceText);
				}
				else
					return pdfProofsPriceAmount;
			}
			
			//view Sample Of Completed Job price  
			public double viewSampleOfCompletedJobPrice() {
				double sampleOfCompletedJobPriceAmount=0.0;
				if(custom_findElement(sampleOfCompletedJobCheckbox).isSelected()) {
					String sampleOfCompletedJobPriceText = getText_custom(custom_findElement(sampleOfCompletedJobPrice));
					sampleOfCompletedJobPriceText=MathUtility.regularExpressionRetainDot(sampleOfCompletedJobPriceText).trim();
					ExtentFactory.getInstance().getExtent().log(Status.INFO, "Sample Of Completed Job Price: "+sampleOfCompletedJobPriceText);
					return MathUtility.parseStringToDouble(sampleOfCompletedJobPriceText);
				}
				else
					return sampleOfCompletedJobPriceAmount;
			}
			
			//view Subtotal price with PDF Proofs 
			public double viewSubTotalPriceWithPDFProofs(SoftAssert sa) {
				double subTotal = viewSubTotalPrice();
				selectPDFProofsCheckbox();
				double expectedPrice=subTotal+viewPDFProofsPrice();
				double actualPrice=viewSubTotalPrice();
				sa.assertEquals(actualPrice, expectedPrice);
				return actualPrice;
			}
			
			//view Subtotal price with Sample Of Completed Job price
			public double viewSubTotalPriceWithSampleOfCompletedJob(SoftAssert sa) {
				double subTotal = viewSubTotalPrice();
				selectSampleOfCompletedJobCheckbox();
				double expectedPrice=subTotal+viewSampleOfCompletedJobPrice();
				double actualPrice=viewSubTotalPrice();
				sa.assertEquals(actualPrice, expectedPrice);
				return actualPrice;
			}
			
			//view Subtotal price with PDF Proofs and Sample Of Completed Job 
			public double viewSubTotalPriceWithPDFProofs_SampleOfCompletedJob(SoftAssert sa) {
				double subTotal = viewSubTotalPrice();
				selectPDFProofsCheckbox();
				selectSampleOfCompletedJobCheckbox();
				double expectedPrice=subTotal+viewPDFProofsPrice()+viewSampleOfCompletedJobPrice();
				double actualPrice=viewSubTotalPrice();
				sa.assertEquals(actualPrice, expectedPrice);
				ExtentFactory.getInstance().getExtent().log(Status.INFO, expectedPrice+ " matched with " +actualPrice);
				return actualPrice;
			}
			
			// for click Start Over Link 
			public void clickStartOverLink(String start) {
				if(!(custom_findElement(startOverLink).isDisplayed())) {
					ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Start Over link not available");
					Assert.fail();
				}
				else {
					click_custom(custom_findElements(startOverLink).get(0), start);
					Assert.assertTrue(true);
				}
			}
			
		// for click Start Over Link 
			public void clickStartOverLinkORLoginFromPDP(String emailText, String passwordText ) {
				if(custom_findElements(startOverLink).size()>0) {
					click_custom(custom_findElements(startOverLink).get(0), "Start Over");
				}
				else {
					loginInFromPDPPage(emailText,passwordText);
					clickStartOverLinkORLoginFromPDP(emailText,passwordText);
				}
			}
		
			public void tATValidationBasedOnStock(String quantityText, SoftAssert sa) throws Throwable {
				for(String stock: getOptionsAsArrayList(custom_findElement(selectStock))) {
					if(stock.equalsIgnoreCase("14PT C2S") || stock.equalsIgnoreCase("16PT C2S")) {
						selectSecondOptionFromDropDown(custom_findElement(selectSize), "Size");
						List<String> runsizeOrQuantityOptions=new ArrayList<>();
						if(custom_findElements(selectRunSize).size()>0) {
							if(custom_findElement(showMoreRunSize).isDisplayed() || custom_findElements(showMoreRunSize).size()>0) 
								click_custom(custom_findElement(showMoreRunSize), "Show More Link");
							for(WebElement runsize:custom_findElements(selectRunSize)) {
								runsizeOrQuantityOptions.add(runsize.getText());
							}
						}
						else if(custom_findElements(quantity).size()>0) {
							runsizeOrQuantityOptions.add(quantityText);
						}
						for(String runsizeOrQuantity:runsizeOrQuantityOptions) {
							selectRunsizeOrQuantity(runsizeOrQuantity);
							if(getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("2-4 Business Days") && (MathUtility.parseStringToInteger(runsizeOrQuantity)>=25 || MathUtility.parseStringToInteger(runsizeOrQuantity)<5000)){
									sa.assertTrue(true);
									ExtentFactory.getInstance().getExtent().log(Status.PASS, "");
								}
							else if(getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("5-7 Business Days") && (MathUtility.parseStringToInteger(runsizeOrQuantity)>=5000 || MathUtility.parseStringToInteger(runsizeOrQuantity)<=15000)) {
								sa.assertTrue(true);
								ExtentFactory.getInstance().getExtent().log(Status.PASS, "");
							}
							else if(MathUtility.parseStringToInteger(runsizeOrQuantity)>15000) {
								ExtentFactory.getInstance().getExtent().log(Status.FAIL, runsizeOrQuantity+ " is more than 15000");
								sa.fail();
							}
							else if(getOptionsAsArrayList(custom_findElement(selectTurnAroundTime)).contains("Next Business Day")) {
								ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Next Business Day");
								sa.fail();
							}
						}
					}
				}
}
}

