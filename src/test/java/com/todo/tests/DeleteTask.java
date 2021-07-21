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

public class DeleteTask extends BaseTest {

	public static String user_sheet = "User_Payload";

	public static String sheetName = "Task_Payload";

	public static final Logger log = Logger.getLogger(DeleteTask.class);

	// ========================== VALID TEST CASES - Delete Task
	// =============================
	@Test
	public static void delete_Task() {
		String testName = "Valid Scenario- When all the data are valid";
		log.info("Valid Scenario: Delete Task");
		// report generation start
		extentTest = extent.startTest("Valid Scenario: Delete Task");

		Response response = HttpOperation.createAuthToken();
		String authToken = ReusableMethods.Auth(extentTest, response);

		// response for login the user
		response = HttpOperation.loginUser(authToken, PayLoads.create_user_Payload(user_sheet, testName));
		log.info("Response received for login");
		extentTest.log(LogStatus.INFO, "Response received for login:- " + response.asPrettyString());

		// get the User Token
		JsonPath jp = ReusableMethods.rawToJson(response);
		userToken = jp.get("jwtToken");
		log.info("Received User Token" + userToken);
		extentTest.log(LogStatus.INFO, "User Token:-  " + userToken);

		// get the User Token
		JsonPath jp2 = ReusableMethods.rawToJson(response);
		taskid = jp2.get("task.id");
		log.info("Received User Token" + taskid);
		extentTest.log(LogStatus.INFO, "User Token:-  " + taskid);

		// Response to delete the task
		response = HttpOperation.delete_Task(authToken, PayLoads.deletetask_Payload(userToken, taskid,sheetName, testName));
		log.info("Response received for delete the task" + response.asString());
		extentTest.log(LogStatus.INFO, "Response received to delete the task:- " + response.asString());

		// Assertion
		Assert.assertEquals(response.getStatusCode(), 200);
		log.info("Assertion Passed!!");
		extentTest.log(LogStatus.INFO, "HTTP Status Code:- " + response.getStatusCode());

	}

}
