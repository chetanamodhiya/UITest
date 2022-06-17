package com.magento.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class CustomerAccountPage extends TestBase{
	
	By orderPlaced=By.xpath("//div[@class='orders-succeed']");
	By orderLink=By.xpath("//div[@class='order-id']//a");
	By myOrdersLink=By.linkText("My Orders");
	By jobId=By.xpath("(//h4[text()='Job ID']/../../..//span)[2]");
	By customerName=By.xpath("//div[@class='welcome-customer-title top-banner-block']");
	By orderId=By.xpath("//div[@class='order-id']/a");
	By uploadArtworkButton=By.xpath("//a[@class='action btn-upload2 btn-small artwork-upload']");
	By savedProjectLink=By.xpath("//a[text()='Saved Projects']");
	By viewSavedProject=By.xpath("//td[@class='col id']");
	By addressBook=By.xpath("//a[text()='Address Book']");
	By company=By.xpath("//a[text()='Company Profile']");
	By accountInfo=By.xpath("//strong[text()='Account Information']/../..//p");
	By legalAddress=By.xpath("//strong[text()='Legal Address']/../..//address");
	By selectSize=By.xpath("//span[text()='Size']/..//select");
	By emailCheckBox=By.xpath("//input[@type='checkbox']/..//span[text()='Change Email']");
	By emailTextBox=By.xpath("//span[text()='Email']/../..//div[contains(@class,'input-text input-placeholder')]");
	By emailField=By.xpath("//span[text()='Email']/../..");
	
	public void validateEmailAddress() {
		if(custom_findElements(emailCheckBox).size()==0 && getAttribute_custom(custom_findElement(emailTextBox), "class").contains("disabled")
			&& (!getAttribute_custom(custom_findElement(emailField), "class").contains("required"))) {
			Assert.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Change Email Checkbox is removed, Email Textbox is disabled and red asterisk sign is not available on Email Label");
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Email functionality not working as expected"); 
			Assert.fail();
		}
	}
	
	
	public void viewAccountInformation() {
		WebElement accountDetails = custom_findElement(accountInfo);
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Account Information : "+accountDetails.getText());
	}
	
	public void viewLegalAddress() {
		WebElement legalAddressDetails = custom_findElement(legalAddress);
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Legal Address : "+legalAddressDetails.getText());
	}
	
	public void clickCompanyProfile() {
		click_custom(custom_findElement(company), "Company Profile");
	}
	
	public void clickAddressBook() {
		waitForElementToBeClickable(custom_findElement(addressBook));
		click_custom(custom_findElement(addressBook), "Address Book link");
	}
	
	public void clickMyOrders() {
		click_custom(custom_findElement(myOrdersLink), "My Orders");
	}
	
	public void orderPlacedConfirmation() throws InterruptedException {
		waitForElementToBeVisible(custom_findElement(orderPlaced));
		Assert.assertTrue(custom_findElement(orderPlaced).isDisplayed());
	}

	public void clickOrderLink() {
		click_custom(custom_findElement(orderLink), "Order Link");
	}
	
	public void validateFirstName(String fname,SoftAssert sa) throws InterruptedException {
		waitFor(2000);
		String customer=getText_custom(custom_findElement(customerName));
		String c1[]=customer.split(" ");
		String firstName=c1[1];
		sa.assertEquals(fname, firstName);
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "Customer FirstName : "+firstName);
	}
	
	public void validateLastName(String lname,SoftAssert sa) {
		String customer=getText_custom(custom_findElement(customerName));
		String c1[]=customer.split(" ");
		String lastName=c1[2];
		sa.assertEquals(lname, lastName);
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "Customer LatstName : "+lastName);
	}
	
	public void validateOrderConfirmationPage() throws InterruptedException {
		waitFor(1000);
		waitForElementToBeVisible(custom_findElement(orderId));
		WebElement order = custom_findElement(orderId);
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Customer Order Id : "+order.getText());
	}
	
	public void clickUploadArtwork() throws InterruptedException {
		waitFor(1000);
		waitForElementToBeVisible(custom_findElement(uploadArtworkButton));
		click_custom(custom_findElement(uploadArtworkButton), "Upload Artwork");
	}
	
	//click Saved Projects link
	public void clickSavedProjects() {
		if(custom_findElement(uploadArtworkButton).isDisplayed())
		click_custom(custom_findElement(savedProjectLink), custom_findElement(savedProjectLink).getText());
	}
	
	//click Saved Projects link
	public void viewSavedProjects() {
		List<WebElement> savedProject = custom_findElements(viewSavedProject);
		for(WebElement project:savedProject)
			ExtentFactory.getInstance().getExtent().log(Status.INFO,project.getText());
	}
	
	//Edit Saved Projects 
	public void editSavedProjects(String projectName) {
		By project=By.xpath("//td[text()='"+projectName+"']");
		By editSavedProject=By.xpath("//td[text()='"+projectName+"']/..//a[@class='action edit']");
		click_javascript(custom_findElement(editSavedProject), "Project "+custom_findElement(project).getText()+" for "+custom_findElement(editSavedProject).getText());
		if(custom_findElement(selectSize).isDisplayed()) {
			Assert.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS,"User is able to the redirect to the PDP of the product for which the Project Saved");
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.PASS,"User do not able to the redirect to the PDP of the product for which the Project Saved");
			Assert.fail();
		}
	}
	
	//delete Saved Projects 
	public void deleteSavedProjects(String projectName) {
		By deleteSavedProject=By.xpath("//td[text()='"+projectName+"']/..//a[@class='action delete']");
		click_javascript(custom_findElement(deleteSavedProject), custom_findElement(deleteSavedProject).getText());
		By deleteMessage=By.xpath("//div[text()='"+projectName+" project bookmark was deleted successfully.']");
		if(custom_findElement(deleteMessage).isDisplayed()) {
			Assert.assertTrue(true);
			ExtentFactory.getInstance().getExtent().log(Status.PASS,custom_findElement(deleteMessage).getText());
		}
	}
}


