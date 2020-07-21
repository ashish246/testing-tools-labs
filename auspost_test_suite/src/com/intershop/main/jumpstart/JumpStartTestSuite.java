package com.intershop.main.jumpstart;
/*
 * 
 */
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.intershop.testframework.utils.AdvanceXMLUtil;
import com.intershop.testframework.utils.PropertyLoader;
import com.intershop.testframework.utils.TestCaseUtil;
import com.thoughtworks.selenium.SeleneseTestBase;
import com.thoughtworks.selenium.Selenium;


public class JumpStartTestSuite extends SeleneseTestBase{
	public static Selenium selenium;
	public static WebDriver driver =null;
	public String browser,version;
	
	public static String noClick="noClick";// used when verify text is to be verified on page load
	public static Properties urlProp;
	public static Properties localizationProp;
	public static Properties dataProp;
	
	String timeoutString = null,moduleName=null,methodName=null,baseWidowHandle=null;
	Boolean checkInNewWindow=false;
	TestCaseUtil testCaseUtil=null;
	LinkedHashMap<String,String> mLink=null, mElements=null;;
	DesiredCapabilities desiredCapabilities;
	FirefoxProfile firefoxProfile=null; 
	
	@BeforeClass(alwaysRun = true)
	public void setupBeforeSuite(ITestContext context) throws Exception{
		String channel = context.getCurrentXmlTest().getParameter("channel");
		AdvanceXMLUtil.loadXml(channel);
		urlProp = PropertyLoader.getUrlPropertiesFile(channel);
		dataProp = PropertyLoader.getDatapropertiesFile(channel);
		localizationProp=PropertyLoader.getLocalizationPropertiesFile(channel);
		browser = context.getCurrentXmlTest().getParameter("selenium.browser");
		version= context.getCurrentXmlTest().getParameter("selenium.browser.version");
		
		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.SEVERE);
		logs.enable(LogType.CLIENT, Level.SEVERE);
		logs.enable(LogType.BROWSER, Level.SEVERE);
		logs.enable(LogType.SERVER, Level.SEVERE);
		logs.enable(LogType.SERVER, Level.SEVERE);
		
		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.WARNING);
		
		if(browser.contains("firefox"))
		{
			//System.setProperty("-Dwebdriver.firefox.profiler", "Selenium");
			
			//firefoxProfile = new FirefoxProfile(new File("C:/Users/tripathi/AppData/Roaming/Mozilla/Firefox/Profiles/28yl6tx0.Selenium")); 
			firefoxProfile = new FirefoxProfile(); 
			//ProfilesIni allProfiles = new ProfilesIni();
			//firefoxProfile = allProfiles.getProfile("Selenium");

			firefoxProfile.setEnableNativeEvents(true); 
			desiredCapabilities  = DesiredCapabilities.firefox();
			desiredCapabilities.setCapability("firefox_profile", firefoxProfile);
			desiredCapabilities.setBrowserName(browser);
			desiredCapabilities.setVersion(version);
			desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
			driver =new FirefoxDriver(desiredCapabilities); 
		}
		else if(browser.contains("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			desiredCapabilities = DesiredCapabilities.chrome();
			desiredCapabilities.setBrowserName(browser);
			desiredCapabilities.setVersion(version);
			desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
			driver =new ChromeDriver(desiredCapabilities); 
		}
		else if(browser.contains("iexplore"))
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
			desiredCapabilities = DesiredCapabilities.internetExplorer();
			//desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true); 
			desiredCapabilities.setBrowserName(browser);
			desiredCapabilities.setVersion(version);
			desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			desiredCapabilities.setCapability(CapabilityType.ENABLE_PERSISTENT_HOVERING, false);
			//desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, "INFO");
			desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
			
			desiredCapabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, false);
			//desiredCapabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			desiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, "dismiss");
			desiredCapabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
			desiredCapabilities.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
			desiredCapabilities.setCapability("ignoreZoomSetting", true);
			
			desiredCapabilities.setPlatform(Platform.WINDOWS);
			driver =new InternetExplorerDriver(desiredCapabilities); 
		}
		else if(browser.contains("safari"))
		{
			desiredCapabilities = DesiredCapabilities.safari();
			desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		    desiredCapabilities.setBrowserName(browser);
			desiredCapabilities.setVersion(version);
			driver =new SafariDriver(desiredCapabilities);   
		}
		
		//driver =new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),desiredCapabilities); 
		driver.manage().deleteAllCookies();
		//driver = new Augmenter().augment(driver); //augmented driver for taking screen-shots 
	}

	public void initialize(String moduleName,String methodName)
	{
		mLink=AdvanceXMLUtil.getAllChildrenForClick(moduleName,methodName);
		timeoutString = AdvanceXMLUtil.getValueForTimeOutById(moduleName, methodName, "timeout");
		testCaseUtil=new TestCaseUtil();

		//set driver timeout
		//driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(timeoutString), TimeUnit.SECONDS);
	    //driver.manage().timeouts().setScriptTimeout(Integer.parseInt(timeoutString), TimeUnit.SECONDS);
		testCaseUtil.setTimeout(Integer.parseInt(timeoutString));
		
		baseWidowHandle=driver.getWindowHandle();
	}
	
	@Test (description="Launch Site ")
	public void launchHomePage() throws InterruptedException {
		moduleName="homePageModule";
		methodName="launchHomePage";
		initialize(moduleName,methodName);
		driver.get(urlProp.getProperty("homePage"));
		driver.manage().window().maximize();
		//Thread.sleep(5000);
		
		if(browser.contains("iexplore")){ // certificate issue in IE 7 and 8
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");
		  }
		testCaseUtil.checkAllTextMsgs(moduleName, methodName, noClick, driver);
	}
	
	@Test (description ="Navigate to Forgot Password page")
	public void forgotPasswordPage() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="forgotPasswordPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Forgot Password blank email")
	public void forgotPasswordBlankmail() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="forgotPasswordBlankmail";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Forgot Password wrong email")
	public void forgotPasswordWrongEmail() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="forgotPasswordWrongEmail";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Forgot Password correct email")
	public void forgotPasswordCorrectEmail() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="forgotPasswordCorrectEmail";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Provide details and Save new Billing Address")
	@Parameters(value="xmlMethodName")
	public void newBillingDetails(@Optional("newBillingDetails") String xmlMethodName) throws InterruptedException{
		moduleName="userAccountModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="New Delivery Address Details")
	@Parameters(value="xmlMethodName")
	public void newDeliveryDetails(@Optional("newDeliveryDetails") String xmlMethodName) throws InterruptedException{
		moduleName="userAccountModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="New Delivery Address Details")
	@Parameters(value="xmlMethodName")
	public void addDeliveryAddressComplete(@Optional("addDeliveryAddressComplete") String xmlMethodName) throws InterruptedException{
		moduleName="userAccountModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="New Delivery Address Details")
	@Parameters(value="xmlMethodName")
	public void addDeliveryAddressComplete2(@Optional("addDeliveryAddressComplete2") String xmlMethodName) throws InterruptedException{
		moduleName="userAccountModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Modify Delivery Address Details")
	@Parameters(value="xmlMethodName")
	public void modifyDeliveryAddress(@Optional("modifyDeliveryAddress") String xmlMethodName) throws InterruptedException{
		moduleName="userAccountModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Edit Billing Address Details")
	public void editBillingAddress() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="editBillingAddress";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Edit Delivery Address Details")
	public void clickEditDeliveryAddress() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickEditDeliveryAddress";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Set Primary Delivery Address")
	public void setPrimaryDeliveryAddress() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="setPrimaryDeliveryAddress";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Remove One Delivery Address")
	public void removeDeliveryAddress() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="removeDeliveryAddress";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Modify Billing Address Details")
	public void modifyBillingDetails() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="modifyBillingDetails";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Navigate to Sign in Page")
	public void signInPage() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="signInPage";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Navigate to Sign in Page")
	public void Page2() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="signInPage";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Navigate to Registration Page")
	public void registrationPage() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="registrationPage";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}

	@Test (description ="Complete Registration")
	@Parameters(value="xmlMethodName")
	public void completeRegistration(@Optional("completeRegistration") String xmlMethodName) throws InterruptedException{
		moduleName="userAccountModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Cancel on Registration Page")
	public void cancelOnRegistrationPage() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="cancelOnRegistrationPage";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Update Profile")
	@Parameters(value="xmlMethodName")
	public void updateProfile(@Optional("updateProfile") String xmlMethodName) throws InterruptedException{
		moduleName="userAccountModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Change Password")
	@Parameters(value="xmlMethodName")
	public void changePassword(@Optional("changePassword") String xmlMethodName) throws InterruptedException{
		moduleName="userAccountModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Incorrect Current Password 3 Times" ,invocationCount=3)
	public void incorrectCurrentPassword3Times() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="incorrectCurrentPassword3Times";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Incorrect Password 3 Times Error")
	public void verifyIncorrectPassword3TimesError() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="verifyIncorrectPassword3TimesError";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Sign in with credentials")
	@Parameters(value="xmlMethodName")
	public void signIn(@Optional("signIn") String xmlMethodName) throws InterruptedException{
		moduleName="userAccountModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Sign in with credentials")
	public void signIn2() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="signIn";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Navigate to My Shop Account page")
	public void navigateToMyAccountPage() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="navigateToMyAccountPage";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on My Account link")
	public void clickMyAccount() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickMyAccount";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Add a new address below Billing Address")
	public void clickAddNewBillAddress() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickAddNewBillAddress";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Add a new address below Delivery Address")
	public void clickAddNewDeliveryAddress() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickAddNewDeliveryAddress";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Add a new address below Delivery Address")
	public void clickAddNewDeliveryAddress2() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickAddNewDeliveryAddress";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Add a new address below Delivery Address")
	public void clickAddNewDeliveryAddress3() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickAddNewDeliveryAddress";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Address Book link")
	public void clickAddressBook() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickAddressBook";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Order History link")
	public void clickOrderHistory() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickOrderHistory";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Save Carts link") 
	public void clickSaveCarts() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickSaveCarts";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
		
	@Test (description ="Verify zero saved carts")
	public void verifyZeroSavedCarts() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="verifyZeroSavedCarts";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName, methodName, "noClick", driver);
	}
	
	@Test (description ="Verify Ten saved carts")
	public void verifyTenSavedCarts() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="verifyTenSavedCarts";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName, methodName, "noClick", driver);
	}
	
	@Test (description ="View Saved Cart detail")
	public void viewSavedCart() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="viewSavedCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}

	@Test (description ="Click Back To Saved Carts")
	public void clickBackToSavedCarts() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickBackToSavedCarts";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Remove Item From Saved Cart")
	public void removeItemFromSavedCart() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="removeItemFromSavedCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Start adding Saved Cart to basket")
	public void checkoutFromSavedCart() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="checkoutFromSavedCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Confirm Saved Cart addition to basket")
	public void confirmCheckoutFromSavedCart() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="confirmCheckoutFromSavedCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Cancel Checkout From Saved Cart")
	public void cancelCheckoutFromSavedCart() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="cancelCheckoutFromSavedCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Delete Saved Cart")
	public void deleteSavedCart() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="deleteSavedCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Zero Orders")
	public void verifyZeroOrders() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="verifyZeroOrders";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on View Order link")
	public void clickViewOrder() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickViewOrder";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Next Order link")
	public void clickNextOrder() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickNextOrder";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Previous Order link")
	public void clickPreviousOrder() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickPreviousOrder";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Back To All Orders link")
	public void clickBackToAllOrders() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickBackToAllOrders";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Track Orders link")
	public void clickTrackOrders() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickTrackOrders";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Print Order link")
	public void clickPrintOrder() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickPrintOrder";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Place Order Again link")
	public void clickPlaceOrderAgain() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickPlaceOrderAgain";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Track Order Link")
	public void VerifyTrackOrder() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="VerifyTrackOrder";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Blank Addresses")
	public void verifyBlankAddresses() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="verifyBlankAddresses";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	@Test (description ="Click on New Billing Address link")
	public void clickNewBillingAddress() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickNewBillingAddress";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Fill Billing Address information")
	@Parameters(value="xmlMethodName")
	public void billingAddressInfo(@Optional("billingAddressInfo") String xmlMethodName) throws InterruptedException{
		moduleName="userAccountModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Edit details or change password button")
	public void clickEditDetailsButton() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="clickEditDetailsButton";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Sign-out")
	public void signOut() throws InterruptedException{
		moduleName="userAccountModule";
		methodName="signOut";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Navigate to Product Detail Page")
	@Parameters(value="productSKU")
	public void productDetailPage(@Optional("SKU=1711286")String productSKU) throws InterruptedException{
		moduleName="productModule";
		methodName="productDetailPage";
		driver.get(urlProp.getProperty("productPageURL").concat(productSKU));
		driver.manage().window().maximize();
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Click on Facebook link")
	public void clickOnFaceBookLink() throws InterruptedException{
		moduleName="productModule";
		methodName="clickOnFaceBookLink";
		initialize(moduleName,methodName);
		checkInNewWindow=true;
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
		checkInNewWindow=false;
	}
	
	@Test (description ="Click on Share link")
	public void clickOnShareLink() throws InterruptedException{
		moduleName="productModule";
		methodName="clickOnShareLink";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Email link")
	public void clickOnEmailLink() throws InterruptedException{
		moduleName="productModule";
		methodName="clickOnEmailLink";
		initialize(moduleName,methodName);
		checkInNewWindow=true;
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
		checkInNewWindow=false;
	}
	
	
	@Test (description ="Navigate to Same Product Detail Page")
	@Parameters(value="productSKU")
	public void productDetailPageSame(String productSKU) throws InterruptedException{
		moduleName="productModule";
		methodName="productDetailPage";
		driver.get(urlProp.getProperty("productPageURL").concat(productSKU));
		driver.manage().window().maximize();
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Navigate to Product2 Detail Page")
	@Parameters(value="productSKU")
	public void productDetailPage2(String productSKU) throws InterruptedException{
		moduleName="productModule";
		methodName="productDetailPage";
		driver.get(urlProp.getProperty("productPageURL").concat(productSKU));
		driver.manage().window().maximize();
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Navigate to Product3 Detail Page")
	@Parameters(value="productSKU")
	public void productDetailPage3(String productSKU) throws InterruptedException{
		moduleName="productModule";
		methodName="productDetailPage";
		driver.get(urlProp.getProperty("productPageURL").concat(productSKU));
		driver.manage().window().maximize();
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Navigate to Product4 Detail Page")
	@Parameters(value="productSKU")
	public void productDetailPage4(String productSKU) throws InterruptedException{
		moduleName="productModule";
		methodName="productDetailPage";
		driver.get(urlProp.getProperty("productPageURL").concat(productSKU));
		driver.manage().window().maximize();
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Sign in at Checkout")
	@Parameters(value="xmlMethodName")
	public void checkoutSignIn(@Optional("checkoutSignIn") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Add Product to Cart")
	public void addToCart() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="addToCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Add Product2 to Cart")
	public void addToCart2() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="addToCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Add Product3 to Cart")
	public void addToCart3() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="addToCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Add Product4 to Cart")
	public void addToCart4() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="addToCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="View Empty Cart")
	public void viewEmptyCart() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="viewEmptyCart";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Mouseover Mini Cart")
	public void mouseOverMiniCart() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="mouseOverMiniCart";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
	}
	
	@Test (description ="Mouseover Empty Mini Cart")
	public void mouseOverEmptyMiniCart() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="mouseOverEmptyMiniCart";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
	}
	
	@Test (description ="View Cart")
	public void viewCart() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="viewCart";
		initialize(moduleName,methodName);
		//mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="View Cart")
	public void viewCart2() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="viewCart";
		initialize(moduleName,methodName);
		//mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="View Cart")
	public void viewCart3() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="viewCart";
		initialize(moduleName,methodName);
		//mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}

	@Test (description ="Back to Shopping")
	public void backToShopping() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="backToShopping";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Back to Shopping")
	public void backToShopping2() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="backToShopping";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Place this order again")
	public void placeOrderAgain() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="placeOrderAgain";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Remove PLI")
	@Parameters(value="xmlMethodName")
	public void removePLI(@Optional("removePLI") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Remove PLI from Mini Cart")
	public void removePLIFromMiniCart() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="removePLIFromMiniCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Select Shipping method")
	@Parameters(value="xmlMethodName")
	public void selectShippingLocation(@Optional("selectShippingLocation") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Select Shipping method to update the quantity")
	@Parameters(value="xmlMethodName")
	public void selectShippingLocation2(@Optional("selectShippingLocation") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Anonymous Checkout Details")
	@Parameters(value="xmlMethodName")
	public void anonymousCheckoutDetails(@Optional("anonymousCheckoutDetails") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Anonymous Checkout Shipping Details")
	@Parameters(value="xmlMethodName")
	public void anonymousCheckoutShippingDetails(@Optional("anonymousCheckoutShippingDetails") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Anonymous Checkout Billing Details")
	@Parameters(value="xmlMethodName")
	public void anonymousCheckoutBillingDetails(@Optional("anonymousCheckoutBillingDetails") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Anonymous Checkout Shipping Error")
	public void verifyAnonymousShippingError() throws InterruptedException{
		moduleName="checkoutModule";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	@Test (description ="Continue Checkout")
	public void continueCheckout() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="continueCheckout";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Proceed to Checkout")
	public void proceedToCheckout() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="proceedToCheckout";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Navigate to Delivery Details")
	public void navigateToDeliveryDetails() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="navigateToDeliveryDetails";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Shipping Location")
	@Parameters(value="xmlMethodName")
	public void verifyShippingLocation(@Optional("verifyShippingLocation") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Select Australia Shipping Address")
	public void selectAUShippingAddress() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="selectAUShippingAddress";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}

	@Test (description ="Verify Australia Shipping Address")
	public void verifyAUShippingAddressError() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyAUShippingAddressError";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Select International Shipping Address")
	public void selectInternationalShippingAddress() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="selectInternationalShippingAddress";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Verify International Shipping Address Error")
	public void verifyInternationalShippingAddressError() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyInternationalShippingAddressError";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Select Different Billing Address")
	public void selectDifferentBillingAddress() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="selectDifferentBillingAddress";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Select Different Shipping Address")
	public void selectDifferentShippingAddress() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="selectDifferentShippingAddress";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Navigate to Shopping Cart")
	public void navigateToShoppingCart() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="navigateToShoppingCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Add New Shipping Address")
	public void addNewShippingAddress() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="addNewShippingAddress";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="New Shipping Address Info")
	@Parameters(value="xmlMethodName")
	public void newShippingDetails(@Optional("newShippingDetails") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Navigate to Your Details")
	public void navigateToYourDetails() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="navigateToYourDetails";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Delivery Details link")
	public void clickDeliveryDetails() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="clickDeliveryDetails";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Navigate to Secure Payment")
	public void navigateToPaymentPage() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="navigateToPaymentPage";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Previous from Payment Page")
	public void previousOnPaymentPage() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="previousOnPaymentPage";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Enter Card Details")
	@Parameters(value="xmlMethodName")
	public void testPaymentData(@Optional("testPaymentData") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Error Without Terms")
	public void verifyErrorWithoutTerms() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyErrorWithoutTerms";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Verify Promotion Free Gift")
	public void verifyPromotionFreeGift() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyPromotionFreeGift";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Verify Minimum quantity of a product")
	public void verifyMinQuantity() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyMinQuantity";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Verify Maximum quantity of a product")
	public void verifyMaxQuantity() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyMaxQuantity";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Verify Error  Minimum quantity requirement")
	public void verifyErrorMinQuantityReq() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyErrorMinQuantityReq";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Verify Error  Maximum quantity requirement")
	public void verifyErrorMaxQuantityReq() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyErrorMaxQuantityReq";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Verify Error  Minimum Cart Value")
	public void verifyErrorMinCartValue() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyErrorMinCartValue";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Verify Error  Maximum Cart Value")
	public void verifyErrorMaxCartValue() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyErrorMaxCartValue";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Delay")
	@Parameters(value="milliseconds")
	public void delay(long milliseconds) throws InterruptedException{
		moduleName="checkoutModule";
		methodName="delay";
		initialize(moduleName,methodName);
		testCaseUtil.delay(milliseconds);
	}
	
	@Test (description ="Verify cart has retained the selected products. ")
	public void verifyCartRetainedProducts() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyCartRetainedProducts";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Verify cart is empty. ")
	public void verifyCartIsEmpty() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyCartIsEmpty";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Verify Round Up Donation updated. ")
	public void verifyDonationUpdated() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyDonationUpdated";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,noClick,moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Shipping Option.")
	@Parameters(value="xmlMethodName")
	public void verifyShippingOption(@Optional("verifyDomesticShipping") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Verify Shipping Selection.")
	@Parameters(value="xmlMethodName")
	public void verifyShippingSelection(@Optional("verifyInternShipSelection") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Remove Promotional Discount. ")
	public void removePromoDiscount() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="removePromoDiscount";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Check Round Up Total. ")
	public void checkRoundupTotal() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="checkRoundupTotal";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Remove Round Up Donation. ")
	public void removeRoundupDonation() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="removeRoundupDonation";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Round Up Donation. ")
	public void verifyRoundUpDonation() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyRoundUpDonation";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,noClick,moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Promotion Code. ")
	@Parameters(value="xmlMethodName")
	public void verifyPromotionCode(@Optional("verifyValidPromotionCode") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,noClick,moduleName,methodName,driver);
	}
	
	@Test (description ="Reset configurations after back to shopping and view cart")
	public void resetConfigViewCart() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="resetConfigViewCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,noClick,moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Error Blank Name")
	public void verifyErrorBlankName() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyErrorBlankName";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,noClick,moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Error Invalid CC Number")
	public void verifyErrorInvalidCCNumber() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyErrorInvalidCCNumber";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,noClick,moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Error Blank CC Number")
	public void verifyErrorBlankCCNumber() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyErrorBlankCCNumber";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,noClick,moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Error Blank Expiry Date")
	public void verifyErrorBlankExpiryDate() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyErrorBlankExpiryDate";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,noClick,moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Error Past Expiry Date")
	public void verifyErrorPastExpiryDate() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyErrorPastExpiryDate";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,noClick,moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Blank CSC")
	public void verifyErrorBlankCSC() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyErrorBlankCSC";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		processElements(testCaseUtil,mLink,noClick,moduleName,methodName,driver);
	}
	
	@Test (description ="Click Terms And Condition")
	public void clickTermsAndCondition() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="clickTermsAndCondition";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="Click On CSS Link")
	public void clickOnCSCLink() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="clickOnCSCLink";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Complete Payment")
	public void completePayment() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="completePayment";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Create Post Checkout Account ")
	@Parameters(value="xmlMethodName")
	public void createPostCheckoutAccount(@Optional("createPostCheckoutAccount") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}

	@Test (description ="Complete Post Checkout Account ")
	public void completePostCheckoutAccount() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="completePostCheckoutAccount";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Close Popup ")
	public void closePopup() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="closePopup";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Order Confirmation")
	public void verifyOrderConfirmation() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="verifyOrderConfirmation";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,noClick,moduleName,methodName,driver);
	}
	
	@Test (description ="Print Order Confirmation")
	public void printOrderConfirmation() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="printOrderConfirmation";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,noClick,moduleName,methodName,driver);
	}
	
	@Test (description ="Continue Shopping")
	public void continueShopping() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="continueShopping";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Anonymous Checkout Step 2 Validations")
	public void anonymousCheckoutStep2Validation() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="anonymousCheckoutStep2Validation";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName,methodName, driver);
		testCaseUtil.checkAllTextMsgs(moduleName, methodName, noClick, driver);
	}
	
	@Test (description ="Signed In Checkout Step 2 Validations")
	public void signedInCheckoutStep2Validation() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="signedInCheckoutStep2Validation";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		testCaseUtil.checkAllTextMsgs(moduleName, methodName, noClick, driver);
	}
	
	@Test (description ="Save Cart")
	@Parameters(value="xmlMethodName")
	public void saveCart(@Optional("saveCart") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Navigate to My Shop Account")
	public void myShopAccount() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="myShopAccount";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
	}
	
	@Test (description ="Temporary Method for double clicking the button; will be removed after fix")
	public void temporaryMethod() throws InterruptedException{
		moduleName="checkoutModule";
		methodName="temporaryMethod";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
		
	@Test (description ="Update Product Quantity in basket")
	@Parameters(value="xmlMethodName")
	public void updateProductQuantity(@Optional("updateProductQuantity") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Update PLI Quantity")
	@Parameters(value="xmlMethodName")
	public void updatePLIQuantity(@Optional("updatePLIQuantity") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Update Promotion Code")
	@Parameters(value="xmlMethodName")
	public void updatePromotionCode(@Optional("updateValidPromotionCode") String xmlMethodName) throws InterruptedException{
		moduleName="checkoutModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click Search Button")
	public void clickSearchButton() throws InterruptedException{
		moduleName="searchModule";
		methodName="clickSearchButton";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Type Search Keyword")
	@Parameters(value="searchKeyword")
	public void typeSearchKeyword(@Optional("Travel") String searchKeyword) throws InterruptedException{
		driver.findElement(By.xpath("//input[@name='SearchTerm']")).clear();
		driver.findElement(By.xpath("//input[@name='SearchTerm']")).sendKeys(searchKeyword);
		
	}
	
	@Test (description ="Select Auto Suggest Value")
	public void selectAutoSuggestValue() throws InterruptedException{
		moduleName="searchModule";
		methodName="selectAutoSuggestValue";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Verify Did You Mean Text")
	public void verifyDidYouMeanText() throws InterruptedException{
		moduleName="searchModule";
		methodName="verifyDidYouMeanText";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName, methodName, noClick, driver);
	}
	
	@Test (description ="Verify Closest Match")
	public void verifyClosestMatch() throws InterruptedException{
		moduleName="searchModule";
		methodName="verifyClosestMatch";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName, methodName, noClick, driver);
	}
	
	@Test (description ="Verify Zero Results")
	public void verifyZeroResults() throws InterruptedException{
		moduleName="searchModule";
		methodName="verifyZeroResults";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllTextMsgs(moduleName, methodName, noClick, driver);
	}
	
	@Test (description ="Sort Search Results")
	@Parameters(value="xmlMethodName")
	public void sortSearchResults(@Optional("sortSearchResults") String xmlMethodName) throws InterruptedException{
		moduleName="searchModule";
		methodName=xmlMethodName;
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click Home From BreadCrumb")
	public void clickHomeFromBreadCrumb() throws InterruptedException{
		moduleName="searchModule";
		methodName="clickHomeFromBreadCrumb";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description ="Click on Product Image")
	public void clickProductImage() throws InterruptedException{
		moduleName="searchModule";
		methodName="clickProductImage";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}	
	
	@Test (description ="Click on Product Title")
	public void clickProductTitle() throws InterruptedException{
		moduleName="searchModule";
		methodName="clickProductTitle";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
		
	public void processElements(TestCaseUtil testCaseUtil,LinkedHashMap<String,String> mLink,String action,String moduleName,String methodName,WebDriver driver)  throws InterruptedException 
	{
		WebElement webElement=null;
		for(String key : mLink.keySet())
		{
			 webElement=null;
			 boolean CSSSelector = true;
			 try{
				 if(driver.findElements(By.cssSelector(mLink.get(key))).size() != 0)
					{
							//webElement=(new WebDriverWait(driver, Integer.parseInt(timeoutString))).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector((mLink.get(key)))));
					}
			 }catch(Throwable t){
				System.out.println("Error occurred during finding element by CSS selector: "+ t.getMessage()); 
				CSSSelector = false;
			 }
			
			if(CSSSelector)
			{
					webElement=(new WebDriverWait(driver, Integer.parseInt(timeoutString))).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector((mLink.get(key)))));
			}
			else if(driver.findElements(By.xpath(mLink.get(key))).size() != 0)
			{
					webElement=(new WebDriverWait(driver, Integer.parseInt(timeoutString))).until(ExpectedConditions.presenceOfElementLocated(By.xpath(mLink.get(key))));
			}
			else if(driver.findElements(By.id(mLink.get(key))).size() != 0)
			{
					webElement=(new WebDriverWait(driver, Integer.parseInt(timeoutString))).until(ExpectedConditions.presenceOfElementLocated(By.id(mLink.get(key))));
			}
			else if(driver.findElements(By.linkText(mLink.get(key))).size() != 0)
			{
					webElement=(new WebDriverWait(driver, Integer.parseInt(timeoutString))).until(ExpectedConditions.presenceOfElementLocated(By.linkText(mLink.get(key))));
			}
			
			if(webElement!=null && action!=null)
			{
				if(action.equalsIgnoreCase("mouseover"))
				{
					Actions mouseOver = new Actions(driver);
					mouseOver.moveToElement(webElement).build().perform(); 
				}
				else
				{
					webElement.click(); //default action	
				}
			}
			Thread.sleep(5000);
			if(browser.contains("iexplore")){ // certificate issue in IE 7 and 8
				if(driver.getPageSource().contains("Continue to this website")){
				driver.get("javascript:document.getElementById('overridelink').click();"); 
				}
				
			  }
			else if(browser.contains("safari")){ // certificate issue in Safari
				Alert alert =null;
				try{
					alert= driver.switchTo().alert();
				}catch(NoAlertPresentException noAlertPresentException){}
				if(alert!=null){alert.accept();}
			}
			if(checkInNewWindow)
			{
				Iterator<String> ite=driver.getWindowHandles().iterator();
				while(ite.hasNext())
				{
				    String newWindowHandle=ite.next().toString();
				    if(!newWindowHandle.contains(baseWidowHandle))
				    {
				                driver.switchTo().window(newWindowHandle); 
				    }
				}
			}
			testCaseUtil.checkAllTextMsgs(moduleName, methodName, key, driver);
			//Thread.sleep(10000);
		}
	}	
	
	@AfterClass(alwaysRun = true)
	public void setupAfterSuite() {
		//driver.close();
		System.out.println("Cookies are about to delete");
		driver.manage().deleteAllCookies();
		   driver.quit();
	}
}

