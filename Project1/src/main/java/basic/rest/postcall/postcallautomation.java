package basic.rest.postcall;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

public class postcallautomation {
	
	@Test
	public void createBroad() {
		RestAssured.baseURI = "https://api.trello.com";
		String board_name = "My Board From API " + (int)(Math.random()*100);
		System.out.println(board_name);

		given().queryParam("key", "ccd3215b0059c20cb38867f33346e8f1")
			   .queryParam("token", "922731ccc7fc77df3ce1a208830177e367f8c0769a1441e01a796370a45392c9")
			   .queryParam("name", board_name)
			   .header("Content-Type", "application/json").
		when()
				.post("/1/boards").
		then().assertThat().statusCode(200).and().
				contentType(ContentType.JSON).and().
				body("name", equalTo(board_name));
	}
	
}
