
/** Class file for Instantiating the Selenium object and doing the setup activity
 *
 */

package com.tempo.common;

import com.tempo.common.SeleniumVariables;
import com.tempo.common.Utility;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@SuppressWarnings("unused")
public class SeleniumObject {
	
	public static WebDriver driver = null;
	public static Logger logger = Logger.getLogger(SeleniumObject.class);    	
	
	
	/** Method to Open the browser and display the Application login page.
	 * @param sURL
	 * @return WebDriver
	 * @throws Exception
	 */
	public static WebDriver setUp (String sURL) throws Exception
	{
		SeleniumVariables.setUp ();
		
		String sBrowser = SeleniumVariables.getBrowser(); 
		
		if (sBrowser.equalsIgnoreCase("ie")){
	    	driver = setUpIEDriver(sURL);
	    	Utility.maximise();
	    }else if (sBrowser.equalsIgnoreCase("firefox")){
	    	driver = setUpFirefoxDriver(sURL);
	    	Utility.maximise();
	    }else if (sBrowser.equalsIgnoreCase("chrome")){
	    	driver = setUpChromeDriver(sURL);
	    }
	    
	    return driver;
	} // End of method - setUp

	
	
	/** Method to set up the Firefox Driver
	 * @param sURL
	 * @return WebDriver
	 */
	private static WebDriver setUpFirefoxDriver(String sURL){
		
		FirefoxProfile firefoxprofile = new FirefoxProfile();
		if (SeleniumVariables.getTestEnvironment().equalsIgnoreCase("qa")){
			firefoxprofile.setAssumeUntrustedCertificateIssuer(false);
		}else{
			firefoxprofile.setAssumeUntrustedCertificateIssuer(true);
		}
		
		driver = new FirefoxDriver(firefoxprofile);
		driver.get(sURL);
		return driver;
	}
	
	
	/** Method to set up the Chrome Driver
	 * @param sURL
	 * @return WebDriver
	 */
	private static WebDriver setUpChromeDriver(String sURL) throws Exception{
		
		String osName = System.getProperty("os.name");
        String chromeDriverLocation = System.getProperty("user.dir")+ "/src/main/resources/driver/chromedriverwindows/chromedriver.exe";
		if (osName.equalsIgnoreCase("windows") || osName.equalsIgnoreCase("windows 7") || osName.equalsIgnoreCase("windows xp")){
			chromeDriverLocation = System.getProperty("user.dir")+ "/src/main/resources/driver/chromedriverwindows/chromedriver.exe";

		}else if (osName.equalsIgnoreCase("Mac OS X")){
			chromeDriverLocation = System.getProperty("user.dir")+ "/src/main/resources/driver/chromedrivermac/chromedriver";
		}

		System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
		ChromeOptions options = new ChromeOptions();
//		options.addArguments(Arrays.asList("--ignore-certificate-errors"));
		options.addArguments(Arrays.asList("--start-maximized"));
		driver = new ChromeDriver(options);
		driver.get(sURL);
		return driver;
	}
	
	
	/** Method to set up the Internet Explorer Driver
	 * @param sURL
	 * @return WebDriver
	 */
	private static WebDriver setUpIEDriver(String sURL){
		
		String arch = System.getenv("PROCESSOR_ARCHITECTURE");
		if (arch.endsWith("64")){
			System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, System.getProperty("user.dir") + "/src/main/resources/driver/64Bit/IEDriverServer.exe");
		} else {
			System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, System.getProperty("user.dir") + "/src/main/resources/driver/32Bit/IEDriverServer.exe");
		}
        
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability("ignoreZoomSetting", true);
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		//File file = new File(System.getProperty("user.dir") + "/src/main/resources/driver/32Bit/IEDriverServer.exe");
		//System.out.println("path set:" + file.getAbsolutePath());
	    //System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		driver = new InternetExplorerDriver(capabilities);
		Utility.pauseForTime(5000);
		return driver;
	}
	
	/** Method to return the WebDriver Instance.
	* @return WebDriver Object
	*/
	public static WebDriver getWebDriverlInstance () {
		return driver;
	}
	
	/** Method to close the browser and all associated window
	*/
	public static void CloseBrowser () {
		driver.quit();
	}

}
