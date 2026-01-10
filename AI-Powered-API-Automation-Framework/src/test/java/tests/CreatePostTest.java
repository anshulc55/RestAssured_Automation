package tests;

import org.testng.annotations.Test;
import client.ApiClient;
import io.restassured.response.Response;
import utils.ResponseValidator;

public class CreatePostTest extends BaseTest {

	@Test(description = "Verify user can create a new post")
	public void shouldCreatePost() {

		// GIVEN: Valid post payload
		String payload = """
				{
				  "title": "AI Friendly Test",
				  "body": "Clean Test Layer Design",
				  "userId": 1
				}
				""";

		// WHEN: Call POST /posts
		Response response = ApiClient.post("/posts", payload, null);

		// THEN: Validate response
		ResponseValidator.validateStatusCode(response, 201);

	}

}
