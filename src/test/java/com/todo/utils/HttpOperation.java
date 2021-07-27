package com.todo.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.relevantcodes.extentreports.LogStatus;
import com.todo.tests.BaseTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HttpOperation extends BaseTest {
	public static final Logger log = Logger.getLogger(HttpOperation.class);

// ***************************AUTHENTICATION CONTROLLER*********************
	
	public static Response createAuthToken(Map<String, Object> jsonBody) {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to to get access Token");
		extentTest.log(LogStatus.INFO, "Request Sent to to get access Token:- " + httpRequest);
		Response response = httpRequest.body(jsonBody).header("Content-Type", "application/json").when()
				.post(BaseTest.prop.getProperty("Auth_EndPoint")).then().log().all().extract().response();

		return response;
	}

//	****************************** USER CONTROLLER ************************
	
	public static Response createUser(String authToken, Map<String, Object> jsonBody) {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to Create User");
		extentTest.log(LogStatus.INFO, "Request Sent to Create User :- " + httpRequest);
		Response response = httpRequest.body(jsonBody).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + authToken).when()
				.post(BaseTest.prop.getProperty("CreateUser_EndPoint")).then().log().all().extract().response();

		return response;
	}

	public static Response loginUser(String authToken, Map<String, Object> jsonBody) {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to Login User");
		extentTest.log(LogStatus.INFO, "Request Sent to Login User :- " + httpRequest);
		Response response = httpRequest.body(jsonBody).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + authToken).when()
				.post(BaseTest.prop.getProperty("LoginUser_EndPoint")).then().log().all().extract().response();

		return response;
	}

//	****************************** TASK CONTROLLER ************************

	public static Response create_Task(String authToken, Map<String, Object> jsonBody) {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to create Task");
		extentTest.log(LogStatus.INFO, "Request Sent to create Task:- " + httpRequest);
		Response response = httpRequest.body(jsonBody).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + authToken).when()
				.post(BaseTest.prop.getProperty("CreateTask_EndPoint")).then().log().all().extract().response();

		return response;
	}

	public static Response delete_Task(int taskID, String authToken, String userToken, String sheetName,
			String testName) {
		// Fetching Data from Excel File
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = CommonUtils.reader.getRowTestData(sheetName, testName);
		String executionRequired = testData.get("Execution Required").toLowerCase();
		String Email = testData.get("Email");
		CommonUtils.toCheckExecutionRequired(executionRequired);
		
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to delete Task");
		extentTest.log(LogStatus.INFO, "Request Sent to delete Task:- " + httpRequest);
		
		Response response =httpRequest.queryParam("token", userToken).queryParam("email", Email)
				.header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + authToken).when()
				.delete(BaseTest.prop.getProperty("DeleteTask_EndPoint") + taskid).then().log().all().extract()
				.response();

		return response;
	}

	public static Response edit_Task(String authToken, Map<String, Object> jsonBody) {
		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to edit Task");
		extentTest.log(LogStatus.INFO, "Request Sent to edit Task:- " + httpRequest);
		Response response = httpRequest.body(jsonBody).header("Content-Type", "application/json")
				.header("Authorization", "Bearer " + authToken).when()
				.post(BaseTest.prop.getProperty("EditTask_EndPoint")).then().log().all().extract().response();

		return response;
	}

	public static Response fetch_Task(String authToken, String userToken, String sheetName, String testName) {
		// Fetching Data from Excel File
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = CommonUtils.reader.getRowTestData(sheetName, testName);
		String executionRequired = testData.get("Execution Required").toLowerCase();
		String Email = testData.get("Email");
		CommonUtils.toCheckExecutionRequired(executionRequired);

		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to fetch all user Task");
		extentTest.log(LogStatus.INFO, "Request Sent to fetch all user Task:- " + httpRequest);

		Response response = httpRequest.queryParam("token", userToken).queryParam("email", Email)
				.header("Content-Type", "application/json").header("Authorization", "Bearer " + authToken).when()
				.get(BaseTest.prop.getProperty("Fetch_All_Task")).then().log().all().extract().response();

		return response;
	}

	public static Response fetch_Task_by_ID(int taskID, String authToken, String userToken, String sheetName,
			String testName) {
		// Fetching Data from Excel File
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = CommonUtils.reader.getRowTestData(sheetName, testName);
		String executionRequired = testData.get("Execution Required").toLowerCase();
		String Email = testData.get("Email");
		CommonUtils.toCheckExecutionRequired(executionRequired);

		// getting response
		RequestSpecification httpRequest = RestAssured.given();
		log.info("Request Sent to fetch all user Task");
		extentTest.log(LogStatus.INFO, "Request Sent to fetch all user Task:- " + httpRequest);

		Response response = httpRequest.queryParam("token", userToken).queryParam("email", Email)
				.header("Content-Type", "application/json").header("Authorization", "Bearer " + authToken).when()
				.get(BaseTest.prop.getProperty("Fetch_Task_By_ID") + taskID).then().log().all().extract().response();

		return response;
	}

}
