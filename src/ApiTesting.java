import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class ApiTesting {

	private static final String BASE_URL = "https://reqres.in";
	private static final int USER_ID = 2;

	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = BASE_URL;
	}

	@Test
	public void getSingleUserTest() {
		// Get the RequestSpecification of the request to be sent to the server
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");
		// Get the status code of the request.
		// If request is successful, status code will be 200
		int statusCode = response.getStatusCode();
		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */, "Correct status code returned");
		RequestSpecification httpRequests = RestAssured.given();
		response = httpRequests.get("/api/users/" + USER_ID);
		// Assert that email first name
		ResponseBody email = response.getBody();
		String responseBody = email.asString();
		Assert.assertEquals(responseBody.contains("janet.weaver@reqres.in") /* Expected value */,
				true /* Actual Value */, "Response body contains email id");
	}

	@Test
	public void updateUserTest() {
		// Request
		RequestSpecification httpRequests = RestAssured.given();
		Response response = httpRequests.put("");
		// Initialize json object
		JSONObject requestParams = new JSONObject();
		// Passing parameters to create
		requestParams.put("name", "Jane Smith");
		requestParams.put("job", "Software Developer");
		Response responses = httpRequests.put("https://reqres.in/api/users/" + USER_ID);
		// Get the status code of the request.
		// If request is successful, status code will be 200
		int statusCode = responses.getStatusCode();
		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */,
				"Correct status code returned hence post has been updated successfully");
	}

	@Test
	public void createUserTest() {
		// Request
		RequestSpecification request = RestAssured.given();
		// Initialize json object
		JSONObject requestParams = new JSONObject();
		// Passing parameters to create
		requestParams.put("name", "John Doe");
		requestParams.put("job", "QA Engineer");
		Response response = request.post("/api/users");
		ResponseBody data = response.getBody();
		System.out.println(data);
	}

}
