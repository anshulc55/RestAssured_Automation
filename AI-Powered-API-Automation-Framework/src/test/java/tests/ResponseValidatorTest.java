package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import client.ApiClient;
import io.restassured.response.Response;
import utils.ResponseValidator;

public class ResponseValidatorTest extends BaseTest {

	@Test
	public void validateGetPostById() {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("id", 3);

		Response response = ApiClient.get("/posts", queryParams);

		ResponseValidator.validateStatusCode(response, 200);

		// Validate response body fields
		Map<String, Object> expectedBodyFields = new HashMap<>();
		expectedBodyFields.put("[0].id", 3);
		expectedBodyFields.put("[0].userId", 1);

		ResponseValidator.validateBodyFields(response, expectedBodyFields);

		// Validate response JSON schema
		ResponseValidator.validateJsonSchema(response, "schema/post-schema.json");

	}

}
