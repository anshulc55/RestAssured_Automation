package JSONResponseValidation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Iterator;

import org.testng.annotations.Test;

public class handleJSONArray {

	@Test
	public void verifyResponseBody() {
		RestAssured.baseURI = "https://api.trello.com";

		Response response = given().param("key", "ccd3215b0059c20cb38867f33346e8f1")
				.param("token", "922731ccc7fc77df3ce1a208830177e367f8c0769a1441e01a796370a45392c9").
		when()
				.get("/1/boards/XJaDDM5o").
		then().assertThat().statusCode(200).and().
				contentType(ContentType.JSON).and().extract().response();
		
		String jsonResponse = response.asString();
		JsonPath responseBody = new JsonPath(jsonResponse);
		
//		System.out.println( "" + responseBody.get("name"));
		//System.out.println("Pref : " + responseBody.get("prefs"));
//		System.out.println("BackGround ID : " + responseBody.get("prefs.background"));
//		System.out.println("BackGround URL : " + responseBody.get("prefs.backgroundImage"));
//		
//		System.out.println("BackGround Image Width : " + responseBody.get("prefs.backgroundImageScaled[2].width"));
		
		int backGroundSize = responseBody.getInt("prefs.backgroundImageScaled.size()");
		
		for (int i = 0; i < backGroundSize; i++) {
			int width = responseBody.get("prefs.backgroundImageScaled["+i+"].width");
			int height = responseBody.get("prefs.backgroundImageScaled["+i+"].height");
			
			System.out.println("BackGround Width at "+i+" is : "+ width +" and Height is : " + height);
		}
			
	}
	
}
