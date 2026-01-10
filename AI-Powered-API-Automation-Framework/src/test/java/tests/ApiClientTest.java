package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import client.ApiClient;
import io.restassured.response.Response;

public class ApiClientTest extends BaseTest{

//	@Test
//	public void testGetAllPosts() {
//		Response response = ApiClient.get("/posts");
//
//		Assert.assertEquals(response.statusCode(), 200);
//		System.out.println("Executing Test Case : testGetAllPosts");
//		System.out.println("API Response Code : " + response.statusCode());
//	}
//
//	@Test
//	public void testGetPostById() {
//		Response response = ApiClient.get("/posts", 10);
//
//		System.out.println("Executing Test Case : testGetPostById");
//		Assert.assertEquals(response.statusCode(), 200);
//		Assert.assertEquals(response.jsonPath().getInt("id"), 10);
//		System.out.println("API Response Code : " + response.statusCode());
//	}

//	@Test
//	public void testCreatePost() {
//		String payload = """
//				{
//				  "title": "API Automation",
//				  "body": "REST Assured Framework",
//				  "userId": 1
//				}
//				""";
//		Response response = ApiClient.post("/posts", payload);
//		
//		System.out.println("Executing Test Case : testCreatePost");
//		Assert.assertEquals(response.statusCode(), 201);
//		System.out.println(response.asString());
//		System.out.println("API Response Code : " + response.statusCode());
//	}
	
//	@Test
//	public void testUpdatePost() {
//        String payload = """
//                {
//                  "id": 1,
//                  "title": "Updated Title",
//                  "body": "Updated Body",
//                  "userId": 1
//                }
//                """;
//        System.out.println("Executing Test Case : testUpdatePost");
//        Response response = ApiClient.put("/posts", payload, 1);
//        Assert.assertEquals(response.statusCode(), 200);
//        System.out.println("API Response Code : " + response.statusCode());
//	}
	
	
	@Test
	public void testDeletePost() {
		
		System.out.println("Executing Test Case : testDeletePost");
		Response response = ApiClient.delete("/posts", 1);
		Assert.assertEquals(response.statusCode(), 200);
		System.out.println("API Response Code : " + response.statusCode());
	}

}
