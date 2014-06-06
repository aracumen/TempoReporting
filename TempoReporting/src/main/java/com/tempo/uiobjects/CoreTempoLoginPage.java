package com.tempo.uiobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.tempo.common.SeleniumObject;
import com.tempo.common.SeleniumUtility;

public class CoreTempoLoginPage extends SeleniumObject{

	public CoreTempoLoginPage(WebDriver driver){
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60),
				this);
	}
	
	@FindBy(how = How.ID, using = "login-email")
	public WebElement login_email_field;
	@FindBy(how = How.ID, using = "login-password")
	public WebElement login_password_field;
	@FindBy(how = How.CSS, using = "button.standard-button")
	public WebElement submit_btn;
	
	public void verifyLoginInTempo(String username,String password)
	{
		loginTempo(username, password);
		
		
	}
	
	public void loginTempo(String username, String password)
	{
		login_email_field.sendKeys(username);
		login_password_field.sendKeys(password);
		submit_btn.click();
	}
}
