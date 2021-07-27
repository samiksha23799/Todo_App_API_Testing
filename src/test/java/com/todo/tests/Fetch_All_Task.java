package com.todo.tests;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.todo.utils.HttpOperation;
import com.todo.utils.PayLoads;
import com.todo.utils.ReusableMethods;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Fetch_All_Task extends BaseTest {

	public static String user_sheet = "User_Payload";

	public static String task_sheetName = "Task_Payload";

	public static String auth_sheetName = "Auth_Data";
	public static String auth_valid_testName = "Valid Scenario- valid Username & Password";

	public static final Logger log = Logger.getLogger(Fetch_All_Task.class);

//==========================     VALID TEST CASES - Fetch All Task of a User     =============================

	// Fetch all Task
	@Test
	public static void fetch_Task() {
		String task_testName = "Valid Scenario- When all the data are valid";
		String user_testName = "Valid Scenario- When all the data are valid";
		log.info("Valid Scenario: Fetch All User Task");
		// report generation start
		extentTest = extent.startTest("Valid Scenario: Fetch All User Task");

		Response response = HttpOperation
				.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);

		// response for login the user
		response = HttpOperation.loginUser(authToken, PayLoads.login(user_sheet, user_testName));
		log.info("Response received for login");
		extentTest.log(LogStatus.INFO, "Response received for login:- " + response);

		// get the User Token
		JsonPath jp = ReusableMethods.rawToJson(response);
		userToken = jp.get("jwtToken");
		log.info("Received User Token:-  " + userToken);
		extentTest.log(LogStatus.INFO, "User Token:-  " + userToken);

		// Response to edit the task
		response = HttpOperation.fetch_Task(authToken, PayLoads.fetchAllTask(userToken, task_sheetName, task_testName));
		log.info("Response received to get all the user task");
		extentTest.log(LogStatus.INFO, "Response received to get all the user task:- " + response.asString());

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}

	// ========================== INVALID TEST CASES - Fetch All Task of a User
	// =============================

	// When user get all the task using invalid email ID
	@Test
	public static void invalid_fetch_Task() {
		String task_testName = "Invalid Scenario- With invalid email";
		String user_testName = "Valid Scenario- When all the data are valid";
		log.info("Invalid Scenario: When user get all the task using invalid email ID");
		// report generation start
		extentTest = extent.startTest("Invalid Scenario: When user get all the task using invalid email ID");

		Response response = HttpOperation
				.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);

		// response for login the user
		response = HttpOperation.loginUser(authToken, PayLoads.login(user_sheet, user_testName));
		log.info("Response received for login");
		extentTest.log(LogStatus.INFO, "Response received for login:- " + response);

		// get the User Token
		JsonPath jp = ReusableMethods.rawToJson(response);
		userToken = jp.get("jwtToken");
		log.info("Received User Token:-  " + userToken);
		extentTest.log(LogStatus.INFO, "User Token:-  " + userToken);

		// Response to edit the task
		response = HttpOperation.fetch_Task(authToken, PayLoads.fetchAllTask(userToken, task_sheetName, task_testName));
		log.info("Response received to get all the user task");
		extentTest.log(LogStatus.INFO, "Response received to get all the user task:- " + response.asString());

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 203);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}

}
