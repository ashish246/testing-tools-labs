package com.intershop.testframework.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.SeleneseTestBase;

public class TestCaseUtil extends SeleneseTestBase{

	 int timeout=60; //default timeout value in seconds
	 
	 public void delay(long milliseconds) {
		   try {
		    Thread.sleep(milliseconds);
		   } catch (InterruptedException e) {
		    e.printStackTrace();
		    throw new Error(e);

		   }
		  }

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Test
	public void checkAllTextMsgs(String moduleName, String methodName,String clickId,WebDriver driver)
	{
		Map<String,String> mVerifyTextMandatory = AdvanceXMLUtil.getAllVerifyTextForClick(moduleName, methodName,clickId,"Mandatory");
		Map<String,String> mVerifyTextnonMandatory = AdvanceXMLUtil.getAllVerifyTextForClick(moduleName,methodName,clickId,"nonMandatory");
		Map<String,String> mVerifyTextloopMsg = AdvanceXMLUtil.getAllVerifyTextForClick(moduleName,methodName,clickId,"loopMsg");
		Map<String,String> mVerifyTextNever = AdvanceXMLUtil.getAllVerifyTextForClick(moduleName,methodName,clickId,"never");
		Map<String,String> mVerifyTextMandatoryOnMethod = AdvanceXMLUtil.getAllVerifyTextForMethodByParamType(moduleName, methodName,"Mandatory");
		Map<String,String> mVerifyTextnonMandatoryOnMethod = AdvanceXMLUtil.getAllVerifyTextForMethodByParamType(moduleName,methodName,"nonMandatory");
		Map<String,String> mVerifyTextloopMsgOnMethod = AdvanceXMLUtil.getAllVerifyTextForMethodByParamType(moduleName,methodName,"loopMsg");
		Map<String,String> mVerifyTextNeverOnMethod = AdvanceXMLUtil.getAllVerifyTextForMethodByParamType(moduleName,methodName,"never");
		
		if(!clickId.equals("noClick")) // if text is to be verified after a button is clicked
		{
			if(!mVerifyTextloopMsg.isEmpty())
			{
				for(String key: mVerifyTextloopMsg.keySet())
				{
					final String textToVerify=mVerifyTextloopMsg.get(key);
					try
					{
						new WebDriverWait(driver,timeout).until(new ExpectedCondition<Boolean>() {
							public Boolean apply(WebDriver driver) {     
								return driver.getPageSource().toLowerCase().contains(textToVerify.toLowerCase());
							}
				    });
					}
					catch(TimeoutException e)
					{
						fail("Couldn't find text : "+textToVerify);
					}
				}
			}
			if(!mVerifyTextMandatory.isEmpty())
			{
				for(String key:mVerifyTextMandatory.keySet())
				{
					if(driver.findElements(By.linkText(mVerifyTextMandatory.get(key))).size() !=0){
						Assert.assertTrue(driver.findElement(By.linkText(mVerifyTextMandatory.get(key))).isDisplayed(),"Could not find mandatory text: "+ mVerifyTextMandatory.get(key));
					}
					else{
						Assert.assertTrue(driver.getPageSource().toLowerCase().contains(mVerifyTextMandatory.get(key).toLowerCase()),"Could not find mandatory text: "+ mVerifyTextMandatory.get(key));
					}
				}
			}
			if(!mVerifyTextNever.isEmpty())
			{
				for(String key:mVerifyTextNever.keySet())
				{
					if(driver.findElements(By.linkText(mVerifyTextNever.get(key))).size() !=0){
						Assert.assertFalse(driver.findElement(By.linkText(mVerifyTextNever.get(key))).isDisplayed());
					}
					else{
						Assert.assertFalse(driver.getPageSource().toLowerCase().contains(mVerifyTextNever.get(key).toLowerCase()),"Found unexpected text: "+mVerifyTextNever.get(key));
					}
				}
			}
			
			
			if(!mVerifyTextnonMandatory.isEmpty())
			{
				for(String key:mVerifyTextnonMandatory.keySet())
				{
					driver.getPageSource().toLowerCase().contains(mVerifyTextnonMandatory.get(key).toLowerCase());
				}
			}

		}
		else /// if text is to be verified at method level i.e., no button has been clicked and text is verified at page load
		{
			if(!mVerifyTextloopMsgOnMethod.isEmpty())
			{
				for(String key: mVerifyTextloopMsgOnMethod.keySet())
				{
					final String textToVerify=mVerifyTextloopMsgOnMethod.get(key);
					try
					{
						new WebDriverWait(driver,timeout).until(new ExpectedCondition<Boolean>() {
					    public Boolean apply(WebDriver driver) {                
				            return driver.getPageSource().toLowerCase().contains(textToVerify.toLowerCase());
				        	}
						});
					}
					catch(TimeoutException e)
					{
						fail("Couldn't find text : "+textToVerify);
					}
				}	
			}
			if(!mVerifyTextMandatoryOnMethod.isEmpty())
			{
				for(String key:mVerifyTextMandatoryOnMethod.keySet())
				{
					if(driver.findElements(By.linkText(mVerifyTextMandatoryOnMethod.get(key))).size() !=0)
					{
					Assert.assertTrue(driver.findElement(By.linkText(mVerifyTextMandatoryOnMethod.get(key))).isDisplayed(),"Could not mandatory find text: "+ mVerifyTextMandatoryOnMethod.get(key));
					}
					else {
						Assert.assertTrue(driver.getPageSource().toLowerCase().contains(mVerifyTextMandatoryOnMethod.get(key).toLowerCase()),"Could not mandatory find text: "+ mVerifyTextMandatoryOnMethod.get(key));
					}
				}
			}
			if(!mVerifyTextNeverOnMethod.isEmpty())
			{
				for(String key:mVerifyTextNeverOnMethod.keySet())
				{
					if(driver.findElements(By.linkText(mVerifyTextNeverOnMethod.get(key))).size() !=0)
					{
					Assert.assertFalse(driver.findElement(By.linkText(mVerifyTextNeverOnMethod.get(key))).isDisplayed(),"Found unexpected text: "+mVerifyTextNeverOnMethod.get(key));
					}
					else {
						Assert.assertFalse(driver.getPageSource().toLowerCase().contains(mVerifyTextNeverOnMethod.get(key).toLowerCase()),"Found unexpected text: "+mVerifyTextNeverOnMethod.get(key));
					}
				}
			}
			
			if(!mVerifyTextnonMandatoryOnMethod.isEmpty())
			{
				for(String key:mVerifyTextnonMandatoryOnMethod.keySet())
				{
					driver.getPageSource().toLowerCase().contains(mVerifyTextnonMandatoryOnMethod.get(key).toLowerCase());
				}
			}
			
		}
		
	}
	
	public void checkAllFormElements(String moduleName, String methodName,WebDriver driver)
	{
		Map<String, String> mDropDownValue = AdvanceXMLUtil.getAllChildrenForDropDown(moduleName, methodName);
		LinkedHashMap<String,String> mTypeValue = AdvanceXMLUtil.getAllChildrenForTextbox(moduleName, methodName);
		
		if(!mDropDownValue.isEmpty())
		{
			WebElement select = null;
			for(String key:mDropDownValue.keySet())
			{
				 boolean CSSSelector = true;
				 try{
					 if(driver.findElements(By.cssSelector(key)).size() != 0)
						{
								//webElement=(new WebDriverWait(driver, Integer.parseInt(timeoutString))).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector((mLink.get(key)))));
						}
				 }catch(Throwable t){
					System.out.println("Error occurred during finding element by CSS selector: "+ t.getMessage()); 
					CSSSelector = false;
				 }
				 
				if(driver.findElements(By.id(key)).size() != 0)
				{
					select = (new WebDriverWait(driver, timeout)).until(ExpectedConditions.presenceOfElementLocated(By.id(key)));
				}
				else if(CSSSelector)
				{
					select = (new WebDriverWait(driver, timeout)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(key)));
				}
				else
				{
					select = (new WebDriverWait(driver, timeout)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(key)));
				}
			   
				List<WebElement> options = select.findElements(By.tagName("option"));
			    for (WebElement option : options) {
			    	if(option.getText().contains(mDropDownValue.get(key)))
			    	{
			            option.click();
			            break;
			        }
			    }
			}
			
		}
		if(!mTypeValue.isEmpty())
		{
				WebElement textField=null;
				for(String key:mTypeValue.keySet())
				{
					 boolean CSSSelector = true;
					 try{
						 if(driver.findElements(By.cssSelector(key)).size() != 0)
							{
									//webElement=(new WebDriverWait(driver, Integer.parseInt(timeoutString))).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector((mLink.get(key)))));
							}
					 }catch(Throwable t){
						System.out.println("Error occurred during finding element by CSS selector: "+ t.getMessage()); 
						CSSSelector = false;
					 }
					
					 if(driver.findElements(By.id(key)).size() != 0)
						{
								textField=(new WebDriverWait(driver, timeout)).until(ExpectedConditions.presenceOfElementLocated(By.id(key)));
								textField.clear();
								textField.sendKeys(mTypeValue.get(key));
						}
					 else if(CSSSelector)
					{
							textField=(new WebDriverWait(driver, timeout)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(key)));
							driver.findElement(By.cssSelector(key)).clear();
							driver.findElement(By.cssSelector(key)).sendKeys(mTypeValue.get(key));
					}
					else if(driver.findElements(By.xpath(key)).size() != 0)
					{
							textField=(new WebDriverWait(driver, timeout)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(key)));
							driver.findElement(By.xpath(key)).clear();
							driver.findElement(By.xpath(key)).sendKeys(mTypeValue.get(key));
					}
					else if(driver.findElements(By.name(key)).size() != 0)
					{
							textField=(new WebDriverWait(driver, timeout)).until(ExpectedConditions.presenceOfElementLocated(By.name(key)));
							textField.clear();
							textField.sendKeys(mTypeValue.get(key));
					}
					
					
				}
		}

	}
}
