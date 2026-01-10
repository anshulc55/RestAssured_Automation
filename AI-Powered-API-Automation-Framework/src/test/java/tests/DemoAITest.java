package tests;

import java.util.Map;

import org.testng.annotations.Test;

import ai.AIInputWriter;
import ai.AIProcessRunner;
import ai.AIResultReader;
import client.ApiClient;
import io.restassured.response.Response;
import utils.ResponseValidator;

public class DemoAITest extends BaseTest {
	
	@Test
	public void demo_AI_Bridge() {
		Response response = ApiClient.get("/posts", Map.of("userId", 99));
		
		ResponseValidator.validateStatusCode(response, 200);
		
		AIInputWriter.writeInput("demo_AI_Bridge", "/posts", response.getStatusCode(), response.asString());
		
		AIProcessRunner.runPythonAI();
		
		Map<String, Object> aiResult = AIResultReader.readResult();
		
		System.out.println("AI Summery: " + aiResult.get("summary"));
		
	}

}
