package Files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static JsonPath jsonPath(String response) {
	
	JsonPath js = new JsonPath(response);
	
	return js;
	}

}
