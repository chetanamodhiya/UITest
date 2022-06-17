package com.magento.pages;

import java.awt.AWTException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.magento.testBase.DriverFactory;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class ArtworkPage extends TestBase{
	By artworkFrameBeforePlaceOrder=By.xpath("//iframe[contains(@data-bind,'iframeLoaded')]");
	By artworkFrameForDM_EDDM=By.xpath("(//iframe[contains(@data-bind,'iframeLoaded')])[2]");
	By artworkFrameAfterPlaceOrder=By.xpath("//div[@id='project-artwork']//iframe");
	By artworkFrameForMyMedia=By.xpath("//div[@id='project-my-media']//iframe");
	By filesHere=By.xpath("//span[text()='here']");
	By successSignLink=By.xpath("//img[contains(@class,'tooltip')]");
	By preflightStatus=By.xpath("//img[contains(@class,'preflight-tooltip-container icon')]");
	By closePreflightMenu=By.xpath("//span[text()='Preflight Status: ']/..//img");
	By deleteImage=By.xpath("//img[@class='delete-icon']");
	By sourcePath=By.xpath("//img[contains(@class,'preflight-tooltip-container icon-check')]/../../..//div[contains(@class,'MuiCardMedia-root')]");
	By sourcePathForCSV=By.xpath("//span[contains(text(),'.csv')]/../..//div[contains(@class,'MuiCardMedia-root')]");
	By destinationPath_ImagesForCommonArea=By.xpath("//p[text()='Common files']/../../..//span[contains(@class,'jss') and not(text()='Variable Data File')]/../../..//div[contains(@class,'MuiCardMedia-root')]/..");
	By destinationPath_CSVForCommonArea=By.xpath("//p[text()='Common files']/../../..//span[text()='Variable Data File']/../../..//div[contains(@class,'MuiCardMedia-root')]/..");
	By spinner=By.xpath("//img[@title='Loading...']");
	By InvisibleFileUpload=By.xpath("//div[@class='filepond--file']");
	By closeFile=By.xpath("(//img[contains(@src,'data:image/png')])[3]");
	By editArtworkButton=By.xpath("//span[text()='EDIT ARTWORK']/..");
	By editLink=By.xpath("//span[text()='Media']/../..//span[text()='Edit']");
	By okToPrintButton=By.xpath("//span[text()='OK TO PRINT']/..");
	By fileAnalyze=By.xpath("//div[text()='Analyzing File...']");
	By fitToTemplateIcon=By.xpath("//div[@class='toolbar']//div[@title='Fit To Template']");
	By rotateTemplateIcon=By.xpath("//div[contains(@id,'rotate-template')]");
	By rotateArtworkLeft= By.xpath("//div[@class='toolbar']//div[@title='Rotate Artwork Left']");
	By rotateArtworkRight= By.xpath("//div[@class='toolbar']//div[@title='Rotate Artwork Right']");
	By checkBoxClick=By.xpath("//ul[@class='rules']//li//input[@type='checkbox']");
	By readyForProductionButton=By.xpath("//button[@class='submit' and text()='Ready For Production']");
	By approveArtworkButton=By.xpath("//button[@class='submit' and text()='Approve All Artwork For Production']");
	By cancelButton=By.xpath("//button[@class='cancel'and text()='cancel']");
	By spinnerContainer=By.xpath("//div[@class='css-spinner-container']");
	By oKButton=By.xpath("//button[text()='OK']");
	By closeArtworkButton=By.xpath("(//h1[contains(text(),'Artwork')])[2]/..//button[@class='action-close']");
	By toolbar=By.xpath("//div[@class='toolbar']");
	By templatePage=By.xpath("//div[@class='toolbar']/..");
	By images=By.xpath("//div[@id='galleryGridContainer']/div/div/div[2]");
	By transparentButton=By.xpath("//button[contains(@class,'button transparent')]");
	By activeOkToPrintButton=By.xpath("//button[contains(@class,'button active-blue')]");
	By itemLoad=By.xpath("//button[contains(@class,'action-abort-item-load')]");
	By internalCardLoad=By.xpath("//div[@class='cssload-card-internal']");
	By understoodButton=By.xpath("//button[@class='EDDM-understood-button']");
	By deleteArtworkFile=By.xpath("//div[@id='galleryGridContainer']/div/div/div[2]/../div[@class='delete-icon-container']/img");
	By thankYouMessage=By.xpath("//p[text()='Thank you!']");
	By UnderstoodMessage1=By.xpath("(//div[@id='modalWrapper'])[2]//p[1]");
	By UnderstoodMessage2=By.xpath("(//div[@id='modalWrapper'])[2]//p[2]");
	By popUpDismiss=By.xpath("//button[@aria-label='Dismiss']/div");
	By frameForPopUp=By.xpath("//iframe[@title='chat widget']");
	By confirmationPopup=By.xpath("//div [@class='confirm-print-description']/ancestor::div[contains(@class,'confirm-print-modal-wrapper')]");
	By printPopup=By.xpath("//div[contains(@class,'modal-ok-to-print-popup')]");
	By reviewArtworkOnTemplateButton=By.xpath("//button[text()='review artwork on template']"); 
	By returnToMediaLibraryButton=By.xpath("//button[text()='Return to Media Library']"); 
	By approveAllArtworkForProductionButton=By.xpath("//button[text()='Approve All Artwork For Production']");
	By editToolTipTabApproveButton=By.xpath("//button[text()='Approve all artwork for production']");
	By shipmentApproved=By.xpath("(//div[contains(@class,'rounded MuiPaper-elevation1 MuiPaper-rounded')])[2]");
	By approvedLockButton=By.xpath("//button[contains(@class,'button active-gray')]//span[text()='APPROVED']");
	By unlockYesCheckBox=By.xpath("//span[text()='Yes, unlock this shipment.']/..//input");
	By unlockYesText=By.xpath("//span[text()='Yes, unlock this shipment.']");
	By yesForUnlockButton=By.xpath("//button[contains(@class,'submit')]");
	By noForUnlockButton=By.xpath("//button[@class='cancel']");
	By shipmentApprovedText=By.xpath("//p[text()='Shipment ']");
	By confirmArtwork=By.xpath("//span[@class='rule-span-text']//span");
	By confirmCheckBox=By.xpath("//input[@type='checkbox']");
	By submitButton=By.xpath("//button[contains(@class,'submit')]");
	By okButton=By.xpath("//button[text()='OK']");
	By deleteImgFromSidebar=By.xpath("//div[@style='position: relative;']//div/img");
	By shipments=By.xpath("//p[text()='Shipment ']");
	By sets=By.xpath("//p[text()='Set ']");
	By loadingContent=By.xpath("//div[@class='loading-content']");		
	By readyForPDFProof=By.xpath("//span[text()='READY FOR PDF PROOF']/..");
	By ruleText=By.xpath("//span[@class='rule-text']");
	By checkBoxForRule=By.xpath("//span[@class='MuiIconButton-label']//input[@type='checkbox']");
	By checkBoxForApproveArtwork=By.xpath("//div[@class='rule-span-text-container']/..//input");
	By disabledApproveAllArtworkForProductionButton = By.xpath("//button[text()='Approve all artwork for production' and @title='Not all files have been added.']");
	By disabledSubmitAllForPDFProofButton=By.xpath("//button[text()='Submit all for PDF Proof' and @title='Not all files have been added.']");
	By activeSubmitAllForPDFProofButton=By.xpath("//button[text()='Submit all for PDF Proof']");
	By projectSummaryLink=By.linkText("Project Summary");
	By tATInArtworkPage=By.xpath("//p[text()='Turnaround Time - ']");
	By quantityInArtworkPage=By.xpath("//p[text()='Runsize']");
	
	public void closeChatPopup(String product) throws InterruptedException {
		waitForElementToBeInVisible(custom_findElements(spinner));
		if(product.contains("EDDM") || product.contains("Direct Mail")) 
			switchToArtworkFrameForDM_EDDM();
		else 
			switchToArtworkFrameBeforePlaceOrder();

		if(custom_findElements(frameForPopUp).size()>0) {
			switchToFrame_custom(custom_findElement(frameForPopUp));
			click_custom(custom_findElement(popUpDismiss), "Close Chat Popup");
			switchToDefaultContent_custom();
		}
	}
	
	public void validateTATAndQuantityInArtworkPage(SoftAssert sa) throws InterruptedException{
		waitFor(2000);
		if(custom_findElements(tATInArtworkPage).size()==0 && custom_findElements(quantityInArtworkPage).size()==0) {
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS,"TAT and Quantity is not get displayed on Artwork Page");
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,"TAT dropdown is get displayed on Artwork Page");
			sa.fail();
		}
	}
	
	//click Project Summary Link 
	public void clickProjectSummary() {
		click_custom(custom_findElement(projectSummaryLink), "Project Summary");
	}
	
	public void validateReadyForPDFProofButton(SoftAssert sa) {
		sa.assertTrue(custom_findElement(readyForPDFProof).isEnabled());
	}
	
	//wait for images loaded on page
	public void waitForFirstImageLoad() {
		waitForElementToBeVisible(custom_findElement(sourcePath));
	}
	
	//click Yes Unlock this shipment checkbox
	public void clickUnlockYesCheckbox(SoftAssert sa) {
		WebElement yesButtonForUnlock = custom_findElement(yesForUnlockButton);
		if(yesButtonForUnlock.getAttribute("class").contains("disbaled")) {
			ExtentFactory.getInstance().getExtent().log(Status.PASS, yesButtonForUnlock.getText()+ " Button is disabled");
			sa.assertTrue(true);
		}
		WebElement unlockYes = custom_findElement(unlockYesCheckBox);
		click_javascript(unlockYes, getText_custom(custom_findElement(unlockYesText)));
	}
	
	//click Yes button for Unlock this shipment 
	public void clickYesButtonForUnlock(SoftAssert sa) {
		WebElement yesButtonForUnlock = custom_findElement(yesForUnlockButton);
		if(!(yesButtonForUnlock.getAttribute("class").contains("disbaled"))) {
			click_javascript(yesButtonForUnlock, yesButtonForUnlock.getText());
			if(custom_findElements(shipmentApprovedText).size()==0) {
				sa.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "Shipment Unlock Successfully!");
			}
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, getText_custom(yesButtonForUnlock)+ " Button is disabled");
			sa.fail();
		}
	}
	
	//click Shipment approved expand arrow
	public void clickShipmentApprovedExpandIcon() {
		List<WebElement> shipmentExpandIcon = custom_findElements(shipmentApproved);
		if(shipmentExpandIcon.size()>0)
			click_javascript(shipmentExpandIcon.get(0), "Expand arrow");
	}
	
	//click Shipment approved expand arrow
		public void clickApprovedLockButton() {
			click_javascript(custom_findElement(approvedLockButton), "Approved Lock Button");
		}
	
	//for click Approve all artwork for production button
		public void clickApproveArtwork() throws InterruptedException{
			waitFor(1000);
			WebElement approveArtwork = custom_findElement(approveArtworkButton);
			waitForElementToBeVisible(approveArtwork);
			click_custom(approveArtwork, getText_custom(approveArtwork));
		}
	
	//switch to the frame 
		public void switchToArtworkFrameFromMyMedia() throws InterruptedException{
			waitForElementToBeInVisible(custom_findElements(spinner));
			switchToFrame_custom(custom_findElement(artworkFrameForMyMedia));
		}
		
		
	//enter inside the frame 
	public void switchToArtworkFrameBeforePlaceOrder() throws InterruptedException{
		switchToFrame_custom(custom_findElement(artworkFrameBeforePlaceOrder));
	}
	
	//enter inside the frame 
		public void switchToArtworkFrameAfterPlaceOrder() throws InterruptedException{
			switchToFrame_custom(custom_findElement(artworkFrameAfterPlaceOrder));
		}
	
	//enter inside the frame for Direct Mail Products and EDDM Products
		public void switchToArtworkFrameForDM_EDDM(){
			switchToFrame_custom(custom_findElement(artworkFrameForDM_EDDM));
		}
	
	//for upload file using click here link
	public void uploadFileUsingClickHere(String filePath) throws InterruptedException, AWTException{
		click_javascript(custom_findElement(filesHere),"Click Here");
		DriverFactory.getInstance().getDriver().switchTo().activeElement().sendKeys(filePath);
		closeLocalWindow();
	}
	
	/*
	 * Below method can be used to drag and drop files on the Artwork page 
	 */
	public void dragAndDropFiles(String filePath,String file) throws InterruptedException, AWTException {
//		uploadFileUsingClickHere(filePath);
//		waitForElementToBeInVisible(custom_findElements(itemLoad));
//		waitForElementToBeInVisible(custom_findElements(internalCardLoad));
		if(file.contains("pdf") || file.contains("jpeg") || file.contains("png")) {
			if(custom_findElements(sourcePath).size()>0) {
				for(WebElement elementDest:custom_findElements(destinationPath_ImagesForCommonArea)) 
					dragAndDrop_custom(custom_findElements(sourcePath).get(0),elementDest);
			}
		}
		else if(file.contains("csv")) {
			if(custom_findElements(sourcePathForCSV).size()>0) {
				for(WebElement elementDest:custom_findElements(destinationPath_CSVForCommonArea)) 
					dragAndDrop_custom(custom_findElements(sourcePathForCSV).get(0),elementDest);
			}
		}
		else {
			uploadFileUsingClickHere(filePath);
			waitForElementToBeInVisible(custom_findElements(itemLoad));
			waitForElementToBeInVisible(custom_findElements(internalCardLoad));
			dragAndDropFiles(filePath,file);
		}
	}
	
	public void validatePDFProofsInArtworkPage(String filePath,String file,String expectedText,SoftAssert sa) throws InterruptedException, AWTException {
		if(custom_findElements(reviewArtworkOnTemplateButton).size()==0 && isElementPresent_custom(custom_findElement(disabledApproveAllArtworkForProductionButton)) && getAttribute_custom(custom_findElement(readyForPDFProof),"class").contains("transparent")) {
			Assert.assertTrue(true);
			dragAndDropFiles(filePath,file);
			waitForFileAnalyzeToDisappear();
			if(getAttribute_custom(custom_findElement(readyForPDFProof),"class").contains("active-blue") && custom_findElements(printPopup).size()>0) {
				sa.assertEquals(expectedText, getText_custom(custom_findElement(ruleText)));
				ExtentFactory.getInstance().getExtent().log(Status.PASS, expectedText+" matched with "+getText_custom(custom_findElement(ruleText)));
				if(!(custom_findElement(checkBoxForRule).isSelected())) {
					click_javascript(custom_findElement(checkBoxForRule), getText_custom(custom_findElement(ruleText)));
					if(!(custom_findElement(submitButton).getAttribute("class").contains("disbaled"))) 
						click_javascript(custom_findElement(submitButton), "Submit Button");
				}
			}
		}
	}
	
	public void validatePDFProofsInArtworkPage1(String filePath,String file,String expectedText,SoftAssert sa) throws InterruptedException, AWTException {
		if(custom_findElements(reviewArtworkOnTemplateButton).size()==0 && custom_findElements(readyForPDFProof).size()==0 && isElementPresent_custom(custom_findElement(disabledSubmitAllForPDFProofButton))) {
			Assert.assertTrue(true);
			dragAndDropFiles(filePath,file);
			waitForFileAnalyzeToDisappear();
			if(custom_findElements(activeSubmitAllForPDFProofButton).size()>0 && custom_findElements(printPopup).size()>0) {
				sa.assertEquals(expectedText, getText_custom(custom_findElement(ruleText)));
				ExtentFactory.getInstance().getExtent().log(Status.PASS, expectedText+" matched with "+getText_custom(custom_findElement(ruleText)));
				if(!(custom_findElement(checkBoxForRule).isSelected())) {
					click_javascript(custom_findElement(checkBoxForRule), getText_custom(custom_findElement(ruleText)));
					if(!(custom_findElement(submitButton).getAttribute("class").contains("disbaled"))) 
						click_javascript(custom_findElement(submitButton), "Submit Button");
				}
			}
		}
	}
		
	//for validate boxes product in artwork page
	public void validateBoxesProductsInArtwork(String filePath,String file,String expectedText, SoftAssert sa) throws InterruptedException, AWTException {
		if(custom_findElements(sourcePath).size()>0) {
			int counter=custom_findElements(sourcePath).size();//3
			uploadFileUsingClickHere(filePath);
			waitForElementToBeInVisible(custom_findElements(itemLoad));
			waitForElementToBeInVisible(custom_findElements(internalCardLoad));
			sa.assertTrue(counter<custom_findElements(sourcePath).size());
			dragAndDropFiles(filePath,file);
			waitForFileAnalyzeToDisappear();
			if(custom_findElements(confirmArtwork).size()>0) {
				sa.assertTrue(true);
				String actualText=getText_custom(custom_findElement(confirmArtwork));
				sa.assertEquals(expectedText, actualText);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, actualText+" message is displayed");
				if(!(custom_findElement(confirmCheckBox).isSelected())) {
					if(custom_findElement(submitButton).getAttribute("class").contains("disbaled")) {
						sa.assertTrue(true);
						ExtentFactory.getInstance().getExtent().log(Status.PASS, getText_custom(custom_findElement(submitButton))+" is disabled");
					}
					else 
						sa.fail();
					click_javascript(custom_findElement(confirmCheckBox), actualText);
					if(!(custom_findElement(submitButton).getAttribute("class").contains("disbaled"))) {
						sa.assertTrue(true);
						ExtentFactory.getInstance().getExtent().log(Status.PASS, getText_custom(custom_findElement(submitButton))+" is enabled");
						click_javascript(custom_findElement(submitButton), "Submit button");
					}
				}
			} 
			else 
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Drag and Drop failed");
		}
	}
			
	//click toolbar Fit to template
	public void toolBarForFitToTemplate() {
		click_custom(custom_findElement(fitToTemplateIcon), "Fit To Template Icon");
	}
	
	//check rotate template icon is disabled 
	public void rotateTemplateIconIsDisabled() throws InterruptedException {
		WebElement rotateTemplateElement = custom_findElement(rotateTemplateIcon);
		waitFor(5000);
		waitForElementToBeClickable(rotateTemplateElement);
			if(rotateTemplateElement.getAttribute("id").contains("disabled")) {
				Assert.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "Template Rotation "+rotateTemplateElement.getAttribute("class"));
			}
			else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Template Rotation "+rotateTemplateElement.getAttribute("class"));
				Assert.fail();
			}
		}
		
	
	//check rotate template icon is enabled 
	public void rotateTemplateIconIsEnabled(String shape) throws InterruptedException {
		waitFor(5000);
		WebElement rotateTemplateElement = custom_findElement(rotateTemplateIcon);
		waitForElementToBeClickable(rotateTemplateElement);
		if(shape.equalsIgnoreCase("Square")) {
			if(rotateTemplateElement.getAttribute("id").contains("disabled")) {
				Assert.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "Template Rotation "+rotateTemplateElement.getAttribute("class"));
				
			}
			else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Template Rotation "+rotateTemplateElement.getAttribute("class"));
				Assert.fail();
			}
		}
		else {
			if(rotateTemplateElement.getAttribute("id").contains("disabled")) {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Template Rotation "+rotateTemplateElement.getAttribute("class"));
				Assert.fail();
			}
			else {
				Assert.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "Template Rotation "+rotateTemplateElement.getAttribute("class"));
			}
		}
	}
	
	//check Rotate Template Icon is enabled
	public void rotateTemplateIconIsEnabled() throws InterruptedException {
		waitFor(5000);
		WebElement rotateTemplateElement = custom_findElement(rotateTemplateIcon);
		waitForElementToBeClickable(rotateTemplateElement);
		if(rotateTemplateElement.getAttribute("id").contains("disabled")) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Template Rotation "+rotateTemplateElement.getAttribute("class"));
			Assert.fail();
		}
		else {
			Assert.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Template Rotation "+rotateTemplateElement.getAttribute("class"));
		}
	}
	
	//click Rotate Artwork Left 
	public void toolBarForRotateArtworkLeft() {
		click_custom(custom_findElement(rotateArtworkLeft), "Rotate Artwork Left");
	}
	
	//click Rotate Artwork Right
	public void toolBarForRotateArtworkRight() {
		click_custom(custom_findElement(rotateArtworkRight), "Rotate Artwork Right");
	}
	
	//click Ok To Print button
	public void clickOkToPrintButton() {
		Assert.assertTrue(custom_findElement(okToPrintButton).isEnabled());
		click_javascript(custom_findElement(okToPrintButton), "OK TO PRINT Button");
	}	

	//click all checkbox
	public void clickCheckbox() {
	for(WebElement checkBox:custom_findElements(checkBoxClick)) {
		click_javascript(checkBox, "Checkbox");
	}
	}
	
	public void waitForFileAnalyzeToDisappear() {
//		wait for File analyzer to disappear
		if(custom_findElements(fileAnalyze).size()>0) {
			waitForElementToBeInVisible(custom_findElements(fileAnalyze));
		}
	}
	
	//check confirmation popup displayed or not
	public void validateConfirmationPopup(SoftAssert sa) {
//		wait for File analyzer to disappear
		waitForFileAnalyzeToDisappear();
//		Validate the presence of Confirmation popup
		if(custom_findElements(confirmationPopup).size()>0) {
			sa.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "confirmation pop up is displayed");
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "confirmation pop up is not displayed");
			sa.fail();
		}
	}
	
	//check Review Artwork on Template button functionality
	public void validateReviewArtworkOnTemplateButton(SoftAssert sa) {
		WebElement reviewArtworkOnTemplateButtonElement = custom_findElement(reviewArtworkOnTemplateButton);
		if(reviewArtworkOnTemplateButtonElement.isEnabled()) {
			click_javascript(reviewArtworkOnTemplateButtonElement, reviewArtworkOnTemplateButtonElement.getText());
			if(custom_findElements(templatePage).size()>0) {
				sa.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "It takes user to template page in order to edit the uploaded artwork.");
			}
			else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "It does not take user to template page in order to edit the uploaded artwork.");
				sa.fail();
			}
		}
	}
	
	//check Return to media library button functionality
		public void validateReturnToMediaLibraryButton(SoftAssert sa) {
			WebElement returnToMediaLibraryButtonElement = custom_findElement(returnToMediaLibraryButton);
			if(returnToMediaLibraryButtonElement.isEnabled()) {
				click_javascript(returnToMediaLibraryButtonElement, getText_custom(returnToMediaLibraryButtonElement));
				if(custom_findElements(filesHere).size()>0) {
					sa.assertTrue(true);
					ExtentFactory.getInstance().getExtent().log(Status.PASS, " It takes user to media library");
				}
				else {
					ExtentFactory.getInstance().getExtent().log(Status.FAIL, " It does not take user to media library");
					sa.fail();
				}
			}
		}
	
		
		//check Approve all artwork for production button functionality
		public void validateApproveAllArtworkForProductionButtonFunctionality1(SoftAssert sa) throws InterruptedException {
			WebElement approveAllArtworkForProductionButtonWebElement = custom_findElement(approveAllArtworkForProductionButton);
			//ApproveAllArtworkForProductionButton should be disabled
			if(approveAllArtworkForProductionButtonWebElement.getAttribute("class").contains("disbaled")) {
				sa.assertTrue(true);
				ExtentFactory.getInstance().getExtent().log(Status.PASS, getText_custom(approveAllArtworkForProductionButtonWebElement)+" is disabled");
			}
			else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, getText_custom(approveAllArtworkForProductionButtonWebElement)+" is enabled");
				sa.fail();
			}
			//			check all the 3 available checkboxes
			selectAllTheCheckboxes();
			/*
			 * check line 457 and remove it, if required
			 */
			//			approveAllArtworkForProductionButtonWebElement = custom_findElement(approveAllArtworkForProductionButton);
			if(!(approveAllArtworkForProductionButtonWebElement.getAttribute("class").contains("disbaled"))) {
				//click Approve All Artwork For Production Button
				click_javascript(approveAllArtworkForProductionButtonWebElement, "Button clicked successfully!");
				//check Thank You Message is display or not  
				if(custom_findElements(thankYouMessage).size()>0) {
					sa.assertTrue(true);
					ExtentFactory.getInstance().getExtent().log(Status.PASS, " It approves all the uploaded artwork for production.");
				}
				else {
					ExtentFactory.getInstance().getExtent().log(Status.FAIL, " It does not approve all the uploaded artwork for production.");
					sa.fail();
				}
			}
		}
		
		//check Approve all artwork for production button functionality
		public void validateApproveAllArtworkForProductionButtonFunctionality(SoftAssert sa) throws InterruptedException {
			for(WebElement approveAllArtworkForProductionButtonWebElement:custom_findElements(approveAllArtworkForProductionButton)) {
				//ApproveAllArtworkForProductionButton should be disabled
				if(approveAllArtworkForProductionButtonWebElement.getAttribute("class").contains("disbaled")) {
					sa.assertTrue(true);
					ExtentFactory.getInstance().getExtent().log(Status.PASS, getText_custom(approveAllArtworkForProductionButtonWebElement)+" is disabled");
					break;
				}
				else {
					ExtentFactory.getInstance().getExtent().log(Status.FAIL, getText_custom(approveAllArtworkForProductionButtonWebElement)+" is enabled");
					sa.fail();
				}
			}
			//check all the 3 available checkboxes
			selectAllTheCheckboxes();
			for(WebElement approveAllArtworkForProductionButtonWebElement:custom_findElements(approveAllArtworkForProductionButton)) {
				if(!(approveAllArtworkForProductionButtonWebElement.getAttribute("class").contains("disbaled"))) {
					//click Approve All Artwork For Production Button
					click_javascript(approveAllArtworkForProductionButtonWebElement, "Button clicked successfully!");
					//check Thank You Message is display or not  
					if(custom_findElements(thankYouMessage).size()>0) {
						sa.assertTrue(true);
						ExtentFactory.getInstance().getExtent().log(Status.PASS, " It approves all the uploaded artwork for production.");
						break;
					}
					else {
						ExtentFactory.getInstance().getExtent().log(Status.FAIL, " It does not approve all the uploaded artwork for production.");
						sa.fail();
					}
				}
			}
		}

			//click all checkbox
		public void selectAllTheCheckboxes() throws InterruptedException {
				for(int i=1;i<=custom_findElements(checkBoxForApproveArtwork).size();i++) {
					By checkBoxesText=By.xpath("(//div[@class='rule-span-text-container']/..//input/../../following-sibling::div/span/span[not(@class='sub-text')])["+i+"]");
					By selectCheckBox=By.xpath("(//div[@class='rule-span-text-container']/..//input)["+i+"]");
					click_javascript(custom_findElement(selectCheckBox), getText_custom(custom_findElement(checkBoxesText))+" Checkbox");
				}
		}

			//click Ready for Production button
			public void clickReadyForProduction() throws InterruptedException {
				click_javascript(custom_findElement(readyForProductionButton), "Ready For Production button");
			}

			//click Ok button if Thank you message available
			public void clickOkButtonOnTheArtworkModalPopup() throws InterruptedException {
				waitForElementToBeClickable(custom_findElement(oKButton));
				if(custom_findElement(thankYouMessage).isDisplayed()) {
					click_javascript(custom_findElement(oKButton), "OK Button");
					Assert.assertTrue(true);
				}
				else {
					ExtentFactory.getInstance().getExtent().log(Status.FAIL, custom_findElement(thankYouMessage)+" message is not displayed!");
					Assert.fail();
				}
			}

			//click close Artwork sign 
			public void closeArtworkPage() {
				click_custom(custom_findElement(closeArtworkButton), "Close icon on the Artwork page");
			}	

			//click Understood button if it is available
			public void clickUnderstoodButton(String expectedText, SoftAssert sa) throws InterruptedException {
				if(custom_findElement(understoodButton).isDisplayed()) {
					WebElement UnderstoodMessageLine1= custom_findElement(UnderstoodMessage1);
					WebElement UnderstoodMessageLine2= custom_findElement(UnderstoodMessage2);
					String actualText=getText_custom(UnderstoodMessageLine1).trim()+" "+getText_custom(UnderstoodMessageLine2).trim();
					sa.assertEquals(expectedText, actualText);
					click_custom(custom_findElement(understoodButton), actualText);
				}
			}

			//delete files if file is more than one
			public void deleteArtworkFiles() throws InterruptedException {
				List<WebElement> artworkFiles = custom_findElements(sourcePath);
				for(int i=artworkFiles.size()-1;i>=2;i--) {
					moveToElement_custom(artworkFiles.get(i),"File");
					click_custom(custom_findElement(deleteArtworkFile), "Delete File");
					waitFor(2000);
				}
			}

			//delete files if file is more than one
			public void deleteArtworkFileFromSidebar() throws InterruptedException {
				moveToElement_custom(custom_findElement(destinationPath_ImagesForCommonArea), "File");
				if(custom_findElement(deleteImgFromSidebar).isDisplayed()) {
					click_custom(custom_findElement(deleteImgFromSidebar), "Delete File");
				}
			}

			// validate 
			public void validateSetsAndShipmentsCount(int totalSets, int totalShipments,SoftAssert sa) {
				int setsCount=custom_findElements(sets).size();
				int shipmentsCount = custom_findElements(shipments).size();
				if(totalSets==setsCount) {
					sa.assertTrue(true);
					ExtentFactory.getInstance().getExtent().log(Status.PASS, "Sets matched with Sets and Shipping Page Sets, Total Sets: "+setsCount);
				}
				else {
					ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Sets did not match with Sets and Shipping Page Sets, Total Sets: "+setsCount);
					sa.fail();
				}

				if(totalShipments==shipmentsCount) {
					sa.assertTrue(true);
					ExtentFactory.getInstance().getExtent().log(Status.PASS, "Shipments matched with Sets and Shipping Page Shipment, Total Shipment: "+shipmentsCount);
				}
				else {
					ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Shipments did not match with Sets and Shipping Page Sets, Total Shipment: "+shipmentsCount);
					sa.fail();
				}
			}
}
