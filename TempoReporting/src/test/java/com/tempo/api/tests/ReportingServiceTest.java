package com.tempo.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static com.thoughtworks.selenium.webdriven.commands.GetElementHeight.*;

import org.hamcrest.Matchers.*;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class ReportingServiceTest {
  @Test
  public void f() {
	  
	  Response res = get("http://jsonplaceholder.typicode.com/posts");
	  System.out.println(res.asString());
	  JsonPath json = new JsonPath(res.asString());
	 
	  System.out.println(json.get("userId").toString());
	  Assert.assertTrue(true);
  }
}
