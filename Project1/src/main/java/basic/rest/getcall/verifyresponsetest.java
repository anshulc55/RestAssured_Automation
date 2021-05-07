package basic.rest.getcall;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

public class verifyresponsetest {

	@Test
	public void verifyResponseBody() {
		RestAssured.baseURI = "https://api.trello.com";

		given().param("key", "ccd3215b0059c20cb38867f33346e8f1")
				.param("token", "922731ccc7fc77df3ce1a208830177e367f8c0769a1441e01a796370a45392c9").
		when()
				.get("/1/boards/XJaDDM5o").
		then().assertThat().statusCode(200).and().log().all().
				contentType(ContentType.JSON).and().
				body("name", equalTo("My First Borad")).and().
				body("desc", equalTo("This is Testing Practice Board."));
	}
	
	@Test
	public void verifyResponseHeader() {
		RestAssured.baseURI = "https://api.trello.com";

		given().param("key", "ccd3215b0059c20cb38867f33346e8f1")
				.param("token", "922731ccc7fc77df3ce1a208830177e367f8c0769a1441e01a796370a45392c9").
		when()
				.get("/1/boards/XJaDDM5o").
		then().assertThat().statusCode(200).and().
				contentType(ContentType.JSON).and().
				header("Referrer-Policy", "strict-origin-when-cross-origin").and().
				header("X-Trello-Environment", "Production");
	}

}
