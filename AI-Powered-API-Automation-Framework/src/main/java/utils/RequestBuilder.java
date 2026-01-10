package utils;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import client.ApiClient;
import config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {
	
	private static final Logger logger = LogManager.getLogger(RequestBuilder.class);

	/**
	 * Builds a base RequestSpecification. This method initializes REST Assured with
	 * base URI and applies common headers.
	 */
	public static RequestSpecification createRequest() {

		String baseUrl = ConfigManager.getInstance().getBaseUrl();
		RestAssured.baseURI = baseUrl;
		
		logger.info("Initializing request with Base URL: {}", baseUrl);
		
		return RestAssured.given().header("Content-Type", "application/json");
	}

	/**
	 * Adds custom headers to the request. Used for auth headers, custom headers,
	 * etc.
	 */
	public static RequestSpecification withHeaders(RequestSpecification request, Map<String, String> headers) {
		if (headers != null && !headers.isEmpty()) {
			logger.info("Adding headers: {}", headers);
			request.headers(headers);
		}
		return request;
	}

	/**
	 * Adds query parameters dynamically. Example: ?userId=1
	 */
	public static RequestSpecification withQueryParams(RequestSpecification request, Map<String, Object> queryParams) {
		if (queryParams != null && !queryParams.isEmpty()) {
			logger.info("Adding query params: {}", queryParams);
			request.queryParams(queryParams);
		}
		return request;
	}
	
	
	public static RequestSpecification withPayload(RequestSpecification request, String payload) {
		if (payload != null && !payload.isBlank()) {
			logger.debug("Adding request payload: {}", payload);
			request.body(payload);
		}
		return request;
	}

}




























