package Test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class testExamples {
	@Test
	public void test_1() {
		
		Response resp = get("https://reqres.in/api/users?page=2");
		System.out.println(resp.getTime());
		System.out.println(resp.getStatusCode());
		System.out.println(resp.getHeader("content-type"));
		System.out.println(resp.getBody().asString());
		System.out.println(resp.getSessionId());
		System.out.println(resp.getStatusLine());
		
		int statuscode = resp.getStatusCode();
		Assert.assertEquals(statuscode,200);
	}
	@Test
	public void test_2() {
		baseURI = "https://reqres.in/api";
		given().get("users?page=2").then().statusCode(200).body("data[1].id", equalTo(8))
		.log().all();
	}

}
