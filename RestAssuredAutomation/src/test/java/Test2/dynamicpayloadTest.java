package Test2;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import Files.ReusableMethods;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class dynamicpayloadTest {
	
	@Test(dataProvider ="bookData")
	public void addBook(String isbn, String aisle) {
	
	baseURI = "http://216.10.245.166";
	String response = given().log().all().header("Content-Type", "application/json")
	.body(Payload.AddBook(isbn,aisle))
	.when().post("Library/Addbook.php")
	.then().assertThat().statusCode(200).extract().response().asString();
	
	JsonPath js = ReusableMethods.jsonPath(response);
	String id = js.get("ID");
	System.out.println(id);
	}
	
	@DataProvider(name ="bookData")
	public Object[][] getData() {
//		for sending the multipe data we use Dynamic array
		return new Object[][] {{"dfdsf","456"},{"sdfsdg","789"},{"wqer","123"}};
	}

}
