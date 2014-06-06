/**
 * 
 */
package com.tempo.uiobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.tempo.common.AssertionUtility;
import com.tempo.common.SeleniumObject;
import com.tempo.common.SeleniumUtility;
import com.tempo.common.Utility;


/**
 * @author Mohammad Arif
 *
 */
public class CoreLoanDetailsForm extends SeleniumObject {
	
	
	// Following elements are for security numbers page
	//@FindBy(how = How.XPATH, using = "//div[@class='header'][.='Security contacts ']")
	@FindBy(how = How.ID, using = "loanAmount")
	public WebElement Input_LoanAmount;
	@FindBy(how = How.ID, using = "loanAPR")
	public WebElement Input_LoanAPR;
	@FindBy(how = How.ID, using = "loanTerm")
	public WebElement Input_LoanTerm;
	//@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary']")
	//public WebElement Btn_Submit;
	@FindBy(how = How.CLASS_NAME, using = "btn-primary")
	public WebElement Btn_Submit;
	//@FindBy(how = How.CSS, using = "button.btn.btn-primary")
	//public WebElement Btn_Submit;
	@FindBy(how = How.XPATH, using = "//div[@class='well clearfix']/fieldset/legend")
	public WebElement StaticText_LoanDetailsForm;
	

	public CoreLoanDetailsForm(WebDriver driver){
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this); 
	}

	/**Method to check elements in LoanDetailsForm
	 * 
	 */
	public void VerifyElementsInLoanDetailsForm()
	{
		AssertionUtility.verifyElementPresent(Input_LoanAmount,"LoanAmout input field is not displayed in LoadDetailsForm page");
		AssertionUtility.verifyElementPresent(Input_LoanAPR,"LoanAPR input field is not displayed in LoadDetailsForm page");
		AssertionUtility.verifyElementPresent(Input_LoanTerm,"LoanTerm input field is not displayed in LoadDetailsForm page");
		AssertionUtility.verifyElementPresent(Btn_Submit,"Submit button is not displayed in LoadDetailsForm page");
		AssertionUtility.verifyStringMatch(StaticText_LoanDetailsForm.getText(), "Enter loan details", "Exact");
		AssertionUtility.verifyStringMatch(Input_LoanAmount.getAttribute("placeholder"), "Amount (Rupees)", "Exact");
		AssertionUtility.verifyStringMatch(Input_LoanAPR.getAttribute("placeholder"), "Rate of Interest (%)", "Exact");
		AssertionUtility.verifyStringMatch(Input_LoanTerm.getAttribute("placeholder"), "term (years)", "Exact");
		
	}
	
	/** Method to Enter Loan Details and Submit 
	* @param amount  Loan Amount
	* @param rateOfInterest Rate Of Interest
	* @param term Tenure of loan in years
	*/
	public CoreAmortizationSchedulePage EnterDetailsAndSubmit(String amount, String rateOfInterest, String term) throws Exception{
		Utility.WriteToLog("debug","Submitting loan details...");
		SeleniumUtility.waitUntilWEDisplayedNEnabled(Input_LoanAmount, 60);
		SeleniumUtility.waitUntilWEDisplayedNEnabled(Input_LoanAPR, 60);
		SeleniumUtility.waitUntilWEDisplayedNEnabled(Input_LoanTerm, 60);
		Input_LoanAmount.sendKeys(amount);
	 	
	 	Input_LoanAPR.sendKeys(rateOfInterest);
	 	Input_LoanTerm.sendKeys(term);
	 	Btn_Submit.submit();
	
	 	CoreAmortizationSchedulePage amortizationSchedulePage = new CoreAmortizationSchedulePage(driver);
	 	//SeleniumUtility.waitUntilWEDisplayedNEnabled(amortizationSchedulePage.StaticText_PrincipalAmount, 5);
	 	return amortizationSchedulePage;

	} // End of method - EnterDetailsAndSubmit
	
	/** Method to Enter Loan Details but donot Submit 
	* @param amount  Loan Amount
	* @param rateOfInterest Rate Of Interest
	* @param term Tenure of loan in years
	*/
	public void EnterDetailsAndDontSubmit(String amount, String rateOfInterest, String term) throws Exception{
		Utility.WriteToLog("debug","Entering loan details...");
		SeleniumUtility.waitUntilWEDisplayedNEnabled(Input_LoanAmount, 60);
		SeleniumUtility.waitUntilWEDisplayedNEnabled(Input_LoanAPR, 60);
		SeleniumUtility.waitUntilWEDisplayedNEnabled(Input_LoanTerm, 60);
		Input_LoanAmount.sendKeys(amount);
	 	
	 	Input_LoanAPR.sendKeys(rateOfInterest);
	 	Input_LoanTerm.sendKeys(term);

	} // End of method - EnterDetailsAndDontSubmit
	
	public void CheckSubmitButtonEnabled()
	{
		AssertionUtility.verifyElementEnabled(Btn_Submit, "Submit Button is Disabled");
	}
	
	public void CheckSubmitButtonDisabled()
	{
		AssertionUtility.verifyElementDisabled(Btn_Submit, "Submit Button is Enabled");
	}
	
}
