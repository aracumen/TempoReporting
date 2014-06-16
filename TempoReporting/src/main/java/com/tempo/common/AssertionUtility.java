package com.tempo.common;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class AssertionUtility extends SeleniumObject {
	
	
	/** Method to verify if a given text is present on the page
	 * @param sText
	 * @param sfailureMessage
	 */
	public static void verifyTextPresent(String sText, String sfailureMessage){
		
		assertTrue(driver.getPageSource().contains(sText),sfailureMessage);
	}
	
	/** Method to verify if a given text is present on the page
	 * @param sText
	 */
	public static void verifyTextPresent(String sText){
		assertTrue(driver.getPageSource().contains(sText));
	}
	
	/** Method to verify if a given web element is present on the page
	 * @param wElement
	 * @param sfailureMessage
	 */
	public static void verifyElementPresent(WebElement wElement, String sfailureMessage){
		assertTrue(wElement.isDisplayed(), sfailureMessage);
	}
	
	/** Method to verify if a given web element is present on the page
	 * @param wElement
	 */
	public static void verifyElementPresent(WebElement wElement){
		assertTrue(wElement.isDisplayed());
	}
	

	/** Method to verify if a given web element is not present on the page
	 * @param wElement
	 * @param sfailureMessage
	 */
	public static void verifyElementNotPresent(WebElement wElement, String sfailureMessage){
		if(!Utility.checkElementPresent(wElement)){
			assertFalse(false, sfailureMessage);
		}
		
	}
	
	/** Method to verify if a given web element is not present on the page
	 * @param wElement
	 */
	public static void verifyElementNotPresent(WebElement wElement){
		if(!Utility.checkElementPresent(wElement)){
			assertFalse(false);
		}
	}
	
	/** Method to verify if a given web element is not present on the page
	 * @param Locators
	 * @param attribute
	 * @param sfailureMessage
	 */
	public static void verifyElementNotPresentByLocators(String Locators ,String attribute,  String sfailureMessage ){
		ArrayList<WebElement>  wElements= new ArrayList<WebElement>() ;
		Boolean webElementPresent= null;
		try{
			if(attribute.equals("xpath")){
				wElements = (ArrayList<WebElement>) driver.findElements(By.xpath(Locators));
				webElementPresent = true;
			}

			else if(attribute.equals("id")){
				wElements= (ArrayList<WebElement>) driver.findElements(By.id(Locators));
				webElementPresent = true;
			}

			else if(attribute.equals("class")){
				wElements= (ArrayList<WebElement>) driver.findElements(By.className(Locators));
				webElementPresent = true;
			}
			 		
		}
		
		catch (NoSuchElementException ex) { 
			if(wElements.isEmpty()){
				webElementPresent = false;
			}
		}
		if(wElements.isEmpty()){
			webElementPresent = false;
		}
		System.out.println(wElements);
		System.out.println(webElementPresent);
		assertFalse(webElementPresent, sfailureMessage);
	
	}
	
	
	/** checks whether the element is enabled or not
	 * @param wElement
	 * @param sfailureMessage
	 */
	public static void verifyElementEnabled(WebElement wElement, String sfailureMessage){
		boolean state= wElement.isEnabled();
		assertTrue(state==true,sfailureMessage );	
	}
	
	
	/** 
	 * checks whether the element is disabled or not
	 * @param wElement
	 * @param sfailureMessage
	 */
	public static void verifyElementDisabled(WebElement wElement, String sfailureMessage){
		boolean state= wElement.isEnabled();
		assertTrue(state==false,sfailureMessage );	
	}
	
	/**
     * checks whether the element is selected or note
     * @param Webelement for which selection will be verified
     * @param Sring Failure message
     */
     public static void verifyElementSelected(WebElement wElement, String sfailureMessage){
            boolean state= wElement.isSelected();
            assertTrue(state,sfailureMessage );      
     }
     
     
     /**
     * checks whether the element is not selected
     * @param Webelement for which selection will be verified
     * @param Sring Failure message
     */
     public static void verifyElementNotSelected(WebElement wElement, String sfailureMessage){
            boolean state= wElement.isSelected();
            assertTrue(state==false,sfailureMessage );      
     }
     
     /**
      * checks if the strings match
      * @param str1 first string
      * @param str2 second string
      * @param Type of match (exact/partial/mask/nocase/nomatch). default value is nocase.
      * 
      */
     public static void verifyStringMatch(String str1, String str2, String typeOfMatch){
 		boolean compRes = false;
 		String FailStmt ="Strings " + str1 + " and " + str2 + " did not match" ;
 		if (typeOfMatch.equalsIgnoreCase("exact")){
 			if (str1.compareTo(str2) == 0) {
 				compRes = true;
 			}
 		} else if (typeOfMatch.equalsIgnoreCase("partial")) {	
 			FailStmt ="Strings " + str1 + " does not contain " + str2 ;
 			if (str1.contains(str2)) {
 				compRes = true;
 			}	
 		} else if(typeOfMatch.equalsIgnoreCase("nomatch")) {
 			FailStmt ="Strings " + str1 + " and " + str2 + "are exact match" ;
 			if (str1.compareTo(str2) != 0) {
 				compRes = true;
 			}
 		} else if(str1.equalsIgnoreCase(str2)) {		
 				compRes = true;
 		}
 		
 		
 		if (compRes == false) {
 			Utility.WriteToLog("debug", FailStmt);
 		}
 		assertTrue(compRes, FailStmt );	
 	}	
     
    public static void VerifyIntMatch(int integer1, int integer2)
    {
    	boolean compRes = false;
    	String FailStmt ="Integer " + integer1 + " and " + integer2 + " did not match" ;
    	if(integer1==integer2)
    	{
    		compRes = true;
    	}
    	assertTrue(compRes, FailStmt);
    }
    
          
}
