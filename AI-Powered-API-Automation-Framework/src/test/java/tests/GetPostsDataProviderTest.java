package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import client.ApiClient;
import io.restassured.response.Response;
import utils.ResponseValidator;

public class GetPostsDataProviderTest extends BaseTest {
	
	@DataProvider(name="userIds")
	public Object[][] userIdData(){
		return new Object[][] {
			{1},
			{2},
			{3}
		};
	}
	
	@Test(groups="regression", dataProvider = "userIds")
	public void shouldFetchPostsForMultipleUsers(int userId) {
		
		System.out.println("Executing Against UserId : " + userId);

		// GIVEN: Query parameters
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("userId", userId);

		// WHEN: Call GET /posts?userId=1
		Response response = ApiClient.get("/posts", queryParams);

		// THEN: Validate status code
		ResponseValidator.validateStatusCode(response, 200);

		// THEN: Validate response body (first element)
		Map<String, Object> expectedBody = new HashMap<>();
		expectedBody.put("[0].userId", userId);

		ResponseValidator.validateBodyFields(response, expectedBody);
		
	}

}
