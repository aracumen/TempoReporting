package com.tempo.api.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.apache.log4j.Logger;
import org.hamcrest.Matcher;
import org.hamcrest.core.StringContains;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*; 

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.LogConfig;
import com.jayway.restassured.filter.log.LogDetail;
import com.jayway.restassured.matcher.ResponseAwareMatcher;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.tempo.common.Utility;
import com.thoughtworks.selenium.webdriven.commands.IsTextPresent;

public class ReportingServiceTest {
	
  @BeforeClass
  public void beforeClass() throws FileNotFoundException {
	 // PrintStream defaultPrintStream = new PrintStream(new File("logs/report.log"));
	  //RestAssured.config = config().logConfig(new LogConfig(defaultPrintStream,true).enableLoggingOfRequestAndResponseIfValidationFails());
	  RestAssured.config = config().logConfig(new LogConfig().enableLoggingOfRequestAndResponseIfValidationFails().enablePrettyPrinting(true));
	  RestAssured.baseURI = "http://apirevenueqa.rightster.com/";
      RestAssured.basePath = "/xml/external/revenuemgr";
      
  }
  
  
  //@Test
  public void f() {
	  
	  Response res = get("http://jsonplaceholder.typicode.com/posts");
	  //System.out.println(res.asString());
	  JsonPath json = new JsonPath(res.asString());
	 
	  //System.out.println(json.get("userId").toString());
	  Assert.assertTrue(true);
  }
  
  //@Test
  public void test() throws FileNotFoundException {
	 	 
	  Utility.WriteToLog("Debug", "Starting test downnoad with valid pub");
	 // RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	 // lg.enableLoggingOfRequestAndResponseIfValidationFails();
	  get("http://jsonplaceholder.typicode.com/posts").then().body("userId", hasItems('a',1,2,3));
	  //get("http://jsonplaceholder.typicode.com/posts").then().log().all();
	  Utility.WriteToLog("Debug", "test Finished");
  }
  
   
  @Test
  public void testDownloadStatements() {
	  
	  
	  given().
	  		param("statementId", 39).
	  		param("publisherId", 2156).
	  when().
	  		post("http://apirevenueqa.rightster.com/xml/external/revenuemgr/downloadStatements").
	  then().
	  		statusCode(200).
	  and().
	  		body(hasXPath("/response/result_set/url")).
	  and().
	  		body(containsString("http://videos.rightster.com/secure/reports/"));
	  
  }
  
  @Test
  public void testDownloadStatements2() {
	  
	 	  
	  
  }
}
