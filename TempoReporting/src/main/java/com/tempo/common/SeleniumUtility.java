package com.tempo.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtility extends SeleniumObject {
	

	/** Wait for a web element to be displayed on the page
	 * @param wElement
	 * @param iTime
	 */
	public static void waitUntilWEDisplayed(WebElement wElement, int iTime){
			
		WebDriverWait wait = new WebDriverWait(driver, iTime);  
		wait.until(ExpectedConditions.visibilityOf(wElement));
		
	}
	
	/** Wait for a web element to be displayed on the page
	 * @param locator
	 * @param iTime
	 */
	public static void waitUntilWEDisplayed(By locator, int iTime){
			
		WebDriverWait wait = new WebDriverWait(driver, iTime);  
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		
	}
	
	/** Wait for a web element to be no more displayed on the page
	 * @param wElement
	 * @param iTime
	 */
	public static void waitUntilWENotDisplayed(WebElement wElement, int iTime){
		int timer = 1;
		while( wElement.isDisplayed() & timer != iTime){
			System.out.println(wElement + "is still displayed");
			Utility.pauseForTime(1000);
			timer++;
		}	
		System.out.println(wElement + "is no more displayed");
		
	}
	
	/** Wait for a web element to be no more displayed on the page
	 * @param locator
	 * @param iTime
	 */
	public static void waitUntilWENotDisplayed(By locator, int iTime){
		WebDriverWait wait = new WebDriverWait(driver, iTime);  
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		
	}
	
	
	/** Wait for the Page with given title to be displayed
	 * @param sTitle
	 * @param iTime
	 */
	public static void waitUntilPageTitleIs(String sTitle,int iTime){
		
		WebDriverWait wait = new WebDriverWait(driver, iTime);  
		wait.until(ExpectedConditions.titleIs(sTitle));
	}
	
	/** Wait for the Page with given title to be displayed
	 * @param sTitle
	 * @param iTime
	 */
	public static void waitUntilPageTitleContains(String sTitleSubString,int iTime){
		
		WebDriverWait wait = new WebDriverWait(driver, iTime);  
		wait.until(ExpectedConditions.titleContains(sTitleSubString));
	}
	
	
	/** Method to verify if an element is visible and enabled. This method is not exposed
	 * @param wElement
	 * @return WebElement
	 */
	private static WebElement elementIfVisibleNEnabled(WebElement wElement) {
	    return wElement.isDisplayed()& wElement.isEnabled() ? wElement : null;
	}
	
	
	/** Wait for a web element to be displayed and enabled on the page
	 * @param wElement
	 * @param iTime
	 */
	public static void waitUntilWEDisplayedNEnabled(final WebElement wElement, int iTime){
		WebDriverWait wait = new WebDriverWait(driver, iTime); 
		wait.until( 
				new ExpectedCondition<WebElement>() 
				{
			      	public WebElement apply(WebDriver driver) {
			          return elementIfVisibleNEnabled(wElement);
			        }
				}
		 );   
		
	}
	
	
	/** Wait for a web element to be displayed and enabled on the page
	 * @param locator
	 * @param iTime
	 */
	public static void waitUntilWEDisplayedNEnabled(By locator, int iTime){
		WebDriverWait wait = new WebDriverWait(driver, iTime); 
		wait.until(ExpectedConditions.elementToBeClickable(locator));   
		
	}
	
	/** Wait until given text is present in the specified element
	 * @param locator
	 * @param sText
	 * @param iTime
	 */
	public static void waitUntilTextToBePresentInElement(By locator, java.lang.String sText, int iTime){
		WebDriverWait wait = new WebDriverWait(driver, iTime); 
		wait.until(ExpectedConditions.textToBePresentInElement(locator, sText));   
		
	}	

}
