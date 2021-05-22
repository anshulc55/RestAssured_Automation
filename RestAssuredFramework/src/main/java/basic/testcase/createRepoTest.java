package basic.testcase;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapibase.BaseClass;

import io.restassured.response.Response;
import resourses.pojoclasses.CreateRepoPOJO;
import utility.Authn;
import utility.CommonUtilFunctions;
import utility.ExtentReporterNG;
import utility.RestFWLogger;
import utility.createURL;
import utility.payloadGenerator;

public class createRepoTest {
	
	Response response;
	ObjectMapper objectMapper;
	String endPoint = createURL.getBaseURI("/user/repos");
	String bearer_Token = Authn.getBearerToken();
	
	@Test
	public void createRepositoryTestCase() throws IOException {
		RestFWLogger.initLogger();
		RestFWLogger.startTestCase("createRepositoryTestCase");
		
		RestFWLogger.info("Step 1 : Generating Payload String");	
		String req_Payload = payloadGenerator.generateStringPayload("createRepo.json");

		RestFWLogger.info("Step 2 : Executing Create repo API");	
		response = BaseClass.postRequest(endPoint, req_Payload, bearer_Token);
		String responseString = response.getBody().asString();
		
		RestFWLogger.info("Step 3 : Validating repository name");
		Assert.assertEquals(CommonUtilFunctions.getResponseKeyValue(req_Payload, "name"), CommonUtilFunctions.getResponseKeyValue(responseString, "name"));
	
		RestFWLogger.info("Step 4 : Validating repository Description");
		Assert.assertEquals(CommonUtilFunctions.getResponseKeyValue(req_Payload, "description"), CommonUtilFunctions.getResponseKeyValue(responseString, "description"));
		RestFWLogger.info("Assertion on Repo Description is Done....");
		
		RestFWLogger.endTestCase();
	}
	
	@Test
	public void createRepoTestCase() throws JsonProcessingException {
		PropertyConfigurator.configure("log4j.properties");
		RestFWLogger.startTestCase("createRepoTestCase");
		RestFWLogger.info("Starting Create Repo Test via POJO");
		RestFWLogger.info("Request URI is - " + endPoint);
		
		CreateRepoPOJO requestPayload = new CreateRepoPOJO();
		requestPayload.setName("Api-testing-restcall-9");
		requestPayload.setDescription("Repository created via Rest Assured Test 2");
		
		objectMapper = new ObjectMapper();
		String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestPayload);
		RestFWLogger.info("Request Payload - " + payload);
		
		response = BaseClass.postRequest(endPoint, payload, bearer_Token);
		String responseString = response.getBody().asString();
		RestFWLogger.info("Request Response - " + responseString);
		
		Assert.assertEquals(requestPayload.getName(), CommonUtilFunctions.getResponseKeyValue(responseString, "name"));
		RestFWLogger.info("Assertion on Repo name is Done....");
		RestFWLogger.info("Request Name - " + requestPayload.getName());
		RestFWLogger.info("Response Name - " + CommonUtilFunctions.getResponseKeyValue(responseString, "name"));
		
		Assert.assertEquals(requestPayload.getDescription(), CommonUtilFunctions.getResponseKeyValue(responseString, "description"));		
		RestFWLogger.info("Assertion on Repo Description is Done....");
		RestFWLogger.info("Request Description - " + requestPayload.getDescription());
		RestFWLogger.info("Response Description - " + CommonUtilFunctions.getResponseKeyValue(responseString, "description"));
		RestFWLogger.endTestCase();
	}
	
	@Test
	public void deleteRepo() throws IOException {
		PropertyConfigurator.configure("log4j.properties");
		RestFWLogger.startTestCase("deleteRepo");
		RestFWLogger.info("Starting Delete Repo Test");
		
		String req_Payload = payloadGenerator.generateStringPayload("createRepo.json");
		RestFWLogger.info("Request Payload is - " + req_Payload); 
		
		String deleteEndPoint = createURL.getBaseURI("/repos/levelup360/") + CommonUtilFunctions.getResponseKeyValue(req_Payload, "name");
		System.out.println(deleteEndPoint);
		response = BaseClass.deleteRequest(deleteEndPoint, bearer_Token);
		Assert.assertEquals(CommonUtilFunctions.getStatusCode(response), 204);
		RestFWLogger.endTestCase();
	}
	
	@Test
	public void deleteRepoPOJO() throws IOException {
		PropertyConfigurator.configure("log4j.properties");
		RestFWLogger.startTestCase("deleteRepoPOJO");
		RestFWLogger.info("Starting Delete Repo Test from POJO Request");
		
		CreateRepoPOJO requestPayload = new CreateRepoPOJO();
		requestPayload.setName("Api-testing-restcall-9");
		requestPayload.setDescription("Repository created via Rest Assured Test 2");
		
		objectMapper = new ObjectMapper();
		String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestPayload);
		RestFWLogger.info("Request Payload - " + payload);
		
		String deleteEndPoint = createURL.getBaseURI("/repos/levelup360/") + requestPayload.getName();
		RestFWLogger.info(deleteEndPoint);
		response = BaseClass.deleteRequest(deleteEndPoint, bearer_Token);
		Assert.assertEquals(CommonUtilFunctions.getStatusCode(response), 204);
		RestFWLogger.info(CommonUtilFunctions.getStatusMessage(response));
		RestFWLogger.endTestCase();
	}

}
