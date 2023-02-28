package Test2;

import static io.restassured.RestAssured.given;



import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.sun.tools.javac.util.DefinedBy.Api;

import POJO.WebAutomation;
import POJO.api;
import POJO.getCourse;

//
//import org.openqa.selenium.By;
//
//import org.openqa.selenium.Keys;
//
//import org.openqa.selenium.WebDriver;
//
//import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.parsing.Parser;

import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;

import io.restassured.response.ResponseBody;





public class OOuthTest extends getCourse {

	public static void main(String[] args) {
		
		
		
//		Mandatory fields for GetAuthorization Code Request ;
//				End Point : Authorization server url
//				Query Params:Scope, Auth_url, client_id, response_type, redirect_uri
//
//				output : Code
//
//
//
//				Mandatory fields for GetAccessToken Request :
//				End point : Access token url
//				Query Params :Code, client_id, 	client_secret, redirect_uri, grant_type
//				Output : Access token
		
//		If grant type(Security Mechanism) is Client Credentials and Password Credentials then we do not req to launch browser
		
//		System.setProperty("webdriver.chrome.driver","D:\\chromedriver_win32\\chromedriver.exe");
//		ChromeDriver driver = new ChromeDriver();
//		driver.get("https://admin-demo.nopcommerce.com/login");
		
//		send username and password to get authorization url

		String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";



		String partialcode=url.split("code=")[1];

		String code=partialcode.split("&scope")[0];

		System.out.println(code); //here we will get a authorization code

		String response =

				given() 

				.urlEncodingEnabled(false)

				.queryParams("code",code)//pass this code in query param



				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

				.queryParams("grant_type", "authorization_code")

				.queryParams("state", "verifyfjdss")

				.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")

				// .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")



				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")

				.when().log().all()

				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		// System.out.println(response);
		
		// In response we will get access token

		JsonPath jsonPath = new JsonPath(response);

		String accessToken = jsonPath.getString("access_token");

		System.out.println(accessToken);//axtract access token

		getCourse gc =    given().contentType("application/json").

				queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)

				.when()

				.get("https://rahulshettyacademy.com/getCourse.php").as(getCourse.class);

				
		System.out.println(gc.getLinkedin());//from Pojo classes
		System.out.println(gc.getInstructor());
		gc.getCourses().getApi().get(1).getCourseTitle();
		
		List<api> apicourses = gc.getCourses().getApi();
		for(int i =0; i<apicourses.size();i++) {
			if(apicourses.get(i).getCourseTitle().equals("SoapUI Webservice Testing")) {
				apicourses.get(i).getPrice();
			}
		}
		String [] expectedCourses = {"Selenium webdriver java","Cypress","Protractor"};
		ArrayList<String> a = new ArrayList<String>();
		List<WebAutomation> w = gc.getCourses().getWebAutomation();
		for(int i =0; i<w.size();i++) {
			if(w.get(i).getCourseTitle().equals("SoapUI Webservice Testing")) {
				a.add(w.get(i).getCourseTitle());
			}
		}
		
		List<String> expectedList = Arrays.asList(expectedCourses);
		Assert.assertTrue(a.equals(expectedCourses));
		


	}

}
