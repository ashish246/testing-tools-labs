/**
 * 
 */
package com.intershop.main.jumpstart;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.intershop.testframework.utils.AdvanceXMLUtil;

/**
 * @author singhbri
 *
 */
public class JumpStartSiteNavigationTestSuite extends JumpStartTestSuite {

	/**
	 * 
	 */
	public JumpStartSiteNavigationTestSuite() {
	}
	
	@Test (description="Launch main home page of Australia Post Corporate Site")
	public void clickBrandLogo() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="clickBrandLogo";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="Launch main home page of Australia Post Corporate Site")
	public void aboutUsLink() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="aboutUsLink";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="Launch main home page of Australia Post Corporate Site")
	public void locationAndHourLink() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="locationAndHourLink";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="Launch main home page of Australia Post Corporate Site")
	public void helpAndSupportLink() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="helpAndSupportLink";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="Launch main home page of Australia Post Corporate Site")
	public void businessSolutionsLink() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="businessSolutionsLink";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="Launch main home page of Australia Post Corporate Site")
	public void parcelsAndMailLink() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="parcelsAndMailLink";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="Launch main home page of Australia Post Corporate Site")
	public void financeAndInsuranceLink() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="financeAndInsuranceLink";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="Launch main home page of Australia Post Corporate Site")
	public void passportAndIDLink() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="passportAndIDLink";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description="Launch sign in pop up window")
	public void signInLink() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="signInLink";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="close sign in pop up window")
	public void closePopUPWindow() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="closePopUPWindow";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="close sign in pop up window")
	public void registerInLink() throws InterruptedException {
		moduleName="userAccountModule";
		methodName="registrationPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="sign in without any information")
	public void signInWithoutAnyInformation() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="signInWithoutAnyInformation";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="sign in without password")
	public void signInWithoutPassword() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="signInWithoutPassword";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="sign in without email")
	public void signInWithoutEmail() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="signInWithoutEmail";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="sign in with incorrect password")
	public void signInWithIncorrectPassword() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="signInWithIncorrectPassword";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="sign in with correct credentials")
	public void signInWithCorrectCredentials() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="signInWithCorrectCredentials";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="sign out")
	public void signOutLink() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="signOutLink";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description="Sign out without saving cart")
	public void clickNoSaveCart() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="clickNoSaveCart";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description="show empty cart")
	public void showEmptyCart() throws InterruptedException {
		moduleName="checkoutModule";
		methodName="viewEmptyCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="mouse over empty mini cart icon")
	public void mouseOverEmptyMiniCartIcon() throws InterruptedException {
		moduleName="checkoutModule";
		methodName="viewEmptyCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="items in cart")
	public void itemsInCart() throws InterruptedException {
		moduleName="checkoutModule";
		methodName="viewCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="mouse over mini cart with items")
	public void mouseOverMiniCartIconWithItems() throws InterruptedException {
		moduleName="checkoutModule";
		methodName="viewCart";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="Anonymous user on site")
	public void anonymousUser() throws InterruptedException {
		moduleName="homePageModule";
		methodName="launchHomePage";
		initialize(moduleName,methodName);
		driver.get(urlProp.getProperty("homePage"));
		driver.manage().window().maximize();
		Thread.sleep(10000);
		testCaseUtil.checkAllTextMsgs(moduleName, methodName, noClick, driver);
	}
	@Test (description="header for signed user")
	public void headerForSignedUser() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="headerForSignedUser";
		initialize(moduleName,methodName);
		driver.get(urlProp.getProperty("homePage"));
	}
	@Test (description="my shop account page")
	public void myShopAccountPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="myShopAccountPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view global shop page")
	public void globalShopPage() throws InterruptedException {
		moduleName="homePageModule";
		methodName="launchHomePage";
		initialize(moduleName,methodName);
		driver.get(urlProp.getProperty("homePage"));
		driver.manage().window().maximize();
	}
	@Test (description="view shop home page")
	public void shopHomePage() throws InterruptedException {
		moduleName="homePageModule";
		methodName="launchHomePage";
		initialize(moduleName,methodName);
		driver.get(urlProp.getProperty("homePage"));
	}
	@Test (description="display pack and post sub catogory")
	public void mouseOverPackAndPost() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="mouseOverPackAndPost";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
	}
	@Test (description="Open Pack & Post category landing page")
	public void viewPostAndParcelLandingPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewPostAndParcelLandingPage";
		initialize(moduleName,methodName);
		driver.get(urlProp.getProperty("packAndPostCategoryURL"));
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}
	@Test (description="display pack and post sub catogories page.")
	public void viewPostAndParcelSubCategoryPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewPostAndParcelSubCategoryPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="display pack and post 3rd level category landing page.")
	public void viewPostAndParcel3rdLevelPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewPostAndParcel3rdLevelPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="display gift and travel sub catogories")
	public void mouseOverGiftAndTravel() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="mouseOverGiftAndTravel";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		if(browser.contains("iexplore"))
		{
			processElements(testCaseUtil,mElements,"click",moduleName,methodName,driver);
		}
		else
		{
			processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		}
	}
	@Test (description="display gift and travel landing page")
	public void viewGiftAndTravelLandingPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewGiftAndTravelLandingPage";
		initialize(moduleName,methodName);
		driver.get(urlProp.getProperty("giftAndTravelCategoryURL"));
		driver.manage().window().maximize();
	}
	@Test (description="display gift and travel landing page")
	public void viewGiftAndTravelSubCategoryPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewGiftAndTravelSubCategoryPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="display home and office sub catogories")
	public void mouseOverHomeAndOffice() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="mouseOverHomeAndOffice";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		if(browser.contains("iexplore"))
		{
			processElements(testCaseUtil,mElements,"click",moduleName,methodName,driver);
		}
		else
		{
			processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		}
	}
	@Test (description="display home and office landing page")
	public void viewHomeAndOfficeLandingPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewHomeAndOfficeLandingPage";
		initialize(moduleName,methodName);
		driver.get(urlProp.getProperty("homeAndOfficeCategoryURL"));
		driver.manage().window().maximize();
	}
	@Test (description="view telecommunication categories page")
	public void viewHomeAndOfficeSubCategoryPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewHomeAndOfficeSubCategoryPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="display home and office sub categories page")
	public void viewTeleCommunicationsPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewTeleCommunicationsPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
		Thread.sleep(5000);
	}
	@Test (description="display home and office sub categories page")
	public void viewAccessoriesPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewAccessoriesPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="display home and office sub categories page")
	public void viewMobilePrePaidPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewMobilePrePaidPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="display home and office sub categories page")
	public void viewMobilePrePaidCategories() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewMobilePrePaidCategories";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="display home and office sub categories page")
	public void viewTelstraCategoryPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewTelstraCategoryPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="display home and office 3rd level sub categories page")
	public void viewHomeAndOffice3rdLevelPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewHomeAndOffice3rdLevelPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="display stamp and coin collectables sub catogories")
	public void mouseOverStampAndCoinCollectables() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="mouseOverStampAndCoinCollectables";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		if(browser.contains("iexplore"))
		{
			processElements(testCaseUtil,mElements,"click",moduleName,methodName,driver);
		}
		else
		{
			processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		}
	}
	@Test (description="display stamp and coin collectables landing page")
	public void viewStampAndCoinCollectablesLandingPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewStampAndCoinCollectablesLandingPage";
		initialize(moduleName,methodName);
		driver.get(urlProp.getProperty("stampAndCoinCollectablesCategoryURL"));
		driver.manage().window().maximize();
	}
	@Test (description="display stamp and coin collectables sub categories page")
	public void viewStampAndCoinCollectablesSubCategoryPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewStampAndCoinCollectablesSubCategoryPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="display stamp and coin collectables 3rd level categories page")
	public void viewStampAndCoinCollectables3rdLevelPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewStampAndCoinCollectables3rdLevelPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="success page. Thanks for subscribing!")
	public void viewPromotionAndofferWithSuccess() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewPromotionAndofferWithSuccess";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="subscribe without email")
	public void viewPromotionAndofferWithoutEmail() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewPromotionAndofferWithoutEmail";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="subscribe without name")
	public void viewPromotionAndofferWithoutName() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewPromotionAndofferWithoutName";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="subscribe with invalid email")
	public void viewPromotionAndofferWithInvalidEmail() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewPromotionAndofferWithInvalidEmail";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description="view pricing and postage information guides")
	public void viewPricingAndPostageGuide() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewPricingAndPostageGuide";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view jobs page")
	public void viewJobsPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewJobsPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view media centre page")
	public void viewMediaCentrePage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewMediaCentrePage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view travel page")
	public void viewTravelPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewTravelPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view stamp and collectables page")
	public void viewStampsAndCollectablesPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewStampsAndCollectablesPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view gift and retail catalogues page")
	public void viewGiftAndRetailCataloguePage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewGiftAndRetailCataloguePage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view click and send page")
	public void viewClickAndSendPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewClickAndSendPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view site map page")
	public void viewSiteMapPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewSiteMapPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view about us site page")
	public void viewAboutUsSitePage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewAboutUsSitePage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view term and conditions page")
	public void viewTeamAndConditionsPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewTeamAndConditionsPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view privacy policy page")
	public void viewPrivacyPolicyPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewPrivacyPolicyPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view facebook page")
	public void viewFacebookPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewFacebookPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view pinterest page")
	public void viewPInterestPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewPInterestPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view twitter page")
	public void viewTwitterPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewTwitterPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view youtube page")
	public void viewYouTubePage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewYouTubePage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view google plus page")
	public void viewGooglePlusPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewGooglePlusPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view feature banners product lister page")
	public void viewFeatureBannerProductList() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewFeatureBannerProductList";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="track an item page")
	public void viewTrackAnItemPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewTrackAnItemPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view icam widget pages")
	public void viewCalculatePostagePage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewCalculatePostagePage";
		initialize(moduleName,methodName);
		driver.get(urlProp.getProperty("homePage"));
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view icam widget pages")
	public void viewFindaPostcodePage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewFindaPostcodePage";
		initialize(moduleName,methodName);
		driver.get(urlProp.getProperty("homePage"));
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view icam widget pages")
	public void viewConvertCurrencyPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewConvertCurrencyPage";
		initialize(moduleName,methodName);
		driver.get(urlProp.getProperty("homePage"));
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view icam widget pages")
	public void viewIcamWidgetPages() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewIcamWidgetPages";
		initialize(moduleName,methodName);
	}
	@Test (description="view most popular section")
	public void viewMostPopularSection() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewMostPopularSection";
		initialize(moduleName,methodName);
		driver.get(urlProp.getProperty("homePage"));
	}
	@Test (description="view icam widget pages")
	public void viewContentSlot1() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewContentSlot1";
		initialize(moduleName,methodName);
	}
	@Test (description="view icam widget pages")
	public void viewContentSlot2() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewContentSlot2";
		initialize(moduleName,methodName);
	}
	@Test (description="back to home page")
	public void backToHomePage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="backToHomePage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="sorting product by price low to high")
	public void sortProductByPriceLowToHigh() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="sortProductByPriceLowToHigh";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="sorting product by price high to low")
	public void sortProductByPriceHighToLow() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="sortProductByPriceHighToLow";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="sorting product by popularity")
	public void sortProductByPopularity() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="sortProductByPopularity";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view all products of the category")
	public void viewAllProducts() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewAllProducts";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description="view products from refinement panel")
	public void viewProductFromRefinementPanel() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewProductFromRefinementPanel";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description="view search products from refinement panel")
	public void viewSearchProductFromRefinementPanel() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewSearchProductFromRefinementPanel";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description="back to express post refinement panel")
	public void backToExpressPostPanel() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="backToExpressPostPanel";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="back to pack and post refinement panel")
	public void backToPackAndPostPanel() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="backToPackAndPostPanel";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="mouse over product image for quick view")
	public void mouseOverProductImage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="mouseOverProductImage";
		initialize(moduleName,methodName);
		driver.get(urlProp.getProperty("packAndPostCategoryURL"));
		driver.manage().window().maximize();
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		Thread.sleep(10000);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
	}
	@Test (description="quick view of the pack and parcel product")
	public void quickViewPackAndParcelProducts() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="quickViewPackAndParcelProducts";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		Thread.sleep(5000);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
		Thread.sleep(10000);
	}
	@Test (description="quick view of the gift and travel product")
	public void quickViewGiftAndTravelProducts() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="quickViewGiftAndTravelProducts";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		Thread.sleep(5000);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
		Thread.sleep(10000);
	}
	@Test (description="quick view of the gift and travel product")
	public void quickViewGiftCardsProducts() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="quickViewGiftCardsProducts";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		Thread.sleep(5000);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
		Thread.sleep(10000);
	}
	@Test (description="quick view of the load and go product")
	public void quickViewLoadAndGoProducts() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="quickViewLoadAndGoProducts";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		Thread.sleep(5000);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
		Thread.sleep(10000);
	}
	@Test (description ="Update Product Quantity with 2")
	public void updateQuickViewProductQuantityWith2() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="updateQuickViewProductQuantityWith2";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="Update Product Quantity with 25")
	public void updateQuickViewProductQuantityWith25() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="updateQuickViewProductQuantityWith25";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view share link")
	public void viewShareLink() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewShareLink";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
	}
	@Test (description="view facebook login page")
	public void viewFacebookLoginPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewFacebookLoginPage";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view twitter login page")
	public void viewTwitterLoginPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewTwitterLoginPage";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view twitter login page")
	public void viewDeliciousLoginPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewDeliciousLoginPage";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view twitter login page")
	public void viewLinkedInLoginPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewLinkedInLoginPage";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view twitter login page")
	public void viewPrinterFriedlyLoginPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewPrinterFriedlyLoginPage";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	
	@Test (description="view twitter login page")
	public void viewDiggLoginPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewDiggLoginPage";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view twitter login page")
	public void viewBitlyLoginPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewBitlyLoginPage";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view feedback page")
	public void viewFeedbackPage() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewFeedbackPage";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view more from share link")
	public void viewMoreToolBox() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewMoreToolBox";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
		Thread.sleep(10000);
	}
	@Test (description="view email toolbox")
	public void viewEmailToolbox() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewEmailToolbox";
		initialize(moduleName,methodName);
		mElements=AdvanceXMLUtil.getAllChildrenForMouseOver(moduleName,methodName);
		processElements(testCaseUtil,mElements,"mouseover",moduleName,methodName,driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view 1 day products from refinement panel")
	public void view1dayProducts() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="view1dayProducts";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view 500g products from refinement panel")
	public void viewPackSize500gProducts() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewPackSize500gProducts";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
		Thread.sleep(10000);
	}
	@Test (description="untick 500g products from refinement panel")
	public void uncheck500gProducts() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="uncheck500gProducts";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view buy now matrix")
	public void viewBuyNowMatrix() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewBuyNowMatrix";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view buy now matrix")
	public void hideBuyNowMatrix() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="hideBuyNowMatrix";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="Navigate to Product3 Detail Page")
	@Parameters(value="productSKU")
	public void productDetailPage3(String productSKU) throws InterruptedException{
		moduleName="productModule";
		methodName="productDetailPage";
		driver.get(urlProp.getProperty("productPageURL").concat(productSKU));
		driver.manage().window().maximize();
		initialize(moduleName,methodName);
	}
	@Test (description="view payment and delivery tab")
	public void viewPaymentAndDelivery() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewPaymentAndDelivery";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description="view returns and refunds tab")
	public void viewReturnsAndRefunds() throws InterruptedException {
		moduleName="siteNavigationModule";
		methodName="viewReturnsAndRefunds";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view we also recommend section")
	@Parameters(value="productSKU")
	public void viewWeAlsoRecommendSection(String productSKU) throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="viewWeAlsoRecommendSection";
		driver.get(urlProp.getProperty("productPageURL").concat(productSKU));
		//initialize(moduleName,methodName);
	}
	@Test (description ="verify content area message")
	@Parameters(value="productSKU")
	public void verifyContentAreaMessage(String productSKU) throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="verifyContentAreaMessage";
		driver.get(urlProp.getProperty("productPageURL").concat(productSKU));
		//initialize(moduleName,methodName);
	}
	@Test (description ="view gift and card sub category page")
	public void viewGiftCardSubCategoryPage() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="viewGiftCardSubCategoryPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view gift and card sub category page")
	public void viewAustralianPostsProducts() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="viewAustralianPostsProducts";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view load and go category page")
	public void viewLoadAndGoSubCategoryPage() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="viewLoadAndGoSubCategoryPage";
		initialize(moduleName,methodName);
		//testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view we also recommend section on category page")
	@Parameters(value="categoryURL")
	public void viewWeAlsoRecommendForCategoryPage(String categoryURL) throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="viewWeAlsoRecommendForCategoryPage";
		initialize(moduleName,methodName);
		driver.get(urlProp.getProperty("categoryURL").concat(categoryURL));
		driver.manage().window().maximize();
		testCaseUtil.checkAllTextMsgs(moduleName,methodName, noClick, driver);
		Thread.sleep(5000);
	}
	@Test (description ="view load and go category page")
	public void loadGiftAndTravelCategoryPage() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="loadGiftAndTravelCategoryPage";
		initialize(moduleName,methodName);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void invalidAccessCode1() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="invalidAccessCode1";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void invalidAccessCode2() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="invalidAccessCode2";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void invalidAccessCode3() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="invalidAccessCode3";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void invalidAccessCode4() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="invalidAccessCode4";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void validAccessCode() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="validAccessCode";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void invalidMemorableWord1() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="invalidMemorableWord1";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void invalidMemorableWord2() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="invalidMemorableWord2";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void invalidMemorableWord3() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="invalidMemorableWord3";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void validMemorableWord() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="validMemorableWord";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void invalidMobileNumber1() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="invalidMobileNumber1";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void invalidMobileNumber2() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="invalidMobileNumber2";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void invalidMobileNumber3() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="invalidMobileNumber3";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void validMobileNumber() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="validMobileNumber";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void invalidDOB1() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="invalidDOB1";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void invalidDOB2() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="invalidDOB2";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void invalidDOB3() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="invalidDOB3";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void invalidDOB4() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="invalidDOB4";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void invalidDOB5() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="invalidDOB5";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view access code text area")
	public void validDOB() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="validDOB";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view next page of category page")
	public void viewNextPage() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="viewNextPage";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view previous page of category page")
	public void viewPreviousPage() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="viewPreviousPage";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view impression sub category page")
	public void viewImpressionsSubCategoryPage() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="viewImpressionsSubCategoryPage";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view impression sub category page")
	public void printProductPage() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="printProductPage";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
	@Test (description ="view impression sub category page")
	public void selectLoadAndGoSKU() throws InterruptedException{
		moduleName="siteNavigationModule";
		methodName="selectLoadAndGoSKU";
		initialize(moduleName,methodName);
		testCaseUtil.checkAllFormElements(moduleName, methodName, driver);
		processElements(testCaseUtil,mLink,"click",moduleName,methodName,driver);
	}
}
