package stepDefinitions;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.AddPlace;
import pojo.Location;
import resources.TestDataBuild;
import resources.Utils;
//Inheritence - Can use all methods of Utils without creating object
public class stepDefinition extends Utils{
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    //Removing this method 	  - moving to next step@when
		// resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	    res=given().spec(requestSpecification())
	    .body(data.addPlacePayload(name,language,address));

	}
	@When("user calls AddPlaceAPI with POST HTTP request")
	public void user_calls_add_place_api_with_post_http_request() {
	    // Write code here that turns the phrase above into concrete actions
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	    response =res.when().post("/maps/api/place/add/json").
	    then().spec(resspec).extract().response();
	}
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		int actual = response.getStatusCode();
		assertEquals(actual,200);
	}
	@Then("the {string} response body is {string}")
	public void the_response_body_is(String keyValue,String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
	    String responseString=response.asString();
       JsonPath js = new JsonPath(responseString);
       //toString - to convert keyValue into String
       assertEquals(js.get(keyValue).toString(),Expectedvalue);

	   }


}
