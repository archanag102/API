import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.json.*;
import static io.restassured.RestAssured.given;

public class PostApiTesting {

	private static final String BASE_URL = "https://reqres.in";
	private static final int USER_ID = 2;

	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = BASE_URL;
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
}
