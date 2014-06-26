package com.tempo.bat.tests;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tempo.common.SeleniumObject;
import com.tempo.common.SeleniumVariables;
import com.tempo.common.Utility;
import com.tempo.uiobjects.CoreTempoLoginPage;



public class LoginPageTest {

	public WebDriver driver;
	public String sAppURL=null;
	
	
	@BeforeClass (alwaysRun = true)
	public void setUp () throws Exception
	{
		SeleniumVariables.setUp ();
	}

	@BeforeMethod (alwaysRun = true)
	public void StartApplication() throws Exception{
		sAppURL = SeleniumVariables.getApplicationURL();
		driver= SeleniumObject.setUp (sAppURL);

	}
	
	@Test ( groups = { "@All", "@Acceptance" })
	public void testLogin() throws Exception {
		Utility.WriteToLog("info","Test case Description: " + "Test Tempo Login");
		CoreTempoLoginPage coreLogin = new CoreTempoLoginPage(driver);
		coreLogin.loginTempo("cacp", "medium5");	    
		
		Utility.WriteToLog("debug", "Completed verification for Tempo Login");
		
	}

	
	@AfterMethod (alwaysRun = true)
	public void CloseBrowser()
	{
		SeleniumObject.CloseBrowser ();
	}
	
	@AfterClass (alwaysRun = true)
	public void tearDown ()  {
		
	}

}
