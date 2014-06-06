/** This Class common utility functions that can be used across different test
*/

package com.tempo.common;

import java.awt.Toolkit;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


public class Utility extends SeleniumObject {
	
	public static JPopupMenu Pmenu;

	
	/** Method to delete the contents of a directory 
	 * @param - File directory path
	*/
	public static boolean deleteDirectory(File path) {
		if( path.exists() ) {
		// include a test for root directory
		  File[] files = path.listFiles();
		  for(int i=0; i<files.length; i++) {
			 if(files[i].isDirectory()) {
			   deleteDirectory(files[i]);
			 }
			 else {
			   files[i].delete();
			 }
		  }
		}
		return( path.delete() );
	  }
	
	
	/** Method to get the Current Date in yyyy-MMMM-dd format
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentDate() throws Exception {
		DateFormat dfDate = new SimpleDateFormat("yyyy-MMMM-dd");
		Date today = Calendar.getInstance().getTime();
		String currentDate = dfDate.format(today);
		return currentDate;
	}
	

	
	/**Method to pause for specified number of seconds
	* @param iTime - Time in milliseconds to pause
	* @return
	*/
	public static void pauseForTime(long iTime){
		try{
			Thread.sleep(iTime);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
		
	/** Method to check if an web element is Present on the page
	 * @param wElement
	 * @return boolean
	 */
	public static boolean checkElementPresent(WebElement wElement){
		boolean available = false;
		try {
			if (wElement.isDisplayed()){
				available = true;
			}
		} catch (NoSuchElementException e) {
			available = false;
		}
		return available;
	}
	
	
	/** Method to maximize a web page. It works only for IE and Firefox browser
	 * 
	 */
	public static void maximise() { 

        Toolkit toolkit = Toolkit.getDefaultToolkit(); 
        Dimension screenResolution = new Dimension((int) 
        toolkit.getScreenSize().getWidth(), (int) 
        toolkit.getScreenSize().getHeight()); 
        driver.manage().window().setSize(screenResolution); 
    }
	
	
	/**
	 * Sends user data to log file 
	 * @param log level (String) (debug/warn/info)
	 * @param log message (String)
	 */
	public static void WriteToLog(String logLevel, String msg){
		if (logLevel.equalsIgnoreCase("debug")) {
			logger.debug(msg);
		} else if (logLevel.equalsIgnoreCase("warn")) {
			logger.warn(msg);
		} else if (logLevel.equalsIgnoreCase("info")) {
			logger.info(msg);
		} else {
			logger.info("Logging was used improperly. Allowed values are debug/warn/info");
		}
		
	}
	
	/**
	 * Calculate duration in HH:MM:SS for mat from milliseconds 
	 * @param start millisecond
	 * @param end millisecond
	 */
	public static String CalculateDuration(long startTime, long endTime) {
		long diff = endTime - startTime;
		long diffInSec= diff/1000;
		long ExecHr=0;
		long ExecMin=0;
		long ExecSec=0;
		ExecSec=diffInSec%60;
		if (diffInSec > 60) {
			ExecMin=diffInSec/60;
			if (ExecMin>60) {
				ExecHr= ExecMin/60;
				ExecMin= ExecMin%60;
				
			}
		}
		return ExecHr+":"+ExecMin+":"+ExecSec + "(in Hour:Min:Sec)";
	}
	
	/**
	 * To delete logs and previous execution results older than specified period
	 * @param daysBack
	 * @param dirWay
	 */
	public static void purgeReportsAndLogsOlderThanNdays(int daysBack, String dirWay) {  
		if (daysBack != -1) {  
        File directory = new File(dirWay);  
        	if(directory.exists()){  
        		File[] listFiles = directory.listFiles();  
        		long duration = daysBack * 24 * 60 * 60;
        		duration = duration * 1000;
        		long purgeTime = System.currentTimeMillis() - (duration); 
        
        		for(File listFile : listFiles) {  		
        			if(listFile.lastModified() < purgeTime) {  
           			 	if(listFile.isDirectory()) {
           			 		WriteToLog("info", "Deleting directory: "+listFile);
           			 		deleteDirectory(listFile);
           			 	} else {
           			 		WriteToLog("info", "Deleting file: "+listFile);
           			 		listFile.delete();
           			 	}
        			}  
        		}  
        	} else {  
        		WriteToLog("info","Files were not deleted, directory " + dirWay + " does'nt exist!");  
        	}  
		}  
    }  
	
	/**
	 * This method is used to show the intermediate results during execution
	 * @param testmethodStr TODO
	 * @param resultStr TODO
	 * @throws InterruptedException
	 */
	public static void showPopup(String testmethodStr, String resultStr) throws InterruptedException {
		JMenuItem testnameItem, resultItem;
		

		Pmenu.setLocation(400, 0);
		Pmenu.setPopupSize(500, 50);
		
		  testnameItem = new JMenuItem(testmethodStr);
		  resultItem = new JMenuItem(resultStr);
		 
		  if (Pmenu.isVisible()) {  		
		  		Pmenu.setEnabled(false);
		  }
		  	Pmenu.add(testnameItem);
		  	Pmenu.add(resultItem);
		  	
		  	Pmenu.setEnabled(true);
			Pmenu.setVisible(true);
			Thread.sleep(2000);
	}
	
	/**
	 * Creates the popup to display runtime result
	 */
	public static void initializePopup() {
		Pmenu = new JPopupMenu();
	//	Pmenu.setBorderPainted(true);
	}
	
	/**
	 * Hides the pop-up at end of test execution 
	 */
	
	public static void hidePopup(){
		Pmenu.setVisible(false);
		Pmenu.removeAll();
	}

}
