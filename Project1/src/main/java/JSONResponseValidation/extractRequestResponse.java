package JSONResponseValidation;

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
		

		Response response = given()
			   .header("Content-Type", "application/json")
			   .header("Authorization", "Bearer " + bearerToken)
			   .
		when()
				.get("/user/repos").
		then().assertThat().statusCode(200).and().
			   contentType(ContentType.JSON).
	    extract().response();
		
		String jsonResponse = response.asString();
		
		JsonPath responseBody = new JsonPath(jsonResponse);
		System.out.println("0th Repo Name : " + responseBody.get("[0].name"));
		System.out.println("1th Repo Name : " + responseBody.get("[1].name"));
				
	}

}
