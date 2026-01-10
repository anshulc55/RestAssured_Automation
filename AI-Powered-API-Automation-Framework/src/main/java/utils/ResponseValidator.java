package utils;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ResponseValidator {

	private static final Logger logger = LogManager.getLogger(ResponseValidator.class);

	// Validates HTTP status code
	@Step("Validate HTTP status code is {expectedStatusCode}")
	public static void validateStatusCode(Response response, int expectedStatusCode) {

		logger.info("Validating status code. Expected: {}, Actual: {}", expectedStatusCode, response.getStatusCode());

		Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Status code validation failed");
	}

	// Validates response headers
	@Step("Validate HTTP Headers")
	public static void validateHeaders(Response response, Map<String, String> expectedHeaders) {
		if (expectedHeaders == null || expectedHeaders.isEmpty()) {
			logger.info("No headers provided for validation");
			return;
		}

		expectedHeaders.forEach((headerName, expectedValue) -> {
			String actualValue = response.getHeader(headerName);
			logger.info("Validating header | Name: {} | Expected: {} | Actual: {}", headerName, expectedValue,
					actualValue);

			Assert.assertEquals(response.getHeader(headerName), expectedValue,
					"Header validation failed for: " + headerName);
		});
	}

	// Validates response body fields using JSON Path
	// Example: id -> 1
	@Step("Validate HTTP Body Fields")
	public static void validateBodyFields(Response response, Map<String, Object> expectedBodyFields) {

		if (expectedBodyFields == null || expectedBodyFields.isEmpty()) {
			logger.info("No Body provided for validation");
			return;
		}

		expectedBodyFields.forEach((jsonPath, expectedValue) -> {
			Object actualValue = response.jsonPath().get(jsonPath);
			logger.info("Validating response body | JSONPath: {} | Expected: {} | Actual: {}", jsonPath, expectedValue,
					actualValue);

			Assert.assertEquals(actualValue, expectedValue, "Body validation failed for path: " + jsonPath);
		});
	}

	// Validates JSON schema
	@Step("Validate HTTP Body JOSN Schema")
	public static void validateJsonSchema(Response response, String schemaFilePath) {
		logger.info("Validating JSON schema using file: {}", schemaFilePath);
		response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaFilePath));
	}
}
