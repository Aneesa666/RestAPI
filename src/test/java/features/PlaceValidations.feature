Feature: Validating AddPlaceAPIs
@AddPlace
Scenario Outline: Verify if place is being added successfully using AddPlaceAPI

	Given Add place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"
	
	Examples:
	|name | language | address |
	|whitehouse|English    |Washington|
#	|hhouse|English  |Globe2 cross center|
# |ihouse|French   |new3 cross center|

@DeletePlace
Scenario: Verify DeletePlaceAPI

Given Delete place Payload
When user calls "deletePlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in response body is "OK"