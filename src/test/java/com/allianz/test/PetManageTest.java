package com.allianz.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class PetManageTest {
	
	public static String baseUrl="https://petstore.swagger.io/v2";
	
	@Test
	public void addValidPetToStore() {
	
	String jsonBody = "{\r\n"
			+ "  \"id\": 331,\r\n"
			+ "  \"category\": {\r\n"
			+ "    \"id\": 0,\r\n"
			+ "    \"name\": \"string\"\r\n"
			+ "  },\r\n"
			+ "  \"name\": \"doggie123\",\r\n"
			+ "  \"photoUrls\": [\r\n"
			+ "    \"string\"\r\n"
			+ "  ],\r\n"
			+ "  \"tags\": [\r\n"
			+ "    {\r\n"
			+ "      \"id\": 0,\r\n"
			+ "      \"name\": \"string\"\r\n"
			+ "    }\r\n"
			+ "  ],\r\n"
			+ "  \"status\": \"available\"\r\n"
			+ "}";
	String resource="/pet/";
	String response=
	RestAssured.given().header("Content-Type", "application/json").body(jsonBody)
	.when().post(baseUrl+resource).then().log().all().statusCode(200).extract().asString();
	
	Assert.assertTrue(response.contains("331"));
	}
	
	@Test
	public void addValidPetToStoreTest2() throws FileNotFoundException {
	
	
	String resource="/pet/";
	
	FileInputStream file = new FileInputStream("src/test/resources/test_data/add_pet.json");
	JsonPath json = new JsonPath(file);
	String jsonBody = json.prettify();
	
	String response=
	RestAssured.given().header("Content-Type", "application/json").body(jsonBody)
	.when().post(baseUrl+resource).then().log().all().statusCode(200).extract().asString();
	
	Assert.assertTrue(response.contains("332"));
	}
	
	@Test
	public void updateValidPetToStoreTest2() throws FileNotFoundException {
	
	
	String resource="/pet/";
	
	FileInputStream file = new FileInputStream("src/test/resources/test_data/update_pet.json");
	JsonPath json = new JsonPath(file);
	String jsonBody = json.prettify();
	
	String response=
	RestAssured.given().header("Content-Type", "application/json").body(jsonBody)
	.when().put(baseUrl+resource).then().log().all().statusCode(200).extract().asString();
	
	Assert.assertTrue(response.contains("doggie-332"));
	}

}
