package Test2;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payload;
import Files.ReusableMethods;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class Basics {
	public static void main(String[] args) {


		baseURI = "https://reqres.in/api";

		String response = given().log().all().queryParam("page","2").header("Content-Type","application/json")
		.body(Payload.addPlace())
		.when().post("/users")
		.then().assertThat().log().all().statusCode(201).body("first_name", equalTo("Michael"))
		.extract().response().asString();
		
		System.out.println(response);
		
		JsonPath ref = new JsonPath(response);
		String email = ref.getString("email");
		
		System.out.println(email);
		
		String newlastName = "Ferguson";
		
		//put method
		
		given().log().all().queryParam("page","2").header("Content-Type","application/json")
		.body("{\r\n"
				+ "            \"id\": 8,\r\n"
				+ "            \"email\": \""+email+"\",\r\n"
				+ "            \"first_name\": \"Lindsay\",\r\n"
				+ "            \"last_name\": \""+newlastName+"\",\r\n"
				+ "            \"avatar\": \"https://reqres.in/img/faces/8-image.jpg\"\r\n"
				+ "        }")
		.when().put("/users")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Email successfully updated"));
		
		
//		get method
		String getResp = given().log().all().queryParam("page","2")
		.queryParam("page", "2")
		.when().get("/users")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(getResp);
		JsonPath js = ReusableMethods.jsonPath(getResp);
		String actualLastName = js.getString("last_name");
		System.out.println(actualLastName);
		Assert.assertEquals(actualLastName, newlastName);
		
		
		
		
		
		
		
		
		
		
		
		


	}

}
