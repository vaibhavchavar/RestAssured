package Test2;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson {
	
	public static void main(String[] args) {
		JsonPath js = new JsonPath(Payload.CourseBody());
		//Print no of courses(count)
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//Print purchaseAmount
		int totalAmt = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmt);
		
		//Print title of first course
		
		String title = js.getString("courses[0].title");
		System.out.println(title);
		
		//Print all courseTitle
		for(int i = 0; i < count; i++) {
			String alltitle = js.get("courses["+i+"].title");
			System.out.println(alltitle);
			System.out.println(js.get("courses["+i+"].price").toString());
		}
		
		//Print no of copies sold by Cypress
		
		System.out.println("Print no of copies sold by RPA");
		for(int i = 0; i < count; i++) {
			String coursetitle = js.get("courses["+i+"].title");
			if(coursetitle.equals("Cypress")) {
			System.out.println(js.get("courses["+i+"].copies").toString());
			break;
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
