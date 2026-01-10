package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import client.ApiClient;
import io.restassured.response.Response;

public class RequestBuilderTest extends BaseTest {
	
	@Test
	public void testGetWithQueryParams() {
		System.out.println("TestCase Name : testGetWithQueryParams");
		
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("userId", 1);
		
		Response resonse = ApiClient.get("/posts", queryParams);
		Assert.assertEquals(resonse.statusCode(), 200);
		System.out.println("Status Code :: " + resonse.statusCode());
	}
	
	@Test
	public void testPostWithDynamicPayload() {
		System.out.println("TestCase Name : testPostWithDynamicPayload");

        String payload = """
                {
                  "title": "AI Ready Framework",
                  "body": "Dynamic Payload Test",
                  "userId": 1
                }
                """;
        Map<String, String> headers = new HashMap<>();
        headers.put("Custom-Header", "AI-Test");
        
        Response resonse = ApiClient.post("/posts", payload, headers);
        Assert.assertEquals(resonse.statusCode(), 201);
        System.out.println("Status Code :: " + resonse.statusCode());
	}
	

}
