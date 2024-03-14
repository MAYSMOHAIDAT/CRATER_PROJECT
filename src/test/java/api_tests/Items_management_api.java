package api_tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import utils.BrowserUtils;
import utils.DataReader;

public class Items_management_api {

	
	
 BrowserUtils utils = new BrowserUtils();
	
	
	// normally in Java, you would just create a method to do certain things
	// and use the main method to call those methods to execute. 
	
	// in TestNG, you just create a method to do certain things, and you don't need main method to execute.

	
	String token;
	String baseurl = "http://crater.primetech-apps.com";
	Response response;
	int item_id;
	// in RestAssured, we attach the body of the request in the given section.
	
	// injecting a string into a string:  "+variable+"
	
@Test(groups= {"regression","smoke-test"})
public void login_test() {

		String body="{\r\n"
		+ " \"username\"   : \""+DataReader.getProperty("username")+"\",\r\n"
		+ " \"password\"   : \""+DataReader.getProperty("password")+"\",\r\n"
    	+ "\"device_name\" : \"mobile_app\"\r\n"
		+ "}";
		
		Response response=RestAssured
	    .given().contentType("application/json").body(body)
		.when().post(baseurl+"/api/v1/auth/login");
		response.prettyPrint();
		token=response.jsonPath().get("token");
		
		response.then().statusCode(200).contentType("application/json");
		
}	



		@Test (dependsOnMethods="login_test" , groups= {"smoke-test"})
		
		public void list_all_items()
		{
			Response response=RestAssured
			.given().contentType("application/json").auth().oauth2("Bearer "+ token)
			.when().get(baseurl+"/api/v1/items");
			
			response.then().statusCode(200).contentType("application/json");
			
			
			
		}
		
		
		
		
		
		@Test(dependsOnMethods="login_test" , groups= {"smoke-test","create_item"})
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
		
		
		
		
//		// create an item and verify the item input is correct in response
//		@Test (dependsOnMethods= {"login_test"})
//		public void create_item() {
//			// in order to create an item, what do we need?  
//			// we need headers, authorization, and body
//			
//			Map<String, Object> payload = new HashMap<>();
//			payload.put("name", "Backpack" + utils.randomNumber());
//			payload.put("price", 3800);
//			payload.put("unit_id", 11);
//			payload.put("description", "nice backpack");
//			
//			response = given().auth().oauth2("Bearer " + token).body(payload).contentType("application/json")
//					.when().post(baseurl + "/api/v1/items");
//			
//			item_id = response.jsonPath().get("data.id");
//			
//			String item_name = response.jsonPath().get("data.name");
//
//			System.out.println(" Name of the item created is: " + item_name);
//		}
		
		
		
		
		
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
			
//			response.then().statusCode(200).contentType("application/json");
			
		
			
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
		
		
		
		
		
		//demo test for query parameter
		
		@Test
		
		public void petStore_findByStatus()
		{
			
			Response response=RestAssured
			.given().accept("application/json")
			.queryParam("status", "available")
			.when().get("https://petstore.swagger.io/v2/pet/findByStatus");
			
			
			response.prettyPrint();
			
			response.then().statusCode(200);
		}
		
		
		
		
		
		
		
		
		//Chain Validation
		//we use assertThat rather than response her
		@Test
		
		public void petStore_findByStatus_chain_validation()
		{
			
			
			 given().accept("application/json")
			.queryParam("status", "available")
			.when().get("https://petstore.swagger.io/v2/pet/findByStatus")
			.then().assertThat().statusCode(200)
			.and().assertThat().contentType("application/json");
			
		
		}
		
		
		
		//invalid api test case demo
		//if we need back to record 2:07:13
//		@Test
		
//		public void petStore_invalid_endpoint()
//		{
//			
//			Response response=RestAssured
//					.given().accept("application/json")
//					.when()
//					.get("https://petstore.swagger.io/v2/pet/" +7);
//					
//			response.prettyPrint();
//			
//			response.then().assertThat().statusCode(404)
//			.and().assertThat().contentType("application/json")
//			.and().assertThat().body("message",Matchers.equalTo("Pet not found"));
//			
//		}
		
		
		
	}
	
	

	
	


















//	
//	@Test (dependsOnMethods= {"login_test()"})
//	public void list_all_items() {
//		Response response = RestAssured
//		.given().accept("application/json").auth().oauth2("Bearer " + token)
//		.when().get(baseurl+"/api/v1/items");
//		
//		//response.prettyPrint();
//		
//		response.then().statusCode(200).contentType("application/json");
//	}
	
	
