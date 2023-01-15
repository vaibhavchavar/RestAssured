package Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PutPatchDeleteExamples {
	
	@Test
	public void putTest() {
		
		JSONObject req = new JSONObject();
		req.put("name","Vaibhav");
		req.put("job","Tester");
		System.out.println(req.toJSONString());
		
		baseURI ="https://reqres.in/api";
		given().
		header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON).
		body(req.toJSONString())
		.when()
		.put("/users/2")
		.then().statusCode(200).log().all();
		
	}
	
	@Test
	public void patchTest() {
		
		JSONObject req = new JSONObject();
		req.put("name","Vaibhav");
		req.put("job","Tester");
		System.out.println(req.toJSONString());
		
		baseURI ="https://reqres.in";
		given().
		header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON).
		body(req.toJSONString())
		.when()
		.patch("/api/users/2")
		.then().statusCode(200).log().all();
		
	}
	
	@Test
	public void DeleteTest() {
		
		baseURI ="https://reqres.in";
		when()
		.delete("/api/users/2")
		.then().statusCode(204).log().all();
		
	}

}
