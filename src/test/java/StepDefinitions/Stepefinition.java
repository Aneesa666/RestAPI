package StepDefinitions;
import static io.restassured.RestAssured.given;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.AddPlace;
import pojo.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResorces;
import resources.TestdataBuild;
import resources.Utils;

public class Stepefinition extends Utils{
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String place_id;
	TestdataBuild data =new TestdataBuild();
	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_Payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		//RestAssured.baseURI ="https://rahulshettyacademy.com";
		
		
		 
		 res = given().spec(requestSpecification())
		.body(data.addPlacePayload(name, language, address));
	}
	
	

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    
	// Write code here that turns the phrase above into concrete actions
		
		APIResorces resourceAPI = APIResorces.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json").build();
		
		if(method.equalsIgnoreCase("POST"))
	   response = res.when().post(resourceAPI.getResource());
	   else if(method.equalsIgnoreCase("GET"))
		   response = res.when().get(resourceAPI.getResource());   
			   
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		
	    assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
		
	    assertEquals(getJsonPath(response,key),value);
	
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		place_id = getJsonPath(response,"place_id");
		system.out.println(place_id);
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualname = getJsonPath(response,"name");
		assertEquals(expectedname,actualname);
		
	   
	}
	
	@Given("Delete place Payload")
	public void delete_place_Payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		 res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}



}
