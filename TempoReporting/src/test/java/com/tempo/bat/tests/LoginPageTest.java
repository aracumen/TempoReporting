package com.tempo.bat.tests;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tempo.common.SeleniumObject;
import com.tempo.common.SeleniumVariables;
import com.tempo.common.Utility;
import com.tempo.uiobjects.CoreAmortizationSchedulePage;
import com.tempo.uiobjects.CoreLoanDetailsForm;
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
	
	
	//@Test ( groups = { "@All", "@Acceptance" })
	public void ValidateSubmitLoanDetailsForm() throws Exception {
		Utility.WriteToLog("info","Test case Description: " + "Validate Submit Loan Details Form");
	    CoreLoanDetailsForm coreLoanDetails = new CoreLoanDetailsForm(driver);
	    CoreAmortizationSchedulePage amortizationSchedulePage = coreLoanDetails.EnterDetailsAndSubmit("1000", "10", "1");
	    amortizationSchedulePage.VerifyElementsInAmortizationSchedulePage();
	    amortizationSchedulePage.VerifyValuesInAmortizationTable(1000, 10, 1);
		
		Utility.WriteToLog("debug", "Completed verification for LoanDetails Form Sumit Feature");
		
	}
	
	//@Test ( groups = { "@All", "@Acceptance" })
	public void ValidateRowAndColumnCountInAmortizationTable() throws Exception {
		Utility.WriteToLog("info","Test case Description: " + "Validate Submit Loan Details Form");
	    CoreLoanDetailsForm coreLoanDetails = new CoreLoanDetailsForm(driver);
	    CoreAmortizationSchedulePage amortizationSchedulePage = coreLoanDetails.EnterDetailsAndSubmit("1000", "10", "1");
	    amortizationSchedulePage.VerifyRowAndColumnCountInAmortizationTable(1);
		
		Utility.WriteToLog("debug", "Completed verification for LoanDetails Form Sumit Feature");
		
	}
	
	//@Test ( groups = { "@All", "@Acceptance" })
	public void ValidateAmortizationScheduleTable() throws Exception {
		Utility.WriteToLog("info","Test case Description: " + "Validate Amortization Schedule Table");
	    CoreLoanDetailsForm coreLoanDetails = new CoreLoanDetailsForm(driver);
	    CoreAmortizationSchedulePage amortizationSchedulePage = coreLoanDetails.EnterDetailsAndSubmit("1000", "10", "1");
	    amortizationSchedulePage.VerifyValuesInAmortizationTable(1000, 10, 1);
		
		Utility.WriteToLog("debug", "Completed verification for Amortization Schedule Table");
		
	}
	
	/*
	 * Data Provider that contains invalid values that can be passed in the 
	 * input fields present on Loan Details forms.
	 * PS: Data can be read from CSV file also. Due to time constraint not implemented in
	 * this prototype framework.
	 */
	@DataProvider(name = "invalidValues")
	public Object[][] invalidValues() {  

		Object[][] obj = new Object[][] {
		/* Loan amount contains alphabets               */{ "abcd", "10", "1"},
		/* Rate of Interest contains alphabets          */{ "1000", "abcd", "1"},
		/* Term contains alphabets                      */{ "1000", "10", "abcd" },
		/* Loan amount contains special characters      */{ "!@##%", "10", "1" },
		/* Rate of Interest contains special characters */{ "1000", "!@@##$", "1" },
		/* Term contains special characters             */{ "1000", "10", "!@#@$"},
		/* Loan amount contains negative values         */{ "-1000", "10", "1" },
		/* Rate of Interest contains negative values    */{ "1000", "-10", "1" },
		/* Term contains negative values                */{ "1000", "10", "-1" },
		/* Loan amount field empty                      */{ "", "10", "1" },
		/* Rate of Interest field empty                 */{ "1000", "", "1" },
		/* Term field empty                             */{ "1000", "10", "" },
		/* All three fields empty                       */{ "", "", "" } };
		

		return obj;
	}
		
	/*
	 * Test loan Form for invalid values.
	 * Invalid values are taken from data provider 'invalidValues'
	 */
	//@Test (dataProvider = "invalidValues", groups = { "@All", "@Acceptance" })
	public void VerifyWhenLoanFormContainsInvalidValues(String loanAmount, String rateOfInterest, String years) throws Exception {
		Utility.WriteToLog("info","Test case Description: " + "Verify When Loan Form Contains Invalid Values");
	    CoreLoanDetailsForm coreLoanDetails = new CoreLoanDetailsForm(driver);
	    coreLoanDetails.EnterDetailsAndDontSubmit(loanAmount, rateOfInterest, years);
	    coreLoanDetails.CheckSubmitButtonDisabled();
		
		Utility.WriteToLog("debug", "Completed verification when Loan Form Contains Invalid Values");
		
	}
	
	/*
	 * Test Loan Form Submit with large values of input data
	 * PS: This test case causing application to hang due to a defect
	 */
	//@Test ( groups = { "@All", "@Acceptance" })
	public void ValidateSubmitWithLargeValueOfTerm() throws Exception {
		Utility.WriteToLog("info","Test case Description: " + "Validate Loan Details for submit with large input data");
	    CoreLoanDetailsForm coreLoanDetails = new CoreLoanDetailsForm(driver);
	    CoreAmortizationSchedulePage amortizationSchedulePage = coreLoanDetails.EnterDetailsAndSubmit("1000", "10", "1111111111111111111");
	    amortizationSchedulePage.VerifyElementsInAmortizationSchedulePage();
		
		Utility.WriteToLog("debug", "Completed verification for submit with large input data");
		
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
