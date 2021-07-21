package com.todo.utils;

import java.util.HashMap;
import java.util.Map;

import com.todo.tests.BaseTest;

public class Invalid_Payloads extends BaseTest {

	public static String sheetName = "Auth_Data";
	public static String sheetName1 = "User&Task_Payload";

//=======================AUTHENTICATION CONTROLLER==========================

	// Access Token By entering invalid password
	public static Map<String, Object> createAuthToken_Payload() {
		String testName = "Invalid Scenario- Valid Username &  invalid Password";

		// Fetching Data from Excel File
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = CommonUtils.reader.getRowTestData(sheetName, testName);
		String executionRequired = testData.get("Execution Required").toLowerCase();
		String username = testData.get("Username");
		String pwd = testData.get("Password");
		CommonUtils.toCheckExecutionRequired(executionRequired);
		log.info("Username:-  " + username);
		log.info("Password:- " + pwd);

		Map<String, Object> jsonBody = new HashMap<String, Object>();
		jsonBody.put("username", username);
		jsonBody.put("password", pwd);
		return jsonBody;
	}

	// Access Token By entering invalid password & invalid username
	public static Map<String, Object> createAuthToken_Payload1() {
		String testName = "Invalid Scenario- invalid Username & invalid Password";

		// Fetching Data from Excel File
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = CommonUtils.reader.getRowTestData(sheetName, testName);
		String executionRequired = testData.get("Execution Required").toLowerCase();
		String username = testData.get("Username");
		String pwd = testData.get("Password");
		CommonUtils.toCheckExecutionRequired(executionRequired);
		log.info("Username:-  " + username);
		log.info("Password:- " + pwd);

		Map<String, Object> jsonBody = new HashMap<String, Object>();
		jsonBody.put("username", username);
		jsonBody.put("password", pwd);

		return jsonBody;
	}

	// Access Token By entering invalid User name
	public static Map<String, Object> createAuthToken_Payload2() {
		String testName = "Invalid Scenario- invalid Username & Valid Password";

		// Fetching Data from Excel File
		HashMap<String, String> testData = new HashMap<String, String>();
		testData = CommonUtils.reader.getRowTestData(sheetName, testName);
		String executionRequired = testData.get("Execution Required").toLowerCase();
		String username = testData.get("Username");
		String pwd = testData.get("Password");
		CommonUtils.toCheckExecutionRequired(executionRequired);
		log.info("Username:-  " + username);
		log.info("Password:- " + pwd);

		Map<String, Object> jsonBody = new HashMap<String, Object>();
		jsonBody.put("username", username);
		jsonBody.put("password", pwd);

		return jsonBody;
	}


}
