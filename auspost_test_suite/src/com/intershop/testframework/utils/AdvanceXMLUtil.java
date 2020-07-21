package com.intershop.testframework.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.intershop.testframework.utils.xml.Button;
import com.intershop.testframework.utils.xml.Channel;
import com.intershop.testframework.utils.xml.Click;
import com.intershop.testframework.utils.xml.Dropdown;
import com.intershop.testframework.utils.xml.LoopMsg;
import com.intershop.testframework.utils.xml.Mandatory;
import com.intershop.testframework.utils.xml.Module;
import com.intershop.testframework.utils.xml.MouseOver;
import com.intershop.testframework.utils.xml.Never;
import com.intershop.testframework.utils.xml.NonMandatory;
import com.intershop.testframework.utils.xml.Selected;
import com.intershop.testframework.utils.xml.Text;
import com.intershop.testframework.utils.xml.TextBox;
import com.intershop.testframework.utils.xml.VerifyText;
import com.intershop.testframework.utils.xml.XMLMethod;
 
public class AdvanceXMLUtil {
 
	static Channel channel = null;
	
	
	public static String getValueForTimeOutById (String moduleName, String method,String nodeName) 
	{
		XMLMethod xmlMethod=getxmlMethod (moduleName, method);
		if(xmlMethod!=null)
		{	
			System.out.println("Timeout value for method "+method+": "+xmlMethod.getTimeOut().toString());
			return xmlMethod.getTimeOut().toString();
		}
		else
		{
				return new String("120"); //default timeout of 120 seconds
		}
	}
	
	public static XMLMethod getxmlMethod (String moduleName, String method) 
	{
		for(Module module:channel.getModules())
		{
			if(module.getName().equals(moduleName))
			{
				for(XMLMethod xmlMethod:module.getXmlMethods())
				{
					if(xmlMethod.getName().equals(method))
					{
						return 	xmlMethod;
					}
				}
			}
		}
		return 	null;
	}
	
	public static Map<String,String> getAllVerifyTextForClick(String moduleName, String method,String buttonId,String paramType) 
	{
		LinkedHashMap<String,String> verifyTextMap=new LinkedHashMap<String, String>();
		XMLMethod xmlMethod=getxmlMethod (moduleName, method);
		if(xmlMethod.getClick()!=null)
		{
			for(Click click:xmlMethod.getClick())
			{
				Button button=getClickButtonById(click,buttonId);
				
				if(button!=null && button.getVerifyTexts()!=null)
				{
					for(VerifyText verifyText :button.getVerifyTexts())
					{
						verifyTextMap=getVerifyTextData(verifyText,paramType);
					}
				}
			}
		}
		
		System.out.println(paramType+" map to validate after click= "+verifyTextMap);
		return verifyTextMap;
	}
	
	private static LinkedHashMap<String, String>  getVerifyTextData(VerifyText verifyText,String paramType)
	{
		String key=null,value=null;
		LinkedHashMap<String, String> returnMap=new LinkedHashMap<String,String>();
		if(("LoopMsg").equalsIgnoreCase(paramType))
		{
			List<LoopMsg> loopMsgList=verifyText.getLoopMessage();
			if(loopMsgList!=null)
			{
				for(LoopMsg loopMsg:loopMsgList)
				{
					if(loopMsg.getId()!=null)
					{
						key=loopMsg.getId();
						value=loopMsg.getValue()!=null?loopMsg.getValue():loopMsg.getXmlValue();
						value=value.replace("&", "&amp;");
						returnMap.put(key,value);				
					}
				}
			}
		}
		else if(("Mandatory").equalsIgnoreCase(paramType))
		{
			List<Mandatory> mandatoryList=verifyText.getMandatory();
			if(mandatoryList!=null)
			{
			
				for(Mandatory mandatory:mandatoryList)
				{
					if(mandatory.getId()!=null)
					{
						key=mandatory.getId();
						value=mandatory.getValue()!=null?mandatory.getValue():mandatory.getXmlValue();
						value=value.replace("&", "&amp;");
						returnMap.put(key,value);				
					}
				}
			}
		}
		else if(("nonMandatory").equalsIgnoreCase(paramType))
		{
			List<NonMandatory> nonMandatoryList=verifyText.getNonMandatory();
			if(nonMandatoryList!=null)
			{
				for(NonMandatory nonMandatory:nonMandatoryList)
				{
					if(nonMandatory.getId()!=null)
					{
						key=nonMandatory.getId();
						value=nonMandatory.getValue()!=null?nonMandatory.getValue():nonMandatory.getXmlValue();
						value=value.replace("&", "&amp;");
						returnMap.put(key,value);				
					}
				}
			}
		}
		else if(("never").equalsIgnoreCase(paramType))
		{
			List<Never> neverList=verifyText.getNever();
			if(neverList!=null)
			{
				for(Never never:neverList)
				{
					if(never.getId()!=null)
					{
						key=never.getId();
						value=never.getValue()!=null?never.getValue():never.getXmlValue();
						value=value.replace("&", "&amp;");
						returnMap.put(key,value);				
					}
				}
			}
		}
		return returnMap;
	}

	
	public static HashMap<String,String> getAllVerifyTextForMethodByParamType(String moduleName,String method,String paramType) 
	{
		LinkedHashMap<String,String> verifyTextMap=new LinkedHashMap<String, String>();
		 
		XMLMethod xmlMethod=getxmlMethod (moduleName, method);
		if(xmlMethod.getVerifyTexts()!=null)
		{
			for(VerifyText verifyText:xmlMethod.getVerifyTexts())
			{
				verifyTextMap=getVerifyTextData(verifyText,paramType);
			}
				
		}
	
		System.out.println(paramType+" map to validate on page load = "+verifyTextMap);
		return verifyTextMap;
		
	}
	private static Button getClickButtonById(Click click,String buttonId) 
	{
		if(click.getButtons()!=null)
		{
			for(Button button: click.getButtons())
			{
				if(button.getId()!=null && button.getId().equals(buttonId))
				{
					return button;
				}
			}
		}
		return null;
	}
	
	public static LinkedHashMap<String,String> getAllChildrenForDropDown(String moduleName, String method)
	{
		LinkedHashMap<String,String> dropdownMap=new LinkedHashMap<String, String>();
		XMLMethod xmlMethod=getxmlMethod (moduleName, method);
		String key,value=null;
		if(xmlMethod.getDropdowns()!=null)
		{
			for(Dropdown dropdown:xmlMethod.getDropdowns())
			{
				if(dropdown.getSelected()!=null)
				{
					for(Selected selected: dropdown.getSelected())
					{
						if(selected.getId()!=null)
						{
							key=selected.getId();
							value=selected.getIndex()!=null?selected.getIndex():selected.getValue();
							dropdownMap.put(key,value);				
						}
					}
				}	
			}
		}
		System.out.println("Dropdown map to select= "+dropdownMap);
		return dropdownMap;
	}
	public static LinkedHashMap<String,String> getAllChildrenForTextbox(String moduleName,String method)
	{
		LinkedHashMap<String,String> textboxMap=new LinkedHashMap<String, String>();
		XMLMethod xmlMethod=getxmlMethod (moduleName, method);
		String key,value=null;
		if(xmlMethod.getTextBoxs()!=null)
		{
			for(TextBox textBox:xmlMethod.getTextBoxs())
			{
				if(textBox.getTextElements()!=null)
				{
					for(Text text: textBox.getTextElements())
					{
						if(text.getId()!=null)
						{
							key=text.getId();
							value=text.getXmlValue()!=null?text.getXmlValue():text.getValue();
							textboxMap.put(key,value);				
						}
					}
				}	
			}
		}
		System.out.println("Textbox map to type= "+textboxMap);
		return textboxMap;
	}
	public static LinkedHashMap<String,String> getAllChildrenForClick(String moduleName, String method)
	{	
		LinkedHashMap<String,String> clickMap=new LinkedHashMap<String, String>();
		XMLMethod xmlMethod=getxmlMethod (moduleName, method);
		String key,value=null;
		if(xmlMethod.getClick()!=null)
		{
			for(Click click:xmlMethod.getClick())
			{
				if(click.getButtons()!=null)
				{
					for(Button button: click.getButtons())
					{
						if(button.getId()!=null)
						{
							key=button.getId();
							value=button.getValue()!=null?button.getValue():button.getXmlValue();
							value=value.replace("&", "&amp;");
							clickMap.put(key,value);				
						}
					}
				}	
			}
		}
		System.out.println("Element map to click = "+clickMap);
		return clickMap;
	}
	public static LinkedHashMap<String,String> getAllChildrenForMouseOver(String moduleName, String method) 
	{
		LinkedHashMap<String,String> mouseOverMap=new LinkedHashMap<String, String>();
		XMLMethod xmlMethod=getxmlMethod (moduleName, method);
		String key,value=null;
		if(xmlMethod.getMouseOver()!=null)
		{
			for(MouseOver mouseOver:xmlMethod.getMouseOver())
			{
				if(mouseOver.getButtons()!=null)
				{
					for(Button button: mouseOver.getButtons())
					{
						if(button.getId()!=null)
						{
							key=button.getId();
							value=button.getValue()!=null?button.getValue():button.getXmlValue();
							mouseOverMap.put(key,value);				
						}
					}
				}	
			}
		}
		System.out.println("Element map to mouseover = "+mouseOverMap);
		return mouseOverMap;
	}
		
	
	public static void loadXml(String xmlFileName) 
		{
		try{
			String xmlFile = PropertyLoader.getXMLResources().getProperty(xmlFileName);
			if (xmlFile != null) {
				JAXBContext jaxbContext = JAXBContext.newInstance(Channel.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				InputStream in = PropertyLoader.class.getResourceAsStream(xmlFile);
				channel = (Channel) jaxbUnmarshaller.unmarshal(in);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


}
