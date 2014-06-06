/** This Class reads all the test configuration properties used in the test
*/

package com.tempo.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;


public class SeleniumVariables {
	
	private static final String PROP_FILE="testConfig.properties";
	static Properties properties = new Properties(); 
	static String sSeleniumBrowser = null;
	static String sApplicationURL = null;
	static String sTestEvn = null;
	static String sPurgeDuration = null;
	static String sPurgeResult = null;
	
	
	static{
		
		Map<Object, Object> map = System.getProperties();
		sSeleniumBrowser = (String) map.get("selenium.browser");
		sTestEvn = (String) map.get("test.env");
		sApplicationURL=(String) map.get("application.url");
		sPurgeResult = (String)map.get("purge.testresults");
		sPurgeDuration = (String)map.get("purge.duration");
	}
    
	
	/** Method to read the test configuration properties file properties
	*  and load the variables
	*/
	public static void setUp(){  
        try{  
        	FileInputStream in = new FileInputStream(PROP_FILE);
        	properties.load(in);
        	in.close();
        }catch(IOException e){  
          System.err.println("Failed to read from " + PROP_FILE + " file.");  
        }  
    } 
    
	/** Method to return the browser on which the test needs to be run on
	* @return String - browser on which the test needs to be run on
	*/
	public static String getBrowser () {
		
		if (sSeleniumBrowser == null){
			return String.valueOf(properties.getProperty("selenium.browser")).trim();
		}else{
			return sSeleniumBrowser;
		}
	}

	/** Method to return the URL of the application
	* @return String - The URL of the application
	*/
	public static String getApplicationURL () {
				
		String sApplication_URL =null;
		
		if (sApplicationURL==null) {
			sApplication_URL = String.valueOf(properties.getProperty("application.url")).trim();
		} else {
			sApplication_URL=sApplicationURL;
		}
		
		return sApplication_URL;
	}
	
	/**
	 * Reads property file and returns the environment in which scripts have to be executed
	 * @return string environment name
	 */
	public static String getTestEnvironment(){
		
		if (sTestEvn == null){
			return String.valueOf(properties.getProperty("test.env")).trim();
		}else{
			return sTestEvn;
		}
	}
	
	/** Method to return the name of the application
	* @return String - The name of the application
	*/
	public static String getApplicationName () {
		return String.valueOf(properties.getProperty("application.name")).trim();
	}

    
    /**
     * Read Purge duration from property file
     * @return return duration to purge or -1 for no purge
     */
    public static int getPurgeDuration() {
    	String purgeEnabled;
    	String purgeDuration;
    	if (sPurgeResult == null) {
    		purgeEnabled = properties.getProperty("purge.testresults");
    	} else {
    		purgeEnabled = sPurgeResult;
    	}
    	if (sPurgeDuration == null) {
    		purgeDuration = properties.getProperty("purge.duration");
    	} else {
    		purgeDuration = sPurgeDuration;
    	} 
    	 
    	if (purgeEnabled.equalsIgnoreCase("yes")){
    		try {
    			int duration = Integer.valueOf(purgeDuration);
    			if (duration >= 0) {
    				return duration;
    			} else {
    				Utility.WriteToLog("info", "Purge duration value is not valid");
    				return -1;
    			}
    		} catch(Exception e) {
    			Utility.WriteToLog("info", "Purge duration value is not valid");
    			return -1;
    		}
    	} else {
    		Utility.WriteToLog("info", "Purging is off");
    		return -1;
    	}
	}
   
    
 
    /**
     * Returns the config tag value from properties file
     * @return
     */
    
    public static String getTagValue(String tagName)
    {
 	   return String.valueOf(properties.getProperty(tagName)).trim();
    }

   

}
