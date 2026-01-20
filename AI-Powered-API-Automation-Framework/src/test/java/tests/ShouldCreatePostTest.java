package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import client.ApiClient;
import io.restassured.response.Response;
import utils.ResponseValidator;

public class ShouldCreatePostTest extends BaseTest {

    @Test
    public void shouldCreatePost() {
        String endpoint = "/posts";

        String payload = "{\"title\":\"AI Ready Framework\",\"body\":\"Dynamic Payload Test\",\"userId\":1}";

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        Response response = ApiClient.post(endpoint, payload, headers);

        ResponseValidator.validateStatusCode(response, 201);

        Map<String, Object> expectedBodyFields = new HashMap<>();
        expectedBodyFields.put("id", 101);
        expectedBodyFields.put("title", "AI Ready Framework");
        expectedBodyFields.put("body", "Dynamic Payload Test");
        expectedBodyFields.put("userId", 1);

        ResponseValidator.validateBodyFields(response, expectedBodyFields);
    }
}
