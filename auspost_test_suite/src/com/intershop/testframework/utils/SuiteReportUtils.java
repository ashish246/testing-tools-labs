package com.intershop.testframework.utils;


import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.unitedinternet.portal.selenium.utils.logging.LoggingUtils;


public class SuiteReportUtils implements ISuiteListener{
	
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
     static public WritableWorkbook workbook;
     static public int testCaseName= 0;
     static public int classMethod=1;
     static public int exception= 2;
     static public int customer= 3;
     static public int browserCol= 4;
     static public int versionCol= 5;
     static public int channelCol= 6;
     static public int localeCol= 7;
     static public int time= 8;
     static public int rowNum = 0;
     static public String browser=null,version=null;
     static public String site=null;
     static public String channel=null;
     static public String locale=null;
     static public String currFileName = null;
     static public String pdfFileName =null;
     static public String pdfTestCaseName =null;
     public WritableSheet sheet=null;
     public File reportDir=null;
     public ArrayList<String> arrFileNames =null,existingPDFFiles=null;
     public File pdfReportDir=null;
     public String testCaseStartAt =null;
     String currDir=null;
     
     static public Document pdfdocument = null;
     static public boolean reportAttributesAdded=false;

     @Override
     public void onFinish(ISuite arg0)
     {
    	 try 
    	 {
    		 if(workbook != null)
    		 {
    			 workbook.write();
    			 workbook.close();
    		 }
    		 if(pdfdocument!=null && pdfdocument.isOpen())
    		 {
    			 pdfdocument.close();
    		 }
    		 if(new File(pdfReportDir+File.separator+pdfFileName).length() == 0)
    		 {
    			 new File(pdfReportDir+File.separator+pdfFileName).delete();	 // if the pdf report has no entry then delete the pdf file.
    		 }
	
    	 }
    	 catch (WriteException e)
    	 {
    		 e.printStackTrace();
    	 }
    	 catch (IOException e)
    	 {
    		 e.printStackTrace();
    	 }
     }
     @Override
	public void onStart(ISuite arg0)
     {
		site = arg0.getXmlSuite().getParameter("site");
		channel = arg0.getXmlSuite().getParameter("channel");
		browser = arg0.getXmlSuite().getParameter("selenium.browser");
		version = arg0.getXmlSuite().getParameter("selenium.browser.version");
		locale = arg0.getXmlSuite().getParameter("locale");
		currDir =  System.getProperty("user.dir")+File.separator+"TestcasesReports";
		String reports = "reports";
		reportDir = new File(currDir+File.separator+site+File.separator+dateformat.format(new Date())+File.separator+reports);
		String fileName = site+"_"+LoggingUtils.timeStampForFileName(); 
		currFileName = reportDir+File.separator+fileName+".xls";
		System.out.println("reportDir ::::::::::::::::::::::::::::::::::::: "+reportDir);
		if(!reportDir.exists()){
			reportDir.mkdirs();
		}
		
		arrFileNames = new ArrayList<String>();
		for(String arrStrFileName:reportDir.list())
		{
			/*Adding fileNames in array to check if the file exists so that the code can be appended in the same report*/
			arrFileNames.add(arrStrFileName);
		}
		try {
			WritableFont wrapCell =  new WritableFont(WritableFont.TIMES, 10,WritableFont.BOLD, false);
			WritableCellFormat headerCellformat = new WritableCellFormat(wrapCell);
			headerCellformat.setWrap(true);
			headerCellformat.setBackground(Colour.GRAY_25);
			
			if(arrFileNames.isEmpty() && !arrFileNames.toString().contains(site+"_"+dateformat.format(new Date()))){
				workbook = Workbook.createWorkbook(new File(currFileName));	
				sheet = workbook.createSheet(site, 0);
				Label label1 = new Label(testCaseName,rowNum, "Automation ID",headerCellformat);
				sheet.setColumnView(testCaseName, 50);
				sheet.addCell(label1);
				Label label2 = new Label(classMethod, rowNum,"Steps",headerCellformat);
				sheet.setColumnView(classMethod, 50);
				sheet.addCell(label2);
				Label label4 = new Label(exception,rowNum,"Test Result",headerCellformat);
				sheet.setColumnView(exception, 50);
				sheet.addCell(label4);
				Label label5 = new Label(customer,rowNum,"Webshop",headerCellformat);
				sheet.addCell(label5);
				Label label6 = new Label(browserCol,rowNum, "Browser",headerCellformat);
				sheet.addCell(label6);
				Label label7 = new Label(versionCol,rowNum, "Version",headerCellformat);
				sheet.addCell(label7);
				Label label8 = new Label(channelCol,rowNum, "Channel",headerCellformat);
				sheet.setColumnView(channelCol, 15);
				sheet.addCell(label8);
				Label label9 = new Label(localeCol,rowNum, "Locale",headerCellformat);
				sheet.addCell(label9);
				Label label3 = new Label(time,rowNum,"Time(ms)",headerCellformat);
				sheet.addCell(label3);
				rowNum++;
			}
			else
			{
				int index=0;
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for(String str: arrFileNames)
				{
					///// search using  : write in an excel from a particular row +java jxl
					if(str.contains(site+"_"+dateformat.format(new Date())+"_"))
					{
						index++;
					}
					
				}
				 Date dt = sdf.parse(sdf.format(new File(reportDir+File.separator+arrFileNames.get(index-1)).lastModified()));
				 Boolean writeInSameFile = getTimeDifference(new Date(),dt);
				 if(!writeInSameFile && new File(reportDir+File.separator+arrFileNames.get(index-1)).getName().contains(".xls")) // if false then create a new file
				 {
					 currFileName = reportDir+File.separator+fileName+".xls";
					 workbook = Workbook.createWorkbook(new File(currFileName));	
						sheet = workbook.createSheet(site, 0);
						Label label1 = new Label(testCaseName,rowNum, "Automation ID",headerCellformat);
						sheet.setColumnView(testCaseName, 50);
						sheet.addCell(label1);
						Label label2 = new Label(classMethod, rowNum,"Steps",headerCellformat);
						sheet.setColumnView(classMethod, 50);
						sheet.addCell(label2);
						Label label4 = new Label(exception,rowNum,"Test Result",headerCellformat);
						sheet.setColumnView(exception, 50);
						sheet.addCell(label4);
						Label label5 = new Label(customer,rowNum,"Webshop",headerCellformat);
						sheet.addCell(label5);
						Label label6 = new Label(browserCol,rowNum, "Browser",headerCellformat);
						sheet.addCell(label6);
						Label label7 = new Label(channelCol,rowNum, "Channel",headerCellformat);
						sheet.setColumnView(channelCol, 15);
						sheet.addCell(label7);
						Label label8 = new Label(localeCol,rowNum, "Locale",headerCellformat);
						sheet.addCell(label8);
						Label label3 = new Label(time,rowNum,"Time(ms)",headerCellformat);
						sheet.addCell(label3);
						
						rowNum++;
				 }
				 else if(new File(reportDir+File.separator+arrFileNames.get(index-1)).getName().contains(".xls"))
				 {
					 // this will make an entry of a channel whose entry is not present in the excel.... try something else 
					 Workbook currWorkbook = Workbook.getWorkbook(new File(reportDir+File.separator+arrFileNames.get(index-1))); 
					 workbook = Workbook.createWorkbook(new File(reportDir+File.separator+arrFileNames.get(index-1)),currWorkbook);
					 sheet = workbook.getSheet(0);
					 rowNum = sheet.getRows() ;
				 }
				
			}
			setPDFReportData(arg0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
	}
     
/*
 * if the time difference between the file and current time is more than five minutes, a new test case is running so create a new file for report 
 * */
public boolean getTimeDifference(Date d1, Date d2)
{
	boolean writeInSameFile = false ;
	
	Calendar cal1 = Calendar.getInstance();
	Calendar cal2 = Calendar.getInstance();
	cal1.setTime(d1);
	cal2.setTime(d2);
	long milliSeconds1 = cal1.getTimeInMillis();
	long milliSeconds2 = cal2.getTimeInMillis();
	long diff = milliSeconds1 - milliSeconds2;
	long minutes = diff/(1000*60);
	System.out.println("difference in minutes is "+minutes);
	if(minutes >= 5)
	{
		writeInSameFile= false;
	}
	else
	{
		writeInSameFile= true;
	}
	
	
	return writeInSameFile;
}

/*
 * Generates a pdf for testCases
 * */
public void setPDFReportData(ISuite result)
{
	pdfdocument = new Document();
	try {
		pdfReportDir = new File(currDir+File.separator+site+File.separator+dateformat.format(new Date())+File.separator+"pdf"+File.separator);
		if(!pdfReportDir.exists()){
			pdfReportDir.mkdirs();
		}
		pdfFileName =channel+"_"+browser.substring(1)+"_"+version+"_"+LoggingUtils.timeStampForFileName()+".pdf";
		PdfWriter.getInstance(pdfdocument, new FileOutputStream(pdfReportDir+File.separator+pdfFileName));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (DocumentException e) {
		e.printStackTrace();
	}
	pdfdocument.open();
	
}


/*
 * set the value of the testCase name which is put in PDF on start
 * */
public void getPDFTestCaseName(ITestContext context)
{
	testCaseStartAt= LoggingUtils.timeStampForFileName();
	String strbrowser = browser.substring(1);
	pdfTestCaseName = channel+"_"+strbrowser+"_"+version+"_"+context.getName()+"_"+testCaseStartAt;
}

/*
 * sets the heading of the failed testcase in the pdf file which is used as reference from the excel. 
 * */
public void setTestCaseName()
{
	Paragraph p = new Paragraph(pdfTestCaseName+ " Results",
			FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD, new Color(0, 0, 255)));
	
	try {
		pdfdocument.add(p);
		pdfdocument.add(new Paragraph(new Date().toString()));
	} catch (DocumentException e1) {
		e1.printStackTrace();
	}
}

}