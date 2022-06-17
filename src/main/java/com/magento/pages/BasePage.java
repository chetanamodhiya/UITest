package com.magento.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.magento.reusableComponents.PropertiesUtility;
import com.magento.testBase.DriverFactory;
import com.magento.testBase.ExtentFactory;
import com.magento.testBase.TestBase;

public class BasePage extends TestBase{

	By signInLink=By.xpath("(//span[text()='Sign In'])[1]/..");
	By emailTextbox=By.id("login-dropdown-customer-email");
	By passwordTextbox=By.id("login-dropdown-pass");
	By signInButton=By.xpath("(//span[text()='Sign In'])[2]/..");
	By customerLoggedIn = By.id("customer-logged-in");
	By myAccountLink=By.xpath("(//li[@class='customer-welcome']//button)[1]");
	By myAccount=By.linkText("My Account");
	By accountProfile=By.linkText("Account Profile");
	By logoutLink=By.xpath("(//span[text()='Logout'])[1]");
	By liveChatButton=By.xpath("//span[text()='Live Chat']/..");
	By promoBarLink=By.xpath("//a[text()='Link']");
	By miniCartLink=By.xpath("//div[@class='minicart-wrapper']");
	By viewCartButton=By.xpath("//a[@class='action viewcart']");
	By productsWithSubCategory=By.xpath("//ul[@class='ui-menu ui-widget ui-widget-content ui-corner-all']/li/a/span[2]");
	By spinner=By.xpath("//img[@title='Loading...']");
	By productNames=By.xpath("//div[@class='minicart-wrapper active']//div//strong[@class='product-item-name']//a");
	By productList=By.xpath("//div[@class='product']");
	By removeProduct=By.xpath("//a[@class='action delete']");
	By okForRemoveButton=By.xpath("//button[@class='action-primary action-accept']");
	By emptyCart=By.xpath("//div[@class='minicart-wrapper active']//div//strong[@class='subtitle empty']");
	By loyaltyLevel=By.xpath("//div[@id='customer-logged-in']");
	By rewardsPoint=By.xpath("//div[@id='customer-logged-in']//br[2]");
	By myOrders=By.xpath("//a[text()='My Orders']");
	By search=By.xpath("//label[@class='label' and @for='search']");
	By infoClickHere=By.xpath("//a[text()='Click Here']");
	By productsCategory=By.xpath("//a[@class='level-top ui-corner-all']//span[not(@class='ui-menu-icon ui-icon ui-icon-carat-1-e')]");
	By subCategoryProducts=By.xpath("//ul[contains(@class,'submenu ui-menu ui-widget ui-widget-content ui-corner-all submenu')]//span");
	By dismissButton=By.xpath("//button[@aria-label='Dismiss']");
	By frame=By.xpath("//iframe[@title='chat widget']");
	By myMedia=By.xpath("(//a[@class='media-link'])[1]");
	
	//click My Media link
	public void clickMyMedia() throws InterruptedException {
		waitFor(2000);
		click_javascript(custom_findElement(myMedia), "My Media");
	}
	
	//close chat popup
	public void closeChatPopUpIfVisible() {
		if(custom_findElements(frame).size()>0) {
			switchToFrame_custom(custom_findElement(frame));
			if(custom_findElement(dismissButton).isDisplayed())
				click_javascript(custom_findElement(dismissButton), "Dismiss popup");
			switchToDefaultContent_custom();
		}
	}
	
	//click Search
	public void clickAccountProfile() {
		click_custom(custom_findElement(accountProfile),"Account Profile");
	}
	
	//click Search
		public void clickSearchOption() {
			click_custom(custom_findElement(search),"Search");
		}
	
	//Validate Loyalty
	public void validateLoyalty(String loyalty,SoftAssert sa) throws InterruptedException {
		String customerLoyalty=getText_custom(custom_findElement(loyaltyLevel));
		String c1[]=customerLoyalty.split(" ");
		String loyaltyLevel=c1[1];
		if(Integer.parseInt(loyalty)==2) {
			loyalty="Associate";
			sa.assertTrue(loyaltyLevel.contains(loyalty));
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Loyalty is : "+loyalty);
		}
		else if(Integer.parseInt(loyalty)==3) {
			loyalty="Admiral";
			sa.assertTrue(loyaltyLevel.contains(loyalty));
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Loyalty is : "+loyalty);
		}
		else if(Integer.parseInt(loyalty)==4) {
			loyalty="President";
			sa.assertTrue(loyaltyLevel.contains(loyalty));
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Loyalty is : "+loyalty);
		}
	}
	
	//Validate Rewards Point
	public void validateRewardsPoints(String rewardPoint, SoftAssert sa) throws InterruptedException {
		waitFor(3000);
		waitForElementToBeVisible(custom_findElement(loyaltyLevel));
		String customerLoyalty=getText_custom(custom_findElement(loyaltyLevel));
		String c1[]=customerLoyalty.split(" ");
		System.out.print(c1[2]);
		String rewards=c1[2];
		if(rewards.contains(rewardPoint)) {
			sa.assertTrue(rewards.contains(rewardPoint));
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Rewards Points : "+rewardPoint);
		}
		else if(rewardPoint.equalsIgnoreCase("0") || rewards.equalsIgnoreCase("")) {
			sa.assertTrue(rewards.contains(rewardPoint));
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Rewards Points : "+rewardPoint);
		}
		else {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Rewards Points : "+rewardPoint);
			sa.fail();
		}
	}
	
	//View Rewards Point
	public void viewRewardsPoints() throws InterruptedException {
		String customerLoyalty=getText_custom(custom_findElement(loyaltyLevel));
		String c1[]=customerLoyalty.split(" ");
		String rewards=c1[2];
		ExtentFactory.getInstance().getExtent().log(Status.INFO, "Rewards Points : "+rewards);
	}
	
	
	/*
	 * Below method accepts 2 arguments. 1st argument = category and 2nd argument = sub category
	 * mouse hover on the category product > click sub category product
	 */
	public void validateBrowseFuntionality(String productCategory, String subCategoryProduct) throws InterruptedException { 
		for(WebElement element:custom_findElements(productsCategory)) {
			if(getText_custom(element).equalsIgnoreCase(productCategory)) 
			{ 
				moveToElement_custom(element, productCategory);
				for(WebElement subCategoryelement:custom_findElements(subCategoryProducts)) {
					if(getText_custom(subCategoryelement).equalsIgnoreCase(subCategoryProduct)) {
						click_custom(subCategoryelement, subCategoryProduct); 
						subCategoryProduct=subCategoryProduct.toLowerCase();
						if(getURL_custom().contains(subCategoryProduct)) {
							Assert.assertTrue(true);
							ExtentFactory.getInstance().getExtent().log(Status.PASS, "Clicked sub-category "+subCategoryProduct+" present under "+productCategory+" category");
						}
						break;
					}
				}
				break; 
			}
		} 
	}

	/*
	 * Below method accepts 1 argument. 1st argument = category
	 * click category product
	 */
	public void clickCategory(String productCategory,String product) throws InterruptedException {
		for(WebElement element:custom_findElements(productsCategory)) {
			if(element.getText().equalsIgnoreCase(productCategory)) {
				click_javascript(element, productCategory);
				if(getURL_custom().contains(product)) {
					Assert.assertTrue(true);
					ExtentFactory.getInstance().getExtent().log(Status.PASS, "Able to click as per Category");
				}
				break;
			}
		}
	}

	//click sign in link from the header before logging in
	public void clickSignInLink() {
		click_custom(custom_findElement(signInLink), "sign in link");
	}
	
	//types email into the email textbox field in the header 
	public void enterEmail(String emailText) {
		sendKeys_custom(custom_findElement(emailTextbox), emailText, "email");
	}

	//types password into the password textbox field in the header
	public void enterPassword(String passwordText) {
		sendKeys_custom(custom_findElement(passwordTextbox), passwordText, "password");
	}

	//click sign in button in the login form from the header 
	public void clickSignInButton() {
		click_custom(custom_findElement(signInButton), "sign in button");
	}

	//click MyAccountLink from the header
	public void clickMyAccountLink() {
		waitForElementToBeClickable(custom_findElement(myAccountLink));
		click_custom(custom_findElement(myAccountLink), "my account link");
	}

	//click  click logout link from the header
	public void clickLogoutLink() {
		waitForElementToBeClickable(custom_findElement(logoutLink));
		click_custom(custom_findElement(logoutLink),"Logout Link");
		Assert.assertTrue(DriverFactory.getInstance().getDriver().getCurrentUrl().contains("logoutSuccess"));
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "User logout successfully!!!");
	}

	/*
	 * the below methods waits for the page to load after the user logs in by checking for the live chat button to be clickable. 
	 */
	public void waitForPageLoad() throws InterruptedException {
		waitFor(Integer.parseInt(PropertiesUtility.getPropertyValueByKey("waitForPageLoad")));
	}

	//login from the header
	public void loginFromHeader(String emailText, String passwordText) {
		clickSignInLink();
		enterEmail(emailText);
		enterPassword(passwordText);
		clickSignInButton();
		Assert.assertTrue(custom_findElement(myAccountLink).isDisplayed());
		ExtentFactory.getInstance().getExtent().log(Status.PASS, "User logged in successfully!!!");
	}

	//logout from the header
	public void logout() throws InterruptedException {
		waitFor(2000);
		clickMyAccountLink();
		clickLogoutLink();
	}

	//click mini cart link from the header
	public void clickMiniCart() throws InterruptedException {
		waitFor(3000);
		if(custom_findElement(miniCartLink).isDisplayed()) {
			Assert.assertTrue(true);
			click_custom(custom_findElement(miniCartLink), "Mini Cart");
		}
	}

	//click view cart button from the mini cart
	public void clickViewCartButton(String ViewCartName) {
		if(custom_findElement(viewCartButton).isDisplayed())
			click_custom(custom_findElement(viewCartButton), ViewCartName);
	}

	//click edit link of a product in mini cart
	public void clickEditLinkOfAProductInMiniCart(String product_text) {
		List<WebElement> productsName=custom_findElements(productNames);
		if(productsName.size()>0) {
			for(WebElement product:productsName) {
				if(product.getText().equalsIgnoreCase(product_text)) {
					click_custom(custom_findElement(By.xpath("//a[text()='"+product_text+"']/../../..//a[@class='action edit']")), "Edit");
					break;
				}
			}
		}
	}

	//click remove link of a product in mini cart
	public void clickRemoveLinkOfAProductInMiniCart(String product_text) throws InterruptedException {
		for(WebElement product:custom_findElements(productNames)) {
			if(product.getText().equalsIgnoreCase(product_text)) {
				click_custom(custom_findElement(By.xpath("//a[text()='"+product_text+"']/../../..//a[@class='action delete']")), "Edit");
				waitFor(2000);
				click_custom(custom_findElement(okForRemoveButton), "OK");
				break;
			}
		}
	}
	
	//View My Cart
	public void viewMyCartOption() throws InterruptedException {
		clickMiniCart();
		if(custom_findElements(productNames).size()>0) {
			List<WebElement> productsName=custom_findElements(productNames);
			for(WebElement product:productsName) {
				ExtentFactory.getInstance().getExtent().log(Status.INFO, "Product Name: "+product.getText()+" is displayed in cart");
			}
		}
		else if(custom_findElements(emptyCart).size()>0) {
			ExtentFactory.getInstance().getExtent().log(Status.INFO, custom_findElement(emptyCart).getText());
		}
	}
	
	//click edit link of a product from the cart
	public void editProductFromCart() {
		WebElement productsName=custom_findElement(productNames);
		if(productsName.isDisplayed()) {
			click_custom(custom_findElement(By.xpath("//a[text()='"+productsName.getText()+"']/../../..//a[@class='action edit']")), "Edit");
		}
	}
	
	//Remove products from the cart
		public void removeProductFromCart() throws InterruptedException {
			List<WebElement> productsName=custom_findElements(productNames);
			if(productsName.size()>0) {
				for(int i=productsName.size();i>0;i--) {
					click_custom(custom_findElement(removeProduct), "minicart's delete link ");
					waitFor(500);
					click_custom(custom_findElement(okForRemoveButton), "OK button ");
					waitFor(2000);
//					clickMiniCart();
				}
			}
			else if(custom_findElement(emptyCart).isDisplayed())
				ExtentFactory.getInstance().getExtent().log(Status.INFO, custom_findElement(emptyCart).getText());
		}
		
		//Remove products from the cart
				public void removeProductFromCart11() throws InterruptedException {
					List<WebElement> productsName=custom_findElements(productNames);
					if(productsName.size()>0) {
						for(int i=productsName.size();i>0;i--) {
							click_custom(custom_findElement(removeProduct), "minicart's delete link ");
							waitFor(1000);
							click_custom(custom_findElement(okForRemoveButton), "OK button ");
							waitFor(4000);
//							clickMiniCart();
						}
					}
					else
					{
						ExtentFactory.getInstance().getExtent().log(Status.INFO, custom_findElement(emptyCart).getText());
					}
				}
		
		
		//Remove products from the cart
		public void removeProductFromCart1() throws InterruptedException {
			if(custom_findElements(emptyCart).size()>0) {
				ExtentFactory.getInstance().getExtent().log(Status.INFO, custom_findElement(emptyCart).getText());
			}
			else if(custom_findElements(productNames).size()>0) {
				List<WebElement> productsName=custom_findElements(productNames);
				for(int i=productsName.size();i>0;i--) {
					click_custom(custom_findElement(removeProduct), "minicart's delete link ");
					waitFor(1000);
					click_custom(custom_findElement(okForRemoveButton), "OK button ");
					waitFor(4000);
//					clickMiniCart();
				}
			}
		}

	//clear the cart
	public void clearCart() throws InterruptedException {
		clickMiniCart();
		removeProductFromCart();
	}

	//click product link from the mini cart
	public void clickProductLink(String product, String productText)  {
		for(WebElement productList:custom_findElements(productNames)) {
			if(productList.getText().equals(productText)) {
				click_custom(productList, product);
				break;
			}
		}
	}

	// click on my account link after clicking on My Account from the header
	public void clickMyAccountLinkFromTheHeader() {
		clickMyAccountLink();
		click_custom(custom_findElement(myAccount), "My Account Link");
	}
	
	// click on my orders 
		public void clickMyOrders() {
			click_custom(custom_findElement(myOrders), "My Orders Link");
		}

}
