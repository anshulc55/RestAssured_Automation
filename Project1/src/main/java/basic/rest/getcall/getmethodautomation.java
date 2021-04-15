package basic.rest.getcall;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class getmethodautomation {
	
	//BaseURI
	public static String baseUri = "https://api.trello.com";
	
	
	public static void main(String agrs[]) {
		RestAssured.baseURI = baseUri;
		
		given().
			param("key", "ccd3215b0059c20cb38867f33346e8f1").
			param("token", "922731ccc7fc77df3ce1a208830177e367f8c0769a1441e01a796370a45392c9").
		when().
			get("/1/boards/XJaDDM5o").
		then().
			assertThat().statusCode(200).and().
			contentType(ContentType.JSON).and().
			body("name", equalTo("My First Borad")).and().
			body("desc", equalTo("This is Testing Practice Board."));
		
		System.out.println("This First Get Automation Call.");
		System.out.println("Get Call, Executed Successfully");
	}

}
