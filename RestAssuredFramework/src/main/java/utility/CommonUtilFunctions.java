package utility;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CommonUtilFunctions {            
	
	public static JsonPath jsonPath;
	
	public static String getResponseKeyValue(String response_body, String response_key) {
		
		jsonPath = new JsonPath(response_body);
		String key_value = jsonPath.get(response_key);
		return key_value;
	}
	
	public static int getStatusCode(Response response) {
		RestFWLogger.initLogger();
		int status_code = response.getStatusCode();
		RestFWLogger.info("Request Response Status Code is - " + status_code);
		return status_code;
	}
	
	public static String getStatusMessage(Response response) {
		RestFWLogger.initLogger();
		String status_message = response.getStatusLine();
		RestFWLogger.info("Request Response Status Message Code is - " + status_message);
		return status_message;
	}
	
	public static String getResponseHeader(Response response, String header_key) {
		RestFWLogger.initLogger();
		String response_header = response.getHeader(header_key);
		RestFWLogger.info("Request Response Header Key is - " + header_key + " And Value is - " + response_header);
		return response_header;
	}
	
	public static String getResponseContentType(Response response) {
		RestFWLogger.initLogger();
		String content_type = response.getContentType();
		RestFWLogger.info("Request Response Content Type is - " + content_type);
		return content_type;
	}
	
	public static int getResponseExecutionTime(Response response) {
		RestFWLogger.initLogger();
		int response_executiontime = (int) response.getTime();
		RestFWLogger.info("Request Response Execution Time is - " + response_executiontime);
		return response_executiontime;
	}

}
