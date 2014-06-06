package com.tempo.uiobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.tempo.common.AssertionUtility;
import com.tempo.common.SeleniumObject;

/**
 * Class to get the objects of Amortization Schedule Page and methods to access
 * and verify them
 * @author Mohammad Arif
 * 
 */
public class CoreTempoDashboardPage extends SeleniumObject{
	
	@FindBy(how = How.CSS, using = "i.icon.icon-rightster")
	public WebElement rightsterIconLink;
	
	//Constructor for CoreAmortizationSchedulePage Class
		public CoreTempoDashboardPage(WebDriver driver) {
			PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60),
					this);
		}

		/**
		 * Method to check elements in LoanDetailsForm
		 * 
		 */
		public void VerifyElementsInTempoDashboardPage() {
			System.out.println(rightsterIconLink.getText());
			AssertionUtility.verifyStringMatch(
					rightsterIconLink.getText(), "rightster","partial");
			
		}
	
}
