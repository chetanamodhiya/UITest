package com.magento.reusableComponents;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import com.aventstack.extentreports.Status;
import com.google.common.base.Function;
import com.magento.testBase.DriverFactory;
import com.magento.testBase.ExtentFactory;

/**
 * @author: Mohan N
 */
public class CommonUtility {

	List<String> allWindowHandlesList;
	
	//Custom sendKeys method-> To log sendKeys message for every occurrence.
	public void sendKeys_custom(WebElement element, String valueToBeSent, String fieldName) {
		try {
			element.clear();
			element.sendKeys(valueToBeSent);
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Entered value as: "+valueToBeSent);
			Assert.assertTrue(true);
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Value enter in field: "+fieldName + " is failed due to exception: "+e);
		}
	}
	
	//custom click method to log every click action
	public void click_custom(WebElement element, String fieldName) {
		try {
			element.click();
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Clicked Successfully! ");
			Assert.assertTrue(true);
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to click on field: " +fieldName +" due to exception: "+e);
		}
	}

	//javascript click method to log every click action
	public void click_javascript(WebElement element, String fieldName) {
		try {
			((JavascriptExecutor)DriverFactory.getInstance().getDriver()).executeScript("arguments[0].click();", element);
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Clicked Successfully! ");
			Assert.assertTrue(true);
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to click on field: " +fieldName +" due to exception: "+e);
		}
	}

	//clear data from field
	public void clear_custom(WebElement element,String fieldName) {
		try {
			element.clear();
			Thread.sleep(250);
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Data Cleared Successfully! ");
			Assert.assertTrue(true);
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to clear Data on field: " +fieldName +" due to exception: "+e);
		} 
	}

	//custom mouseHover 
	public void moveToElement_custom(WebElement element,String fieldName){
		try{
			JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
			Actions actions = new Actions(DriverFactory.getInstance().getDriver());		
			actions.moveToElement(element).build().perform();
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Mouse hovered Successfully! ");
			Thread.sleep(1000);
			Assert.assertTrue(true);
		}catch(Exception e){
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to hover mouse on field: " +fieldName +" due to exception: "+e);
		}
	}

	//Select dropdown value by visibleText
	public void selectDropDownByVisibleText_custom(WebElement element, String ddVisibleText, String fieldName) throws Throwable {
		try {
			Select s = new Select(element);
			s.selectByVisibleText(ddVisibleText);
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Dropdown Value Selected by visible text: "+ ddVisibleText);
			Assert.assertTrue(true);
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown value not selected for field: " +fieldName +"  due to exception: "+e);
		}
	}
	
		public void selectDropDownByPartialText_custom(WebElement element, String ddVisibleText, String fieldName) throws Throwable {
			try {
				Select s = new Select(element);
				for(WebElement option: s.getOptions())
				{
					String optionText=option.getText();
					if(optionText.contains(ddVisibleText))
					{
						s.selectByVisibleText(optionText);
						//log success message in extent report
						ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Dropdown Value Selected by visible text: "+ optionText);
						break;
					}
				}
			} catch (Exception e) {
				//log failure message in extent report
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown value not selected for field: " +fieldName +"  due to exception: "+e);
			}
		}

	//Select dropdown value by value
	public void selectDropDownByValue_custom(WebElement element, String ddValue, String fieldName) throws Throwable {
		try {
			Select s = new Select(element);
			s.selectByValue(ddValue);
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Dropdown Value Selected by visible text: "+ ddValue);
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown value not selected for field: " +fieldName +"  due to exception: "+e);
		}
	}
	
	//Select dropdown value by first index
		public void selectSecondOptionFromDropDown(WebElement element, String fieldName) throws Throwable {
			try {
				Select s = new Select(element);
				s.selectByIndex(1);
				//log success message in extent report
				ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName+"==> Dropdown Value Selected by index: "+s.getFirstSelectedOption().getText());
				Assert.assertTrue(true);
			} catch (Exception e) {
				//log failure message in extent report
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Dropdown value not selected for field: " +fieldName+"  due to exception: "+e);
				Assert.fail();
			}
		}


	//get all the options from select dropdown
	public List<WebElement> getOptions(Select selectDropdown) {
		return selectDropdown.getOptions();
	}
	
	//get all the options from select dropdown and store it into a ArrayList
		public List<String> getOptionsAsArrayList1(WebElement element) {
			List<String> l=new ArrayList<String>();
			Select selectDropdown=new Select(element);
			List<WebElement> elements=selectDropdown.getOptions();
			for(WebElement list:elements) {
				l.add(list.getText());
			}
			return l;
		}
		
	//get all the options from select dropdown and store it into a ArrayList
		public List<String> getOptionsAsArrayList(WebElement element) {
			List<String> list=new ArrayList<String>();
			Select selectDropdown=new Select(element);
			List<WebElement> elements=selectDropdown.getOptions();
			for(int i=1;i<elements.size();i++) {
				list.add(elements.get(i).getText());
			}
			return list;
		}
		
	//get Page URL
	public String getURL_custom() {
		return DriverFactory.getInstance().getDriver().getCurrentUrl();
	}
		
//fluent wait implementation for element disable
	public boolean waitTillSpinnerDisable(WebDriver driver, By by)
	{		
		FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver);
		fWait.withTimeout(10, TimeUnit.SECONDS);
		fWait.pollingEvery(250, TimeUnit.MILLISECONDS);
		fWait.ignoring(NoSuchElementException.class);

		Function<WebDriver, Boolean> func = new Function<WebDriver, Boolean>()
		{
			@Override
			public Boolean apply(WebDriver driver) {
				WebElement element = driver.findElement(by);

				if(element.getCssValue("display").equalsIgnoreCase("none")){
					return true;}

				return false;
			}
		};
		return fWait.until(func);
	}

	//Get text from WebElement
	public String getText_custom(WebElement element) {
		String text = "";
		try {
			text = element.getText();
			//log success message in extent report
//			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Text retrived is: "+ text);
			return text;
		} catch (Exception e) {		
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Text not retrived due to exception: "+ e);
		}
		return text;
	}
	
	//Get attribute from WebElement
		public String getAttribute_custom(WebElement element, String value) {
			String attribute = "";
			try {
				attribute = element.getAttribute(value);
				//log success message in extent report
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "Value retrived is: "+ attribute);
				return attribute;
			} catch (Exception e) {		
				//log failure message in extent report
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Value not retrived due to exception: "+ e);
			}
			return attribute;
		}	
	
	

	//	Validations
	//check if element is Present
	public boolean isElementPresent_custom(WebElement element){
		boolean flag = false;
		try {
			flag = element.isDisplayed();
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Presence of field is: "+ flag);
			return flag;
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Checking for presence of field: not tested due to exception: "+e);
			return flag;
		}
	}

	//String Asserts
	public void assertEqualsString_custom(String expvalue, String actualValue, String locatorName) throws Throwable {
		try {
			if(actualValue.equals(expvalue)) {
				Assert.assertTrue(true);
				//log success message in extent report
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "String Assertion is successful on field "+ locatorName + " Expected value was: "+ expvalue + " actual value is: "+actualValue);
			}else {
				//log failure message in extent report
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "String Assertion FAILED on field "+ locatorName + " Expected value was: "+ expvalue + " actual value is: "+actualValue);
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.toString());
		}
	}


	//	WebDriverWait calls
	//	Wait for element to be clickable
	public void waitForElementToBeClickable(WebElement ele) {
		new WebDriverWait(DriverFactory.getInstance().getDriver(), 120).until(ExpectedConditions.elementToBeClickable(ele));
	}

	//	Wait for element to be visible
	public void waitForElementToBeVisible(WebElement ele) {
		new WebDriverWait(DriverFactory.getInstance().getDriver(), 360).until(ExpectedConditions.visibilityOf(ele));
	}
	
//	Wait for element to be invisible
	public void waitForElementToBeInVisible(List<WebElement> ele) {
		new WebDriverWait(DriverFactory.getInstance().getDriver(), 120).until(ExpectedConditions.invisibilityOfAllElements(ele));
	}

	//	Stop for x milliseconds
	public static void waitFor(int mSecs) throws InterruptedException {
		Thread.sleep(mSecs);
	}

//	Scroll operations
	public void scrollDown(int x,int y) {
		((JavascriptExecutor)DriverFactory.getInstance().getDriver()).executeScript("window.scrollBy("+x+","+y+")");
	}
	
	public void scrollDownToWebElement(WebElement element) {
		((JavascriptExecutor)DriverFactory.getInstance().getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void scrollUp() {
		((JavascriptExecutor)DriverFactory.getInstance().getDriver()).executeScript("window.scrollTo(0,document.body.scrollTop)");
	}
	
	public void scrollDownToTheBottom() {
		((JavascriptExecutor)DriverFactory.getInstance().getDriver()).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	

	//clear browser Cookies
	public void clearBrowserCookies() {
		DriverFactory.getInstance().getDriver().manage().deleteAllCookies();//delete all cookies
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} //wait 5 seconds to clear cookies.
	}

	//	navigate() implementations
	//for naviate to home page 
	public void navigateTo_custom(String url) throws InterruptedException {
		try {
			DriverFactory.getInstance().getDriver().navigate().to(url);
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Navigated to ==> "+url);
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to Navigate to : " +url +" due to exception: "+e);
			Assert.assertTrue(false);
		}	
	}

	//for navigate back 
	public void navigateBack_custom() {
		try {
			DriverFactory.getInstance().getDriver().navigate().back();
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Navigated Back");
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to Navigate Back: due to exception: "+e);
			Assert.assertTrue(false);
		}
	}
	
	//for switch inside the Frame
	public void switchToFrame_custom(WebElement frameElement) {
		try {
			DriverFactory.getInstance().getDriver().switchTo().frame(frameElement);
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Inside the Frame");
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to come Inside the Frame: due to exception: "+e);
			Assert.assertTrue(false);
		}
	}
	
	public void switchToDefaultContent_custom() {
		try {
			 DriverFactory.getInstance().getDriver().switchTo().defaultContent();
			//log success message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Outside the Frame");
		} catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to come Outside the Frame: due to exception: "+e);
			Assert.assertTrue(false);
		}
	}
	
	public void getLocationAndScrollToCoordinatesOfTheWebElement(WebElement ele) {
		Point point = ele.getLocation();
		scrollDown(point.getX(), point.getY());
	}

	//for Upload the artwork file from the Local Machine
	public void uploadTheFiles_custom(String fileName) throws InterruptedException, AWTException{
		Robot rb = new Robot();
		// copying File path to Clipboard
		StringSelection str = new StringSelection(fileName);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		
		// press Contol+V for pasting
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		// release Contol+V for pasting
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		
		waitFor(1000);
		// for pressing and releasing Enter
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
	}
	
//	public void dragAndDrop(WebElement from, WebElement to) {
//		JavascriptExecutor js = (JavascriptExecutor)DriverFactory.getInstance().getDriver();
//		try {
//			js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
//					+ "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
//					+ "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
//					+ "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
//					+ "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
//					+ "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
//					+ "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
//					+ "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
//					+ "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
//					+ "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
//					+ "var dropEvent = createEvent('drop');\n"
//					+ "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
//					+ "var dragEndEvent = createEvent('dragend');\n"
//					+ "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
//					+ "var source = arguments[0];\n" + "var destination = arguments[1];\n"
//					+ "simulateHTML5DragAndDrop(source,destination);", from, to);
//			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Drag and Drop File Successfully! ");
//		}
//		catch (Exception e) {
//			//log failure message in extent report
//			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to Drag and Drop File: due to exception: "+e);
//			Assert.assertTrue(false);
//		}
//	}	
	
	//for drag and drop functionality for artwork
	public void dragAndDrop_custom(WebElement from, WebElement to) {
		JavascriptExecutor js = (JavascriptExecutor)DriverFactory.getInstance().getDriver();
		try {
			js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
					+ "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
					+ "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
					+ "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
					+ "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
					+ "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
					+ "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
					+ "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
					+ "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
					+ "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
					+ "var dropEvent = createEvent('drop');\n"
					+ "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
					+ "var dragEndEvent = createEvent('dragend');\n"
					+ "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
					+ "var source = arguments[0];\n" + "var destination = arguments[1];\n"
					+ "simulateHTML5DragAndDrop(source,destination);", from, to);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, "Drag and Drop File Successfully! ");
		}
		catch (Exception e) {
			//log failure message in extent report
			ExtentFactory.getInstance().getExtent().log(Status.FAIL, "Unable to Drag and Drop File: due to exception: "+e);
			Assert.assertTrue(false);
		}
	}
	
	//custom findElements
	public List<WebElement> custom_findElements(By locator) {
		return DriverFactory.getInstance().getDriver().findElements(locator);
	}
	
	//custom findElement
	public WebElement custom_findElement(By locator) {
		return DriverFactory.getInstance().getDriver().findElement(locator);
	}
	
	
	//close local window
	public void closeLocalWindow() throws AWTException {
		Robot robot = new Robot();
	    robot.keyPress(KeyEvent.VK_ESCAPE);
	    robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
	//close Chat popup
	public void closeChatPopup(WebElement frameElement, WebElement closePopupElement) {
		if(frameElement.isDisplayed()) {
			switchToFrame_custom(frameElement);
			if(closePopupElement.isDisplayed())
				click_javascript(closePopupElement, "Dismiss popup");
			switchToDefaultContent_custom();
		}
	}
	
	public void switchToLastWindow() {
		allWindowHandlesList = new ArrayList<String>();
		Set<String> allWindowHandles = DriverFactory.getInstance().getDriver().getWindowHandles();
		Iterator<String> iterator = allWindowHandles.iterator();
		
		while(iterator.hasNext()) {
			allWindowHandlesList.add(iterator.next());
		}
		
		DriverFactory.getInstance().getDriver().switchTo().window(allWindowHandlesList.get(allWindowHandlesList.size()-1));
	}
	
	public void switchToParentWindow() {
		allWindowHandlesList = new ArrayList<String>();
		Set<String> allWindowHandles = DriverFactory.getInstance().getDriver().getWindowHandles();
		Iterator<String> iterator = allWindowHandles.iterator();
		
		while(iterator.hasNext()) {
			allWindowHandlesList.add(iterator.next());
		}
		DriverFactory.getInstance().getDriver().switchTo().window(allWindowHandlesList.get(0));
	}
	
	public static Document convertStringToDocument(String xmlString) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;

		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			return doc;
		} catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public void iterateOverAllElements(Document document) {
		NodeList nodeList = document.getElementsByTagName("*");
		  for (int i = 0; i < nodeList.getLength(); i++) {
		    Node node = nodeList.item(i);
		    if (node.getNodeType() == Node.ELEMENT_NODE) {
		      // do something with the current element
		      System.out.println(node.getNodeName());
		    }
		  }
	}
	
	public String getAttributeValueFromOrderXML(Document doc, String tagName, String attribute) {
		return doc.getElementsByTagName(tagName).item(0).getAttributes().getNamedItem(attribute).getNodeValue();
	}
}
