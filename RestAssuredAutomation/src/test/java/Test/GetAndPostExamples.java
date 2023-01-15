package Test;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetAndPostExamples {
//	@Test
	public void getTest() {
		baseURI ="https://reqres.in/api";
		given().
		get("users?page=2").
		then().
		statusCode(200).
		body("data[3].first_name",equalTo("Byron")).
		body("data.first_name",hasItems("George", "Rachel"));
		
		
	}
	@Test
	public void postTest() {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("name","Vaibhav");
//		map.put("job","Tester");
//		System.out.println(map);
		
		JSONObject req = new JSONObject();
		req.put("name","Vaibhav");
		req.put("job","Tester");
		System.out.println(req.toJSONString());
		
		baseURI ="https://reqres.in/api";
		given().
		header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON).
		body(req.toJSONString())
		.when()
		.post("/users")
		.then().statusCode(201).log().all();
		
	}

	
	
	
	
	
	
	
	
	
	
	
}
