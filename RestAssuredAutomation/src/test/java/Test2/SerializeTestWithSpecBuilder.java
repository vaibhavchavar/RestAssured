package Test2;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import Files.ReusableMethods;
import POJO.addPlace;
import POJO.location;

import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SerializeTestWithSpecBuilder {
	
	public static void main(String[] args) {
		baseURI = "https://rahulshettyacademy.com";
		addPlace p = new addPlace();
		p.setAccuracy(50);
		p.setAddress("29, sidelayout, cohen 09");
		p.setLanguage("French-In");
		p.setPhone_number("(+91) 435 546 3463");
		
		p.setWebsite("https://rahulshettyacademy.com");
		
		p.setName("frontline house");
		List<String> mylist = new ArrayList<String>();
		mylist.add("Shoe Park");
		mylist.add("shop");
		
		p.setTypes(mylist);
		
		location l = new location();
		l.setLat(-38.6594);
		l.setLng(33.2587);
		p.setLocation(l);
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON)
				.addQueryParam("key","qaclick123").build();
		
		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification res = given().spec(req)
		.body(p);
		Response response = res.when()
		.post("/maps/api/place/add/json")
		.then()
		.spec(resspec).extract().response();
		
		String responseString = response.asString();
		System.out.println(responseString);
	}

}
