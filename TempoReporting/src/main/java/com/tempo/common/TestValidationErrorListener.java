package com.tempo.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.tempo.common.SeleniumVariables;
import com.tempo.common.Utility;


/**
 *
 */

public class TestValidationErrorListener extends SeleniumObject implements ITestListener, ISuiteListener {
    
	int totalExecuted ,totalPassed, totalFailed, totalSkipped, notApplicable; 
	float passPercent;
	long startDuration, endDuration;
	public static Writer htmlReportWriter;
	public static Writer htmlReportWriter2;
	public static FileWriter fw;
	public static FileWriter fw2;
	public static String na=null;
	
	/**
	 * Following method is executed at the end of each class mentioned in TestNG config xml 
	 */
	
	@Override
	public void onFinish(ITestContext iTestContext) {

		String sTestSuiteName = iTestContext.getCurrentXmlTest().getName();
		Utility.WriteToLog("info", "---------------------" + sTestSuiteName + " Module test execution COMPLETED" + "---------------------");
		
	}
	
	/**
	 * Following method is executed at the beginning of each class mentioned in TestNG config xml 
	 */
	
	@Override
	public void onStart(ITestContext iTestContext) {
		// TODO Auto-generated method stub
		
		String sTestSuiteName = iTestContext.getCurrentXmlTest().getName();
		Utility.WriteToLog("info", "---------------------" + sTestSuiteName + " Module test execution STARTED" + "---------------------");
		Utility.WriteToLog("info", "  ");
		
		try {
			htmlReportWriter.append("<tr><td><b>" + sTestSuiteName + "</b></td></tr>");
			htmlReportWriter2.append("<tr><td><b>" + sTestSuiteName + "</b></td></tr>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Following method is executed at the end of a test case that was failed 
	 */
	
	@Override
	public void onTestFailure(ITestResult iTestResult) {

		try{
			String sTestMethodName = iTestResult.getMethod().getMethodName();
			String sTestSuiteName = iTestResult.getTestClass().getRealClass().getSimpleName();
		
			Utility.WriteToLog("info", "---------------------" + sTestSuiteName + "."+ sTestMethodName + " testcase execution ENDED with FAILURE" + "---------------------");
		
			totalFailed = totalFailed + 1;
			Utility.hidePopup();
			Utility.showPopup(sTestMethodName + " FAILED", "Passed: " + totalPassed + "; Failed: " + totalFailed + "; Skipped: " + totalSkipped);
			htmlReportWriter.append("<tr><td>" + sTestMethodName + "</td><td><font color=red>Failed</font></td></tr>");
			
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}
	/**
	 * Following method is executed at the end of a test case that was skipped 
	 */
	
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		String sTestMethodName = iTestResult.getMethod().getMethodName();
		String sTestSuiteName = iTestResult.getTestClass().getRealClass().getSimpleName();
		Utility.WriteToLog("info", "---------------------" + sTestSuiteName + "."+ sTestMethodName + " testcase execution SKIPPED" + "---------------------");
		try {
		
			Utility.hidePopup();
			Utility.showPopup(sTestMethodName+ "SKIPPED", "Passed: " + totalPassed + "; Failed: " + totalFailed + "; Skipped: " + totalSkipped);
			if (na.equalsIgnoreCase("Not Applicable")) {
				notApplicable = notApplicable + 1;
				htmlReportWriter.append("<tr><td>" + sTestMethodName + "</td><td><font color=blue>Not Applicable</font></td></tr>");
				htmlReportWriter2.append("<tr><td>" + sTestMethodName + "</td><td><font color=blue>Not Applicable</font></td><td>-</td><td>-</td></tr>");
				na = null;
			} else {
				totalSkipped = totalSkipped + 1;
				htmlReportWriter.append("<tr><td>" + sTestMethodName + "</td><td><font color=blue>Skipped</font></td></tr>");
				htmlReportWriter2.append("<tr><td>" + sTestMethodName + "</td><td><font color=blue>Skipped</font></td><td>-</td><td>-</td></tr>");
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Following method is executed before each test case 
	 */
	
	@Override
	public void onTestStart(ITestResult iTestResult){

		try{
			totalExecuted = totalExecuted + 1;
			String sTestMethodName = iTestResult.getMethod().getMethodName();
			String sTestSuiteName = iTestResult.getTestClass().getRealClass().getSimpleName();
			
			Utility.WriteToLog("info", "---------------------" + sTestSuiteName + "."+ sTestMethodName + " testcase execution STARTED" + "---------------------");
			Utility.WriteToLog("info", "  ");
			Utility.initializePopup();
			Utility.showPopup("Executing: "+sTestMethodName, "Passed: " + totalPassed + "; Failed: " + totalFailed + "; Skipped: " + totalSkipped);
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("Recording Error in OnTestStart" + e.getMessage());
		}
		
	}

	/**
	 * Following method is executed at the end of a test case that was passed 
	 */
	
	@Override
	public void onTestSuccess(ITestResult iTestResult) {

		try{
			String sTestMethodName = iTestResult.getMethod().getMethodName();
			String sTestSuiteName = iTestResult.getTestClass().getRealClass().getSimpleName();
			
			totalPassed = totalPassed + 1;
			Utility.hidePopup();
			Utility.showPopup(sTestMethodName + " PASSED", "Passed: " + totalPassed + "; Failed: " + totalFailed + "; Skipped: " + totalSkipped);
			
			
			Utility.WriteToLog("info", "---------------------" + sTestSuiteName + "."+ sTestMethodName + " testcase execution FINISHED SUCCESSFULLY" + "---------------------");
			
			htmlReportWriter.append("<tr><td>" + sTestMethodName + "</td><td><font color=green>Passed</font></td></tr>");
			htmlReportWriter2.append("<tr><td>" + sTestMethodName + "</td><td><font color=green>Passed</font><td>-</td><td>-</td></tr>");
			
			
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("Recording Error in OnTestSuccess" + e.getMessage());
		}
	}
	
	/**
	 * Following method is executed at the end of complete test suite run 
	 */
	@Override
	public void onFinish(ISuite suite) {
		endDuration = System.currentTimeMillis();
		String executionDuration = Utility.CalculateDuration(startDuration, endDuration);
		Utility.WriteToLog("info", "Execution Durarion: "+executionDuration);
		Utility.WriteToLog("info","Execution Duration:" + executionDuration);
		Utility.WriteToLog("info", "================================Suite Result: "+ suite.getName() +"==================================");
		Utility.WriteToLog("info","Number of testcases in test suite:" + totalExecuted);
		Utility.WriteToLog("info","Total Passed:" + totalPassed);
		Utility.WriteToLog("info","Total Failed:" + totalFailed);
		Utility.WriteToLog("info","Total Skipped:" + totalSkipped);
		Utility.WriteToLog("info","Not Applicable:" + notApplicable);
		try {
			GraphReport.CreatePng(totalPassed, totalFailed, totalSkipped);
		} catch (IOException e) {
			Utility.WriteToLog("debug", "Problem with creating pie chart");
		}
		totalExecuted = totalExecuted - notApplicable;
		passPercent = 100*totalPassed/totalExecuted;
		Utility.WriteToLog("info","Pass percentage:" + passPercent);
		
		Utility.WriteToLog("info", "================================Ending of suite "+ suite.getName() +"==================================");
		//code to purge old report & log files
		Utility.WriteToLog("info", "Purging TestNG reports and Validation errors...");
		Utility.purgeReportsAndLogsOlderThanNdays(SeleniumVariables.getPurgeDuration(), System.getProperty("user.dir") + "/testresult/"+SeleniumVariables.getApplicationName());
		Utility.WriteToLog("info", "Purging logs...");
		Utility.purgeReportsAndLogsOlderThanNdays(SeleniumVariables.getPurgeDuration(),System.getProperty("user.dir") + "/logs");
		
		//Closing HTML report
		try {
			htmlReportWriter.append("</table></body></html>");
			htmlReportWriter.close();
			htmlReportWriter2.append("</table></body></html>");
			htmlReportWriter2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * Following method is executed before the test suite is started
	 */
	@Override
	public void onStart(ISuite suite) {
		Utility.WriteToLog("info", "===================================Starting of suite==================================");
		Utility.WriteToLog("info", "Suite Name: "+ suite.getName());
		try {
			Utility.WriteToLog("info", "On: "+ Utility.getCurrentDate());
		} catch (Exception e) {
			Utility.WriteToLog("info", e.getMessage());
		}
		
		//create HTML report
		
		File htmlReport = new File("Report.html");
		if (htmlReport.exists()){
			htmlReport.delete();
		}
		
		//String sCurrentDate;
		try {
			SeleniumVariables.setUp();
			String sCurrentDate = Utility.getCurrentDate();
			String htmlReportName2= System.getProperty("user.dir")  + "/testresult/"+SeleniumVariables.getApplicationName()+ "/"+ sCurrentDate + "/"+ SeleniumVariables.getBrowser()+"/ErrorReport.html";
			File htmlReport2 = new File(htmlReportName2);
			if (htmlReport2.exists()){
				htmlReport2.delete();
			}
			fw = new FileWriter("Report.html",true);
			htmlReportWriter = new BufferedWriter(fw);
			htmlReportWriter.append("<html><body><b><h2 align='center'>Test Execution Results</h2></b><br>");
			htmlReportWriter.append("<table border='1' align='center'><tr><td><b>Application URL</b></td><td>"+ SeleniumVariables.getApplicationURL() +"</td></tr>");
			htmlReportWriter.append("<tr><td><b>Browser</b></td><td>"+ SeleniumVariables.getBrowser() + "</td></tr>");
			htmlReportWriter.append("<tr><td><b>OS</b></td><td>"+ System.getProperty("os.name") + "</td></tr></table><p>");
			htmlReportWriter.append("<table align='center' border=1><tr bgcolor='#FFFF00'><td><b>TC Name</b></td><td><b>Status</b></td></tr>");
			
			fw2 = new FileWriter(htmlReportName2,true);
			htmlReportWriter2 = new BufferedWriter(fw2);
			htmlReportWriter2.append("<html><body><b><h2 align='center'>Test Execution Results</h2></b><br>");
			htmlReportWriter2.append("<table border='1' align='center'><tr><td><b>Application URL</b></td><td>"+ SeleniumVariables.getApplicationURL() +"</td></tr>");
			htmlReportWriter2.append("<tr><td><b>Browser</b></td><td>"+ SeleniumVariables.getBrowser() + "</td></tr>");
			htmlReportWriter2.append("<tr><td><b>OS</b></td><td>"+ System.getProperty("os.name") + "</td></tr></table><p>");
			htmlReportWriter2.append("<table align='center' border=1><tr bgcolor='#FFFF00'><td><b>TC Name</b></td><td><b>Status</b></td><td><b>Screenshot</b></td><td><b>Recording</b></td></tr>");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
				
		//Initialize variables
		totalExecuted = 0;
		totalPassed = 0;
		totalFailed = 0;
		totalSkipped= 0;
		notApplicable=0;
		passPercent = 0;
		startDuration = System.currentTimeMillis();
		
	}	
}
