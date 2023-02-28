package Test2;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void sumvalidation() {
	JsonPath js = new JsonPath(Payload.CourseBody());
	int count = js.getInt("courses.size()");
	int sum = 0;
	
	for(int i =0; i<count;i++) {
		int price = js.getInt("courses["+i+"].price");
		int copies = js.getInt("courses["+i+"].copies");
		
		int amount = price * copies;
		System.out.println(amount);
		sum = sum + amount;
		
	}
	Assert.assertEquals(sum,js.get("dashboard.purchaseAmount"));
	}

}
