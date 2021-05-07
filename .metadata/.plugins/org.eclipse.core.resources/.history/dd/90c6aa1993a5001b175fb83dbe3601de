package basic.rest.postcall;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

public class extractRequestResponse {
	
	@Test
	public void extractResponse() {
		RestAssured.baseURI = "https://api.github.com";
		String bearerToken = "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";
		
		String requestBody = "{\n"
		   		+ "    \"name\": \"Api-testing-restcall-3\",\n"
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
		
		//System.out.println(response.asString());
				
	}

}
