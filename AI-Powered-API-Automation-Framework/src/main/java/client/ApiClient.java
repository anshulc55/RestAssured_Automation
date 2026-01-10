package client;

import java.util.Map;
import java.io.ByteArrayInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.RequestBuilder;

public class ApiClient {

//	private static RequestSpecification requestSpec;
//	
//	static {
//		RestAssured.baseURI = ConfigManager.getInstance().getBaseUrl();
//		requestSpec = RestAssured.given().header("Content-Type","application/json");
//	}
//
//	private static RequestSpecification getRequestSpec() {
//		return requestSpec;
//	}
//
//	// ---------- GET ----------
//	public static Response get(String endpoint) {
//		return getRequestSpec().when().get(endpoint).then().extract().response();
//
//	}
//
//	// ---------- GET with Path Param ----------
//	public static Response get(String endpoint, int id) {
//		return getRequestSpec().when().get(endpoint + "/" + id).then().extract().response();
//
//	}
//
//	// ---------- POST ----------
//	public static Response post(String endpoint, String payload) {
//		return getRequestSpec().body(payload).when().post(endpoint).then().extract().response();
//
//	}
//
//	// ---------- PUT ----------
//	public static Response put(String endpoint, String payload, int id) {
//		return getRequestSpec().body(payload).when().put(endpoint + "/" + id).then().extract().response();
//
//	}
//	
//	// ---------- DELETE ----------
//    public static Response delete(String endpoint, int id) {
//    	return getRequestSpec().when().delete(endpoint + "/" + id).then().extract().response();
//    }

	private static final Logger logger = LogManager.getLogger(ApiClient.class);

	/**
	 * Centralized response logging Centralized Allure Report Attachment
	 */
	private static void logResponse(Response response) {
		logger.info("Response Status Code: {}", response.getStatusCode());
		logger.debug("Response Body: {}", response.asString());

//		Allure.addAttachment("Response Status", String.valueOf(response.getStatusCode()));
//		Allure.addAttachment("Response Body", new ByteArrayInputStream(response.asPrettyString().getBytes()));
		
		Allure.parameter("Response Status", response.getStatusCode());
		Allure.parameter("Response Body", response.asPrettyString());

	}

	// ---------- GET ----------
	public static Response get(String endpoint, Map<String, Object> queryParams) {

		logger.info("Preparing GET request for endpoint: {}", endpoint);
		logger.info("Query Params: {}", queryParams);

		RequestSpecification request = RequestBuilder.createRequest();
		RequestBuilder.withQueryParams(request, queryParams);

//		Allure.addAttachment("Get Request",
//				new ByteArrayInputStream(("Endpoint: " + endpoint + "\nQuery Params: " + queryParams).getBytes()));

		Allure.parameter("Endpoint", endpoint);
		Allure.parameter("Query Params", queryParams);
		
		Response response = request.when().get(endpoint).then().extract().response();

		logResponse(response);

		return response;
	}

	// ---------- POST ----------
	public static Response post(String endpoint, String payload, Map<String, String> headers) {

		logger.info("Executing POST request: {}", endpoint);
		logger.info("POST request Header: {}", headers);
		logger.info("POST request Payload: {}", payload);

		RequestSpecification request = RequestBuilder.createRequest();

		RequestBuilder.withHeaders(request, headers);
		RequestBuilder.withPayload(request, payload);

//		Allure.addAttachment("POST Request Payload", new ByteArrayInputStream(payload.getBytes()));
		
		Allure.parameter("Endpoint", endpoint);
		Allure.parameter("POST Request Payload", payload);
		Allure.parameter("POST Request Headers", headers);

		Response response = request.when().post(endpoint).then().extract().response();

		logResponse(response);

		return response;
	}

	// ---------- PUT ----------
	public static Response put(String endpoint, int id, String payload) {

		logger.info("Executing PUT request: {}/{}", endpoint, id);

		RequestSpecification request = RequestBuilder.createRequest();
		RequestBuilder.withPayload(request, payload);

//		Allure.addAttachment("PUT Request Payload", new ByteArrayInputStream(payload.getBytes()));
		Allure.parameter("Endpoint", endpoint);
		Allure.parameter("PUT Request Payload", payload);
		Allure.parameter("PUT Request ReqID", id);

		Response response = request.when().put(endpoint + "/" + id).then().extract().response();
		logResponse(response);

		return response;
	}

	// ---------- DELETE ----------
	public static Response delete(String endpoint, int id) {

		logger.info("Executing DELETE request: {}/{}", endpoint, id);

		RequestSpecification request = RequestBuilder.createRequest();

//		Allure.addAttachment("Delete Request ",
//				new ByteArrayInputStream(("Endpoint: " + endpoint + "\nReqID: " + id).getBytes()));
		
		Allure.parameter("Endpoint", endpoint);
		Allure.parameter("DELETE Request ReqID", id);
		
		Response response = request.when().delete(endpoint + "/" + id).then().extract().response();

		logResponse(response);

		return response;
	}

}
