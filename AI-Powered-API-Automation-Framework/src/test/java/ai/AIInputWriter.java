package ai;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import org.apache.logging.log4j.ThreadContext;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AIInputWriter {

	private static final String INPUT_PATH = "ai/io/input.json";

	public static void writeInput(String testName, String endpoint, int statusCode, String responseBody) {

		try {
			Map<String, Object> input = new HashMap<>();

			input.put("correlationId", ThreadContext.get("correlationId"));
			input.put("testName", testName);
			input.put("endpoint", endpoint);
			input.put("httpStatus", statusCode);
			input.put("responseBody", responseBody);
			input.put("logsPath", "logs/api-automation.log");

			ObjectMapper mapper = new ObjectMapper();
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File(INPUT_PATH), input);
		} catch (Exception e) {
			throw new RuntimeException("Failed to write AI Input.json", e);
		}

	}

}
