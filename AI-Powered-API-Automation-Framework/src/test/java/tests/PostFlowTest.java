package tests;

import org.testng.annotations.Test;

import client.ApiClient;
import io.restassured.response.Response;
import utils.ResponseValidator;

public class PostFlowTest extends BaseTest {
	private static int postId = 1;

	@Test(groups = "flow")
	public void createPost() {
		String payload = """
				    {
				      "title": "Flow Test",
				      "body": "Dependency Example",
				      "userId": 1
				    }
				""";

		Response response = ApiClient.post("/posts", payload, null);
		ResponseValidator.validateStatusCode(response, 201);
	}

	@Test(groups = "flow", dependsOnMethods = "createPost")
	public void updatePost() {
		String payload = """
				    {
				      "id": 1,
				      "title": "Updated Flow",
				      "body": "Updated",
				      "userId": 1
				    }
				""";
		Response response = ApiClient.put("/posts", postId, payload);
		ResponseValidator.validateStatusCode(response, 200);
	}

}
