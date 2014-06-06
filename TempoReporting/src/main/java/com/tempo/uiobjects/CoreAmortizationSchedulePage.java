/**
 * 
 */
package com.tempo.uiobjects;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.tempo.apputility.AmortizationCalculator;
import com.tempo.common.AssertionUtility;
import com.tempo.common.SeleniumObject;

/**
 * Class to get the objects of Amortization Schedule Page and methods to access
 * and verify them
 * @author Mohammad Arif
 * 
 */
public class CoreAmortizationSchedulePage extends SeleniumObject {

	@FindBy(how = How.XPATH, using = "//p[1]")
	public WebElement StaticText_PrincipalAmount;
	@FindBy(how = How.XPATH, using = "//p[2]")
	public WebElement StaticText_RateOfInterest;
	@FindBy(how = How.XPATH, using = "//p[3]")
	public WebElement StaticText_Term;
	@FindBy(how = How.CSS, using = "table.table-bordered")
	public WebElement Table_Amortization;
	@FindBy(how = How.XPATH, using = "//table/thead/tr/th[1]")
	public WebElement StaticText_Column1;
	@FindBy(how = How.XPATH, using = "//table/thead/tr/th[2]")
	public WebElement StaticText_Column2;
	@FindBy(how = How.XPATH, using = "//table/thead/tr/th[3]")
	public WebElement StaticText_Column3;
	@FindBy(how = How.XPATH, using = "//table/thead/tr/th[4]")
	public WebElement StaticText_Column4;


	//Constructor for CoreAmortizationSchedulePage Class
	public CoreAmortizationSchedulePage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60),
				this);
	}

	/**
	 * Method to check elements in LoanDetailsForm
	 * 
	 */
	public void VerifyElementsInAmortizationSchedulePage() {
		AssertionUtility.verifyStringMatch(
				StaticText_PrincipalAmount.getText(), "Principle Amount:",
				"partial");
		AssertionUtility.verifyStringMatch(StaticText_RateOfInterest.getText(),
				"Rate of Interest:", "partial");
		AssertionUtility.verifyStringMatch(StaticText_Term.getText(), "Term :",
				"partial");
		AssertionUtility
				.verifyElementPresent(Table_Amortization,
						"Amortization table is not displayed in AmortizationSchedule page");
		VerifyColumnNamesInAmortizationTable();
	}

	public void VerifyRowAndColumnCountInAmortizationTable(int tenureInYears) {
		int rowCount = driver.findElements(By.xpath("//table/tbody/tr")).size();
		int ColCount = driver.findElements(By.xpath("//table/thead/tr/th"))
				.size();
		System.out.println("rowCount : " + rowCount);
		AssertionUtility.VerifyIntMatch(rowCount, (tenureInYears * 12));
		AssertionUtility.VerifyIntMatch(ColCount, 4);
	}

	public void VerifyColumnNamesInAmortizationTable() {
		AssertionUtility.verifyStringMatch(StaticText_Column1.getText(), "No",
				"exact");
		AssertionUtility.verifyStringMatch(StaticText_Column2.getText(), "EMI",
				"exact");
		AssertionUtility.verifyStringMatch(StaticText_Column3.getText(),
				"Monthly Interest", "exact");
		AssertionUtility.verifyStringMatch(StaticText_Column4.getText(),
				"Outstanding Principle", "exact");
	}

	public void VerifyValuesInAmortizationTable(int laonAmount,
			int rateOfInterest, int years) {
		Map<Integer, AmortizationCalculator.Row> actualMap = getTableValues();
		Map<Integer, AmortizationCalculator.Row> expectedMap = AmortizationCalculator
				.calculateAmortization(laonAmount, rateOfInterest, years);
		AssertionUtility.CompareTables(expectedMap, actualMap);
	}

	public static Map<Integer, AmortizationCalculator.Row> getTableValues() {
		
		Map<Integer, AmortizationCalculator.Row> actualMap = new TreeMap<Integer, AmortizationCalculator.Row>();
		
		ArrayList<WebElement> rows = new ArrayList<WebElement>(driver.findElements(By
				.xpath("//table/tbody/tr")));
		
		System.out.println("\t"  + "      " + "Monthly" + "\t" + "Outstanding");
		System.out.println("Month" + "\t" + "EMI" + "   " + "Interest" + "  " + "Principle");
		
		for (int i = 1; i < rows.size(); i++) {
			ArrayList<WebElement> cols = new ArrayList<WebElement>(driver.findElements(By
					.xpath("//table/tbody/tr" + "[" + i + "]/td")));
			
			int month = Integer.parseInt(cols.get(0).getText());
			int emi = (int) Double.parseDouble(cols.get(1).getText());
			int monthlyInterest = (int) Double.parseDouble(cols.get(2).getText());
			int outstandingPrincipal = (int) Double
					.parseDouble(cols.get(3).getText());
			
			AmortizationCalculator.Row row = new AmortizationCalculator.Row(
					month, emi, monthlyInterest, outstandingPrincipal);
			
			actualMap.put(month, row);
			
			for (AmortizationCalculator.Row row1 : actualMap.values()) {
				System.out.println(row1.getMonth() + "\t" + row1.getEMI()
						+ "\t" + row1.getMonthlyInterest() + "\t"
						+ row1.getOutstandingPrinciple());
			}
		}
		return actualMap;
	}

}
