package tests;

import org.testng.annotations.Test;

import client.ApiClient;
import io.restassured.response.Response;
import utils.ResponseValidator;

public class UpdateDeletePostsTest extends BaseTest {
	
	@Test(description = "Verify user can update a post")
	public void shouldUpdatePost() {
		
        String payload = """
                {
                  "id": 1,
                  "title": "Updated Title",
                  "body": "Updated Body",
                  "userId": 1
                }
                """;
        Response response = ApiClient.put("/posts", 1, payload);
        ResponseValidator.validateStatusCode(response, 200);
	}
	
	@Test(description = "Verify user can delete a post")
	public void shouldDeletePost() {
		
		Response response = ApiClient.delete("/posts", 1);
		ResponseValidator.validateStatusCode(response, 200);
	}

}
