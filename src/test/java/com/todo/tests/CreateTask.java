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

public class CreateTask extends BaseTest {
	public static String user_sheet = "User_Payload";
	public static String sheetName = "Task_Payload";
	public static String auth_sheetName = "Auth_Data";
	public static String auth_valid_testName = "Valid Scenario- valid Username & Password";

	public static final Logger log = Logger.getLogger(CreateTask.class);

//==========================     VALID - Create Task      =============================
	@Test
	public static void create_Task() {
		String testName = "Valid Scenario- When all the data are valid";
		log.info("Valid Scenario: Create Task");
		// report generation start
		extentTest = extent.startTest("Task Controller- Create Task");

		Response response = HttpOperation.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);

		// response for login the user
		response = HttpOperation.loginUser(authToken, PayLoads.create_user_Payload(user_sheet, testName));
		log.info("Response received for login");
		extentTest.log(LogStatus.INFO, "Response received for login:- " + response.asString());

		// get the User Token
		JsonPath jp = ReusableMethods.rawToJson(response);
		userToken = jp.get("jwtToken");
		log.info("Received User Token:- " + userToken);
		extentTest.log(LogStatus.INFO, "User Token:-  " + userToken);

		// Creating the Task response
		response = HttpOperation.create_Task(authToken, PayLoads.create_task_Payload(userToken, sheetName, testName));

		log.info("Response received to create the task");
		extentTest.log(LogStatus.INFO, "Response received to create the task:- " + response.asString());
		extentTest.log(LogStatus.INFO, "Response :- " + response.asString());

		// Assertion

		Assert.assertEquals(response.getStatusCode(), 201);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}

	//==========================     INVALID - Create Task      =============================
	// When User try to create task by entering invalid email
	@Test
	public static void invalid_create_Task_email() {
		String login_testName = "Valid Scenario- When all the data are valid";
		String task_testName = "Invalid Scenario- With invalid email";
		log.info("Invalid Scenario- With invalid email");
		// report generation start
		extentTest = extent.startTest("Invalid Scenario- With invalid email");

		Response response = HttpOperation.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);

		// response for login the user
		response = HttpOperation.loginUser(authToken, PayLoads.create_user_Payload(user_sheet, login_testName));
		log.info("Response received for login");
		extentTest.log(LogStatus.INFO, "Response received for login:- " + response.asString());

		// get the User Token
		JsonPath jp = ReusableMethods.rawToJson(response);
		userToken = jp.get("jwtToken");
		log.info("Received User Token:- " + userToken);
		extentTest.log(LogStatus.INFO, "User Token:-  " + userToken);

		// Creating the Task response
		response = HttpOperation.create_Task(authToken,
				PayLoads.create_task_Payload(userToken, sheetName, task_testName));

		log.info("Response received to create the task");
		extentTest.log(LogStatus.INFO, "Response received to create the task:- " + response.asString());
		extentTest.log(LogStatus.INFO, "Response :- " + response.asString());

		// Assertion

		Assert.assertEquals(response.getStatusCode(), 203);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}

	// When User try to create task by entering invalid task name
	@Test
	public static void invalid_create_Task_taskname() {
		String login_testName = "Valid Scenario- When all the data are valid";
		String task_testName = "Invalid Scenario- With Invalid Taskname";
		log.info("Invalid Scenario- Create Task With invalid task name");
		// report generation start
		extentTest = extent.startTest("Invalid Scenario- Create Task With invalid taskname");

		Response response = HttpOperation.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);

		// response for login the user
		response = HttpOperation.loginUser(authToken, PayLoads.create_user_Payload(user_sheet, login_testName));
		log.info("Response received for login");
		extentTest.log(LogStatus.INFO, "Response received for login:- " + response.asString());

		// get the User Token
		JsonPath jp = ReusableMethods.rawToJson(response);
		userToken = jp.get("jwtToken");
		log.info("Received User Token:- " + userToken);
		extentTest.log(LogStatus.INFO, "User Token:-  " + userToken);

		// Creating the Task response
		response = HttpOperation.create_Task(authToken,
				PayLoads.create_task_Payload(userToken, sheetName, task_testName));

		log.info("Response received to create the task");
		extentTest.log(LogStatus.INFO, "Response received to create the task:- " + response.asString());
		extentTest.log(LogStatus.INFO, "Response :- " + response.asString());

		// Assertion

		Assert.assertEquals(response.getStatusCode(), 406);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}

	// When User try to create task by entering invalid Priority
	@Test
	public static void invalid_create_Task_priority() {
		String login_testName = "Valid Scenario- When all the data are valid";
		String task_testName = "Invalid Scenario- With Invalid priority";
		log.info("Invalid Scenario- Create Task With invalid priority");
		// report generation start
		extentTest = extent.startTest("Invalid Scenario- Create Task With invalid priority (Priority must be between 1-10)");

		Response response = HttpOperation.createAuthToken(PayLoads.createAuthToken_Payload(extentTest, auth_sheetName, auth_valid_testName));
		String authToken = ReusableMethods.Auth(extentTest, response);

		// response for login the user
		response = HttpOperation.loginUser(authToken, PayLoads.create_user_Payload(user_sheet, login_testName));
		log.info("Response received for login");
		extentTest.log(LogStatus.INFO, "Response received for login:- " + response.asString());

		// get the User Token
		JsonPath jp = ReusableMethods.rawToJson(response);
		userToken = jp.get("jwtToken");
		log.info("Received User Token:- " + userToken);
		extentTest.log(LogStatus.INFO, "User Token:-  " + userToken);

		// Creating the Task response
		response = HttpOperation.create_Task(authToken,
				PayLoads.create_task_Payload(userToken, sheetName, task_testName));

		log.info("Response received to create the task");
		extentTest.log(LogStatus.INFO, "Response received to create the task:- " + response.asString());
		extentTest.log(LogStatus.INFO, "Response :- " + response.asString());

		// Assertion

		Assert.assertEquals(response.getStatusCode(), 406);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}
}
