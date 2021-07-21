package com.todo.tests;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.todo.utils.Invalid_Payloads;
import com.todo.utils.PayLoads;
import com.todo.utils.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Authenticate_Token extends BaseTest {
	public static final Logger log = Logger.getLogger(Authenticate_Token.class);

	@Test
	public static void getAccessToken() {
		log.info("Valid Scenario:Get Access Token");
		// report generation start
		extentTest = extent.startTest("Valid Scenario:Get Access Token");

		// creating request
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to get token");
		extentTest.log(LogStatus.INFO, "Request Sent to get token:- " + httpRequest);

		// getting response
		Response response = httpRequest.body(PayLoads.createAuthToken_Payload())
				.header("Content-Type", "application/json").when().post(BaseTest.prop.getProperty("Auth_EndPoint"))
				.then().log().all().extract().response();
		
		log.info("Response received");
		extentTest.log(LogStatus.INFO, "Response received:- " + response.asString());

		// get the Token
		JsonPath jsonresp = ReusableMethods.rawToJson(response);
		authToken = jsonresp.get("jwt");
		log.info("Token created");
		extentTest.log(LogStatus.INFO, "Token created:-  " + authToken);

		// Assertion
		Assert.assertTrue(response.body().asString().contains(authToken));
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}

	@Test
	public static void invalid_getAccessToken() {
		log.info("Invalid Scenario : Get Access Token - When user enters the wrong password.");
		// report generation start
		extentTest = extent.startTest("Invalid Scenario : Get Access Token - When user enters the wrong password.");

		// creating request
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to get token");
		extentTest.log(LogStatus.INFO, "Request Sent to get token:- " + httpRequest);

		// getting response
		Response response = httpRequest.body(Invalid_Payloads.createAuthToken_Payload())
				.header("Content-Type", "application/json").when().post(BaseTest.prop.getProperty("Auth_EndPoint"))
				.then().log().all().extract().response();

		log.info("Response received");
		extentTest.log(LogStatus.INFO, "Response received:- " + response.asString());

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 403);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}
	
	@Test
	public static void invalid_getAccessToken1() {
		log.info("Invalid Scenario : Get Access Token - When user enters the invalid password and username.");
		// report generation start
		extentTest = extent.startTest("Invalid Scenario : Get Access Token - When user enters the invalid password and username.");

		// creating request
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to get token");
		extentTest.log(LogStatus.INFO, "Request Sent to get token:- " + httpRequest);

		// getting response
		Response response = httpRequest.body(Invalid_Payloads.createAuthToken_Payload1())
				.header("Content-Type", "application/json").when().post(BaseTest.prop.getProperty("Auth_EndPoint"))
				.then().log().all().extract().response();
		

		log.info("Response received");
		extentTest.log(LogStatus.INFO, "Response received:- " + response.asString());

		// Assertion

		Assert.assertEquals(response.getStatusCode(), 403);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}
	
	@Test
	public static void invalid_getAccessToken2() {
		log.info("Invalid Scenario : Get Access Token - When user enters the invalid username.");
		// report generation start
		extentTest = extent.startTest("Invalid Scenario : Get Access Token - When user enters the invalid username.");

		// creating request
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to get token");
		extentTest.log(LogStatus.INFO, "Request Sent to get token:- " + httpRequest);

		// getting response
		Response response = httpRequest.body(Invalid_Payloads.createAuthToken_Payload2())
				.header("Content-Type", "application/json").when().post(BaseTest.prop.getProperty("Auth_EndPoint"))
				.then().log().all().extract().response();
		 

		log.info("Response received");
		extentTest.log(LogStatus.INFO, "Response received:- " + response.asString());

		// Assertion

		Assert.assertEquals(response.getStatusCode(), 403);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}

}
