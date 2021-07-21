package com.todo.utils;


import java.util.Map;

import org.apache.log4j.Logger;

import com.relevantcodes.extentreports.LogStatus;
import com.todo.tests.BaseTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HttpOperation extends BaseTest {
	public static final Logger log = Logger.getLogger(HttpOperation.class);

	// ****************AUTHENTICATION CONTROLLER*********************
	public static Response createAuthToken() {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to to get access Token");
		extentTest.log(LogStatus.INFO, "Request Sent to to get access Token:- " + httpRequest);
		Response response = httpRequest.body(PayLoads.createAuthToken_Payload())
				.header("Content-Type", "application/json").when().post(BaseTest.prop.getProperty("Auth_EndPoint"))
				.then().log().all().extract().response();

		return response;
	}

	
//	****************************** USER CONTROLLER ************************8
	public static Response createUser(String authToken,Map<String, Object> jsonBody) {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to Create User");
		extentTest.log(LogStatus.INFO, "Request Sent to Create User :- " + httpRequest);
		Response response = httpRequest
				.body(jsonBody)
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + authToken).when()
				.post(BaseTest.prop.getProperty("CreateUser_EndPoint"))
				.then()
				.log().all().extract().response();

		return response;
	}
	
	public static Response loginUser(String authToken,Map<String, Object> jsonBody) {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to Login User");
		extentTest.log(LogStatus.INFO, "Request Sent to Login User :- " + httpRequest);
		Response response = httpRequest
				.body(jsonBody)
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + authToken).when()
				.post(BaseTest.prop.getProperty("LoginUser_EndPoint"))
				.then()
				.log().all().extract().response();

		return response;
	}

//	****************************** TASK CONTROLLER ************************8

	
	public static Response create_Task(String authToken, Map<String, Object> jsonBody) {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to create Task");
		extentTest.log(LogStatus.INFO, "Request Sent to create Task:- " + httpRequest);
		Response response = httpRequest
				.body(jsonBody)
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + authToken).when()
				.post(BaseTest.prop.getProperty("CreateTask_EndPoint"))
				.then()
				.log().all().extract().response();

		return response;
	}

	public static Response delete_Task(String authToken,Map<String, Object> jsonBody) {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to delete Task");
		extentTest.log(LogStatus.INFO, "Request Sent to delete Task:- " + httpRequest);
		Response response = httpRequest
				.body(jsonBody)
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + authToken).when()
				.post(BaseTest.prop.getProperty("DeleteTask_EndPoint"))
				.then()
				.log().all().extract().response();

		return response;
	}

	
	public static Response edit_Task(String authToken,Map<String, Object> jsonBody) {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to edit Task");
		extentTest.log(LogStatus.INFO, "Request Sent to edit Task:- " + httpRequest);
		Response response = httpRequest
				.body(jsonBody)
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + authToken).when()
				.post(BaseTest.prop.getProperty("EditTask_EndPoint"))
				.then()
				.log().all().extract().response();

		return response;
	}

}
