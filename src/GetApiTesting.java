import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class GetApiTesting {

	public static final String BASE_URL = "https://reqres.in";
	public static final int USER_ID = 2;

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
}
