package api_tests;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BrowserUtils;
import utils.DataReader;

public class items_managment_improve {
	
	
 BrowserUtils utils = new BrowserUtils();
	
	
	// normally in Java, you would just create a method to do certain things
	// and use the main method to call those methods to execute. 
	
	// in TestNG, you just create a method to do certain things, and you don't need main method to execute.

	
	String token;
	String baseurl = "http://crater.primetech-apps.com";
	Response response;
	int item_id;
	
	RequestSpecification specs;
	
	// in RestAssured, we attach the body of the request in the given section.
	
	// injecting a string into a string:  "+variable+"
	
@Test
public void login_test() {
	String body="{\r\n"
			+ " \"username\"   : \""+DataReader.getProperty("username")+"\",\r\n"
			+ " \"password\"   : \""+DataReader.getProperty("password")+"\",\r\n"
	    	+ "\"device_name\" : \"mobile_app\"\r\n"
			+ "}";
	
	
	
	    specs=RestAssured.given();
	    specs.baseUri(baseurl);
//      specs.port(8080);
		specs.contentType("application/json").body(body);
		response=specs.post("/api/v1/auth/login");
//		Response response=RestAssured
//	    .given().contentType("application/json").body(body)
//		.when().post(baseurl+"/api/v1/auth/login");
		response.prettyPrint();
		token=response.jsonPath().get("token");
		
		response.then().statusCode(200).contentType("application/json");
		
}	



		@Test (dependsOnMethods="login_test")
		
		public void list_all_items()
		{
			
			
			specs.baseUri(baseurl).accept("application/json").auth().oauth2("Bearer "+ token);
			response=specs.get("/api/v1/items");
//			.given().contentType("application/json").auth().oauth2("Bearer "+ token)
//			.when().get(baseurl+"/api/v1/items");
			
			response.then().statusCode(200).contentType("application/json");
			
			
			
		}
		
		
		
		
		
		@Test(dependsOnMethods="login_test")
		public void create_item()
		{
			
			Map<String, Object> Body= new HashMap<>();
			Body.put("name", "Backpak " + utils.randomNumber());
			Body.put("price", 3000);
			Body.put("unit_id", 11);
		
			
			Response response=RestAssured
			.given().auth().oauth2("Bearer "+ token).body(Body).contentType("application/json")
			.when().post(baseurl+"/api/v1/items");
			
			
    		item_id=response.jsonPath().get("data.id");
    		
    		
    		response.then().statusCode(200).contentType("application/json");
			
			
		}
		
		
		
		
		@Test(dependsOnMethods="create_item")
		
		public void get_item()
		{
			Response response=RestAssured
			.given().auth().oauth2("Bearer "+ token).contentType("application/json")
			.when().get(baseurl+"/api/v1/items/"+item_id);
			
			
			
			response.then().statusCode(200).contentType("application/json");
			
		}
		
		
		@Test(dependsOnMethods="get_item")
		
		public void update_item()
		{
			
			
			File payload = new File("./src/test/resources/testfiles/update_item_data.json");
			
			       Response response=RestAssured 
					.given().auth().oauth2("Bearer " + token).contentType("application/json")
					.body(payload)
					.when().put(baseurl+"/api/v1/items/"+item_id);
			
			response.prettyPrint();
			
			response.then().statusCode(200).contentType("application/json");
			
		
			
		}	
			
		
		@Test(dependsOnMethods="get_item")
		
		public void delete_item()
		{
			String body=" {\r\n"
					+ "    \"ids\": ["+item_id+"]\r\n"
					+ "}";
			Response response=RestAssured
			.given().auth().oauth2("Bearer "+ token).contentType("application/json")
			.body(body)
			.when().post(baseurl+"/api/v1/items/delete");
			
			response.then().statusCode(200).contentType("application/json");
			boolean deleteSuccess = response.jsonPath().get("success");
	    	}
		
		
		
	}


