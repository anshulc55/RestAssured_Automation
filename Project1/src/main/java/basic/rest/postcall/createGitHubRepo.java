package basic.rest.postcall;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

public class createGitHubRepo {
	
	@Test
	public void createRepository() {
		RestAssured.baseURI = "https://api.github.com";
		String bearerToken = "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";
		
		System.out.println("Staring Create Repo Test Case");
		

		given()
			   .header("Content-Type", "application/json")
			   .header("Authorization", "Bearer " + bearerToken)
			   .body("{\n"
			   		+ "    \"name\": \"Api-testing-restcall-1\",\n"
			   		+ "    \"description\": \"Repository created via Rest Assured Call\"\n"
			   		+ "}").
		when()
				.post("/user/repos").
		then().assertThat().statusCode(201).and().
				contentType(ContentType.JSON).and().
				body("name", equalTo("Api-testing-restcall-1")).and().
				body("description", equalTo("Repository created via Rest Assured Call"));
		
		System.out.println("Completed Create Repo Test Case");
	}

}
