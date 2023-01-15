package Test;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;


import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.Matchers.equalTo;


public class SoapXML {
	@Test
	public void soapxml() throws IOException {
		File file = new File("./SOAPRequest/add.xml.odt");
		
		if(file.exists()) {
			System.out.println("File exists>>>>");
		}
		
		FileInputStream xmlfile = new FileInputStream(file);
		
		String reqbody = IOUtils.toString(xmlfile,"UTF-8");
		
		baseURI = "https://ecs.syr.edu/faculty/fawcett/Handouts/cse775/code/calcWebService";
		
		given().contentType("text/xml")
		.accept(ContentType.XML)
		.body(reqbody)
		.when()
		.post("/Calc.asmx?op=Add")
		.then().statusCode(200).log().all()
		.and()
		.body("//*:AddResult.text()",equalTo("12"));
		
	}

}
