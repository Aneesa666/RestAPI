package StepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		Stepefinition m =new Stepefinition();
		
		if(Stepefinition.place_id==null)
		{
		m.add_place_Payload_with("sweeety", "English", "Mangalore");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_maps_to_using("sweeety", "getPlaceAPI");
		}
	}

}
