package tests;

import java.util.Map;

import org.testng.annotations.Test;

import client.ApiClient;
import io.restassured.response.Response;
import utils.ResponseValidator;

public class DemoAIBridgeTest {
	
    @Test
    public void shouldCreatePost() {
        String endpoint = "/posts";
        String payload = "{\"title\":\"AI Ready Framework\",\"body\":\"Dynamic Payload Test\",\"userId\":1}";

        Response response = ApiClient.post(endpoint, payload, null);

        ResponseValidator.validateStatusCode(response, 201);

        Map<String, Object> expectedFields = Map.of(
                "id", 101,
                "title", "AI Ready Framework",
                "body", "Dynamic Payload Test",
                "userId", 1
        );
        ResponseValidator.validateBodyFields(response, expectedFields);
    }

}
