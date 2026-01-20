package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import client.ApiClient;
import io.restassured.response.Response;
import utils.ResponseValidator;

public class PostsPostTest extends BaseTest {

    @Test
    public void testCreatePost_positive() {
        String endpoint = "/posts";
        String payload = "{\"title\":\"AI Ready Framework\",\"body\":\"Dynamic Payload Test\",\"userId\":1}";

        Response response = ApiClient.post(endpoint, payload, null);

        ResponseValidator.validateStatusCode(response, 201);

        Map<String, String> expectedHeaders = new HashMap<>();
        expectedHeaders.put("Content-Type", "application/json; charset=utf-8");
        ResponseValidator.validateHeaders(response, expectedHeaders);

        Map<String, Object> expectedBodyFields = new HashMap<>();
        expectedBodyFields.put("id", 101);
        expectedBodyFields.put("title", "AI Ready Framework");
        expectedBodyFields.put("body", "Dynamic Payload Test");
        expectedBodyFields.put("userId", 1);
        ResponseValidator.validateBodyFields(response, expectedBodyFields);
    }

    @Test
    public void testCreatePost_negative_missingTitle() {
        String endpoint = "/posts";
        String payload = "{\"body\":\"Dynamic Payload Test\",\"userId\":1}";

        Response response = ApiClient.post(endpoint, payload, null);

        ResponseValidator.validateStatusCode(response, 400);
    }

    @Test
    public void testCreatePost_negative_missingBody() {
        String endpoint = "/posts";
        String payload = "{\"title\":\"AI Ready Framework\",\"userId\":1}";

        Response response = ApiClient.post(endpoint, payload, null);

        ResponseValidator.validateStatusCode(response, 400);
    }

    @Test
    public void testCreatePost_negative_missingUserId() {
        String endpoint = "/posts";
        String payload = "{\"title\":\"AI Ready Framework\",\"body\":\"Dynamic Payload Test\"}";

        Response response = ApiClient.post(endpoint, payload, null);

        ResponseValidator.validateStatusCode(response, 400);
    }

    @Test
    public void testCreatePost_negative_invalidUserIdType() {
        String endpoint = "/posts";
        String payload = "{\"title\":\"AI Ready Framework\",\"body\":\"Dynamic Payload Test\",\"userId\":\"one\"}";

        Response response = ApiClient.post(endpoint, payload, null);

        ResponseValidator.validateStatusCode(response, 400);
    }

    @Test
    public void testCreatePost_negative_malformedJsonPayload() {
        String endpoint = "/posts";
        String payload = "{\"title\":\"AI Ready Framework\",\"body\":\"Dynamic Payload Test\",\"userId\":1";

        Response response = ApiClient.post(endpoint, payload, null);

        ResponseValidator.validateStatusCode(response, 400);
    }

    @Test
    public void testCreatePost_corner_emptyStringsForTitleAndBody() {
        String endpoint = "/posts";
        String payload = "{\"title\":\"\",\"body\":\"\",\"userId\":1}";

        Response response = ApiClient.post(endpoint, payload, null);

        ResponseValidator.validateStatusCode(response, 400);
    }

    @Test
    public void testCreatePost_corner_userIdZero() {
        String endpoint = "/posts";
        String payload = "{\"title\":\"AI Ready Framework\",\"body\":\"Dynamic Payload Test\",\"userId\":0}";

        Response response = ApiClient.post(endpoint, payload, null);

        ResponseValidator.validateStatusCode(response, 400);
    }

    @Test
    public void testCreatePost_corner_userIdNegative() {
        String endpoint = "/posts";
        String payload = "{\"title\":\"AI Ready Framework\",\"body\":\"Dynamic Payload Test\",\"userId\":-1}";

        Response response = ApiClient.post(endpoint, payload, null);

        ResponseValidator.validateStatusCode(response, 400);
    }

    @Test
    public void testCreatePost_corner_extraUnknownField() {
        String endpoint = "/posts";
        String payload = "{\"title\":\"AI Ready Framework\",\"body\":\"Dynamic Payload Test\",\"userId\":1,\"extraField\":\"extra\"}";

        Response response = ApiClient.post(endpoint, payload, null);

        ResponseValidator.validateStatusCode(response, 201);

        Map<String, String> expectedHeaders = new HashMap<>();
        expectedHeaders.put("Content-Type", "application/json; charset=utf-8");
        ResponseValidator.validateHeaders(response, expectedHeaders);
    }

    @Test
    public void testCreatePost_corner_veryLongTitle() {
        String endpoint = "/posts";
        String payload = "{\"title\":\"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"body\":\"Dynamic Payload Test\",\"userId\":1}";

        Response response = ApiClient.post(endpoint, payload, null);

        ResponseValidator.validateStatusCode(response, 201);

        Map<String, String> expectedHeaders = new HashMap<>();
        expectedHeaders.put("Content-Type", "application/json; charset=utf-8");
        ResponseValidator.validateHeaders(response, expectedHeaders);
    }
}

