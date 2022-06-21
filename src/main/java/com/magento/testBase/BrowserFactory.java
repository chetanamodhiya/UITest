/*
 * Author: Mohan N
 */
package com.magento.testBase;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
public class BrowserFactory {
	public static final String AUTOMATE_USERNAME = "wqaoffshore_zuKpmq";
	public static final String AUTOMATE_ACCESS_KEY = "stFxGHtRoPMUkypQpBb4";
	public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";
	//create WebDriver object for given browser
	public WebDriver createBrowserInstance(String browser) throws MalformedURLException{
//		WebDriver driver = null;
		HtmlUnitDriver driver = null;
		if(browser.equalsIgnoreCase("Chrome")) {
		//	DesiredCapabilities caps = new DesiredCapabilities();
		//	caps.setCapability("resolution", "1920x1080");
			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.silentOutput", "true");
			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--incognito");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-web-security");
			options.addArguments("--disable-site-isolation-trials");
		//	options.addArguments("--disable-notifications");
			options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//			driver = new ChromeDriver(options);
			driver = new HtmlUnitDriver();
			
//			//browserstack code
//			DesiredCapabilities caps = new DesiredCapabilities();
//		    caps.setCapability("os_version", "10");
//		    caps.setCapability("resolution", "1920x1080");
//		    caps.setCapability("browser", "Chrome");
//		    caps.setCapability("browser_version", "latest");
//		    caps.setCapability("os", "Windows");
//		    caps.setCapability("browserstack.selenium_version", "3.141.59");
//		    options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
//			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//			driver = new RemoteWebDriver(new URL(URL), caps);
		}else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions fOptions = new FirefoxOptions();
			fOptions.addArguments("-private");
			fOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			driver = new FirefoxDriver(fOptions);
		}else if (browser.equalsIgnoreCase("safari")) {
			//browserstack code
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("os", "OS X");
			caps.setCapability("os_version", "Catalina");
			caps.setCapability("browser", "Safari");
			caps.setCapability("browser_version", "13.1");
			caps.setCapability("browserstack.local", "false");
			caps.setCapability("resolution", "1920x1080");
			caps.setCapability("browserstack.selenium_version", "3.141.59");
			driver = new RemoteWebDriver(new URL(URL), caps);
		}
		return driver;
	}
}