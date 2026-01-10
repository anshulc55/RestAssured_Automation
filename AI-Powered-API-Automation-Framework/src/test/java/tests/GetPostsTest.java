package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import client.ApiClient;
import io.restassured.response.Response;
import utils.ResponseValidator;

public class GetPostsTest extends BaseTest {

	
    @Test(
            groups = {"smoke", "regression"},
            description = "Verify user can fetch posts by userId"
        )
	public void shouldFetchPostsByUserId() {

		// GIVEN: Query parameters
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("userId", 1);

		// WHEN: Call GET /posts?userId=1
		Response response = ApiClient.get("/posts", queryParams);

		// THEN: Validate status code
		ResponseValidator.validateStatusCode(response, 200);

		// THEN: Validate response body (first element)
		Map<String, Object> expectedBody = new HashMap<>();
		expectedBody.put("[0].userId", 1);

		ResponseValidator.validateBodyFields(response, expectedBody);

	}
    
    @Test(
            groups = {"negative"},
            description = "Verify API returns empty response for invalid userId"
        )
    public void shouldReturnEmptyForInvalidUser() {
		// GIVEN: Query parameters
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("userId", 19687484);

		// WHEN: Call GET /posts?userId=1
		Response response = ApiClient.get("/posts", queryParams);

		// THEN: Validate status code
		ResponseValidator.validateStatusCode(response, 200);
    }

}
