package basic.rest.postcall;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

public class deleteRequest {
	
	public String baseUri = "https://api.github.com";
	public String bearerToken = "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";
	public String repoName ;
	
	@Test
	public void createRepo() {
		RestAssured.baseURI = baseUri;
		
		String requestBody = "{\n"
		   		+ "    \"name\": \"Api-testing-restcall-5\",\n"
		   		+ "    \"description\": \"Repository created via Rest Assured Call\"\n"
		   		+ "}";
		

		Response response = given()
			   .header("Content-Type", "application/json")
			   .header("Authorization", "Bearer " + bearerToken)
			   .body(requestBody).
		when()
				.post("/user/repos").
		then().assertThat().statusCode(201).and().
			   contentType(ContentType.JSON).
	    extract().response();
		
		String jsonResponse = response.asString();
		
		JsonPath responseBody = new JsonPath(jsonResponse);
		System.out.println("Node Id : " + responseBody.get("node_id"));
		System.out.println("Name : " + responseBody.get("name"));
		System.out.println("Full Name : " + responseBody.get("full_name"));
		
		repoName = responseBody.get("full_name");
				
	}
	
	@Test
	public void deleteRepo() {
		RestAssured.baseURI = baseUri;
		
		given()
		   .header("Content-Type", "application/json")
		   .header("Authorization", "Bearer " + bearerToken).
		when()
			.delete("/repos/" + repoName).
		then().assertThat().statusCode(204);
		
		System.out.println("Repository Delete : " + repoName);
		
	}

}
